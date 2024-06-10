package service.carros;

import java.util.ArrayList;
import java.util.List;

import exception.vacina.CarrosException;
import model.entity.carros.Carro;
import model.entity.carros.Montadora;
import model.repository.carros.CarroRepository;
import model.seletor.carros.CarroSeletor;

public class CarroService {

	private MontadoraService montadoraService = new MontadoraService();
	private CarroRepository carroRepository = new CarroRepository();

	public ArrayList<Carro> consultarComFiltros(CarroSeletor seletor) throws CarrosException {
		if (seletor != null) {
			if((seletor.getAnoFinal() != null && seletor.getAnoInicial() == null) ||(seletor.getAnoFinal() == null && seletor.getAnoInicial() != null) ) {
				throw new CarrosException("Preencha as duas datas.");
			}

			return carroRepository.consultarComFiltro(seletor);

		} else {
			throw new CarrosException("O filtro não pode estar vazio. Preencha pelo menos um filtro.");
		}
	}

	public int consultarEstoqueCarros(int idMontadora) throws CarrosException {
		ArrayList<Carro> carros = carroRepository.consultarTodos();
		ArrayList<Montadora> montadoras = montadoraService.consultarTodas();

		boolean montadoraCadastrada = montadoras.stream().anyMatch(montadora -> montadora.getId().equals(idMontadora));

		if (!montadoraCadastrada) {
			throw new CarrosException("Montadora não encontrada. Id informado: " + idMontadora);
		}

		List<Carro> carrosDaMontadoraSelecionada = carros.stream()
				.filter(c -> c.getMontadora().getId().equals(idMontadora)).toList();

		return carrosDaMontadoraSelecionada.size();
	}
}
