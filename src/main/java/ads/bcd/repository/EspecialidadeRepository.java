package ads.bcd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ads.bcd.model.Especialidade;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Integer> {
    
}
