package com.bridgelabz.address_book.repository;

import com.bridgelabz.address_book.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
