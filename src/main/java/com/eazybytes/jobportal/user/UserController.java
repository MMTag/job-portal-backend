package com.eazybytes.jobportal.user;

import com.eazybytes.jobportal.dto.UserDto;
import com.eazybytes.jobportal.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping("/search/admin")
    ResponseEntity<?> searchUserByEmail(@RequestParam String email){
        var userDtoOptional = userService.findUserByEmail(email);
        if(userDtoOptional.isPresent()){
            return ResponseEntity.ok(userDtoOptional.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "User not found with email: " + email));
        }
    }

    @PatchMapping("${userId}/role/employer/admin")
    ResponseEntity<?> elevateToEmployer(@PathVariable Long userId){
        UserDto updatedUser = userService.elevateToEmployer(userId);
        return ResponseEntity.ok(updatedUser);
    }

    @PatchMapping("/{userId}/company/{companyId}/admin")
    public ResponseEntity<?> assignCompanyToEmployer(
            @PathVariable Long userId, @PathVariable Long companyId) {
        UserDto updatedUser = userService.assignCompanyToEmployer(userId, companyId);
        return ResponseEntity.ok(updatedUser);
    }
}
