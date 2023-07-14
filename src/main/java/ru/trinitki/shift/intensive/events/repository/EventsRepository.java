package ru.trinitki.shift.intensive.events.repository;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import ru.trinitki.shift.intensive.events.repository.entity.Events;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface EventsRepository extends CassandraRepository<Events, Events.Key> {
    @AllowFiltering
    Events findByEventId(UUID eventId);

    List<Events> findAllByKey_OwnerIdAndKey_DateBetween(UUID ownerId, LocalDate startDate, LocalDate endDate);
}
