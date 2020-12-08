import React, { Component } from "react";
import axios from 'axios';
import TextField from '@material-ui/core/TextField';
import Typography from "@material-ui/core/Typography";
import Button from '@material-ui/core/Button';

export default class AddBlogPost extends React.Component {
    state = {
      title: '',
      body: ''
    }
  
    handleChangeTitle = event => {
      this.setState({ title: event.target.value});
    }
    handleChangeBody = event => {
      this.setState({body: event.target.value });
    }
    handleSubmit = event => {
      event.preventDefault();
  
      const post = {
        title: this.state.title,
        body: this.state.body
      };
      
      axios.post(`http://localhost:8080/api/post`, { title: this.state.title, body: this.state.body})
        .then(res => {
          console.log(res);
          console.log(res.data);
        })
    }
  
    render() {
      return (
        <div>
          <form onSubmit={this.handleSubmit}>
            <label>
              Title:
              <input type="text" name="title" onChange={this.handleChangeTitle} />
            </label>
            <label>
              Body:
              <input type="text" name="body" onChange={this.handleChangeBody} />
            </label>
            <button type="submit">Add</button>
          </form>
        </div>
    );
}
}