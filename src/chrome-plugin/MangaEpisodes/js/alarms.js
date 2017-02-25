(function () {
   var alarmName = 'check-updates';
   
   function checkAlarm(callback) {
     chrome.alarms.getAll(function(alarms) {
       var hasAlarm = alarms.some(function(a) {
         return a.name == alarmName;
       });
     })
   }
   
   function createAlarm() {
    console.log('createAlarm');
     chrome.alarms.create(alarmName, {
      when: Date.now() + 3000,
      periodInMinutes: 60
    });
   }

   function cancelAlarm() {
     chrome.alarms.clear(alarmName);
   }

  if(!checkAlarm()) {
    createAlarm();
  }

})();