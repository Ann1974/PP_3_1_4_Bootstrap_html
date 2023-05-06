package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class LoadUser {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public LoadUser(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @Transactional
    @PostConstruct
    public void load() {

        Role roleAdmin = new Role( "ROLE_ADMIN");
        Role roleUser = new Role( "ROLE_USER");
        Set<Role> adminSet = new HashSet<>();
        Set<Role> userSet = new HashSet<>();

        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);

        adminSet.add(roleAdmin);
        adminSet.add(roleUser);


        User admin = new User("Boris", "Borisov", 25, "email@ru.ru","BorisKata",
                "100", adminSet);

        User user = new User("Ivan", "Ivanov", 20, "emai1l@ru.ru", "IvanKata",
              "50", adminSet);

        User userOne = new User("Vasya", "Vasilev", 20 , "emai2@ru.ru", "VasyaKata",
                "50", adminSet);


        userService.saveUser(admin);
        userService.saveUser(user);
        userService.saveUser(userOne);


    }


}
