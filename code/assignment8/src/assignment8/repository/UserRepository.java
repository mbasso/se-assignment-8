package assignment8.repository;

import java.util.List;
import java.util.function.Function;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import assignment8.model.Address;
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

	private void setIfNotNullOrEmpty(Criteria criteria, String property, String value) {
		if (value != null && !value.isEmpty()) {
			criteria.add(Restrictions.like(property, "%" + value + "%"));
		}
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<User> findUsers(User user) {
		return doTransaction(session -> {
			Criteria criteria = session.createCriteria(User.class);

			setIfNotNullOrEmpty(criteria, "username", user.getUsername());
			setIfNotNullOrEmpty(criteria, "name", user.getName());
			
			User bestFriend = user.getBestFriend();
			if (bestFriend != null) {
				String bestFriendUsername = bestFriend.getUsername();
				if (bestFriendUsername != null && !bestFriendUsername.isEmpty()) {
					criteria.createAlias("bestFriend", "bf");
					criteria.add(Restrictions.eq("bf.username", bestFriend.getUsername()));
				}
			}
			
			Address address = user.getAddress();
			if (address != null) {
				criteria.createAlias("address", "a");
				setIfNotNullOrEmpty(criteria, "a.country", address.getCountry());
				setIfNotNullOrEmpty(criteria, "a.city", address.getCity());
				setIfNotNullOrEmpty(criteria, "a.street", address.getStreet());
			}

			return criteria.list();
		});
	}
}
