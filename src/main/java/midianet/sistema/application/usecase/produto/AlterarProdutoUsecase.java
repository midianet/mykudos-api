package midianet.sistema.application.usecase.produto;

import lombok.RequiredArgsConstructor;
import midianet.sistema.application.model.Produto;
import org.springframework.lang.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;

import midianet.sistema.infra.database.jpa.repository.ProdutoRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AlterarProdutoUsecase {
    private final ProdutoRepository repository;
    private final ObterProdutoUsecase obterProduto;

    @Transactional
    public void execute(@NonNull final Long id, @NonNull final In produto) {
        final var persistent = obterProduto.execute(id);
        BeanUtils.copyProperties(produto, persistent);
        repository.save(persistent);
    }

    public record In(
            @NotBlank
            @Size(min = 5, max = 40)
            String descricao,

            @NotNull
            Produto.Grupo grupo,

            @NotNull
            Produto.Classe classe,

            @NotNull
            BigDecimal valor,

            @NotNull
            BigDecimal peso){}

}
