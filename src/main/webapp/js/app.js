var mainView;
var cars;

$(document).ready(function(){
    cars = new Cars();
    cars.fetch({
		success: function(data){
			mainView = new MainView({collection: cars});
			mainView.$el.appendTo(document.body);
		}
	});
});

