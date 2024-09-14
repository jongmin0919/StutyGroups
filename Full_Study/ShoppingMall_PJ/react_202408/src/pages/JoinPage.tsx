import { useState } from "react";
import { Box, TextField, Button, Typography, MenuItem, Select, FormControl, InputLabel } from "@mui/material";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const JoinPage = () => {
  const [id, setId] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [confirmPassword, setConfirmPassword] = useState<string>("");
  const [name, setName] = useState<string>("");
  const [role, setRole] = useState<string>("user");
  const [error, setError] = useState<string | null>(null);
  const navigate = useNavigate();

  const handleSignUp = async () => {
    if (password !== confirmPassword) {
      setError("비밀번호가 일치하지 않습니다.");
      return;
    }

    try {
      const response = await axios.post(
        "/join",
        {
          userid: id,
          password,
          username: name,
          role,
        },
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      if (response.status === 201) {
        // 회원가입 성공 후 페이지 이동
        navigate("/login"); // 로그인 페이지로 이동
      }
    } catch (error) {
      console.error("회원가입 실패:", error);
      setError("회원가입에 실패했습니다. 다시 시도하세요.");
    }
  };

  return (
    <Box display="flex" justifyContent="center" alignItems="center" height="100vh" flexDirection="column" gap={2}>
      <Typography variant="h4" sx={{ marginBottom: 4 }}>
        회원가입
      </Typography>

      {error && (
        <Typography color="error" sx={{ marginBottom: 2 }}>
          {error}
        </Typography>
      )}

      <TextField label="ID" variant="outlined" value={id} onChange={(e) => setId(e.target.value)} fullWidth />

      <TextField label="Password" variant="outlined" type="password" value={password} onChange={(e) => setPassword(e.target.value)} fullWidth />

      <TextField label="Confirm Password" variant="outlined" type="password" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} fullWidth />

      <TextField label="Name" variant="outlined" value={name} onChange={(e) => setName(e.target.value)} fullWidth />

      <FormControl fullWidth>
        <InputLabel id="role-label">Role</InputLabel>
        <Select labelId="role-label" value={role} onChange={(e) => setRole(e.target.value as string)} label="Role">
          <MenuItem value="user">User</MenuItem>
          <MenuItem value="admin">Admin</MenuItem>
        </Select>
      </FormControl>

      <Button variant="contained" color="primary" onClick={handleSignUp} fullWidth>
        회원가입
      </Button>
    </Box>
  );
};

export default JoinPage;
