package ru.trinitki.shift.intensive.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.trinitki.shift.intensive.users.dto.*;
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
        Users user = new Users();
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setActive(Boolean.TRUE);
        user.setId(UUID.randomUUID());
        user.setAccessToken("ama-access-token");
        this.usersRepository.save(user);
        IdByEmail byEmail = new IdByEmail();
        byEmail.setEmail(user.getEmail());
        byEmail.setId(user.getId());
        this.idByEmailRepository.save(byEmail);

        return new SignUpResponseDto(user.getFullName(), user.getEmail(), user.getActive(), user.getId(), user.getAccessToken());
    }

    @Override
    public LogInResponseDto login(LogInRequestDto userDto) {
        Users user = this.usersRepository.findByKey_Id(this.idByEmailRepository.findByKey_Email(userDto.getEmail()).getId());
        return new LogInResponseDto(user.getAccessToken());
    }

    @Override
    public RetrieveResponseDto find(UUID id) {
        Users user = this.usersRepository.findByKey_Id(id);
        System.out.println(id);
        return new RetrieveResponseDto(user.getFullName(), user.getEmail(), user.getActive(), user.getId());
    }

    public UpdateResponseDto update(UUID id, UpdateRequestDto updateUser) {
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
}
