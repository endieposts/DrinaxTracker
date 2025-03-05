package com.endie.drinaxtracker.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.Test;
import com.endie.drinaxtracker.model.User;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void should_find_no_users_if_repository_is_empty() {
        userRepository.deleteAll();

        Iterable<User> users = userRepository.findAll();

        assertThat(users).isEmpty();
    }

    @Test
    public void should_store_a_user() {
        User user = userRepository.save(new User( "username1", "dummypassword", "username1@test.com", "1"));

        //assertThat(user).hasFieldOrPropertyWithValue("id", 1);
        assertThat(user).hasFieldOrPropertyWithValue("password", "dummypassword");
    }

    @Test
    public void should_find_all_users() {
        User user1 = userRepository.save(new User("username2", "dummypassword", "username2@test.com", "1"));
        User user2 = userRepository.save(new User("username3", "dummypassword", "username3@test.com", "2"));
        User user3 = userRepository.save(new User("username4", "dummypassword", "username4@test.com", "3"));

        Iterable<User> AppUsers = userRepository.findAll();

        assertThat(AppUsers).contains(user1, user2, user3);
    }

    @Test
    public void should_find_user_by_id() {
        User user = userRepository.save(new User("username5", "dummypassword", "username5@test.com", "1"));
        User user2 = userRepository.save(new User("username6", "dummypassword", "username6@test.com", "1"));

        User foundUser = userRepository.findById(user2.getId()).get();

        assertThat(foundUser).isEqualTo(user2);
    }

    @Test
    public void should_update_user_by_id() {
        User user = userRepository.save(new User("username7", "dummypassword", "username7@test.com", "1"));
        User user2 = userRepository.save(new User("username8", "dummypassword", "username8@test.com", "1"));

        User updatedUser = userRepository.save(new User("updatedusername8", "dummypassword", "username8@test.com", "2"));

        User foundUser = userRepository.findById(user2.getId()).get();
        foundUser.setUsername(updatedUser.getUsername());
        foundUser.setRole(updatedUser.getRole());
        userRepository.save(foundUser);

        User checkUser = userRepository.findById(user2.getId()).get();

        assertThat(checkUser.getId()).isEqualTo(user2.getId());
        assertThat(checkUser.getUsername()).isEqualTo(updatedUser.getUsername());
        assertThat(checkUser.getRole()).isEqualTo(updatedUser.getRole());
    }

    @Test
    public void should_delete_user_by_id() {
        userRepository.deleteAll();

        User user = userRepository.save(new User("username9", "dummypassword", "username9@test.com", "1"));
        User user2 = userRepository.save(new User("username10", "dummypassword", "username10@test.com", "1"));

        userRepository.deleteById(user2.getId());

        assertThat(userRepository.findAll()).hasSize(1).contains(user);
    }
}
