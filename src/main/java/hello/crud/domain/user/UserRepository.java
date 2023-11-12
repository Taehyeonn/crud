package hello.crud.domain.user;

import hello.crud.domain.board.Board;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    private static final Map<Long, User> db = new HashMap<>();
    private static Long sequence = 0L;

    public User findById(Long num) {
        return db.get(num);
    }

    public User save(User user) {
        user.setId(++sequence);
        db.put(user.getId(), user);
        return user;
    }

    public void update(Long id, User updateParam) {
        User findUser = findById(id);
        findUser.setUserId(updateParam.getUserId());
        findUser.setUserPw(updateParam.getUserPw());
        findUser.setEmail(updateParam.getEmail());
    }

    public List<User> findAll() {
        return new ArrayList<>(db.values());
    }

    public void delete(User user) {
        db.remove(user.getId());
    }

    public void clearDb() {
        db.clear();
    }
}
