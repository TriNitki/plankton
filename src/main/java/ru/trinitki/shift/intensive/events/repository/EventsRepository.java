package ru.trinitki.shift.intensive.events.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import ru.trinitki.shift.intensive.events.repository.entity.Events;

public interface EventsRepository extends CassandraRepository<Events, Events.Key> {
}
