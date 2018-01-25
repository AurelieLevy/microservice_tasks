package io.heig.tasks.repositories;

import io.heig.tasks.entities.ExecutionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 */
@Repository
public interface ExecutionRepository extends MongoRepository<ExecutionEntity, String>{
}
