package br.com.aps.entidade.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	@Produces
	@RequestScoped
	public EntityManager criarEntityManager(EntityManagerFactory factory) {
		return factory.createEntityManager();
	}

	@Produces
	@ApplicationScoped
	public EntityManagerFactory criarFactory() {
		return Persistence.createEntityManagerFactory("aps_persistence");
	}

	public void fecharEntityManager(@Disposes EntityManager em) {
		em.close();
	}
}
