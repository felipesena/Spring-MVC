package fsena.course.springmvc.services.security;

import lombok.RequiredArgsConstructor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EncryptionServiceImpl implements EncryptionService {

    private final StrongPasswordEncryptor strongEncryptor;

    public String encryptString(String input){
        return strongEncryptor.encryptPassword(input);
    }

    public boolean checkPassword(String plainPassword, String encryptedPassword){
        return strongEncryptor.checkPassword(plainPassword, encryptedPassword);
    }
}
