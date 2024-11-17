package dp.practice.project.Book_App_Project.Exceptions;

public class UserAlreadyExistsException extends RuntimeException{
   public UserAlreadyExistsException(String msg){
        super(msg);
    }
}
