import { ActionFunctionArgs, redirect } from "react-router-dom";
import { collection, addDoc, Timestamp, updateDoc, doc, increment } from "firebase/firestore";
import { db } from "../lib/firebase";

const createReplyAction = async ({ request, params }: ActionFunctionArgs) => {
  
  const formData = await request.formData();
  const body = formData.get("body");

  const { categoryId, threadId } = params;
  try {
    
    // コメントの書き込み
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
    
    // コメント数の更新
    const threadsDocRef = doc(db, `/categories/${categoryId}/threads/${threadId}`);
    await updateDoc(threadsDocRef, {
      reply_count: increment(1),
    })

  } catch (error) {
    console.error("投稿に失敗しました。")
    throw new Error("投稿に失敗しました。")
  }


  return redirect(`/categories/${categoryId}/threads/${threadId}`);
}

export default createReplyAction;
