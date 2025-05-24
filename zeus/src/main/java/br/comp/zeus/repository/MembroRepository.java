package br.comp.zeus.repository;

import br.comp.zeus.model.Membro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembroRepository extends JpaRepository<Membro, Long> {
}
