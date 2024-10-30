package utilities;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class DataGenerationUtility {

    public static String firstRandomName(){
        String[] firstName = {"John", "Emma", "Michael", "Sarah", "William", "Tisha", "Bongani", "Thando"};
        Random random = new Random();
        return firstName[random.nextInt(firstName.length)];
    }

    public static String lastRandomName(){
        String[] lastName = {"Smith", "Doe", "Johnson", "Khumalo", "Zondo", "Khubeka", "Stigling", "Pilay"};
        Random random = new Random();
        return lastName[random.nextInt(lastName.length)];
    }

    public static String randomUserName(){
        String[] userName = {"Smith123", "Doe456", "Johnson7", "Khumalo3", "Zondo1", "Khubeka5", "UnathiL1", "HappyM2"};
        Random random = new Random();
        return userName[random.nextInt(userName.length)];
    }

    public static String generateRandomStreetAddress(){
        String[] streets = {"Main Street", "Park Ave", "Oak Street", "Long Street", "NewTown"};
        Random random = new Random();
        return streets[random.nextInt(streets.length)];
    }

    public static String generateRandomCityAddress(){
        String[] cities = {"Cape Town", "Johannesburg", "Durban", "Pretoria", "Polokwane", "Kimberly", "Bloemfontein"};
        Random random = new Random();
        return cities[random.nextInt(cities.length)];
    }

    public static String generateRandomProvinceAddress(){
        String[] province = {"Western Cape", "Gauteng", "KwaZulu-Natal", "North West", "Free State", "Port Elizabeth"};
        Random random = new Random();
        return province[random.nextInt(province.length)];
    }

    // Method to generate a random number of specified length
    public static String getRandomNumber(int length) {
        String digits = "0123456789";
        StringBuilder randomNumber = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(digits.length());
            randomNumber.append(digits.charAt(index));
        }

        return randomNumber.toString();
    }

    public static String generatePassword(){
        String[] password = {"Smith@12", "Doe_491", "JohnsonY@1", "Khumalo!P@sswor1", "Zondo2516", "56282@Khubeka"};
        Random random = new Random();
        return password[random.nextInt(password.length)];
    }

    public static String generateRandomIncorrectUserName(){
        Random random = new Random();
        String[] incorrectUserNameType = {"empty", "short", "long", "specialChars"};

        String username = "";
        String type = incorrectUserNameType[random.nextInt(incorrectUserNameType.length)];

        switch (type){
            case "empty":
                break;
            case "short":
                username = "u";
                break;
            case "long":
                for (int i = 0; i < 10; i++){
                    username += "a";
                }
                break;
            case "specialChars":
                username = "!@%#^$&()";
                break;
        }
        return username;
    }

    public static String generateRandomIncorrectPassword(){
        Random random = new Random();
        String[] incorrectPasswordType = {"empty", "short", "long", "specialChars"};

        String password = "";
        String type = incorrectPasswordType[random.nextInt(incorrectPasswordType.length)];

        switch (type){
            case "empty":
                break;
            case "short":
                password = "u";
                break;
            case "long":
                for (int i = 0; i < 10; i++){
                    password += "a";
                }
                break;
            case "specialChars":
                password = "!@%#^$&()";
                break;
        }
        return password;
    }

    public static String randomAlphaNumeric(){
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        String generatedNumber = RandomStringUtils.randomNumeric(3);
        return (generatedString + "@" + generatedNumber);
    }
}
