package app.repository.impl;

import app.entity.Contact;
import app.entity.ContactMapper;
import app.repository.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

// Positional parameters are used in SQL-queries
@Component
public class ContactRepository implements AppRepository<Contact> {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean create(Contact contact) {
        String sql = "INSERT INTO contacts (first_name, last_name, phone) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(),
                contact.getPhone()) > 0;
    }

    @Override
    public Optional<List<Contact>> fetchAll() {
        String sql = "SELECT * FROM contacts";
        Optional<List<Contact>> optional;
        try {
            optional = Optional.of(jdbcTemplate
                    .query(sql, new ContactMapper()));
        } catch (Exception ex) {
            optional = Optional.empty();
        }
        return optional;
    }

    @Override
    public Optional<Contact> fetchById(Long id) {
        String sql = "SELECT * FROM contacts WHERE id = ? LIMIT 1";
        Optional<Contact> optional;
        try {
            optional = Optional.ofNullable(jdbcTemplate
                    .queryForObject(sql, new ContactMapper(), id));
        } catch (Exception ex) {
            optional = Optional.empty();
        }
        return optional;
    }

    @Override
    public boolean update(Contact contact) {
        Optional<Contact> optional = fetchById(contact.getId());
        if (optional.isEmpty()) return false;
        else {
            String sql = "UPDATE contacts SET first_name = ?, last_name = ?, phone = ? WHERE id = ?";
            return jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(), contact.getPhone(),
                    contact.getId()) > 0;
        }
    }

    @Override
    public boolean delete(Contact contact) {
        Optional<Contact> optional = fetchById(contact.getId());
        if (optional.isEmpty()) return false;
        else {
            String sql = "DELETE FROM contacts WHERE id = ?";
            return jdbcTemplate.update(sql, contact.getId()) > 0;
        }
    }

}
