import { error_messages, messages, regex } from "./constant/Message.js";
import { fetchData } from "./fetchData.js";

// 전역 DOM 변수
const $password        = document.getElementById("password");
const $passwordConfirm = document.getElementById("passwordConfirm");
const $message         = document.getElementById("message");
const $writer          = document.getElementById("writer");
const $title           = document.getElementById("title");
const $content         = document.getElementById("content");
const $writeForm       = document.getElementById("writeForm");


// 비밀번호 keyup 이벤트
$password.addEventListener("keyup", equalsPassword);
// 비밀번호 확인 keyup 이벤트
$passwordConfirm.addEventListener("keyup", equalsPassword);
// 폼 제출 이벤트 (유효성 검증)
$writeForm.addEventListener("submit", onFormSubmit);


// 수정 전 데이터 폼에 세팅
setFormData();


// form에 데이터 세팅
async function setFormData() {
    try {
      const FORM_DATA_URI = "https://jsonplaceholder.typicode.com/posts/1";
      const data = await fetchData(FORM_DATA_URI);

      $writer.value      = data.userId;
      $title.value       = data.title;
      $content.innerText = data.body;
    } catch (e) {
      console.log(`${error_messages.ERROR_FORM_DATA_SETTING} ${e}`);
    }
}


// 비밀번호, 비밀번호 확인 동일 검증
function equalsPassword(e) {
  const password        = $password.value.trim();
  const passwordConfirm = $passwordConfirm.value.trim();

  $message.style.display = password === passwordConfirm ? "none" : "block";
}


// 작성자, 제목, 내용, 비밀번호 유효성 검증
function onFormSubmit(e) {
  e.preventDefault();

  const writer  = $writer.value.trim();
  const title   = $title.value.trim();
  const content = $content.value.trim();

  // 작성자 유효성 검증
  if (!writer) {
    alert(error_messages.ERROR_EMPTY_WRITER);
    return;
  } else if (!regex.writerRegex.test(writer)) {
    alert(error_messages.ERROR_INVALID_WRITER);
    return;
  }

  // 제목 유효성 검증
  if (!title) {
    alert(error_messages.ERROR_EMPTY_TITLE);
    return;
  }

  // 내용 유효성 검증
  if (!content || content.length < 20) {
    alert(error_messages.ERROR_INVALID_CONTENT);
    return;
  }

  const password        = $password.value.trim();
  const passwordConfirm = $passwordConfirm.value.trim();

  // 비밀번호 유효성 검증
  if (!password || !regex.passwordRegex.test(password)) {
    alert(error_messages.ERROR_INVALID_PASSWORD);
    return;
  }

  // 비밀번호 불일치
  if (password !== passwordConfirm) {
    alert(error_messages.ERROR_NOT_EQUAL_PASSWORD);
    return;
  }

  // 글쓰기 성공 메세지
  alert(messages.WRITE_MEESAGE);
}
