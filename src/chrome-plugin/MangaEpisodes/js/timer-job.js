 function checkForNewEpisodes() {
     refreshManga();
}

chrome.alarms.onAlarm.addListener(function() {
	checkForNewEpisodes();
});