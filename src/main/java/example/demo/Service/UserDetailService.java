package example.demo.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import example.demo.Entity.RoleEntity;
import example.demo.Entity.UserEntity;
import example.demo.Repository.RoleRepository;
import example.demo.Repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		Set<RoleEntity> roles = user.getRoles();
		for (RoleEntity role : roles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}

		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				grantedAuthorities);
	}
	public UserEntity Register(UserEntity user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setUserName(user.getUserName());
        userEntity.setFull_Name(user.getFull_Name());
        HashSet<RoleEntity> roles = new HashSet<>();
        roles.add(roleRepository.findByName("Admin"));
//        roles.add(roleRepository.findByName("User"));
        userEntity.setRoles(roles);
        return userRepository.save(userEntity);
	}

	// tìm user theo tên
	public UserEntity findByUserName(String userName) {
		return userRepository.findByUserName(userName) ;
	}
	public UserEntity findByEmail(String Email) {
		return userRepository.findByEmail(Email);
	}
//	// lấy tất cả các user
	public List<UserEntity> getAllUser() {
		List<UserEntity> userEntities = userRepository.findAll();
		
		return userEntities;                           
	}
}
