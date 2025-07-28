/*
  설명    : 공통 함수 정의
  주사용처 : 공통
  작성자   : 
  작성일   : 2025-07-14
*/
const Utils = (function () {
  /**
   * 공통 GET 요청 함수
   * @param {string} url
   */
  async function getData(url) {
    try {
      const response = await fetch(url);

      if (!response.ok) {
        if (response.status >= 500) {
          throw new Error(
            "서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요."
          );
        } else if (response.status >= 400) {
          throw new Error("요청에 문제가 있습니다. 다시 확인해주세요.");
        }
      }

      return await response.json();
    } catch (error) {
      throw new Error(error.message || "알 수 없는 오류가 발생했습니다.");
    }
  }

  /**
   * 공통 POST 요청 함수
   * @param {string} url
   * @param {object} data
   */
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
            "서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요."
          );
        } else if (response.status >= 400) {
          throw new Error("요청에 문제가 있습니다. 다시 확인해주세요.");
        }
      }

      return await response.json();
    } catch (error) {
      throw new Error(error.message || "알 수 없는 오류가 발생했습니다.");
    }
  }

  return {
    getData,
    postData,
  };
})();
