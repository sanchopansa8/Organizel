package org.Organizel.Organizel.repos;

import org.Organizel.Organizel.domain.Tasks;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TasksRepository extends CrudRepository<Tasks,Integer> {

    List<Tasks> findByTaskContaining(String word);
}
