package cohorte16.homeservice.mappers;

import cohorte16.homeservice.dtos.*;
import cohorte16.homeservice.models.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public OrderRatingDTO orderToOrderRatingDTO(Order order){
        return new OrderRatingDTO(
                order.getId(),
                order.getClient().getRating(),
                order.getProfessional().getRating(),
                order.getProfessional().getWallet()
        );
    }
    public OrderAceptedDTO orderToOrderAceptedDTO(Order order){
        return new OrderAceptedDTO(
                order.getId(),
                order.getDescription(),
                order.getDescription_professional(),
                order.getPrice(),
                order.getClient().getId(),
                new ProfessionalOrderDTO(
                        order.getProfessional().getId(),
                        order.getProfessional().getName(),
                        order.getProfessional().getLastname(),
                        order.getProfessional().getPhone(),
                        order.getProfessional().getUser().getEmail(),
                        order.getProfessional().getCuit(),
                        order.getProfessional().getCbu(),
                        order.getProfessional().getRating(),
                        order.getProfessional().getProfession(),
                        new DirectionDTO(
                                order.getProfessional().getDirection().getStreet(),
                                order.getProfessional().getDirection().getNumber(),
                                order.getProfessional().getDirection().getProvince(),
                                order.getProfessional().getDirection().getLocation()
                        )
                )
        );

    }

    public OrderGetPendRespDTO orderToOrderGetPendRespDTO(Order order){
        return new OrderGetPendRespDTO(
                order.getId(),
                order.getDescription(),
                order.getDescription_professional(),
                order.getPrice(),
                order.getClient().getId(),
                new ProfessionalMiniDTO(
                        order.getProfessional().getId(),
                        order.getProfessional().getName(),
                        order.getProfessional().getLastname(),
                        order.getProfessional().getPhone(),
                        order.getProfessional().getProfession(),
                        order.getProfessional().getRating()
                )
        );
    }

    public OrderGetInitialDTO orderToOrderGetInitialDTO(Order order){
        return new OrderGetInitialDTO(
                order.getId(),
                order.getDescription(),
                order.getDate(),
                order.getClient().getId()
        );
    }
    public OrderCreateRespDTO orderToOrderCreateRespDTO(Order order){
        return new OrderCreateRespDTO(
                order.getId(),
                order.getDescription(),
                order.getOrderstatus(),
                order.getDate(),
                order.getClient().getId()
        );
    }

    public OrderResponseDTO orderToOrderResponseDTO(Order order){
        return new OrderResponseDTO(
                order.getId(),
                order.getDescription(),
                order.getDescription_professional(),
                order.getPrice(),
                order.getDate(),
                order.getOrderstatus(),
                new ClientResponseDTO(
                        order.getClient().getId(),
                        order.getClient().getName(),
                        order.getClient().getLastname(),
                        order.getClient().getPhone(),
                        order.getClient().getDni(),
                        order.getClient().getRating(),
                        new UserDTO(
                                order.getClient().getUser().getId(),
                                order.getClient().getUser().getEmail(),
                                order.getClient().getUser().getAvatar()
                        ),
                        new DirectionDTO(
                                order.getClient().getDirection().getStreet(),
                                order.getClient().getDirection().getNumber(),
                                order.getClient().getDirection().getProvince(),
                                order.getClient().getDirection().getLocation()
                        )
                ),
                new ProfessionalResponseDTO(
                        order.getProfessional().getId(),
                        order.getProfessional().getName(),
                        order.getProfessional().getLastname(),
                        order.getProfessional().getPhone(),
                        order.getProfessional().getCuit(),
                        order.getProfessional().getCbu(),
                        order.getProfessional().getRating(),
                        order.getProfessional().getProfession(),
                        new DirectionDTO(
                                order.getProfessional().getDirection().getStreet(),
                                order.getProfessional().getDirection().getNumber(),
                                order.getProfessional().getDirection().getProvince(),
                                order.getProfessional().getDirection().getLocation()
                        ),
                        new UserDTO(
                                order.getProfessional().getUser().getId(),
                                order.getProfessional().getUser().getEmail(),
                                order.getProfessional().getUser().getAvatar()
                        )
                )
        );
    }


}
