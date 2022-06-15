package bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.JogoDAO;
import entidades.Jogo;

@ManagedBean
public class JogoBean {

	private Jogo jogo = new Jogo();
	private List<Jogo> lista;

	public String salvar() {
		try {
			JogoDAO.salvar(jogo);
			jogo = new Jogo();
			mensagemExito("Sucesso!", "Jogo cadastrado com sucesso.");
		} catch (Exception e) {
			mensagemErro("Falhou!", "Jogo não foi salvo pelos seguintes motivos: " + e);
		}
		return null;
	}

	public String excluir(Integer id, String descricao) {
		try {
			JogoDAO.excluir(id);
			mensagemExito("Sucesso!", "O jogo '" + descricao + "' foi deletado com sucesso!");
		} catch (Exception e) {
			mensagemErro("Erro!", "O jogo '" + descricao + "' não pôde ser deletado pelos seguintes motivos: \n " + e);
		}

		return null;

	}
	
	public String numPares(Integer id) {
		String pares = JogoDAO.numPares(id);
		mensagemExito("","Pares: " + pares);
		return null;
	}
	

	public String editar() {
		try {
			JogoDAO.editar(jogo);
			mensagemExito("Sucesso!", "O jogo '" + jogo.getDescricao() + "' foi editado com sucesso!");
		} catch (Exception e) {
			mensagemErro("Erro!",
					"O jogo '" + jogo.getDescricao() + "' não pôde ser editado pelos seguintes motivos: \n " + e);
		}

		return null;

	}
	
	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public List<Jogo> getLista() {
		lista = JogoDAO.listar();
		return lista;
	}

	public void setLista(List<Jogo> lista) {
		this.lista = lista;
	}

	public void mensagemExito(String titulo, String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensagem));
	}

	public void mensagemErro(String titulo, String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, mensagem));
	}

}
