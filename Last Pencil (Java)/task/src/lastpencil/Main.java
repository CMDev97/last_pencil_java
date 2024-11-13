package lastpencil;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        List<String> users = List.of("John", "Jack");
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many pencils would you like to use:");
        int pencils = getNumbersOfPencils(scanner);
        System.out.println("Who will be the first (John, Jack):");
        String name = getFirstPlayer(scanner);
        int current = users.indexOf(name);
        while (pencils > 0) {
            System.out.println("|".repeat(pencils));
            System.out.printf("%s's turn!%n", users.get(current));
            int choosing = 0;
            if (current == 1) {
                choosing = computerChoice(pencils);
                System.out.println(choosing);
            } else {
                choosing = howPencilsRemove(scanner, pencils);
            }
            pencils -= choosing;
            current = (current == 0) ? 1 : 0;
        }
        System.out.printf("%s won!", users.get(current));
    }

    private static int computerChoice(int remainingPencils) {
        if (remainingPencils == 1) return 1;
        if (remainingPencils % 4 == 0) return 3;
        if (remainingPencils % 4 - 1 == 2) return 2;
        if (remainingPencils % 2 == 0) return 1;
        if (remainingPencils % 2 - 1 == 0) return new Random().nextInt(3) + 1;
        return -1;
    }

    private static int howPencilsRemove(Scanner scanner, int remainingPencils) {
        while (true) {
            try {
                String maybePencils = scanner.next();
                int pencils = Integer.parseInt(maybePencils);
                if (pencils < 1 || pencils > 3) {
                    System.out.println("Possible values: '1', '2' or '3'");
                    continue;
                }
                if (pencils > remainingPencils) {
                    System.out.println("Too many pencils were taken");
                    continue;
                }
                return pencils;
            } catch (Exception e) {
                System.out.println("Possible values: '1', '2' or '3'");
            }

        }
    }

    private static String getFirstPlayer(Scanner scanner) {
        while (true) {
            String name = scanner.next();
            if (!"John".equals(name) && !"Jack".equals(name)) {
                System.out.println("Choose between 'John' and 'Jack'");
                continue;
            }
            return name;
        }
    }

    private static int getNumbersOfPencils(Scanner scanner) {
        while (true) {
            try {
                String maybePencils = scanner.nextLine();
                if (maybePencils.isBlank()) {
                    System.out.println("The number of pencils should be numeric");
                    continue;
                }
                int pencils = Integer.parseInt(maybePencils);
                if (pencils <= 0) {
                    System.out.println("The number of pencils should be positive");
                    continue;
                }
                return pencils;
            } catch (Exception e) {
                System.out.println("The number of pencils should be numeric");
            }
        }

    }

}
