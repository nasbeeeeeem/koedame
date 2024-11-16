import React from 'react';
import { useLoaderData, useParams } from 'react-router-dom';
import { ThreadDetail } from '../loaders/threadLoader';
import ReplyCard from '../components/ReplyCard';
import ReplyForm from '../components/ReplyForm';
import { Box, Typography } from '@mui/material';

const ThreadDetailPage: React.FC = () => {

  const { categoryId, threadId } = useParams<{categoryId: string, threadId: string}>();

  const threadDetail = useLoaderData() as ThreadDetail;

  return (
    <div>
      <Typography variant="h4" sx={{ marginTop: 10, paddingBottom: 3}}>{threadDetail.title}</Typography>
      <Box sx={{ marginBottom: 30}}>
        {threadDetail.replies.map((reply, index) => (
          <ReplyCard count={index + 1} name="名無し" date={reply.created_at} body={reply.body} />
        ))}
      </Box>
      <ReplyForm categoryId={categoryId as string} threadId={threadId as string}/>
    </div>
  );
};

export default ThreadDetailPage;
