import java.util.*;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toMap;



public class PhoneBook {

    private Map<String, List<String>> contacts;



    public PhoneBook() {

        contacts = new HashMap<>();

    }



    public void addContact(String name, String phone) {

        if (contacts.containsKey(name)) {

            List<String> phones = contacts.get(name);

            if (!phones.contains(phone)) {

                phones.add(phone);

            }

        } else {

            List<String> phones = new ArrayList<>();

            phones.add(phone);

            contacts.put(name, phones);

        }

    }



    public void printContacts(boolean sort) {

        if(sort) {

            sortContacts();

        }

        System.out.println("Контакты:");

        List<String> reverseOrderedKeys = new ArrayList<String>(contacts.keySet());
        Collections.reverse(reverseOrderedKeys);

        for (String key : reverseOrderedKeys) {
            List<String> phones = contacts.get(key);

            String name = key;

            System.out.println(name + " - " + phones.size() + ":");

            for (String phone : phones) {

                System.out.println("tel:" + phone);

            }

        }

    }



    private void sortContacts() {

        contacts = contacts.entrySet().stream()
            .sorted(comparingInt(e -> e.getValue().size()))
            .collect(toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (a, b) -> { throw new AssertionError(); },
                LinkedHashMap::new));

    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.addContact("John Doe", "+7 123-456-7890");

        phoneBook.addContact("John Doe", "+7 234-567-8901");

        phoneBook.addContact("Jane Doe", "+7 345-678-9012");

        phoneBook.addContact("Bob Smith", "+7 456-789-0123");

        phoneBook.addContact("Bob Smith", "+7 567-890-1234");

        phoneBook.addContact("Bob Smith", "+7 649-234-1234");



        phoneBook.printContacts(true);
    }
}