# springbootecommerce
spring boot java ecommerce backend

ecommerce website backend with spring boot, spring security, spring data mongoDB.

functionality:
1.Signup:
 	User can sign up for a new account with an email address as username and complex password (Contains upper- & lower-case characters and at least one number and one symbol).
2.Authentication:
	Use spring security with basic authentication. User can log in with existing username and password.
3.Authorization:
	employee can update/add/delete the products where normal customer can only get the product information.
4.shopping cart/create order:
  user can add products to shopping cart and delete items from shopping cart. After user input the address/payment information and click check out, an order will be created if all shopping cart items is in stock.
	Shopping cart items will be stored in database, so every user will be able to save their shopping cart items and retrieve them later.

this is Application UML diagram
<img src="https://user-images.githubusercontent.com/79245472/116330875-4aa7d700-a783-11eb-8cb8-409cb472deb6.png" width="90%"></img> 

this is add to cart Activity diagram 
<img src="https://user-images.githubusercontent.com/79245472/116330891-572c2f80-a783-11eb-9a9e-c7f3e05f16e1.png" width="90%"></img> 

this is place order activity diagram
<img src="https://user-images.githubusercontent.com/79245472/116330894-58f5f300-a783-11eb-9d22-179454f18010.png" width="90%"></img> 
