var CarView = Backbone.View.extend({
    model: Car,
    tagName:'div',
    className: 'col-sm-6 col-md-4 col-lg-3',
    events:{
        "click img": "showModal"
    },
    initialize: function(){
        console.log("car view init");
        this.model.on('change', this.render, this);
        //	console.log('Values for a model have changed');
        // });
    },
    showModal: function(e){
        var car=this.model;
        new DetailsView({model:car}).show();
    },
    render:function(){
        var template= _.template($('#car_list').html(), this.model.toJSON());
        return this.$el.html(template);
    }
});