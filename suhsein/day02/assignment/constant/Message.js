export const regex = {
      writerRegex   : /^[ㄱ-ㅎ가-힣]+$/
    , passwordRegex : /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z0-9]{6,12}$/
    ,
};

export const error_messages = {
      ERROR_EMPTY_WRITER        : "[ERROR] 작성자는 빈 칸일 수 없습니다."
    , ERROR_INVALID_WRITER      : "[ERROR] 작성자 입력값은 한글만 허용됩니다."
    , ERROR_EMPTY_TITLE         : "[ERROR] 제목은 빈 칸일 수 없습니다."
    , ERROR_INVALID_CONTENT     : "[ERROR] 내용은 최소 20자 이상으로 입력해주세요."
    , ERROR_INVALID_PASSWORD    : "[ERROR] 비밀번호는 6자 이상 12자 이하의 영문/숫자 조합으로 입력해주세요."
    , ERROR_NOT_EQUAL_PASSWORD  : "[ERROR] 비밀번호가 일치하지 않습니다."
    , ERROR_FORM_DATA_SETTING   : "[ERROR] 데이터 불러오기에 실패했습니다."
    , ERROR_FETCHGING_DATA      : "[ERROR] API 호출 과정에서 오류가 발생했습니다."
};

export const messages = {
    WRITE_MEESAGE               : "게시글 작성이 완료되었습니다."
  , 
}