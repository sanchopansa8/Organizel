package org.Organizel.Organizel.controller;
import org.Organizel.Organizel.domain.Tasks;
import org.Organizel.Organizel.repos.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.sql.Date;
import java.util.Map;

@Controller
public class GreetingController {

    @Autowired
    private TasksRepository tasksRepository;

    @GetMapping("/")
    public String home() {
        return "home";
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