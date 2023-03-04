import { createBrowserRouter } from 'react-router-dom';
import { CreateAccount, Home, Login, Logout, Profile } from '../ui/screen';
import { PrivateRoute } from './private-route';

export const router = createBrowserRouter([
  {
    path: '/',
    element: <Login />,
  },
  {
    path: '/create',
    element: <CreateAccount />,
  },
  {
    path: '/home',
    element: <PrivateRoute Screen={Home} />,
  },
  {
    path: '/profile',
    element: <PrivateRoute Screen={Profile} />,
  },
  {
    path: '/logout',
    element: <PrivateRoute Screen={Logout} />,
  },
]);
