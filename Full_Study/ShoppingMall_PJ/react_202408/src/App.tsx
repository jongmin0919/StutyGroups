// App.tsx
import { Route, Routes } from "react-router-dom";
import { Layout } from "./components/shared";
import { CartPage, HomePage, NotFoundPage, ProductCreatePage, ProductPage, PurchasePage, LoginPage, JoinPage, ProductEditPage } from "./pages";
import { AuthProvider } from "./contexts/AuthContext"; // AuthProvider 추가

//수정화면.
function App() {
  return (
    <AuthProvider>
      <Layout>
        <Routes>
          <Route index element={<HomePage />} />
          <Route path="create" element={<ProductCreatePage />} />
          <Route path="cart" element={<CartPage />} />
          <Route path="login" element={<LoginPage />} />
          <Route path="join" element={<JoinPage />} />
          <Route path="product/:productId" element={<ProductPage />} />
          <Route path="product/edit/:productId" element={<ProductEditPage />} />
          <Route path="purchase/:productId" element={<PurchasePage />} />
          <Route path="*" element={<NotFoundPage />} />
        </Routes>
      </Layout>
    </AuthProvider>
  );
}

export default App;
