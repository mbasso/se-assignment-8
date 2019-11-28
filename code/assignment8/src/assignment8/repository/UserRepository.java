package assignment8.repository;

import java.util.List;
import java.util.function.Function;

import org.hibernate.Session;
import org.hibernate.Transaction;

import assignment8.model.User;
import assignment8.util.HibernateUtil;

public class UserRepository {
	
	private <T> T doTransaction(Function<Session, T> performTransaction) {
		T result = null;
		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();
			result = performTransaction.apply(session);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}
	
	public void insert(User user) {
		doTransaction(session -> session.save(user));
	}

	public void update(User user) {
		doTransaction(session -> {
			session.update(user);
			return null;
		});
	}

	public void delete(String username) {
		doTransaction(session -> {
			User user = session.get(User.class, username);
			if (user != null) {
				session.delete(user);
				System.out.println("user is deleted");
			}
			return null;
		});
	}

	public User get(String username) {
		return doTransaction(session -> session.get(User.class, username));
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		return doTransaction(session -> session.createQuery("from User").getResultList());
	}
}
