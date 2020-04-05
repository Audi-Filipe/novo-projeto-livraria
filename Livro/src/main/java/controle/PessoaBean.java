

package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.PessoaDAO;
import dao.PessoaDAOImpl;
import entidade.Livro;
import entidade.Pessoa;
import util.JpaUtil;


 
 //         Classe responsavel por controlar as telas de consultar e manter
 
 


@ManagedBean(name = "PessoaBean")
@SessionScoped
public class PessoaBean {

	private Pessoa usuario;
	private Livro livro;
	private List<Pessoa> listPessoa;
	private String cpfSelecionado;

	// Interfase do pessoaDAO
	
	private PessoaDAO pessoaDAO;

	private static final String MANTER = "mantPessoa.xhtml";
	private static final String PESQUISAR = "pesqPessoa.xhtml";
	
	public PessoaBean() {

		this.usuario = new Pessoa();
		this.usuario.setLivros(new ArrayList<Livro>());

		this.livro = new Livro();
		this.listPessoa = new ArrayList<Pessoa>();

		// Instanciando a interface com a implementação, passando a conexão
		this.pessoaDAO = new PessoaDAOImpl(JpaUtil.getEntityManager());

		// Recupera a lista de todos os usuarios
		this.listPessoa = this.pessoaDAO.listarTodos();

		System.out.println(this.listPessoa);
	}

	
	 // Metodo pesquisar, vai realizar a consulta na base e retornar a lista
	 
	public void pesquisar() {
		
		// Recupera a lista de todos os usuarios
		
		this.listPessoa = this.pessoaDAO.listarTodos();
		System.out.println("Entrou PEsquisar ====");
	}

	public void salvar() throws IOException {
			
		
		
		if (this.pessoaDAO.inserir(this.usuario)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Sucesso !!!"));
			
			abrirPerquisarUsuario();
			
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Erro ao inserir !!!"));
		}

	}
	
	public void adicionarLivro() {

		if(!this.existeLivro(livro)) {
			
			Livro livroNovo = new Livro();
			
			livroNovo.setNome(this.livro.getNome());
			livroNovo.setAutor(this.livro.getAutor());
			livroNovo.setAno_lancamento(this.livro.getAno_lancamento());
			livroNovo.setUsuario(this.usuario);
			
			this.usuario.getLivros().add(livroNovo);
			
			this.livro = new Livro();

		}else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Telefone já existe !!!"));
		}
		
	}

	private boolean existeLivro(Livro livro) {
		boolean retorno = false;
		
		for (Livro livLista : this.usuario.getLivros()) {
			if(livLista.getNome() == livro.getNome() && 
			   livLista.getAutor() == (livro.getAutor())) {
				retorno = true;
			}
		}
		
		return retorno;
	}
	
	public void abrirManterUsuario() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect(MANTER);
	}

	public void abrirPerquisarUsuario() throws IOException {
		this.limpar();
		FacesContext.getCurrentInstance().getExternalContext().redirect(PESQUISAR);
	}

	public void editar() throws IOException {
		Pessoa usuarioEdicao = this.pessoaDAO.pesquisar(cpfSelecionado);
		this.usuario = usuarioEdicao;
		abrirManterUsuario();
	}
	
	public String remover() {
		Pessoa usuarioRemocao = this.pessoaDAO.pesquisar(cpfSelecionado);
		this.pessoaDAO.remover(usuarioRemocao);
		this.listPessoa = this.pessoaDAO.listarTodos();
		return "";
	}
	
	public void limpar() {
		this.listPessoa = new ArrayList<Pessoa>();
		this.usuario = new Pessoa();
		this.usuario.setLivros(new ArrayList<Livro>());
		this.livro = new Livro();
	}
	
	public String voltar() {
		   return("pesqPessoa.xhtml");
		}
	

	
	
	public Pessoa getUsuario() {
		return usuario;
	}

	public void setUsuario(Pessoa usuario) {
		this.usuario = usuario;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<Pessoa> getListPessoa() {
		return listPessoa;
	}

	public void setListPessoa(List<Pessoa> listPessoa) {
		this.listPessoa = listPessoa;
	}

	public String getCpfSelecionado() {
		return cpfSelecionado;
	}

	public void setCpfSelecionado(String cpfSelecionado) {
		this.cpfSelecionado = cpfSelecionado;
	}


}

