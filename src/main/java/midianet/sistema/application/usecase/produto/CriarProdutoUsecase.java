package midianet.sistema.application.usecase.produto;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;

import midianet.sistema.application.model.Produto;
import midianet.sistema.infra.database.jpa.repository.ProdutoRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CriarProdutoUsecase {
    private final ProdutoRepository repository;

    @Transactional
    public Long execute(@NonNull final In produto) {
        final var persistent = new Produto();
        BeanUtils.copyProperties(produto,persistent);
        return repository.save(persistent).getId();
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
