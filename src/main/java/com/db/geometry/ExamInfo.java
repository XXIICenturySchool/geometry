package com.db.geometry;

import lombok.Getter;

@Getter
public class ExamInfo {
    private final int teacherId = 1307;
    private final String serviceName = "geometry";
    private final String url;

    public ExamInfo(Exam exam) {
        url = "/id/" + exam.getId();
    }
}
