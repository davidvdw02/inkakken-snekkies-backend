package nl.inkakken.snekkies.inkakkensnekkiesbackend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.OnlineEntertainment;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.services.OnlineEntertainmentService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/onlineentertainment")
public class OnlineEntertainmentController {
    
    private final OnlineEntertainmentService onlineEntertainmentService;

    @Autowired
    public OnlineEntertainmentController(OnlineEntertainmentService onlineEntertainmentService) {
        this.onlineEntertainmentService = onlineEntertainmentService;
    }


    @GetMapping
    public ResponseEntity<List<OnlineEntertainment>> getAllOnlineEntertainment() {
        List<OnlineEntertainment> onlineEntertainment = onlineEntertainmentService.getAllOnlineEntertainment();
        return ResponseEntity.ok(onlineEntertainment);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<OnlineEntertainment> getOnlineEntertainmentById(@PathVariable UUID id) {
        return ResponseEntity.ok(onlineEntertainmentService.getOnlineEntertainmentById(id));
    }

    @PostMapping
    public ResponseEntity<OnlineEntertainment> createOnlineEntertainment(@PathVariable OnlineEntertainment onlineEntertainment) {
        OnlineEntertainment createdOnlineEntertainment = onlineEntertainmentService.createOnlineEntertainment(onlineEntertainment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOnlineEntertainment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnlineEntertainment(@PathVariable UUID id) {
        onlineEntertainmentService.deleteOnlineEntertainment(id);
        return ResponseEntity.noContent().build();
    }

   @PutMapping("/{id}")
    public ResponseEntity<Void> putOnlineEntertainment(@PathVariable UUID id, @PathVariable OnlineEntertainment onlineEntertainment) {
        this.onlineEntertainmentService.putOnlineEntertainment(id, onlineEntertainment);
        return ResponseEntity.noContent().build();
    }

}
