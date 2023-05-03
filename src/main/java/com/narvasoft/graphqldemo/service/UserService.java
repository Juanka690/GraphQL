package com.narvasoft.graphqldemo.service;

import com.narvasoft.graphqldemo.models.User;
import com.narvasoft.graphqldemo.repositories.UserRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@GraphQLApi
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //@Autowired
    //private UserService userservice;
    private User user;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GraphQLQuery(name = "users")//READ ALL
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GraphQLQuery(name = "user")//READ ONE BY ID
    public Optional<User> getUser(@GraphQLArgument(name = "id") Long id) {
        return Optional.ofNullable(userRepository.findById(id).orElse(null));
    }

    @MutationMapping(name = "createUser")
    public User createUser(
            @Argument String nombre,
            @Argument String apellido,
            @Argument String email){
        User user = new User();
        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setEmail(email);

        return userRepository.save(user);
    }

    @MutationMapping(name = "updateUser")//UPDATE
    public User updateUser(@GraphQLArgument(name = "id") Long id,
                           @GraphQLArgument(name = "nombre") String nombre,
                           @GraphQLArgument(name = "apellido") String apellido,
                           @GraphQLArgument(name = "email") String email) {
        user= new User(id,nombre, apellido, email);
        return userRepository.save(user);
    }

    @MutationMapping(name = "deleteUser")//DELETE
    public User deleteUser(@GraphQLArgument(name = "id") Long id) {
        userRepository.deleteById(id);
        return user;
    }

}
