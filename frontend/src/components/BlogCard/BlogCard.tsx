import * as React from 'react';
import { styled } from '@mui/material/styles';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardMedia from '@mui/material/CardMedia';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import Collapse from '@mui/material/Collapse';
import Avatar from '@mui/material/Avatar';
import IconButton, { IconButtonProps } from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import { red } from '@mui/material/colors';
import FavoriteIcon from '@mui/icons-material/Favorite';
import ShareIcon from '@mui/icons-material/Share';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import MoreVertIcon from '@mui/icons-material/MoreVert';
import AvatarTWO from '../Assets/isagiyoichi.png'
import { User } from '../../types/models/User.model';

interface ExpandMoreProps extends IconButtonProps {
  expand: boolean;
}

type data = {id : string, title: string, text : string, author : User, date: Date}  

  


const ExpandMore = styled((props: ExpandMoreProps) => {
  const { expand, ...other } = props;
  return <IconButton {...other} />;
})(({ theme, expand }) => ({
  transform: !expand ? 'rotate(0deg)' : 'rotate(180deg)',
  marginLeft: 'auto',
  transition: theme.transitions.create('transform', {
    duration: theme.transitions.duration.shortest,
  }),
}));

export default function RecipeReviewCard({id, title , text , author, date }:data ) {
  const [expanded, setExpanded] = React.useState(false);

  const handleExpandClick = () => {
    setExpanded(!expanded);
  };

  return (
    <Card sx={{ maxWidth: 345 }}>
      <CardHeader
        avatar={
          <Avatar src={AvatarTWO}sx={{ bgcolor: red[500] }} aria-label="recipe">
            
          </Avatar>
        }
        action={
          <IconButton aria-label="settings">
            <MoreVertIcon />
          </IconButton>
        }
        title={author.firstName + author.lastName}
        subheader={date.toDateString()}
      />
      <CardMedia
        component="img"
        height="194"
        image={AvatarTWO}
        alt="Paella dish"
      />
      <CardContent>
        <Typography variant="body2" color="text.secondary">
           {text}
        </Typography>
      </CardContent>
      <CardActions disableSpacing>
        <IconButton aria-label="add to favorites">
          <FavoriteIcon />
        </IconButton>
        <IconButton aria-label="share">
          <ShareIcon />
        </IconButton>
        <ExpandMore
          expand={expanded}
          onClick={handleExpandClick}
          aria-expanded={expanded}
          aria-label="show more"
        >
          <ExpandMoreIcon />
        </ExpandMore>
      </CardActions>
      <Collapse in={expanded} timeout="auto" unmountOnExit>
        <CardContent>
          <Typography paragraph>Method:</Typography>
          <Typography paragraph>
          Isagi Yoichi propsect number 259 of the Blue Lock. Special Ability Emporers Eyes,
          He is able to sense Goals and Winning plays, he is able to pinpointly put the ball trough for Goal
          </Typography>
          <Typography paragraph>
          Isagi Yoichi propsect number 259 of the Blue Lock. Special Ability Emporers Eyes,
          He is able to sense Goals and Winning plays, he is able to pinpointly put the ball trough for Goal
          </Typography>
          <Typography paragraph>
          Isagi Yoichi propsect number 259 of the Blue Lock. Special Ability Emporers Eyes,
          He is able to sense Goals and Winning plays, he is able to pinpointly put the ball trough for Goal
          </Typography>
          <Typography>
          Isagi Yoichi propsect number 259 of the Blue Lock. Special Ability Emporers Eyes,
          He is able to sense Goals and Winning plays, he is able to pinpointly put the ball trough for Goal          </Typography>
        </CardContent>
      </Collapse>
    </Card>
  );
}
