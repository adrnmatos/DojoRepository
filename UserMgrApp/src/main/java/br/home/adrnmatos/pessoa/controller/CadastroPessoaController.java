package br.home.adrnmatos.pessoa.controller;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.primefaces.model.UploadedFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
	
	private UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

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
	
	public void uploadRegistros() {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {

			if(this.file.getFileName().equals("")){
				Uteis.mensagemAtencao("Nenhum arquivo selecionado!");
				return;
			}

			DocumentBuilder builder = factory.newDocumentBuilder();

			Document doc = builder.parse(this.file.getInputstream());

			Element element = doc.getDocumentElement();

			NodeList nodes = element.getChildNodes();

			for (int i = 0; i < nodes.getLength(); i++) {

				Node node  = nodes.item(i);

				if(node.getNodeType() == Node.ELEMENT_NODE){

					Element elementPessoa = (Element) node;

					// FETCHING VALUES
					String nome     = elementPessoa.getElementsByTagName("nome").item(0).getChildNodes().item(0).getNodeValue();
					String sexo     = elementPessoa.getElementsByTagName("sexo").item(0).getChildNodes().item(0).getNodeValue();
					String email    = elementPessoa.getElementsByTagName("email").item(0).getChildNodes().item(0).getNodeValue();
					String endereco = elementPessoa.getElementsByTagName("endereco").item(0).getChildNodes().item(0).getNodeValue();

					PessoaModel newPessoaModel = new PessoaModel();

					newPessoaModel.setUsuarioModel(this.usuarioController.getUsuarioSession());
					newPessoaModel.setEmail(email);
					newPessoaModel.setEndereco(endereco);
					newPessoaModel.setNome(nome);
					newPessoaModel.setOrigemCadastro("X");
					newPessoaModel.setSexo(sexo);

					// SAVING ONE REGISTRY
					pessoaRepository.salvarNovoRegistro(newPessoaModel);
				}
			}

			Uteis.mensagemInfo("Registros cadastrados com sucesso!");

		} catch (ParserConfigurationException e) {

			e.printStackTrace();

		} catch (SAXException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
}
