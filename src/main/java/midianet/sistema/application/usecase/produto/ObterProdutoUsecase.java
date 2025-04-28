package midianet.sistema.application.usecase.produto;

import lombok.*;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;

import midianet.sistema.application.model.Produto;
import midianet.sistema.infra.database.jpa.repository.ProdutoRepository;

@Service
@RequiredArgsConstructor
public class ObterProdutoUsecase {
    private final ProdutoRepository repository;

    public Produto execute(@NonNull final Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
    }

}