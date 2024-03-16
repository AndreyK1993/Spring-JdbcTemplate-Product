package app.view;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class ContactUpdateView {

    public Map<String, String> getData() {
        Map<String, String> map = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        String title = "Input first name: ";
        System.out.print(title);
        map.put("firstName", scanner.nextLine().trim());
        title = "Input last name: ";
        System.out.print(title);
        map.put("lastName", scanner.nextLine().trim());
        title = "Input phone in format xxx xxx-xxxx: ";
        System.out.print(title);
        map.put("phone", scanner.nextLine().trim());
        title = "Input id: ";
        System.out.print(title);
        map.put("id", scanner.nextLine().trim());
        return map;
    }

    public void getOutput(String output) {
        System.out.println(output);
    }
}
