import { Typography } from "@mui/material";
import React, { useEffect, useState } from "react";
import UserService from "../../Services/UserService";
import { User } from '../../types/models/User.model';


const UsersPage = () => {
    const [users, setUsers] = useState<User[]>([]);

    useEffect(() => {
        UserService.getAllUsers().then((res) => setUsers(res.data))
        .catch((error) => console.log(error));
    }, []);

    return(
        <div>
            {users.map((user: User) => {
                return(
                    <Typography sx={{ fontSize: "h5.fontSize"}}>{user.firstName}</Typography>
                );
            })}
        </div>
    );
}

export default UsersPage;