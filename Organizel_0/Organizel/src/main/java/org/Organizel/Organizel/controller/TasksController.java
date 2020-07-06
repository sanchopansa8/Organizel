package org.Organizel.Organizel.controller;

import org.Organizel.Organizel.domain.Tasks;
import org.Organizel.Organizel.domain.User;
import org.Organizel.Organizel.repos.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Map;

@Controller
@RequestMapping("/tasks")
public class TasksController {

    @Autowired
    private TasksRepository tasksRepository;

    @GetMapping
    public String tasks(@AuthenticationPrincipal User user, Map<String, Object> model){
        Iterable<Tasks> tasks = tasksRepository.findAllByAuthor(user);
        model.put("tasks",tasks);
        return "tasks";
    }

    @PostMapping
    public String add(@AuthenticationPrincipal User user, @RequestParam String task, @RequestParam Date date, Map<String, Object> model){
        Tasks newTask = new Tasks(task,date,user);
        tasksRepository.save(newTask);
        Iterable<Tasks> tasks = tasksRepository.findAllByAuthor(user);
        model.put("tasks",tasks);
        return "tasks";
    }
    @PostMapping("/find")
    public String find(@AuthenticationPrincipal User user,@RequestParam String text, Map<String, Object> model){
        Iterable<Tasks> tasksList;
        if(text==null || text.isEmpty()){
            tasksList = tasksRepository.findAllByAuthor(user);
        }else {tasksList = tasksRepository.findByTaskContaining(text);}
        model.put("tasks",tasksList);
        return "tasks";
    }
    @PostMapping("/remove")
    public String remove(@AuthenticationPrincipal User user,@RequestParam("task") Integer taskId , Map<String, Object> model){
        System.out.println(taskId);
        tasksRepository.deleteById(taskId);
        Iterable<Tasks> tasks = tasksRepository.findAllByAuthor(user);
        model.put("tasks",tasks);
        return "redirect:/tasks";
    }



}
