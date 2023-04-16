# HealthMate 
## "Your Reliable Fitness Partner"

> Pages 
>> 1. Calories Page
>>> - A circualr progress bar to track calorie intake for the day.
>>> - Displays the different meals logged.
>>> - Progress indicators for Protein, Fats, Carbs as visual indication of consumption levels 

>> 2. Trends Page
>>> - Integrated Google Fitness API that retrives data from Google Fitness and displays it in a SQL table. 
>>> - Displays the total Step Count, Distance, Calories Burnt for the day with the click of 'Load Data' button. 
>>> - Allows users to sign out and sign into a different Google Account with the click of 'Sign Out'. 
>>> Allows users to clear history in SQL table with 'Clear History'. 

>> 3.  A Floating '+' button
>>> - Allows for adding a new meal by manually keying in the nutritonal content of the food, which will then be stored in the 'Calorie Page'. 
>>> - Allows for tracking a run which will bring up the Google Maps, and the data logged during the run will be automatically stored on Google Fitness, which will then be retrieved from our app. 
>>> - Allows for creation of a new post which can then be shared to Instagram. 

>> 3. Goals Page 
>>> - This is where a user updates their particulars, like name, sex, weight, height, calorie intake goals and workout goals. 
>>> - With the click of 'Update Data', the calorie progress bar in the 'Calorie Page' will be updated to reflect the new calorie intake goal. 
>>> - With the click of 'Suggest Goals', the app will suggest the recommended calorie intake and the workout goal based on your weight and height. 

>>4. Social Page 
>>> Has a 'For You' and 'Following' page. 
>>> 'For You' page shows the posts created by other users of HealthMate app 
>>> Upon creation of a new post, users are given the option to select an existing image from their gallery, or to take a new image to accompany their post. 
>>> With the click of the 'share' icon, users can share their post to Instagram, with the activity name autoamtically copied to the clipboard for convenience. 

## README: HealthMate

> HealthMate is a mobile application designed to help users maintain a healthy lifestyle by tracking their fitness activities and encouraging social engagement with other fitness enthusiasts. The app integrates with Google Fit to access users' fitness data and provides a social platform for sharing workout achievements, tips, and encouragement.

> Features
>> - Calorie Tracker: Allows users to log their meals and calculate their daily calorie intake. 
>> - Exercise Tracker: Allows users to track their runs using Google Maps integration for seamless tracking of fitness activities. 
>> - Trends: Displays various metric related to the user’s fitness history using data retrieved from Gogole Fitness. 
>> Social: Encourages users to connect with friends and share their progress, fostering a supportive fitness community  

> Getting Started
These instructions will help you set up the project on your local machine for development and testing purposes.
>> Prerequisites
>>> - Android Studio 3.0 or later
>>> - Android SDK with API level 26 or higher
>>> - JDK 8
>>> - A Physical Android device or an emulator for testing 

>> Installation
- Clone the repository to your local machine.
- git clone https://github.com/your_username/HealthMate.git
- Open the project in Android Studio.
- Sync the project with Gradle files by clicking on File -> Sync Project with Gradle Files.
- Build the project by selecting “Build” > “Make Project” in the Android Studio toolbar. 
- Run the project on an Android device or emulator.

> Usage
- Upon launching the app, sign in with your Google account to sync your fitness data with Google Fit.
- Calorie Tracker: Navigate to the “Calorie” tab to view and manage your daily meals. Tap the “+” button to add a new meal, input the meal details, and save your entry. 
- Exercise Tracker: Switch to the “Track a run” tab under the '+' icon to track your exercises. Click on the map a location you want to run to. Press the “Track a run” button to open up Google Maps which will display the most feasible running route. 
- Trends: Go to the “Trends” tab to view a summary of your fitness progress over time. The app displays various metrics, such as Steps Count, Distance clocked, and calories burnt. 
- Explore the For You section to discover posts from other users in the HealthMate community. Follow other users to stay updated with their fitness activities in the Following section.
- Create and share your own fitness posts with details like activity name, distance, and time. 
- Update your user profile with information such as name, weight, sex and height, which the app uses to recommend calorie intake and workout goal. 


Acknowledgements
We would like to express our gratitude to the following individuals and organizations for their support and contributions to the development of the Fitness Tracker App:
- Google Maps API 
- Google Fitness API 

Please note that this document serves as an overview of the Fitness Tracker App project, and may not cover every aspect of the codebase. For more detailed information on specific functions or components, refer to the source code comments and documentation within the project repository.




