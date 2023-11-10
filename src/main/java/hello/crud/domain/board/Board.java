package hello.crud.domain.board;


import lombok.Data;

@Data
public class Board {

    private Long id;
    private String title;
    private String content;

    public Board() {
    }

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
