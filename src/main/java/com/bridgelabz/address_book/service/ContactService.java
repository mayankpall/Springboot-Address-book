package com.bridgelabz.address_book.service;

import com.bridgelabz.address_book.dto.ContactDTO;
import com.bridgelabz.address_book.model.Contact;
import com.bridgelabz.address_book.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<ContactDTO> getAllContacts(){
        return contactRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<ContactDTO> getContactById(Long id){
        return contactRepository.findById(id).map(this::convertToDTO);
    }

    public ContactDTO createContact(ContactDTO contactDTO) {
        Contact contact = convertToEntity(contactDTO);
        Contact saved = contactRepository.save(contact);
        return convertToDTO(saved);
    }

    public Optional<ContactDTO> updateContact(Long id, ContactDTO updatedContactDTO) {
        return contactRepository.findById(id)
                .map(contact -> {
                    contact.setName(updatedContactDTO.getName());
                    contact.setEmail(updatedContactDTO.getEmail());

                    Contact saved = contactRepository.save(contact);
                    return convertToDTO(saved);
                });
    }

    public boolean deleteContact(Long id) {
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
            return true;
        }
        return false;
    }



    private ContactDTO convertToDTO(Contact contact){
     return new ContactDTO(contact.getName(), contact.getEmail());
    }

    private Contact convertToEntity(ContactDTO contactDTO){
        return new Contact(contactDTO.getName(), contactDTO.getEmail(), null);
    }



}
