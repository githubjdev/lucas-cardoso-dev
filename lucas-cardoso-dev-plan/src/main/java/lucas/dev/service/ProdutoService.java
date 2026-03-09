package lucas.dev.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lucas.dev.model.Produto;
import lucas.dev.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Optional<Produto> buscarPorId(Long id) {
		return produtoRepository.findById(id);
	}

	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}

}
