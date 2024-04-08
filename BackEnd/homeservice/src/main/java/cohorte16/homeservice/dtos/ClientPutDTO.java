package cohorte16.homeservice.dtos;

public record ClientPutDTO(

        String phone,
        String dni,

        DirectionDTO direction
) {
}
