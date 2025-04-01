package midianet.mykudos.infra.database.jpa.repository;

import midianet.mykudos.application.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
