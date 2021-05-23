package com.github.talick.photoappusersapi.ui.controller;

import com.github.talick.photoappusersapi.service.UsersService;
import com.github.talick.photoappusersapi.shared.UserDto;
import com.github.talick.photoappusersapi.ui.model.CreateUserRequestModel;
import com.github.talick.photoappusersapi.ui.model.CreateUserResponseModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final Environment environment;
    private final UsersService usersService;

    @Autowired
    public UsersController(Environment environment, UsersService usersService) {
        this.environment = environment;
        this.usersService = usersService;
    }

    @GetMapping("/status/check")
    public String getStatus(HttpServletRequest request) {
        System.out.println(request.getRemoteAddr());
        return "Working on port: " + environment.getProperty("local.server.port") + ", with token = " + environment.getProperty("token.secret");
    }

    @PostMapping
    public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel requestModel) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = modelMapper.map(requestModel, UserDto.class);

        userDto = usersService.createUser(userDto);
        CreateUserResponseModel returnValue = modelMapper.map(userDto, CreateUserResponseModel.class);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(returnValue);
    }
}
