package experiments;

public class TODO {

    // TODO: Session Redesign
    /*
    * Made major updates to the way driver is initialized and used. Basically, every test will have their own
    * driver instance. This is also in support of our push to run tests in parallel. Every module, utility, etc.
    * will now accept as argument a new driver instance in their constructor.
    *
    * The way we implement POM also changed. Each page is composed of modules, so it would make sense to create models
    * for each module, and simply call them in their respective tests. Before, the full layout of a page is represented
    * by an XML. This time around, a test class will represent the full page, with the class containing every module
    * of the page you wish to test.
    * */

    // TODO: Parallel testing
    /*
    * Tests will be grouped inside a test class. There will no longer be the need for an XML for each individual
    * test, everything will be grouped inside the test class itself. Browser, page, geo, envi, etc. will all
    * be defined inside the test class.
    * */

    // TODO: Modular testing
    /*
    * Have identified a way to focus on a specific module. Need to decide on whether a module locator is to be
    * provided every time the module POM is invoked, or to create a method instead that would give us the ability
    * to select a module. The latter has the advantage of being more convenient as we only need to use one instance
    * of the module POM, but is a little complex to implement.
    *
    * UPDATE: Implementation done. Tests suggests the module POM is working. But it still needs to be monitored.
    * Some pages might have a different attribute for the module. Also, need to check with other pages just to confirm
    * if there are no scenarios missed.
    * */

    // TODO: Headless browsers
    /*
    * Simply call the getChromeOptionsInstance() or getFirefoxOptionsInstance() then call method setHeadless(true).
    * */

    // TODO: Class Generics
    /*
    * This is no longer needed. Framework arch will no longer be based on POM, but on MOM. Meaning, every clicking
    * action (method) will no longer require the method to return an instance of the POM since we won't be creating
    * POMs anymore.
    * */

    // TODO: Reporting
    /*
    * There are multiple types of reporting formats, of which each individual style might suit a particular
    * testing activity. The challenge, if we select 2 or more different styles to support, is creating a library
    * and finding the common theme between them.
    *
    * Update 1: Apparently, the majority of the reporters are 'PRO' only, meaning it is paid. Sad.
    * So right now, we'll stick with only 1 reporter, HtmlReporter. There are 2 others (I think) that's free,
    * but I don't see a use case for them, yet.
    *
    * Update 2: We can add BDD-style reporting, but requires a bit of work when writing the step definitions.
    * I'll try to create a custom listener for Cucumber <fingers crossed>.
    * */

    // TODO: Cloud testing support
    // TODO: Unit testing
    // TODO: Integration testing
    // TODO: README.md file
    // TODO: Continuous Integration
    // TODO: Checking if a page is testable - PageTestability class
    // TODO: Feedback loop
    // TODO: UI Region Testing
    // TODO: Test Data

    // Kristoffer, Axel, Luigi, Camille, Julien Augusto, Sembrano
    // Kevin, Gil, Lloyd, Cyle, Raymond, Jz
}
