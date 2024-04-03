package com.example.securitychains.service;

import com.example.securitychains.RoleName;
import com.example.securitychains.config.JwtService;
import com.example.securitychains.config.PrincipleUser;
import com.example.securitychains.dto.RegisterDTO;
import com.example.securitychains.dto.RegisterResponseDTO;
import com.example.securitychains.dto.ResponseDTO;
import com.example.securitychains.dto.UsersDTO;
import com.example.securitychains.entity.Roles;
import com.example.securitychains.entity.UserToken;
import com.example.securitychains.entity.Users;
import com.example.securitychains.repository.RoleRepository;
import com.example.securitychains.repository.UserRepository;
import com.example.securitychains.repository.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final JwtService service;
    private final AuthenticationManager manager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserTokenRepository userTokenRepository;
    private final EmailService emailService;
    public ResponseDTO login(UsersDTO usersDTO){
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(usersDTO.getUsername(),usersDTO.getPassword())
        );
        PrincipleUser principleUser = (PrincipleUser) authentication.getPrincipal();
        log.info(principleUser.getUsername());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = service.generateToken(principleUser.getId(), principleUser.getUsername(), principleUser.getRoles());
        return ResponseDTO.builder()
                .token(token)
                .build();
    }

    public String register(RegisterDTO dto){

        String uuid = UUID.randomUUID().toString();
        String code = RandomStringUtils.random(6,false,true);
        UserToken build = UserToken.builder()
                .code(code)
                .token(uuid)
                .username(dto.getUsername())
                .first_name(dto.getFirst_name())
                .last_name(dto.getLast_name())
                .password(dto.getPassword())
                .build();
        userTokenRepository.save(build);
        emailService.sendSimpleMessage(dto.getUsername(),"Account activation code", "Your activation code: " + code);

        return uuid;
    }

    public RegisterResponseDTO verify(String code, String token) throws Exception {
        log.info(LocalTime.now().toString());
        UserToken byToken = userTokenRepository.findUserTokenByToken(token)
                .orElseThrow(() -> new NullPointerException("Token not found"));
        log.info(LocalTime.now().toString());
        log.info(byToken.getExpireTime().toString());

        if (LocalTime.now().isBefore(byToken.getExpireTime().toLocalTime())){
            log.info("ENTERED");
            if (byToken.getCount() <=3){

                if (code.equals(byToken.getCode())){

                    Users users = Users.builder()
                            .username(byToken.getUsername())
                            .last_name(byToken.getLast_name())
                            .first_name(byToken.getFirst_name())
                            .password(passwordEncoder.encode(byToken.getPassword()))
                            .roles(Set.of(new Roles(RoleName.ROLE_USER)))
                            .build();
                    Users save = userRepository.save(users);
                    userTokenRepository.deleteByUsername(save.getUsername());
                    return RegisterResponseDTO.builder()
                            .token(service.generateToken(users.getId(), users.getUsername(), users.getRoles()))
                            .build();

                }
                    else {

                    byToken.setCount(byToken.getCount()+1);
                    userTokenRepository.save(byToken);
                    throw new Exception("invalid verification code");

                         }
            }else {
                throw new Exception("Too much attempts ");
            }

        }else{
            throw new Exception("Expired Token. Try to register again");
        }

    }



}


