package cohorte16.homeservice.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String msj){
        super(msj);
    }
}
