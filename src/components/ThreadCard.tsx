import { Box, Card, CardContent, Typography } from "@mui/material"
import { Timestamp } from "firebase/firestore"
import { useNavigate } from "react-router-dom"

interface ThreadCardProps {
  id: string
  title: string
  body: string
  category_id: string
  created_at: Timestamp
  count: number
}

const ThreadCard = ({id, title, body, category_id, count, created_at}: ThreadCardProps) => {
  const naviget = useNavigate();

  const handleClick = () => {
    naviget(`/categories/${category_id}/threads/${id}`);
  };

  return(
    <Card
      onClick={handleClick}
      sx={{
        cursor: "pointer",
        marginBottom: 0.5,
        '&:hover': {
          boxShadow: 10,   // ホバー時にシャドウ効果を追加
        },
      }}
    >
      <CardContent>
        <Typography variant="h6" color="textPrimary">
          {title} ({count})
        </Typography>
        <Box sx={{ display: 'flex', justifyContent: 'space-between'}}>
          <Typography variant="body2" color="textSecondary">
            {body}
          </Typography>
          <Typography variant="body2" color="textSecondary">
            {new Date(created_at.seconds * 1000).toLocaleDateString()} {new Date(created_at.seconds * 1000).toLocaleTimeString()}
          </Typography>
        </Box>
      </CardContent>
    </Card>
  );
}

export default ThreadCard;
