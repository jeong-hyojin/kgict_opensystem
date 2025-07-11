import {
  API_URL,
  MSG,
  KOREAN_REGEX,
  PASSWORD_REGEX
} from "./config.js";


// DOM 요소 참조
const form            = document.getElementById("postForm");
const writer          = document.getElementById("writer");
const title           = document.getElementById("title");
const content         = document.getElementById("content");
const password        = document.getElementById("password");
const passwordConfirm = document.getElementById("passwordConfirm");
const pwMessage       = document.getElementById("pwMessage");

// 비밀번호 확인 입력 시 일치 여부 실시간 검사
passwordConfirm.addEventListener("input", validatePasswordMatch);


// 폼 제출 이벤트 처리
form.addEventListener("submit", function (e) {
  e.preventDefault();

  if (!isValidWriter())   { return; }
  if (!isValidTitle())    { return; }
  if (!isValidContent())  { return; }
  if (!isValidPassword()) { return; }
  if (!isPasswordMatch()) { return; }

  // 성공 시 알림 및 초기화
  alert(MSG.submitSuccess);
  form.reset();
  pwMessage.innerText = "";
});


// ===유효성 검사 함수들=== 

// 작성자 유효성 검사
function isValidWriter() {
  if (!writer.value.trim() || !KOREAN_REGEX.test(writer.value)) {
    alert(MSG.writerInvalid);
    writer.focus();
    return false;
  } else {
    return true;
  }
}

// 제목 유효성 검사
function isValidTitle() {
  if (!title.value.trim()) {
    alert(MSG.titleRequired);
    title.focus();
    return false;
  } else {
    return true;
  }
}

// 내용 유효성 검사 (20자 이상)
function isValidContent() {
  if (!content.value.trim() || content.value.trim().length < 20) {
    alert(MSG.contentTooShort);
    content.focus();
    return false;
  } else {
    return true;
  }
}

// 비밀번호 형식 유효성 검사
function isValidPassword() {
  if (!PASSWORD_REGEX.test(password.value)) {
    alert(MSG.passwordInvalid);
    password.focus();
    return false;
  } else {
    return true;
  }
}

// 비밀번호 확인 일치 여부 검사(제출시 검사)
function isPasswordMatch() {
  if (passwordConfirm.value !== password.value) {
    alert(MSG.passwordMismatch);
    passwordConfirm.focus();
    return false;
  } else {
    return true;
  }
}

// 비밀번호 입력 중 실시간 일치 여부 검사(실시간 UI 검사)
function validatePasswordMatch() {
  if (passwordConfirm.value !== password.value) {
    pwMessage.innerText = MSG.passwordMismatch;
  } else {
    pwMessage.innerText = "";
  }
}

// [day03:과제] API 데이터 가져오기 및 폼에 세팅
fetch(API_URL)
  .then((res) => res.json())
  .then((data) => {
    writer.value  = `사용자 ${data.userId}`;
    title.value   = data.title;
    content.value = data.body;
  })
  .catch((err) => {
    console.error(MSG.fetchError, err);
  });
