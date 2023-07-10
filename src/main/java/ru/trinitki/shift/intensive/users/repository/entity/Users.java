package ru.trinitki.shift.intensive.users.repository.entity;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.UUID;

@Table(value = "users")
public class Users {
    @PrimaryKey
    private Key key = new Key();
    @Column(value = "access_token")
    private String accessToken;
    @Column(value = "full_name")
    private String fullName;
    @Column
    private String password;
    @Column
    private String email;
    @Column(value = "is_active")
    private Boolean isActive;

    @PrimaryKeyClass
    public static class Key {
        @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
        private UUID id;

        public Key() {
        }
    }


    public UUID getId() {
        return this.key.id;
    }

    public void setId(UUID id) {
        this.key.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return this.isActive;
    }


    public void setActive(Boolean active) {
        this.isActive = active;
    }
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
