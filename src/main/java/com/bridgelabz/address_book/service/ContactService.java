package com.bridgelabz.address_book.service;

import com.bridgelabz.address_book.model.Contact;
import com.bridgelabz.address_book.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getAllContacts(){
        return contactRepository.findAll();
    }

    public Optional<Contact> getContactById(Long id){
        return contactRepository.findById(id);
    }

    public Contact createContact(Contact contact){
        return contactRepository.save(contact);
    }

    public Optional<Contact> updateContact(Long id, Contact newContact){
        return contactRepository.findById(id).map(contact->{
                contact.setName(newContact.getName());
            contact.setEmail(newContact.getEmail());
            contact.setPhone(newContact.getPhone());
            return contactRepository.save(contact);

        });
    }

    public boolean deleteContact(Long id){
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
            return true;
        }
        return false;
    }



}
