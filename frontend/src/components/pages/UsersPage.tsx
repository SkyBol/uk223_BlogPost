import { Typography } from "@mui/material";
import React, { useEffect, useState } from "react";
import UserService from "../../Services/UserService";
import { User } from '../../types/models/User.model';
import Header from "../Header/Header";


const UsersPage = () => {
    const [users, setUsers] = useState<User[]>([]);

    useEffect(() => {
        UserService.getAllUsers().then((res) => setUsers(res.data))
        .catch((error) => console.log(error));
    }, []);

    return(
        <div>
            <Header/>
            {users.map((user: User) => {
                return(
                    <Typography key={user.id} sx={{ fontSize: "h5.fontSize"}}>First Name: {user.firstName}</Typography>
                );
            })}
        </div>
    );
}

export default UsersPage;