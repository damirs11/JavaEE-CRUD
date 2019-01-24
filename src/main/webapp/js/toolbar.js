/* global JSONForm */

function toJSONString( form ) {
		var obj = {};
		var elements = $(form).serializeArray();
		for( var i = 0; i < elements.length; ++i ) {
			var element = elements[i];
			var name = element.name;
			var value = element.value;

			if( name ) {
				obj[ name ] = value;
			}
		}

		return JSON.stringify( obj );
	}


function Add(){
require([
    "dojo/dom",
    "dijit/Dialog", 
    "dijit/form/Form", 
    "dijit/form/TextBox", 
    "dijit/form/Button",
    "dojo/text!./json/treemodel.json",
    "dojo/json",
    "dijit/registry",
    "dojo/on",
    "dojo/_base/event",
    "dojo/domReady!" 
], function(dom, Dialog, Form, TextBox, Button, treemodel, json, registry, on) 
{   
    json = json.parse(treemodel);
    
    var form = new Form({
        id: "dijit_form"
    });

    var dia = new Dialog({
        id: "dialog",
        content: form,
        onHide: function () {
            form.destroy();
            dia.destroy();
        }
    });
    
    
    
    $.getScript("js/util.js", function(){ 
        temp = get_selectedTab_TabContainerWatch();
        console.log(temp);
        let json_form_structure = JSON.search(json, '//children/children[id="'+ temp +'"]/Add_form_structure')[0];
        
        dia.set('title', JSON.search(json, '//children/children[id="'+ temp +'"]/name')); //dialoge title set

        $(form.containerNode).jsonForm(json_form_structure);

        var ScopedButtonCancel = dom.byId(JSON.search(json_form_structure, '//form/items[id="cancel_button"]/id')[0]);
        on(ScopedButtonCancel, "click", function(evt) {
            console.log(evt);
            dia.hide();
        });
        
    }); 
    
    
    form.startup();
    dia.show();


    $("#" + form.id).submit(function(e) {
            e.preventDefault();

            let _form = $(this);
            let data = toJSONString( _form );
            
            let url = JSON.search(json, '//children/children[id="'+ temp +'"]/ajax')[0];
            console.log(url);
            
            $.ajax({
                   type: "POST",
                   url: url,
                   contentType: "application/json; charset=utf-8",
                   dataType: "json",
                   data: data, // serializes the form's elements.
                   statusCode:{
                       201: function(){
                           $.getScript("js/datagrid.js", function(){
                                gridUpdate();
                            });
                       }
                   }
            });
            
        form.destroy();
        dia.destroy();
    });
});//~require            
}

function Delete(){
    require([
    "dojo/dom",
    "dijit/Dialog", 
    "dijit/form/Form", 
    "dijit/form/TextBox", 
    "dijit/form/Button",
    "dojo/text!./json/treemodel.json",
    "dojo/json",
    "dijit/registry",
    "dojo/on",
    "dojo/_base/event",
    "dojo/domReady!" 
    ], function(dom, Dialog, Form, TextBox, Button, treemodel, json, registry, on){
        
        json = json.parse(treemodel);
        
        temp = get_selectedTab_TabContainerWatch();
        
        var json_form_structure = JSON.search(json, '//children/children[id="'+ temp +'"]/grid')[0];  
        grid = registry.byId(json_form_structure.id);
        
        let url = JSON.search(json, '//children/children[id="'+ temp +'"]/ajax')[0];
        console.log(url);
        
        for (var e in grid.selection) {
            
            id = e;
            console.log(id);
            
            $.ajax({
               url: url + "/" + id,
               type: "DELETE",
               statusCode:{
                           204: function(){
                               $.getScript("js/datagrid.js", function(){
                                    gridUpdate();
                                });
                           }
                       }
             });
        }                
    });       
}

function Update(row){
    require([
    "dojo/dom",
    "dijit/Dialog", 
    "dijit/form/Form", 
    "dijit/form/TextBox", 
    "dijit/form/Button",
    "dojo/text!./json/treemodel.json",
    "dojo/json",
    "dijit/registry",
    "dojo/on",
    "dojo/_base/event",
    "dojo/domReady!" 
    ], function(dom, Dialog, Form, TextBox, Button, treemodel, json, registry, on, query){
        
        json = json.parse(treemodel);
    
    var form = new Form({
        id: "dijit_form"
    });

    var dia = new Dialog({
        id: "dialog",
        content: form,
        onHide: function () {
            form.destroy();
            dia.destroy();
        }
    });
    
    
    
        $.getScript("js/util.js", function(){ 
            temp = get_selectedTab_TabContainerWatch();
            let json_form_structure = JSON.search(json, '//children/children[id="'+ temp +'"]/Update_form_structure')[0];
            
            console.log(json_form_structure["value"]);
            
            
            for (var key in json_form_structure["value"]) {
                json_form_structure["value"][key] = row[key];
            }
            
            dia.set('title', JSON.search(json, '//children/children[id="'+ temp +'"]/name')); //dialoge title set

            
            $(form.containerNode).jsonForm(json_form_structure); 

            var ScopedButtonCancel = dom.byId(JSON.search(json_form_structure, '//form/items[id="cancel_button"]/id')[0]);
            on(ScopedButtonCancel, "click", function(evt) {
                dia.hide();
            });
        }); 
        
        form.startup();
        dia.show(); 

        $("#" + form.id).submit(function(e) {
            e.preventDefault();

            let _form = $(this);

            let data = toJSONString( _form );
            
            let url = JSON.search(json, '//children/children[id="'+ temp +'"]/ajax')[0];
            console.log(url);
            
            $.ajax({
                type: "PUT",
                url: url,
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: data, // serializes the form's elements.
                statusCode:{
                    200: function(){
                        $.getScript("js/datagrid.js", function(){
                            gridUpdate();
                        });
                   }
                }
            });       
            form.destroy();
            dia.destroy();
        });
            
    
    
    
    });//~require
}

