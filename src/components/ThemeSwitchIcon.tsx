import React, { useState } from 'react';
import { IconButton, Menu, MenuItem } from '@mui/material';
import { DarkMode,  LightMode } from '@mui/icons-material';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';

const ThemeSwitcherIcon = () => {
  // テーマの状態管理
  const [themeMode, setThemeMode] = useState<'light' | 'dark' >('light');
  const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);

  // メニューを開く
  const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
    setAnchorEl(event.currentTarget);
  };

  // メニューを閉じる
  const handleClose = () => {
    setAnchorEl(null);
  };

  // テーマを変更する
  const handleThemeChange = (mode: 'light' | 'dark' ) => {
    setThemeMode(mode);
    handleClose();
  };

  // 現在のテーマに応じてアイコンを切り替える
  const currentIcon = themeMode === 'dark' ? <DarkMode /> : <LightMode />;

  // テーマのカスタム設定
  const theme = createTheme({
    palette: {
      mode: themeMode,
    },
  });

  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <IconButton
        aria-label="theme-switcher"
        onClick={handleClick}
        color="inherit"
      >
        {currentIcon}
      </IconButton>
      <Menu
        anchorEl={anchorEl}
        open={Boolean(anchorEl)}
        onClose={handleClose}
      >
        <MenuItem onClick={() => handleThemeChange('light')}>
          <LightMode /> Light
        </MenuItem>
        <MenuItem onClick={() => handleThemeChange('dark')}>
          <DarkMode /> Dark
        </MenuItem>
      </Menu>
    </ThemeProvider>
  );
};

export default ThemeSwitcherIcon;
