"use strict";
function load_dgrid(item, extra){
    var grid;
    require([ 'dstore/RequestMemory',
    'dojo/_base/declare',
    'dgrid/OnDemandGrid',
    'dgrid/extensions/DijitRegistry',
    'dgrid/Selection',
    'dgrid/extensions/ColumnResizer',
    'dgrid/extensions/Pagination'
    ], function (RequestMemory, declare, OnDemandGrid, DijitRegistry, Selection, ColumnResizer) {

        console.log(extra);

        var coll = item.grid.columns;
        console.log(coll);


        // var formatTimestamp =
        //     function formatTimestamp(value) {
        //         require(['dojo/date/locale'], function (locale){
        //
        //         var inputDate = new Date(value);
        //         return locale.format(inputDate,
        //         {
        //             selector: 'date',
        //             datePattern: 'yyyy/MM/dd'
        //         });
        //     });
        // };
        //
        //
        // $.each(coll, function(i, v) {
        //     if(v.formatter !== undefined) {
        //         eval('var formatter = ' + formatTimestamp);
        //         v.formatter = formatter(v);
        //         console.log(coll[i]);
        //     }
        // });
        
        
        
        grid = new (declare ([OnDemandGrid, DijitRegistry, Selection, ColumnResizer])) ({
            collection: new RequestMemory({ target: item.ajax }),
            allowTextSelection: true,
            columns: coll,
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
            var _row;
            var row = grid.row(event);
            console.log(row.data);
            _row = row.data;
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
        
        var json = JSON.stringify(item, function(key, value) {
          if (typeof value === "function") {
            return "/Function(" + value.toString() + ")/";
          }
          return value;
        });
        
        console.log(json);

        if(extra !== undefined)
            var url = item.ajax + extra;

        grid = new (declare ([Grid, DijitRegistry, Selection, ColumnResizer, Pagination])) ({
            collection: new RequestMemory({ target: url }),
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
            var _row;
            var row = grid.row(event);
            console.log(row.data);
            _row = row.data;
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


            var grid;
            var json;
            json = JSON.parse(treemodel);
            var temp;
            temp = get_selectedTab_TabContainerWatch();
            var grid_info;
            grid_info = JSON.search(json, '//children/children[id="'+ temp +'"]/grid')[0];
            var url;
            url = JSON.search(json, '//children/children[id="'+ temp +'"]/ajax')[0];

                grid  = registry.byId(grid_info.id);

                grid.set("collection", new RequestMemory({ target: url }));

                console.log("updated"); 
        });
    });
}
















