function inputForm(item) {
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
    ], function (dom, Dialog, Form, TextBox, Button, treemodel, json, registry, on) {

        console.log("inputForm");

        var dia;
        var form;
        json = json.parse(treemodel);

        form = new Form({
            id: "dijit_form"
        });

        dia = new Dialog({
            id: "dialog",
            content: form,
            onHide: function () {
                form.destroy();
                dia.destroy();
            }
        });

        $.getScript("js/util.js", function(){
            var temp = get_selectedTab_TabContainerWatch();
            console.log(temp);
            var json_form_structure = JSON.search(json, '//children/children[id="'+ temp +'"]/Add_form_structure')[0];

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

        callback();
    });
}