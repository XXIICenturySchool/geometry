package com.db.geometry.tasks;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class Task {
    private final List<String> answers;
    private final String question;
    private final List<String> options = new ArrayList<>();
    private final String pictureUrl;

    @java.beans.ConstructorProperties({"answers", "question", "pictureUrl"})
    Task(List<String> answers, String question, String pictureUrl) {
        this.answers = answers;
        this.question = question;
        this.pictureUrl = pictureUrl;
    }

    public static TaskBuilder builder() {
        return new TaskBuilder();
    }

    public static class TaskBuilder {
        private List<String> answers;
        private String question;
        private String pictureUrl;

        TaskBuilder() {
        }

        public Task.TaskBuilder answer(String answer) {
            this.answers = Arrays.asList(answer);
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
            return new Task(answers, question, pictureUrl);
        }

        public String toString() {
            return "com.db.geometry.tasks.Task.TaskBuilder(answers=" + this.answers + ", question=" + this.question + ", pictureUrl=" + this.pictureUrl + ")";
        }
    }
}
