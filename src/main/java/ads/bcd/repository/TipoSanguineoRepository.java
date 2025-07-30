package ads.bcd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ads.bcd.model.TipoSanguineo;

public interface TipoSanguineoRepository extends JpaRepository<TipoSanguineo, Integer> {
    
}
