package io.heig.tasks.repositories;

import io.heig.tasks.entities.TaskEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 */
@Repository
public interface TaskRepository extends MongoRepository<TaskEntity, String>{
    TaskEntity findOneByTaskId(String taskId);
}
