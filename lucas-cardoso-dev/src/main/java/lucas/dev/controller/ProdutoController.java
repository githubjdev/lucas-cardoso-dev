package lucas.dev.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lucas.dev.exception.MsgApiException;
import lucas.dev.model.Produto;
import lucas.dev.service.ProdutoService;

/*Controller é feito pra lançar exeção, e notificar o usuário*/
/*Controler: recebe, valida, processa e retornar*/

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@PostMapping
	public ResponseEntity<Produto> salvar(@RequestBody Produto produto) {
		return ResponseEntity.ok(produtoService.salvar(produto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarPorId(@PathVariable(name = "id") Long id) {

		Optional<Produto> produto = produtoService.buscarPorId(id);
		
		if (!produto.isPresent()) { /*Não achou o objeto*/
			throw new MsgApiException("Usuário não encontrado com id: " + id, HttpStatus.NOT_FOUND);
		}
		

		return new ResponseEntity<Produto>(produto.get(), HttpStatus.OK);
	}

}
