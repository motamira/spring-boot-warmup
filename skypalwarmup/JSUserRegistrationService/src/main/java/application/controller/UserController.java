package application.controller;

import application.entity.User;
import application.service.UserService;
import commons.dto.UserInputDTO;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (c) 2016-2018, Jumia.
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class UserController {

    private static final Log logger = LogFactory.getLog(UserController.class);

    private final UserService userService;

    /**
     * @param user the user parameter dto
     * @param bindingResult the binding result for the user dto
     * @return created in case user was successfully created, or bad request in case of missing data
     */
    @PostMapping(consumes = "application/json")
    public ResponseEntity<String> register(@RequestBody @Valid UserInputDTO user, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            if (userService.register(mapFromUserDTO(user))) {
                logger.info(user);
                return RegistrationActionResponses.CREATED.getResponseEntity();
            } else {
                return RegistrationActionResponses.CONFLICT.getResponseEntity();
            }
        } else {
            return RegistrationActionResponses.BAD_REQUEST.getResponseEntity();
        }
    }

    private User mapFromUserDTO(UserInputDTO userInputDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userInputDTO, User.class);
    }
}
