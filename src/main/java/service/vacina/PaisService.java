package service.vacina;

import exception.vacina.ControleVacinasException;
import model.entity.vacina.Pais;
import model.repository.vacina.PaisRepository;

public class PaisService {
	PaisRepository repository = new PaisRepository();

	public Pais salvar(Pais novoPais) throws ControleVacinasException {
		/*if (!repository.verificarSePaisJaCadastrado(novoPais.getPais(), novoPais.getSigla())) {
			throw new ControleVacinasException("País já cadastrado!");
		} */
		return repository.salvar(novoPais);
	}

}
