package ifrn.pi.estoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.pi.estoque.models.Equipamento;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {

}
