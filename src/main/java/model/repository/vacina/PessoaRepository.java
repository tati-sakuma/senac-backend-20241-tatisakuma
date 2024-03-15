package model.repository.vacina;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.enums.x1.TipoPessoa;
import model.entity.vacina.Pessoa;
import model.entity.vacina.Vacina;
import model.entity.vacina.Vacinacao;
import model.repository.x1.Banco;
import model.repository.x1.BaseRepository;

public class PessoaRepository implements BaseRepository<Pessoa>{

	@Override
	public Pessoa salvar(Pessoa novaPessoa){
		String query = "INSERT INTO Pessoa (nome, CPF, sexo, dataNascimento, tipo) VALUES (?, ?, ?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setString(1, novaPessoa.getNome());
			
			pstmt.setString(2, novaPessoa.getCpf());
			pstmt.setString(3, novaPessoa.getSexo());
			pstmt.setDate(4, Date.valueOf(novaPessoa.getDataNascimento()));
			pstmt.setString(5, novaPessoa.getTipo().toString());
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();
			
			if(resultado.next()) {
				novaPessoa.setId(resultado.getInt(1));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao salvar nova pessoa");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		
		return novaPessoa;
	}
	
	
	public boolean verificarExistenciaRegistroPorCpf(Pessoa novaPessoa) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		boolean retorno = false;
		String query = "SELECT cpf FROM pessoa WHERE cpf = '" + novaPessoa.getCpf() + "'";
		
		try {
			resultado = stmt.executeQuery(query);
			if(resultado.next()) {
				retorno = true;
			}
		} catch(SQLException erro) {
			System.out.println("Erro ao executar a query do método verificarExistenciaRegristroPorCpf!");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}
	
	/*public ArrayList<Vacinacao> vacinacoesPorId(int id){
		
		ArrayList<Vacinacao> vacinacoes = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ResultSet resultado = null;
		String query = "SELECT * FROM Aplicacao_vacina where id_pessoa = " + id;
		
		try{
			resultado = stmt.executeQuery(query);
			while(resultado.next()){
				Vacinacao vacinacao = new Vacinacao();
				Vacina vacina = new Vacina();
				
				
				vacinacao.setId(Integer.parseInt(resultado.getString("ID")));
				vacinacao.setSexo(resultado.getString("SEXO"));
				vacinacao.setDataNascimento(resultado.getDate("DATANASCIMENTO").toLocalDate()); 
				vacinacao.setTipo(TipoPessoa.valueOf(resultado.getString("TIPO")));
				vacinacoes.add(vacinacao);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao executar consultar todas as pessoas");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return pessoas;
	}
	*/
	@Override
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "DELETE FROM pessoa WHERE id = " + id;
		try {
			if(stmt.executeUpdate(query) == 1) {
				excluiu = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao excluir pessoa.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluiu;
	}
	
	
	@Override
	public boolean alterar(Pessoa pessoa) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Pessoa consultarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public ArrayList<Pessoa> consultarTodos() {
		ArrayList<Pessoa> pessoas = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ResultSet resultado = null;
		String query = "SELECT * FROM pessoa;";
		
		try{
			resultado = stmt.executeQuery(query);
			while(resultado.next()){
				Pessoa pessoa = new Pessoa();
				
				pessoa.setId(Integer.parseInt(resultado.getString("ID")));
				pessoa.setNome(resultado.getString("NOME"));
				pessoa.setCpf(resultado.getString("CPF"));
				pessoa.setSexo(resultado.getString("SEXO"));
				pessoa.setDataNascimento(resultado.getDate("DATANASCIMENTO").toLocalDate()); 
				pessoa.setTipo(TipoPessoa.valueOf(resultado.getString("TIPO")));
				pessoas.add(pessoa);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao executar consultar todas as pessoas");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return pessoas;
	}

	
	
}
