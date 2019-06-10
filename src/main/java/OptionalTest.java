import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalTest {

    public static void main(String[] args) {
        Optional<String> optional = Optional.empty();
        System.out.println(optional); // Optional.empty
        System.out.println(optional.isPresent());

        List<String> listOpt = Optional.ofNullable(getList()).orElseGet(() -> new ArrayList<>());

        String streetOrigin = getStreet();

        Optional<User> user = Optional.ofNullable(getUser());
        Optional<Address> address = user.map(User::getAddress);
        Optional<String> street = address.map(Address::getStreet);
        String result = street.orElse("주소 없음");

    // 다음과 같이 축약해서 쓸 수 있다.
        user.map(User::getAddress)
                .map(Address::getStreet)
                .orElse("주소 없음");

//        User user1 = getUser();
//        Optional<User> userOptional = Optional.ofNullable(user1);
//        String result2 = userOptional
//                .flatMap(User::getAddress)
//                .flatMap(Address::getStreet)
//                .orElse("주소 없음");

        String value = null;
        Optional<String> valueOpt = Optional.ofNullable(value);
//        String result = valueOpt.orElseThrow(CustomExcpetion::new).toUpperCase();
    }

    private static String getStreet() {
        User user = getUser();
        if (user != null) {
            Address address = user.getAddress();
            if (address != null) {
                String street = address.getStreet();
                if (street != null) {
                    return street;
                }
            }
        }
        return "주소 없음";
    }

    private static User getUser() {

        return User.builder().address(Address.builder().street("seoul").build()).build();
    }

    private static List<String> getList() {
        return Arrays.asList("a","b");
    }
}
