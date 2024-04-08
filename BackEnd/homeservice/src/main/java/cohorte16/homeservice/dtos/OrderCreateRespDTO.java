package cohorte16.homeservice.dtos;

import cohorte16.homeservice.enums.Orderstatus;

import java.util.Date;

public record OrderCreateRespDTO(
        Long id,
        String description,
        Orderstatus orderstatus,
        Date date,
        Long clientId
) {
}
