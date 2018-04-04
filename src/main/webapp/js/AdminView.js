var AdminView = Backbone.View.extend({
    collection: Cars,
    id: 'admin-view',
    events:{
        "click #modalButton": "createCar"
    },
    createCar: function(e){
        new DetailsView({model:new Car()}).show();
    },
    render:function(){
        console.log("render admin view");
        var template= _.template($('#admin_page').html(), this.collection.toJSON());
        return this.$el.html(template);
    }
});