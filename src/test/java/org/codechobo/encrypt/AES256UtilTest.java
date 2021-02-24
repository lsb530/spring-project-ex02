package org.codechobo.encrypt;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
@Log4j
public class AES256UtilTest {
    String id1="123456";
    String id2="boki";
    String id3="안녕하세요";

    @Test
    public void testEncrypt() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
//        AES256Util a256 = AES256Util.getInstance();
//        String encrypted_id1 = a256.AES_Encode(id1);
//        String encrypted_id2 = a256.AES_Encode(id2);
//        String encrypted_id3 = a256.AES_Encode(id3);
//
//        String decoded_id1 = a256.AES_Decode(encrypted_id1);
//        String decoded_id2 = a256.AES_Decode(encrypted_id2);
//        String decoded_id3 = a256.AES_Decode(encrypted_id3);
//
//        System.out.println(decoded_id1);
//        System.out.println(decoded_id2);
//        System.out.println(decoded_id3);
    }
}