package midianet.sistema.application.usecase.produto;

import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import midianet.sistema.application.model.Produto;
import midianet.sistema.infra.database.jpa.repository.ProdutoRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ListarProdutoUsecase {
    private final ProdutoRepository repository;

    public Page<Produto> execute(@NonNull final In produto,
                                 @NonNull final Pageable page) {
        final var matcher = ExampleMatcher.matchingAll()
            .withMatcher("descricao", ExampleMatcher.GenericPropertyMatchers.startsWith());
        return repository.findAll(Example.of(produto.toEntity(), matcher), page);
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class In {
        private String descricao;
        private Produto.Grupo grupo;
        private Produto.Classe classe;
        private BigDecimal valor;
        private BigDecimal peso;

        public Produto toEntity(){
            final var example = new Produto();
            BeanUtils.copyProperties(this,example);
            return example;
        }
    }

}