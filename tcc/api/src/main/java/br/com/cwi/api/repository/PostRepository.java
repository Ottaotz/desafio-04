package br.com.cwi.api.repository;

import br.com.cwi.api.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByAllIgnoringCaseLegendaContains(String legenda, Pageable pageable);
}
