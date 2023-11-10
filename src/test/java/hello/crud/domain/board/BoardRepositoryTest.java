package hello.crud.domain.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BoardRepositoryTest {

    BoardRepository boardRepository = new BoardRepository();

    @AfterEach
    void afterEach() {
        boardRepository.clearDb();
    }

    @Test
    void save() {
        //given
        Board board1 = new Board("제목1", "내용1");

        //when
        boardRepository.save(board1);

        //then
        Board findBoard = boardRepository.findById(board1.getId());
        assertThat(findBoard).isEqualTo(board1);

    }

    @Test
    void findAll() {
        //given
        Board board1 = new Board("제목1", "내용1");
        Board board2 = new Board("제목2", "내용2");

        boardRepository.save(board1);
        boardRepository.save(board2);

        //when
        List<Board> result = boardRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(board1, board2);
    }

    @Test
    void update() {

        //given
        Board board1 = new Board("제목1", "내용1");
        Board saveBoard = boardRepository.save(board1);
        Long boardId = saveBoard.getId();

        //when
        Board updateParam = new Board("제목2", "내용2");
        boardRepository.update(boardId, updateParam);

        Board findBoard = boardRepository.findById(boardId);

        //then
        assertThat(findBoard.getTitle()).isEqualTo(updateParam.getTitle());
        assertThat(findBoard.getContent()).isEqualTo(updateParam.getContent());
    }

    @Test
    void delete() {

        //given
        Board board1 = new Board("제목1", "내용1");
        Board board2 = new Board("제목2", "내용2");

        boardRepository.save(board1);
        boardRepository.save(board2);

        //when
        boardRepository.delete(board1);

        List<Board> result = boardRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(1);
    }
}