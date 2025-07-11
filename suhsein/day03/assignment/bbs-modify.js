// 전역 DOM 변수
const $password        = document.getElementById("password");
const $passwordConfirm = document.getElementById("passwordConfirm");
const $message         = document.getElementById("message");


// 비밀번호 keyup 이벤트
$password.addEventListener("keyup", equalsPassword);
// 비밀번호 확인 keyup 이벤트
$passwordConfirm.addEventListener("keyup", equalsPassword);


// 폼 제출 이벤트 (유효성 검증)
document
    .getElementById("writeForm")
    .addEventListener("submit", onSubmitAction);


// API 주소   
const formDataUri = "https://jsonplaceholder.typicode.com/posts/1";
setFormData(formDataUri)


// API 호출 후, form에 데이터 세팅
async function setFormData(uri) {
    try {
    const response = await fetch(uri);
    response.json().then((data) => {
        const $writer  = document.getElementById("writer");
        const $title   = document.getElementById("title");
        const $content = document.getElementById("content");
                
        $writer.value      = data.userId;
        $title.value       = data.title;
        $content.innerText = data.body;
    });
    } catch(e) {
    alert("[API 호출 오류] " + e);
    }
}

// 비밀번호, 비밀번호 확인 동일 검증
function equalsPassword(e) {
    const password        = $password.value.trim();
    const passwordConfirm = $passwordConfirm.value.trim();

    $message.style.display = password === passwordConfirm ? "none" : "block";
}

// 작성자, 제목, 내용, 비밀번호 유효성 검증
function onSubmitAction(e) {
    e.preventDefault();

    const writer  = document.getElementById("writer").value.trim();
    const title   = document.getElementById("title").value.trim();
    const content = document.getElementById("content").value.trim();

    const writerRegex   = /^[ㄱ-ㅎ가-힣]+$/;
    const passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z0-9]{6,12}$/;

    if (!writer) {
        alert("작성자는 빈 칸일 수 없습니다.");
        return;
    } else if (!writerRegex.test(writer)) {
        alert("작성자 입력값은 한글만 허용됩니다.");
        return;
    }

    if (!title) {
        alert("제목은 빈 칸일 수 없습니다.");
        return;
    }

    if (!content || content.length < 20) {
        alert("내용은 최소 20자 이상으로 입력해주세요.");
        return;
    }

    const password        = $password.value.trim();
    const passwordConfirm = $passwordConfirm.value.trim();

    if (!password || !passwordRegex.test(password)) {
        alert(
        "비밀번호는 6자 이상 12자 이하의 영문/숫자 조합으로 입력해주세요."
        );
        return;
    }

    if (password !== passwordConfirm) {
        alert("비밀번호가 일치하지 않습니다.");
        return;
    }

    alert("게시글 작성이 완료되었습니다.");
}