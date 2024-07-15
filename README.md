# DND-Tools
A collection of tools a Game Master (GM) might use in a TTRPG.

# Description

This is CRUD App, which allows the target audience of a Game Master to create, read, update, and display data about Campaigns and One-Shots. As well as give access to a series of adventure-specific tools to aid Game Masters in TTRPGs (tabletop roleplaying games). Currently has several screens: Intro, Add, Selection, and a few other Character and Adventure-specific screens.

---
## Intro Screen

- Displays a filterable list of current adventures in a database, and buttons to add a new Campaign or One-Shot.
- UI distinction between the type of adventure - either Campaign or One-Shot

<div style="display: flex; justify-content: space-between;">
<img src="https://github.com/user-attachments/assets/c43314f6-3da8-417e-8568-9c89adb6a8ff" style="width: 48%;"/>
<img src="https://github.com/user-attachments/assets/45bd850c-7fc2-41b3-b072-3ccbf7c63455" style="width: 48%;"/>
</div>

## Add Screen

- Displays `textfields` with appropriate labels
- Uses Dao functions to add and update data in the app's Room Database

<div style="display: flex; justify-content: space-between;">
<img src="https://github.com/user-attachments/assets/3047cb6d-b339-4eea-b155-73d4c67ce7e2" style="width: 48%;"/>
<img src="https://github.com/user-attachments/assets/c3b3c323-28db-4233-9cda-a088b956a52e" style="width: 48%;"/>
</div>

## Selection Screen

- Displays a "toolkit" for use regarding a specific adventure
  
<div style="display: flex; justify-content: space-between;">
<img src="https://github.com/user-attachments/assets/9a4fa780-1b83-444d-9a08-73929bcb5397" style="width: 48%;"/>
</div>

  - **Character Names** allows the user to save and store character names in a table linked to the adventure's ID via a `Foreign Key`
  - **Player Info** allows the user to add details about character stats in another table linked to the adventure's ID via a `Foreign Key`
  - **Initiative Tracker** allows users to create an order of turns for the established characters, user-entered number of enemies, and non-player characters. 

    <div style="display: flex; justify-content: space-between;">
    <img src="https://github.com/user-attachments/assets/9a4fa780-1b83-444d-9a08-73929bcb5397" style="width: 48%;"/>
    <img src="https://github.com/user-attachments/assets/29bd4c7c-335d-4b11-a596-a3b24b55ae2a" style="width: 48%;"/>
    </div>

  - **Edit Info** enables the user to edit information about the adventure by using the update Dao function in the Room Database
  - **Delete** enables the user to delete an adventure by using the delete Dao function in the Room Database
  - **Add Notes, View Notes & Schedule Sessions** are currently unavailable screens

# Features

- **Compose Navigation**
- **MVVM Design Pattern**
- **Sorting Behaviour**
- **Alert Dialogs**
- **Room Database**
  - Multiple DAOs
  - Use of Type Converters to allow adventure type to be stored in the database
  - Use of Foreign Keys to link related data to a specific adventure
