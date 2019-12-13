package br.home.adrnmatos.filters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


/***
 * INTERCEPT FACES SERVLET REQUESTS
 * */
@WebFilter(servletNames = {"Faces Servlet"})
public class JPAFilter implements Filter {

	private EntityManagerFactory entityManagerFactory;
	private String persistence_unit_name = "unit_app";
	
    public JPAFilter() {
    }

    public void destroy() {
		this.entityManagerFactory.close();
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		/* CREATE ENTITYMANAGER */
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		
		/* PUT ON REQUEST */
		request.setAttribute("entityManager", entityManager);
		
		/* INIT TRANSACTION */
		entityManager.getTransaction().begin();
		
		/* FORWARD REQUEST */
		chain.doFilter(request, response);
		
		try {
			
			/* PERSISTE TRANSACAO */
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			
			/* ROLLBACK ON ERROR */
			entityManager.getTransaction().rollback();
			
		} finally {
			
			/* RELEASE RESOURCES */
			entityManager.close();
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
		/* CREATE ENTITYMANAGERFACTORY */
		this.entityManagerFactory = Persistence.createEntityManagerFactory(this.persistence_unit_name);
	}

}
