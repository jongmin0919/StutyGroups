import React from "react";

export default function Banner() {
  return (
    <div
      style={{
        width: "500px",
        height: "100px",
        display: "flex",
        alignItems: "center",
      }}
    >
      <img
        src="/img/htmlcss.png"
        alt=""
        style={{ width: "100px", borderRadius: "5px", marginRight: "15px" }}
      />
      <div style={{ width: "500px" }}>
        <p style={{ margin: 0 }}>입문자를 위한, HTML&CSS 웹 개발 입문</p>
        <p style={{ margin: 0 }}>웹 개발에 필요한 기본 지식을 배웁니다.</p>
      </div>
    </div>
  );
}
