import React, { useContext } from 'react';
import { Route, Routes } from 'react-router-dom';
import LoginPage from '../components/pages/LoginPage/LoginPage';
import ActiveUserContext from '../Contexts/ActiveUserContext';
import PrivateRoute from './PrivateRoute';
import HomePage from '../components/pages/HomePage';
import UserPage from '../components/pages/UserPage';
import UsersPage from '../components/pages/UsersPage';
import authorities from '../config/Authorities';
import CreatePostPage from '../components/pages/CreatePostPage';
import RegisterPage from '../components/pages/RegisterPage';
import EditUserPage from '../components/pages/EditUserPage';
import EditPostPage from '../components/pages/EditPostPage';

/**
 * Router component renders a route switch with all available pages
 */

const Router = () => {
  //const { checkRole } = useContext(ActiveUserContext);

  /** navigate to different "home"-locations depending on Role the user have */

  return (
    <Routes>
      <Route path={'/'} element={<HomePage />} />
      <Route path={'/login'} element={<LoginPage />} />
      <Route path={'/register'} element={<RegisterPage />} />
      <Route path={'/:userId/post'} element={<CreatePostPage />} />
      <Route path={'/:userId/edit'} element={<EditUserPage/>} />
      <Route path={'/:userId/:blogPostId/edit'} element={<EditPostPage/>} />

      <Route
        path={'/users'}
        element={
          <PrivateRoute authorities={[{id: "", name: authorities.USER_READ}, 
          {id: "", name: authorities.USER_CREATE}, {id: "", name: authorities.USER_UPDATE},
          {id: "", name: authorities.USER_DELETE}]} element={<UsersPage/>} />
        }
      />
      <Route
        path='/users/:userId'
        element={
          <PrivateRoute
            authorities={[]}
            element={<UserPage/>}
          ></PrivateRoute>
        }
      />

      <Route
        path='/profile'
        element={
          <PrivateRoute
            authorities={[]}
            element={<div>nothing here</div>}
          ></PrivateRoute>
        }
      />

      <Route path='*' element={<div>Not Found</div>} />
    </Routes>
  );
};

export default Router;
