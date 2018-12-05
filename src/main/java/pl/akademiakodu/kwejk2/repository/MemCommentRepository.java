package pl.akademiakodu.kwejk2.repository;

import org.springframework.data.repository.CrudRepository;
import pl.akademiakodu.kwejk2.model.MemComment;


public interface MemCommentRepository extends CrudRepository<MemComment,Long> {
}
