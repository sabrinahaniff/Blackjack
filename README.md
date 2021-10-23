# Blackjack

**ASCII Art (Text Generator)**:
https://patorjk.com/software/taag/#p=display&f=Graffiti&t=Type%20Something%20

**ASCII Card Design Inspiration (NOTE: I did not copy the code!):**
https://github.com/damn1/ascii-blackjack

**RULES OF BLACKJACK**
1. The player will play first, not the dealer
2. Each player places a bet before dealing
3. Bets will range from $2 to $500
4. Once the sum of someone’s card reaches 21, they have won
5. If the sum of the player’s cards are over 21, then the player is busted
6. Ace cards represent 1 or 11
7. The dealer will collect your bet if you go bust or the sum of your cards are lower than the
sum of the dealer’s cards
8. If the player wins against the dealer, the dealer will pay 1.5 more of the players bet to the
player
9. Jack, Queen and, King cards each represents 10
10. “Hit me” is a phrase used in Blackjack which means to pass the player another
randomized card from the deck
11. “Stand” is a phrase used in Blackjack which means that the player does not want to be
passed another card from the deck

**OVERVIEW**

Creating the Blackjack game will include Blackjack’s basic rules.
These rules will be added in the game but it is not mandatory for the user to read them before
playing the game. The user can respond with either “y” to see the rules (if the user does not
know how to play Blackjack) or “n” if the user does know how to play Blackjack.
The Blackjack application will ask for the player’s name and then use it for personalized
messages.

Once a player’s name is chosen, the dealer(computer) will shuffle the deck of cards (note: the
joker cards will NOT be included) and pass two randomized cards to the player. The dealer will
also deal two randomized cards for themself which will include one card facing up and one card
facing down. However, this will not be the same for the player (the player will be dealt two cards
facing up).

The player gets the first move and can check both of their cards determining whether to say
“stand” or “hit me”.
If the player were to “stand”, the dealer will first flip their hidden card over to make it visible and
then would either hit or stand.
The game will continue once the sum of someone’s cards equals 21. If the sum of someone’s
cards is equal to 21 then they have won. Likewise, if they are above 21, then they are busted and
their opponent wins. If the sum of everyone’s cards does not equal 21, the sum of their cards will
be compared. Whoever has a higher sum will win.
