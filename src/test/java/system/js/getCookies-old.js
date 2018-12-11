var wPage = require('webpage');
var page = wPage.create();
var system = require("system");
var fileName = system.args[3];
var fs = require("fs");
var luCookies = "_ss_gpv_pn|_ss_vo_psc|acn_coach_mark|ADRUM_BT1|ADRUM_BT2|ADRUM_BT3|ADRUM_BT4|ADRUM_BTa|ADRUM_BTs|AMCV_AAB73BC75245B44A0A490D4D%40AdobeOrg|AMCVS_xxxxxAdobeOrg|ASP.NET_SessionId|ASPSESSIONIDAWTTSBTC|AWSELB|AWSELB|AWSELB|bcookie|bcookie|bc-visitor-id|bscookie|check|demdex|dmdbase_cdc|dpm|everest_g_v2|everest_session_v2|iuuid|JSESSIONID|kms_ctamuls|mbox|MSISContext+guid|MSISIPSelectionSession|MSISTtpDataReceivedCookie|s_cc|s_nr|s_ppv|s_ppvl|s_sq|sc_anonymous_id|TrackingHistory|tuuid|tuuid_last_update|X-Mapping-xxxxxxxx|Investis.com|lastUrl|visid_incap_1591782|nlbi_1591782|incap_ses_464_1591782|na_sc_x|__atuvc|__atuvs|acn_us-en_web#lang|_userCookiePreference|OptanonConsent|_sdsat_OneTrustActiveGroups";
var tpCountPrimary = 0;
var tpCountSecondary = 0;

console.log("STARTING TEST FOR: " + fileName + "...");

page.open(system.args[1], function (status) {
  // Get all cookies prior to accepting cookie statement then store in cookiesPrimary
  console.log("FIRST COOKIE RETRIEVAL...")
  var cookiesPrimary = phantom.cookies;
  var cookiesSecondary;

  // Create file at the specified path
  var pathBC_TP = "../../../../cookies/" + fileName + "__tp_bc.txt";
  fs.touch(pathBC_TP);

  // Open file then write to file
  var streamBC_TP = fs.open(pathBC_TP, "w");

  console.log("NUMBER OF PRIMARY COOKIES: " +  cookiesPrimary.length);  
  console.log("SAVING TP COUNT - BC");
  for (var index = 0; index < cookiesPrimary.length; index++) {
    var cookie = cookiesPrimary[index];
    var grouping = (luCookies.indexOf(cookie.name) !== -1) ? "LU" : "TP";    
    if (grouping === "TP") {   
      streamBC_TP.writeLine(cookie.name + " : " + cookie.domain + " | ");   
      tpCountPrimary++; 
    }    
  }  
  console.log("DONE, TP COUNT FOR PRIMARY: " + tpCountPrimary);
  streamBC_TP.close();

  if (system.args[2] === "true") {
    // Create an event to click the accept cookies button from the cookie banner
    page.evaluate(function() {
      console.log("CLICKING BUTTON...")
      var clickEvent = document.createEvent("MouseEvents");
      clickEvent.initEvent("click", true, true);
      document.querySelector("a.optanon-allow-all").dispatchEvent(clickEvent);
    });
  }

  page.open(system.args[1], function (status) {
      // Get all cookies after accepting cookie statement then store in cookiesSecondary
      console.log("SECOND COOKIE RETRIEVAL...")
      cookiesSecondary = phantom.cookies;

      var pathAD_TP = "../../../../cookies/" + fileName + "__tp_ad.txt";
      fs.touch(pathAD_TP);

      // Open file then write to file
      var streamAD_TP = fs.open(pathAD_TP, "w");

      console.log("NUMBER OF SECONDARY COOKIES: " +  cookiesSecondary.length);  
      //console.log("TP COOKIES:");
      console.log("SAVING TP COUNT - AD");
      for (var index = 0; index < cookiesSecondary.length; index++) {
        var cookie = cookiesSecondary[index];
        var grouping = (luCookies.indexOf(cookie.name) !== -1) ? "LU" : "TP";    
        if (grouping === "TP") {    
          streamAD_TP.writeLine(cookie.name + " : " + cookie.domain + " | ");  
          tpCountSecondary++;
        }    
      }  
      console.log("DONE, TP COUNT FOR SECONDARY: " + tpCountSecondary);
      streamAD_TP.close();

      console.log("SAVING COOKIES...")

      var pathBC = "../../../../cookies/" + fileName + "__bc.txt";
      fs.touch(pathBC);

      // Open file then write to file
      var streamBC = fs.open(pathBC, "w");

      // Print and store primary cookies
      //console.log('Listing Primary Cookies:');
      for(var i in cookiesPrimary) {
        //console.log(cookiesPrimary[i].name + ":" + cookiesPrimary[i].domain);  // Uncomment for console printing
        streamBC.writeLine(cookiesPrimary[i].name + " : " + cookiesPrimary[i].domain + " | ");
      }

      streamBC.close();
      console.log("\n");

      var pathAD = "../../../../cookies/" + fileName + "__ad.txt";
      fs.touch(pathAD);

      // Open file then write to file
      var streamAD = fs.open(pathAD, "w");

      // Print and store secondary cookies
      //console.log('Listing Secondary Cookies:');
      for(var i in cookiesSecondary) {
        //console.log(cookiesSecondary[i].name + ":" + cookiesSecondary[i].domain);  // Uncomment for console printing
        streamAD.writeLine(cookiesSecondary[i].name + " : " + cookiesSecondary[i].domain + " | ");
      }

      console.log("COMPLETED!")
      streamAD.close();
      phantom.exit();
  });
});
