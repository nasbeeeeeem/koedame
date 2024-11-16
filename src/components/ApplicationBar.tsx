import { AppBar, Box, IconButton, Toolbar, Typography } from "@mui/material";
import ThemeSwitcherIcon from "./ThemeSwitchIcon";

interface ApplicationBarProps {
  subject: string;
}

const ApplicationBar= ({ subject } :ApplicationBarProps) => {
  return(
    <Box sx={{ flexGrow:1 }}>
      <AppBar position="fixed">
        <Toolbar>
          <Typography variant="h5" component={"div"} sx={{flexGrow: 1}}>
            {subject}
          </Typography>
          <IconButton
          size="large"
            edge="start"
            color="inherit"
            aria-label="menu"
            sx={{ mr: 2 }}
          >
            <ThemeSwitcherIcon />
          </IconButton>
        </Toolbar>
      </AppBar>
    </Box>
  );
}

export default ApplicationBar;
