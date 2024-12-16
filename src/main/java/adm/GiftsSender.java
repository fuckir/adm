package adm;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import adm.domain.NeUser;
import adm.domain.NewStaffGrab;
import adm.domain.User;
import adm.domain.user.NewRawUser;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

import static adm.domain.Properties.PASSWORD;
import static adm.domain.Properties.USER_NAME;
import static java.lang.Thread.*;

/**
 * @author dmitrys
 * @since 15.09.2020
 */
public class GiftsSender {
    public static void main(String[] args) {
//        List<String> logins = getLogins();
//        List<User> users = grabUsers(logins);
//
//        List<String> gifts = getGiftIds();
//        Collections.shuffle(users);
//
//        if (users.size() > gifts.size()) {
//            throw new RuntimeException("Wrong users size");
//        }

//        for (int i = 0; i < users.size(); i++) {
//            User user = users.get(i);
//            String gift = gifts.get(i);
//            System.out.println(user.getFirstName() + " " +
//                               user.getLastName() + ", " +
//                               user.getEmail() + ", " +
//                               user.getLocation() + ", " +
//                               gift);
//            String content = getContent(user, gift);
//            try {
//                sleep(200);
//                sendEmail(content, user.getEmail());
//            } catch (InterruptedException | EmailException e) {
//                e.printStackTrace();
//            }
//        }

        List<NeUser> users = getList();
        for (NeUser user : users) {
            System.out.println(user.getFirstName() + " " +
                               user.getSecondName() + ", " +
                               user.getLogin() + ", " +
                               user.getLink() + ", " +
                               user.getPassword());
            String content = getContent(user);
            try {
                sleep(200);
                sendEmail(content, user.getEmail());
            } catch (InterruptedException | EmailException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getContent(NeUser user) {
        return String.format("<html>"
                             + "<p>Добрый привет!</p>"
                             + "<p>Остаются считанные часы до наступления нашего теплого и уютного новогоднего вечера!</p>"
                             + "<p><b>Вот заветная персональная ссылка для входа на праздничную онлайн-платформу:</b></p>"
                             + "<p>%s</p>"
                             + "<p>А это твой пароль: %s</p>"
                             + "<p>Пароль МОЖЕТ потребоваться при повторном входе с другого устройства или браузера, если ссылка не сработает.</p>"
                             + "<p>Твое имя, фамилию и название команды платформа уже знает. Тебе необходимо только перейти по ссылке на компьютере или в телефоне.</p>"
                             + "<p>Мы начнём 16 декабря ровно в 17:00. Уверены, что ты не забыл про свою роль капитана на новогоднем Квизе. Только ты сможешь отправлять на платформе ответы своей команды.</p>"
                             + "<p>Варианты будут появляться прямо в чате трансляции и об этом подробно расскажет ведущий.</p>"
                             + "<p><b>На игру приходи с заряженным компьютером и телефоном, подключенным интернетом, чтобы отмечать на платформе ответы твоей команды. </b></p>"
                             + "<ul>"
                             + "<li>Если команда в Смольном, Биткоине или Трюме, то задания будут исходить из экрана переговорки, "
                             + "поэтому звук твоего устройства необходимо выключить. Отвечать можно с телефона или компьютера.</li>"
                             + "<li><p>Если команда в отдельном укромном месте офиса, важно чтобы ребята из команды слушали вопросы и смотрели задания с твоего компьютера или со своих, но звук пусть будет включен у кого-то одного. </p>"
                             + "<p><b>Важно.</b> С Телефона капитан может только отвечать на вопросы. Трансляция параллельно видна "
                             + "не будет. Поэтому приоритетнее открыть трансляцию с компьютера.</p></li>"
                             + "</ul>"
                             + "<p>Также не забудь подобрать уютный наряд и быть в отличнейшем настроении!</p>"
                             + "<p>Вопросы задавай Лине Курковой или своему HR BP.</p>"
                             + "<p>Заходи, мы ждём тебя!</p>"
                             + "<br>"
                             + "<p>С теплотой,<br>"
                             + "Команда ЮTeam</p>"

                             + "<svg width=\"129\" height=\"27\" viewBox=\"0 0 129 27\" fill=\"none\" xmlns=\"http://www.w3"
                             + ".org/2000/svg\">"
                             + "    <path fill=\"#8b3ffd\" d=\"M24.5225 0C17.0035 0 11.0225 6.06646 11.0225 13.5C11.0225 21.019 17"
                             + ".0889 27 24.5225 27C31.956 27 38.0225 20.9335 38.0225 13.5C38.1079 6.06646 31.956 0 24.5225 0ZM24"
                             + ".5225 18.5411C21.7883 18.5411 19.4813 16.2342 19.4813 13.5C19.4813 10.7658 21.7883 8.45886 24.5225 8"
                             + ".45886C27.2566 8.45886 29.5636 10.7658 29.5636 13.5C29.5636 16.2342 27.3421 18.5411 24.5225 18"
                             + ".5411Z\"></path>"
                             + "    <path fill=\"#8b3ffd\" d=\"M11.0222 3.93005V23.5819H6.1519L0 3.93005H11.0222Z\"></path>"
                             + "    <path fill=\"#000000\" d=\"M124.917 7.43433L121.926 14.7824C121.841 14.8679 121.841 15.0388 121"
                             + ".841 15.1242L121.755 15.2951L121.67 15.1242C121.67 15.0388 121.584 14.8679 121.584 14.7824L118.594 7"
                             + ".43433H114.749L119.79 19.9944L117.654 24.7793H121.499L128.591 7.43433H124.917Z\"></path>"
                             + "    <path fill=\"#000000\" d=\"M96.5505 8.11703C95.867 7.60437 95.0125 7.2626 93.9872 7.17716C92"
                             + ".2784 7.00627 90.6549 7.60438 89.5442 8.71513V7.34805H86.041V19.9082H89.5442V13.3291C89.5442 11.8765"
                             + " 89.8005 11.4493 90.0568 11.1075C90.4841 10.424 91.253 10.0822 92.1929 10.0822C93.1328 10.0822 93"
                             + ".9872 10.5094 94.4144 11.193C94.6708 11.7056 94.7562 12.3892 94.7562 12.731C94.7562 13.8417 94.7562 "
                             + "19.9082 94.7562 19.9082H98.2594V12.3037C98.2594 10.3385 97.6613 8.97146 96.5505 8.11703Z\"></path>"
                             + "    <path fill=\"#000000\" d=\"M79.3763 7.5197C77.6674 6.9216 75.7877 7.00704 74.0788 7.77603C72"
                             + ".9681 8.28869 71.9428 9.14312 71.3447 10.2539C70.7466 11.1937 70.4902 12.39 70.4902 13.6716C70.4902 "
                             + "14.9532 70.7466 16.1494 71.3447 17.0893C71.9428 18.0292 72.7117 18.8836 73.7371 19.3963C74.7624 19"
                             + ".9089 75.9586 20.2507 77.0693 20.2507C77.6674 20.2507 78.2655 20.1653 78.8637 19.9944C79.3763 19"
                             + ".8235 79.889 19.6526 80.4016 19.3963C81.4269 18.7982 82.2814 18.0292 82.794 17.0893C83.3921 16.1494 "
                             + "83.6485 14.9532 83.6485 13.6716C83.7339 10.852 82.1105 8.45957 79.3763 7.5197ZM80.4016 13.8425C80"
                             + ".3162 14.9532 79.9744 15.8931 79.2909 16.4912C78.7782 16.9184 78.0092 17.1748 77.0693 17.2602C76"
                             + ".1295 17.2602 75.4459 17.0039 74.8478 16.4912C74.1643 15.8931 73.8225 14.9532 73.7371 13.8425C73"
                             + ".6516 12.5608 74.1643 11.5355 75.1042 10.852C75.6168 10.5102 76.3004 10.2539 76.9839 10.2539C77.7529"
                             + " 10.2539 78.351 10.5102 78.9491 10.852C79.9744 11.4501 80.4871 12.5608 80.4016 13.8425Z\"></path>"
                             + "    <path fill=\"#000000\" d=\"M66.4743 8.11712C65.7907 7.60446 64.9363 7.26269 63.911 7.17725C63"
                             + ".7401 7.17725 63.4838 7.17725 63.3129 7.17725C61.9458 7.17725 60.7496 7.77535 59.7243 8.97155L59"
                             + ".5534 9.22788H59.2116C58.9553 8.80066 58.6135 8.4589 58.2717 8.20257C57.5882 7.68991 56.7338 7.34813"
                             + " 55.7084 7.26269C54.085 7.0918 52.8034 7.60446 51.6926 8.80066V7.51902H48.1895V20.0791H51.6926V13"
                             + ".5V13.3291C51.6926 11.9621 51.8635 11.6203 52.1198 11.1931C52.5471 10.5095 53.316 10.0823 54.1705 10"
                             + ".1678C55.0249 10.1678 55.7939 10.595 56.2211 11.2785C56.5629 11.7912 56.5629 12.3893 56.5629 12"
                             + ".9874V19.9937H60.066V13.2437C60.066 11.9621 60.2369 11.5348 60.4933 11.1076C60.9205 10.4241 61.6895 "
                             + "10.0823 62.5439 10.0823C63.3983 10.0823 64.0819 10.5095 64.5091 11.1931C64.7654 11.6203 64.8508 12"
                             + ".1329 64.8508 12.4747C64.8508 12.6456 64.8508 12.8165 64.8508 12.9019V19.9083H68.354V12.2184C68.354 "
                             + "11.5348 68.2686 10.8513 68.0977 10.2532C67.7559 9.39876 67.2433 8.62978 66.4743 8.11712Z\"></path>"
                             + "    <path fill=\"#000000\" d=\"M111.417 16.1494C110.904 16.662 109.708 17.6019 108.085 17.6873C106"
                             + ".718 17.7728 105.692 17.3456 104.923 16.5766C104.582 16.2348 104.325 15.7221 104.154 15.2095H113"
                             + ".809V15.124C113.895 14.6114 113.98 14.1842 113.98 13.6715C113.98 13.2443 113.895 12.6462 113.724 11"
                             + ".8772C113.297 10.4247 112.442 9.31392 111.332 8.45948C110.05 7.51961 108.854 7.26328 108.512 7"
                             + ".26328C106.034 6.83607 103.214 7.94682 101.762 10.2538C100.737 11.8772 100.822 13.5006 100.822 14"
                             + ".0133C100.822 14.6114 100.907 16.4911 102.445 18.2C104.24 20.1652 106.718 20.2506 107.487 20"
                             + ".3361C110.904 20.4215 113.211 18.2854 113.724 17.8582L111.417 16.1494ZM105.436 10.8519C105.949 10"
                             + ".5101 106.547 10.2538 107.316 10.2538C108.085 10.2538 108.683 10.5101 109.281 10.8519C109.794 11"
                             + ".2791 110.221 11.7918 110.477 12.3899H104.325C104.411 11.7918 104.838 11.1937 105.436 10"
                             + ".8519Z\"></path>"
                             + "</svg>"
                             + "</html>", user.getLink(), user.getPassword());
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
                    null,
                    null
                )
            );
        }

        return users;
    }

