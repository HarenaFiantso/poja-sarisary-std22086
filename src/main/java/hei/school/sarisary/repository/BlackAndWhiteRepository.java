package hei.school.sarisary.repository;

import hei.school.sarisary.repository.model.BlackAndWhite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackAndWhiteRepository extends JpaRepository<BlackAndWhite, String> {}
