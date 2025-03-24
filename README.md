# COMP3717 Will Otterbein Mini App
Will Otterbein, 2025

This document describes the application idea and design decisions.

---
## Idea: Elden Ring Equipment Quick Reference
Elden Ring is a big game, a huge game -- some say one of the biggest games.

There are a lot of items / armour / weapons / spells and incantations to choose from
when playing through the game, and frankly it can be cumbersome to pull open a bunch of
wiki pages to find info about a specific weapons level requirements or stats.

Hence: **EldenRingQuickRef** -- title to be refined -- a quick reference for elden ring=
equipment.
- Uses API to get information about game equipment
	- RESTful component of the assignment
- Provide the user w/ ability to query wrt to specific equipment
- Provide the user w/ bookmarking capabilities -- save items to local storage w/ tags.
	- Search by tags
	- Room component of the assignment
- Should be snappy -- that is to say quick and easy.
	- Room component also -- cache API responses

---
## Part 1: App structure
The following section outlines my intentions for the structure of the application.

My idea for the structure of the application can be summarised in the following diagram

![Elden Ring Equipment Quick Ref](./.gitresources/AppIdeaDiagram.png)

- There are four pages
	- Home page, users can search for equipment and save certain API res data to local storage.
		- User refine the search based of equipment type
		- Users save equipment items based off the equipment type
		- Local storage is organized based off the type that the user was searching for
	- Saved page, presents the user w/ the categories where they have stored items
		- Users can query their saved equipment items based off tags
	- Info page, present the information of the item in depth
		- Structure the api response data in a nice manner
		- Icon of the equipment

---
## Part 2: APIs Exploration
To be honest, the idea for this app is derived from an API that I found.

[Elden Ring Fan API](https://docs.eldenring.fanapis.com/docs)

The above mentioned provides endpoints for querying wrt to
- Locations
- Items
- Weapons / Armour / Spells / Incantations 
- Enemies
- Creatures

The scope of the application could be to provide the user w/ quick access
to information regarding any of the above, however I thought it best to start
w/ a more restricted subset of the games content and go from there -- hence weapons/armour/spells/incantations.

---
## Part 3: Room Database Setup
More details about the Room local database.

...  ...