import { useState, useMemo, useEffect } from 'react';
import { RouterProvider } from 'react-router-dom';
import { ThemeProvider, createTheme, CssBaseline} from '@mui/material';
import { AppBar, IconButton, Toolbar, Typography } from "@mui/material";
import AppRouter from './routes/AppRouter';
import { DarkMode, LightMode } from '@mui/icons-material';

function App() {
  // useStateでテーマモードの状態を管理
  const [darkMode, setDarkMode] = useState(false);

  // 現在のテーマに応じてアイコンを切り替える
  const currentIcon = darkMode ? <LightMode /> : <DarkMode />;

  // useMemoを使ってテーマを動的に変更する
  const theme = useMemo(
    () =>
      createTheme({
        palette: {
          mode: darkMode ? 'dark' : 'light',
          primary: {
            main: darkMode ? '#90caf9' : '#1976d2', // ダークモードとライトモードのプライマリカラー
          },
          secondary: {
            main: darkMode ? '#ffffff' : '#ffffff', // ダークモードとライトモードのセカンダリカラー
          },
          background: {
            default: darkMode ? '#383737ff' : '#ddd9d9ff', // 全体のバックグラウンドカラー
            paper: darkMode ? '#424242' : '#ffffff',   // カードやモーダルなどの背景色
          },
          text: {
            primary: darkMode ? '#ffffff' : '#000000',  // ダークモードとライトモードでのテキストカラー
            secondary: darkMode ? '#aaaaaa' : '#666666',
          }
        },
      }),
    [darkMode]
  );

  // テーマ変更時にローカルストレージへ保存する
  const toggleTheme = () => {
    setDarkMode((prevMode) => {
      const newMode = !prevMode;
      localStorage.setItem('darkMode', JSON.stringify(newMode));
      return newMode;
    });
  };

  // ローカルストレージに保存されたテーマ設定を初回レンダー時に通用
  useEffect(() => {
    const saveMode = localStorage.getItem('darkMode');
    if(saveMode) {
      setDarkMode(JSON.parse(saveMode));
    }
  }, []);

  return (
    <ThemeProvider theme={theme}>

      {/* グローバルに適用するCSSリセット */}
      <CssBaseline />

      <AppBar position="fixed">
        <Toolbar>
          <Typography color="secondary" variant="h5" component={"div"} sx={{flexGrow: 1}}>
            Koedame
          </Typography>
          <IconButton
          size="large"
            edge="start"
            color="inherit"
            aria-label="menu"
            onClick={toggleTheme}
            sx={{ mr: 2 }}
          >
            {currentIcon}
          </IconButton>
        </Toolbar>
      </AppBar>
      <RouterProvider router={AppRouter} />
    </ThemeProvider>
  );
}

export default App;
