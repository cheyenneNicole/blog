import './App.css';
import React, { Component } from 'react';
import {
  Link,
  withRouter,
  Route,
  BrowserRouter as Router,
  Switch
} from 'react-router-dom';
import Profile from '../user/Profile';
import AddBlogPost from '../blogPost/AddBlogPost';
import AppHeader from '../common/NavBar';
import ViewPosts from '../blogPost/ViewPosts';
import Login from '../user/Login';
import AutorenewIcon from '@material-ui/icons/Autorenew';
import { getCurrentUser } from '../blogPost/APIService';
import { ACCESS_TOKEN } from '../constants';
import PrivateRoute from '../common/PrivateRoute';
import { notification } from 'antd';
import Signup from '../user/Signup';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      currentUser: null,
      isAuthenticated: false,
      isLoading: false
    }
    this.handleLogout = this.handleLogout.bind(this);
    this.loadCurrentUser = this.loadCurrentUser.bind(this);
    this.handleLogin = this.handleLogin.bind(this);
  }
  loadCurrentUser() {
    this.setState({
      isLoading: true
    });
    getCurrentUser()
    .then(response => {
      this.setState({
        currentUser: response,
        isAuthenticated: true,
        isLoading: false
      });
    }).catch(error => {
      this.setState({
        isLoading: false
      });  
    });
  }
  handleLogout(redirectTo="/", notificationType="success", description="You're successfully logged out.") {
    localStorage.removeItem(ACCESS_TOKEN);

    this.setState({
      currentUser: null,
      isAuthenticated: false
    });

    this.props.history.push(redirectTo);
    
    notification[notificationType]({
      message: 'Polling App',
      description: description,
    });
  }

  handleLogin() {
    notification.success({
      message: 'Polling App',
      description: "You're successfully logged in.",
    });
    this.loadCurrentUser();
    this.props.history.push("/");
  }

  render() {
    if(this.state.isLoading) {
      return <AutorenewIcon />
    }
  return (
    <div>

    {/* <AppHeader isAuthenticated={this.state.isAuthenticated} 
            currentUser={this.state.currentUser} 
            onLogout={this.handleLogout} /> */}
    <Router>
        <Route exact path="/" 
                  render={(props) => <ViewPosts isAuthenticated={this.state.isAuthenticated} 
                      currentUser={this.state.currentUser} handleLogout={this.handleLogout} {...props} />}>
        </Route>
        <Route exact path="/add-post/:id" component= {AddBlogPost}/>
        <Route exact path="/posts" component= {ViewPosts}/>
        <Route path="/login" 
                  render={(props) => <Login onLogin={this.handleLogin} {...props} />}></Route>
                <Route path="/signup" component={Signup}></Route>
                <Route path="/users/:username" 
                  render={(props) => <Profile isAuthenticated={this.state.isAuthenticated} currentUser={this.state.currentUser} {...props}  />}>
                </Route>
                <PrivateRoute authenticated={this.state.isAuthenticated} path="/poll/new" component={AddBlogPost} handleLogout={this.handleLogout}></PrivateRoute>
    </Router>
    </div>
  );
}
}
export default App;
