package br.home.adrnmatos.pessoa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.home.adrnmatos.model.PessoaModel;
import br.home.adrnmatos.repository.PessoaRepository;

@Named(value = "exportarRegistrosXmlController")
@RequestScoped
public class ExportarRegistrosXmlController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject transient
	private PessoaRepository pessoaRepository;

	private StreamedContent arquivoDownload;

	public StreamedContent getArquivoDownload() {
		
		this.DownloadArquivoXmlPessoa();
		
		return arquivoDownload;
	}

	private void DownloadArquivoXmlPessoa() {

		File arquivoXml = this.GerarXmlPessoas();
		
		InputStream inputStream;
		
		try {
			
			inputStream = new FileInputStream(arquivoXml.getPath());
			arquivoDownload = new DefaultStreamedContent(inputStream, "application/xml", arquivoXml.getName());
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}	
	}

	private File GerarXmlPessoas() {
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		
		List<PessoaModel> pessoasModel = pessoaRepository.getPessoas();
		
		Element elementPessoas = new Element("Pessoas");
		
		Document documentPessoas = new Document(elementPessoas);
		
		pessoasModel.forEach(pessoa -> {
			
			Element elementPessoa = new Element("Pessoa");
			elementPessoa.addContent(new Element("codigo").setText(pessoa.getCodigo().toString()));
			elementPessoa.addContent(new Element("nome").setText(pessoa.getNome()));
			elementPessoa.addContent(new Element("sexo").setText(pessoa.getSexo()));
			
			String dataCadastroFormatada = pessoa.getDataCadastro().format(dateTimeFormatter);
			elementPessoa.addContent(new Element("dataCadastro").setText(dataCadastroFormatada));
			
			elementPessoa.addContent(new Element("email").setText(pessoa.getEmail()));
			elementPessoa.addContent(new Element("endereco").setText(pessoa.getEndereco()));
			elementPessoa.addContent(new Element("origemCadastro").setText(pessoa.getOrigemCadastro()));
			elementPessoa.addContent(new Element("usuarioCadastro").setText(pessoa.getUsuarioModel().getUsuario()));
			
			elementPessoas.addContent(elementPessoa);
		});
		
		XMLOutputter xmlGerado = new XMLOutputter();
		
		try {
			
			String nomeArquivo = "pessoas_".concat(java.util.UUID.randomUUID().toString()).concat(".xml");
			
			File arquivo = new File("C:\\Users\\Adriano".concat(nomeArquivo));
			
			FileWriter fileWriter = new FileWriter(arquivo);
			
			xmlGerado.output(documentPessoas, fileWriter);
			
			return arquivo;
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
		}
		
		return null;
	}
	
}
