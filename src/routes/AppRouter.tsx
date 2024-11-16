import { createBrowserRouter } from 'react-router-dom';
import ThreadsPage from '../pages/ThreadsPage';
import ThreadDetailPage from '../pages/ThreadDetailPage';
import { threadsLoader } from '../loaders/threadsLoader';
import { threadLoader } from '../loaders/threadLoader';
import createThreadAction from '../actions/createThreadAction';
import createReplyAction from '../actions/createReplyAction';

const AppRouter = createBrowserRouter([
  {
    path: "/categories/:categoryId/threads",
    element: <ThreadsPage />,
    loader: threadsLoader,
    action: createThreadAction,
  },
  {
    path: "/categories/:categoryId/threads/:threadId",
    element: <ThreadDetailPage />,
    loader: threadLoader,
    action: createReplyAction,
  }
])

export default AppRouter;
