package midianet.mykudos.infra.http.rest;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import midianet.mykudos.application.usecase.user.ObterUserUsecase;
import midianet.mykudos.application.usecase.user.SalvarUserUsecase;
import midianet.mykudos.application.usecase.user.DeletarUserUsecase;

@Validated
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserResource {

    private final SalvarUserUsecase criarUser;
    private final DeletarUserUsecase deletarUser;
    private final ObterUserUsecase obterUser;

    @GetMapping("/{id}")
    @ApiOperation(value = "Obter User", notes = "Obter User  por sua chave.")
    public ObterUserUsecase.Out get(@PathVariable final Long id) {
        return obterUser.execute(id);
    }

//    @GetMapping
//    @ApiOperation(value = "Listar User", notes = "Listar User  por filtros com paginação e ordenação.")
//    public Page<ListarUserUsecase.Out> find(@RequestParam(required = false) final String texto,
//                                               @RequestParam(required = false) final Integer valor,
//                                               @PageableDefault final Pageable pageable) {
//        final var example = ListarUserUsecase.In.builder();
//        Optional.ofNullable(texto).ifPresent(example::texto);
//        Optional.ofNullable(valor).ifPresent(example::valor);
//        return listarUser.execute(example.build(),pageable);
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Criar User.", notes = "Cria um novo User.")
    public void post(@Valid @RequestBody final SalvarUserUsecase.In User,
                     final HttpServletResponse response) {
        response.setHeader(HttpHeaders.LOCATION, ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(criarUser
                        .execute(User))
                .toUri().toString()
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Excluir User.", notes = "Exclui User.")
    public void delete(@PathVariable final Long id) {
        deletarUser.execute(id);
    }

}
