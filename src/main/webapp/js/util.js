"use strict";
var _initTabContainerWatch;
var _selectedTab_TabContainerWatch;


function initTabContainerWatch(){
    require(["dijit/registry"], function (registry) {
        var centerPanel = registry.byId("centerPanel_");

        centerPanel.watch("selectedChildWidget", function(name, oval, nval){
            _selectedTab_TabContainerWatch = nval.id;
        });
    });
    
    _initTabContainerWatch = true;
}

function  get_InitTabContainerWatch_Status(){
    return _initTabContainerWatch;
}

function  get_selectedTab_TabContainerWatch(){
    return _selectedTab_TabContainerWatch;
}
