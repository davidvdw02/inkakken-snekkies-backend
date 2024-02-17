package nl.inkakken.snekkies.inkakkensnekkiesbackend.services;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import nl.inkakken.snekkies.inkakkensnekkiesbackend.repositories.AttendeeRepository;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.Attendee;
@Service
public class AttendeeService {


        private final Logger logger = LoggerFactory.getLogger(AttendeeService.class);

     private final AttendeeRepository attendeeRepository;

    @Autowired
    public AttendeeService(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    public List<Attendee> getAllAttendees() {
        logger.debug("Getting all attendees");
        return attendeeRepository.findAll();
    }

    public Attendee getAttendeeById(UUID id) {
        logger.debug("Getting attendee with id: " + id);
        Attendee attendee = attendeeRepository.findById(id).orElseThrow(() -> {
            logger.error("Attendee not found with id: " + id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attendee not found with id: " + id);
        });
        return attendee;
    }

    public Attendee getAttendeeByName(String name) {
        logger.debug("Getting attendee with name:" + name);
        Attendee attendee = attendeeRepository.findByName(name).orElseThrow(() -> {
            logger.error("Attendee not found with name: " + name);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attendee not found with name: " + name);
        });
        return attendee;
    }

    public Attendee createAttendee(Attendee attendee) {
        logger.debug("Creating attendee");
        return attendeeRepository.save(attendee);
    }

    public void deleteAttendee(UUID id) {
        logger.debug("Deleting attendee with id: " + id);
        attendeeRepository.deleteById(id);
    }
}
