package org.Organizel.Organizel.controller;

import org.Organizel.Organizel.domain.Tasks;
import org.Organizel.Organizel.domain.User;
import org.Organizel.Organizel.repos.TasksRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.Map;

@Controller
@RequestMapping("/tasks")
public class TasksController {

    private final TasksRepository tasksRepository;

    public TasksController(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @GetMapping
    public String tasks(@AuthenticationPrincipal User user, @ModelAttribute(name = "taskObj")Tasks task, Model model){
        Iterable<Tasks> tasks = tasksRepository.findAllByAuthor(user);
        model.addAttribute("tasks",tasks);
        return "tasks";
    }
    @PostMapping("/add")
    public String add(
            @ModelAttribute(name = "taskObj") @Valid Tasks taskObj,
            @AuthenticationPrincipal User user,
            BindingResult bindingResult,
            Model model){
        if(bindingResult.hasErrors()){
            return "redirect:/tasks";
        }
        taskObj.setAuthor(user);
        tasksRepository.save(taskObj);
        Iterable<Tasks> tasks = tasksRepository.findAllByAuthor(user);
        model.addAttribute("tasks",tasks);
        return "redirect:/tasks";
    }
    @PostMapping("/find")
    public String find(@AuthenticationPrincipal User user,@RequestParam String text, @ModelAttribute(name = "taskObj")Tasks task, Model model){
        Iterable<Tasks> tasksList;
        if(text==null || text.isEmpty()){
            model.addAttribute("message","Nothing found :(");
            tasksList = null;
        }else {
            tasksList = tasksRepository.findByTaskContainingAndAuthor(text,user);
        }
        model.addAttribute("tasks",tasksList);
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

    @PostMapping("/edit")
    public String editTask(@AuthenticationPrincipal User user, @ModelAttribute(name = "taskObj") @Valid Tasks taskObj, Model model){

        Tasks task = tasksRepository.findById(taskObj.getId()).orElse(null);

        if(task != null && !taskObj.getTask().isEmpty()) {
            tasksRepository.deleteById(taskObj.getId());
            task.setTask(taskObj.getTask());
            task.setDate(taskObj.getDate());
            tasksRepository.save(task);
        }
        Iterable<Tasks> tasks = tasksRepository.findAllByAuthor(user);
        model.addAttribute("tasks",tasks);
        return "redirect:/tasks";
    }



}
