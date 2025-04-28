package midianet.sistema.application.usecase.produto;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import midianet.sistema.infra.database.jpa.repository.ProdutoRepository;

@Service
@RequiredArgsConstructor
public class DeletarProdutoUsecase {
    private final ProdutoRepository repository;
    private final ObterProdutoUsecase obterProduto;

    @Transactional
    public void execute(@NonNull final Long id) {
        repository.delete(obterProduto.execute(id));
    }

}
