/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 *
 * @author sabrinahaniff
 */
public class blackjack
{

    //card suit unicode icons: ♥ ♣ ♠ ♦
    static final String SPADES = "\u2660";
    static final String HEARTS = "\u2665";
    static final String DIAMONDS = "\u2666";
    static final String CLUBS = "\u2663";

    static String[] deck = new String[52];
    static int top = deck.length - 1;

    static String[] playerHand = new String[2];
    static String[] dealerHand = new String[2];

    static Scanner console = new Scanner(System.in);

    static int round = 0;

    public static void main(String[] args)
    {
        boolean rules = true;

        Scanner src1 = new Scanner(System.in);

        //title display
        System.out.println("  ____\n"
                + " /\\' .\\    _____\n"
                + "/: \\___\\  / .  /\\\n"
                + "\\' / . / /____/..\\\n"
                + " \\/___/  \\'  '\\  /\n"
                + "          \\'__'\\/");

        System.out.println("    ____  __    ___   ________ __    _____   ________ __\n"
                + "   / __ )/ /   /   | / ____/ //_/   / /   | / ____/ //_/\n"
                + "  / __  / /   / /| |/ /   / ,< __  / / /| |/ /   / ,<   \n"
                + " / /_/ / /___/ ___ / /___/ /| / /_/ / ___ / /___/ /| |  \n"
                + "/_____/_____/_/  |_\\____/_/ |_\\____/_/  |_\\____/_/ |_|  \n"
                + "                                                        ");
        
        System.out.println("\n");
        System.out.println("WELCOME TO BLACKJACK!");
        System.out.println("\n");
        System.out.println("Would you like to see the rules? (y/n)");
        String response = src1.nextLine();

        Scanner src2 = new Scanner(System.in);

        //if user enters "y" show the rules of Blackjack
        if (response.equalsIgnoreCase("y"))
        {
            System.out.println("\033[4;35mRULES OF BLACKJACK: \033[0m \n"
                    + "1. The player will play first, not the dealer\n"
                    + "2.\033[4;31m If the sum of the cards equal 21, then it is Blackjack and they win the game \033[0m \n"
                    + "3.\033[4;31m If the sum of the cards are over 21, then the player is busted and their opponent wins the game\033[0m\n"
                    + "4. Ace cards represent 1 or 11\n"
                    + "5. Jack, Queen and, King cards each represent 10 \n"
                    + "6. If the dealer goes bust or player wins, dealer will pay the player 1.5 times the player's bet to the player\n"
                    + "7.\033[4;34m“Hit me”\033[0m is a phrase used in Blackjack which means to pass the player another randomized card from the deck\n"
                    + "8.\033[4;34m“Stand”\033[0m is a phrase used in Blackjack which means that the player does not want to be passed another card from the deck ");

            System.out.println();
            System.out.println("\033[4;31m NOTE: The computer will decide whether an ACE card is equal to 1 or 11 based off the sum of your cards and your response.\033[0m");
            System.out.println(" Example: "
                    + "\n YOUR HAND: "
                    + "\n┌─────┌────────┐"
                    + "                                  "
                    + "\n│5 ♠ ·│ A ♣ ·  │"
                    + "                                  "
                    + "\n│ · · │· · · · │"
                    + "                                  "
                    + "\n│ · · │· · · · │"
                    + "                                  "
                    + "\n│ · · │· · · · │"
                    + "                                  "
                    + "\n└─────└────────┘ "
                    + "\033[4;31mTotal: 16 "
                    + "Ace = 11\033[0m");
            System.out.println("YOUR RESPONSE: Hit me");
            System.out.println("┌─────┌─────┌───────┐\n"
                    + "│5 ♠ ·│A ♣ ·│ 6 ♣ · │\n"
                    + "│ · · │ · · │· · · ·│\n"
                    + "│ · · │ · · │· · · ·│\n"
                    + "│ · · │ · · │· · · ·│\n"
                    + "└─────└─────└───────┘ "
                    + "\033[4;31mTotal: 12 "
                    + "Ace = 1\033[0m");
        }

        Scanner playerName1 = new Scanner(System.in);
        String player;

        System.out.println("\n");
        System.out.println("Enter player's name: ");
        player = playerName1.nextLine();

        //bet display
        Scanner betting1 = new Scanner(System.in);
        System.out.println(" ________  _______  _________   \n"
                + "|\\   __  \\|\\  ___ \\|\\___   ___\\ \n"
                + "\\ \\  \\|\\ /\\ \\   __/\\|___ \\  \\_| \n"
                + " \\ \\   __  \\ \\  \\_|/__  \\ \\  \\  \n"
                + "  \\ \\  \\|\\  \\ \\  \\_|\\ \\  \\ \\  \\ \n"
                + "   \\ \\_______\\ \\_______\\  \\ \\__\\\n"
                + "    \\|_______|\\|_______|   \\|__|\n"
                + "                                \n"
                + "                                \n"
                + "                                ");
        System.out.println("Bets range from $2 - $500.");
        System.out.println(player + "'s bet: ");
        int bet = betting1.nextInt();

        //if the users bet is not entered in correctly, allow the user to again enter in a bet and print the following message:
        if (bet <= 0 || bet > 500)
        {
            System.out.println("Sorry! That is not a correct bet! Please try again.");
            System.out.println(player + "'s bet: ");
            bet = betting1.nextInt();

        }

        //call play again method
        playAgain(player, bet);

    }

