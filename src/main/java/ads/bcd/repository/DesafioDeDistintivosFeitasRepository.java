package ads.bcd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ads.bcd.model.DesafioDeDistintivosFeitas;
import ads.bcd.model.DesafioDeDistintivosFeitasID;

public interface DesafioDeDistintivosFeitasRepository extends JpaRepository<DesafioDeDistintivosFeitas, DesafioDeDistintivosFeitasID> {
}
