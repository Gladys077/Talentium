package cohorte16.homeservice.repositories;

import cohorte16.homeservice.enums.Profession;
import cohorte16.homeservice.models.Professional;
import cohorte16.homeservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, Long> {
    Professional findProfessionalByUser(User userCreated);

    List<Professional> findProfessionalByProfession(Profession profession);
}
