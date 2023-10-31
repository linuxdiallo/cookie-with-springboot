package com.codepro.cookiemgmnt;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aboubacar.diallo
 * @created 31/10/2023 - 14:42
 * @project cookie-mgmnt
 * @package com.codepro.cookiemgmnt
 */
@RestController
public class CookieController {

    @GetMapping("/set")
    public void setCookie(HttpServletResponse response) {

        int cookieAgeInSeconds = 86400;

        Cookie cookieWebsite = new Cookie("website", "https://codepro.org");
        cookieWebsite.setMaxAge(cookieAgeInSeconds); // expire in 1 day
        cookieWebsite.setHttpOnly(true);
        cookieWebsite.setSecure(true);

        Cookie cookieColor = new Cookie("color", "blue");
        cookieColor.setMaxAge(cookieAgeInSeconds); // expire in 1 day
        cookieColor.setHttpOnly(true);
        cookieColor.setSecure(true);

        response.addCookie(cookieWebsite);
        response.addCookie(cookieColor);

    }

    @GetMapping("/get")
    public String getCookie(@CookieValue(value = "color", defaultValue = "No color found in cookie") String color) {
        return "The sky is " + color;
    }

    @GetMapping("/expiry")
    public String setCookieExpiry(HttpServletResponse response) {

        int cookieAgeInSeconds = 86400;

        Cookie cookieWebsite = new Cookie("website", "https://codepro.org");
        cookieWebsite.setMaxAge(cookieAgeInSeconds); // expire in 1 day
        cookieWebsite.setHttpOnly(true);

        Cookie cookieColor = new Cookie("color", "blue");
        cookieColor.setMaxAge(cookieAgeInSeconds); // expire in 1 day
        cookieColor.setHttpOnly(true);

        response.addCookie(cookieWebsite);
        response.addCookie(cookieColor);

        return "Cookie will expire in " + cookieAgeInSeconds + "seconds.";
    }

    @GetMapping("/delete")
    public String deleteCookie(HttpServletResponse response) {

        Cookie cookie = new Cookie("color", null);
        cookie.setMaxAge(0); // delete cookie
        response.addCookie(cookie);

        return "Cookie deleted";
    }
}