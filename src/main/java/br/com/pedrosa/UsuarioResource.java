package br.com.pedrosa;

import br.com.pedrosa.proto.service.MutinyUsuarioServiceGrpc;
import br.com.pedrosa.proto.service.UsuarioRequest;
import br.com.pedrosa.proto.service.UsuarioResponse;
import br.com.pedrosa.proto.service.UsuarioServiceGrpc.UsuarioServiceBlockingStub;
import io.quarkus.grpc.runtime.annotations.GrpcService;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/usuario")
public class UsuarioResource {

    @Inject
    @GrpcService("usuarios")
    UsuarioServiceBlockingStub usuarioService;

    @Inject
    @GrpcService("usuarios")
    MutinyUsuarioServiceGrpc.MutinyUsuarioServiceStub mutinyUsuarioService;

    @GET
    @Path("blocking/grpc/name")
    public String getUserName() {
        return usuarioService.adicionarUsuario(UsuarioRequest.newBuilder().setIdade(39).setNome("Fabio Pedrosa").build()).getNome();
    }

    @GET
    @Path("multiny/grpc/name")
    public Uni<String> getUserNameReactive() {
       return mutinyUsuarioService.adicionarUsuario(UsuarioRequest.newBuilder().setIdade(39).setNome("Fabio Pedrosa").build())
               .onItem().apply(UsuarioResponse::getNome);
    }

}
