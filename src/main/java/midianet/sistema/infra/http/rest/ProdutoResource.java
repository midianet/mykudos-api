package midianet.sistema.infra.http.rest;

import lombok.RequiredArgsConstructor;
import midianet.sistema.application.model.Produto;
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
import java.math.BigDecimal;
import java.util.Optional;

import midianet.sistema.application.usecase.produto.ObterProdutoUsecase;
import midianet.sistema.application.usecase.produto.ListarProdutoUsecase;
import midianet.sistema.application.usecase.produto.CriarProdutoUsecase;
import midianet.sistema.application.usecase.produto.AlterarProdutoUsecase;
import midianet.sistema.application.usecase.produto.DeletarProdutoUsecase;

@Validated
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/produtos")
public class ProdutoResource {

    private final CriarProdutoUsecase criarProduto;
    private final AlterarProdutoUsecase alterarProduto;
    private final DeletarProdutoUsecase deletarProduto;
    private final ListarProdutoUsecase listarProduto;
    private final ObterProdutoUsecase obterProduto;

    @GetMapping("/{id}")
    public Produto get(@PathVariable final Long id) {
        return obterProduto.execute(id);
    }

    @GetMapping
    public Page<Produto> find(@RequestParam(required = false) final String descricao,
                              @RequestParam(required = false) final Produto.Grupo grupo,
                              @RequestParam(required = false) final Produto.Classe classe,
                              @RequestParam(required = false) final BigDecimal valor,
                              @RequestParam(required = false) final BigDecimal peso,
                              @PageableDefault final Pageable pageable) {
        final var example = ListarProdutoUsecase.In.builder();
        Optional.ofNullable(descricao).ifPresent(example::descricao);
        Optional.ofNullable(grupo).ifPresent(example::grupo);
        Optional.ofNullable(classe).ifPresent(example::classe);
        Optional.ofNullable(valor).ifPresent(example::valor);
        Optional.ofNullable(peso).ifPresent(example::peso);
        return listarProduto.execute(example.build(),pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@Valid @RequestBody final CriarProdutoUsecase.In produto,
                     final HttpServletResponse response) {
        response.setHeader(HttpHeaders.LOCATION, ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(criarProduto
                        .execute(produto))
                .toUri().toString()
        );
    }

    @PutMapping("/{id}")
    public void put(@PathVariable final Long id,
                    @Valid @RequestBody final AlterarProdutoUsecase.In produto) {
        alterarProduto.execute(id, produto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final Long id) {
        deletarProduto.execute(id);
    }

}