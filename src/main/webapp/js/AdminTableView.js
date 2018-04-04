var AdminTableView = Backbone.View.extend({
    collection:Cars,
    tagName:'table',
    className:'table  table-bordered table-striped table-selectable',
    attributes: {
        'data-pagination' :'true',
        'data-search' :'true',
         'data-toggle' :'table'
    },
    render:function(){
        console.log("render table view");
        var template =_.template($('#admin_table').html(), this.collection.toJSON());
        this.$el.html(template);
        this.renderRows();
        console.log(" finished render table view");
        return this;
    },
    renderRows: function(){
        this.collection.forEach(this.addRow, this);
    },
    addRow: function(car){
        this.$("tbody").append(new AdminRowView({model:car}).render().el);
        console.log(this.el);
    }

});