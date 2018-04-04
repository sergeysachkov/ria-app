var rootURL = "http://localhost:8080/ria-app/rest/cars";

var Car = Backbone.Model.extend({
    urlRoot:rootURL,
    defaults:{
        "id":null,
        "model":"BMW",
        "year":"2018",
        "body":"SUV",
        "doors":"5",
         "engine": {
            "id":null,
            "size": "2",
            "fuel": "Petrol",
            "cylinders": "12",
            "transmission": "Automatic"
         }
        },
    initialize: function(){
        console.log("car model init");
        //this.on('change', function(){
        //	console.log('Values for a model have changed');
        // });
    }
});

var Cars = Backbone.Collection.extend({
    model: Car,
    url:rootURL});