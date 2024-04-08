package cohorte16.homeservice.controllers;

import cohorte16.homeservice.dtos.ProfessionalDTO;
import cohorte16.homeservice.dtos.ProfessionalPutDTO;
import cohorte16.homeservice.enums.Profession;
import cohorte16.homeservice.services.impl.ProfessionalServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/professional")
@CrossOrigin("*")
public class ProfessionalController {

    private final ProfessionalServiceImpl professionalService;

    public ProfessionalController(ProfessionalServiceImpl professionalService){
        this.professionalService = professionalService;
    }

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(professionalService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error! Something went wrong");
        }
    }
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(professionalService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error! Professional not found");
        }
    }

    @GetMapping(value = "/user/{id}", produces = "application/json")
    public ResponseEntity<?> getProfessionalByUserId(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(professionalService.findByUser(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error! Professional not found");
        }
    }

    @GetMapping(value = "/users/{profession}", produces = "application/json")
    public ResponseEntity<?> getProfessionalsByProfession(@PathVariable Profession profession){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(professionalService.findByProfession(profession));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error! Professional not found");
        }
    }

    @PostMapping(consumes = "application/json",produces = "application/json")
    public ResponseEntity<?> save(@Valid @RequestBody ProfessionalDTO professionalDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(professionalService.save(professionalDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error! Something went wrong");
        }
    }
    @PutMapping(value = "/{id}", consumes = "application/json",produces = "application/json")
    public ResponseEntity<?> update(@Valid @PathVariable Long id, @RequestBody ProfessionalPutDTO professionalDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(professionalService.update(id,professionalDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error! Something went wrong");
        }
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(professionalService.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error! Error! Professional not found");
        }
    }

}
