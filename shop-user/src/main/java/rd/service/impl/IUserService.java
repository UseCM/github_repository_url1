package rd.service.impl;

import com.rd.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

public interface IUserService {

    User findByUid(Integer uid);

}
