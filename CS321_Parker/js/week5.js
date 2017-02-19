/**
 * Created by Paul Parker on 2/19/2017.
 */

var surveyResults = {
    accepted: false,
    userName: "",
    userFirstName: "",
    userEmail: "",
    userAddress: "",
    userCity: "",
    userState: "",
    userZip: "",
    userComments: ""
};

var keyToLabelMap = {
    'userName': "Name",
    'userEmail': "E-mail",
    'userAddress': "Address",
    'userCity': "City",
    'userState': "State",
    'userZip': "Zip",
    'userComments': "Comments"
};

function confirmSurvey() {
    surveyResults.accepted = confirm(surveyResults.userFirstName + ", would you like to participate in a survey?");
}

function promptUserName() {
    surveyResults.userName = prompt("What is your Name?") || "";
    surveyResults.userFirstName = surveyResults.userName.split(" ")[0] || surveyResults.userName;
}

function promptUserEmail() {
    surveyResults.userEmail = prompt(surveyResults.userFirstName + "\r\nWhat is your Email?") || "";
}
function promptUserAddress() {
    surveyResults.userAddress = prompt(surveyResults.userFirstName + "\r\nWhat is your Address?") || "";
}
function promptUserCity() {
    surveyResults.userCity = prompt(surveyResults.userFirstName + "\r\nWhat is your City?") || "";
}
function promptUserState() {
    surveyResults.userState = prompt(surveyResults.userFirstName + "\r\nWhat is your State?") || "";
}
function promptUserZip() {
    surveyResults.userZip = prompt(surveyResults.userFirstName + "\r\nWhat is your Zip?") || "";
}

function promptUserComments() {
    surveyResults.userComments = prompt(surveyResults.userFirstName + "\r\nWhat comments do you have about the site?") || "";
}

function writeCookie() {
    for (var prop in surveyResults) {
        var cvalue = surveyResults[prop];
        setCookie(prop, cvalue);
    }
}


function setCookie(cname, cvalue, exdays) {
    exdays = exdays || 0;
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function alertComplete() {
    alert("Thank you for your time");
    writeCookie();
    renderResults();
}

function readFromCookie() {
    if(!window.navigator.cookieEnabled || !document.cookie) {
        alert("Cookies were not enabled for this browser. Using in memory results");
        return surveyResults;
    }

    var cookieResults = {};
    for (var prop in keyToLabelMap) {
        cookieResults[prop] = getCookie(prop);
    }

    return cookieResults;
}

function renderResults() {
    var results = $("#results");
    if (!results.length) {
        return;
    }

    surveyResults = readFromCookie();
    if (!surveyResults.accepted) {
        results.append($('<div>User declined to participate</div>'));
    } else {
        results.append($('<div><label>{0}:</label><label class="Value">{1}</label></div>'.format("Name", surveyResults.userName)));
        results.append($('<div><label>{0}:</label><label class="Value">{1}</label></div>'.format("E-mail", surveyResults.userEmail)));
        results.append($('<div><label>{0}:</label><label class="Value">{1}</label></div>'.format("Address", surveyResults.userAddress)));
        results.append($('<div><label>{0}:</label><label class="Value">{1}</label></div>'.format("City", surveyResults.userState)));
        results.append($('<div><label>{0}:</label><label class="Value">{1}</label></div>'.format("State", surveyResults.userState)));
        results.append($('<div><label>{0}:</label><label class="Value">{1}</label></div>'.format("Zip", surveyResults.userZip)));
        results.append($('<div><label>{0}:</label><label class="Value">{1}</label></div>'.format("Comments", surveyResults.userComments)));
    }
}


var surveyActions = [
    confirmSurvey,
    promptUserEmail,
    promptUserAddress,
    promptUserCity,
    promptUserState,
    promptUserZip,
    promptUserComments,
    alertComplete
];

$(document).ready(function () {
    promptUserName();
    for (var i = 0; i < surveyActions.length; i++) {
        var fncAction = surveyActions[i];
        fncAction();
        if (!surveyResults.accepted) {
            alertComplete();
            break;
        }
    }
});


