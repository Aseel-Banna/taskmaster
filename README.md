# taskmaster

This is an Android app related to tasks.

In this app, we have three activities:
* Main Activity: it has two buttons( Add Task and All Tasks) to allow the user moving throw other activities<br/>
For lab 29, we used Room to add data to database and show them in RecyclerView to view the tasks with different titles, bodies and state. Once the user clicks on the task item, it will start the details activity.<br/>
Also, there is a title for the tasks buttons that is related to the user name.<br/>
![image description](screenshots/homepage.PNG)<br/>
* Add Task: it allows the user to add a title for the task, the body and the state of it. Once the user click on submit button, a ***Submitted!*** text view will be shown to the user.<br/>
![image description](screenshots/addtask.PNG)<br/>
* All Tasks: for now, it has only an image.<br/>
![image description](screenshots/alltasks.PNG)<br/>
* Settings: Allows the user to insert his/her name to show it in the home page.<br/>
![image description](screenshots/setings.PNG)<br/>
* Details: By default, it has no description for tasks, but once the user click on the task item in the home page and get to the details activity, a title related to the task, body and the state of the task will be displayed.<br/>
![image description](screenshots/details.PNG)<br/>
* Sign Up: Allow the user to sign up using AWS.<br/>
![image description](screenshots/signup.PNG)<br/>
* Confirm: Allow the user to enter the verification code to verify user's email.<br/>
![image description](screenshots/confirm.PNG)<br/>


## Lab 32
For lab 32, we have used AWS amplify to create DynamoDB, and it is working fine and saving data in DynamoDB.


## Lab 36
For lab 36, we used AWS authentication to enable the user create a profile or sign up and confirm his/her email.  

## Lab 37 
For lab 37, we added AWS Storage S3 to enable the user upload file.

## Lab 38 
For lab 38, we added the notification service using firebase.

## Lab 41
For lab 41, we added the ability to files to be sharable with our app.


## Test
For testing hte app, we have used Espresso Test with implementing the dependencies in build.gradle file.

* onView(withId(R.id.ID)): This is used to check if the component is exist in the activity or not.
* onView(withId(R.id.ID)).perform(click()): This is used to check if the component is clickable such as buttons, recyclerViews and items in recyclerViews.
* onView(withId(R.id.ID)).check(matches(withText("Test"))): This is used to check if the text of the component is equal to "Test".
* onView(withId(R.id.ID)).check(matches(isDisplayed())): This is used to check if the component is displayed or not.

