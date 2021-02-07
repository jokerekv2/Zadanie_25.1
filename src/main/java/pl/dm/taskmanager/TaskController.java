package pl.dm.taskmanager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
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


    @GetMapping("/edit/{id}")
    public String showById(@PathVariable Long id, Model model) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            model.addAttribute("task", task);
            return "addOrEdit";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/addTask")
    public String showById(Model model) {
        Task task = new Task();
        model.addAttribute("task", task);
        return "addOrEdit";
    }

    @PostMapping("/task/edit")
    public String editTas(Task task) {
        if (task.getCreateDate() == null) {
            task.setCreateDate(LocalDate.now());
        }
        taskRepository.save(task);
        return "redirect:/";
    }
}
