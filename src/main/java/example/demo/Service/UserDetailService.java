package example.demo.Service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import example.demo.Model.User;
import example.demo.Repository.UserRepository;
import example.demo.Security.MyUserDetail;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

  @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new MyUserDetail(user);
        }
        
    }


