# Ajirayaan
Ajira Virtual Hiring Hackathon
# Note:
I have deployed this application in heroku. So, you can test all the end points with out building it in your local system.

I have used postgresql(which is free in heroku) to store data.(Sorry, its my mistake. I didn't see guidelines properly)

GET https://ajirayaan.herokuapp.com/api/environment/ping - To make service active

GET https://ajirayaan.herokuapp.com/api/environment/flush - To delete, in case new configurations to upload

GET https://ajirayaan.herokuapp.com/api/rover/ping - To delete, in case new configurations to upload

POST https://ajirayaan.herokuapp.com/api/environment/configure - To configure environment

POST https://ajirayaan.herokuapp.com/api/rover/configure - To configure rover

GET https://ajirayaan.herokuapp.com/api/rover/status - To get status

POST https://ajirayaan.herokuapp.com/api/rover/move - To move

PATCH https://ajirayaan.herokuapp.com/api/environment - To update environment

# How to run in local system through git clone

1. Clone this project in your local system from https://github.com/arun0110/ajirayaan.git (Branch: master)

2. Import into IntelliJ (used Gradle)

3. Go to \src\main\resources\application.properties, and change datasource values according to your local database settings.

4. Build and Run

5. Configure Environment

6. Configure Rover

7. Test according to your test case

# How to run in local system from zip file

1. Extract this project from zip

Do the same as above from point 2
