/**
 * 공통 유틸 스크립트 로더
 * - AlertUtil, ApiUtil, StringUtil, ValidationUtil 등 공통 유틸 모음
 * - 각 유틸은 /js/utils/에 위치
 * - HTML에서 common.js 하나만 import하면 됨
 */

(function loadCommonScripts() {
    // CSS 파일 리스트
    const styles = [
        "https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
        , "https://unpkg.com/bootstrap-table@1.21.2/dist/bootstrap-table.min.css"
    ];
    const scripts = [
        "https://code.jquery.com/jquery-3.6.0.min.js"
        , "https://cdn.jsdelivr.net/npm/sweetalert2@11"
        , "https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"
        , "https://cdn.jsdelivr.net/npm/lodash@4.17.21/lodash.min.js"
        , "https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
        , "https://unpkg.com/bootstrap-table@1.21.2/dist/bootstrap-table.min.js"
        , "/js/utils/AlertUtil.js"
        , "/js/utils/ApiUtil.js"
        , "/js/utils/StringUtil.js"
        , "/js/utils/ValidationUtil.js"
        , '/js/utils/Modal.js'
    ];

    // CSS 로드
    styles.forEach(href => {
        const link = document.createElement("link");
        link.rel = "stylesheet";
        link.href = href;
        document.head.appendChild(link);
    });

    scripts.forEach(src => {
        const script = document.createElement('script');
        script.src = src;
        script.defer = true;
        document.head.appendChild(script);
    });
})();