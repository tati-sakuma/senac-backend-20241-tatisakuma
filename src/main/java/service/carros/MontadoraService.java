package service.carros;

import java.util.ArrayList;
import java.util.Objects;

import exception.vacina.CarrosException;
import model.entity.carros.Montadora;
import model.repository.carros.MontadoraRepository;

public class MontadoraService {
	private MontadoraRepository montadoraRepository = new MontadoraRepository();
	
	public Montadora salvar(Montadora novaMontadora) throws CarrosException{
		this.validarMontadora(novaMontadora);
		novaMontadora = montadoraRepository.salvar(novaMontadora);
		
		return null;
	}
	
	private void validarMontadora(Montadora novaMontadora) throws CarrosException {
		String mensagemErro = "";
		
		if (Objects.requireNonNullElse(novaMontadora.getNome(), "").isEmpty()) {
			mensagemErro += "Erro: o campo nome não pode estar vazio ou nulo.";
	    }
	    if (Objects.requireNonNullElse(novaMontadora.getPaisFundacao(), "").isEmpty()) {
	    	mensagemErro += "Erro: o campo pais não pode estar vazio ou nulo.";
	    }
	    if (Objects.requireNonNullElse(novaMontadora.getNomePresidente(), "").isEmpty()) {
	    	mensagemErro += "Erro: o campo nome do presidente não pode estar vazio ou nulo.";
	    }
	    if (novaMontadora.getDataFundacao() == null) {
	    	mensagemErro += "Erro: o campo data de fundação não pode ser nulo.";
	    }
	    
	    if(!mensagemErro.isEmpty()) {
	    	throw new CarrosException(mensagemErro);
	    }
	}

	public ArrayList<Montadora> consultarTodas() {
		return montadoraRepository.consultarTodos();
	}
	
}
