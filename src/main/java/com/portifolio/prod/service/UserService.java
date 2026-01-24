package com.portifolio.prod.service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

import com.portifolio.prod.dto.UserDTO;
import com.portifolio.prod.model.UserModel;
import com.portifolio.prod.dto.CreatedUserDTO;
import com.portifolio.prod.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDTO> listAll() {
        return userRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public Optional<UserDTO> searchById(Long id) {
        return userRepository.findById(id)
            .map(this::convertToDTO);
    }

    public UserDTO created(CreatedUserDTO dto) {
        UserModel userModel = new UserModel();
        userModel.setName(dto.getName());
        userModel.setMail(dto.getMail());
        userModel.setPassword(passwordEncoder.encode(dto.getPassword()));
        userModel.setRole(dto.getRole());

        UserModel saveUSer = userRepository.save(userModel);

        return convertToDTO(saveUSer);
    }

    public Optional<UserDTO> update(Long id, CreatedUserDTO dto) {
        Optional<UserModel> existsUser = userRepository.findById(id);

        if (existsUser.isPresent()) {
            UserModel user = existsUser.get();
            user.setName(dto.getName());
            user.setMail(dto.getMail());
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
            user.setRole(dto.getRole());

            UserModel updateUser = userRepository.save(user);

            return Optional.of(convertToDTO(updateUser));
        }
        return Optional.empty();
    }

    public boolean delete(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean mailAlreadyExists(String mail) {
        return userRepository.existsByMail(mail);
    }

    public Optional<UserModel> searchByMail(String mail) {
        return userRepository.findByMail(mail);
    }

    private UserDTO convertToDTO(UserModel userModel) {
        UserDTO dto = new UserDTO();
        dto.setId(userModel.getId());
        dto.setName(userModel.getName());
        dto.setMail(userModel.getMail());
        dto.setRole(userModel.getRole());
        return dto;
    }

    public Optional<UserModel> authentic(String mail, String password) {
        Optional<UserModel> userOpt = searchByMail(mail);

        if (userOpt.isPresent()) {
            UserModel user = userOpt.get();

            if (passwordEncoder.matches(password, user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
