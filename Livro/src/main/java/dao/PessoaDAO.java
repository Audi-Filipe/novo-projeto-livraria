package dao;

import java.util.List;

import entidade.Pessoa;

 
 // PessoaDAO � uma interface, onde compartilha a chamada dos metodos, mas n�o os implementam.
 

public interface PessoaDAO {
	
	public boolean inserir(Pessoa usuario);
	
	public void alterar(Pessoa usuario);

	public void remover(Pessoa usuario);

	public Pessoa pesquisar(String cpf);

	public List<Pessoa> listarTodos();

}
