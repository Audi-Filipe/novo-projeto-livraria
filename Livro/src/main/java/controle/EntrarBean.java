
package controle;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import dao.PessoaDAO;
import dao.PessoaDAOImpl;
import entidade.Pessoa;
import util.JpaUtil;


 
 //	EntrarBean, classe responsavel pela logica de logar no sistema
 

@ManagedBean(name="EntrarBean")
@RequestScoped
public class EntrarBean {

	//Essas variaveis são responsaveis para o acesso geral, o admin
	
	private String usarioAdmin = "admin";
	private String senhaAdmin = "admin";
	
	private String usuarioTXT;
	private String senhaTXT;
	                                         
	private static final String PESQUISAR = "paginas/pesqPessoa.xhtml"; 
	private PessoaDAO pessoaDAO;
	private String mensagem;
	
	public EntrarBean() { 
		this.pessoaDAO = new PessoaDAOImpl(JpaUtil.getEntityManager());
	}
	
	
	 // MEtodo entrar, vai verificar se é o admin ou um usuario já existente
	
	 
	public void entrar() throws IOException {
		if(this.usuarioTXT.equals(this.usarioAdmin)
		  && this.senhaTXT.equals(this.senhaAdmin)) {
			FacesContext.getCurrentInstance().getExternalContext().redirect(PESQUISAR);
		}else { 
			Pessoa usuarioPesquisa = this.pessoaDAO.pesquisar(this.usuarioTXT);
			if(usuarioPesquisa != null) {
				if(usuarioPesquisa.getSenha().equals(this.senhaTXT)) {
					FacesContext.getCurrentInstance().getExternalContext().redirect(PESQUISAR);
				}else {
					this.mensagem = "Usuario ou senha incorretos";
				}
			}else {
				this.mensagem = "Usuario ou senha incorretos";
			}
			
		}
	}

	public String getUsuarioTXT() {
		return usuarioTXT;
	}

	public void setUsuarioTXT(String usuarioTXT) {
		this.usuarioTXT = usuarioTXT;
	}

	public String getSenhaTXT() {
		return senhaTXT;
	}

	public void setSenhaTXT(String senhaTXT) {
		this.senhaTXT = senhaTXT;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}

