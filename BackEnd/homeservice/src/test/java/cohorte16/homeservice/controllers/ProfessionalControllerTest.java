package cohorte16.homeservice.controllers;

import cohorte16.homeservice.dtos.ProfessionalDTO;
import cohorte16.homeservice.dtos.RegistroUsuarioDTO;
import cohorte16.homeservice.enums.Profession;
import cohorte16.homeservice.models.User;
import cohorte16.homeservice.services.impl.ProfessionalServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = ProfessionalController.class)
class ProfessionalControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfessionalServiceImpl professionalService;

    /*
    @Test
    void testGetAllProfessional() throws Exception {
        List<ProfessionalDTO> professionalDTOList = new ArrayList<>();
        professionalDTOList.add(
                new ProfessionalDTO(
                        "Franco",
                        "Tallei",
                        "20123456789",
                        Profession.fromString("Otros"),
                        1,
                        "bajbjsfafpa.jpg",
                        "123456789123",
                        null,
                        null,
                        Collections.emptyList()
                        ));
        professionalDTOList.add(
                new ProfessionalDTO(
                        "Jorge",
                        "Garcia",
                        "28456123792",
                        Profession.fromString("Otros"),
                        1,
                        "bajbjsf.jpg",
                        "123789456123",
                        null,
                        null,
                        Collections.emptyList()
                ));

        when(professionalService.findAll()).thenReturn(professionalDTOList);
        mockMvc.perform(get("/api/professional/all"))
                .andExpect(status().isOk());
    }
*/
    @Test
    void getOne() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}