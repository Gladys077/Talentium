package cohorte16.homeservice.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class EntityNotSavedException extends EntityNotFoundException {
    public EntityNotSavedException (String msj){
        super(msj);
    }
}
