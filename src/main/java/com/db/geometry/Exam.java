package com.db.geometry;

import com.db.geometry.tasks.Task;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
public class Exam {
    @Id
    private String id;
    private List<Task> tasks;

    public Exam(List<Task> tasks) {
        this.tasks = tasks;
    }
}
