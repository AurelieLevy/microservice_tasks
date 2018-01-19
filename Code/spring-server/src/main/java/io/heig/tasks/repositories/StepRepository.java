package io.heig.tasks.repositories;

import io.heig.tasks.entities.StepEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StepRepository extends MongoRepository<StepEntity, String>{
}
