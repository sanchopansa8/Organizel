package org.Organizel.Organizel;
import org.Organizel.Organizel.domain.Tasks;
import org.Organizel.Organizel.domain.UserInfo;
import org.Organizel.Organizel.repos.TasksRepository;
import org.Organizel.Organizel.repos.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
public class GreetingController {
    @Autowired
    private UserInfoRepository userRepository;

    @Autowired
    private TasksRepository tasksRepository;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/SIGNUP")
    private String signUp(@RequestParam String userName
            , @RequestParam String password, Map<String,Object> model){
        UserInfo user = new UserInfo(userName,password);
        userRepository.save(user);
        return "registered";
    }

    @GetMapping("/tasks")
    public String tasks(Map<String, Object> model){
        Iterable<Tasks> tasks = tasksRepository.findAll();
        model.put("tasks",tasks);
        return "tasks";
    }

    @PostMapping("/tasks")
    public String add(@RequestParam String task, @RequestParam Date date, Map<String, Object> model){
        Tasks newTask = new Tasks(task,date);
        tasksRepository.save(newTask);
        Iterable<Tasks> tasks = tasksRepository.findAll();
        model.put("tasks",tasks);
        return "tasks";
    }
    @PostMapping("/find")
    public String find(@RequestParam String text, Map<String, Object> model){
        Iterable<Tasks> tasksList;
        if(text==null || text.isEmpty()){
            tasksList = tasksRepository.findAll();
        }else {tasksList = tasksRepository.findByTaskContaining(text);}
        model.put("tasks",tasksList);
        return "tasks";
    }



}