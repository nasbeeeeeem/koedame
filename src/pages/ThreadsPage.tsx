import React, { useState } from 'react';
import { useLoaderData, useParams } from 'react-router-dom';
import BottomNavigationBar from '../components/BottomNavigationBar';

import ThreadCard from '../components/ThreadCard';
import { Box } from '@mui/material';
import CreateThreadForm from '../components/CreateThreadForm';
import { Thread } from '../loaders/threadsLoader';

const ThreadsPage: React.FC = () => {

  const { categoryId } = useParams<{ categoryId: string}>();
  if(!categoryId) {
    return <>指定されたカテゴリーIDが見つかりません</>
  }

  const threads = useLoaderData() as Thread[];

  const [isModalOpen, setIsModalOpen] = useState(false);

  const openModal = () =>setIsModalOpen(true);

  const closeModal = () => setIsModalOpen(false);

  return (
    <div>
      <Box sx={{marginTop: 10}}>
        {threads.map(thread => (
          <ThreadCard 
          key={thread.id}
          id={thread.id}
          title={thread.title}
          body={thread.body}
          category_id={thread.category_id}
          count={thread.reply_count}
          created_at={thread.created_at}
          />
        ))}
      </Box>
      <BottomNavigationBar onThreadCreateClick={openModal}/>
      <CreateThreadForm isOpen={isModalOpen} onClose={closeModal} categoryId={categoryId}/>
    </div>
  );
};

export default ThreadsPage;
