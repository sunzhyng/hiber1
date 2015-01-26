package hiber1;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class Test {

	public static void main(String[] args) {

		try {
			
			Configuration cfg = new Configuration().configure();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build(); 
            SessionFactory sf = cfg.buildSessionFactory(serviceRegistry); 
			
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();

			for (int i = 0; i < 2; i++) {
				Contact customer = new Contact();
				customer.setName("customer" + i);
				customer.setEmail("customer");
				session.save(customer);
				
			}

			tx.commit();
			session.close();
			sf.close();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
}