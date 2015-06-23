package br.ufc.webdev.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.webdev.model.Pessoa;
import br.ufc.webdev.model.PessoaDAO;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class updateController
 */
@WebServlet("/EditarPessoa")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
	  
	  
			List<String> erros = new ArrayList<String>();
			Pessoa pessoa = new Pessoa();
			
			if (isValid(req, pessoa, erros)){
				
				Connection connection = (Connection) req.getAttribute("connection");
				PessoaDAO dao = new PessoaDAO(connection);
				
				try {
					dao.update(pessoa);
					req.getRequestDispatcher("index.jsp").forward(req, resp);
					
				} catch (SQLException e) {} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else {
				req.setAttribute("erros", erros);
				///
				///
				///
				///
				///
				//resp.sendRedirect("formEditar.jsp");
			}
			
		}

		private boolean isValid(HttpServletRequest req, Pessoa pessoa, List<String> erros) {
			
			int id = Integer.parseInt(req.getParameter("id"));
			String nome = req.getParameter("username").trim();
			String email = req.getParameter("email").trim();
			String senha = req.getParameter("password").trim();
			
			
			if (nome == null || senha == null || email == null || id <= 0) {
				erros.add("Preencha os campos de nome e senha");
				
			} else if (nome.trim().equals("") || senha.trim().equals("") || email.trim().equals("")) {
				erros.add("Preencha os campos de nome, senha ou email");
			} else if(id <= 0){
				erros.add("Id inválido");
			}else if (erros.isEmpty() == false) {
				return false;
			}
			
			pessoa.setId(id);
			pessoa.setNome(nome);
			pessoa.setSenha(senha);
			pessoa.setEmail(email);
			
			return true;
		}

}
		
