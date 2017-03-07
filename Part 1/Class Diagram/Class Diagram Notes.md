# Class Diagram Notes

## The Classes

The class diagram consists of four main classes:

- __User:__ The user will do the majority of the operations involving the other classes and will allow for event creation and deletion aswell as sending, reciving and accepting invitations. The variables inside this class are protected as the class Admin will inherit from this class and an admin must have all the functionality of user and more.

- __Event:__ The event class will store the details for a single event. Information such as dates, times and locations will be stored in this class through the use of the database.

- __EventRequest:__ The Event Request class allows the User class to send out requests specifying the event being invited to as well as the recipient to the request.

- __Admin:__ The administrator Class is a user with special privilages which allow for the creation and removal of new and existing accounts. As the Admin class will extend the User class, it will have all the functionality of a User plus more. This authorization will only be given to a select few. 

## The Relationships

- __User - Event:__ The relationship between these classes is composition, as an event can no longer exist without the host who created it. The multiplicity is one-to-many, as the user can create many events.

- __User - EventRequest:__ The relationship between these classes is aggregation, as even if the user who send the request is deleted, the request can still exist as it links directly to the event. The multiplicity is one-to-many as a user can send many requests.

- __Event - EventRequests:__ The relationship between these classes is composition, as if the event a request points to is removed, any requests relating to it must also be removed. The multiplicity is one-to-many as an event can have many event requests.

- __User - Admin:__ The relationship between these classes is generalisation, as the class Admin inherits from the class User. This is to ensure the class Admin has all the functionality of a User class aswell as further behaviour such as being able to create accounts.
