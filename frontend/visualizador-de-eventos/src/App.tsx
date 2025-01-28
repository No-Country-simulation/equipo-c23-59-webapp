import { BrowserRouter, createBrowserRouter, RouterProvider } from 'react-router-dom';
import Home from './view/Home';
import Login from './view/Login';

const browserRouter = createBrowserRouter([
  {
    path: '/',
    element: <Home />,
  },
  {
    path: '/login',
    element: <Login />,
  }
  
]);
function App() {
  return (
    <BrowserRouter>
      <RouterProvider router={browserRouter} />
      
    </BrowserRouter>
  );
}

export default App;