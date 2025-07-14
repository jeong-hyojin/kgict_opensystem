const Utils = (function () {
  async function getData(url) {
    try {
      const response = await fetch(url);

      if (!response.ok) {
        if (response.status >= 500) {
          throw new Error(
            "서버에 오류가 발생했습니다. 잠시 후 다시 시도해주세요."
          );
        } else if (response.status >= 400) {
          throw new Error("요청에 문제가 있습니다. 다시 한번 확인해주세요.");
        }
      }

      return await response.body();
    } catch (error) {
      throw new Error(error.message || "알 수 없는 오류가 발생했습니다.");
    }
  }

  async function postData(url, data) {
    try {
      const response = await fetch(url, {
        method: "POST",
        headers: {
          "Content-Type": "application/json; charset=UTF-8",
        },
        body: JSON.stringify(data),
      });

      if (!response.ok) {
        if (response.status >= 500) {
          throw new Error(
            "서버에 오류가 발생했습니다. 잠시 후 다시 시도해주세요."
          );
        } else if (response.status >= 400) {
          throw new Error("요청에 문제가 있습니다. 다시 한번 확인해주세요.");
        }
      }
      return await response.body();
    } catch (error) {
      throw new Error(error.message || "알 수 없는 오류가 발생했습니다.");
    }
  }
})();

/*
  설명     : 게시물 랜더링 함수
  주사용처 : 공통
  작성자   : 김형주
  작성일   : 2025-07-14
*/
