package adm;

import java.util.*;


import adm.domain.Grab;
import adm.domain.RawUser;
import adm.domain.User;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import static adm.domain.Properties.PASSWORD;
import static adm.domain.Properties.USER_NAME;

/**
 * Created by dmitrys
 * 01.12.16.
 */
public class Adm {

    public static void main(String... args) throws EmailException {

        Map<String, String> loginWishlistMap = getLoginWishlistMap();

        List<String> logins = new ArrayList<>(loginWishlistMap.keySet());

        Collections.shuffle(logins);

        collectMessagesAndSendEmail(logins, loginWishlistMap);
    }

    private static void collectMessagesAndSendEmail(List<String> logins, Map<String, String> loginWishlistMap) throws EmailException {
        List<User> userList = getUsers(logins, loginWishlistMap);

        User firstUser = null;
        User oldUser = null;
        String content;
        for (User user : userList) {
            if (oldUser == null) {
                firstUser = user;
            } else {
                content = getMessages(user, oldUser);
//                sendEmail(content, oldUser.getEmail()); //todo
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            oldUser = user;
        }
        content = getMessages(firstUser, oldUser);
//        sendEmail(content, oldUser.getEmail()); //todo
    }

    //
    private static String getMessages(User newUser, User oldUser) {
        String wishPart = !newUser.getWishList().isEmpty() ? "В вишлисте написано: \n" + newUser.getWishList() : "Вишлист пуст.\n\n";

        String[] testMessage = {oldUser.getFirstName(), oldUser.getLastName(), oldUser.getEmail(), newUser.getFirstName(),
            newUser.getLastName(), newUser.getLogin(), newUser.getWishList()
        };
        System.out.println(String.join(" ", testMessage));

        return String.format("Здравствуй, %s. \n"
                             + "Мы тут всё хорошенько перетасовали и так случилось, что в этом году тебе предстоит осчастливить "
                             + "подарком коллегу по имени: %s %s "
                             + "(https://staff.yandex-team.ru/%s).\n\n"
                             + "Теперь есть время до вечера 19 декабря (день перед празднованием Нового года в офисе), чтобы "
                             + "обзавестись милым подарком для своей \"жертвы\" :)\n\n"
                             + "В день празднования – 20 декабря до 15:00 оставь свой подарок вместе с остальными подарками под "
                             + "Ёлочкой в лаунже. \n"
                             + "\n"
                             + "Обязательно напиши на подарке имя получателя!\n"
                             + "Забрать свой подарок можно у этой же нарядной Ёлки в праздничный вечер. \n"
//                             + wishPart
                             + "\n\n-- \n"
                             + "Помощник ТС\n",
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

    private static List<User> getUsers(List<String> logins, Map<String, String> loginWishlistMap) {
        List<User> users = new ArrayList<>();

        //grab
        Grab grab = new Grab();
        for (String login : logins) {
            RawUser rawUser = null;
            rawUser = grab.parseRowUser(login);
//                  if (rawUser != null && !rawUser.getLocation().getOffice().getCity().getName().getEn().equals("St. Petersburg")) {
//                      // TODO: надомники и прочие москвичи
//                      System.out.println("!!!!!!!!! " + rawUser.getLogin());
//                  }
            users.add(new User(
                login,
                rawUser.getName().getLast().getRu(),
                rawUser.getName().getFirst().getRu(),
                rawUser.getEmail(),
                rawUser.getLocation().getOffice().getCity().getName().getEn(),
                loginWishlistMap.get(login))
            );
//      }
        }
        return users;
    }

    private static void sendEmail(String content, String address) throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName("smtp.yandex-team.ru");
        email.setSmtpPort(25);
        email.setAuthenticator(new DefaultAuthenticator(USER_NAME, PASSWORD));
        email.setSSLOnConnect(true);
        email.setCharset("UTF-8");

        email.setFrom("dmitrys@yandex-team.ru");
        email.setSubject("Письмо для Тайного Санты");
//        email.setSubject("Клуб АДМ. Праздник уже завтра.");
        email.setMsg(content);
        email.addTo(address);
        email.addCc("dmitrys@yamoney.ru");
        email.send();
    }

    private static Map<String, String> getLoginWishlistMap() {
        Map<String, String> loginWishlistMap = new HashMap<>();

        loginWishlistMap.put("dedovaiv", "");
        loginWishlistMap.put("starovoitovak", "");

        return loginWishlistMap;
    }
}
