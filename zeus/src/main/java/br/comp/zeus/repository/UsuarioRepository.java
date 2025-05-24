package br.comp.zeus.repository;

import br.comp.zeus.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

    Usuario findById(long id);

    @Query(value="select * from zeus.usuario where email = :email and senha =:senha", nativeQuery = true)
    public Usuario login(String email, String senha);

    Usuario findByEmail(String email);
}
