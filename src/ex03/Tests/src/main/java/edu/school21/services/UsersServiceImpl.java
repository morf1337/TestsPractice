package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;

import javax.persistence.EntityNotFoundException;

public class UsersServiceImpl {
    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public boolean authenticate(String login, String password) {
        User user = usersRepository.findByLogin(login);

        if (user == null) {
            throw new EntityNotFoundException("User not found with login: " + login);
        }

        if (user.isAuthSuccess()) {
            throw new AlreadyAuthenticatedException("User is already authenticated.");
        }

        if (password.equals(user.getPassword())) {
            user.setAuthSuccess(true);
            usersRepository.update(user);
            return true;
        } else {
            return false;
        }
    }
}
