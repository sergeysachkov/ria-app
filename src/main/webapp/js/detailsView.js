var DetailsView = Backbone.View.extend({
	model: Car,
    id: 'myModal',
    className: 'modal fade hide',
    template: 'car_details',
    initialize: function() {
        console.log("details init");
        //this.model.on('sync', this.render, this);
        this.render();
    },
	  saveCar: function(e){
          console.log("model"+this.model.get('model'));

		  var carDetails= {model:$('#carModel').val(),
                            year:$('#carYear').val(),
                            body:$('#carBody').val(),
              				doors:$('#carDoors').val(),
              				engine:{
		  	                          size:$('#engineSize').val(),
                                      fuel:$('#engineFuel').val(),
                                      cylinders:$('#engineCylinders').val(),
                                      transmission:$('#engineTransmission').val()
		                    }};

		//  if (this.model.isNew()) {
		  if (($('#carId').val()) == ''){
              console.log("isnew");
			  this.model= new Car(carDetails);
              console.log("test"+this.model.get('model'));
              cars.add(this.model);
              console.log("added");
			  this.model.save({
                  model:$('#carModel').val(),
                  year:$('#carYear').val(),
                  body:$('#carBody').val(),
                  doors:$('#carDoors').val(),
                  engine:{
                      size:$('#engineSize').val(),
                      fuel:$('#engineFuel').val(),
                      cylinders:$('#engineCylinders').val(),
                      transmission:$('#engineTransmission').val()
                  }
			  },{
						  success:function(car){
                              console.log("sucess - added");
							  $('#carId').val(car.id);
							  mainView.renderList();
						  }
					  });
			  }
		  else{
		  this.model.save({
			  id:parseInt($('#carId').val()),
              model:$('#carModel').val(),
              year:$('#carYear').val(),
              body:$('#carBody').val(),
              doors:$('#carDoors').val(),
              engine:{
                  id:parseInt($('#engineId').val()),
                  size:$('#engineSize').val(),
                  fuel:$('#engineFuel').val(),
                  cylinders:$('#engineCylinders').val(),
                  transmission:$('#engineTransmission').val()
			  }
			  },{
				  success:function(car){
                      console.log("sucess - updated " + car.get('doors'));
					  mainView.renderList();
				  }
			  });
		  }
          this.hide();
		  return false;
	  },
	  deleteCar: function(e){
		  var id= parseInt($('#carId').val());
		  this.model.set({id:id});
		  this.model.destroy(
				  {success:function(data){
                          console.log`("car deleted");
					  mainView.renderList();
				  }
		  });
          this.hide();
		  return false;
	  },

    addCar: function(e){
          $('#btnDelete').hide();
          $('#carId').val(null);
          $('#carModel').val("");
          $('#carBody').val("");
          $('#carYear').val("");
          $('#carDoors').val("");
          $('#engineId').val(null);
          $('#engineSize').val("");
          $('#engineFuel').val("");
          $('#engineCylinders').val("");
          $('#engineTransmission').val("");
		  return false;
	  },
	
    render: function() {
        console.log("render "+this.model.get('model'));
        var template= _.template($('#car_details').html(), this.model.toJSON());
        this.renderView(template);
        return this;
    },

    renderView: function(template) {
        this.$el.html(template);
        this.$el.modal({show:false}); // dont show modal on instantiation
    },

    events: {
        'hide.bs.modal': 'teardown',
        "click #btnDelete": "deleteCar",
        "click #carButton": "saveCar"
    },

    show: function() {
        this.$el.modal('show');
    },

    hide: function() {
        this.$el.modal('hide');
    },
    teardown: function() {
        console.log("teardown");
        this.$el.data('modal', null);
        this.remove();
    }
});




