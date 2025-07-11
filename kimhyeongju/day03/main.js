import { getFetchData } from './fetchData.js';

//  폼 제출 이벤트
document.getElementById("postForm").addEventListener("submit", handleSubmit);

// 비밀번호 확인 이벤트 : 에러 메세지 이후, 비밀번호 확인란에 입력시 InnerText 제거
document.getElementById("passwordConfirm").addEventListener("input", () => {
    const errorMessage = document.getElementById("passwordConfirmError");
    errorMessage.style.display = "none";
});

// day03 과제 : fetch로 데이터 가져오기
getFetchData();

// 폼 제출 핸들링
function handleSubmit(e) {
    e.preventDefault();

    const data = getData();

    // 비밀번호와 비밀번호 확인 데이터가 일치하지 않을시, InnerText 노출
    if (data.password !== data.passwordConfirm) {
        const errEl = document.getElementById("passwordConfirmError");
        errEl.style.display = "block";
        return;
    }

    // validation 체크
    const error = isValid(data);
    if (error) {
        alert(error);
        return;
    }

    alert("게시글이 등록되었습니다.");
    document.getElementById("postForm").reset();
}

// 폼 데이터 반환 메서드
function getData() {
     return {
          username        : document.getElementById("username").value.trim()
        , title           : document.getElementById("title").value.trim()
        , content         : document.getElementById("content").value.trim()
        , password        : document.getElementById("password").value.trim()
        , passwordConfirm : document.getElementById("passwordConfirm").value.trim()
    };
}

// 폼 데이터 유휴성 검사 메서드
function isValid({ username, title, content, password }) {
    /*
     * 작성자 정규표현식 (usernameRegex) : 한글만 입력 가능합니다.
     * 비밀번호 정규표현식 (passwordRegex) : 영문과 숫자로 구성 가능합니다.
     */
    const usernameRegex = /^[가-힣]+$/;
    const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]+$/;

    if (!username) {
        return "작성자를 입력해주세요.";
    }
    if (!username || !usernameRegex.test(username)) {
        return "작성자는 한글로만 입력해주세요 .";
    }
    if (!title) {
        return "제목을 입력해주세요.";
    }
    if (content.length < 20) {
        return "내용은 최소 20자 이상이어야 합니다.";
    }
    if (password.length < 6 || password.length > 12 || !passwordRegex.test(password)) {
        return "비밀번호는 6자 이상 12자 이하이며, 영문과 숫자로 구성되어야 합니다.";
    }
    return null;
}
