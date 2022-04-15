package example.demo.Security;

import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import example.demo.Service.UserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailService userDetailService;
    @Autowired
    javax.sql.DataSource dataSource;
    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    			provider.setUserDetailsService(userDetailService);
    			provider.setPasswordEncoder(new BCryptPasswordEncoder());
    			return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()// Cho phép tất cả mọi người truy cập vào 2 địa chỉ này
                .antMatchers("/getproduct").hasRole("User")
                .antMatchers("/getproduct","/addproduct","edit product","deleteproduct").hasRole("Admin")                
                .and()
                .authorizeRequests()
                .and()
                .formLogin() // Cho phép người dùng xác thực bằng form login
                .loginPage("/login") // trang login
//              .loginProcessingUrl("/loginSecurity") // url login
                .failureUrl("/login?error")
                .defaultSuccessUrl("/getproduct")
                .permitAll() // Tất cả đều được truy cập vào địa chỉ này
                .and()
                .logout() // Cho phép logout
                .logoutUrl("/logout") // url logout
                .logoutSuccessUrl("/") // trang logout trả về
                .permitAll();
    }
}
