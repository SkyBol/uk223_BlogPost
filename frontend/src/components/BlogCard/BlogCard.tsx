import { Button } from '@mui/material';
import Avatar from '@mui/material/Avatar';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardHeader from '@mui/material/CardHeader';
import { red } from '@mui/material/colors';
import Typography from '@mui/material/Typography';
import moment from 'moment';
import * as React from 'react';
import { BlogPost } from '../../types/models/BlogPost.model';

type Props = {
  blogPost: BlogPost,
  isHomePage: boolean,
};


const BlogCard = (props: Props) => {
  const subheader = props.blogPost.creationTime ? moment(props.blogPost.creationTime).format("DD.MM.YYYY hh:mm") : "" 
    + props.blogPost.editTime ? "Edited : " + moment(props.blogPost.editTime).format("DD-MM-YYYY hh:mm") : "";

  return (
    <Card sx={{ maxWidth: '35%', mb: 10, ml: 10 }}>
      <CardHeader
        avatar={
          <Avatar sx={{ bgcolor: red[500] }} aria-label="recipe">OG</Avatar>
        }
        title={props.blogPost.author ? props.blogPost.author.firstName : "anonymous"}
        subheader={subheader}
      />
      <CardContent>
        <Typography variant="h6">
           {props.blogPost.title}
           <hr />
        </Typography>
        <Typography variant="body2">
           {props.blogPost.text}
        </Typography>
      </CardContent>
      <CardActions>
        {!props.isHomePage ? 
        <Button variant="outlined" color="error" >Delete</Button>
        : <></>
        }
      </CardActions>
    </Card>
  );
}

export default BlogCard;
