package br.comp.zeus.converter;

import br.comp.zeus.model.Usuario;
import br.comp.zeus.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToUsuarioConverter implements Converter<String, Usuario> {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    public StringToUsuarioConverter(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario convert(String id) {

        if (id == null || id.isEmpty()) {
            return null;
        }

        return usuarioRepository.findById(id).orElse(null);
    }
}
