package br.comp.zeus.repository;

import br.comp.zeus.model.OrcamentoProjeto;
import br.comp.zeus.model.StatusOrcamento;
import br.comp.zeus.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrcamentoProjetoRepository extends JpaRepository<OrcamentoProjeto, Long> {

    List<OrcamentoProjeto> findByResponsavel(Usuario responsavel);

    List<OrcamentoProjeto> findByStatus(StatusOrcamento status);

}
