package app.service;

import app.entity.Contact;
import app.exceptions.ContactDataException;
import app.repository.impl.ContactRepository;
import app.utils.Constants;
import app.utils.IdValidator;
import app.utils.PhoneValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ContactService {

    @Autowired
    Contact contact;
    @Autowired
    ContactRepository repository;

    Map<String, String> errors = new HashMap<>();

    public String create(Contact contact) {
        validateData(contact);
        if (!errors.isEmpty()) {
            try {
                throw new ContactDataException("Check inputs", errors);
            } catch (ContactDataException e) {
                return e.getErrors(errors);
            }
        }

        if (repository.create(contact)) {
            return Constants.DATA_INSERT_MSG;
        } else {
            return Constants.SMTH_WRONG_MSG;
        }
    }

    public String getAll() {
        Optional<List<Contact>> optional = repository.fetchAll();
        if (optional.isPresent()) {
            AtomicInteger count = new AtomicInteger(0);
            StringBuilder stringBuilder = new StringBuilder();
            List<Contact> list = optional.get();
            list.forEach((contact) ->
                    stringBuilder.append(count.incrementAndGet())
                            .append(") ")
                            .append(contact.toString())
            );
            return stringBuilder.toString();
        } else return Constants.DATA_ABSENT_MSG;
    }

    public String getById(String id) {
        validateId(id);
        if (!errors.isEmpty()) {
            try {
                throw new ContactDataException("Check inputs", errors);
            } catch (ContactDataException e) {
                return e.getErrors(errors);
            }
        }

        Optional<Contact> optional = repository.fetchById(Long.parseLong(id));
        if (optional.isEmpty()) {
            return Constants.DATA_ABSENT_MSG;
        } else {
            Contact contact = optional.get();
            return contact.toString();
        }
    }

    public String update(Contact contact) {
        validateData(contact);
        validateId(String.valueOf(contact.getId()));
        if (!errors.isEmpty()) {
            try {
                throw new ContactDataException("Check inputs",
                        errors);
            } catch (ContactDataException e) {
                return e.getErrors(errors);
            }
        }

        if (repository.update(contact)) {
            return Constants.DATA_UPDATE_MSG;
        } else {
            return Constants.SMTH_WRONG_MSG;
        }
    }

    public String delete(String id) {
        validateId(id);
        if (!errors.isEmpty()) {
            try {
                throw new ContactDataException("Check inputs", errors);
            } catch (ContactDataException e) {
                return e.getErrors(errors);
            }
        }

        contact.setId(Long.parseLong(id));
        if (repository.delete(contact)) {
            return Constants.DATA_DELETE_MSG;
        } else {
            return Constants.SMTH_WRONG_MSG;
        }
    }

    private void validateData(Contact contact) {
        if (contact.getFirstName().isEmpty())
            errors.put("first name", Constants.INPUT_REQ_MSG);
        if (contact.getLastName().isEmpty())
            errors.put("last name", Constants.INPUT_REQ_MSG);
        if (PhoneValidator.isPhoneValid(contact.getPhone()))
            errors.put("phone", Constants.PHONE_ERR_MSG);
    }

    private void validateId(String id) {
        if (IdValidator.isIdValid(id))
            errors.put("id", Constants.ID_ERR_MSG);
    }
}
