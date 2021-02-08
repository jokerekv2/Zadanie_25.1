package pl.dm.taskmanager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String home(Model model, @RequestParam(required = false) Priority priority) {

        List<Task> tasks;
        if (priority != null) {
            tasks = taskRepository.findByPriority(priority);
        } else {
            tasks = taskRepository.findAll();
        }

        model.addAttribute("tasks", tasks);
        return "home";
    }

    @GetMapping("/show")
    public String showByDoneStatus(Model model, @RequestParam Boolean done) {
        List<Task> byDone = taskRepository.findByDone(done);
        if (byDone != null) {
            model.addAttribute("tasks", byDone);
            return "showByDoneStatus";
        } else {
            return "redirect:/";
        }

    }


    @GetMapping("/edit/{id}")
    public String searchById(@PathVariable Long id, Model model) {
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
    public String addTask(Model model) {
        Task task = new Task();
        model.addAttribute("task", task);
        return "addOrEdit";
    }

    @PostMapping("/task/edit")
    public String editTask(Task task) {
        if (task.getCreateDate() == null) {
            task.setCreateDate(LocalDate.now());
        }
        taskRepository.save(task);
        return "redirect:/";
    }
}
