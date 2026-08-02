package com.eazybytes.jobportal.contact.service.impl;

import com.eazybytes.jobportal.contact.service.IContactService;
import com.eazybytes.jobportal.dto.ContactRequestDto;
import com.eazybytes.jobportal.entity.Contact;
import com.eazybytes.jobportal.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements IContactService {

    private final ContactRepository contactRepository;

    @Override
    public boolean saveContact(ContactRequestDto contactRequestDto) {
        var savedContact = contactRepository.save(transformToEntity(contactRequestDto));
        if(null!=savedContact && savedContact.getId()!=null){
            return true;
        }
        return false;
    }

    private Contact transformToEntity(ContactRequestDto contactRequestDto){
        var contact = new Contact();
        BeanUtils.copyProperties(contactRequestDto,contact);
//        contact.setCreatedAt(Instant.now());
//        contact.setCreatedBy("System");
        contact.setStatus("NEW");
        return contact;
    }
}
