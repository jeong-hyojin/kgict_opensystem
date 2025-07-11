// DOM 요소 참조
const form            = document.getElementById("postForm");
const writer          = document.getElementById("writer");
const title           = document.getElementById("title");
const content         = document.getElementById("content");
const password        = document.getElementById("password");
const passwordConfirm = document.getElementById("passwordConfirm");
const pwMessage       = document.getElementById("pwMessage");


// 작성자: 한글(공백 포함) 1자 이상
const koreanRegex     = /^[가-힣\s]+$/;
// 비밀번호: 영문 + 숫자 조합, 6~12자
const passwordRegex   = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,12}$/;


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
  alert("글이 성공적으로 등록되었습니다!");
  form.reset();
  pwMessage.innerText = "";
});


// ===유효성 검사 함수들=== 

// 작성자 유효성 검사
function isValidWriter() {
  if (!writer.value.trim() || !koreanRegex.test(writer.value)) {
    alert("작성자를 빈값이 아닌 한글로 입력해주세요.");
    writer.focus();
    return false;
  } else {
    return true;
  }
}

// 제목 유효성 검사
function isValidTitle() {
  if (!title.value.trim()) {
    alert("제목을 입력해주세요.");
    title.focus();
    return false;
  } else {
    return true;
  }
}

// 내용 유효성 검사 (20자 이상)
function isValidContent() {
  if (!content.value.trim() || content.value.trim().length < 20) {
    alert("내용은 20자 이상 입력해주세요.");
    content.focus();
    return false;
  } else {
    return true;
  }
}

// 비밀번호 형식 유효성 검사
function isValidPassword() {
  if (!passwordRegex.test(password.value)) {
    alert("비밀번호는 6~12자, 영문+숫자 조합이어야 합니다.");
    password.focus();
    return false;
  } else {
    return true;
  }
}

// 비밀번호 확인 일치 여부 검사(제출시 검사)
function isPasswordMatch() {
  if (passwordConfirm.value !== password.value) {
    alert("비밀번호가 일치하지 않습니다.");
    passwordConfirm.focus();
    return false;
  } else {
    return true;
  }
}

// 비밀번호 입력 중 실시간 일치 여부 검사(실시간 UI 검사)
function validatePasswordMatch() {
  if (passwordConfirm.value !== password.value) {
    pwMessage.innerText = "비밀번호가 일치하지 않습니다.";
  } else {
    pwMessage.innerText = "";
  }
}

// [day03:과제] API 데이터 가져오기 및 폼에 세팅
fetch("https://jsonplaceholder.typicode.com/posts/1")
  .then((res) => res.json())
  .then((data) => {
    writer.value  = `사용자 ${data.userId}`;
    title.value   = data.title;
    content.value = data.body;
  })
  .catch((err) => {
    console.error("데이터 로딩 실패:", err);
});