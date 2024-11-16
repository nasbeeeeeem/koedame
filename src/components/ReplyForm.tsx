import { Box, Button, TextField } from "@mui/material";
import { Controller, useForm } from "react-hook-form";
import { Form, useSubmit } from "react-router-dom";

interface ReplyFormProps {
  categoryId: string;
  threadId: string;
}

interface FormInputs {
  body: string;
}


const ReplyForm = ({ categoryId, threadId}: ReplyFormProps) => {  
  const submit = useSubmit();
  const { control, handleSubmit ,reset } = useForm<FormInputs>({
    defaultValues: {
      body: "",
    },
  });

  // バリデーション成功時に呼ばれる関数
  const onSubmit = (data: FormInputs) => {

    const normalizedBody = normalizeNewlines(data.body)

    const formData = new FormData();
    formData.append("body", normalizedBody);

    submit(formData, {
      method: "post",
      action: `/categories/${categoryId}/threads/${threadId}`,
      encType: "multipart/form-data"
    })

    reset();
  }
  
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

  return(
    <Box sx={{
        position: 'fixed',      // 常に表示されるように固定
        bottom: 50,             // 画面下に配置
        width: 400,             // パネルの幅
        padding: 2,             // 内側の余白
        boxShadow: 3,           // 影をつける
        borderRadius: 2,        // 角を少し丸める
        backgroundColor: 'background.paper',
        zIndex: 1000,
      }}
    >
      <Form onSubmit={handleSubmit(onSubmit)}>
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
        render={({ field, fieldState}) => (
          <TextField
            {...field}
            variant="outlined"
            fullWidth
            multiline
            minRows={3}
            maxRows={5}
            error={!!fieldState.error}
            helperText={fieldState.error?.message}
            sx={{ marginBottom: 2 }}
            />
        )}
      />

      <Button
        variant="contained"
        color="primary"
        fullWidth
        type="submit"
        >
        投稿する
      </Button>
      </Form>
    </Box>
  );
}

export default ReplyForm;
