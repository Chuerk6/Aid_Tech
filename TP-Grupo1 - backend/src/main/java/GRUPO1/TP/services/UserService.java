package GRUPO1.TP.services;
import GRUPO1.TP.dto.DTOUser;
import GRUPO1.TP.entities.User;

import java.util.List;

//import GRUPO1.TP.dto.DTOUser;
public interface UserService {
    public List<User> listAll();
     User findById(Long id);

   User register(DTOUser user);
//
    User changePassword(DTOUser user);

    //no habia
    public void delete(Long id);
}
