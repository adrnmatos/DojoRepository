package br.home.adrnmatos.pessoa.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.home.adrnmatos.model.PessoaModel;
import br.home.adrnmatos.repository.PessoaRepository;

@Named(value = "consultarPessoaController")
@ViewScoped
public class ConsultarPessoaController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject transient
	private PessoaModel pessoaModel;
	
	@Produces
	private List<PessoaModel> pessoas;
	
	@Inject transient
	private PessoaRepository pessoaRepository;
	
	public List<PessoaModel> getPessoas() {
		return pessoas;
	}
	
	public void setPessoas(List<PessoaModel> pessoas) {
		this.pessoas = pessoas;
	}
	
	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}
	
	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}
	
	@PostConstruct
	public void init() {
		this.pessoas = pessoaRepository.getPessoas();
	}
	
	public void editar(PessoaModel pessoaModel) {
		
		pessoaModel.setSexo(pessoaModel.getSexo().substring(0, 1));
		this.pessoaModel = pessoaModel;
	}
	
	public void alterarRegistro() {
		
		this.pessoaRepository.alterarRegistro(this.pessoaModel);
		
		this.init();
	}
	
}
