# Dominion
 ### Scenario
 This project aims to create a virtual space where 2-4 users can sign in to an account or play as guests, and play the beloved deck-building game **Dominion**. 
 ### Design Paradigm
 Users play against each other in a *pass and play* format, so that players can be at the same device. The **Dominion** rulebook is very long and complicated, so here is a link to the rules: 
 ##### *https://www.riograndegames.com/wp-content/uploads/2016/09/Dominion2nd.pdf*
 ### Expected Output
 The user is able to play with commands directly into the terminal of the project. All output of the game are also in the terminal, using pure text to communicate with the program. 
 ### Hierarchies
 * There is one large hierarchy structure where the absolute parent is the `Card` abstract class, whose children are the `ActionCard`, `Treasure`, and `Vicory` abstract classes. The `ActionCard` class will contain up to 30 individual default classes. The `BasicTreasure` abstract class extends from `Treasure`; the default classes `Copper`, `Silver`, and `Gold` extend from `BasicTreasure`. `BasicVictory` extends from `Victory`; `Estate`, `Duchy`, and `Province` are default classes that extend from `BasicVictory`. Also, the `Curse` default class extends from `Card`. 
 * There is one large hierarchy structure where the absolute parent is the `Card` abstract class, whose children are the `Action`, `Treasure`, and `Vicory` abstract classes. The `Action` class will contain 25 individual default classes. The `BasicTreasure` abstract class extends from `Treasure`; the default classes `Copper`, `Silver`, and `Gold` extend from `BasicTreasure`. `BasicVictory` extends from `Victory`; `Estate`, `Duchy`, and `Province` are default classes that extend from `BasicVictory`. Also, the `Curse` default class extends from `Card`. The reason why there exist `BasicTreasure` and `BasicVictory` is because in certain expansions, there are some treasures and victory cards that are not basic and demand more complicated code, similar to action cards. 
 * The second hierarchy is for players. There is a `Player` abstract class, `GuestPlayer` and `AccountPlayer` extend from it. They are largely the same thing, except that `AccountPlayer` can keep track of its previous games and must log in to access them and `GuestPlayer` plays as a guest, so does not log in.
 ### Interface
 * An interface used in the project is `Attackable`. This does not mean the card can be attacked, but that it can attack another player. This is a funcitonal interface and the only method is `attack(Player player, Players players, Supply supply, Trash trash)`.
 * Also, there is a card called `Moat` which is not only an ActionCard, but also a reaction card, so it will implement the `Reactable` interface. However, it is difficult to program so might be omitted from the project. Cards that implement this fucntional interface can be used on another player's turns to play a specific action or prevent an opponent from playing one. Similar card types appear in expansions to the game, such as heirlooms, travellers, and spirits. 
 ### Interfaces
 * An interface used in the project is `Attackable`. This does not mean the card can be attacked, but that it can attack another player. This is a funcitonal interface and the only method is `attack(Player player, List<Player> players, Supply supply, Trash trash)`.
 * Also, there is a `Action` child class called `Moat` which is not only an action card, but also a reaction card, so it will implement the `Reactable` interface. However, it is difficult to program so might be omitted from the project. Cards that implement this fucntional interface can be used on another player's turn to play a specific action or prevent an opponent from playing one. Other cards are also `Reactable` in **Dominion** expansions. 
 ### Runtime-polymorphism
 * The deck, hand, and discard of players are lists of `Card`s even though `Card` is an abstract method. All the cards in those lists are being upcasted into `Card`s.
 * If a player, during the game, wants to see the description of a card, its `toString()` method is called on a `Card` object. However, the method called is from the specific kind of card, so the `toString()` is overridden by its subclass.
 * If a player, during the game, wants to see the description of a card, its `toString()` method is called on a `Card` object. However, the method called is from the specific subclass, so the `toString()` is overridden by its subclass.
 ### TextIO
 The `AccountPlayer` has login information inside a `.csv` file along with how many games it has won and lost. 
 ### Comparable and Comparator
 * **Comparable:** The players will be in order of when they play (player 1, player 2, etc) and when listed, it will always be this way when ordering players.
 * **Comparator** Card can have multiple ways of being sorted. When in a player's hand, it is random; when given the choice to buy a card, it is by price; when showing the supply, it is by alphabetical order of the name of the cards.
 ### Class and Interface Diagram
 
 
 * **Comparable:** The players are compared in the order of when they play (player 1, player 2, etc) and when listed, it will always be this way when ordering players.
 * **Comparator** `Card` can have multiple ways of being sorted. When in a player's hand, it is random; when given the choice to buy a card, it is by price; when showing the supply, it is by alphabetical order of the name of the cards.
 ### Class Diagram
 ![class diagram](https://github.com/user-attachments/assets/6ba90b89-5b47-423a-be6f-5cba2fc9d048)
 ### Coming Soon
 Deliverable 2 will include the code for the `Supply`, `Trash`, `Player` (with at least one of its subclasses), `Card` (with some of its subclasses but not all), and the very beginning of `Game`. 
