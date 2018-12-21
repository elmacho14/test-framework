## Overview
The **`testautomationframework`** project is MarComm's second automation framework. There were a lot of feedback about the original version being too complicated to use and understand.
So, in this version, we've tried to address that and toned down some of the complexities from the original one. 

We've added a lot of improvements like lots of documentation (including this README) and 
less code verbosity (I think) to make this version a bit more intuitive to use.

At the end of the day, what we hope to accomplish is to help you with your testing needs and be more productive. 

By the way, this README file is a living documentation. As contributors (whoever you may be) add new features to the project, we expect that you also update this file to explain and document
that new feature.

**Happy Automating!**
<br/>
<br/>

## Table of Contents
* [Prerequisites](#markdown-header-prerequisites)
    * [Git](#markdown-header-git)
    * [IntelliJ Idea](#markdown-header-intellij-idea)
    * [Java](#markdown-header-java)
* [Getting Started](#markdown-header-getting-started)
* [Project Structure](#markdown-header-project-structure)
* [`main` Folder](#markdown-header-main-folder)
* [`test` Folder](#markdown-header-test-folder)
* [Rules and Standards](#markdown-header-rules-and-standards)
* [Contributing](#markdown-header-contributing)
* [Limitations and Bugs](#markdown-header-limitations-and-bugs)


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
<br/>
<br/>
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
<br/>
<br/>
## Project Structure
![Alt Text](src\main\resources\images\project-structure.jpg)
<br/>
At a high-level, the project is composed of many files and folders, of which the important one's are described below:
* `reports` - Tests run via XML will generate a test report. That report gets dumped in this folder.
* `screenshots` - For failed tests, a screenshot will be taken and dumped in this folder. The screenshot will also be appended to the test report.
* `src` - Source code. Tests and test implementations are found here.
* `.gitignore` - Files we don't need Git to track are added here.
* `pom.xml` - All the project's dependencies are specified in this file.
* `README.md` - The project's documentation.

The majority of your work will be contained within the `src` folder. This folder in turn contains two additional folders, `main`, and `test`. The module implementations, utilities, driver instance (and so much more) are contained within the `main` folder.

The `test` folder on the other hand contains all the tests, including step definitions. It also contains the XML files from which some of our tests are executed from.

In the next section, we will go through every folder and package within the `main` and `test` folders.
<br/>
<br/>
## `main` Folder
Then `main` folder will contain your module implementations, utility classes, feature files, and more.

**_This folder will not contain any test of any kind._**

##### `java/experiments` Package
Experimental code will be added here. And the contents of this package will vary per user, depending on what the user is experimenting on.
<br/>

##### `java/features` Package
This package will contain feature files.\
Suggested naming convention: _rmt1988_us004.feature_
<br/>

##### `java/modules` Package
The old approach was to create implementations for a single page, they are called **_Page Object Models (POM)_**. The idea was to create 1 class that
would represent 1 page. But for our purposes, this approach had maintenance issues (which we will not get into here). 

Moving forward, we will instead be creating implementations for modules, **NOT PAGES**. This will help us considerably
with maintenance. 

Note that the module design will still follow that of a POM.
<br/>

##### `java/session` Package
One of the complaints from using the original framework was that the `driversession` package was too complicated and difficult to understand. We've addressed this by having only 1 class to handle instantiating the driver object. No other classes or interfaces, just the one class, `Instance`.
<br/>

##### `java/utilities` Package
Nothing has changed here. This package will still hold utility classes. Libraries we've created that will help us with our testing will be held in this package.

Our utility classes won't follow the standard definition of a utility class: 

> Utility Class, also known as Helper class, is a class, which contains just static methods, it is stateless and cannot be instantiated. It contains a bunch of related methods, so they can be reused across the application.

We need them to be instantiated to support our goal of parallel testing.

Perhaps we're doing utility classes wrong, but this will have to be the case for now.
<br/>

##### `/resources` Folder
JSON files, drivers, and images are stored here.

JSON is an alternative data format for our test data. Our `JSONParser` class will be reading JSON objects from this folder (**_/data_**).

Drivers such as `chromedriver` and `geckodriver` is located in the **_/drivers_** package within this folder. They are essential for launching browsers.
<br/>
<br/>
## `test` Folder
The `test` folder contains all the tests, including step definitions.

**_This folder will not contain any implementation of any kind._**

##### `java/api` Package
This package will hold API tests.

Tests to follow.
<br/>

##### `java/integration` Package
This package will hold integration tests.

The integration tests contained here will not be tests for the production code, but rather for the different libraries we've created to help with our tests.
<br/>

##### `java/performance` Package
This package will hold performance tests.
<br/>

##### `java/system` Package
End-to-end tests (tests performed via the user interface) will be held here. This includes step definitions, analytics tests, visual tests, and functional tests.
<br/>

##### `java/unit` Package
This package will hold unit tests.

The unit tests contained here will not be tests for the production code, but rather for the different libraries we've created to help with our tests.
<br/>

##### `/xml` Folder
XML files are stored here. These XML files are especially useful for running batch tests and for automatic generation of test results.
<br/>