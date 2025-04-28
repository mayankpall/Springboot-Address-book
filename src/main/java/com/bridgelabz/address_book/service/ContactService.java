package com.bridgelabz.address_book.service;

import com.bridgelabz.address_book.dto.ContactDTO;
import com.bridgelabz.address_book.model.Contact;
//import com.bridgelabz.address_book.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ContactService {

    private final List<Contact> contactRepository = new ArrayList<>();
    private final AtomicLong  counter = new AtomicLong();

    public List<ContactDTO> getAllContacts(){
        log.info("Retrieving all contacts. Total count: {}", contactRepository.size());
        return contactRepository.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<ContactDTO> getContactById(Long id){
        return contactRepository.stream().filter(c -> Objects.equals(c.getId(), id)).findFirst().map(this::convertToDTO);

    }

    public ContactDTO createContact(ContactDTO contactDTO) {
        Long id = counter.incrementAndGet();
        Contact contact = convertToEntity(contactDTO, id);
        contactRepository.add(contact);
        return convertToDTO(contact);
    }

    public Optional<ContactDTO> updateContact(Long id, ContactDTO updatedDTO) {
        for (Contact c : contactRepository) {
            if (Objects.equals(c.getId(), id)) {
                c.setName(updatedDTO.getName());
                c.setEmail(updatedDTO.getEmail());
                return Optional.of(convertToDTO(c));
            }
        }
        return Optional.empty();
    }

    public boolean deleteContact(Long id) {
        return contactRepository.removeIf(c -> Objects.equals(c.getId(), id));
    }



    private ContactDTO convertToDTO(Contact contact){
     return new ContactDTO(contact.getName(), contact.getEmail());
    }

    private Contact convertToEntity(ContactDTO dto, Long id) {
        return new Contact(id, dto.getName(), dto.getEmail(), null);
    }



}
