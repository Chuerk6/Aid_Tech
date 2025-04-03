package GRUPO1.TP.servicesimpl;
import GRUPO1.TP.dto.DTOUser;
import GRUPO1.TP.entities.AuthorityName;
import GRUPO1.TP.entities.Level;
import GRUPO1.TP.entities.User;
import GRUPO1.TP.exceptions.IncompleteDataException;
import GRUPO1.TP.exceptions.KeyRepeatedDataException;
import GRUPO1.TP.exceptions.ResourceNotFoundException;
import GRUPO1.TP.repositories.AuthorityRepository;
import GRUPO1.TP.repositories.UserRepository;
import GRUPO1.TP.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    public List<User> listAll(){return userRepository.findAll();}
    @Override
    public User findById(Long id) {
        User userFound = userRepository.findById(id).orElse(null);
        if (userFound == null) {
            throw new ResourceNotFoundException("There are no User with the id: "+String.valueOf(id));
        }
        return userFound;
    }

    @Override
    public User register(DTOUser userDTO) {

        if (userDTO.getUserName().length()>4 && userDTO.getPassword().length()>4) {

            User userFound = userRepository.findByUserName(userDTO.getUserName());
            if (userFound != null) {
                throw new KeyRepeatedDataException("User name can not be duplicated");
            };

            User newUser = new User();
            newUser.setUserName(userDTO.getUserName());
            newUser.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
            newUser.setEnabled(true);
            newUser.setPasswordLastUpdate(new Date());
            AuthorityName authorityName=AuthorityName.ROLE_STUDENT;
            if (userDTO.getType().equals("ROLE_STUDENT")) authorityName= AuthorityName.ROLE_STUDENT;
            if (userDTO.getType().equals("ROLE_PRINCIPAL")) authorityName= AuthorityName.ROLE_PRINCIPAL;
            newUser.setAuthorities(
                    List.of(
                            authorityRepository.findByName(authorityName)
                    )
            );

            return userRepository.save(newUser);
        } else {
            throw new IncompleteDataException("User name and password length can not be less than 4 characters");
        }
    }

    @Override
    public User changePassword(DTOUser userDto) {
        if (userDto.getUserName().length()>4 && userDto.getPassword().length()>4) {

            User userFound = userRepository.findByUserName(userDto.getUserName());
            if (userFound == null) {
                throw new ResourceNotFoundException("User name can not be found");
            };

            //userFound.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
            userFound.setPasswordLastUpdate(new Date());
            return userRepository.save(userFound);
        } else {
            throw new IncompleteDataException("User name and password length can not be less than 4 characters");
        }
    }


    //no habia
    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new ResourceNotFoundException("object with id: "+String.valueOf(id)
                    + " not found");
        }
        userRepository.deleteById(id);
    }
}
