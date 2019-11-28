package com.diploma.knowledgebase.controllers.registrationController;

import com.diploma.knowledgebase.entities.ConfirmationToken;
import com.diploma.knowledgebase.entities.User;
import com.diploma.knowledgebase.services.registrationService.ConfirmationTokenService;
import com.diploma.knowledgebase.services.registrationService.EmailSenderService;
import com.diploma.knowledgebase.services.registrationService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("registration")
public class UserController {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSenderService emailSenderService;
    @Value("${spring.mail.username}")
    private String accountEmail;
    @Value("${mail.confirm}")
    private String confirm;

    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder, ConfirmationTokenService confirmationTokenService, EmailSenderService emailSenderService) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.confirmationTokenService = confirmationTokenService;
        this.emailSenderService = emailSenderService;
    }




    @PreAuthorize("hasAuthority('user')")
    @GetMapping("users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> list = userService.getAllUsers();
        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("user")
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        if (userService.checkUser(user.getPassword())) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            boolean flag = userService.addUser(user);
            if (!flag)
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            else {
                ConfirmationToken confirmationToken = new ConfirmationToken(user);
                user.setReg_id(3L);
                confirmationTokenService.save(confirmationToken);
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setFrom(accountEmail);
                mailMessage.setTo(user.getUsername());
                mailMessage.setSubject("Complete Registration!");
                mailMessage.setText("To confirm your account, please click here : "
                        + confirm + "confirm-account/" + confirmationToken.getConfirmationToken());

                emailSenderService.sendEmail(mailMessage);

                return new ResponseEntity<>(HttpStatus.OK);
            }
        } else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
