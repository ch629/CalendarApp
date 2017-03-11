# Model View Control Design Notes

The Model-View-Controller design application consists several main aspects. It consists of:

- __The View classes__

Every view in the application will have its own View class. This class handles the updating and gathering of information through the user interface.

- __The Controller Classes__

Each respective View has its own Controller. This controller class handles communicating the information from the View to the Model class.

- __The Model Class__

The Model class is where all the respective information will be stored and all logical calculations will take place. This model is shared amongst all controller classes, but its possible that this large Model class will be split up into smaller individual model classes.

- __The Server__

The Server will handle communications between the database and the Model class.

- __The Database__

The database is where all the data in the application will be stored using appropriate encyrption and hashing depending on the confidentiality of the data stored.

Each view has its own View and Controller class. Each view communicates with its respective controller. Each controller communicates with a model class which is shared amongst all controllers. It is possible that this larger model class may be split up into several individual model classes. This model is wher


# Model View Controller Explanation, Rationale, Benefits and Disadvantages

The Model-View-Controller is a design pattern which focuses on providing a versatile user interface. It seperates internal representations of information from the parts of the program which present this information to the user. This section will describe our rationale behind the decision to use the MVC. It will also outline how the use of the MVC design pattern within our application will offer benefits not only to the user, but also to the developers whilst creating the application. Despite this however, its usage also comes with a few disadvantages. These will also be outlined and discussed below.

## Rationale
The rationale for the use of the MVC pattern within our application is formed from our analysis of the requirements. We needed a design pattern that is both versatile and maintainable whilst remaining uncomplicated to allow other development teams to extend the project in the future. The project needs to be able to perform the basic tasks of a calendar system such as modifying views and updating data. The Model-View-Controller pattern provides the tools to do this in an efficent manner through its relatively simple framework.

## Benefits
The MVC design pattern comes with a variety of benefits, such as:

- __Seperation of Logic and View__

The seperate Model and View classes will allow the code for the logic to be seperated from the code for the views. This is useful as it promotes good code organisation, making it easier to pinpoint where problems occur and as a result bugs easier to fix. It also allows new features to be implemented more efficiently as well as existing features to be modified effectively.

- __Simultanenous Views__

The nature of the MVC's specified encapsulation allows the program to display multiple views based off the same information. This will be useful when we implement the different types of calendars such as the Daily, Monthly and Yearly views.

- __Parallel Development__

The loose coupling provided by the MVC pattern will allow seperate members of the development team to be working on different aspects of the application at the same time. For example, One member could be working on the logic within the model whilst another on the physical representation of the view to the user. This is an advantage when considering both development speed and team-member co-operation.

- __Strong Cohesion__

Whilst the code regarding different aspects of the application will remain seperate in different classes, the pattern relies on relationships between these classes, giving the application an essense of strong cohesion. An example of this is how the controller will communicate between the model and the view to perform actions.

## Disadvantages
Whilst the MVC pattern comes with a variety of benefits, it also has several disadvantages. These include:

- __Keeping things Consistent__

As we will be splitting code in to several different classes instead of keeping it form within one class, it will require the maintaining of several classes at once. This can be dangerous as forgetting to update one of these classes could cause bugs and other unforseen problems. This can be avoided by careful planning and implementation.

- __Complexity of Framework and Navigation Within Code__

Whilst the MVC provides good organisation and encapsulation, it will require us to adapt the planning steps we made during the planning phase to function correctly with the MVC. This adds new layers of abstraction to the process, making it more complex and difficult to navigate..

