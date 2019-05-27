package hibernate;

import java.util.List;

import dbmodel.School;
import org.hibernate.Criteria;
import org.hibernate.Session;

public class Main {

	Session session;

	public static void main(String[] args) {
		Main main = new Main();
		main.printSchools();
		main.close();
	}

	public Main() {
		session = HibernateUtil.getSessionFactory().openSession();
	}

	public void close() {
		session.close();
		HibernateUtil.shutdown();
	}

	private void printSchools() {
		Criteria crit = session.createCriteria(School.class);
		List<School> schools = crit.list();

		System.out.println("### Schools and classes");
		for (School s : schools) {
			System.out.println(s);
		}
	}
}
