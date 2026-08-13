package com.eazybytes.jobportal.contact.contoller;

import com.eazybytes.jobportal.contact.service.IContactService;
import com.eazybytes.jobportal.contact.service.impl.ContactServiceImpl;
import com.eazybytes.jobportal.dto.ContactRequestDto;
import com.eazybytes.jobportal.dto.ContactResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final IContactService contactService;

    @PostMapping(version = "1.0")
    public ResponseEntity<String> saveContactMsg(@RequestBody @Valid ContactRequestDto contactRequestDto){

            boolean isSaved = contactService.saveContact(contactRequestDto);
            if (isSaved) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("Request processed successfully");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Request processing failed");
    }

    @GetMapping
    ResponseEntity<String> fetchOpenContacts(@RequestParam
                                             @Validated @NotBlank(message = "Status cannot be blank")
                                             @Size(min=4,message = "Status length should be minimum 4 chars") String status){
        return ResponseEntity.ok("These are contacts with given status: "+ status);
    }

    @GetMapping("/admin")
    public ResponseEntity<List<ContactResponseDto>> fetchNewContactMsgs() {
        List<ContactResponseDto> contactResponseDtos = contactService.fetchNewContactMsgs();
        return ResponseEntity.status(HttpStatus.OK).body(contactResponseDtos);
    }
}
