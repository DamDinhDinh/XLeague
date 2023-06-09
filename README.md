# XLeague

- Used Clean Architecture + MVVM for separating code base, de-coupling module, and reducing the effort to change and maintain. Easy to implement DI for Unit Test the functions.
- Used Hilt for DI which is designed for Android applications. It builds dependency graphs at compile time so I will know what's wrong while compiling the app.
- UI design and implementation with Jetpack compose and Material Theme because I want to apply a recent technology for the app. Also, the Jetpack Compose is more maintainable, changeable, and reusable.
- Used Retrofit for performing networking
- Used Mockito for creating mock instance functions for testing
- Used Faker for creating mock objects for testing
- Used Junit for testing
- Used Coroutines for performing asynchronous
- Used Exoplayer for playing highlight video
- Used AlarmManager and BroadcastReceiver for schedule match starting notifications
- Change to use vertical lists for devices have screen width > 600 DP
- ...
- Demo video: https://drive.google.com/file/d/19D-9Fn_VTYBmoXS8DLX9VLa8kTrrT0Cj/view?usp=sharing
- Notification demo in the video scheduled for notify after 30s
- If I have more time, I will implement a cache offline app by Room database. Then refactor the UI/UX. Create a leaderboard feature by counting won matches. After 3 days of creating this sample app, this is a good result for me. I got some new aspects of app development and also reviewed my knowledge.

Functional Requirements:
- Ability to show all participating teams. -> Landing screen shows a horizontal team list. Click **Team List** label to navigate to the fullscreen all participating teams
- Ability to show all previous and upcoming matches. -> Landing screen shows a vertical match list. Click **Match List** to navigate to the fullscreen all matches
- Ability for a user to select a team and filter matches per team. -> Landing screen -> Click **Team Item** in the horizontal **Team List** -> navigate to **Match Of Team Screen**
- Watch previous match highlights. -> Click on **Previous Match Item**
- Users can set a reminder for an upcoming match. -> Click on **Upcoming Match Item**
- Notify the user when the match is about to start. -> Click on **Upcoming Match Item**


Coding Requirements
- Application should be written using Kotlin Programming Language -> Meet the requirement
- UI Design must follow Android’s Material Design Guidelines -> Using compose UI with Material Theme. I'm not good at self-design UI/UX app
- Only use appropriate libraries needed based on the functional requirements -> Meet the requirement
- Integrate unit tests on the project -> Meet the requirement
- Support for Tablet design (Bonus) -> Change to use vertical lists for devices have screen width > 600 DP
- Integrate UI test on the project (Bonus) -> X



Build an Android App for a hypothetical sports event.


Functional Requirements:


Ability to show all participating teams.
Ability to show all previous and upcoming matches.
Ability for a user to select a team and filter matches per team.
Watch previous match highlights.
Users can set a reminder for an upcoming match.
Notify the user when the match is about to start.

Coding Requirements

Application should be written using Kotlin Programming Language
UI Design must follow Android’s Material Design Guidelines
Only use appropriate libraries needed based on the functional requirements
Integrate unit tests on the project
Support for Tablet design (Bonus)
Integrate UI test on the project (Bonus)

General Requirements and guidelines:


In the application, create a README file and answer the following questions:
Describe the approach you’ve taken (Architecture, frameworks or libraries used) and explain why you’ve selected it for the sample Android application.

List down any functionalities or technical details that you wanted to add if you had additional time.

Use the following endpoints below for getting the data:

URL

Description

https://jmde6xvjr4.execute-api.us-east-1.amazonaws.com/teams


Provides the list of participating teams

https://jmde6xvjr4.execute-api.us-east-1.amazonaws.com/teams/matches


Provides the list of matches

https://jmde6xvjr4.execute-api.us-east-1.amazonaws.com/teams/{id}/matches


Provides the list of match per team
