var month_leap = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
var month_normal = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
var month_name = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"]
var daysDisplay = document.getElementById("days")
var prev = document.getElementById("prev")
var next = document.getElementById("next")
var monthElement = document.getElementById("calendar-month")
var yearElement = document.getElementById("calendar-year")
var date = new Date()
var year = date.getFullYear()
var month = date.getMonth()
var today = date.getDate()
var lastClass //class of last pick
var lastElement //element of last pick
var classToday = 'green greenbox'
var classFocus = 'blue bluebox'
var classPast = 'lightgrey'
var classFuture = 'darkgrey'

prev.onclick = function(e) {
    e.preventDefault();
    month--;
    if (month < 0) {
        year--;
        month = 11;
    }
    refreshDate();
}
next.onclick = function(e) {
    e.preventDefault();
    month++;
    if (month > 11) {
        year++;
        month = 0;
    }
    refreshDate();
}

function addEventForDays() {
    var days = daysDisplay.getElementsByTagName('li')
    Array.from(days).forEach(function(e) {
        if (e.innerHTML !== '')
            e.onclick = function(event) {
                if (lastClass != null && lastClass !== '' && lastElement != null) {
                    lastElement.setAttribute('class', lastClass)
                }
                lastClass = e.getAttribute('class')
                lastElement = e
                e.setAttribute('class', classFocus)
            }
    })
}

function refreshDate() {
    var str = "";
    var totalDay = numOfDays(month, year);
    var firstDay = dayStart(month, year);
    var tclass;
    for (var i = 1; i < firstDay; i++) {
        str += "<li></li>";
    }
    for (var i = 1; i <= totalDay; i++) {
        if ((i < today && year == date.getFullYear() && month == date.getMonth()) || year < date.getFullYear() || (year == date.getFullYear() && month < date.getMonth())) {
            tclass = " class='lightgrey'";
        } else if (i == today && year == date.getFullYear() && month == date.getMonth()) {
            tclass = " class='green greenbox'";
        } else {
            tclass = " class='darkgrey'";
        }
        str += "<li" + tclass + ">" + i + "</li>";
    }
    daysDisplay.innerHTML = str;
    monthElement.innerHTML = month_name[month];
    yearElement.innerHTML = year;
    addEventForDays()
}

function dayStart(month, year) {
    var tmpDate = new Date(year, month, 1);
    return (tmpDate.getDay());
}

function numOfDays(month, year) {
    var tmp = year % 4;
    if (tmp == 0) {
        return (month_leap[month]);
    } else {
        return (month_normal[month]);
    }
}
refreshDate();