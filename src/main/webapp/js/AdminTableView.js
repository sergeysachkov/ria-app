var AdminTableView = Backbone.View.extend({
    collection:Cars,
    tagName:'table',
    id: 'admin-table',
    className:'table  table-bordered table-striped table-selectable',
    attributes: {
        'data-pagination' :'true',
        'data-search' :'true',
        'data-toggle' :'table',
        'data-url':'http://localhost:8080/ria-app/rest/cars'
    },
    initialize: function() {
        console.log("details init");
        this.collection.on('change', this.updateTable, this);
    },
    render:function(){
        console.log("render table view");
        console.log(" finished render table view");
        $(this.el).append("<table></table>");
        $(this.el).find('table').bootstrapTable({
            url: 'http://localhost:8080/ria-app/rest/cars',
            data: this.collection.toJSON(),
            pagination: true,
            search: true,
            columns: [{
                field: 'id',
                title: 'Car ID'
            }, {
                field: 'model',
                sortable: 'true',
                title: 'Car Model'
            }, {
                field: 'year',
                sortable: 'true',
                title: 'Year'
            }, {
                field: 'body',
                sortable: 'true',
                title: 'Body Type'
            }, {
                field: 'doors',
                sortable: 'true',
                title: 'Doors'
            }, {
                field: 'action',
                formatter: function actionFormatter(value, row, index) {
                    return [
                        '<a class="edit ml10" href="javascript:void(0)" title="Edit">',
                        '<i class="far fa-edit"></i>',
                        '</a>',
                        '<a class="remove ml10" href="javascript:void(0)" title="Remove">',
                        '<i class="fa fa-times-circle"></i>',
                        '</a>'
                    ].join('');
                },
                events: {
                    'click .edit': function (e, value, row, index) {
                        new DetailsView({model:new Car(row)}).show();
                    },
                    'click .remove': function (e, value, row, index) {
                        new DetailsView({model:new Car(row)}).show();
                    }
                },
                title: 'Action'
            }]
        });
        return this;
    },
    updateTable:function(){
        $(this.el).find('table').bootstrapTable('refresh');
    }
});