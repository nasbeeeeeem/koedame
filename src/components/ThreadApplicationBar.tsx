import { AppBar, Box, IconButton, Toolbar, Typography } from "@mui/material";
import ThemeSwitcherIcon from "./ThemeSwitchIcon";
import { ArrowBackIos } from "@mui/icons-material";
import { useNavigate } from "react-router-dom";

interface ThreadApplicationBarProps {
  subject: string;
}

const ThreadApplicationBar = ( { subject } :ThreadApplicationBarProps) => {

  const navigate = useNavigate();

  const handleOnClick = () => {
    navigate("/categories/BpSYufXXs6D30ebTtPND/threads");
  }
  return(
    <Box sx={{ flexGrow:1 }}>
      <AppBar position="fixed">
        <Toolbar>
        <IconButton
            size="large"
            edge="start"
            color="inherit"
            aria-label="menu"
            sx={{ mr: 2 }}
            onClick={handleOnClick}
          >
            <ArrowBackIos />
          </IconButton>
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

export default ThreadApplicationBar;
