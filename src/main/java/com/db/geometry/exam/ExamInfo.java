package com.db.geometry.exam;

import lombok.Getter;

@Getter
public class ExamInfo {
    private final int teacherId;
    private final String serviceName = "geometry";
    private final String url;

    public ExamInfo(Exam exam) {
        teacherId = exam.getTeacherId();
        url = "/id/" + exam.getId();
    }
}
