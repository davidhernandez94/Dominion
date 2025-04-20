# Dominion
### Scenario
This project aims to create a virtual space where 2-4 users can sign in to an account or play as guests, and play the deck-building game **Dominion**. 
### Design Paradigm
Users play against each other in a *pass and play* format, so that players can be at the same device. 
### Expected Output
The user is able to play with commands directly into the terminal of the project. All output of the game are also in the terminal, using pure text to communicate with the program. 
### Hierarchies
* There is one large hierarchy structure where the absolute parent will be the `Card` abstract class, whose children are the `ActionCard`, `Treasure`, and `Vicory` abstract classes. The `ActionCard` class will contain up to 30 individual default classes. The `BasicTreasure` abstract class extends from `Treasure`; the default `Copper`, `Silver`, and `Gold` extend from `BasicTreasure`. `BasicVictory` extends `Victory`; `Estate`, `Duchy`, and `Province` are default classes that extend `BasicVictory`.
* The second hierarchy is for players. There is a `Player` abstract class, `GuestPlayer` and `AccountPlayer` extend from it. They are largely the same thing, except that `AccountPlayer` can keep track of its previous games and must log in to see access them and `GuestPlayer` plays as a guest, so does not log in.
### Interface
The interface `Countable` is a functional interface which counts the amount of 
