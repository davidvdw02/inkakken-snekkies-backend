package nl.inkakken.snekkies.inkakkensnekkiesbackend.controllers;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.Attendee;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.services.AttendeeService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/attendee")
public class AttendeeController {

      private final AttendeeService attendeeService;

    @Autowired
    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    @GetMapping()
    public ResponseEntity<List<Attendee>> getAllAttendees() {
        List<Attendee> attendees = attendeeService.getAllAttendees();
        return ResponseEntity.ok(attendees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attendee> getAttendeeById(@RequestParam UUID id) {
        return ResponseEntity.ok(attendeeService.getAttendeeById(id));
    }

    @PostMapping
    public ResponseEntity<Attendee> createAttendee(@RequestBody Attendee attendee) {
        Attendee createdAttendee = attendeeService.createAttendee(attendee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAttendee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendee(@RequestParam UUID id) {
        attendeeService.deleteAttendee(id);
        return ResponseEntity.noContent().build();
    }
}
