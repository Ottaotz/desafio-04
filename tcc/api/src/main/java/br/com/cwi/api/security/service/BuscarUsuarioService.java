package br.com.cwi.api.security.service;

import br.com.cwi.api.security.controller.response.UsuarioCompletoResponse;
import br.com.cwi.api.security.controller.response.UsuarioResponse;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static br.com.cwi.api.security.mapper.UsuarioCompletoMapper.toResponseCompleto;
import static br.com.cwi.api.security.mapper.UsuarioMapper.toResponse;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class BuscarUsuarioService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioResponse buscar() {
        Usuario usuarioAutenticado = usuarioAutenticadoService.get();
        return toResponse(usuarioAutenticado);
    }

    public UsuarioCompletoResponse buscarDetalhe() {
        Usuario usuarioAutenticado = usuarioAutenticadoService.get();
        return toResponseCompleto(usuarioAutenticado);
    }

    public Usuario buscarUsuario(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuário não encontrado"));
    }
}
