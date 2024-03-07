package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UsersServiceImplTest {
    private User user;
    private String CORRECT_LOGIN = "user1";
    private String CORRECT_PASSWORD = "password1";
    private String INCORRECT_LOGIN = "user2";
    private String INCORRECT_PASSWORD = "password2";
    private UsersRepository mockUsersRepository = Mockito.mock(UsersRepository.class);
    private UsersServiceImpl usersService = new UsersServiceImpl(mockUsersRepository);

    @BeforeEach
    private void init() {
        user = new User(1L, CORRECT_LOGIN, CORRECT_PASSWORD, false);
        when(mockUsersRepository.findByLogin(CORRECT_LOGIN)).thenReturn(user);
        doNothing().when(mockUsersRepository).update(user);
    }

    @Test
    public void successAuthTest() {
        Assertions.assertTrue(usersService.authenticate(CORRECT_LOGIN, CORRECT_PASSWORD));
        verify(mockUsersRepository).update(user);
    }

    @Test
    public void failedAuthTest() {
        Assertions.assertFalse(usersService.authenticate(CORRECT_LOGIN, INCORRECT_PASSWORD));
    }

    @Test
    public void nonExistentUserTest() {
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> usersService.authenticate(INCORRECT_LOGIN, INCORRECT_PASSWORD));
    }

    @Test
    public void alreadyAuthedUserTest() {
        user.setAuthSuccess(true);
        Assertions.assertThrows(AlreadyAuthenticatedException.class,
                () -> usersService.authenticate(CORRECT_LOGIN, CORRECT_PASSWORD));
    }
}
