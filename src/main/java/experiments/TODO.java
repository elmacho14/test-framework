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

    // TODO: testing
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
     *
     * Update 3: Nope, can't do i. Pressed for time.
     * */

    // TODO: PageTestability class
    /*
     * Created a class called PageTestability. It basically checks for the page's status code, rendering errors and
     * glassmappers in the page to determine if the page is ready for further testing. Unfortunately, I have not figured
     * out a way yet to make it dynamic by creating a separate PageTestability test class and having all the rest
     * of the tests just call it. So current implementation will be to add it to your tests manually. Looking into
     * creating a template though to automatically insert it into the tests.
     * */

    // TODO: Cloud testing support
    // TODO: README.md file
    // TODO: Continuous Integration
    // TODO: Feedback loop
    // TODO: UI Region Testing
    // TODO: RCA for failed tests (UI only)
    // TODO: Test Data Management
    // TODO: 1 Applitools batch for multiple tests
    // TODO: Adding nodes in reports

    // Kristoffer, Axel, Luigi, Camille, Julien Augusto, Sembrano
    // Kevin, Gil, Lloyd, Cyle, Raymond, Jz

    /*
    * LIMITATIONS
    * Module Design
    * Cannot use the same module object once focus has shifted from it. Need to create a new object
    *
    * Reporting
    * Unable to create test nodes for 2 or more test classes. Works for only 1.
    * We may need to modify how tests are executed, or I may need to change the
    * listener.
    *
    *
    *
    *
    *
    *
    * */
}
