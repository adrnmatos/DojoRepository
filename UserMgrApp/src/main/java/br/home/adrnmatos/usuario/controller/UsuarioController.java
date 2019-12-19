package br.home.adrnmatos.usuario.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.home.adrnmatos.model.UsuarioModel;
import br.home.adrnmatos.repository.UsuarioRepository;
import br.home.adrnmatos.repository.entity.UsuarioEntity;
import br.home.adrnmatos.uteis.Uteis;

@Named(value = "usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioModel usuarioModel;
	
	@Inject
	private UsuarioRepository usuarioRepository;
	
	@Inject
	private UsuarioEntity usuarioEntity;

	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}
	
	public UsuarioModel getUsuarioSession() {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return (UsuarioModel)facesContext.getExternalContext().getSessionMap().get("usuarioAutenticado");
	}
	
	public String logout() {
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();		
		return "/index.xhtml?faces-redirect=true";
	}
	
	public String efetuarLogin() {
		
		if(StringUtils.isEmpty(usuarioModel.getUsuario()) || StringUtils.isBlank(usuarioModel.getUsuario())) {
			
			Uteis.mensagem("Favor informar o login!");
			return null;
		}
		
		else if(StringUtils.isEmpty(usuarioModel.getSenha()) || StringUtils.isBlank(usuarioModel.getSenha())) {
			
			Uteis.mensagem("Favor informar a senha!");
			return null;
		}
		
		else {
			
			System.out.println("*****************************" + usuarioModel.getUsuario() + "************" + usuarioModel.getSenha());
			usuarioEntity = usuarioRepository.validaUsuario(usuarioModel);
			
			if(usuarioEntity != null) {
				
				usuarioModel.setSenha(null);
				usuarioModel.setCodigo(usuarioEntity.getCodigo());
				
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.getExternalContext().getSessionMap().put("usuarioAutenticado", usuarioModel);
				
				return "sistema/home?faces-redirect=true";
			}
			
			else {
				
				Uteis.mensagem("Não foi possivel efetuar login com esse usuario e senha!");
				return null;
			}
		}
	}
	
}
