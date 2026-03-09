package lucas.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lucas.dev.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
