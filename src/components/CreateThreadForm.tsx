import { Box, Button, Modal, TextField, Typography } from "@mui/material";
import { Controller, useForm } from "react-hook-form";
import { Form, useSubmit } from "react-router-dom";

interface CreateThreadFormProps {
  isOpen: boolean;
  onClose: () => void;
  categoryId: string;
}

interface FormInputs {
  title: string;
  body: string;
}

const CreateThreadForm = ({ isOpen, onClose, categoryId }: CreateThreadFormProps) => {
  const submit = useSubmit();
  const { control, handleSubmit, reset } = useForm<FormInputs>({
    defaultValues: {
      title: "",
      body: "",
    },
  });

  // バリデーション成功時に呼ばれる関数
  const onSubmit = (data: FormInputs) => {

    const formData = new FormData();

    formData.append("title", data.title);
    formData.append("body", data.body);

    submit(formData, {
      method: "post",
      action: `/categories/${categoryId}/threads`,
      encType: "multipart/form-data"
    })

    reset();
    onClose();
  };

  // 改行を1つにまとめる関数
  const normalizeNewlines = (str: string) => {
    return str
    .split(/\n/)
    .filter((line, index, arr) => {
      // 前後に文字がない場合の連続改行は除去し1つにする
      return !(line === "" && arr[index - 1] === "");
    })
    .join("\n");
  };

  // 改行以外の文字数をカウントする関数
  const countNonNewlineChars = (str: string) => {
    return str.replace(/\n/g, "").length; // 改行を除いた文字数をカウント
  };

  // 行数を数える関数
  const countLines = (str: string) => {
    return str.split("\n").length;
  }

  return (
    <Modal open={isOpen} onClose={onClose}>
      <Box
        sx={{
          position: "absolute",
          top: "50%",
          left: "50%",
          transform: "translate(-50%, -50%)",
          width: 400,
          bgcolor: "background.paper",
          borderRadius: 2,
          boxShadow: 24,
          p: 4,
        }}
      >
        <Typography variant="h6" component="h2" sx={{ mb: 2 }}>
          新しいスレッドを作成
        </Typography>

        {/* react-router-domのFormを使用 */}
        <Form onSubmit={handleSubmit(onSubmit)}>
          {/* タイトルの入力欄 */}
          <Controller
            name="title"
            control={control}
            rules={{ 
              required: "タイトルは必須です",
              validate: {
                maxLength: (value) =>
                  countNonNewlineChars(value) <= 40 || "本文が長すぎます（本文は40文字以内）",
                minLength: (value) => 
                  countNonNewlineChars(value) >=3 || "本文が短すぎます（本文は3文字以上）",
                noOnlyWhitespace: (value) =>
                  value.trim().length > 0 || "本文が空です",
              },
            }}
            render={({ field, fieldState }) => (
              <TextField
                {...field}
                label="タイトル"
                variant="outlined"
                fullWidth
                error={!!fieldState.error}
                helperText={fieldState.error?.message}
                sx={{ mb: 2 }}
              />
            )}
          />

          {/* 本文入力欄 */}
          <Controller
            name="body"
            control={control}
            rules={{ 
              required: "本文は必須です",
              validate: {
                maxLength: (value) =>
                  countNonNewlineChars(value) <= 40 || "本文が長すぎます（本文は40文字以内）",
                minLength: (value) => 
                  countNonNewlineChars(value) >=3 || "本文が短すぎます（本文は3文字以上）",
                maxLines: (value) => 
                  countLines(normalizeNewlines(value)) <= 5 || "改行が多すぎます（改行は5行以内）",
                noOnlyWhitespace: (value) =>
                  value.trim().length > 0 || "本文が空です",
              },
            }}
            render={({ field, fieldState }) => (
              <TextField
                {...field}
                label="本文"
                variant="outlined"
                multiline
                rows={5}
                fullWidth
                error={!!fieldState.error}
                helperText={fieldState.error?.message}
                sx={{ mb: 2 }}
              />
            )}
          />

          {/* スレッド作成ボタン */}
          <Button variant="contained" color="primary" fullWidth type="submit">
            スレッド作成
          </Button>
        </Form>
      </Box>
    </Modal>
  );
};

export default CreateThreadForm;
