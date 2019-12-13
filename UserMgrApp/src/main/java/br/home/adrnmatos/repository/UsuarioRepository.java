package br.home.adrnmatos.repository;

import java.io.Serializable;

import javax.persistence.Query;

import br.home.adrnmatos.model.UsuarioModel;
import br.home.adrnmatos.repository.entity.UsuarioEntity;
import br.home.adrnmatos.uteis.Uteis;

public class UsuarioRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	public UsuarioEntity validaUsuario(UsuarioModel usuarioModel) {
		
		try {
			
			/* GET NAMMED QUERY */ 
			Query query = Uteis.jpaEntityManager().createNamedQuery("UsuarioEntity.findUser");
			
			/* SET PARAMETERS */
			query.setParameter("usuario", usuarioModel.getUsuario());
			query.setParameter("senha", usuarioModel.getSenha());
			
			/* VALIDATE ON DATABASE */
			return (UsuarioEntity)query.getSingleResult();

		} catch(Exception e) {			
			return null;
		}	
	
	}
}
