"use strict";

require([
                    "dijit/registry", "dojo/dom", "dojo/json", "dojo/store/Memory",
                    "dijit/tree/ObjectStoreModel", "dijit/Tree", "dojo/request" ,"dojo/text!./json/treemodel.json",
                    "dijit/layout/ContentPane", "dojo/domReady!"
], function(registry, dom, json, Memory, ObjectStoreModel, Tree, request, treemodel, ContentPane){
                    // set up the store to get the tree data
                    
                    
                    var governmentStore = new Memory({
                        data: [ json.parse(treemodel) ],
                        getChildren: function(object){
                            return object.children || [];
                        }
                    });

                    // set up the model, assigning governmentStore, and assigning method to identify leaf nodes of tree
                    var governmentModel = new ObjectStoreModel({
                        store: governmentStore,
                        query: {id: 'root'},
                        mayHaveChildren: function(item){
                            return "children" in item;
                        }
                    });
                    
                    function addTab(item) {

                        console.log("addTab");

                        var pane;
                        if(item.ajax !== undefined){
                            if(dom.byId(item.id) === null && dom.byId(item.grid.id) === null){
                                pane = new ContentPane({
                                    id: item.id,
                                    title: item.name,
                                    content: "<dir id='" + item.grid.id + "'></dir>",
                                    closable:true
                                });

                                $.getScript("js/datagrid.js", function() {
                                    load_dgrid(item);
                                    window.dispatchEvent(new Event('resize'));
                                });
                                registry.byId("centerPanel_").addChild(pane);
                                
                                
                                
                                
                                // add the new pane to our contentTabs widget
                                
                            }else 
                                $.getScript("js/datagrid.js", function() {
                                       gridUpdate(item); 
                                    });
                        }
                    }
                    
                    // set up the tree, assigning Model;
                    var governmentTree = new Tree({
                        model: governmentModel,
                        onOpenClick: false,
                        showRoot: false,
                        onClick: function(item){
                            if(dijit.byId(item.id) === undefined){
                                console.log(item.inputNeed);
                                if(item.inputNeed){
                                    $.getScript("js/inputSearch.js", function () {
                                        console.log("before f");
                                        prompt("123", "0");
                                        inputForm(item, function () {
                                            addTab(item);
                                        });
                                    });
                                }
                                else addTab(item);
                            }
                            else 
                                $.getScript("js/datagrid.js", function() {
                                   gridUpdate(item); 
                                });
                        }
                    }, "divTree");
                    
                    governmentTree.startup(); 
                    
                    $.getScript("js/util.js")//new
                    .done(function (script, textStatus) {
                        var temp;
                        temp = get_InitTabContainerWatch_Status();
                        if(!temp) {
                            initTabContainerWatch();
                        }
                    });
});