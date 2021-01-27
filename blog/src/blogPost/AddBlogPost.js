import React, { Component } from "react";
import axios from 'axios';
import TextField from '@material-ui/core/TextField';
import Typography from "@material-ui/core/Typography";
import Button from '@material-ui/core/Button';
import ViewPosts from '../blogPost/ViewPosts';
import { getPostById, createPost, updatePost } from './APIService';

export default class AddBlogPost extends React.Component {
  constructor(props) {
    super(props)

    this.state = {
        id: this.props.match.params.id,
        title: '',
        body: ''
      }
      this.handleChangeTitle = this.handleChangeTitle.bind(this);
      this.handleChangeBody= this.handleChangeBody.bind(this);
      this.saveOrUpdatePost = this.saveOrUpdatePost.bind(this);
    }
    componentDidMount(){

      // step 4
      if(this.state.id === '_add'){
          return
      }else{
          getPostById(this.state.id).then( (res) =>{
              let post = res.data;
              this.setState({title: post.title,
                  body: post.body
              });
          });
      }        
  }
  saveOrUpdatePost = (e) => {
    e.preventDefault();
    let post = {title: this.state.title, body: this.state.body};
    console.log('post => ' + JSON.stringify(post));

    // step 5
    if(this.state.id === '_add'){
        createPost(post).then(res =>{
            // window.location.reload();
            console.log(post);
            this.props.history.push('/posts');
        });
    }else{
        updatePost(post, this.state.id).then( res => {
          this.props.history.push('/posts');
        });
    }
    }
    getTitle(){
      if(this.state.id === '_add'){
          return <h3 className="text-center">Add Post</h3>
      }else{
          return <h3 className="text-center">Update Post</h3>
      }
    }

    handleChangeTitle = event => {
      this.setState({ title: event.target.value});
    }
    handleChangeBody = event => {
      this.setState({body: event.target.value });
    }

  
    render() {
      return (
        <div>
              {
                this.getTitle()
              }
            <label>

              Title:
              <input type="text" name="title" value = {this.state.title} onChange={this.handleChangeTitle} />
              
            </label>
            <label>
              Body:
              <input type="text" name="body" value = {this.state.body} onChange={this.handleChangeBody} />
            </label>
            <button type="submit" onClick={this.saveOrUpdatePost} >Add</button>

            {/* <div>
              <ViewPosts/>
            </div> */}

        </div>
    );
}
}