import {  useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import UserService from "../../Services/UserService";
import { User } from '../../types/models/User.model';


const UserPage = () => {
    const { userId } = useParams();
    const [user, setUser] = useState<User>();

    useEffect(() => {
        UserService.getUser(userId).then((res) => setUser(res.data)).catch((error) => console.log(error));
    }, [userId])

    return(
        <div>
            <p>{user?.firstName}</p>
            <p>{user?.lastName}</p>
            <p>{user?.email}</p>
        </div>
    );
}

export default UserPage;