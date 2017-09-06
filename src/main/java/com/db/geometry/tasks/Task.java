package com.db.geometry.tasks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class Task {
    private final String answer;
    private final String question;
    private final String url;
}
