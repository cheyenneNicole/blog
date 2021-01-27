import axios from 'axios';
import { API_BASE_URL, ACCESS_TOKEN } from '../constants';

const POST_API_REST_URL = "http://localhost:8080/api/post";
const COMMENT_API_REST_URL = "http://localhost:8080/api/comments";


    const request = (options) => {
        const headers = new Headers({
            'Content-Type': 'application/json',
        })
        
        if(localStorage.getItem(ACCESS_TOKEN)) {
            headers.append('Authorization', 'Bearer ' + localStorage.getItem(ACCESS_TOKEN))
        }
    
        const defaults = {headers: headers};
        options = Object.assign({}, defaults, options);
    
        return fetch(options.url, options)
        .then(response => 
            response.json().then(json => {
                if(!response.ok) {
                    return Promise.reject(json);
                }
                return json;
            })
        );
    };
    export function checkUsernameAvailability(username) {
        return request({
            url: API_BASE_URL + "/user/checkUsernameAvailability?username=" + username,
            method: 'GET'
        });
    }
    
    export function checkEmailAvailability(email) {
        return request({
            url: API_BASE_URL + "/user/checkEmailAvailability?email=" + email,
            method: 'GET'
        });
    }
    export function login(loginRequest) {
        return request({
            url: API_BASE_URL + "/auth/signin",
            method: 'POST',
            body: JSON.stringify(loginRequest)
        });
    }
    
    export function signup(signupRequest) {
        return request({
            url: API_BASE_URL + "/auth/signup",
            method: 'POST',
            body: JSON.stringify(signupRequest)
        });
    }
    export function getCurrentUser() {
        if(!localStorage.getItem(ACCESS_TOKEN)) {
            return Promise.reject("No access token set.");
        }
    
        return request({
            url: API_BASE_URL + "/user/me",
            method: 'GET'
        });
    }
    
    export function getUserProfile(username) {
        return request({
            url: API_BASE_URL + "/users/" + username,
            method: 'GET'
        });
    }
    
    export function getPosts(){
        return axios.get(POST_API_REST_URL, {headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json',
          }});
    }
    export function getPostById(postId){
        return axios.get(POST_API_REST_URL + '/' + postId, {headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json',
          }});
    }
    export function getComments(){
        return axios.get(COMMENT_API_REST_URL, {headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json',
          }});
    }
    export function addComments(comment){
        return axios.get(COMMENT_API_REST_URL, comment, {headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json',
          }});
    }
    export function updatePost(post, postId){
        return axios.put(POST_API_REST_URL + '/' + postId, post, {headers: {
            'Access-Control-Allow-Origin':'*',
            "Access-Control-Allow-Methods": "PUT, POST, GET, DELETE, PATCH, OPTIONS",
            "Access-Control-Allow-Credentials": "true",
            'Content-Type': 'application/json',
            "Access-Control-Allow-Headers": "content-type"
            }});
    }
    export function createPost(post){
        return axios.post(POST_API_REST_URL, post, {headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json',
          }});
    }