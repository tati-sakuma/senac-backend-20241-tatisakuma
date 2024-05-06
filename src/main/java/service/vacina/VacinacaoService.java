package service.vacina;

import java.time.LocalDate;
import java.util.ArrayList;

import exception.vacina.ControleVacinasException;
import model.entity.enums.x1.TipoPessoa;
import model.entity.vacina.Pessoa;
import model.entity.vacina.Vacinacao;
import model.repository.vacina.PessoaRepository;
import model.repository.vacina.VacinacaoRepository;

public class VacinacaoService {

	VacinacaoRepository repository = new VacinacaoRepository();
	private static final int NOTA_MAXIMA = 5;

	public Vacinacao salvar(Vacinacao novaVacinacao) throws ControleVacinasException {
		if (novaVacinacao.getPessoa().getId() == 0 || novaVacinacao.getVacina() == null
				|| novaVacinacao.getVacina().getId() == 0) {
			throw new ControleVacinasException("Informe o id da pessoa e a vacina da aplicação");
		}
		novaVacinacao.setData(LocalDate.now());

		if (novaVacinacao.getAvaliacao() == 0) {
			novaVacinacao.setAvaliacao(NOTA_MAXIMA);
		}

		this.validarAplicacao(novaVacinacao);

		novaVacinacao = repository.salvar(novaVacinacao);

		double media = repository.calcularMediaVacina(novaVacinacao.getVacina().getId());

		VacinaService vacinaService = new VacinaService();
		vacinaService.atualizarMediaVacina(novaVacinacao.getVacina().getId(), media);

		return novaVacinacao;
	}

	private void validarAplicacao(Vacinacao novaVacinacao) throws ControleVacinasException {
		String mensagemErro = "";
		PessoaRepository pessoaRepository = new PessoaRepository();
		Pessoa pessoa = pessoaRepository.consultarPorId(novaVacinacao.getPessoa().getId());

		if (novaVacinacao.getVacina().getEstagio() == 1
				&& (pessoa.getTipo() == TipoPessoa.PUBLICO_GERAL || pessoa.getTipo() == TipoPessoa.VOLUNTARIO)) {
			mensagemErro += "Estágio 1: apenas pesquisador pode receber essa vacina!";
		}

		if (novaVacinacao.getVacina().getEstagio() == 2 && pessoa.getTipo() == TipoPessoa.PUBLICO_GERAL) {
			mensagemErro += "Estágio 2: apenas pesquisador ou voluntário podem receber essa vacina!";
		}

		if (!mensagemErro.isEmpty()) {
			throw new ControleVacinasException(mensagemErro);
		}
	}

	public boolean excluir(int id) {
		return repository.excluir(id);
	}

	public boolean alterar(Vacinacao vacinacao) {
		return repository.alterar(vacinacao);
	}

	public Vacinacao consultarPorIdPessoa(int id) {
		return repository.consultarPorId(id);
	}

	public ArrayList<Vacinacao> consultarTodos() {
		return repository.consultarTodos();
	}

	public Vacinacao consultarPorIdVacina(int id) {
		return repository.consultarPorId(id);
	}

}
