## Overview
The **`testautomationframework`** project is MarComm's second automation framework. There were a lot of feedback about the original version being too complicated to use and understand.
So, in this version, we've tried to address that and toned down some of the complexities from the original one. 

We've added a lot of improvements like lots of documentation (including this README) and 
less code verbosity (I think) to make this version a bit more intuitive to use.

At the end of the day, what we hope to accomplish is to help you with your testing needs and be more productive. 

By the way, this README file is a living documentation. As contributors (whoever you may be) add new features to the project, we expect that you also update this file to explain and document
that new feature.

**Happy Automating!**



## Prerequisites

#### <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/3/3f/Git_icon.svg/2000px-Git_icon.svg.png" width="60" height="60"> Git

To get started, you need to have Git installed on your machine. 
You can download Git from [here](https://git-scm.com/).

Git is a version control system that greatly enhances 
collaboration and teamwork. It helps a team manage changes to source code. We highly suggest that you get yourself comfortable with the tool.

There are tons of tutorials online, but we feel like [Atlassian's Git tutorial](https://www.atlassian.com/git/tutorials)
is one of the best.
 

#### <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/IntelliJ_IDEA_Logo.svg/1200px-IntelliJ_IDEA_Logo.svg.png" width="60" height="60"> IntelliJ Idea

Next, you need an IDE. IDE is short for **_Integrated Development Environment_**. And the choice of IDE largely depends on the
language you will be using. In our project, automated tests are written in **_Java_**. 

Now there are a lot of IDE options for Java - IntelliJ Idea, Eclipse, Netbeans, among others.
But, we will be sticking with IntelliJ. So we suggest you download that too. You can find the IntelliJ Community Edition for Windows 
[here](https://www.jetbrains.com/idea/download/#section=windows).


#### <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMVAETpo8_lom4xMe1fLxhDVdD11b4Uue1MF5oYaETyBtfc9BD" width="60" height="60"> Java

As mentioned, the language of choice for writing our automated tests is Java. So you need to have that installed as well.
Download the JDK & JRE [here](https://www.oracle.com/technetwork/java/javase/downloads/index.html). 


## Getting Started

* Go to the [project's repo](https://innersource.accenture.com/users/kristian.g.maglasang/repos/test-automation-framework/browse).
* Click the clone button and copy the HTTP URL (the one with the arrow, below your profile image at the top leftmost portion of the page).
* On your machine, open Git Bash and **`cd`** to your desired directory/folder.
* Clone the project. Cloning basically means downloading.

    ```git
    git clone <HTTP URL>
    ```
* Now that you have a copy of the project, launch IntelliJ Idea and open the newly cloned project. Make sure to have Maven resolve all dependencies. 

**_Side note: It is expected that you have some Java, WebDriver, Maven, and Linux command knowledge. That means we'll be taking liberties in not detailing some of the steps
here (such as making sure Maven resolves all dependencies, and using `cd`)_**.


## Project Structure

![Alt Text](src\main\resources\images\project-structure.jpg)

    * Module design
    * Instance
    * Environment, Geo, Page, ResourcesFolder
    * Data package
    * Drivers package
    * XML files

* Installation instructions
    * Git
    * IntelliJ Idea Community Edition

* Structure
    * POM
    * `reports` folder
    * `screenshots` folder
    * `src folder`
    
* Utilities
    * NetworkRequests
    * PageTestability
    * TestHelper
    * Wait
    * TestListener & Reporting
    * Applitools
    * HttpResponseCode
    * JSONParser
   
* Standards and Rules
    * Ease of use
    * Coding standards
    * Commenting
    * Writing tests

* Contributing
    
* Limitations