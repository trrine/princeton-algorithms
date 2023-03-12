import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SocialNetworkConnectivity {
    public static void main(String[] args) {
        String date, time;
        int n = 5; // number of members
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);

        try {
            File logFile = new File("friendships.txt");
            Scanner scanner = new Scanner(logFile);

            while (scanner.hasNextLine()) {
                int memberA = scanner.nextInt();
                int memberB = scanner.nextInt();
                date = scanner.next();
                time = scanner.next();

                // form friendship
                uf.union(memberA, memberB);

                if (uf.count() == 1) {
                    System.out.println("All members connected at timestamp: " + date + " " + time);
                    break;
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}
