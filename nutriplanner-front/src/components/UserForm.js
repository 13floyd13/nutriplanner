import React, { useState, useEffect } from 'react';
import { createUser, getAllUsers, deleteUser } from '../api/userApi';


const UserForm = () => {
  const [users, setUsers] = useState([]);
  const [newUser, setNewUser] = useState({login: '', password: ''});

  // Fetch users when component mounts
  useEffect(() => {
    const fetchUsers = async () => {
      const allUsers = await getAllUsers();
      setUsers(allUsers);
      console.log(allUsers);
    };

    fetchUsers();
  }, []);

  const handleSubmit = async (event) => {
    event.preventDefault();
    const createdUser = await createUser(newUser);
    setUsers([...users, createdUser]); // Add new user to list
    setNewUser({login: '', password: ''}); // Clear form
  };

  const handleDelete = async (idUser) => {
    await deleteUser(idUser);
    setUsers(users.filter(user => user.idUser !== idUser)); // Remove user from list
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <input 
          value={newUser.login} 
          onChange={e => setNewUser({...newUser, login: e.target.value})} 
          placeholder="Login" 
          required
        />
        <input 
          value={newUser.password} 
          onChange={e => setNewUser({...newUser, password: e.target.value})}
          placeholder="Password" 
          required
        />
        <button type="submit">Create User</button>
      </form>

      <table>
        <thead>
          <tr>
            <th>Login</th>
            <th>Password</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {users.map(user => (
            <tr key={user.idUser}>
              <td>{user.login}</td>
              <td>{user.password}</td>
              <td><button onClick={() => handleDelete(user.idUser)}>Delete</button></td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default UserForm;

