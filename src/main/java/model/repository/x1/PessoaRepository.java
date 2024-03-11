package model.repository.x1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.entity.x1.Pessoa;

public class PessoaRepository {

	public Pessoa salvar(Pessoa novaPessoa) {
		String query = "INSERT INTO Pessoa (nome, CPF, sexo, dataNascimento, tipo) VALUES (?, ?, ?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setString(1, novaPessoa.getNome());
			pstmt.setString(2, novaPessoa.getCpf());
			pstmt.setString(3, novaPessoa.getSexo() + "");
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
	
	/*

	@Override
	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Object entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object consultarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	*/
}
