package br.com.cwi.api.security.service;

import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class AmizadeService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    public void solicitar(Long amigoId) {

        Usuario usuario = usuarioAutenticadoService.get();
        Optional<Usuario> amigo = usuario.getAmigos().stream()
                .filter(amigoUsuario -> Objects.equals(amigoUsuario.getId(), amigoId)).findFirst();

        if (amigo.isPresent()) {
            throw new ResponseStatusException(BAD_REQUEST, "Você é amigo desse perfil");
        }

        usuario.solicitarAmizade(buscarUsuarioService.buscarUsuario(amigoId));

        usuarioRepository.save(usuario);
    }

    public void adicionar(Long amigoId) {

        Usuario usuario = usuarioAutenticadoService.get();
        Optional<Usuario> amigo = usuario.getSolicitacoes().stream()
                .filter(amigoUsuario -> Objects.equals(amigoUsuario.getId(), amigoId)).findFirst();

        if (amigo.isEmpty()) {
            throw new ResponseStatusException(BAD_REQUEST, "Não há solicitação");
        }

        usuario.adicionarAmigo(amigo.get());

        usuarioRepository.save(usuario);
    }

    public void remover(Long amigoId) {

        Usuario usuario = usuarioAutenticadoService.get();
        Optional<Usuario> amigo = usuario.getAmigos().stream()
                .filter(amigoUsuario -> Objects.equals(amigoUsuario.getId(), amigoId)).findFirst();

        if (amigo.isEmpty()) {
            throw new ResponseStatusException(BAD_REQUEST, "Você não é amigo desse perfil");
        }

        usuario.removerAmizade(amigo.get());

        usuarioRepository.save(usuario);
    }

    public void rejeitar(Long amigoId) {

        Usuario usuario = usuarioAutenticadoService.get();
        Optional<Usuario> amigo = usuario.getSolicitacoes().stream()
                .filter(amigoUsuario -> Objects.equals(amigoUsuario.getId(), amigoId)).findFirst();

        if (amigo.isEmpty()) {
            throw new ResponseStatusException(BAD_REQUEST, "Não há solicitação");
        }

        usuario.rejeitarAmigo(amigo.get());

        usuarioRepository.save(usuario);
    }
}
