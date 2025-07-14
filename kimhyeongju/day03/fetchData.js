const API_URL = "https://jsonplaceholder.typicode.com/posts/1";

export const getFetchData = async () => {
  try {
    // fetch로 데이터 습득
    let response = await fetch(API_URL); // fetch는 HTTP요청을 비동기로 보내고, 결과를 promise로 반환함.
    if (!response.ok) throw new Error();

    // form 세팅
    let data = await response.json(); // await을 붙이면, 이 줄에서 요청이 끝날 때까지 기다림.
    document.getElementById("title").value = data.title;
    document.getElementById("content").value = data.body;
    document.getElementById("username").value = data.userId;
  } catch (err) {
    console.error("error:", err);
  }
};
