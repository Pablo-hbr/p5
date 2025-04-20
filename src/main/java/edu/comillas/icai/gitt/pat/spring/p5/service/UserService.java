package edu.comillas.icai.gitt.pat.spring.p5.service;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import edu.comillas.icai.gitt.pat.spring.p5.entity.Token;
import edu.comillas.icai.gitt.pat.spring.p5.model.ProfileRequest;
import edu.comillas.icai.gitt.pat.spring.p5.model.ProfileResponse;
import edu.comillas.icai.gitt.pat.spring.p5.model.RegisterRequest;
import edu.comillas.icai.gitt.pat.spring.p5.repository.TokenRepository;
import edu.comillas.icai.gitt.pat.spring.p5.repository.AppUserRepository;
import edu.comillas.icai.gitt.pat.spring.p5.util.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * TODO#6
 * Completa los métodos del servicio para que cumplan con el contrato
 * especificado en el interface UserServiceInterface, utilizando
 * los repositorios y entidades creados anteriormente
 */

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private Hashing hashing;

    @Override
    public Token login(String email, String password) {
        AppUser appUser = appUserRepository.findByEmail(email);
        if (appUser == null) return null;

        if (!hashing.compare(appUser.getPassword(), password)) return null;

        Token existingToken = tokenRepository.findByAppUser(appUser);
        if (existingToken != null) return existingToken;

        Token token = new Token();
        token.setAppUser(appUser);
        return tokenRepository.save(token);
    }

    @Override
    public AppUser authentication(String tokenId) {
        Token token = tokenRepository.findById(tokenId).orElse(null);
        return token != null ? token.getAppUser() : null;
    }

    @Override
    public ProfileResponse profile(AppUser appUser) {
        return new ProfileResponse(appUser.getEmail(), appUser.getName(), appUser.getRole());
    }

    @Override
    public ProfileResponse profile(AppUser appUser, ProfileRequest profile) {
        appUser.setName(profile.name());
        appUserRepository.save(appUser);
        return profile(appUser);
    }

    @Override
    public ProfileResponse profile(RegisterRequest register) {
        AppUser appUser = new AppUser();
        appUser.setEmail(register.email());
        appUser.setName(register.name());
        appUser.setRole(register.role());
        appUser.setPassword(hashing.hash(register.password()));
        return profile(appUserRepository.save(appUser));
    }

    @Override
    public void logout(String tokenId) {
        tokenRepository.deleteById(tokenId);
    }

    @Override
    public void delete(AppUser appUser) {
        appUserRepository.delete(appUser);
    }
}