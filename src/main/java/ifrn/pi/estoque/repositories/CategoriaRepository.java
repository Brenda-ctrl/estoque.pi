package ifrn.pi.estoque.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.pi.estoque.models.Categoria;
import ifrn.pi.estoque.models.Equipamento;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

		List<Categoria> findByEquipamento(Equipamento equipamento);

}
