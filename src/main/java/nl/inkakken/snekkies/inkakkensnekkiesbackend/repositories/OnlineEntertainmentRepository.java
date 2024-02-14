package nl.inkakken.snekkies.inkakkensnekkiesbackend.repositories;

import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.OnlineEntertainment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OnlineEntertainmentRepository extends JpaRepository<OnlineEntertainment, UUID> {
}
