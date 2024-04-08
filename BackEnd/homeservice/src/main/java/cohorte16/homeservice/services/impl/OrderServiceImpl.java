package cohorte16.homeservice.services.impl;

import cohorte16.homeservice.dtos.*;
import cohorte16.homeservice.enums.Orderstatus;
import cohorte16.homeservice.enums.Profession;
import cohorte16.homeservice.exceptions.EntityNotSavedException;
import cohorte16.homeservice.mappers.OrderMapper;
import cohorte16.homeservice.models.Client;
import cohorte16.homeservice.models.Order;
import cohorte16.homeservice.models.Professional;
import cohorte16.homeservice.repositories.OrderRepository;
import cohorte16.homeservice.repositories.ProfessionalRepository;
import cohorte16.homeservice.services.OrderService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.service.spi.ServiceException;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;

    private final ProfessionalRepository professionalRepository;

    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository,
                            ProfessionalRepository professionalRepository,
                            OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.professionalRepository = professionalRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OrderGetInitialDTO> getAllInitialOrders() {
        List<OrderGetInitialDTO> orderResponseDTOs;
        try {
            List<Order> orderList = orderRepository.findByOrderStatus(Orderstatus.Inicial);
            orderResponseDTOs = orderList.stream().map(orderMapper::orderToOrderGetInitialDTO).toList();
        } catch (Exception e) {
            throw new ServiceException("Error occurred while fetching all orders", e);
        }
        return orderResponseDTOs;
    }

    @Override
    public List<OrderGetPendRespDTO> getAllPendingOrders() {
        List<OrderGetPendRespDTO> orderResponseDTOs;
        try {
            List<Order> orderList = orderRepository.findByOrderStatus(Orderstatus.Pendiente);
            orderResponseDTOs = orderList.stream().map(orderMapper::orderToOrderGetPendRespDTO).toList();
        } catch (Exception e) {
            throw new ServiceException("Error occurred while fetching all orders", e);
        }
        return orderResponseDTOs;
    }

    @Override
    public List<Order> getAllApprovedOrders() throws Exception {
        try {
            return orderRepository.findByOrderStatus(Orderstatus.Aprobada);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    @Override
    public OrderCreateRespDTO CreatedOrder(OrderDTO order) {
        try {
            Order odenDb = Order.builder()
                    .description(order.description())
                    .client(Client.builder().id(order.cliente_id()).build())
                    .date(new Date())
                    .orderstatus(Orderstatus.valueOf("Inicial"))
                    .build();
            Order orderEntity = orderRepository.save(odenDb);
            return orderMapper.orderToOrderCreateRespDTO(orderEntity);
        } catch (EntityNotSavedException en) {
            throw new EntityNotSavedException("Try again, the order has not been saved");
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public OrderGetPendRespDTO takeOrderProfessional(Long id, OrderProfessionalDTO orderProfessionalDTO) {
        try {
            Order order = orderRepository.findById(id).orElseThrow(
                    () -> new EntityNotFoundException("Order Not Found")
            );
            Professional professional = professionalRepository.findById(
                    orderProfessionalDTO.idProfessional()).orElseThrow(
                    () -> new EntityNotFoundException("Professional Not Found")
            );
            order.setProfessional(professional);
            order.setDescription_professional(orderProfessionalDTO.descriptionProfessional());
            order.setPrice(orderProfessionalDTO.price());
            order.setOrderstatus(Orderstatus.Pendiente);
            Order orderSaved = orderRepository.save(order);
            return orderMapper.orderToOrderGetPendRespDTO(orderSaved);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }


    @Override
    public OrderGetInitialDTO updateOrderDescription(UpdateOrderDTO updateOrderDTO) {
        try {
            Order existingOrder = orderRepository.findById(updateOrderDTO.order_id())
                    .orElseThrow(() -> new EntityNotFoundException("Order not found"));
            if (updateOrderDTO.description() != null) existingOrder.setDescription(updateOrderDTO.description());
            Order orderSaved = orderRepository.save(existingOrder);
            return orderMapper.orderToOrderGetInitialDTO(orderSaved);
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public OrderRatingDTO updateRating(OrderRatingDTO order) {
        try {
            Order orderEntity = orderRepository.findById(order.id())
                    .orElseThrow(() -> new EntityNotFoundException("Order not found"));
            if (order.ratingClient() != null) {
                orderEntity.getClient().setRating((order.ratingClient() + orderEntity.getClient().getRating()) / 2);
            }
            if (order.ratingProfessional() != null && orderEntity.getPrice() != null) {
                orderEntity.getProfessional().setRating((orderEntity.getProfessional().getRating() + order.ratingProfessional()) / 2);

                orderEntity.getProfessional().setWallet(orderEntity.getPrice().add((orderEntity.getProfessional().getWallet() == null) ? BigDecimal.valueOf(0) : orderEntity.getProfessional().getWallet()));
                orderEntity.setOrderstatus(Orderstatus.Finalizada);
            }
            Order orderSaved = orderRepository.save(orderEntity);
            return orderMapper.orderToOrderRatingDTO(orderSaved);
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public OrderAceptedDTO orderAcepted(Long id) {
        try {
            Order exintingOrder = orderRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Order not found"));
            exintingOrder.setOrderstatus(Orderstatus.Aprobada);
            Order orderSaved = orderRepository.save(exintingOrder);
            return orderMapper.orderToOrderAceptedDTO(orderSaved);
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        }
    }


    public OrderAceptedDTO orderFinalizada(Long id) {
        try {
            Order exintingOrder = orderRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Order not found"));
            exintingOrder.setOrderstatus(Orderstatus.Finalizada);
            Order orderSaved = orderRepository.save(exintingOrder);
            return orderMapper.orderToOrderAceptedDTO(orderSaved);
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /*
     * Revisar porque viajan null con entidades completas y no dto
     * */
    @Override
    public Order cancelOrderOfProfessional(Long id) {
        try {
            Order order = orderRepository.findById(id).
                    orElseThrow((() -> new EntityNotFoundException("Order Not Found")));
            order.setProfessional(null);
            order.setDescription_professional(null);
            order.setPrice(null);
            order.setOrderstatus(Orderstatus.Inicial);
            return orderRepository.save(order);
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean deleteOrder(Long id) throws Exception {
        try {
            Optional<Order> orderOptional = orderRepository.findById(id);
            if (orderOptional.isPresent()) {
                Order order = orderOptional.get();
                orderRepository.delete(order);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Page<Order> getAll(Pageable pageable) {
        try {
            return orderRepository.findAll(pageable).map(Order::new);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public Page<Order> findOrdenesByClienteId(Long id, PageRequest pageRequest) {
        try {
        return orderRepository.findOrdenesByClienteId(id,pageRequest).map(Order::new);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public Page<Order> findOrdenesByProfesionalId(Long id, PageRequest pageRequest) {
        try {
            return orderRepository.findOrdenesByProfesionalId(id,pageRequest).map(Order::new);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public Page<ordenProfessionalList> findOrdenesByProfesionalIdPendiente(Long id,Orderstatus orderstatus, PageRequest pageRequest) {
        try {
            return  orderRepository.findOrdenesByProfesionalIdPendiente(id,  orderstatus, pageRequest);//.map(Order::new);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public Page<?> findOrdenesByClientIdPendiente(Long id, Orderstatus orderstatus, PageRequest pageRequest) {
        try {
            return orderRepository.findOrdenesByClientIdPendiente(id,  orderstatus, pageRequest);//.map(Order::new);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public Page<Order> findOrdenesByClientIdInitial(Long id, Orderstatus orderstatus, PageRequest pageRequest) {
        try {
            return orderRepository.findOrdenesByClientIdInitial(id,  orderstatus, pageRequest).map(Order::new);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
