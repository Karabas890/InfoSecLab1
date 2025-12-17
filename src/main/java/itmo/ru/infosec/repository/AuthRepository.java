package itmo.ru.infosec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import itmo.ru.infosec.entity.AuthModel;

@Repository
public interface AuthRepository extends JpaRepository<AuthModel, String> {
}
