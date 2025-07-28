const modalState = {
    visible : false
    , title : ''
    , message : ''
    , onConfirm : null
    , onCancel : null
};

/**
 * 기본 모달 표시
 *
 * @param {object} options 모달 옵션 {title, message, onConfirm, onCancel}
 */
function showModal({ title = '', message = '', onConfirm = null, onCancel = null }) {
    modalState.title = title;
    modalState.message = message;
    modalState.onConfirm = onConfirm;
    modalState.onCancel = onCancel;
    modalState.visible = true;

    // DOM 조작 추가 필요
    renderModal();
}


/**
 * 간단한 모달 render
 */
function renderModal() {
    const modalEl = document.getElementById('globalModal');
    if (!modalEl) return;

    modalEl.style.display = modalState.visible ? 'block' : 'none';
    modalEl.querySelector('.modal-title').textContent = modalState.title;
    modalEl.querySelector('.modal-body').textContent = modalState.message;

    const confirmBtn = modalEl.querySelector('#modalConfirmBtn');
    confirmBtn.onclick = () => {
        if (typeof modalState.onConfirm === 'function') {
            modalState.onConfirm();
        }
        hideModal();
    };
}

/**
 * 폼 모달 표시
 * @param {object} options {title, formContent, onConfirm, onCancel}
 * formContent: HTML Element 또는 HTML 문자열
 */
function showFormModal({ title = '', formContent = '', onConfirm = null, onCancel = null }) {
    modalState.title = title;
    modalState.formContent = formContent;
    modalState.onConfirm = onConfirm;
    modalState.onCancel = onCancel;
    modalState.visible = true;

    renderFormModal();
}

/**
 * 폼 모달 render
 */
function renderFormModal() {
    const modalEl = document.getElementById('globalModal');
    if (!modalEl) return;

    modalEl.style.display = modalState.visible ? 'block' : 'none';
    modalEl.querySelector('.modal-title').textContent = modalState.title;

    // 폼 내용 삽입
    const bodyEl = modalEl.querySelector('.modal-body');
    if (typeof modalState.formContent === 'string') {
        bodyEl.innerHTML = modalState.formContent;
    } else if (modalState.formContent instanceof HTMLElement) {
        bodyEl.innerHTML = '';
        bodyEl.appendChild(modalState.formContent);
    }

    const confirmBtn = modalEl.querySelector('#modalConfirmBtn');
    confirmBtn.onclick = async () => {
        if (typeof modalState.onConfirm === 'function') {
            modalState.onConfirm();
        }
        hideModal();
    };
}

/**
 * 모달 닫기
 */
function hideModal() {
    modalState.visible = false;
    renderModal();
}

/**
 * 모달 상태(BaseModal에서 접근)
 */
function useGlobalModal() {
    return modalState;
}

// 전역 등록
window.Modal = {
      showModal
    , showFormModal
    , hideModal
    , useGlobalModal
};