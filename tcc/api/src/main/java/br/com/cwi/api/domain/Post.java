package br.com.cwi.api.domain;

import br.com.cwi.api.enuns.Visualizacao;
import br.com.cwi.api.security.domain.Usuario;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id") @ToString(of = "id")
public class Post {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String imagem;

    private String legenda;

    private Long totalCurtidas;

    private LocalDateTime dataPostagem;

    @Enumerated(STRING)
    private Visualizacao visualizacao;

    @OneToMany(mappedBy = "post")
    private List<Comentario> comentarios = new ArrayList<>();

    @ManyToMany(mappedBy = "curtidas")
    private List<Usuario> curtidas = new ArrayList<>();

    public void adicionarComentario(Comentario comentario) {
        this.comentarios.add(comentario);
        comentario.setPost(this);
    }
}

