package test.lombok;

public class Main {

    public static void main(String[] args) {
        Lombok lombok = new Lombok(1);

        lombok.setName("Oleg");

        System.out.println(lombok);
    }
}
