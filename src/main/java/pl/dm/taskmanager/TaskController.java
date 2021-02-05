package pl.dm.taskmanager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {

    private TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    public String home(Model model) {

        List<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);

        return "home";
    }



//    @GetMapping("/edit/{id}")
//    public String editById(@PathVariable Long id, Model model) {
//        Optional<Task> optionalTask = taskRepository.findById(id);
//        if (optionalTask.isPresent()) {
//            optionalTask.
//        } else {
//            return "redirect:/";
//        }
//
//    }

}
