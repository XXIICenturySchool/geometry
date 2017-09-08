package com.db.geometry.tasks;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class Task {
    private final List<String> answer;
    private final String question;
    private final List<String> options = new ArrayList<>();
    private final String pictureUrl;

    @java.beans.ConstructorProperties({"answer", "question", "pictureUrl"})
    Task(List<String> answer, String question, String pictureUrl) {
        this.answer = answer;
        this.question = question;
        this.pictureUrl = pictureUrl;
    }

    public static TaskBuilder builder() {
        return new TaskBuilder();
    }

    public static class TaskBuilder {
        private List<String> answer;
        private String question;
        private String pictureUrl;

        TaskBuilder() {
        }

        public Task.TaskBuilder answer(String answer) {
            this.answer = Arrays.asList(answer);
            return this;
        }

        public Task.TaskBuilder question(String question) {
            this.question = question;
            return this;
        }

        public Task.TaskBuilder url(String pictureUrl) {
            this.pictureUrl = pictureUrl;
            return this;
        }

        public Task build() {
            return new Task(answer, question, pictureUrl);
        }

        public String toString() {
            return "com.db.geometry.tasks.Task.TaskBuilder(answer=" + this.answer + ", question=" + this.question + ", pictureUrl=" + this.pictureUrl + ")";
        }
    }
}