    //randomize the deck of cards with the values and suits. Ex: {"4D", "10S", "KH", ...}
    public static void createDeck()
    {
        String denominations = "0123456789JQK";
        String suits = "CSDH";

        int c = 0;

        for (int i = 0; i < denominations.length(); i++)
        {
            for (int j = 0; j < suits.length(); j++)
            {
                String val = denominations.substring(i, i + 1);
                String suit = suits.substring(j, j + 1);

                deck[c] = (val + suit);
                c++;

            }
        }
    }

    //fischer yates shuffle (see online)
    //using the fischer yates shuffle for each random permutation in the deck to be equally likely
    static void shuffle(String[] array)
    {
        int n = array.length;
        Random random = new Random();

        for (int i = 0; i < array.length; i++)
        {
            //get a random index of the array past the current index.
            //the argument is an exclusive bound.
            //it will not go past the array's end.
            int randomValue = i + random.nextInt(n - i);
            //swap the random element with the present element.
            String randomElement = array[randomValue];
            array[randomValue] = array[i];
            array[i] = randomElement;
        }
    }

    //update variable top to point to the top of the deck
    public static String dealCard()
    {
        String card = deck[top];
        top--;

        return card;
    }

    //at the beginning of the game, the dealer will deal two cards for themself and the player
    public static void dealTwoCard()
    {
        playerHand[0] = dealCard();
        playerHand[1] = dealCard();

        dealerHand[0] = dealCard();
        dealerHand[1] = dealCard();
    }

    //if a card contains either Jack, Queen, King or a 10 (0), they represent 10
    public static int getHandValue(String[] hand)
    {
        int total = 0;

        for (String card : hand)
        {
            String temp = getValue(card);
            switch (temp)
            {
                //0 represents the actual 10 number on a card
                case "0":
                case "J":
                case "Q":
                case "K":
                    total += 10;
                    break;
                //1 represents Ace
                case "1":

                    total += 11;

                    break;
                default:
                    total += new Integer(temp);
                    break;
            }
        }

        for (String card : hand)
        {
            String temp = getValue(card);
            if (temp.equals("1"))
            {
                if (total > 21)
                {
                    total -= 10;
                }
            }
        }

        return total;
    }

    //card example: "5S" also known as 5 Spades
    /*
    *   Return the string representation of the value of a card
    *   For example "5D" -> "5"
     */
    public static String getValue(String card)
    {

        return card.substring(0, 1);

    }

    //if a card contains "4C" the character C will be represented as Clubs
    /*
    *   Return the string representation of the suite of a card
    *   For example "5D" -> "D"
     */
    public static String getSuit(String card)
    {

        if (card.contains("C"))
        {
            return CLUBS;
        }
        else if (card.contains("D"))
        {
            return DIAMONDS;
        } 
        else if (card.contains("H"))
        {
            return HEARTS;
        }
        else
        {
            return SPADES;
        }
    }

    /*
    *  draws a string representation of the cards in either a player or dealer's hand
     */
    public static void displayHand(String[] hand, boolean isDealer)
    {
        // =============================================================
        //   ┌───── ┌───── ... will be repeated cards in hand ... ───┐
        // ============================================================

        //   Unicode representation of the pattern used to draw the top of the cards.
        String topStart = "\u250C\u2500\u2500\u2500\u2500\u2500";
        String topEnd = "\u2500\u2500\u2500\u2510";

        /* draw the top of the cards in a given hand */
        String top = "";
        for (String hand1 : hand)
        {
            top += topStart;
        }
        top += topEnd;

        //====================================================
        // draw card labels and suit for each card in the hand
        // │ 10 ♠   │
        //======================================================
        String values = "";
        for (int i = 0; i < hand.length; i++)
        {
            if ((isDealer == true) && i == 0)
            {
                values += "\u2502 · · ";
            } else
            {
                String cardValue = getValue(hand[i]);

                switch (cardValue)
                {
                    case "0":
                        values += "\u2502" + "10" + " " + getSuit(hand[i]) + " ";
                        break;
                    case "1":
                        values += "\u2502" + "A" + " " + getSuit(hand[i]) + "  ";
                        break;
                    default:
                        values += "\u2502" + getValue(hand[i]) + " " + getSuit(hand[i]) + "  ";
                        break;
                }
            }
        }
        values += "· ·\u2502";

        //=========================================
        // Draw body of card
        // │ · · · · │
        //========================================
        String body = "";
        for (int j = 0; j < 3; j++)
        {
            for (String hand1 : hand)
            {
                body += "\u2502 · · ";
            }
            body += "· ·\u2502\n";
        }

        // =============================================================================
        //   draw bottom of the cards in a player's hand
        //   └───── └───── ... will be repeated cards in hand times followed by ... ───┘
        // ============================================================================
        String bottomEnd = "\u2500\u2500\u2500\u2518";

        String bottom = "";
        for (String hand1 : hand)
        {
            /* Unicode representation of the pattern used to draw the bottom of the cards */
            bottom += "\u2514\u2500\u2500\u2500\u2500\u2500";
        }
        bottom += bottomEnd;

        String cards = top + "\n" + values + "\n" + body + bottom + "\n";

        System.out.print(cards);
    }

