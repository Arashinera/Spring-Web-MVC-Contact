package app.repository.impl.contact;

import app.entity.Contact;
import app.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends BaseRepository<Contact> {
    boolean create(Contact contact);

    Optional<List<Contact>> fetchAll();

    Optional<Contact> fetchById(Long id);

    boolean update(Long id, Contact contact);

    boolean delete(Long id);
}
