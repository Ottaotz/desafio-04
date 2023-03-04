package br.com.cwi.api.security.repository;

import br.com.cwi.api.security.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);

    Page<Usuario> findByAllIgnoringCaseNomeContainsAndNomeIsNotOrEmailContainsAndEmailNotLike(String nome, String email, String userNome,String userEmail, Pageable pageable);

    boolean existsByEmail(String email);
}
