var AdminRowView = Backbone.View.extend({
    model : Car,
    tagName: 'tr',
    render:function(){
        console.log("render table row view");
        var template= _.template($('#admin_table_row').html(), this.model.toJSON());
        this.$el.html(template);
        console.log(this.el);
        return this;
    },
    events:{
        "click .edit": 'updateCar',
        "click .remove": 'updateCar'
    },
    updateCar:function (){
        new DetailsView({model:this.model}).show();
    }
});