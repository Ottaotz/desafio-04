package br.com.cwi.api.security.service;

import br.com.cwi.api.security.controller.request.UsuarioRequest;
import br.com.cwi.api.security.controller.response.UsuarioResponse;
import br.com.cwi.api.security.domain.Permissao;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.repository.UsuarioRepository;
import br.com.cwi.api.security.validator.ValidaDataNascimentoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static br.com.cwi.api.security.mapper.UsuarioMapper.toEntity;
import static br.com.cwi.api.security.mapper.UsuarioMapper.toResponse;

@Service
public class IncluirUsuarioService {

    @Autowired
    private ValidaDataNascimentoValidator validaDataNascimentoValidator;

    @Autowired
    private ValidaEmailUnicoService validaEmailUnicoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioResponse incluir(UsuarioRequest request) {

        validaDataNascimentoValidator.validar(request.getDataNascimento());
        validaEmailUnicoService.validar(request.getEmail());

        Usuario usuario = toEntity(request);
        usuario.setSenha(passwordEncoder.encode(request.getSenha()));
        usuario.setAtivo(true);
        usuario.adicionarPermissao(Permissao.builder().nome("USUARIO").build());

        usuarioRepository.save(usuario);

        return toResponse(usuario);
    }
}
