/**
 * StringUtil - 문자열 관련 유틸리티
 */

/**
 * 문자열의 null 또는 공백 체크
 * 
 * @param {string} str 검사할 문자열
 * @returns {boolean} null, undefined, 공백이 아니면 true
 */
function hasText(str) {
    return _.isString(str) && str.trim().length > 0;
}

/**
 * 문자열의 앞, 뒤 공백 제거
 * 
 * @param {string} str 대상 문자열
 * @returns {string} str 앞, 뒤 공백 제거된 문자열(str이 문자열이 아니라면 ""반환)
 */
function trim(str) {
    return _.isString(str) ? str.trim() : '';
}

/**
 * 문자열의 모든 공백 제거
 * 
 * @param {string} str 대상 문자열
 * @return {string} 모든 공백 제거된 문자열
 */
function removeAllSpace(str) {
    return _.isString(str) ? str.replace(/\s+/g, '') : '';
}

/**
 * 특수문자 제거(한글/영문/숫자/공백은 허용)
 * 
 * @param {string} str  대상 문자열
 * @returns {string} 특수문자 제거된 문자열
 */
function removeSpecialChars(str) {
    return _.isString(str) ? str.replace(/[^\w\sㄱ-ㅎㅏ-ㅣ가-힣]/g, ''): '';
}

/**
 * 대소문자 구분 없이 두 문자열이 같은지 비교
 * 
 * @param {string} str1 비교 문자열1
 * @param {string} str2 비교 문자열2
 * @returns {boolean} 대소문자 무시하고 동일하면 true
 */
function isEqualIgnoreCase(str1, str2) {
    return (
           _.isString(str1)
        && _.isString(str2)
        && str1.toLowerCase() === str2.toLowerCase()
    );
}

// 전역 등록
window.StringUtil = {
      hasText
    , trim
    , removeAllSpace
    , removeSpecialChars
    , isEqualIgnoreCase
};