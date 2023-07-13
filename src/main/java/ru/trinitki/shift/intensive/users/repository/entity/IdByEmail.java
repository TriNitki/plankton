package ru.trinitki.shift.intensive.users.repository.entity;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.UUID;

@Table(value = "id_by_email")
public class IdByEmail {
    @PrimaryKey
    private Key key = new Key();
    @Column
    private UUID id;

    public static class Key {
    public String getEmail() {
        return key.email;
    }

    public void setEmail(String email) {
        this.key.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @PrimaryKeyClass
    public static class Key {
        @PrimaryKeyColumn(name = "email", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
        private String email;

        public Key() {
        }
    }
}
