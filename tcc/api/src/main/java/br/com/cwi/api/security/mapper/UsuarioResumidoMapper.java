package br.com.cwi.api.security.mapper;

import br.com.cwi.api.security.controller.response.UsuarioResumidoResponse;
import br.com.cwi.api.security.domain.Usuario;

public class UsuarioResumidoMapper {

    public static UsuarioResumidoResponse toResponse(Usuario entity) {
        return UsuarioResumidoResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .apelido(entity.getApelido())
                .imagemPerfil(entity.getImagemPerfil())
                .build();
    }
}
