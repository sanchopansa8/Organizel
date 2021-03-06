package org.Organizel.Organizel.repos;

import org.Organizel.Organizel.domain.Tasks;
import org.Organizel.Organizel.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TasksRepository extends CrudRepository<Tasks,Integer> {

    List<Tasks> findByTaskContainingAndAuthor(String word,User user);
    List<Tasks> findAllByAuthor(User author);
}
