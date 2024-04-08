package cohorte16.homeservice.dtos;

import cohorte16.homeservice.enums.Profession;
import cohorte16.homeservice.models.Client;
import cohorte16.homeservice.models.Order;
import cohorte16.homeservice.models.Professional;
import cohorte16.homeservice.models.User;

public record dtoOrderClientList(Order order, Professional professional) {

    public dtoOrderClientList(Order order,Long id, String name, String lastname, Profession profession,
                              Integer rating, Long userId,String avatar, String phone){

        this(order, new Professional( id,  name,  lastname,  profession, rating,  userId, avatar, phone));
    }

}
