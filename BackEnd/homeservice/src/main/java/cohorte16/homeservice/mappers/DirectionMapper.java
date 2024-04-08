package cohorte16.homeservice.mappers;

import cohorte16.homeservice.dtos.DirectionDTO;
import cohorte16.homeservice.models.Direction;
import org.springframework.stereotype.Service;

@Service
public class DirectionMapper {

    public DirectionDTO directionToDirectionDTO(Direction direction){
        return new DirectionDTO(
                direction.getStreet(),
                direction.getNumber(),
                direction.getProvince(),
                direction.getLocation()
        );
    }

}
