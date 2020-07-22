package br.com.pedrosa;

import br.com.pedrosa.proto.service.MutinyUsuarioServiceGrpc.UsuarioServiceImplBase;
import br.com.pedrosa.proto.service.UsuarioRequest;
import br.com.pedrosa.proto.service.UsuarioResponse;
import io.smallrye.mutiny.Uni;

import javax.inject.Singleton;

@Singleton
public class UsuarioService extends UsuarioServiceImplBase {

    @Override
    public Uni<UsuarioResponse> adicionarUsuario(UsuarioRequest request) {
        Integer id = 123;
        UsuarioResponse usuarioResponse = UsuarioResponse.newBuilder()
                .setId(id)
                .setNome(request.getNome()+" GRPC")
                .setIdade(request.getIdade())
                .build();
        return Uni.createFrom().item(usuarioResponse);
    }
}