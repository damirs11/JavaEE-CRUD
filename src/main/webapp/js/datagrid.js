function load_dgrid(item){
    var grid;
    require([ 'dstore/RequestMemory',
    'dojo/_base/declare',
    'dgrid/OnDemandGrid',
    'dgrid/extensions/DijitRegistry',
    'dgrid/Selection',
    'dgrid/extensions/ColumnResizer',
    'dgrid/extensions/Pagination'
    ], function (RequestMemory, declare, OnDemandGrid, DijitRegistry, Selection, ColumnResizer) {
    
        grid = new (declare ([OnDemandGrid, DijitRegistry, Selection, ColumnResizer])) ({
            collection: new RequestMemory({ target: item.ajax }),
            allowTextSelection: true,
            columns: item.grid.columns,
            loadingMessage: "Loading data...",
            noDataMessage: "No results found.",
            keepScrollPosition : true,
            minRowsPerPage : 5,
            maxRowsPerPage : 10
        }, item.grid.id);
        
        
        
        grid.on("dgrid-select", function(event){
        // Get the rows that were just selected
            var rows = event.rows;
            });
        
        grid.on('.dgrid-content .dgrid-row:dblclick', function (event) {
            var row = grid.row(event);
            console.log(row.data);
            var _row = row.data;
            $.getScript("js/toolbar.js", function(){
                Update(_row);
            });
        });
        
        grid.startup();
    });
    return grid;
}

function load_dgrid_pagination(item){
    var grid;
    require([ 'dstore/RequestMemory',
    'dojo/_base/declare',
    'dgrid/Grid',
    'dgrid/extensions/DijitRegistry',
    'dgrid/Selection',
    'dgrid/extensions/ColumnResizer',
    'dgrid/extensions/Pagination'
    ], function (RequestMemory, declare, Grid, DijitRegistry, Selection, ColumnResizer, Pagination) {
    
        grid = new (declare ([Grid, DijitRegistry, Selection, ColumnResizer, Pagination])) ({
            collection: new RequestMemory({ target: item.ajax }),
            firstLastArrows: true,
            allowTextSelection: true,
            columns: item.grid.columns,
            loadingMessage: "Loading data...",
            noDataMessage: "No results found.",
            keepScrollPosition : true
        }, item.grid.id);
        
        
        
        grid.on("dgrid-select", function(event){
        // Get the rows that were just selected
            var rows = event.rows;
            });
        
        grid.on('.dgrid-content .dgrid-row:dblclick', function (event) {
            var row = grid.row(event);
            console.log(row.data);
            var _row = row.data;
            $.getScript("js/toolbar.js", function(){
                Update(_row);
            });
        });
        
        grid.startup();
    });
    return grid;
}


function gridUpdate(){
    require([
        "dstore/RequestMemory",
        "dijit/registry",
        "dojo/text!./json/treemodel.json"
    ], function(RequestMemory, registry, treemodel) {
        
        $.getScript("js/util.js", function(){
            
                
                json = JSON.parse(treemodel);
                temp = get_selectedTab_TabContainerWatch();
                grid_info = JSON.search(json, '//children/children[id="'+ temp +'"]/grid')[0];
                url = JSON.search(json, '//children/children[id="'+ temp +'"]/ajax')[0];  



                var grid  = registry.byId(grid_info.id);

                grid.set("collection", new RequestMemory({ target: url }));

                console.log("updated"); 
        });
    });
}
















