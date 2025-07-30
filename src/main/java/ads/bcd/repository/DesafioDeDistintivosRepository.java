package ads.bcd.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;

import ads.bcd.model.DesafioDeDistintivos;
import ads.bcd.model.DesafioDeDistintivosFeitasID;

public interface DesafioDeDistintivosRepository extends JpaRepository<DesafioDeDistintivos, DesafioDeDistintivosFeitasID> {
}

