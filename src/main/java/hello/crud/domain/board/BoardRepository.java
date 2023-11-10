package hello.crud.domain.board;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardRepository {

    private static final Map<Long, Board> db = new HashMap<>();
    private static Long sequence = 0L;

    public Board save(Board board) {
        board.setId(++sequence);
        db.put(board.getId(), board);
        return board;
    }

    public Board findById(Long id) {
        return db.get(id);
    }

    public List<Board> findAll() {
        return new ArrayList<>(db.values());
    }

    public void update(Long id, Board updateParam) {
        Board findBoard = findById(id);
        findBoard.setTitle(updateParam.getTitle());
        findBoard.setContent(updateParam.getContent());
    }

    public void delete(Board board) {
        db.remove(board.getId());
    }

    public void clearDb() {
        db.clear();
    }
}
