/**
 * 일반 정보 알림
 * @param {string} message 표시할 메시지
 */
function showInfo(message) {
  Swal.fire("알림", message, "info");
}

/**
 * 성공 알림
 * @param {string} message 표시할 메시지
 * @returns {Promsie} 알림 확인 후 resolve 됨
 */
async function showSuccess(message, title = '성공') {
  return await createAlert({icon: 'success', title, text:message });
}

/**
 * 경고 알림
 * @param {string} message 표시할 메시지
 */
function showWarning(message) {
  Swal.fire("경고", message, "warning");
}

/**
 * 에러 알림
 * @param {string} message 표시할 메시지
 */
function showError(message) {
  Swal.fire("오류", message, "error");
}

/**
 * 확인/취소 알림
 *
 * @param {string} message 확인할 메시지
 * @returns {Promise<boolean>} 사용자가 확인을 누르면 true, 취소를 누르면 false
 */
async function showConfirm(message) {
  const result = await Swal.fire({
    title: "확인",
    text: message,
    icon: "question",
    showCancelButton: true,
    confirmButtonText: "확인",
    cancelButtonText: "취소",
  });

  return result.isConfirmed;
}
// 전역 등록
window.AlertUtil = {
  showInfo,
  showSuccess,
  showWarning,
  showError,
  showConfirm,
};

/**
 * 공통 알림 생성 함수
 * 
 * @param {object} options  알림옵션
 * @param {string} options  아이콘 타입 (success, error, warning, info 등)
 * @param {string} options  알림 제목
 * @param {string} options  알림 본문 메시지
 * @param {string} options  확인 버튼 텍스트
 * @param {string} options  SweetAlert2의 결과 객체 Promise
 * @returns {Promise<SwalResult>} SweetAlert2의 결과 객체 Promise
 */
function createAlert({
  icon = "info",
  title = "알림",
  text = "",
  confirmText = "확인",
}) {
  return Swal.fire({
    icon,
    title,
    text,
    confirmButtonText: confirmText,
    allowOutsideClick: false,
    allowEscapeKey: false,
  }).then((result) => {
    ok: result.isConfirmed, 
    result;
  });
}
