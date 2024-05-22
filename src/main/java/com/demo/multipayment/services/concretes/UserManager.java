package com.demo.multipayment.services.concretes;

import com.demo.multipayment.core.utilities.mappers.ModelMapperService;
import com.demo.multipayment.entities.concretes.User;
import com.demo.multipayment.repositories.UserRepository;
import com.demo.multipayment.services.abstracts.UserService;
import com.demo.multipayment.services.constants.Messages;
import com.demo.multipayment.services.dtos.user.requests.AddUserRequest;
import com.demo.multipayment.services.dtos.user.requests.UpdateUserRequest;
import com.demo.multipayment.services.dtos.user.responses.GetAllUsersResponse;
import com.demo.multipayment.services.dtos.user.responses.GetByIdUserResponse;
import com.demo.multipayment.services.rules.UserBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserManager implements UserService {

    private final UserRepository userRepository;

    private final ModelMapperService modelMapperService;

    private final UserBusinessRules userBusinessRules;


    @Override
    public void add(AddUserRequest addUserRequest) {

        this.userBusinessRules.checkIfUserByEmailExists(addUserRequest.getEmail());

        User user = this.modelMapperService.forRequest().map(addUserRequest, User.class);
        user.setId(null);

        this.userRepository.save(user);

    }

    @Override
    public void delete(Integer id) {

        this.userBusinessRules.checkIfUserByIdExists(id);

        User user = this.modelMapperService.forRequest()
                .map(id, User.class);

        this.userRepository.delete(user);

    }

    @Override
    public void update(UpdateUserRequest updateUserRequest) {

        this.userBusinessRules.checkIfUserByIdExists(updateUserRequest.getId());

        User user = this.modelMapperService.forRequest()
                .map(updateUserRequest, User.class);

        this.userRepository.save(user);

    }

    @Override
    public GetByIdUserResponse getById(Integer id) {
        this.userBusinessRules.checkIfUserByIdExists(id);

        GetByIdUserResponse response = this.modelMapperService.forResponse()
                .map(userRepository.findById(id), GetByIdUserResponse.class);

        return response;

    }

    @Override
    public List<GetAllUsersResponse> getAll() {
        List<User> users = userRepository.findAll();

        List<GetAllUsersResponse> usersResponse = users.stream()
                .map(user -> this.modelMapperService.forResponse().map(user, GetAllUsersResponse.class)).toList();

        return usersResponse;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(Messages.USER_NOT_FOUND));
    }
}
