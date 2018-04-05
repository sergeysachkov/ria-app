var mainView;
var cars;
var adminTable;

$(document).ready(function(){
    cars = new Cars();
    cars.fetch({
		success: function(data){
			mainView = new MainView({collection: cars});
			mainView.$el.appendTo(document.body);
		}
	});
});

