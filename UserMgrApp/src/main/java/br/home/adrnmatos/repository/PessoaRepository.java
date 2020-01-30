package br.home.adrnmatos.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.home.adrnmatos.model.PessoaModel;
import br.home.adrnmatos.model.UsuarioModel;
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
	
	public List<PessoaModel> getPessoas() {
		
		List<PessoaModel> pessoasModel = new ArrayList<PessoaModel>();
		
		entityManager = Uteis.jpaEntityManager();
		
		Query query = entityManager.createNamedQuery("PessoaEntity.findAll");
		
		@SuppressWarnings("unchecked")
		Collection<PessoaEntity> pessoasEntity = (Collection<PessoaEntity>)query.getResultList();
		
		PessoaModel pessoaModel = null;
		
		for(PessoaEntity pessoaEntity : pessoasEntity) {
			
			pessoaModel = new PessoaModel();
			pessoaModel.setCodigo(pessoaEntity.getCodigo());
			pessoaModel.setDataCadastro(pessoaEntity.getDataCadastro());
			pessoaModel.setEmail(pessoaEntity.getEmail());
			pessoaModel.setEndereco(pessoaEntity.getEndereco());
			pessoaModel.setNome(pessoaEntity.getNome());
			
			if(pessoaEntity.getOrigemCadastro().equals("X"))
				pessoaModel.setOrigemCadastro("XML");
			else
				pessoaModel.setOrigemCadastro("INPUT");
			
			if(pessoaEntity.getSexo().equals("M"))
				pessoaModel.setSexo("Masculino");
			else
				pessoaModel.setSexo("Feminino");
			
			UsuarioEntity usuarioEntity = pessoaEntity.getUsuarioEntity();
			UsuarioModel usuarioModel = new UsuarioModel();
			usuarioModel.setUsuario(usuarioEntity.getCodigo());
			pessoaModel.setUsuarioModel(usuarioModel);
			
			pessoasModel.add(pessoaModel);
		}
		
		return pessoasModel;
	}
	
	private PessoaEntity getPessoa(int codigo) {
		
		entityManager = Uteis.jpaEntityManager();
		
		return entityManager.find(PessoaEntity.class, codigo);
	}
	
	public void alterarRegistro(PessoaModel pessoaModel) {
		
		entityManager = Uteis.jpaEntityManager();
		
		PessoaEntity pessoaEntity = getPessoa(pessoaModel.getCodigo());
		
		pessoaEntity.setEmail(pessoaModel.getEmail());
		pessoaEntity.setEndereco(pessoaModel.getEndereco());
		pessoaEntity.setNome(pessoaModel.getNome());
		pessoaEntity.setSexo(pessoaModel.getSexo());
		
		entityManager.refresh(pessoaEntity);
		
	}

	public void excluirRegistro(int codigo) {

		entityManager = Uteis.jpaEntityManager();
		
		PessoaEntity pessoaEntity = getPessoa(codigo);
		
		entityManager.remove(pessoaEntity);
	}
	
	
}
