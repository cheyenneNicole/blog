import React from 'react'
import { makeStyles } from "@material-ui/core/styles";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Paper from "@material-ui/core/Paper";
import Avatar from "@material-ui/core/Avatar";
import GroupIcon from "@material-ui/icons/Group";
import { Link } from "react-router-dom";
import Typography from "@material-ui/core/Typography";
import CircularProgress from "@material-ui/core/CircularProgress";
import Button from '@material-ui/core/Button';
import APIService from './APIService'
import axios from 'axios';

const useStyles = makeStyles(theme => ({
  table: {
    minWidth: 600
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main
  },
  paper: {
    display: "flex",
    flexDirection: "column",
    justifyContent: "center",
    alignItems: "center",
    margin: `10px`,
    height: "100%",
    width: "99%",
    marginTop: theme.spacing(7)
  },
  link: {
    color: "rgba(0,0,0,0.65)",
    textDecoration: "none",
    marginLeft: "10%",
    alignSelf: "flex-start",
    "&:hover": {
      color: "rgba(0,0,0,1)"
    }
  }
}));

export default class ViewPosts extends React.Component {

  constructor(props) {
      super(props)
  
      this.state = {
           posts: []
      }
  }
  componentDidMount(){
    console.log("inside component did mount");

    APIService.getPosts().then((data) => {
        console.log(data.data);
        this.setState({ posts: data.data })
        
      })
      .catch(function (ex) {
          console.log('Response parsing failed. Error: ', ex);
      });
  }
  
  deleteRow(id, e){
    axios.delete(`http://localhost:8080/api/post/${id}`)
      .then(res => {
        console.log(res);
        console.log(res.data);
  
        const posts = this.state.posts.filter(item => item.id !== id);
        this.setState({ posts });
      })
  
  }
  
  render(){
    return(
        <div>
            <h2 className="text-center">View Blog Posts</h2>
            <table className="table table-striped">
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Body</th>
                        <th>Update/Delete</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        
                        this.state.posts.map(post =>
                                <tr key={post.id}>
                                    <td>{post.title}</td>
                                    <td>{post.body}</td>
                                    <td>
                                      <button>Update</button>
                                      <button onClick={(e) => this.deleteRow(post.id, e)}>Delete</button>
                                    </td>
                                </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    )
}
}

  // const deleteButton = (
  //   <Button onClick={console.log("deleted")}>
  //     Delete
  //   </Button>
  // );
