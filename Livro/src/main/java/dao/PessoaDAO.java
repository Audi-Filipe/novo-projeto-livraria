package dao;

import java.util.List;

import entidade.Pessoa;

 
 // PessoaDAO é uma interface, onde compartilha a chamada dos metodos, mas não os implementam.
 

public interface PessoaDAO {
	
	public boolean inserir(Pessoa usuario);
	
	public void alterar(Pessoa usuario);

	public void remover(Pessoa usuario);

	public Pessoa pesquisar(String cpf);

	public List<Pessoa> listarTodos();

}
