package com.portifolio.prod.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portifolio.prod.dto.CreatedUserDTO;
import com.portifolio.prod.dto.UserDTO;
import com.portifolio.prod.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> listUser() {
        return userService.listAll();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.searchById(id)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "User not found"
        )); 
    }
    
    @PostMapping
    public ResponseEntity<UserDTO> createdUser(@Valid @RequestBody CreatedUserDTO dto) {
        
        if (userService.mailAlreadyExists(dto.getMail())) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Email registered system"
            );
        } 
    
        UserDTO userCreated = userService.created(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
    
    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @Valid @RequestBody CreatedUserDTO dto) {
        return userService.update(id, dto)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User not found"
            ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.delete(id);

        if (deleted) {
            return ResponseEntity.ok("User successfully deleted");
        } else {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User not found"
            );
        }
    }
}
