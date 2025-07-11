// 정규 표현식
// 작성자: 한글(공백 포함) 1자 이상
export const KOREAN_REGEX     = /^[가-힣\s]+$/;
// 비밀번호: 영문 + 숫자 조합, 6~12자
export const PASSWORD_REGEX   = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,12}$/;

// API URL
export const API_URL          = "https://jsonplaceholder.typicode.com/posts/1";

// 사용자 메시지
export const MSG = {
  writerInvalid:     "작성자를 빈값이 아닌 한글로 입력해주세요."
  ,titleRequired:     "제목을 입력해주세요."
  ,contentTooShort:   "내용은 20자 이상 입력해주세요."
  ,passwordInvalid:   "비밀번호는 6~12자, 영문+숫자 조합이어야 합니다."
  ,passwordMismatch:  "비밀번호가 일치하지 않습니다."
  ,submitSuccess:     "글이 성공적으로 등록되었습니다!"
  ,fetchError:        "데이터 로딩 실패:"
};
