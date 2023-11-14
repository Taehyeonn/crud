package hello.crud.domain;

import hello.crud.domain.board.Board;
import hello.crud.domain.board.BoardRepository;
import hello.crud.domain.user.User;
import hello.crud.domain.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRepositoryTest {

    UserRepository userRepository = new UserRepository();

    @AfterEach
    void afterEach() {
        userRepository.clearDb();
    }

    @Test
    void delete() {

        //given
        User user1 = new User("id1", "pw1", "email1");
        User user2 = new User("id2", "pw2", "email2");


        userRepository.save(user1);
        userRepository.save(user2);

        //when
        userRepository.delete(user1);

        List<User> result = userRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(1);
    }
}
