package midianet.mykudos.infra.http.rest;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

import midianet.mykudos.application.usecase.event.ObterEventUsecase;
import midianet.mykudos.application.usecase.event.ListarEventUsecase;
import midianet.mykudos.application.usecase.event.CriarEventUsecase;
import midianet.mykudos.application.usecase.event.AlterarEventUsecase;
import midianet.mykudos.application.usecase.event.DeletarEventUsecase;

@Validated
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/events")
public class EventResource {

    private final CriarEventUsecase criarEvent;
    private final AlterarEventUsecase alterarEvent;
    private final DeletarEventUsecase deletarEvent;
    private final ListarEventUsecase listarEvent;
    private final ObterEventUsecase obterEvent;

    @GetMapping("/{id}")
    @ApiOperation(value = "Obter Event", notes = "Obter Event  por sua chave.")
    public ObterEventUsecase.Out get(@PathVariable final Long id) {
        return obterEvent.execute(id);
    }

    @GetMapping
    @ApiOperation(value = "Listar Event", notes = "Listar Event  por filtros com paginação e ordenação.")
    public Page<ListarEventUsecase.Out> find(@RequestParam(required = false) final String texto,
                                             @PageableDefault final Pageable pageable) {
        final var example = ListarEventUsecase.In.builder();
        Optional.ofNullable(texto).ifPresent(example::name);
        return listarEvent.execute(example.build(),pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Criar Event.", notes = "Cria um novo Event.")
    public void post(@Valid @RequestBody final CriarEventUsecase.In event,
                     final HttpServletResponse response) {
        response.setHeader(HttpHeaders.LOCATION, ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(criarEvent
                        .execute(event))
                .toUri().toString()
        );
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Alterar Event.", notes = "Atualiza os dados Event.")
    public void put(@PathVariable final Long id,
                    @Valid @RequestBody final AlterarEventUsecase.In event) {
        alterarEvent.execute(id, event);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Excluir Event.", notes = "Exclui Event.")
    public void delete(@PathVariable final Long id) {
        deletarEvent.execute(id);
    }

}
