package br.com.cwi.api.security.service;

import br.com.cwi.api.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@Service
public class ValidaEmailUnicoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validar(String email) {
        if (usuarioRepository.existsByEmail(email)) {
            throw new ResponseStatusException(NOT_ACCEPTABLE, "Cadastro inválido");
        }
    }
}
