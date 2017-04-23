# Cloud Assignment Hadoop app

## Description
This app is a web chat where multiple clients can connect.
When a new member connects, it is broadcast to all the others members.
A message is seen by everyone connected.

All the logs of discussion are saved with timestamps in the myOutput.txt file.
Later on, a Map Reduce job can be run by pressing the "Compute stats! " button.

## Installation 
Node.Js and npm are required.
Once done, go to the repository and run "npm install"

## File manifest
.
├── app.js
├── index.html
├── myOutput.txt
├── package.json
├── topNwords.jar
└── views
    └── homepage.ejs

## Credits
Jennifer
John
Anjana
Thomas
Openclassrooms.com for the Node.js tutorial

## Future updates
Retrieve the output data of the jar file and display it on the page.