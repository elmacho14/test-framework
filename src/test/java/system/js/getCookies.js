var wPage = require('webpage');
var page = wPage.create();
var system = require("system");

console.log("STARTING TEST FOR " + system.args[2]);

page.open(system.args[1], function (status) {
  // Get all cookies prior to accepting cookie statement then store in cookiesPrimary
  console.log("GETTING COOKIES BC...");
  var cookiesPrimary = phantom.cookies;
  var cookiesSecondary;

  // Create an event to click the accept cookies button from the cookie banner
  if (system.args[3] === "true") {
    console.log("CLICKING ACCEPT COOKIES BUTTON...");
    page.evaluate(function() {
      var clickEvent = document.createEvent("MouseEvents");
      clickEvent.initEvent("click", true, true);
      document.querySelector("a.optanon-allow-all").dispatchEvent(clickEvent);
    });
  }

  page.open(system.args[1], function (status) {
      // Get all cookies after accepting cookie statement then store in cookiesSecondary
      console.log("GETTING COOKIES AD...");
      cookiesSecondary = phantom.cookies;

      console.log("SAVING COOKIES..."); 
      var fs = require("fs");
      var pathBC = "../../../../cookies/allcookies/" + system.args[2] + "_bc.txt";
      var pathAD = "../../../../cookies/allcookies/" + system.args[2] + "_ad.txt";

      // Create file at the specified path
      fs.touch(pathBC);
      fs.touch(pathAD);

      // Open file then write to file
      var streamBC = fs.open(pathBC, "w");

      // Print and store primary cookies
      for(var i in cookiesPrimary) {
        //console.log(cookiesPrimary[i].name + ":" + cookiesPrimary[i].domain);  // Uncomment for console printing
        streamBC.writeLine(cookiesPrimary[i].name + " ; " + cookiesPrimary[i].value + " ; " + cookiesPrimary[i].domain);
      }

      streamBC.close();
      console.log("\n");

      // Open file then write to file
      var streamAD = fs.open(pathAD, "w");

      // Print and store secondary cookies
      for(var i in cookiesSecondary) {
        //console.log(cookiesSecondary[i].name + ":" + cookiesSecondary[i].domain);  // Uncomment for console printing
        streamAD.writeLine(cookiesSecondary[i].name + " ; " + cookiesSecondary[i].value + " ; " + cookiesSecondary[i].domain);
      }

      console.log("COMPLETED!");
      streamAD.close();
      phantom.exit();
  });
});
