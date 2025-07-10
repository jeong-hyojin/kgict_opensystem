document.getElementById("postForm").addEventListener("submit", handleSubmit);

// 에러 메세지 이후, 비밀번호 확인란에 입력시 InnerText 제거
document.getElementById("passwordConfirm").addEventListener("input", () => {
    const errorMessage = document.getElementById("passwordError");
    errorMessage.style.display = "none";
});

function handleSubmit(e) {
    e.preventDefault();

    const data = getData();


    if (data.password !== data.passwordConfirm) {
        const errEl = document.getElementById("passwordError");
        errEl.style.display = "block";
        return;
    }

    const error = isValid(data);
    if (error) {
        alert(error);
        return;
    }

    alert("게시글이 등록되었습니다.");
    location.reload(true);
}

function getData() {
    return {
        username: document.getElementById("username").value.trim(),
        title: document.getElementById("title").value.trim(),
        content: document.getElementById("content").value.trim(),
        password: document.getElementById("password").value.trim(),
        passwordConfirm: document.getElementById("passwordConfirm").value.trim()
    };
}

function isValid({ username, title, content, password }) {
    const usernameRegex = /^[가-힣]+$/;

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
    if (password.length < 6 || password.length > 12) {
        return "비밀번호는 6자 이상 12자 이하로 입력해주세요.";
    }
    return null;
}
