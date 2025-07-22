/**
 * 값이 비어있는지 검사 (null, undefined, '', 공백 포함)
 * 
 * @param {any} val 검사할 값
 * @returns {boolean} 비어있지 않으면 true
 */
function isNotEmpty(val) {
    const valid = !_.isNil(val) && StringUtil.hasText(val);

    return valid;
}

/**
 * 값이 소문자(a~z)만으로 이루어졌는지 검사
 * 
 * @param {any} val 검사할 값
 * @returns {boolean} 조건을 만족하면 true
 */
function isLowerCaseOnly(val) {
    const valid = typeof val === 'string' && /^[a-z]+$/.test(val);

    return valid;
}

/**
 * 이메일 형식인지 검사
 * 
 * @param {string}  email 이메일 문자열
 * - regex.test(str) -> str이 정규식(regex)과 일치하는지
 */
function isValidEmail(email) {
    const regex = /^[\w.-]+@[\w.-]+\.[a-zA-Z]{2,}$/;
    const valid = _.isString(email) && regex.test(email.trim());

    return valid
}

/**
 * 비밀번호 길이 유효성 검사
 * 
 * @param {string} password 비밀번호 문자열
 * @param {int} minLength 비밀번호 최소 자릿수
 * @param {int} maxLength 비밀번호 최대 자릿수
 * @returns {boolean} 유효하면 true
 */
function isValidPasswordLength(password, minLength, maxLength) {
    const valid = _.isString(password) && password.length >= minLength && password.length <= maxLength;

    return valid;
}

/**
 * 비밀번호와 확인 값이 일치하는지 검사
 * 
 * @param {string} password 원본 비밀번호
 * @param {string} confirmPassword 확인용 비밀번호
 * @returns {boolean} 일치하면 true
 */
function isPasswordMatch(password, confirmPassword) {
    const valid = _.isString(password) && password === confirmPassword;

    return valid;
}

// 전역 등록
window.ValidationUtil = {
      isNotEmpty
    , isValidEmail
    , isLowerCaseOnly
    , isValidPasswordLength
    , isPasswordMatch
};