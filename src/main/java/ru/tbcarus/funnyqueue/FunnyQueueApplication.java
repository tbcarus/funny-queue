package ru.tbcarus.funnyqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.tbcarus.funnyqueue.controller.AdminUserController;
import ru.tbcarus.funnyqueue.model.Role;
import ru.tbcarus.funnyqueue.model.Slot;
import ru.tbcarus.funnyqueue.model.User;
import ru.tbcarus.funnyqueue.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class FunnyQueueApplication {

    public static void main(String[] args) throws JsonProcessingException {
        ConfigurableApplicationContext context = SpringApplication.run(FunnyQueueApplication.class, args);

        AdminUserController controller = context.getBean(AdminUserController.class);

        ObjectMapper mapper = new ObjectMapper();

//        Slot slot = Slot.builder()
//                .dateTime(LocalDateTime.of(2025, 1, 5, 15, 0)).build();
//        List<Slot> slots = new ArrayList<>();
////        slots.add(slot);
//        User user = User.builder()
//                .name("NewUser")
//                .email("nu@qwe.rt")
//                .password("pass")
//                .enabled(true)
//                .roles(Set.of(Role.USER))
//                .slots(slots)
//                .build();
//        String s = mapper.writeValueAsString(user);
//        System.out.println(s);
//        {"id":null,"name":"NewUser","email":"nu@qwe.rt","password":"pass","enabled":true,"roles":["USER"],"myQueue":null,"slots":null,"createDate":null,"lastUpdate":null}


//        List<User> users = controller.getUsers();
//        System.out.println(users);

//        User byId = controller.getById(1);
//        System.out.println(byId);

//        User byEmail = controller.getByEmail("l2@og.in");
//        System.out.println(byEmail);

//        User user = User.builder().email("e1@mail.com").password("1234").name("Alex").enabled(true).roles(new HashSet<>()).build();
//        user.addRole(Role.USER);
//        user.addRole(Role.ADMIN);
//        controller.createUser(user);

//        User byId = controller.getById(3);
//        byId.setName(byId.getName() + 1);
//        controller.updateUser(3, byId);
//        User byIdUpdated = controller.getById(3);
//        System.out.println(byIdUpdated);

//        controller.deleteUser(7);

//        controller.reverseEnableUser(3);


    }

}
