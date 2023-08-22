# On how to run
## frontend

### Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build   
```
## backend
```
npm install
```
>* Run traverplanner.sql in mysql terminal to add database in your PC
>* Modify trip/src/main/resources/application.properties file and add: spring.datasource.username=yourUserName, spring.datasource.password=yourDatabasePassword, and remove other similar lines
>* Run the main class with @SpringBootApplication annotation to run

# Dependencies

> ├── @vue/cli@5.0.8
> ├── corepack@0.10.0
> ├── nodemon@2.0.16
> └── npm@8.5.5


# Functionalities
Consists of 5 components which are navigation bar, jumbotron,card,carousel and footer .
Clicking on the items on the navigation bar will take you to specific pages(URL):
PLAN TRAVEL: ./choose1 -> ./choose2 -> ./combo -> ./home/plan -> ./payment
PROFILE: ./profile
Login: ./login

Register: ./register
* Login/register: User have to login to start planning, the form will not send to backend until all inputs are valid
* Profile: 
  * a little avatar in the right corner of the nav-bar will appear after login
  * click it to the profile page and user can modify their personal information in that page
  * form will also not be send to backend until all modified info are valid
* planning:
  * choose: select origin, destination, departure and return dates, budget level, and group size, only 3 cities are available so far(Syd, Mel, Bris)
  * choose travel style - attraction, art, food&drink, water sport
  * choose automatically arranged transport and accommodation plan or not.
  * generated plan: all arranged by day
  * place and order: input card info, comfirm to home page
    
  * The Explore Attractions button will take the user to the carousel component where top attractions in three amazing cities will be displayed
    with their name,image  as well as descriptions. Not only these , our generated travel plan for the user will also cover a range of other
    wonderful things to do in these cities with accommodation and transportation service provided if you would like to.

<!-- ## The home page

Consists of 5 components which are navigation bar, jumbotron,card,carousel and footer .

Clicking on the items on the navigation bar will take you to specific pages(URL):

PLAN TRAVEL: ./choose1 -> ./choose2 -> ./combo -> ./home/plan -> ./payment

PROFILE: ./profile

Login: ./login

Register: ./register




The Explore Attractions button will take the user to the carousel component where top attractions in three amazing cities will be displayed 
with their name,image  as well as descriptions. Not only these , our generated travel plan for the user will also cover a range of other
wonderful things to do in these cities with accommodation and transportation service provided if you would like to.

The card component  shows top 3 cities in Australia this application contains -Sydney,Melbourne and Brisbane. 
Iconic Scenery of each city is displayed on the top of the card with the the second half being the city brief.
Clicking on the Check Weather button at the bottom will generate a diagram that depicts the weather conditions in the next few days.

There are 2 links on the footer bar, by clicking which can take the user to the specific page which is application-based.



## Plan page:

Based on the parameters that has been selected by the user which involves startday, endday ,departure city,destination city,
group size, travel style,travel mode, a humanistic plan will be automatically generated .On this page, the user can clearly view 
potential accommodation, transportation and things to do. By clicking on the Proceed to Payment button at the bottom will take users 
to the payment page where they can view the total price of their travel plan and pay for it. -->






