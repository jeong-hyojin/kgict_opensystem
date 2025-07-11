import { error_messages } from "./constant/Message.js";

// API 호출
export const fetchData = async function fetchData(uri) {
    try {
        const response = await fetch(uri);
        if(!response.ok) {
          throw new Error(`${error_messages.ERROR_FETCHGING_DATA} 에러 상태 코드 ${response.status}`);
        }
        return await response.json();
    } catch (e) {
        alert(`${error_messages.ERROR_FETCHGING_DATA} ${e}`);
        throw e;
    }
};