    //displays round for the player
    public static void displayRound(String player, int bet)
    {
        DecimalFormat money = new DecimalFormat("$0.00");
        System.out.println("_______________________________________________________________________________");
        System.out.println("_______________________________________________________________________________");

        System.out.println("  ____  _____    _    _     _____ ____  \n"
                + " |  _ \\| ____|  / \\  | |   | ____|  _ \\ \n"
                + " | | | |  _|   / _ \\ | |   |  _| | |_) |\n"
                + " | |_| | |___ / ___ \\| |___| |___|  _ < \n"
                + " |____/|_____/_/   \\_\\_____|_____|_| \\_\\\n"
                + "                                        ");

         String dealerScore = "_";
         
          if (round > 0)
        {
            dealerScore = Integer.toString(getHandValue(dealerHand));
        }
        //if the round is zero, make sure one of the dealer's cards is hidden from the player
        if (round == 0)
        {
            displayHand(dealerHand, true);
        }
        //else, if the round is not 0, make the dealer's card visible to the player
        else
        {
            displayHand(dealerHand, false);
        }
         System.out.println("DEALER: " + dealerScore + "\n"); 

        System.out.println(" __   _____  _   _ ____    _   _    _    _   _ ____  \n"
                + " \\ \\ / / _ \\| | | |  _ \\  | | | |  / \\  | \\ | |  _ \\ \n"
                + "  \\ V / | | | | | | |_) | | |_| | / _ \\ |  \\| | | | |\n"
                + "   | || |_| | |_| |  _ <  |  _  |/ ___ \\| |\\  | |_| |\n"
                + "   |_| \\___/ \\___/|_| \\_\\ |_| |_/_/   \\_\\_| \\_|____/ \n"
                + "                                                     ");
        displayHand(playerHand, false); 

        String playerScore = "";
        playerScore = Integer.toString(getHandValue(playerHand));
    
        System.out.println(player + ": " + playerScore);
        System.out.println("\n");
        System.out.println("\033[0mROUND: " + round + "\033[0m");      
        System.out.println("\033[4;31m" + player.toUpperCase() + "'s bet: ".toUpperCase() + money.format(bet) + "\033[0m");
    }

