package br.home.adrnmatos.pessoa.controller;

import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

import br.home.adrnmatos.repository.PessoaRepository;

@Named(value = "graficoPizzaPessoaController")
@RequestScoped
public class GraficoPizzaPessoaController {
	
	@Inject
	private PessoaRepository pessoaRepository;
	
	private PieChartModel pieChartModel;
	
	public PieChartModel getPieChartModel() {
		return pieChartModel;
	}
	
	@PostConstruct
	public void init() {
		
		this.pieChartModel = new PieChartModel();
		
		this.montarGrafico();
	}

	private void montarGrafico() {

		Hashtable<String, Integer> hashtableRegistros = pessoaRepository.getOrigemPessoa();
		
		hashtableRegistros.forEach((chave, valor) -> {
			pieChartModel.set(chave, valor);
		});
		
		pieChartModel.setTitle("Total Pessoas por Tipo");
		pieChartModel.setShowDataLabels(true);
		pieChartModel.setLegendPosition("e");
	}

}