    private static void sendEmail(String content, String address) throws EmailException {
        HtmlEmail email = new HtmlEmail();

        email.setHostName("mail.yoomoney.ru");
        email.setSmtpPort(25);
        email.setAuthenticator(new DefaultAuthenticator(USER_NAME, PASSWORD));
        email.setCharset("UTF-8");

        email.setFrom("yooteamnews@yoomoney.ru");
        email.setSubject("Ссылка на праздничную платформу для капитана");
        email.setHtmlMsg("<html>" + content + "<img src=\"@{/resources/logo.png}\" alt=\"\" /></html>");
        email.addTo(address);
//        email.addCc("dmitrys@yoomoney.ru");
        email.send();
    }

    private static List<String> getGiftIds() {
        List<String> gifts = new ArrayList<>();
        gifts.add("Yooteam1");
        gifts.add("Yooteam2");
        gifts.add("Yooteam3");
//        gifts.add("Yooteam4");
//        gifts.add("Yooteam5");

        return gifts;
    }

    private static List<String> getLogins() {
        List<String> logins = new ArrayList<>();

        logins.add("dmitrys");
        logins.add("kurkova");

        return logins;
    }

    private static List<NeUser> getList() {
        List<NeUser> list = new ArrayList<>();

//        list.add(new NeUser("Сергиенко", "Дмитрий", "", "dmitrys@yoomoney.ru", "dmitrys", "https://yoohugge.ru/login/signIn/aaaaaa", "aaaaaa"));
//        list.add(new NeUser("Гичко", "Светлана", "", "gichkosm@yoomoney.ru", "gichkosm", "https://yoohugge.ru/login/signIn/ababab", "ababab"));
//        list.add(new NeUser("Куркова", "Лина", "", "kurkova@yoomoney.ru", "kurkova", "https://yoohugge.ru/login/signIn/bcbcbc", "bcbcbc"));

        list.add(new NeUser("Савельев", "Никита", "Сергеевич", "nssavelyev@yoomoney.ru", "nssavelyev", "https://yoohugge.ru/login/signIn/cap2g6", "cap2g6"));
        list.add(new NeUser("Кузнецов", "Никита", "Владимирович", "nkuznetcov@yoomoney.ru", "nkuznetcov", "https://yoohugge.ru/login/signIn/capke0", "capke0"));
        list.add(new NeUser("Кокорина", "Екатерина", "Сергеевна", "esvikhareva@yoomoney.ru", "esvikhareva", "https://yoohugge.ru/login/signIn/capp4c", "capp4c"));
        list.add(new NeUser("Жигалова", "Светлана", "Александровна", "zhigalovasa@yoomoney.ru", "zhigalovasa", "https://yoohugge.ru/login/signIn/capm37", "capm37"));
        list.add(new NeUser("Шафранская", "Влада", "Сергеевна", "vlada@yoomoney.ru", "vlada", "https://yoohugge.ru/login/signIn/capg7t", "capg7t"));
        list.add(new NeUser("Трухин", "Андрей", "Игоревич", "truhin@yoomoney.ru", "truhin", "https://yoohugge.ru/login/signIn/capy95", "capy95"));
        list.add(new NeUser("Мамедова", "Мария", "Владимировна", "mtryaskina@yoomoney.ru", "mtryaskina", "https://yoohugge.ru/login/signIn/cap97u", "cap97u"));
        list.add(new NeUser("Соловьев", "Николай", "Александрович", "nasolovev@yoomoney.ru", "nasolovev", "https://yoohugge.ru/login/signIn/capmu0", "capmu0"));
        list.add(new NeUser("Фомина", "Мария", "Владимировна", "mvrogozina@yoomoney.ru", "mvrogozina", "https://yoohugge.ru/login/signIn/capn2e", "capn2e"));
        list.add(new NeUser("Скорняков", "Иван", "Васильевич", "skornyakov@yoomoney.ru", "skornyakov", "https://yoohugge.ru/login/signIn/capu0y", "capu0y"));
        list.add(new NeUser("Дедиченко", "Анна", "Игоревна", "pervedetseva@yoomoney.ru", "pervedetseva", "https://yoohugge.ru/login/signIn/capm0v", "capm0v"));
        list.add(new NeUser("Нянькина", "Ксения", "Олеговна", "nyankina@yoomoney.ru", "nyankina", "https://yoohugge.ru/login/signIn/cap3un", "cap3un"));
        list.add(new NeUser("Матвиенко", "Евгения", "Сергеевна", "matvienkoes@yoomoney.ru", "matvienkoes", "https://yoohugge.ru/login/signIn/cap8pz", "cap8pz"));
        list.add(new NeUser("Колпакова", "Ксения", "Игоревна", "kikolpakova@yoomoney.ru", "kikolpakova", "https://yoohugge.ru/login/signIn/capxn8", "capxn8"));
        list.add(new NeUser("Базякина", "Мария", "Александровна", "bazyakina@yoomoney.ru", "bazyakina", "https://yoohugge.ru/login/signIn/capyc6", "capyc6"));
        list.add(new NeUser("Безверхова", "Анастасия", "Олеговна", "aobezverkhova@yoomoney.ru", "aobezverkhova", "https://yoohugge.ru/login/signIn/capp6t", "capp6t"));
        list.add(new NeUser("Разуваева", "Анна", "Олеговна", "voitenko@yoomoney.ru", "voitenko", "https://yoohugge.ru/login/signIn/capu3n", "capu3n"));
        list.add(new NeUser("Воронина", "Анна", "Валерьевна", "avvoronina@yoomoney.ru", "avvoronina", "https://yoohugge.ru/login/signIn/cap9sk", "cap9sk"));
        list.add(new NeUser("Маслова", "Елена", "Ивановна", "eikhorosheva@yoomoney.ru", "eikhorosheva", "https://yoohugge.ru/login/signIn/cap60p", "cap60p"));
        list.add(new NeUser("Герман", "Екатерина", "Валерьевна", "germanev@yoomoney.ru", "germanev", "https://yoohugge.ru/login/signIn/cap5fv", "cap5fv"));
        list.add(new NeUser("Лаптева", "Александра", "Андреевна", "alaptevaa@yoomoney.ru", "alaptevaa", "https://yoohugge.ru/login/signIn/capkr8", "capkr8"));
        list.add(new NeUser("Михайлов", "Александр", "Валерьевич", "mikhailovav@yoomoney.ru", "mikhailovav", "https://yoohugge.ru/login/signIn/cap5ti", "cap5ti"));
        list.add(new NeUser("Шаяхметов", "Рустам", "Рашидович", "shaiakhmetovrr@yoomoney.ru", "shaiakhmetovrr", "https://yoohugge.ru/login/signIn/capbf4", "capbf4"));
        list.add(new NeUser("Ювженко", "Алёна", "Игоревна", "aiyuvzhenko@yoomoney.ru", "aiyuvzhenko", "https://yoohugge.ru/login/signIn/capq8y", "capq8y"));
        list.add(new NeUser("Толстуха", "Сергей", "Александрович", "tolstukha@yoomoney.ru", "tolstukha", "https://yoohugge.ru/login/signIn/capb7n", "capb7n"));
        list.add(new NeUser("Сюсюра", "Анна", "Михайловна", "syusyura@yoomoney.ru", "syusyura", "https://yoohugge.ru/login/signIn/cap3i5", "cap3i5"));
        list.add(new NeUser("Майснер", "Ольга", "Владимировна", "meisner@yoomoney.ru", "meisner", "https://yoohugge.ru/login/signIn/cap6jg", "cap6jg"));
        list.add(new NeUser("Карабанова", "Елена", "Леонтьевна", "karabanova@yoomoney.ru", "karabanova", "https://yoohugge.ru/login/signIn/capav8", "capav8"));
        list.add(new NeUser("Гризоглазова", "Алёна", "Николаевна", "grizoglazovaan@yoomoney.ru", "grizoglazovaan", "https://yoohugge.ru/login/signIn/capp4j", "capp4j"));
        list.add(new NeUser("Бегларян", "Алена", "Олеговна", "aomaruk@yoomoney.ru", "aomaruk", "https://yoohugge.ru/login/signIn/capnc6", "capnc6"));
        list.add(new NeUser("Юдина", "Дарья", "Алексеевна", "yudinada@yoomoney.ru", "yudinada", "https://yoohugge.ru/login/signIn/capg32", "capg32"));
        list.add(new NeUser("Чуркина", "Елена", "Николаевна", "enilina@yoomoney.ru", "enilina", "https://yoohugge.ru/login/signIn/cap49t", "cap49t"));
        list.add(new NeUser("Головин", "Дмитрий", "Александрович", "golovinda@yoomoney.ru", "golovinda", "https://yoohugge.ru/login/signIn/cap9iv", "cap9iv"));
        list.add(new NeUser("Калинин", "Владимир", "Сергеевич", "kalininvs@yoomoney.ru", "kalininvs", "https://yoohugge.ru/login/signIn/cap3v6", "cap3v6"));
        list.add(new NeUser("Абрамов", "Андрей", "Евгеньевич", "aeabramov@yoomoney.ru", "aeabramov", "https://yoohugge.ru/login/signIn/cap5av", "cap5av"));
        list.add(new NeUser("Полякова", "Ирина", "Олеговна", "iopolyakova@yoomoney.ru", "iopolyakova", "https://yoohugge.ru/login/signIn/cap3t9", "cap3t9"));
        list.add(new NeUser("Кушниренко", "Александр", "Викторович", "kushnirenko@yoomoney.ru", "kushnirenko", "https://yoohugge.ru/login/signIn/capd2r", "capd2r"));
        list.add(new NeUser("Ефимов", "Антон", "Олегович", "antonefimov@yoomoney.ru", "antonefimov", "https://yoohugge.ru/login/signIn/capa7q", "capa7q"));
        list.add(new NeUser("Рябухин", "Вадим", "Александрович", "varyabukhin@yoomoney.ru", "varyabukhin", "https://yoohugge.ru/login/signIn/cap5s9", "cap5s9"));
        list.add(new NeUser("Копытова", "Анастасия", "Андреевна", "kopytovaaa@yoomoney.ru", "kopytovaaa", "https://yoohugge.ru/login/signIn/cap7s3", "cap7s3"));
        list.add(new NeUser("Пахомов", "Кирилл", "Ярославович", "pakhomovk@yoomoney.ru", "pakhomovk", "https://yoohugge.ru/login/signIn/capg3b", "capg3b"));
        list.add(new NeUser("Рудник", "Алина", "Вадимовна", "rudnikav@yoomoney.ru", "rudnikav", "https://yoohugge.ru/login/signIn/cap2jb", "cap2jb"));
        list.add(new NeUser("Пушик", "Маргарита", "Сергеевна", "marpushik@yoomoney.ru", "marpushik", "https://yoohugge.ru/login/signIn/cap500", "cap500"));
        list.add(new NeUser("Опыхтина", "Юлия", "Анатольевна", "schegoleva@yoomoney.ru", "schegoleva", "https://yoohugge.ru/login/signIn/cap3dq", "cap3dq"));
        list.add(new NeUser("Фильчаков", "Дмитрий", "Игоревич", "filchakov@yoomoney.ru", "filchakov", "https://yoohugge.ru/login/signIn/caph0w", "caph0w"));
        list.add(new NeUser("Комарова", "Анна", "Сергеевна", "askomarova@yoomoney.ru", "askomarova", "https://yoohugge.ru/login/signIn/capeq5", "capeq5"));
        list.add(new NeUser("Суходол", "Таисия", "Викторовна", "sukhodol@yoomoney.ru", "sukhodol", "https://yoohugge.ru/login/signIn/capy46", "capy46"));
        list.add(new NeUser("Точилина", "Татьяна", "Романовна", "tochilinatr@yoomoney.ru", "tochilinatr", "https://yoohugge.ru/login/signIn/capr46", "capr46"));
        list.add(new NeUser("Фролов", "Анатолий", "Иванович", "aifrolov@yoomoney.ru", "aifrolov", "https://yoohugge.ru/login/signIn/capcn9", "capcn9"));
        list.add(new NeUser("Туманов", "Александр", "Степанович", "astumanov@yoomoney.ru", "astumanov", "https://yoohugge.ru/login/signIn/capy6v", "capy6v"));
        list.add(new NeUser("Суслов", "Максим", "Николаевич", "suslovmn@yoomoney.ru", "suslovmn", "https://yoohugge.ru/login/signIn/cap8su", "cap8su"));
        list.add(new NeUser("Сердюков", "Денис", "Александрович", "dserdukov@yoomoney.ru", "dserdukov", "https://yoohugge.ru/login/signIn/cap8c9", "cap8c9"));
        list.add(new NeUser("Серый", "Денис", "Васильевич", "grey-dv@yoomoney.ru", "grey-dv", "https://yoohugge.ru/login/signIn/capg5j", "capg5j"));
        list.add(new NeUser("Ходаков", "Анатолий", "Анатольевич", "aakhodakov@yoomoney.ru", "aakhodakov", "https://yoohugge.ru/login/signIn/capnf3", "capnf3"));
        list.add(new NeUser("Кокарева", "Юлия", "Вячеславовна", "yukokareva@yoomoney.ru", "yukokareva", "https://yoohugge.ru/login/signIn/capbq9", "capbq9"));
        list.add(new NeUser("Агишева", "Татьяна", "Александровна", "agishevat@yoomoney.ru", "agishevat", "https://yoohugge.ru/login/signIn/cap39o", "cap39o"));
        list.add(new NeUser("Сергиенко", "Дмитрий", "Сергеевич", "dmitrys@yoomoney.ru", "dmitrys", "https://yoohugge.ru/login/signIn/capf0c", "capf0c"));

        return list;
    }


}
