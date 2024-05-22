package com.demo.multipayment.services.abstracts;

import com.demo.multipayment.services.dtos.user.requests.AddUserRequest;
import com.demo.multipayment.services.dtos.user.requests.UpdateUserRequest;
import com.demo.multipayment.services.dtos.user.responses.GetAllUsersResponse;
import com.demo.multipayment.services.dtos.user.responses.GetByIdUserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    void add(AddUserRequest addUserRequest);

    void delete(Integer id);

    void update(UpdateUserRequest updateUserRequest);

    GetByIdUserResponse getById(Integer id);

    List<GetAllUsersResponse> getAll();

}
