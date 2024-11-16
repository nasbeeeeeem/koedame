import * as React from 'react';
import Box from '@mui/material/Box';
import CssBaseline from '@mui/material/CssBaseline';
import BottomNavigation from '@mui/material/BottomNavigation';
import BottomNavigationAction from '@mui/material/BottomNavigationAction';
import FavoriteIcon from '@mui/icons-material/Favorite';
import CreateIcon from '@mui/icons-material/Create';
import Paper from '@mui/material/Paper';
import { Book, Home } from '@mui/icons-material';

interface BottomNavigationBarProps {
  onThreadCreateClick: () => void;
}

const BottomNavigationBar = ({ onThreadCreateClick }: BottomNavigationBarProps ) => {
  const [value, setValue] = React.useState(0);
  const ref = React.useRef<HTMLDivElement>(null);

  return (
    <Box sx={{ pb: 7 }} ref={ref}>
      <CssBaseline />
      <Paper sx={{ position: 'fixed', bottom: 0, left: 0, right: 0 }} elevation={3}>
        <BottomNavigation
          showLabels
          value={value}
          onChange={(_event, newValue) => {
            setValue(newValue);
          }}
        > 
          <BottomNavigationAction label="トップ＆更新" icon={<Home />} />
          <BottomNavigationAction label="スレ作成" icon={<CreateIcon />} onClick={onThreadCreateClick} />
          <BottomNavigationAction label="お気に入り" icon={<FavoriteIcon />} />
          <BottomNavigationAction label="履歴" icon={<Book />} />
        </BottomNavigation>
      </Paper>
    </Box>
  );
}

export default BottomNavigationBar;
