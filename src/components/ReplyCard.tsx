import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import { Timestamp } from 'firebase/firestore';

type ReplyCardProps = {
  count: number
  name: string
  date: Timestamp
  body: string
}

const ReplyCard = ({count, name, date, body}: ReplyCardProps)  => {
  return (
    <Card variant='outlined'>
      <CardContent>
        <Typography variant="subtitle1" color="textSecondary">
          {count} : 名前: {name}
        </Typography>
        <Box sx={{display: 'flex', justifyContent: 'space-between'}}>
          <Typography variant="body1" color="textPrimary" sx={{ whiteSpace: "pre-wrap" }}>
            {body}
          </Typography>
          <Typography variant="body2" color="textSecondary">
          {new Date(date.seconds * 1000).toLocaleDateString()} {new Date(date.seconds * 1000).toLocaleTimeString()}
          </Typography>
        </Box>
      </CardContent>
    </Card>
  );
}

export default ReplyCard;
