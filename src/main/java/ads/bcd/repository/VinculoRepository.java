package ads.bcd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ads.bcd.model.Vinculo;
import ads.bcd.model.VinculoID;

public interface VinculoRepository extends JpaRepository<Vinculo, VinculoID> {
    
}
