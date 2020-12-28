import React from 'react'
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import APIService from './APIService';
import Button from '@material-ui/core/Button';
import ThumbUpAltOutlinedIcon from '@material-ui/icons/ThumbUpAltOutlined';
import ThumbUpAltIcon from '@material-ui/icons/ThumbUpAlt';
import ChatBubbleOutlineIcon from '@material-ui/icons/ChatBubbleOutline';
import Accordion from '@material-ui/core/Accordion';
import AccordionDetails from '@material-ui/core/AccordionDetails';
import AccordionSummary from '@material-ui/core/AccordionSummary';
import axios from 'axios';

export default class ViewPosts extends React.Component {
  
  
  constructor(props) {
      super(props)
  
      this.state = {
           posts: [],
           liked: false,
           comments: []
      }

      this.addPost = this.addPost.bind(this);
      this.editPost = this.editPost.bind(this);
      this.deleteRow = this.deleteRow.bind(this);
      this.handleLiked = this.handleLiked.bind(this);
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
    APIService.getComments().then((data)=> {
      console.log(JSON.stringify(data.data));
      this.setState({ comments: data.data })
      
    })
    .catch(function (ex) {
      console.log('Response parsing failed. Error: ', ex);
  });
  }
  editPost(id) {
    console.log('update ' + id)
    this.props.history.push(`/add-post/${id}`);
  }
  handleLiked(){
    this.setState({
      liked: !this.state.liked
    });
  }
  addPost(){
    this.props.history.push('/add-post/_add');
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
    const label = this.state.liked ? <ThumbUpAltIcon/> : <ThumbUpAltOutlinedIcon/>;
    return(
      <div>
      <Table style={{width: '75%', margin: 'auto'}}>
      <TableHead>
        <TableRow >
          <TableCell style={{fontWeight: "bold", textAlign: "right"}}>Title</TableCell>
          <TableCell style={{fontWeight: "bold", textAlign: "right"}}>Body</TableCell>
          <TableCell style={{ fontWeight: "bold", textAlign: "right"}}>Update/Delete</TableCell>
          <TableCell style={{ fontWeight: "bold", textAlign: "right"}}>Liked</TableCell>
          <TableCell style={{ fontWeight: "bold", textAlign: "right"}}>Comments</TableCell>
          </TableRow>
          </TableHead>
          <TableBody>
                    {
                        
                        this.state.posts.map(post =>
                          this.state.comments.map(commentBody => 
                          <TableRow key={post.entityID}>
                                    <TableCell align="right">{post.title}</TableCell>
                                    <TableCell align="right">{post.body}</TableCell>
                                    <TableCell align="right"> 
                                      <button onClick={() => this.editPost(post.id)}>Update</button>
                                      <button onClick={(e) => this.deleteRow(post.id, e)}>Delete</button>
                                    </TableCell>

                                    <TableCell> 
                                      <Button onClick={() => this.handleLiked()}>{label}</Button>
                                    </TableCell>
                                    <TableCell>
                                      {/* <Button><ChatBubbleOutlineIcon/></Button> */}
                                      {commentBody.comment}
                                    </TableCell>
                          </TableRow>
                          ))
                    }
                    

            </TableBody>
            <button className="btn btn-primary" onClick={this.addPost}> Add Post</button>

            </Table>
            </div>
    );
}
}