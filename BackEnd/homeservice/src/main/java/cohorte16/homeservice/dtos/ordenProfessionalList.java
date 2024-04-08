package cohorte16.homeservice.dtos;

import cohorte16.homeservice.models.Client;
import cohorte16.homeservice.models.Order;

public record ordenProfessionalList(Order order,Client client ) {

    public ordenProfessionalList(Order order, String name, String lastname, String phone, Long id ){
        this(order, new Client(id,name,lastname,phone));
    }
}
