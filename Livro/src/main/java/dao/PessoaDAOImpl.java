
package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entidade.Pessoa;


 //	Essa classe implementa a interface, todos os metodos mostrados na interface.
 

public class PessoaDAOImpl implements PessoaDAO {

	private EntityManager ent;

	//Construtor vai receber a conex�o para executar
	
	public PessoaDAOImpl(EntityManager ent) {
		this.ent = ent;
	}


	
	 //* Metodo inserir, recebe o usuario todo preenchido, cria uma transi��o, inicia e 
	 //* executar a a��o de persistir, tudo dando certo realiza o commit no final
	 
	public boolean inserir(Pessoa usuario) {

		EntityTransaction tx = ent.getTransaction();
		tx.begin();

		ent.persist(usuario);
		tx.commit();

		return true;

	}


// * Metodo alterar, recebe o usuario, criar uma transi��o, inicia e executa a a��o de merger
 
	public void alterar(Pessoa usuario) {

		EntityTransaction tx = ent.getTransaction();
		tx.begin();

		ent.merge(usuario);
		tx.commit();

	}


	public void remover(Pessoa usuario) {
		EntityTransaction tx = ent.getTransaction();
		tx.begin();

		ent.remove(usuario);
		tx.commit();
		
	}


 //* Pesquisar, pesquisar pela chave primaria que seria o cpf
 
	public Pessoa pesquisar(String cpf) {

		Pessoa usuario = ent.find(Pessoa.class, cpf);
		
		return usuario;
		
	}


// * O metodo listar todos, faz um select * from, por�m com o JPA, vc faz a consulta pelo objeto direto
 //* assim from Usuario, que isso � o objeto usuario e n�o a tabela
 
	public List<Pessoa> listarTodos() {

		Query query = ent.createQuery("from Pessoa u");
		
		List<Pessoa> usuarios = query.getResultList();
	
		return usuarios;
		
	}

}
