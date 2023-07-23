package rd.service;

import com.rd.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rd.dao.UserDao;
import rd.service.impl.IUserService;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;
    @Override
    public User findByUid(Integer uid) {
        return userDao.findById(uid).get();
    }
}
