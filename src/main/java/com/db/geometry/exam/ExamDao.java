package com.db.geometry.exam;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RestResource;

public interface ExamDao extends MongoRepository<Exam, String> {
    @RestResource(path = "id")
    Exam findById(String id);
}