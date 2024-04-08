package cohorte16.homeservice.controllers;

import cohorte16.homeservice.dtos.ClientDTO;
import cohorte16.homeservice.dtos.ClientPutDTO;
import cohorte16.homeservice.models.Client;
import cohorte16.homeservice.services.impl.ClientServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
@CrossOrigin("*")
public class ClientController {
    private final ClientServiceImpl clientService;

    public ClientController(ClientServiceImpl clientService){
        this.clientService = clientService;
    }

    @PostMapping(consumes = "application/json",produces = "application/json")
    public ResponseEntity<?> save(@Valid @RequestBody ClientDTO clientDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(clientService.save(clientDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error! Something went wrong");
        }
    }

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error! Something went wrong");
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(clientService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error! Something went wrong");
        }
    }

    @PutMapping(value = "/{id}", consumes = "application/json",produces = "application/json")
    public ResponseEntity<?> update(@Valid @PathVariable Long id, @RequestBody ClientPutDTO clientDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(clientService.update(id,clientDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error! Something went wrong");
        }
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(clientService.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error! Something went wrong");
        }
    }

}
