export interface CommentResponse {

    id: number;
  
    content: string;
  
    authorUsername: string;
  
    createdAt: string;
  
    replies: CommentResponse[];
  }
  
  export interface CreateCommentRequest {
  
    content: string;
  }