package com.bridgelabz.address_book.service;

import com.bridgelabz.address_book.controller.AddressBookException;
import com.bridgelabz.address_book.dto.ContactDTO;
import com.bridgelabz.address_book.model.Contact;
import com.bridgelabz.address_book.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<ContactDTO> getAllContacts() {
        log.info("Retrieving all contacts. Total count: {}", contactRepository.count());
        return contactRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ContactDTO> getContactById(Long id) {
        return Optional.ofNullable(contactRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new AddressBookException("Contact with ID " + id + " not found")));
    }

    public ContactDTO createContact(ContactDTO contactDTO) {
        Contact contact = convertToEntity(contactDTO, null);
        Contact saved = contactRepository.save(contact);
        log.info("Created new contact with ID: {}", saved.getId());
        return convertToDTO(saved);
    }

    public Optional<ContactDTO> updateContact(Long id, ContactDTO updatedDTO) {
        return Optional.ofNullable(contactRepository.findById(id).map(existing -> {
            Contact updated = convertToEntity(updatedDTO, id);
            Contact saved = contactRepository.save(updated);
            log.info("Updated contact with ID: {}", id);
            return convertToDTO(saved);
        }).orElseThrow(() -> new AddressBookException("Contact with ID " + id + " not found")));
    }

    public boolean deleteContact(Long id) {
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
            log.info("Deleted contact with ID: {}", id);
            return true;
        }
        log.warn("Contact with ID {} not found for deletion", id);
        return false;
    }

    private ContactDTO convertToDTO(Contact contact) {
        return new ContactDTO(
                contact.getId(),
                contact.getName(),
                contact.getPhoneNumber(),
                contact.getAddress(),
                contact.getCity(),
                contact.getState(),
                contact.getZipCode(),
                contact.getEmail()
        );
    }
    private Contact convertToEntity(ContactDTO dto, Long id) {
        Contact contact = new Contact();
        if (id != null) {
            contact.setId(id);
        }
        contact.setName(dto.getName());
        contact.setPhoneNumber(dto.getPhoneNumber());
        contact.setAddress(dto.getAddress());
        contact.setCity(dto.getCity());
        contact.setState(dto.getState());
        contact.setZipCode(dto.getZipCode());
        contact.setEmail(dto.getEmail());
        return contact;
    }
}