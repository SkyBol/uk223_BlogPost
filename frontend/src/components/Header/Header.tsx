import { Button, Box, AppBar, Toolbar } from '@mui/material';
import React, { useContext } from "react";
import { useNavigate } from 'react-router-dom';
import ActiveUserContext from '../../Contexts/ActiveUserContext';


const Header = () => {
    const navigate = useNavigate();
    const { user, checkRole } = useContext(ActiveUserContext);
    const buttonStyle = {
        mr: 5
    };


    return (
            <Box>
                <AppBar position="static">
                    <Toolbar sx={{backgroundColor: "black"}}>          
                    <Button variant="outlined" sx={buttonStyle} onClick={() => navigate('/')}>Blogs</Button>
                    {checkRole("ADMIN") && <Button variant="outlined" sx={buttonStyle} onClick={() => navigate('/users')}>Users</Button>}
                    {checkRole("USER") && <Button variant="outlined" sx={buttonStyle} onClick={() => navigate(`/users/${user?.id}`)}>Account</Button>}
                    {checkRole("USER") && <Button variant="outlined" sx={buttonStyle} onClick={() => navigate(`/${user?.id}/post`)}>Create Post</Button>}
                    </Toolbar> 
                </AppBar> 
            </Box>
    );
}

export default Header;