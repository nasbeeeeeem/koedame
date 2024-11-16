import { collection, doc, getDoc, getDocs, orderBy, query, Timestamp } from "firebase/firestore"
import { db } from "../lib/firebase"
import { json } from "react-router-dom"


interface Reply {
  id: string
  body: string
  parent_id: string
  thread_id: string
  created_at: Timestamp
  updated_at: Timestamp
  deleted_at: Timestamp
  user_ip: string
}
export interface ThreadDetail {
  title: string,
  replies: Reply[],
}

const getThreadById = async (categoryId: string , threadId: string) => {
  const threadDocRef = doc(db, `categories/${categoryId}/threads`, threadId);

  const threadDocSnap = await getDoc(threadDocRef);

  if (threadDocSnap.exists()) {

    const threadData = threadDocSnap.data();

    return threadData;
  } else {
    
    return null;
  }
}

export const threadLoader = async ({ params }: any) => {
  const { categoryId, threadId } = params

  if (!categoryId) {
    throw new Error("カテゴリーIDは必須項目です")
  }

  if(!threadId) {
    throw new Error("スレッドIDは必須項目です")
  }

  const threadData = await getThreadById(categoryId, threadId);

  const title = threadData?.title;

  const replyCollection = collection(db, `categories/${categoryId}/threads/${threadId}/replies`)

  const replyQuery = query(replyCollection, orderBy("created_at", "asc"));

  const querySnapshot = await getDocs(replyQuery);

  

  const replies: Reply[] = querySnapshot.docs.map(doc => ({
    id: doc.id,
    ...doc.data(),
  })) as Reply[];

  const threadDetail: ThreadDetail = {
    title: title,
    replies: replies,
  }

  return json(threadDetail);
}
