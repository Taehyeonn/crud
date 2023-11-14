package hello.crud.controller;

import hello.crud.domain.board.Board;
import hello.crud.domain.user.User;
import hello.crud.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/list")
    public String list(Model model) {
        List<User> list = userRepository.findAll();
        model.addAttribute("list", list);
        return "user/list";
    }

    @GetMapping("/list/add")
    public String add() {
        return "user/add";
    }

    @PostMapping("/list/add")
    public String addUser(User user, RedirectAttributes redirectAttributes) {
        User saveUser = userRepository.save(user);
        redirectAttributes.addAttribute("userNum", saveUser.getId());
        redirectAttributes.addAttribute("status", true);//파라미터로 추가
        return "redirect:/user/detail/{userNum}";
    }
//
//    @PostMapping("/add")
//    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
//        Item saveItem = itemRepository.save(item);
//        redirectAttributes.addAttribute("itemId", saveItem.getId());
//        redirectAttributes.addAttribute("status", true);//파라미터로 추가
//        return "redirect:/basic/items/{itemId}";
//    }

    @GetMapping("/detail/{userNum}")
    public String detail(@PathVariable Long userNum, Model model) {
        User user = userRepository.findById(userNum);
        model.addAttribute("user", user);
        return "user/detail";
    }

    @PostConstruct
    public void init() {
        userRepository.save(new User("임시아이디1", "임시비밀번호1", "임시이메일1"));
        userRepository.save(new User("임시아이디2", "임시비밀번호2", "임시이메일2"));
    }

    @GetMapping("/detail/{userNum}/delete")
    public String delete() {
        return "user/delete";
    }

    @PostMapping("/detail/{userNum}/delete")
    public String deleteUser(@PathVariable User userNum) {
        userRepository.delete(userNum);
        return "redirect:/user/list";
    }
}