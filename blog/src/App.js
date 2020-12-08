import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import AddBlogPost from './AddBlogPost';
import ButtonAppBar from './NavBar';
import ViewPosts from './ViewPosts';
function App() {
  return (
    <div>
    <ButtonAppBar/>
    <Router>
        <Route exact path="/" component= {AddBlogPost}/>
        <Route exact path="/posts" component= {ViewPosts}/>
    </Router>
    </div>
  );
}

export default App;
