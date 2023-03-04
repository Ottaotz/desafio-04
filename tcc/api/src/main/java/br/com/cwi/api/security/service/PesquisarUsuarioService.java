package br.com.cwi.api.security.service;

import br.com.cwi.api.security.controller.response.UsuarioResumidoResponse;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.mapper.UsuarioResumidoMapper;
import br.com.cwi.api.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PesquisarUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public Page<UsuarioResumidoResponse> pesquisar(String text, Pageable pageable) {

        Usuario usuario = usuarioAutenticadoService.get();

        return usuarioRepository
                .findByAllIgnoringCaseNomeContainsAndNomeIsNotOrEmailContainsAndEmailNotLike(
                        text, text, usuario.getNome(), usuario.getEmail(), pageable)
                .map(UsuarioResumidoMapper::toResponse);
    }
}