    //play the actual game (hit me or stand for the user)
    public static void play(String player, int bet)
    {
        DecimalFormat money = new DecimalFormat("$0.00");

        displayRound(player, bet);

        int playerScore = getHandValue(playerHand);

        while (!isBlackJack(playerScore) && !isBusted(playerScore))
        {
            System.out.print("(h) hit me / (s) stand ");
            char choice = console.next().charAt(0);

            //if the user enters "h" pass the user another card from the deck
            if (choice == 'h')
            {
                String card = dealCard();
                int size = playerHand.length;
                //copy the value from the players hand and increase the size by 1 (they are obtaining another card, therefore the size will increase)
                playerHand = Arrays.copyOf(playerHand, size + 1);
                playerHand[size] = card;

                round++;
                displayRound(player, bet);
                playerScore = getHandValue(playerHand);
                System.out.println("\u001B[0m" + player + "\u001B[34m" + " hits, score is: " + playerScore + "\033[0m");
                
            } 
            else
            {
                //if the user stays, no card will be passed to the user
                round++;
                System.out.println("\u001B[0m" + player + " stays, DEALERS turn.");
                break;

            }
        }
        //blackjack display
        if (isBlackJack(playerScore))
        {

            System.out.println("\n");
            System.out.println("\u001B[0m" + player + " WINS!\u001B[0m");
            System.out.println("\n");
            System.out.println("_______________________________________________________________________________");
            System.out.println("_______________________________________________________________________________");

            System.out.println("  ____  _        _    ____ _  __   _   _    ____ _  ___  \n"
                    + " | __ )| |      / \\  / ___| |/ /  | | / \\  / ___| |/ / | \n"
                    + " |  _ \\| |     / _ \\| |   | ' /_  | |/ _ \\| |   | ' /| | \n"
                    + " | |_) | |___ / ___ \\ |___| . \\ |_| / ___ \\ |___| . \\|_| \n"
                    + " |____/|_____/_/   \\_\\____|_|\\_\\___/_/   \\_\\____|_|\\_(_) \n"
                    + "                                                         ");

            System.out.println("_______________________________________________________________________________");
            System.out.println("_______________________________________________________________________________");

            System.out.println("\n");
            System.out.println("\u001B[32mDEALER pays " + money.format(bet * 1.5) + " to " + player + "\u001B[0m");

            return;
        } //busted display
        else if (isBusted(playerScore))
        {
            System.out.println("\n");
            System.out.println("\u001B[0m" + "Whoops! Sorry " + player + ", you got " + "\u001B[31m" + "BUSTED!\u001B[0m");
            System.out.println("\n");
            System.out.println("\u001B[0m" + "DEALER collects " + player + "'s bet of: " + money.format(bet));
            return;
        }

        //dealer's strategy
        int dealerScore = getHandValue(dealerHand);
        displayRound(player, bet);

        while (dealerScore < 17)
        {
            //if the sum of the dealer's cards are less than 17, the dealer will hit
            String card = dealCard();
            int size = dealerHand.length;
            dealerHand = Arrays.copyOf(dealerHand, size + 1);
            dealerHand[size] = card;

            displayRound(player, bet);
            dealerScore = getHandValue(dealerHand);
            System.out.println("\u001B[0m" + "DEALER" + "\u001B[34m" + " hits, score is: " + dealerScore);
        }

        if (dealerScore <= 21 && dealerScore > playerScore)
        {
            System.out.println("\n");
            System.out.println("\u001B[0m" + "DEALER WINS!");
            System.out.println("\u001B[0m" + "DEALER collects " + player + "'s bet of: " + money.format(bet));
        }
        else if (dealerScore <= 21 && dealerScore == playerScore)
        {
            System.out.println("  _____ ___ _____ _ \n"
                    + " |_   _|_ _| ____| |\n"
                    + "   | |  | ||  _| | |\n"
                    + "   | |  | || |___|_|\n"
                    + "   |_| |___|_____(_)\n"
                    + "                    ");
            System.out.println("\u001B[0m" + "DEALER does not collect " + player + "'s bet.");
        }
        else
        {
            System.out.println("\u001B[0m" + player + " WINS!\u001B[0m");
            System.out.println("\033[4;32mDEALER pays " + money.format(bet * 1.5) + " to " + player + "\u001B[0m");
        }
    }

    //if the sum of the player's cards are over 21, the player is busted
    public static boolean isBusted(int value)
    {
        return value > 21;
    }

    //if the sum of the players cards equal 21, they have won
    public static boolean isBlackJack(int value)
    {
        return value == 21;
    }

    public static void playAgain(String player, int bet)
    {

        boolean retry = true;

        createDeck();

        shuffle(deck);

        playerHand = new String[2];
        dealerHand = new String[2];

        dealTwoCard();

        round = 0;

        play(player, bet);

        while (retry)
        {
            Scanner ans2 = new Scanner(System.in);

            System.out.println("Would you like to play again? (y/n)");
            String ans = ans2.nextLine();

            if (ans.equalsIgnoreCase("y"))
            {
                Scanner betting2 = new Scanner(System.in);
                System.out.println(" ________  _______  _________   \n"
                        + "|\\   __  \\|\\  ___ \\|\\___   ___\\ \n"
                        + "\\ \\  \\|\\ /\\ \\   __/\\|___ \\  \\_| \n"
                        + " \\ \\   __  \\ \\  \\_|/__  \\ \\  \\  \n"
                        + "  \\ \\  \\|\\  \\ \\  \\_|\\ \\  \\ \\  \\ \n"
                        + "   \\ \\_______\\ \\_______\\  \\ \\__\\\n"
                        + "    \\|_______|\\|_______|   \\|__|\n"
                        + "                                \n"
                        + "                                \n"
                        + "                                ");
                System.out.println("Bets range from $2 - $500.");
                System.out.println(player + "'s bet: ");
                bet = betting2.nextInt();

                if (bet < 0 || bet > 500)
                {
                    System.out.println("Sorry! That is not a correct bet! Please try again.");
                    System.out.println(player + "'s bet: ");
                    bet = betting2.nextInt();
                }

                createDeck();

                shuffle(deck);

                playerHand = new String[2];
                dealerHand = new String[2];

                dealTwoCard();

                round = 0;

                play(player, bet);

            } 
            else
            {
                System.out.println(player + ", thank you for playing the Blackjack game!");
                System.exit(0);
            }

        }

    }

}
