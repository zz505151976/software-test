package Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;
import Model.*;
public class HibernateUtil {
		private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		public static Session getSession(){
			Session session = sessionFactory.openSession();
	        return session;
		}
		public static void main(String[] args){
			Session session=getSession();
			List<BeanOperator> pubs=session.createQuery("from BeanOperator").list();
			System.out.println(pubs.size());
		}

}


