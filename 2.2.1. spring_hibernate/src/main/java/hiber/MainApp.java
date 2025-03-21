package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Иван", "Петров", "ivan@mail.com");
      user1.setCar(new Car("BMW",1));
      userService.add(user1);
      User user2 = new User("Андрей", "Смирнов", "andrey@mail.com");
      user2.setCar(new Car("BMW",6));
      userService.add(user2);
      User user3 = new User("Юрий", "Шагаев", "youriy@mail.com");
      user3.setCar(new Car("BMW",7));
      userService.add(user3);
      User user4 = new User("Денис", "Щеглов", "denis@mail.com");
      user4.setCar(new Car("BMW",4));
      userService.add(user4);
      User user5 = new User("Валерий", "Кузнецов", "valera@mail.com");
      user5.setCar(new Car("BMW",4));
      userService.add(user5);
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user);
         System.out.println();
      }

      List<User> userList = userService.getUserByAuto("BMW", 4);

      if (!userList.isEmpty()) {
         for (User user : userList) {
            System.out.println(user);
            System.out.println("__________________________________");
         }
      } else {
         System.out.println("Пользователь с таким автомобилем не зарегестрирован");
      }

      context.close();
   }
}
