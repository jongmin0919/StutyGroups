// src/context/AuthContext.tsx
import React, { createContext, useContext, useState, ReactNode } from "react";

interface AuthContextType {
  isLoggedIn: boolean;
  jwtToken: string | null;
  role: string | null;
  login: (token: string, role: string) => void;
  logout: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider = ({ children }: { children: ReactNode }) => {
  const [jwtToken, setJwtToken] = useState<string | null>(localStorage.getItem("jwtToken"));
  const [role, setRole] = useState<string | null>(localStorage.getItem("role"));
  const [isLoggedIn, setIsLoggedIn] = useState<boolean>(!!jwtToken);

  const login = (token: string, role: string) => {
    localStorage.setItem("jwtToken", token);
    localStorage.setItem("role", role);
    setJwtToken(token);
    setRole(role);
    setIsLoggedIn(true);
  };

  const logout = () => {
    localStorage.removeItem("jwtToken");
    localStorage.removeItem("role");
    setJwtToken(null);
    setRole(null);
    setIsLoggedIn(false);
  };

  return <AuthContext.Provider value={{ isLoggedIn, jwtToken, role, login, logout }}>{children}</AuthContext.Provider>;
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (context === undefined) {
    throw new Error("useAuth must be used within an AuthProvider");
  }
  return context;
};
