package br.com.cwi.api.security.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class UsuarioRequest {

    @NotBlank(message = "Nome obrigatório")
    private String nome;

    @NotBlank(message = "Email obrigatório") @Email(message = "Email deve ser válido")
    private String email;

    @NotBlank(message = "Senha obrigatória")
    @Size(min = 5, message = "Senha deve conter no mínimo 5 caracteres")
    private String senha;

    private String apelido;

    @NotNull(message = "Data de Nascimento obrigatória")
    private LocalDate dataNascimento;

    @Size(max = 512, message = "Imagem de Perfil deve conter no máximo 512 caracteres")
    private String imagemPerfil;
}
