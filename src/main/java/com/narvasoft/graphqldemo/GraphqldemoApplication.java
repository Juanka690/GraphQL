package com.narvasoft.graphqldemo;

import com.narvasoft.graphqldemo.models.User;
import com.narvasoft.graphqldemo.service.UserService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@SpringBootApplication
public class GraphqldemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(GraphqldemoApplication.class, args);
    }
}
