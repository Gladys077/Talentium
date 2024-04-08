package cohorte16.homeservice.repositories;

import cohorte16.homeservice.dtos.dtoOrderClientList;
import cohorte16.homeservice.dtos.ordenProfessionalList;
import cohorte16.homeservice.enums.Orderstatus;
import cohorte16.homeservice.enums.Profession;
import cohorte16.homeservice.models.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.orderstatus = :status")
    List<Order> findByOrderStatus(@Param("status") Orderstatus orderstatus);


    Page<Order> findAll(Pageable pageable);

    @Query("SELECT o FROM Order o  WHERE o.client.id = :clienteId")
    Page<Order> findOrdenesByClienteId(@Param("clienteId") Long clienteId, Pageable pageable);


    @Query("SELECT o FROM Order o  WHERE o.professional.id = :professionalId")
    Page<Order> findOrdenesByProfesionalId(@Param("professionalId") Long professionalId, Pageable pageable);

    @Query("SELECT NEW cohorte16.homeservice.dtos.ordenProfessionalList(o, o.client.name, o.client.lastname, o.client.phone,o.client.id )  FROM Order o WHERE o.professional.id = :professionalId AND o.orderstatus = :orderstatus GROUP BY o.id")
    Page<ordenProfessionalList>  findOrdenesByProfesionalIdPendiente(@Param("professionalId") Long professionalId, @Param("orderstatus") Orderstatus orderstatus, PageRequest pageRequest);

    @Query("SELECT NEW cohorte16.homeservice.dtos.dtoOrderClientList( o,o.professional.id,o.professional.name, o.professional.lastname, o.professional.profession,o.professional.rating, o.professional.user.id, o.professional.user.avatar, o.professional.phone )FROM Order o   WHERE o.client.id = :clientId AND o.orderstatus = :orderstatus")
    Page<dtoOrderClientList> findOrdenesByClientIdPendiente(@Param("clientId")Long clientId, @Param("orderstatus")  Orderstatus orderstatus, PageRequest pageRequest);

    @Query("SELECT o FROM Order o   WHERE o.client.id = :clientId AND o.orderstatus = :orderstatus")
    Page<Order> findOrdenesByClientIdInitial(@Param("clientId")Long clientId,@Param("orderstatus")  Orderstatus orderstatus, PageRequest pageRequest);

}