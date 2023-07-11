package ru.trinitki.shift.intensive.events.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import ru.trinitki.shift.intensive.events.repository.entity.EventGroups;

public interface EventGroupsRepository extends CassandraRepository<EventGroups, EventGroups.Key> {

}
