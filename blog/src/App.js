import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import AddBlogPost from './AddBlogPost';
import ButtonAppBar from './NavBar';
import ViewPosts from './ViewPosts';
import Login from './Login';
function App() {
  return (
    <div>
    <ButtonAppBar/>
    <Router>
        <Route exact path="/add-post/:id" component= {AddBlogPost}/>
        <Route exact path="/posts" component= {ViewPosts}/>
        <Route exact path="/login" component= {Login}/>
    </Router>
    </div>
  );
}

export default App;
