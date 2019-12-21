package br.home.adrnmatos.pessoa.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.home.adrnmatos.model.PessoaModel;
import br.home.adrnmatos.repository.PessoaRepository;
import br.home.adrnmatos.usuario.controller.UsuarioController;
import br.home.adrnmatos.uteis.Uteis;

@Named(value = "cadastroPessoaController")
@RequestScoped
public class CadastroPessoaController {

	@Inject
	private PessoaModel pessoaModel;
	
	@Inject
	private UsuarioController usuarioController;
	
	@Inject
	private PessoaRepository pessoaRepository;

	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}

	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}
	
	public void salvarNovaPessoa() {
		
		pessoaModel.setUsuarioModel(this.usuarioController.getUsuarioSession());
		
		// indicate person is created on input screen
		pessoaModel.setOrigemCadastro("I");
		
		pessoaRepository.salvarNovoRegistro(pessoaModel);
		
		Uteis.mensagemInfo("Registro criado com sucesso");
	}
}
