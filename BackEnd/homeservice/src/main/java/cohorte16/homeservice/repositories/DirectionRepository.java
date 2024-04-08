package cohorte16.homeservice.repositories;

import cohorte16.homeservice.models.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionRepository extends JpaRepository<Direction, Long> {
}
