const API_URL = 'https://jsonplaceholder.typicode.com/posts/1';

export const getFetchData = async () => {
    try{
        let response = await fetch(API_URL);
        if (!response.ok) throw new Error();
    
        let data = await response.json();
        document.getElementById("username").value = data.userId;
        document.getElementById("title").value = data.title;
        document.getElementById("content").value = data.body;
    } catch(err) {
        console.error('error:', err);
    }
}
