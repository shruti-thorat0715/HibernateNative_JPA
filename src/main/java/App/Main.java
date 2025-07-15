package App;

import service.CountryService;
import service.RegionService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("myPersistenceUnit");

    private static final RegionService regionService = new RegionService(emf);
    private static final CountryService countryService = new CountryService(emf);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = getUserChoice();
            if (choice == 0) {
                System.out.println("Exiting...");
                break;
            }
            processChoice(choice);
        }
        scanner.close();
        emf.close();
    }

    private static void displayMenu() {
        System.out.println("\n=== JPA CRUD Menu ===");
        System.out.println("1. Create Region");
        System.out.println("2. Read Region");
        System.out.println("3. Update Region");
        System.out.println("4. Delete Region");
        System.out.println("5. Create Country");
        System.out.println("6. Read Country");
        System.out.println("7. Update Country");
        System.out.println("8. Delete Country");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return -1;
        }
    }

    private static void processChoice(int choice) {
        switch (choice) {
            case 1: createRegion(); break;
            case 2: readRegion(); break;
            case 3: updateRegion(); break;
            case 4: deleteRegion(); break;
            case 5: createCountry(); break;
            case 6: readCountry(); break;
            case 7: updateCountry(); break;
            case 8: deleteCountry(); break;
            case 0: System.out.println("Exiting..."); break;
            case -1: System.out.println("Please try again."); break;
            default: System.out.println("Invalid choice. Please try again."); break;
        }
    }

    private static void createRegion() {
        System.out.print("Enter region name: ");
        String name = scanner.nextLine();
        regionService.createRegion(name);
    }

    private static void readRegion() {
        System.out.print("Enter region ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            regionService.readRegion(id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        }
    }

    private static void updateRegion() {
        System.out.print("Enter region ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter new region name: ");
            String name = scanner.nextLine();
            regionService.updateRegion(id, name);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        }
    }

    private static void deleteRegion() {
        System.out.print("Enter region ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            regionService.deleteRegion(id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        }
    }

    private static void createCountry() {
        System.out.print("Enter country ID (2 characters): ");
        String id = scanner.nextLine();
        System.out.print("Enter country name: ");
        String name = scanner.nextLine();
        System.out.print("Enter region ID: ");
        try {
            int regionId = Integer.parseInt(scanner.nextLine());
            countryService.createCountry(id, name, regionId);
        } catch (NumberFormatException e) {
            System.out.println("Invalid region ID format.");
        }
    }

    private static void readCountry() {
        System.out.print("Enter country ID: ");
        String id = scanner.nextLine();
        countryService.readCountry(id);
    }

    private static void updateCountry() {
        System.out.print("Enter country ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter new country name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new region ID: ");
        try {
            int regionId = Integer.parseInt(scanner.nextLine());
            countryService.updateCountry(id, name, regionId);
        } catch (NumberFormatException e) {
            System.out.println("Invalid region ID format.");
        }
    }

    private static void deleteCountry() {
        System.out.print("Enter country ID: ");
        String id = scanner.nextLine();
        countryService.deleteCountry(id);
    }
}

