# classattendanceappv02

Funtionality:

This app allows lecturers/admins to create modules, which in turn contain two lectures. Students can then sign into said lectures, confirming they watched the lecture in person, streamed live or watched a recording.
If the student attedned the class in person, then their current location is logged to ensure they are being truthful and to help with Covid contact tracing.
For the other two options, location is not logged to protect the students' privacy.
Admins can then see the attendance for the module.
If they click into an individual sign in, they will be brought to a fragment that shows a map with the student's location and details of their sign in, including the time of logging.

Admins can also assign notifications to lectures, which will be visible beneath the lecture details in red.
This is to inform students if a lecture has been cancelled, etc.
Admins can also create and delete modules.

All users can edit their details, including name and student number.

Technology Used:

For persistence, I used Firebase Realtime Database.
Separate Schemas where used for modules, sign ins, and users.
Lectures are contained as a list within modules.

Google Maps API and Location Services used to track current location of a student.

For authentication, Firebase Auth has been used.

UX/DX Approach:

An MVVM approach was used with a Nav Drawer Menu for navigation.

Git Approach:

Standard commits from terminal

Personal Statement:

I confirm that all work is my own, based on what was taught during the module. Any additional research to be included as references in the submission template.

