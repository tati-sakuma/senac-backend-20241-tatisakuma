package model.repository.vacina;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.vacina.Vacina;
import model.entity.vacina.Vacinacao;
import model.repository.x1.Banco;
import model.repository.x1.BaseRepository;
import service.vacina.VacinaService;

public class VacinacaoRepository implements BaseRepository<Vacinacao> {

	@Override
	public Vacinacao salvar(Vacinacao novaVacinacao) {
		String query = "INSERT INTO Aplicacao_Vacina (ID_PESSOA, ID_VACINA, DATA_VACINA, AVALIACAO) VALUES (?, ?, ?, ?)";
		
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setInt(1, novaVacinacao.getIdPessoa());
			pstmt.setInt(2, novaVacinacao.getVacina().getId());
			pstmt.setDate(3, Date.valueOf(novaVacinacao.getData()));
			pstmt.setInt(4, novaVacinacao.getAvaliacao());
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();

			if (resultado.next()) {
				novaVacinacao.setId(resultado.getInt(1));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao salvar nova vacina");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return novaVacinacao;
	}

	@Override
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "DELETE FROM Aplicacao_vacina WHERE id = " + id;
		try {
			if (stmt.executeUpdate(query) == 1) {
				excluiu = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao excluir Vacinação.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluiu;
	}

	@Override
	public boolean alterar(Vacinacao vacinacao) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean retorno = false;

		String query = " UPDATE aplicacao_vacina SET " + " ID_PESSOA= " + vacinacao.getIdPessoa() + " , ID_VACINA="
				+ vacinacao.getVacina().getId() + " , DATA_VACINA= '" + vacinacao.getData() + "' , AVALIACAO= "
				+ vacinacao.getAvaliacao() + " WHERE ID= " + vacinacao.getId();
		try {
			if (stmt.executeUpdate(query) == 1) {
				retorno = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método alterar vacinação!");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}

	@Override
	public Vacinacao consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		String query = " SELECT * FROM Aplicacao_vacina WHERE ID = " + id;
		Vacinacao vacinacao = null;

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				vacinacao = new Vacinacao();
				vacinacao.setId(id);
				vacinacao.setIdPessoa(resultado.getInt("ID_PESSOA"));

				VacinaRepository vacinaRepository = new VacinaRepository();
				Vacina vacina = vacinaRepository.consultarPorId(resultado.getInt("ID_VACINA"));
				vacinacao.setVacina(vacina);

				vacinacao.setData(resultado.getDate("DATA_VACINA").toLocalDate());
				vacinacao.setAvaliacao(resultado.getInt("AVALIACAO"));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar consultar Vacinação com id (" + id + ")");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return vacinacao;
	}

	@Override
	public ArrayList<Vacinacao> consultarTodos() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		ArrayList<Vacinacao> vacinacoes = new ArrayList<Vacinacao>();
		String query = "SELECT * FROM APLICACAO_VACINA;";

		try {
			resultado = stmt.executeQuery(query);
			VacinaRepository vacinaRepository = new VacinaRepository();
			Vacinacao vacinacao = new Vacinacao();

			while (resultado.next()) {
				Vacina vacina = vacinaRepository.consultarPorId(resultado.getInt("ID_VACINA"));

				vacinacao.setId(Integer.parseInt(resultado.getString("ID")));
				vacinacao.setIdPessoa(Integer.parseInt(resultado.getString("ID_PESSOA")));
				vacinacao.setVacina(vacina);
				vacinacao.setData(resultado.getDate("DATA_VACINA").toLocalDate());
				vacinacao.setAvaliacao(resultado.getInt("AVALIACAO"));
				vacinacoes.add(vacinacao);
			}

		} catch (SQLException erro) {
			System.out.println("Erro ao executar consultar todas as vacinações");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return vacinacoes;
	}

	public ArrayList<Vacinacao> vacinacoesPorIdPessoa(int id) {

		ArrayList<Vacinacao> vacinacoes = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String query = "SELECT * FROM Aplicacao_vacina where id_pessoa = " + id;

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				Vacinacao vacinacao = new Vacinacao();
				VacinaService vacinaService = new VacinaService();
				Vacina vacina = vacinaService.consultarPorId(resultado.getInt("ID_VACINA"));

				vacinacao.setId(id);
				vacinacao.setIdPessoa(Integer.parseInt(resultado.getString("ID_PESSOA")));
				vacinacao.setVacina(vacina);
				vacinacao.setData(resultado.getDate("DATA_VACINA").toLocalDate());
				vacinacao.setAvaliacao(resultado.getInt("AVALIACAO"));
				vacinacoes.add(vacinacao);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar consultar vacinações do id " + id);
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return vacinacoes;
	}

	public ArrayList<Vacinacao> vacinacoesPorIdVacina(int id) {

		ArrayList<Vacinacao> vacinacoes = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String query = "SELECT * FROM Aplicacao_vacina where id_vacina = " + id;

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				Vacinacao vacinacao = new Vacinacao();
				VacinaService vacinaService = new VacinaService();
				Vacina vacina = vacinaService.consultarPorId(resultado.getInt("ID_VACINA"));

				vacinacao.setId(id);
				vacinacao.setIdPessoa(Integer.parseInt(resultado.getString("ID_PESSOA")));
				vacinacao.setVacina(vacina);
				vacinacao.setData(resultado.getDate("DATA_VACINA").toLocalDate());
				vacinacao.setAvaliacao(resultado.getInt("AVALIACAO"));
				vacinacoes.add(vacinacao);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar consultar vacinações do id " + id);
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return vacinacoes;
	}
}
