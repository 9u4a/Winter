package study.security.security.common.auth.security.util;

import java.util.Random;

public class PasswordUtil {

    public static String randomPassword() {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 15;
        Random random = new Random();

        return random.ints(leftLimit,rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
