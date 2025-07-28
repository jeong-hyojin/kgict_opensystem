/**
 * 공통 유틸 스크립트 로더
 * - AlertUtil, ApiUtil, StringUtil, ValidationUtil 등 공통 유틸 모음
 * - 각 유틸은 /js/utils/에 위치
 * - HTML에서 common.js 하나만 import하면 됨
 */

(function loadCommonScripts() {
  const isLoginPage = window.location.pathname.includes('login.html');
  const isSignupPage = window.location.pathname.includes('signup_jpa.html');
  const isAdminPage = window.location.pathname.includes('admin/');

  const excludedFiles = {
    login: ['bootstrap.min.css', 'bootstrap.bundle.min.js', 'bootstrap-table.min.css', 'bootstrap-table.min.js', 'jquery-3.6.0.min.js'],
    signup: ['bootstrap.min.css', 'bootstrap.bundle.min.js', 'bootstrap-table.min.css', 'bootstrap-table.min.js', 'jquery-3.6.0.min.js'],
    admin: ['bootstrap.min.css', 'bootstrap.bundle.min.js', 'bootstrap-table.min.css', 'bootstrap-table.min.js', 'jquery-3.6.0.min.js']
  };

  let currentPageExclusions = [];
  if (isLoginPage) currentPageExclusions = excludedFiles.login;
  else if (isSignupPage) currentPageExclusions = excludedFiles.signup;
  else if (isAdminPage) currentPageExclusions = excludedFiles.admin;

  // CSS 파일 리스트
  const styles = [
      "https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css",
      "https://unpkg.com/bootstrap-table@1.21.2/dist/bootstrap-table.min.css"
  ];  
  const scripts = [
      "https://code.jquery.com/jquery-3.6.0.min.js",
      "https://cdn.jsdelivr.net/npm/sweetalert2@11",
      "https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js",
      "https://cdn.jsdelivr.net/npm/lodash@4.17.21/lodash.min.js",
      "https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js",
      "https://unpkg.com/bootstrap-table@1.21.2/dist/bootstrap-table.min.js",
      "/js/utils/AlertUtil.js",
      "/js/utils/ApiUtil.js",
      "/js/utils/StringUtil.js",
      "/js/utils/ValidationUtil.js",
      '/js/utils/Modal.js'
  ]; 

  styles.forEach(href => {
    if (currentPageExclusions.some(file => href.includes(file))) {
        return;
    }
    const link = document.createElement("link");
    link.rel = "stylesheet";
    link.href = href;
    document.head.appendChild(link);
  });

  scripts.forEach(src => {
      if (currentPageExclusions.some(file => src.includes(file))) {
          return;
      }
      const script = document.createElement('script');
      script.src = src;
      script.defer = true;
      document.head.appendChild(script);
  });
})();