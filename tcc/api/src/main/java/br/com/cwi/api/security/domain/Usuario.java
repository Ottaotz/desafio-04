package br.com.cwi.api.security.domain;

import br.com.cwi.api.domain.Comentario;
import br.com.cwi.api.domain.Post;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String nome;

    @Email
    private String email;

    private String senha;

    private String apelido;

    private LocalDate dataNascimento;

    private String imagemPerfil;

    private boolean ativo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Permissao> permissoes = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "usuario_curtida",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id"))
    private List<Post> curtidas = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "amigo",
               joinColumns = @JoinColumn(name = "usuario_id"),
                inverseJoinColumns = @JoinColumn(name = "amigo_id"))
    private List<Usuario> amigos = new ArrayList<>();

    @OneToMany
    @JoinTable(name = "solicitacao",
              joinColumns = @JoinColumn(name = "usuario_id"),
                inverseJoinColumns = @JoinColumn(name = "amigo_id"))
    private List<Usuario> solicitacoes = new ArrayList<>();

    public void adicionarPermissao(Permissao permissao) {
        this.permissoes.add(permissao);
        permissao.setUsuario(this);
    }

    public void adicionarPost(Post post) {
        this.posts.add(post);
        post.setUsuario(this);
    }

    public void adicionarCurtida(Post post) {
        if (this.getCurtidas().contains(post)) {
            throw new ResponseStatusException(BAD_REQUEST, "Post já curtido");
        }

        this.getCurtidas().add(post);
        post.getCurtidas().add(this);
        setTotalCurtidas(post);
    }

    public void removerCurtida(Post post) {
        if (!this.getCurtidas().contains(post)) {
            throw new ResponseStatusException(BAD_REQUEST, "Post não curtido");
        }

        this.getCurtidas().remove(post);
        post.getCurtidas().remove(this);
        setTotalCurtidas(post);
    }

    private void setTotalCurtidas(Post post) {
        post.setTotalCurtidas((long) post.getCurtidas().size());
    }

    public void adicionarComentario(Comentario comentario) {
        this.getComentarios().add(comentario);
        comentario.setUsuario(this);
    }

    public void solicitarAmizade(Usuario usuario) {
        if (Objects.equals(this.getId(), usuario.getId())) {
            throw new ResponseStatusException(BAD_REQUEST, "Usuário não pode ser amigo dele mesmo");
        }
        usuario.getSolicitacoes().add(this);
    }

    public void adicionarAmigo(Usuario usuario) {
        if (this.getSolicitacoes().contains(usuario)) {
           removerSolicitacao(usuario);
            this.getAmigos().add(usuario);
            usuario.getAmigos().add(this);
        }
    }

    public void rejeitarAmigo(Usuario usuario) {
        if (this.getSolicitacoes().contains(usuario)) {
           removerSolicitacao(usuario);
        }
    }

    private void removerSolicitacao(Usuario usuario) {
        this.getSolicitacoes().remove(usuario);
    }

    public void removerAmizade(Usuario usuario) {
        this.getAmigos().remove(usuario);
        usuario.getAmigos().remove(this);
    }
}