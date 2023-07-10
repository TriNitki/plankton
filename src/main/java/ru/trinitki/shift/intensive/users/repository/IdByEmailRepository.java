package ru.trinitki.shift.intensive.users.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import ru.trinitki.shift.intensive.users.repository.entity.IdByEmail;

public interface IdByEmailRepository extends CassandraRepository<IdByEmail, IdByEmail.Key> {
    IdByEmail findByKey_Email(String email);
}
