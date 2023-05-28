package Classes;

import java.util.ArrayList;
import java.util.Scanner;


public class Admin extends User {

    public Admin(String name, String phone, String email, String password, String role, int age, String payment, Address address) {
        super(name, phone, email, password, role, age, payment, address);

    }

    public Admin(){}

}
