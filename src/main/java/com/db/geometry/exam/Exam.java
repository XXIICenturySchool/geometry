package com.db.geometry.exam;

import com.db.geometry.tasks.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Exam {
    @Id
    private String id;
    private int teacherId;
    private List<Task> tasks;
}
