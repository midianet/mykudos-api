package midianet.sistema.infra.database.jpa.repository;

import midianet.sistema.application.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
