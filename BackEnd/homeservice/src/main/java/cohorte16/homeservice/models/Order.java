package cohorte16.homeservice.models;

import cohorte16.homeservice.dtos.OrderDTO;
import cohorte16.homeservice.enums.Orderstatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ordenes")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "profesional_id", referencedColumnName = "id")
    @JsonIgnore
    private Professional professional;

    @Column(name = " descripcion_profesional")
    private String description_professional;

    @Column(name = "precio")
    @DecimalMin(value = "0.00")
    private BigDecimal price;

    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date date =  new Date();

    @Column(name = "ESTADO")
    @Enumerated(value = EnumType.STRING)
    private Orderstatus orderstatus;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    @JsonIgnore
    private Client client;

    public Order(OrderDTO orderDTO){
        this.client.setId(orderDTO.cliente_id());
        this.description = orderDTO.description();
    }

    public Order(Order order) {
        this.id = order.getId();
        this.description = order.getDescription();
        this.professional = order.getProfessional();
        this.description_professional = order.getDescription_professional();
        this.price = order.getPrice();
        this.date = order.getDate();
        this.orderstatus = order.getOrderstatus();
        this.client = order.getClient();
    }



}
