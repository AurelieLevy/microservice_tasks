package io.heig.tasks.repositories;

import io.heig.tasks.entities.TaskEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
public interface TaskRepository extends CrudRepository<TaskEntity, Long>{

}
