const form = document.getElementById('postForm');
const writer = document.getElementById('writer');
const title = document.getElementById('title');
const content = document.getElementById('content');
const password = document.getElementById('password');
const passwordConfirm = document.getElementById('passwordConfirm');
const pwMessage = document.getElementById('pwMessage');

// 한글 1자 이상
const koreanRegex = /^[가-힣\s]+$/;

// 비밀번호 정규식 (6~12자, 영문 + 숫자)
const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,12}$/;

passwordConfirm.addEventListener('input', () => {
  if (passwordConfirm.value !== password.value) {
    pwMessage.innerText = '비밀번호가 일치하지 않습니다.';
  } else {
    pwMessage.innerText = '';
  }
});

form.addEventListener('submit', function (e) {
  e.preventDefault();

  if (!writer.value.trim() || !koreanRegex.test(writer.value)) {
    alert('작성자를 빈값이 아닌 한글로 입력해주세요.');
    writer.focus();
    return;
  }

  if (!title.value.trim()) {
    alert('제목을 입력해주세요.');
    return;
  }

  if (!content.value.trim() || content.value.trim().length < 20) {
    alert('내용은 20자 이상 입력해주세요.');
    content.focus();
    return;
  }

  if (!passwordRegex.test(password.value)) {
    alert('비밀번호는 6~12자, 영문+숫자 조합이어야 합니다.');
    password.focus();
    return;
  }

  if (passwordConfirm.value !== password.value) {
    alert('비밀번호가 일치하지 않습니다.');
    passwordConfirm.focus();
    return;
  }

  alert('글이 성공적으로 등록되었습니다!');
  form.reset();
  pwMessage.innerText = '';
});