package com.db.geometry.exam;

import com.db.geometry.tasks.Task;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ExamResponse {
    private final int teacherId;
    private final List<Task> tasks;

    public ExamResponse(Exam exam) {
        this.teacherId = exam.getTeacherId();
        this.tasks = exam.getTasks();
    }
}
