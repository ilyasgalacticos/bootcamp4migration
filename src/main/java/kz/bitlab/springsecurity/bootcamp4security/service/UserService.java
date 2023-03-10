package kz.bitlab.springsecurity.bootcamp4security.service;

import kz.bitlab.springsecurity.bootcamp4security.model.Permission;
import kz.bitlab.springsecurity.bootcamp4security.model.User;
import kz.bitlab.springsecurity.bootcamp4security.repository.PermissionRepository;
import kz.bitlab.springsecurity.bootcamp4security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user==null) throw new UsernameNotFoundException("User not found!");
        return user;
    }

    public Boolean registerUser(String email, String password, String rePassword, String fullName){
        Boolean result = null;
        User user = userRepository.findByEmail(email);
        if(user == null){
            result = Boolean.FALSE;
            if(password.equals(rePassword)){
                User newUser = new User();
                newUser.setEmail(email);
                newUser.setPassword(passwordEncoder.encode(password));
                newUser.setFullName(fullName);
                Permission permission = permissionRepository.findByRole("ROLE_USER");
                List<Permission> permissionList = new ArrayList<>();
                permissionList.add(permission);
                newUser.setPermissions(permissionList);
                userRepository.save(newUser);
                result = Boolean.TRUE;
            }
        }
        return result;
    }

    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            return (User) authentication.getPrincipal();
        }
        return null;
    }

    public User insertUser(User user){
        return userRepository.save(user);
    }
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}