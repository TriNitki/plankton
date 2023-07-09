package ru.trinitki.shift.intensive.users.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import ru.trinitki.shift.intensive.users.repository.entity.Users;

import java.util.UUID;

public interface UsersRepository extends CassandraRepository<Users, Users.Key> {
    Users findByKey_Id(UUID id);
    //Users findByKey_Email(String email);
}
