package br.home.adrnmatos.repository;

import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.home.adrnmatos.model.PessoaModel;
import br.home.adrnmatos.repository.entity.PessoaEntity;
import br.home.adrnmatos.repository.entity.UsuarioEntity;
import br.home.adrnmatos.uteis.Uteis;

public class PessoaRepository {
		
	@Inject
	PessoaEntity pessoaEntity;
	
	private EntityManager entityManager;
	
	public void salvarNovoRegistro(PessoaModel pessoaModel) {
		
		entityManager = Uteis.jpaEntityManager();
		
		pessoaEntity = new PessoaEntity();
		pessoaEntity.setDataCadastro(LocalDateTime.now());
		pessoaEntity.setEmail(pessoaModel.getEmail());
		pessoaEntity.setEndereco(pessoaModel.getEndereco());
		pessoaEntity.setNome(pessoaModel.getNome());
		pessoaEntity.setOrigemCadastro(pessoaModel.getOrigemCadastro());
		pessoaEntity.setSexo(pessoaModel.getSexo());
		
		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class, pessoaModel.getUsuarioModel().getCodigo());
		
		pessoaEntity.setUsuarioEntity(usuarioEntity);
		
		entityManager.persist(pessoaEntity);
	}
	
}
