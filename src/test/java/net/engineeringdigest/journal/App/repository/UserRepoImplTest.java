package net.engineeringdigest.journal.App.repository;

import net.engineeringdigest.journal.App.entity.User;
import net.engineeringdigest.journal.App.service.UserArgumentsProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserRepoImplTest {

    @Autowired
    private UserRepoImpl userRepoImpl;

    @Test
    public void testSaveNewUser(){
        Assertions.assertNotNull(userRepoImpl.getUserForSA());
    }


}
