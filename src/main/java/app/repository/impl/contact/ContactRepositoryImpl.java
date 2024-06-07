package app.repository.impl.contact;

import app.entity.Contact;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ContactRepositoryImpl implements ContactRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean create(Contact contact) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "INSERT INTO Contact (firstName, lastName, phoneNumber) " +
                "VALUES (:firstName, :lastName, :phoneNumber)";
        MutationQuery query = session.createMutationQuery(hql);
        query.setParameter("firstName", contact.getFirstName());
        query.setParameter("lastName", contact.getLastName());
        query.setParameter("phoneNumber", contact.getPhoneNumber());
        return query.executeUpdate() > 0;
    }

    @Override
    public Optional<List<Contact>> fetchAll() {
        try {
            Session session = sessionFactory.getCurrentSession();
            List<Contact> list = session.createQuery("FROM Contact",
                    Contact.class).list();
            return Optional.of(list);
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    @Override
    public boolean update(Long id, Contact contact) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "UPDATE Contact SET firstName = :firstName, " +
                    "lastName = :lastName, " +
                    "phoneNumber = :phoneNumber " +
                    "WHERE id = :id";
            MutationQuery query = session.createMutationQuery(hql);
            query.setParameter("firstName", contact.getFirstName());
            query.setParameter("lastName", contact.getLastName());
            query.setParameter("phoneNumber", contact.getPhoneNumber());
            query.setParameter("id", id);
            query.executeUpdate();
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Contact contact = session.byId(Contact.class).load(id);
            session.remove(contact);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public Optional<Contact> fetchById(Long id) {
        Optional<Contact> optional;
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "FROM Contact m WHERE m.id = :id";
            Query<Contact> query = session.createQuery(hql, Contact.class);
            query.setParameter("id", id);
            optional = query.uniqueResultOptional();
            return optional;
        } catch (Exception exception) {
            return Optional.empty();
        }
    }
}
