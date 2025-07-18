/**
 * 공통 유틸 스크립트 로더
 * - AlertUtil, ApiUtil, StringUtil, ValidationUtil 등 공통 유틸 모음
 * - 각 유틸은 /js/utils/에 위치
 * - HTML에서 common.js 하나만 import하면 됨
 */

(function loadCommonScripts() {
  const scripts = [
    "https://cdn.jsdelivr.net/npm/sweetalert2@11",
    "https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js",
    "https://cdn.jsdelivr.net/npm/lodash@4.17.21/lodash.min.js",
    "https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js",
    "/js/utils/AlertUtil.js",
    "/js/utils/ApiUtil.js",
    "/js/utils/StringUtil.js",
    "/js/utils/ValidationUtil.js",
  ];

  scripts.forEach((src) => {
    const script = document.createElement("script");
    script.src = src;
    script.defer = true;
    document.head.appendChild(script);
  });
})();
