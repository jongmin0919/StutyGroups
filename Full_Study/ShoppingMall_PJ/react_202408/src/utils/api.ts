import axios, { type AxiosResponse } from "axios";

import type { ProductType } from "../types";

type ReturnType<T> = Promise<AxiosResponse<T>>;

// Axios 인스턴스 생성
const apiClient = axios.create({
  baseURL: "/api", // 공통 base URL 설정
});

apiClient.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("jwtToken");
    console.log("JWT Token:", token); // 토큰이 제대로 설정되었는지 확인
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export const getProducts = async (): Promise<ProductType[]> => {
  try {
    const response: AxiosResponse<ProductType[]> = await apiClient.get(
      "/product/all"
    );
    return response.data;
  } catch (error) {
    throw error;
  }
};
// ProductType을 export합니다.
export type { ProductType };

export const getProduct = async (id: string): Promise<ProductType> => {
  try {
    const response: AxiosResponse<ProductType> = await apiClient.get(
      `/product/${id}`
    );
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const createProduct = async (
  newProduct: Omit<ProductType, "id" | "thumbnail">
): ReturnType<{ product: ProductType }> => {
  try {
    const response = await apiClient.post("/product", newProduct);
    return response;
  } catch (error) {
    throw error;
  }
};

export const modifyThumbnail = async (
  productId: string,
  thumbnail: File
): ReturnType<{ product: ProductType }> => {
  try {
    const formData = new FormData();
    formData.append("thumbnail", thumbnail);

    const response = await apiClient.patch(
      `/edit/product/thumbnail/${productId}`,
      formData
    );
    return response;
  } catch (error) {
    throw error;
  }
};

export const deleteProduct = async (id: string) => {
  try {
    const response = await apiClient.delete(`/delete/product/${id}`);

    return response;
  } catch (error: any) {
    // 403 에러일 때 커스텀 에러 메시지 노출
    if (error.response && error.response.status === 403) {
      alert(error.response.data);
      return;
    }

    // 다른 에러는 그대로 throw
    throw error;
  }
};

export const modifyProduct = async (updateProduct: ProductType) => {
  try {
    const response = await apiClient.patch(
      `/edit/product/${updateProduct.id}`,
      updateProduct
    );
    return response;
  } catch (error) {
    throw error;
  }
};

export const addCarts = async (productId: string) => {
  try {
    const response = await apiClient.post("/cart", { productId });
    return response.data;
  } catch (error) {
    console.error("Failed to add product to cart", error);
    throw error;
  }
};
