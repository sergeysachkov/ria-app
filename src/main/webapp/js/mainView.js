var MainView = Backbone.View.extend({
  collection: Cars,
  id: "container",
  initialize: function(){
   console.log("main view init");
   this.listenTo(this.collection, 'add', this.renderList);
   this.render();
  },

  render: function(){
    this.collection.each(function(car){
    $('#carList').append(new CarView({model:car}).render());
    }, this);
      $('#admin_data').append(new AdminTableView({collection:this.collection}).render().el);
      $('#admin').append(new AdminView({collection:this.collection}).render());
  },
  
  renderList:function(){
	  $('#carList div').remove();
      $('#admin_data table').remove();
      $('#admin div').remove();
	  this.collection.each(function(car){
		    $('#carList').append(new CarView({model:car}).render());
		    }, this);
      $('#admin_data').append(new AdminTableView({collection:this.collection}).render().el);
      $('#admin').append(new AdminView({collection:this.collection}).render());
  }

});

