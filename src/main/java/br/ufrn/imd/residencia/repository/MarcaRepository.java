

package br.ufrn.imd.residencia.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufrn.imd.residencia.model.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {
	
}