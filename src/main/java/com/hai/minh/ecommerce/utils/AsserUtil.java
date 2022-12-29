package com.hai.minh.ecommerce.utils;

import com.hai.minh.ecommerce.exceptions.InvalidArgumentException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AsserUtil {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_PHONE_NUMBER_REGEX = Pattern.compile("^[0-9]{8,11}$");

    public static void notNull(final Object object, final String message) {
        if (object == null) {
            throw new InvalidArgumentException(message);
        }
    }

    public static void notEmpty(final Collection<?> collection, final String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new InvalidArgumentException(message);
        }
    }

    public static void hasText(final String text, final String message) {
        if (!StringUtils.hasText(text)) {
            throw new InvalidArgumentException(message);
        }
    }

    public static void isTrue(final boolean expression, final String message) {
        if (!expression) {
            throw new InvalidArgumentException(message);
        }
    }

    public static void isNull(final Object object, final String message) {
        if (object != null) {
            throw new InvalidArgumentException(message);
        }
    }

    public static void validateEmail(final String emailStr, final String message) {
        final Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        if (!matcher.find()) {
            throw new InvalidArgumentException(message);
        }
    }

    public static void validatePhoneNumber(final String phoneNum, final String message) {
        final Matcher matcher = VALID_PHONE_NUMBER_REGEX.matcher(phoneNum);
        if (!matcher.find()) {
            throw new InvalidArgumentException(message);
        }
    }

    public static void validateYear(final Integer yearFrom, final Integer yearTo, final String message) {
        if (yearFrom > yearTo) {
            throw new InvalidArgumentException(message);
        }
    }

}
