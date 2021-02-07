package pl.dm.taskmanager;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean doneOrNot;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public boolean isDoneOrNot() {
        return doneOrNot;
    }

    public void setDoneOrNot(boolean doneOrNot) {
        this.doneOrNot = doneOrNot;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
}
