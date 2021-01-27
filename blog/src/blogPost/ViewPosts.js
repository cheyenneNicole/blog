import React from 'react'
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import { getPosts, getComments, addComments } from './APIService';
import Button from '@material-ui/core/Button';
import ThumbUpAltOutlinedIcon from '@material-ui/icons/ThumbUpAltOutlined';
import ThumbUpAltIcon from '@material-ui/icons/ThumbUpAlt';
import axios from 'axios';
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';

export default class ViewPosts extends React.Component {
  
  constructor(props) {
      super(props)
  
      this.state = {
           posts: [],
           liked: false,
           comments: [],
           open: false,
           isOpen: false,
           comment:''
      }

      this.addPost = this.addPost.bind(this);
      this.editPost = this.editPost.bind(this);
      this.deleteRow = this.deleteRow.bind(this);
      this.handleLikedClicked = this.handleLikedClicked.bind(this);
      this.handleClickOpen = this.handleClickOpen.bind(this);
      this.handleComment = this.handleComment.bind(this);
      this.handleCommentChange = this.handleCommentChange.bind(this);
  }
  componentDidMount(){
    getPosts().then((data) => {
        console.log(JSON.stringify(data.data));
        this.setState({ posts: data.data })
    })
    .catch(function (ex) {
      console.log('Response parsing failed. Error: ', ex);
    });
    getComments().then((data)=> {
      console.log(JSON.stringify(data.data));
      this.setState({ comments:data.data })
      console.log(this.state.comments[0]);
    })
    .catch(function (ex) {
      console.log('Response parsing failed. Error: ', ex);
  });
  }
  editPost(id) {
    console.log('update ' + id)
    this.props.history.push(`/add-post/${id}`);
  }
  handleLikedClicked(){
    this.setState({
      liked: !this.state.liked
    });
    console.log(this.state.liked);
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
  handleCommentChange = event => {
    this.setState({ comment: event.target.value});
  }
  handleClickOpen (){
    console.log("INSIDE HANLDLE CLICK");
    this.setState({
      open: !this.state.open
    });
    console.log(this.state.open);
  };
  handleClose = () => {
    this.setState({
      open: false
    });

  };
  handleComment = () => {
    let comment = {comment: this.state.comment};
    console.log(comment);
    addComments(comment).then(res =>{
        this.props.history.push('/posts');
    });
    this.setState({
      open: false
    });

  };
  render(){
    let result = this.state.posts.map(obj => {
      let data = this.state.comments.find(item => item.id === obj.id);
      return {...obj, ...data}
    });
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
                        result.map(post =>
                          <TableRow key={post.id}>
                                    <TableCell align="right">{post.title}</TableCell>
                                    <TableCell align="right">{post.body}</TableCell>
                                    <TableCell align="right"> 
                                      <button onClick={() => this.editPost(post.id)}>Update</button>
                                      <button onClick={(e) => this.deleteRow(post.id, e)}>Delete</button>
                                    </TableCell>
                                    
                                    {post.likes == true ?
                                      <TableCell> 
                                      <Button onClick={this.handleLikedClick}><ThumbUpAltIcon/></Button>
                                      </TableCell> 
                                      :
                                      <TableCell> 
                                      <Button onClick={this.handleLikedClick}><ThumbUpAltOutlinedIcon/></Button>
                                      </TableCell>
                                    }
                                    <TableCell>
                                        {post.comment}
                                    </TableCell>
                                    <TableCell>
                                        <Button onClick ={this.handleClickOpen} >Add a comment</Button>
                                        <Dialog open={this.state.open} onClose={this.handleClose} aria-labelledby="form-dialog-title">
                                      <DialogTitle id="form-dialog-title">Subscribe</DialogTitle>
                                      <DialogContent>
                                        <DialogContentText>
                                          Add a Comment
                                        </DialogContentText>
                                        <TextField
                                          autoFocus
                                          margin="dense"
                                          id="name"
                                          label="Comment"
                                          type="email"
                                          fullWidth
                                          value = {this.state.comment} 
                                          onChange={this.handleCommentChange}
                                        />
                                      </DialogContent>
                                      <DialogActions>
                                        <Button onClick={this.handleClose} color="primary">
                                          Cancel
                                        </Button>
                                        <Button onClick={this.handleComment} color="primary">
                                          Comment
                                        </Button>
                                      </DialogActions>
                                    </Dialog>
                                    </TableCell>
                                    
                          </TableRow>
                          )
                                  }

            </TableBody>
            <button className="btn btn-primary" onClick={this.addPost}> Add Post</button>

            </Table>
            </div>
    );
}
}