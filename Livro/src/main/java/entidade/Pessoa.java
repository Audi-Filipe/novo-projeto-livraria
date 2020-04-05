package entidade;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


// * Pessoa é a classe principal desse projeto onde tem a tela de pesquisa e a tela de manter
 //* Usuario tem uma lista de livros e tem como chave primaria o cpf.
 

@Entity
@Table(name = "PESSOA")
public class Pessoa {

	@Id
	@Column(name="cpf")
	private String cpf;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="senha")
	private String senha;
	
	@Column(name="sexo")
	private String sexo;
	
	@Column(name="idade")
	private int idade;
	
	
	// * A lista de livros é carregado de forma automatica pelo proprio jpa, para isso acontecer
	 //* tem que realizar o mapeamento com o livro, lá em livro tb tem a configuração de mapeamento
	 
	@OneToMany(mappedBy="usuario", cascade= CascadeType.ALL)  
	private List<Livro> livros;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}


	

}
