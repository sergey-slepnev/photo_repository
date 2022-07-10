package com.slepnev.stockphoto.temp;

import java.util.List;
import java.util.Random;

public class RandomUtils {
    private static final Random RANDOM = new Random();

    private static final List<String> phoneNumbers = List.of(
            "+375 29 999 78 91", "+375 30 989 78 95",
            "+37525-777-77-53", "33 356 52 43",
            "+375257777885", "33 392 52-19",
            "+375 55 999 78 99", "+375 33 989 78 99",
            "+375 25-777-77-09", "33 356 58 14",
            "+375 25777 7896", "33 394 78-19"
    );

    private static final List<String> names = List.of(
            "Abramson Oliver", "Jack Jenkin", "James Harry", "Jacob Jenkin", "Charley",
            "Thomas Brooks", "George Mercer", "Oscar Carter", "Maxim Derrick", "Will Ellington"
    );

    private static final List<String> usernames = List.of(
            "Michael_001", "Christopher_002", "Jessica_003",
            "Matthew_004", "Ashley_005", "Jennifer_006",
            "Joshua_007", "Amanda_008", "Daniel_009"
    );

    private static final List<String> emails = List.of(
            "Michael_001@gmai.com", "Christopher_002@gmai.com", "Jessica_003@gmai.com",
            "Matthew_004@gmai.com", "Ashley_005@gmai.com", "Jennifer_006@gmai.com",
            "Joshua_007@gmai.com", "Amanda_008@gmai.com", "Daniel_009@gmai.com"
    );

    private static final List<String> socialNetworkLinks = List.of(
            "Michael_001_INSTAGRAM", "Christopher_002_FACEBOOK", "Jessica_003__FACEBOOK",
            "Matthew_004_INSTAGRAM", "Ashley_005_FACEBOOK", "Jennifer_006_INSTAGRAM",
            "Joshua_007_FACEBOOK", "Amanda_008_INSTAGRAM", "Daniel_009_FACEBOOK"
    );

    private static final List<String> passwords = List.of(
            "1234", "Mouse_233", "KeyBoard_(0(", "Table_34!",
            "WindScreen_up", "WebCam_1080", "PhotoFrame_007",
            "Microphone_new_15", "BreakWall_MY_67", "LampOn_1"
    );

    private static final List<String> resolutions = List.of(
            "3264 x 2448", "3648 x 2736", "4000 x 3000"
    );

    private static final List<Double> photoSizes = List.of(
            2.3, 1.7, 5.6, 3.6, 11.2, 9.0
    );

    private RandomUtils() {
    }

    private static int randomIndex(List<?> list) {
        return RANDOM.nextInt(list.size());
    }

    public static String randomPhoneNumber() {
        return phoneNumbers.get(randomIndex(phoneNumbers));
    }

    public static String randomName() {
        return names.get(randomIndex(names));
    }

    public static String randomUsername() {
        return usernames.get(randomIndex(names));
    }

    public static String randomEmail() {
        return emails.get(randomIndex(emails));
    }

    public static String randomSocialNetworkLink() {
        return socialNetworkLinks.get(randomIndex(socialNetworkLinks));
    }

    public static String randomPassword() {
        return passwords.get(randomIndex(passwords));
    }

    public static String randomResolution() {
        return resolutions.get(randomIndex(resolutions));
    }

    public static Double randomSize() {
        return photoSizes.get(randomIndex(photoSizes));
    }
}