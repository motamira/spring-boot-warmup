package application.controller;

import application.dto.UserInputDTO;
import application.service.UserService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@AllArgsConstructor
public class UserController {

    private static final Log logger = LogFactory.getLog(UserController.class);

    private enum Result {
        CONFLICT {
            @Override
            public String getResponseBody() {
                return "Attempt to insert user that already exists.";
            }

            @Override
            public ResponseEntity<String> getResponseEntity() {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(getResponseBody());
            }
        },
        BAD_REQUEST {
            @Override
            public String getResponseBody() {
                return  "There was a problem with the request payload. Either a mandatory field "
                    + "is missing or a field type is not compliant with the contract.";
            }

            @Override
            public ResponseEntity<String> getResponseEntity() {
                return ResponseEntity.badRequest().body(getResponseBody());
            }
        },
        CREATED {
            @Override
            public String getResponseBody() {
                return "The request was accepted and the resource (user) correctly created.";
            }

            @Override
            public ResponseEntity<String> getResponseEntity() {
                return ResponseEntity.status(HttpStatus.CREATED).body(getResponseBody());
            }
        };

        public abstract String getResponseBody();
        public abstract ResponseEntity<String> getResponseEntity();
    }

    @Autowired
    UserService userService;

    /**
     *
     * @param user the user parameter dto
     * @param bindingResult the binding result for the user dto
     * @return created in case user was successfully created, or bad request in case of missing data
     */
    @PostMapping(consumes = "application/json")
    public ResponseEntity<String> register(@RequestBody @Valid UserInputDTO user, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            if(userService.register(user)) {
                logger.info(user);
                return Result.CREATED.getResponseEntity();
            } else {
                return Result.CONFLICT.getResponseEntity();
            }
        } else {
            return Result.BAD_REQUEST.getResponseEntity();
        }
    }
}
