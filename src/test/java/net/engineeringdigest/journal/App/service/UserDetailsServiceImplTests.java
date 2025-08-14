package net.engineeringdigest.journal.App.service;

import net.engineeringdigest.journal.App.Service.UserDetailsServiceImpl;
import net.engineeringdigest.journal.App.entity.User;
import net.engineeringdigest.journal.App.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class UserDetailsServiceImplTests {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepo userRepo;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsernameTests(){
        when(userRepo.findByusername(ArgumentMatchers.anyString())).thenReturn(User.builder().username("ram").password("inrinrick").roles(new ArrayList<>()).build());
        UserDetails user=userDetailsService.loadUserByUsername("ram");
        assertNotNull(user);
    }
}
