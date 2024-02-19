@tag
Feature: Purchase order from Ecommerce website

    Background: I landed on Ecommerce page

    @tag2
    Scenario Outline: Positive Test of Submitting the order
        Given Logged in with email <email> and password <password>
        When I add product <productName> to Cart
        And Checkout <productName> and submit the order
        Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation Page
        Examples:
            | email                       | password  | productName     | status  |
            | moldosheripovbaya@gmail.com | R0hul_bek | ADIDAS ORIGINAL | success |
            | moldosheripovbaya@gmail.com | R0hul_bek | ZARA COAT 3     | success |
            | moldosheripovbaya@gmail.com | R0hul_bek | ZARA COAT 57    | fail    |
            | moldosheripovbaya@gmail.com | R0hul_b   | SAMSUNGROGPHONE | fail    |