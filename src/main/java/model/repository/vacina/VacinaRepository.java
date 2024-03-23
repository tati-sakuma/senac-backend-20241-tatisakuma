package model.repository.vacina;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.entity.vacina.Pessoa;
import model.entity.vacina.Vacina;
import model.repository.x1.Banco;
import model.repository.x1.BaseRepository;

public class VacinaRepository implements BaseRepository<Vacina> {

	@Override
	public Vacina salvar(Vacina novaVacina) {
		String query = "INSERT INTO Vacina (nome, ID_PAIS, id_pesquisador, DATA_INICIO_PESQUISA, estagio) VALUES (?, ?, ?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setString(1, novaVacina.getNome());
			pstmt.setInt(2, novaVacina.getPaisOrigem().getIdPais());
			pstmt.setInt(3, novaVacina.getPesquisadorResponsavel().getId());
			pstmt.setDate(4, Date.valueOf(novaVacina.getDataInicioPesquisa()));
			pstmt.setInt(5, novaVacina.getEstagio());
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();

			if (resultado.next()) {
				novaVacina.setId(resultado.getInt(1));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao salvar nova vacina");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}

		return novaVacina;
	}

	@Override
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "DELETE FROM vacina WHERE id = " + id;
		try {
			if (stmt.executeUpdate(query) == 1) {
				excluiu = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao excluir Vacina.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluiu;
	}

	@Override
	public boolean alterar(Vacina vacina) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vacina consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		ResultSet resultado = null;
		Vacina vacina = new Vacina();
		String query = " SELECT * FROM vacina WHERE id = " + id;

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				VacinaRepository vacinaRepository = new VacinaRepository();
				PaisRepository paisRepository = new PaisRepository();

				vacina.setId(id);
				vacina.setNome(resultado.getString("NOME"));
				vacina.setPaisOrigem(paisRepository.consultarPorId(id));
				vacina.setPesquisadorResponsavel(vacinaRepository.buscarPesquisadorID(id));
				vacina.setDataInicioPesquisa(resultado.getDate("DATA_INICIO_PESQUISA").toLocalDate());
				vacina.setEstagio(resultado.getInt("estagio"));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar consultar partida com id (" + id + ")");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return vacina;
	}

	
	private Pessoa buscarPesquisadorID(int id) {
		PessoaRepository pessoaRepository = new PessoaRepository();
		return pessoaRepository.consultarPorId(id);
	}

	@Override
	public ArrayList<Vacina> consultarTodos() {
		ArrayList<Vacina> vacinas = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		ResultSet resultado = null;
		String query = "SELECT * FROM vacina;";

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				Vacina vacina = new Vacina();
				VacinaRepository vacinaRepository = new VacinaRepository();
				PaisRepository paisRepository = new PaisRepository();
				int idPesquisador = Integer.parseInt(resultado.getString("ID"));
				
				vacina.setId(idPesquisador);
				vacina.setNome(resultado.getString("NOME"));
				vacina.setPaisOrigem(paisRepository.consultarPorId(idPesquisador));
				vacina.setPesquisadorResponsavel(vacinaRepository.buscarPesquisadorID(idPesquisador));
				vacina.setEstagio(resultado.getInt("estagio"));
				vacina.setDataInicioPesquisa(resultado.getDate("DATA_INICIO_PESQUISA").toLocalDate());
				vacinas.add(vacina);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar consultar todas as vacinas");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return vacinas;
	}

}
