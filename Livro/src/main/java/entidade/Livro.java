
package entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



 //* Classe telefone utilizada com referencia com usuário, onde faz um para muitos.
 //* Nesta classe tem uma coisa em particular, ela tem um ID, onde o mesmo é autoincrmentado
// * pelo proprio JPA com a sequencia S_LIVRO
 

@Entity
@Table(name = "LIVRO")
public class Livro {
	
	@Id
	@Column(name="id")
	@GeneratedValue(generator = "S_LIVRO")
	@SequenceGenerator(name = "S_LIVRO", sequenceName = "S_LIVRO", allocationSize = 1)
	private long id;

	@Column(name="nome") 
	private String nome;
	
	@Column(name="ano_lancamento") 
	private int ano_lancamento;
	
	@Column(name="autor") 
	private String autor;

	
	// * @ManyToOne essa referencia faz com que, ao recuperar um usuario o mesmo, trás todos os
	 //* livros do usuário, pegando a chave de referencia. cpf de usuario com cpf_pessoa 
	 //* do livro
	 
	@ManyToOne
	@JoinColumn(name = "cpf_pessoa", referencedColumnName = "cpf", nullable = false)
	private Pessoa usuario;

	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAno_lancamento() {
		return ano_lancamento;
	}

	public void setAno_lancamento(int ano_lancamento) {
		this.ano_lancamento = ano_lancamento;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Pessoa getUsuario() {
		return usuario;
	}

	public void setUsuario(Pessoa usuario) {
		this.usuario = usuario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
