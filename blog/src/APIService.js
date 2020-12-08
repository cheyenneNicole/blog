import axios from 'axios';

const POST_API_REST_URL = "http://localhost:8080/api/post";

class APIService {
    
    getPosts(){
        return axios.get(POST_API_REST_URL, {headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json',
          }});
    }

}

export default new APIService();