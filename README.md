# Bootleg Bird


A Java remake of the famous mobile game Flappy Bird, as a school project.


## Introduction

Assets used in this project was ripped from the original game.

## Technologies

 - Java 8
 - libGDX 1.11.0 framework

## Setup

### Download

If you are new to GitHub (or any Version Control System in general), don't worry! It might look confusing, but we'll get there pretty quickly!

The simplest way to download is to go to the Releases page, then click on **Source code (zip)** for Windows users or **Source code (tar.gz)** for Linux users.
 
### Import

Before importing the project, make sure your IDE has Gradle build system installed.

 - IntelliJ IDEA/Android Studio: no need to do anything.
 - Eclipse: follow [this tutorial](https://www.digitalocean.com/community/tutorials/gradle-eclipse-plugin-tutorial).
 - NetBeans: Version 8.2 does not seem to have Gradle plug-in, so it is recommended to use NetBeans 11 or later.

To import this project into your IDE, select File > Open Project, then select the folder containing all other folders (make sure to unzip your download file first!). If all goes well, your IDE should be showing all the project files in the navigator.

### Launch

Open the Gradle tab on your IDE, which will show a list of tasks. Double click on **Desktop** > **Tasks** > **other** > **run**. Wait a few minutes to build the game, and it should run in a small window.

## Features

As this is a simple school project, the remake will copy most (if not all) of the original game's basic features, such as:

 - Basic gameplay: jump through gaps between 2 vertical pipes while not touching either said pipes or the ground
 - Tap to Start screen
 - Tap to Restart upon death
 - Scoring functionalities
 - Basic UI for score tracking during gameplay
 - High score tracking (coming soon!)

If we still have any interest in this project after the university subject ends, maybe some cool features like achievements, AI opponents, boss battles could be implemented in the future (who knows!)



## Sources

This project was inspired by Kilobolt's Zombie Bird tutorial: ([http://www.kilobolt.com/zombie-bird-tutorial-flappy-bird-remake.html](http://www.kilobolt.com/zombie-bird-tutorial-flappy-bird-remake.html)). It's a bit outdated so there were a few things we had to change, but was still good enough to follow.
