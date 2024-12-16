package adm;

import adm.domain.LoginAddressWishlist;
import adm.domain.NewStaffGrab;
import adm.domain.User;
import adm.domain.user.NewRawUser;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.util.ArrayList;
import java.util.List;

import static adm.domain.Properties.PASSWORD;
import static adm.domain.Properties.USER_NAME;
import static java.lang.String.format;
import static java.lang.String.join;
import static java.lang.Thread.sleep;
import static java.util.Collections.shuffle;

/**
 * Created by dmitrys
 * 01.12.16.
 */
public class Adm {

    public static void main(String... args) throws EmailException {
        List<LoginAddressWishlist> loginAddressWishlistList = new ArrayList<>(getLoginAddressWishlist());
        shuffle(loginAddressWishlistList);

        List<User> users = grabUsers(loginAddressWishlistList);

        collectMessagesAndSendEmail(users);
    }

    private static void collectMessagesAndSendEmail(List<User> users) throws EmailException {
        User firstUser = null;
        User oldUser = null;

        for (User user : users) {
            if (oldUser == null) {
                firstUser = user;
            } else {
                String message = getMessage(user, oldUser);
                sendEmail(message, oldUser.getEmail()); //todo
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            oldUser = user;
        }
        String message = getMessage(firstUser, oldUser);
        sendEmail(message, oldUser.getEmail()); //todo
    }

    //
    private static String getMessage(User newUser, User oldUser) {
        String wishPart = !newUser.getWishList().isEmpty() ? "В вишлисте написано: \n" + newUser.getWishList() : "Вишлист пуст.\n\n";

        String[] testMessage = {oldUser.getFirstName(), oldUser.getLastName(), oldUser.getEmail(), newUser.getFirstName(),
                newUser.getLastName(), newUser.getLogin(), newUser.getWishList()
        };
        System.out.println(join(" ", testMessage));

        return format("Здравствуй, %s. \n"
                        + "Мы тут всё хорошенько перетасовали и так случилось, что в этом году тебе предстоит осчастливить "
                        + "подарком коллегу по имени: %s %s "
                        + "(https://staff.yooteam.ru/#/%s).\n\n"
                        + "Теперь есть время до 20 декабря (день празднования Нового года), чтобы "
                        + "обзавестись подарком для своей \"жертвы\" :)\n\n"
                        + "и 20 декабря положить подарок под ёлочку.\n" //todo разные места для городов
                        + "\n"
                        + wishPart
                        + "\n\n-- \n"
                        + "Yoomoney Santa\n",
                oldUser.getFirstName(), newUser.getFirstName(), newUser.getLastName(), newUser.getLogin());
//        return "Привет.\n"
//               + "Завтра в течение дня надо принести подарок под ёлку, которая стоит на 5м этаже у ресепшена.\n"
//               + "Подарок обязательно надо подписать. Постарайся подписать так, чтобы эта подпись не отвалилась, и чтобы потом не "
//               + "гадать от какого она подарка.\n"
//               + "Если найдешь подарок со своим именем, то его можно сразу забирать. Централизованной раздачи не будет.\n"
//               + "Очень хочется, чтобы все подарки приносились до 17:00, чтобы каждый участник не ждал до ночи своего Деда Мороза.\n"
//               + "\n"
//               + "Хорошо тебе отпраздновать, позитивных эмоций и больше детской радости.\n"
//               + "\n"
//               + "ЗЫ Если ты до сих пор не подготовил подарок своей жертве, то сегодня самое время это сделать :)\n"
//               + "ЗЫЗЫ Если ты заболел(а)/в командировке и не успел(а) подготовить подарок, срочно напиши мне, будем что-то "
//               + "оперативно придумывать.";
    }

    private static List<User> grabUsers(List<LoginAddressWishlist> loginAddressWishlistList) {
        List<User> users = new ArrayList<>();

        NewStaffGrab grab = new NewStaffGrab();
        for (LoginAddressWishlist loginAddressWishlist : loginAddressWishlistList) {
            NewRawUser rawUser = grab.parseRowUser(loginAddressWishlist.getLogin());
            users.add(
                    new User(
                            rawUser.getLogin(),
                            rawUser.getLastName(),
                            rawUser.getFirstName(),
                            rawUser.getWorkEmail(),
                            loginAddressWishlist.getAddress(),
                            null,
                            loginAddressWishlist.getWishlist()
                    )
            );
        }

        return users;
    }

    private static void sendEmail(String content, String address) throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName("mail.yoomoney.ru");
        email.setSmtpPort(25);
        email.setAuthenticator(new DefaultAuthenticator(USER_NAME, PASSWORD));
        email.setCharset("UTF-8");

        email.setFrom("yoosanta@yoomoney.ru"); //yooteam@yoomoney.ru
        email.setSubject("Письмо для YooSanta");
        email.setMsg(content);
        email.addTo(address);
        email.addCc("dmitrys@yoomoney.ru");
        email.send();
    }

    private static List<LoginAddressWishlist> getLoginAddressWishlist() {
        List<LoginAddressWishlist> users = new ArrayList<>();

        users.add(new LoginAddressWishlist("kroshchupkina", "", "что-то пахучее или рыбное или и такое и такое"));
        users.add(new LoginAddressWishlist("zhenevskaya", "", ""));
        users.add(new LoginAddressWishlist("bugaenkoki", "", "то, что ассоциируется с новым годом и уютом."));
        users.add(new LoginAddressWishlist("avmelnikova", "", "дивное диво)"));
        users.add(new LoginAddressWishlist("solovyevatd", "", "елочную игрушку или гирлянду"));

        return users;
    }
}
