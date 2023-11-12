package hello.crud.controller;

import hello.crud.domain.user.User;
import hello.crud.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

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

    @GetMapping("/add")
    public String add() {
        return "user/add";
    }

    @PostMapping("/add")
    public String addUser(User user, RedirectAttributes redirectAttributes) {
        User saveUser = userRepository.save(user);
        redirectAttributes.addAttribute("userNum", saveUser.getId());
        redirectAttributes.addAttribute("status", true);//파라미터로 추가
        return "redirect:/user/list/{userNum}";
    }

    @GetMapping("/detail/{userNum}")
    public String detail(@PathVariable Long userNum, Model model) {
        User user = userRepository.findById(userNum);
        model.addAttribute("user", user); ///////////////
        return "user/list/{userNum}";
    }

    @PostConstruct
    public void init() {
        userRepository.save(new User("임시아이디1", "임시비밀번호1", "임시이메일1"));
        userRepository.save(new User("임시아이디2", "임시비밀번호2", "임시이메일2"));
    }

    @GetMapping("/{userNum}/edit")
    public String edit(@PathVariable Long userNum, Model model) {
        User user = userRepository.findById(userNum);
        model.addAttribute(user);
        return "user/editForm";
    }

    @PostMapping("/{userNum}/edit")
    public String editUser(@PathVariable Long userNum, @ModelAttribute User user) {
        userRepository.update(userNum, user);
        return "redirect:/user/list/{userNum}";
    }

    @GetMapping("/{userNum}/delete")
    public String delete() {
        return "/user/deleteForm";
    }

    @PostMapping("/{userNum}/delete")
    public String deleteUser(@PathVariable User userNum, @ModelAttribute User user) {
        userRepository.delete(userNum);
        return "redirect:/user/list";
    }
}
