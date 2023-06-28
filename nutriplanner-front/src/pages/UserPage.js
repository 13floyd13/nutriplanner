import React, { useEffect, useState } from 'react';
import UserForm from '../components/UserForm';
import { getAllUsers } from '../api/userApi';

function UserPage() {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    const fetchUsers = async () => {
      const users = await getAllUsers();
      setUsers(users);
    };

    fetchUsers();
  }, []);

  return (
    <div>
      <UserForm />
      <h2>Existing Users:</h2>
      {users.map((user) => (
        <div key={user.idUser}>
          <p>Login: {user.login}</p>
        </div>
      ))}
    </div>
  );
}

export default UserPage;
