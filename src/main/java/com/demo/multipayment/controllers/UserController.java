package com.demo.multipayment.controllers;

import com.demo.multipayment.services.abstracts.UserService;
import com.demo.multipayment.services.dtos.user.requests.AddUserRequest;
import com.demo.multipayment.services.dtos.user.requests.UpdateUserRequest;
import com.demo.multipayment.services.dtos.user.responses.GetAllUsersResponse;
import com.demo.multipayment.services.dtos.user.responses.GetByIdUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody AddUserRequest addUserRequest) {

        this.userService.add(addUserRequest);

    }

    @DeleteMapping()
    public void delete(@PathVariable Integer id) {

        this.userService.delete(id);

    }

    @PutMapping()
    public void update(@RequestBody UpdateUserRequest updateUserRequest) {

        this.userService.update(updateUserRequest);

    }

    @GetMapping()
    public List<GetAllUsersResponse> getAll() {

        return this.userService.getAll();

    }

    @GetMapping("/{id}")
    public GetByIdUserResponse getById(@PathVariable Integer id) {

        return this.userService.getById(id);

    }

}
