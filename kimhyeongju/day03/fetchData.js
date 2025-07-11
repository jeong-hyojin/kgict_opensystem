const API_URL = 'https://jsonplaceholder.typicode.com/posts/1';

export const getFetchData = async () => {
    try{
        // fetch로 데이터 습득
        let response = await fetch(API_URL);
        if (!response.ok) throw new Error();
    
        // form 세팅
        let data = await response.json();
        document.getElementById("title").value     = data.title;
        document.getElementById("content").value   = data.body;
        document.getElementById("username").value  = data.userId;
    } catch(err) {
        console.error('error:', err);
    }
}
