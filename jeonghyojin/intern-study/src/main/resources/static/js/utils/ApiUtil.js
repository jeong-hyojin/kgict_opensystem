/**
 * ApiUtil - axios 기반 HTTP 유틸리티
 */

/**
 * HTTP GET 요청을 처리하는 함수
 * 
 * @param {string} url 요청 URL
 * @param {object} params = {} 쿼리 파라미터 객체
 * @returns {Promise<any>} 성공 시 응답 데이터
 * @throws {Error} 실패 시 예외 발생 및 알림 표시
 */
async function get(url, params = {}) {
    try {
        const response = await axios.get(url, {params});
        return handleApiResponse(response);
    } catch (error) {
        handleApiError(error);
        throw error;
    }
}

/**
 * HTTP POST 요청을 처리하는 함수
 * 
 * @param {string} url 요청 URL
 * @param {object} data = {} 전송할 파라미터 객체
 * @returns {Promise<any>} 성공 시 응답 데이터
 * @throws {Error} 실패 시 예외 발생 및 알림 표시
 */
async function post(url, data = {}) {
    try {
        const response = await axios.post(url, data);
        return handleApiResponse(response);
    } catch (error) {
        handleApiError(error);
        throw error;
    }
}

/**
 * HTTP PUT 요청을 처리하는 함수
 * 
 * @param {string} url 요청 URL
 * @param {object} data = {} 수정할 데이터 객체
 * @returns {Promise<any>} 성공 시 응답 데이터
 * @throws {Error} 실패 시 예외 발생 및 알림 표시
 */
async function put(url, data = {}) {
    try {
        const response = await axios.put(url, data);
        return handleApiResponse(response);
    } catch (error) {
        handleApiError(error);
        throw error;
    }
}

/**
 * HTTP DELETE 요청을 처리하는 함수
 * 
 * @param {string} url 요청 URL
 * @param {object} params = {} 삭제 조건 쿼리 파라미터 객체
 * @returns {Promise<any>} 성공 시 응답 데이터
 * @throws {Error} 실패 시 예외 발생 및 알림 표시
 */
async function del(url, params = {}) {
    try {
        const response = await axios.delete(url, params);
        return handleApiResponse(response);
    } catch (error) {
        handleApiError(error);
        throw error;
    }
}

/**
 *  공통 응답 처리
 * - 성공 시 메시지 잇으면 showSuccess()
 * - 실패 시 showError() 후 예외 throw
 */
function handleApiResponse(response) {
    const {data} = response;

    if(data?.success != true){
        const message = data?.message || '요청 처리에 실패했습니다.';
        AlertUtil.showError(message);
        throw new Error(message);
    }

    if(data?.message) {
        AlertUtil.showSuccess(data.message);
    }

    return data.data;
}

/**
 * 공통 요청 실패
 * - 
 */
function handleApiError(error) {
    if(error.response) {
        const msg = error.response.data?.message || '서버 오류가 발생했습니다.';
        AlertUtil.showError(`[${error.response.status}] ${msg}`);
    } else if(error.request) {
        AlertUtil.showError('서버로부터 응답이 없습니다.');
    } else {
        AlertUtil.showError('요청 처리 중 오류가 발생했습니다.');
    }
}

// 전역 등록
window.ApiUtil = {
      get
    , post
    , put
    , del
};