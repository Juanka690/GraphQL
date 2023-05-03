package com.narvasoft.graphqldemo.controllers;

import com.narvasoft.graphqldemo.models.User;
import com.narvasoft.graphqldemo.service.UserService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
@GraphQLApi
public class UserController {

    @Autowired
    private UserService userservice;

    @QueryMapping
    public List<User> getUsers() {
        return userservice.getUsers();
    }

    @BatchMapping(typeName = "usuarios")
    public List<User> getUsuarios(List<String> usuarios) {
        return userservice.getUsers().stream().filter(user -> usuarios.contains(user.getNombre())).collect(Collectors.toList());
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

        return userservice.createUser(user.getNombre(), user.getApellido(), user.getEmail());
    }

    @BatchMapping(typeName = "users")
    public List<User> getUsuarios() {
        return userservice.getUsers();
    }

    @MutationMapping(name = "updateUser")//UPDATE
    public User updateUser(@GraphQLArgument(name = "id") Long id,
                           @GraphQLArgument(name = "nombre") String nombre,
                           @GraphQLArgument(name = "apellido") String apellido,
                           @GraphQLArgument(name = "email") String email) {
        User user = new User();
        user.setId(id);
        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setEmail(email);

        return userservice.updateUser(user.getId(), user.getNombre(), user.getApellido(), user.getEmail());
    }

    @MutationMapping(name = "deleteUser")//DELETE
    public User deleteUser(@Argument(name = "id") Long id) {
        return userservice.deleteUser(id);
    }
}