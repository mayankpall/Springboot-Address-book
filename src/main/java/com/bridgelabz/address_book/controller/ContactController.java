package com.bridgelabz.address_book.controller;

import com.bridgelabz.address_book.model.Contact;
import com.bridgelabz.address_book.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactService service;

    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        return ResponseEntity.ok(service.getAllContacts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id){
        Optional <Contact> contactID = service.getContactById(id);
        return contactID.map(ResponseEntity::ok) .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact){
      Contact con =  service.createContact(contact);
      return ResponseEntity.ok(con);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact updatedContact) {
        Optional<Contact> updated = service.updateContact(id, updatedContact);
        return updated.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        boolean deleted = service.deleteContact(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }






}
