package ru.trinitki.shift.intensive.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.trinitki.shift.intensive.users.dto.*;
import ru.trinitki.shift.intensive.users.exception.EmailConflictException;
import ru.trinitki.shift.intensive.users.exception.UserForbiddenException;
import ru.trinitki.shift.intensive.users.exception.UserNotAuthorizedException;
import ru.trinitki.shift.intensive.users.exception.UserNotFoundException;
import ru.trinitki.shift.intensive.users.repository.IdByEmailRepository;
import ru.trinitki.shift.intensive.users.repository.entity.IdByEmail;
import ru.trinitki.shift.intensive.users.repository.entity.Users;
import ru.trinitki.shift.intensive.users.repository.UsersRepository;

import java.util.UUID;

@Service("usersService")
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final IdByEmailRepository idByEmailRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, IdByEmailRepository idByEmailRepository) {
        this.usersRepository = usersRepository;
        this.idByEmailRepository = idByEmailRepository;
    }

    @Override
    public SignUpResponseDto create(SignUpRequestDto userDto) {
        try {
            if (this.usersRepository.findByKey_Id(this.idByEmailRepository.findByKey_Email(userDto.getEmail()).getId()) != null) {
                throw new EmailConflictException();
            }
        } catch (NullPointerException ignored) {}

        UUID token = UUID.randomUUID();
        Users user = new Users();
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setActive(Boolean.TRUE);
        user.setId(token);
        user.setAccessToken(token.toString());
        this.usersRepository.save(user);
        IdByEmail byEmail = new IdByEmail();
        byEmail.setEmail(user.getEmail());
        byEmail.setId(user.getId());
        this.idByEmailRepository.save(byEmail);

        return new SignUpResponseDto(user.getFullName(), user.getEmail(), user.getActive(), user.getId(), user.getAccessToken());
    }

    @Override
    public LogInResponseDto login(LogInRequestDto userDto) {
        Users user;
        try {
            user = this.usersRepository.findByKey_Id(this.idByEmailRepository.findByKey_Email(userDto.getEmail()).getId());
        } catch (Exception e) {
            throw new UserNotAuthorizedException();
        }

        if (!user.getPassword().equals(userDto.getPassword())) {
            throw new UserNotAuthorizedException();
        }

        return new LogInResponseDto(user.getAccessToken());
    }

    @Override
    public RetrieveResponseDto find(UUID id, String token) {
        validateUser(id, token);

        Users user = this.usersRepository.findByKey_Id(id);

        return new RetrieveResponseDto(user.getFullName(), user.getEmail(), user.getActive(), user.getId());
    }

    @Override
    public UpdateResponseDto update(UUID id, UpdateRequestDto updateUser, String token) {
        validateUser(id, token);

        Users user = this.usersRepository.findByKey_Id(id);

        if (updateUser.getActive() != null){
            user.setActive(updateUser.getActive());
        }
        if (updateUser.getEmail() != null){
            user.setEmail(updateUser.getEmail());
        }
        if (updateUser.getFullName() != null){
            user.setFullName(updateUser.getFullName());
        }
        this.usersRepository.insert(user);
        return new UpdateResponseDto(user.getFullName(), user.getEmail(), user.getActive(), id);
    }

    private void validateUser(UUID id, String token) {
        UUID UuidToken = UUID.fromString(token);
        Users user = this.usersRepository.findByKey_Id(UuidToken);

        if (this.usersRepository.findByKey_Id(id) == null) {
            throw new UserNotFoundException();
        }

        if (user == null) {
            throw new UserNotAuthorizedException();
        }

        if (!user.getId().equals(id)) {
            throw new UserForbiddenException();
        }
    }
}
