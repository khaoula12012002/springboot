package backEnd.springApp.controller;

import backEnd.springApp.dao.entity.User;
import backEnd.springApp.dao.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @GetMapping
    public List<User> getAll(){return this.userRepository.findAll();}

    @GetMapping("/get/{id}")
    public User getById(@PathVariable("id") Integer id){
        return userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("user not found"));
    }

    @PostMapping
    public User save(@RequestBody User user){
        return userRepository.save(user);
    }

    @DeleteMapping("id")
    public void delete(@PathVariable("id") Integer id){
        userRepository.deleteById(id);
    }

}
