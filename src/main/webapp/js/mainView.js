var MainView = Backbone.View.extend({
  collection: Cars,
  //id: "container",
  initialize: function(){
   console.log("main view init");
   this.listenTo(this.collection, 'add', this.renderList);
   this.render();
  },

  render: function(){
    this.collection.each(function(car){
    $('#carList').append(new CarView({model:car}).render());
    }, this);
    adminTable = new AdminTableView({collection:this.collection});
      $('#admin_data').append(adminTable.render().el);
      $('#admin').append(new AdminView({collection:this.collection}).render());
  },
  
  renderList:function(){
	  $('#carList div').remove();
      $('#admin div').remove();
	  this.collection.each(function(car){
		    $('#carList').append(new CarView({model:car}).render());
		    }, this);
      $('#admin').append(new AdminView({collection:this.collection}).render());
  }

});

