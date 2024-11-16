import { ActionFunctionArgs, redirect } from "react-router-dom";
import { collection, addDoc, doc, Timestamp } from "firebase/firestore";
import { db } from "../lib/firebase";

const createThreadAction = async ({ request, params }: ActionFunctionArgs) => {
  // フォームデータを取得
  const formData = await request.formData();
  const title = formData.get("title") as string;
  const body = formData.get("body") as string;

  // パスパラメータを取得
  const { categoryId } = params;
  if(!categoryId) {
    throw new Error("指定のカテゴリIDが見つかりません")
  }
  

  // Firestoreにスレッドを追加
  try {
    // カテゴリのドキュメント参照を取得
    const categoryDocRef = doc(db, "categories", categoryId);
    
    // サブコレクション「threads」に新しいドキュメントを追加
    const threadsCollectionRef = collection(categoryDocRef, "threads");
    const docRef = await addDoc(threadsCollectionRef, {
      title,
      body,
      category_id: categoryId,
      created_at: Timestamp.fromDate(new Date()),
      updated_at: Timestamp.fromDate(new Date()),
      deleted_at: null, 
      reply_count: 1,
      user_ip: "xxx.xxx.xxx.xxx", // 実際のIPアドレスは別途取得する
    });

    // 作成したスレッドのIDを取得し、スレッド詳細ページにリダイレクト
    const threadId = docRef.id;

    // 作成したスレッドの「replies」に新しいドキュメントを追加
    const replysCollenctionRef = collection(db, `/categories/${categoryId}/threads/${threadId}/replies`);
    await addDoc(replysCollenctionRef, {
      body,
      thread_id: threadId,
      parent_id: null,
      user_ip: "xxx.xxx.xxx.xxx",
      created_at: Timestamp.fromDate(new Date()),
      updated_at: Timestamp.fromDate(new Date()),
      deleted_at: null,
    });

    // 作成したスレッドのページへリダイレクト
    return redirect(`/categories/${categoryId}/threads/${threadId}`);
  } catch (error) {
    console.error("スレッド作成に失敗しました", error);
    throw new Error("スレッド作成に失敗しました");
  }
};

export default createThreadAction;
