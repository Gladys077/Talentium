package cohorte16.homeservice.services;

import cohorte16.homeservice.dtos.*;
import cohorte16.homeservice.models.Order;

import java.util.List;

public interface OrderService {
    List<OrderGetInitialDTO> getAllInitialOrders();
    List<OrderGetPendRespDTO> getAllPendingOrders();
    List<Order> getAllApprovedOrders() throws Exception;
    OrderCreateRespDTO CreatedOrder(OrderDTO orderDTO);
    OrderGetPendRespDTO takeOrderProfessional(Long id, OrderProfessionalDTO orderProfessionalDTO);
    OrderGetInitialDTO updateOrderDescription(UpdateOrderDTO updateOrderDTO);
    OrderRatingDTO updateRating(OrderRatingDTO order);
    OrderAceptedDTO orderAcepted(Long id);
    Order cancelOrderOfProfessional(Long id);
    boolean deleteOrder(Long id) throws  Exception;
}