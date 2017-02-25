 function insertDocument() {
	App42.initialize("d01ddd44c7b6c64faa216d96db39e3fb0cda97ee66c442fa5927a6ee30ade450","cd7f10ada60d0d7585240ef6bb0684191096264efe308a3f3ebdf5c1d37772e1"); 
	var storageService  = new App42Storage();
	var dbName = "test",  
	collectionName = "foo",  
	employeeJSON =  "{\"name\":\"Nick\",\"age\":30,\"phone\":\"xxx-xxx-xxx\"}";   
	var result ;    
	storageService.insertJSONDocument(dbName, collectionName, employeeJSON,{    
		success: function(object)   
		{    
			var storageObj = JSON.parse(object);    
			result = storageObj.app42.response.storage;  
			console.log("dbName is " + result.dbName)  
			console.log("objectId is " + result.jsonDoc._id.$oid)  
		},    
		error: function(error) {    
		}    
	});  
 }
 
 function getDocument(documentId) {
	App42.initialize("d01ddd44c7b6c64faa216d96db39e3fb0cda97ee66c442fa5927a6ee30ade450","cd7f10ada60d0d7585240ef6bb0684191096264efe308a3f3ebdf5c1d37772e1"); 
	var storageService  = new App42Storage();
	var dbName = "test",  
	collectionName = "foo",  
	objectId = "55c8aacbe4b0b0febe4981ee";            
	var result ;    
	storageService.findDocumentById(dbName,collectionName,objectId,{    
		success: function(object)   
		{    
			var storageObj = JSON.parse(object);    
			result = storageObj.app42.response.storage;  
			console.log("dbName is " + result.dbName)  
			console.log("collectionName is " + result.collectionName)  
			return result.jsonDoc;
		},    
		error: function(error) {    
		}  	 
	});	
 }