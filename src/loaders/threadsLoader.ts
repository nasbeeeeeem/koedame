import { collection, getDocs, Timestamp } from "firebase/firestore"
import { db } from "../lib/firebase"
import { json } from "react-router-dom"

export interface Thread {
  id: string
  title: string
  body: string
  reply_count: number
  user_ip: string
  category_id: string
  created_at: Timestamp
  updated_at: Timestamp
  deleted_at: Timestamp
}

export const threadsLoader = async ({ params }: any) =>  {
  const { categoryId } = params;

  if(!categoryId) {
    throw new Error("カテゴリーIDは必須項目です");
  }

  // `categories/:categoryID/threads` コレクションを参照
  const threadsCollection = collection(db, `categories/${categoryId}/threads`);
  
  // スレッドコレクションのデータを取得
  const querySnapshot = await getDocs(threadsCollection);

  // 取得したデータを整形して返す
  const threads: Thread[] = querySnapshot.docs.map(doc => ({
    id: doc.id,
    ...doc.data(),
  })) as Thread[];

  return json(threads);
}
