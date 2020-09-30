package adm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import adm.domain.NewStaffGrab;
import adm.domain.User;
import adm.domain.user.NewRawUser;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import static adm.domain.Properties.PASSWORD;
import static adm.domain.Properties.USER_NAME;

/**
 * @author dmitrys
 * @since 15.09.2020
 */
public class GiftsSender {
    public static void main(String[] args) {
//        List<String> logins = new ArrayList<>(getLogins());
//        List<User> users = grabUsers(logins);

        List<User> users = getUsers();

        List<String> gifts = getGiftIds();
        Collections.shuffle(gifts);

        if (users.size() > gifts.size()) {
            throw new RuntimeException("Wrong users size");
        }

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            String gift = gifts.get(i);
            System.out.println(user.getFirstName() + " " + user.getLastName() + ", " +
                               user.getEmail() + ", " +
                               user.getLocation() + ", " +
                               gift);
            String content = getContent(user, gift);
            try {
                Thread.sleep(200);
                sendEmail(content, user.getEmail());
            } catch (InterruptedException | EmailException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getContent(User user, String gift) {
        return String.format("Привет, %s.\n\nРады тебе сообщить, что твой код подарка: %s", user.getFirstName(), gift);
    }

    private static List<User> grabUsers(List<String> logins) {
        List<User> users = new ArrayList<>();

        NewStaffGrab grab = new NewStaffGrab();
        for (String login : logins) {
            NewRawUser rawUser = grab.parseRowUser(login);
            users.add(
                new User(
                    rawUser.getLogin(),
                    rawUser.getLastName(),
                    rawUser.getFirstName(),
                    rawUser.getWorkEmail(),
                    rawUser.getWorkPlace(),
                    null
                )
            );
        }

        return users;
    }

    private static void sendEmail(String content, String address) throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName("mail-mx10.yamoney.ru");
        email.setSmtpPort(25);
        email.setAuthenticator(new DefaultAuthenticator(USER_NAME, PASSWORD));
        email.setCharset("UTF-8");

        email.setFrom("meetup@yamoney.ru");
        email.setSubject("Подарочек");
        email.setMsg(content);
        email.addTo(address);
        email.send();
    }

    private static List<String> getGiftIds() {
        List<String> gifts = new ArrayList<>();
        gifts.add("айлавью1");
        gifts.add("айлавью2");
        gifts.add("айлавью3");
        gifts.add("айлавью4");
        gifts.add("айлавью5");

        return gifts;
    }

    private static List<User> getUsers() {
        List<User> users = new ArrayList<>();

//        users.add(new User("kurkova", "Куркова", "Лина", "kurkova@yamoney.ru", "Санкт-Петербург", null));
        users.add(new User("dmitrys", "Сергиенко", "Дмитрий", "dmitrys@yamoney.ru", "Санкт-Петербург", null));
//        users.add(new User("yukokareva", "Кокарева", "Юлия", "yukokareva@yamoney.ru", "Санкт-Петербург", null));
//        users.add(new User("evuspenskaya", "Успенская", "Елена", "evuspenskaya@yamoney.ru", "Москва", null));
//        users.add(new User("krainovane", "Крайнова", "Наталья", "krainovane@yamoney.ru", "Нижний Новгород", null));

        return users;
    }


}
