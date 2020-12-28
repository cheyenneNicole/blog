import axios from 'axios';

const POST_API_REST_URL = "http://localhost:8080/api/post";
const COMMENT_API_REST_URL = "http://localhost:8080/api/comments";

class APIService {
    
    getPosts(){
        return axios.get(POST_API_REST_URL, {headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json',
          }});
    }
    getPostById(postId){
        return axios.get(POST_API_REST_URL + '/' + postId, {headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json',
          }});
    }
    getComments(){
        return axios.get(COMMENT_API_REST_URL, {headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json',
          }});
    }
    updatePost(post, postId){
        return axios.put(POST_API_REST_URL + '/' + postId, post, {headers: {
            'Access-Control-Allow-Origin':'*',
            "Access-Control-Allow-Methods": "PUT, POST, GET, DELETE, PATCH, OPTIONS",
            "Access-Control-Allow-Credentials": "true",
            'Content-Type': 'application/json',
            "Access-Control-Allow-Headers": "content-type"
            }});
    }
    createPost(post){
        return axios.post(POST_API_REST_URL, post, {headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json',
          }});
    }
}

export default new APIService();