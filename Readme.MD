Project Title : Shopping checkout with promotions

Project type: Maven project

Project design description:
1. The Checkout class contains the list of Promotions and shopping basket. It calculates the total price of the items by applying all promotions on the content of the shopping basket
2. The shopping basket contains the list of the check-out items and their total price
3. The Product encapsulates the product information
4. The CheckoutItem is a class that represents the products when its checked-out. The price of the item can be changed after discount is applied, without affecting the original price of the original Product
5. The Promotion interface is implemented by the specific promotion. Each implementation of the promotion would implement the void applyOffer(ShoppingBasket basket) method and apply the promotion on the items in the shopping basket and update the total price.
6. For any new promotion required, a new implementation of the Promotion interface can be added to the Checkout by invoking the addPromotionalRule(Promotion promotionalRule) method. The order of the promotion applied is managed by the order they are added to the Checkout promotionRules list.

Running the test :

Open the project in eclipse/IntelliJ as a maven project
Run the test: The junit tests are in the src/test/java folder. .


Authors Tanveer Rameez Ali (tanveerrameez@yahoo.com)
