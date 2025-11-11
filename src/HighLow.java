import java.util.Random;
import java.util.Scanner;

//Class representing a single playing card (values 1–13)

class Card {
    private final int value; // numeric value of the card (1 = Ace, 11 = Jack, etc.)

    public Card(int value) {
        this.value = value;
    }

    // Returns the numeric value of the card
    public int getValue() {
        return value;
    }

    // Returns a readable name for the card.
    public String display() {
        switch (value) {
            case 1: return "Ace";
            case 11: return "Jack";
            case 12: return "Queen";
            case 13: return "King";
            default: return String.valueOf(value);
        }
    }
}

class HighLowGame {
    private final Random random = new Random();
    private int balance;

    public HighLowGame(int startingBalance) {
        this.balance = startingBalance;
    }

    public Card drawCard() {
        return new Card(random.nextInt(13) + 1);
    }

    public void playRound(Scanner scanner) {
        if (balance <= 0) {
            System.out.println("You are out of credits! Game over :D");
            System.exit(0);
        }

        System.out.println("Balance: " + balance + " credits\n");
        System.out.println("Enter bet amount or [0] to quit ");
        System.out.println("$=============================$");
        int bet = Integer.parseInt(scanner.nextLine());

        if (bet <= 0) {
            System.out.println("\nYou cash out with " + balance + " credits.");
            System.out.println("99% of gamblers quit before hitting it big.");
            System.exit(0);
           
        }

        if (bet > balance) {
            System.out.println("\nToo broke for that, try again.\n");
            return;
        }

        Card current = drawCard();
        System.out.println("Current card: " + current.display());

        System.out.print("Will the next card be Higher (H) or Lower (L)? ");
        String guess = scanner.nextLine().trim().toUpperCase();

        Card next = drawCard();
        System.out.println("Next card: " + next.display());

        if ((guess.equals("H") && next.getValue() > current.getValue()) ||
            (guess.equals("L") && next.getValue() < current.getValue())) {
            System.out.println("You win! Keep gambling!!!");
            balance += bet;
        } else if (next.getValue() == current.getValue()) {
            System.out.println("A tie. No credits lost");
        } else {
            System.out.println("You lost!");
            balance -= bet;
        }
    }
}

public class HighLow {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HighLowGame game = new HighLowGame(100);

        System.out.println("$====  ^ HIGH-LOW GAME v  ====$");
        while (true) {
            game.playRound(scanner);
        }
    }
}
