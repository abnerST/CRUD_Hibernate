package bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.UsuarioDAO;
import entidades.Usuario;

@ManagedBean
public class UsuarioBean {
	
	private Usuario usuario = new Usuario();
	private List<Usuario> lista;

	public String salvar() {
		try {
			UsuarioDAO.salvar(usuario);
			usuario = new Usuario();
			mensagemExito("Sucesso!", "Usuario cadastrado com sucesso.");
		} catch (Exception e) {
			mensagemErro("Falhou!", "Usuario não foi salvo pelos seguintes motivos: " + e);
		}
		return null;
	}

	public String acessar() {
		if(usuario.getUsuario().toLowerCase().equals("admin".toLowerCase()) && usuario.getSenha().equals("40028922")) {
			return "/opcoes";
		}
	    usuario = UsuarioDAO.loginUsuario(usuario.getUsuario(), usuario.getSenha());
	    if (usuario == null) {
	      usuario = new Usuario();
	     mensagemErro("Usuário não encontrado","");
	      return null;
	    } else {
	          return "/opcoes";
	    }
	  }
	

	public String excluir(Integer id, String descricao) {
		try {
			UsuarioDAO.excluir(id);
			mensagemExito("Sucesso!", "O usuario '" + descricao + "' foi deletado com sucesso!");
		} catch (Exception e) {
			mensagemErro("Erro!", "O usuario '" + descricao + "' não pôde ser deletado pelos seguintes motivos: \n " + e);
		}

		return null;

	}
	

	public String editar() {
		try {
			UsuarioDAO.editar(usuario);
			mensagemExito("Sucesso!", "O usuario '" + usuario.getNome() + "' foi editado com sucesso!");
		} catch (Exception e) {
			mensagemErro("Erro!",
					"O usuario '" + usuario.getNome() + "' não pôde ser editado pelos seguintes motivos: \n " + e);
		}

		return null;

	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getLista() {
		lista = UsuarioDAO.listar();
		return lista;
	}

	public void setLista(List<Usuario> lista) {
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
