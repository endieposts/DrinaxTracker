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
        User user = userRepository.save(new User(1, "username1", "dummypassword", "username1@test.com", "1"));

        assertThat(user).hasFieldOrPropertyWithValue("id", 1);
        assertThat(user).hasFieldOrPropertyWithValue("password", "dummypassword");
    }

    @Test
    public void should_find_all_users() {
        User user1 = userRepository.save(new User(2, "username2", "dummypassword", "username2@test.com", "1"));
        User user2 = userRepository.save(new User(3, "username3", "dummypassword", "username3@test.com", "2"));
        User user3 = userRepository.save(new User(4, "username4", "dummypassword", "username4@test.com", "3"));

        Iterable<User> AppUsers = userRepository.findAll();

        assertThat(AppUsers).contains(user1, user2, user3);
    }


}
