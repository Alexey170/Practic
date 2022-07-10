package com.company;
import java.util.Scanner;

public class Аuthorization {

        private static final String LOGIN = "admin";
        private static final String PASSWORD = "1234";

        public static int authenticate() {
            Scanner s = new Scanner(System.in);

            boolean isLoginSuccess = false;
                System.out.print("Login: ");
                var login = s.nextLine();
                System.out.print("Password: ");
                var password = s.nextLine();
                if (login.equals(LOGIN) && password.equals(PASSWORD)) {
                    isLoginSuccess = true;
                }
            return isLoginSuccess ? 0 : -1;

         }
}
