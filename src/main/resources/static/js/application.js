var application = (function() {

    var taskTemplate;

    function print(param) {
        if (console && console.log) {
            console.log(param);
        }
    }
    var categories = [];
    var tasks = [];
    $.getJSON("/todos", function(data){
        $.each(data, function(i, field){
            console.log(i+" / "+field.id + " -> " + field.title);
            tasks.push(field);
        })
    });

    $.getJSON("/categories", function(data){
        $.each(data, function(i, field){
            categories.push(field.name);
        })
    });

    //var categories = {
//		STUDY: "study",
    //WORK: "work",
    //PERSONAL: "personal"
    //};


    function shouldBeShowTask(index, task) {
        return !task.compleated;
    }

    function addTask(index, el) {

        var taskContent = $("#templateTask")
            .html()
            .replace("%NAME%", el.title)
            .replace("%DESC%", el.description)
            .replace("%ID%", index);

        $("#templateTask")
            .clone()
            .attr('id', 'task' + index)
            .show()
            .html(taskContent)
            .appendTo("#taskContainer");

        $("#task" + index).on('click', doneTask);
        $("#task" + index).addClass('category-' + el.category);
    }

    function closeInfoBox() {
        $(".info").slideUp("fast");
    }

    function newTask() {
        var description = $("#newTaskForm [name='taskDesc']");
        var name = $("#newTaskForm [name='taskName']");
        var category = $("#newTaskForm select option:selected");

        $("#newTaskForm").validate({
            errorPlacement: function(error, element) {
                return true;
            }
        });

        description.toggleClass('errorField', !description.valid());
        name.toggleClass('errorField', !name.valid());
        $("#newTaskForm select").toggleClass('errorField', category.val() == "");

        print(category.text());

        if (!description.valid() || !name.valid() || category.val() == "") {
            return;
        }

        if ($("input[id*='task']").length == 1) {
            $("#taskContainer").html("");
        }

        var newTask = {
            title: name.val(),
            description: description.val(),
            compleated: false,
            category: categories[Object.keys(categories)[category.val()]]
        };
        addTask($(".row .task").length, newTask);
        print("Size of tasks:"+tasks.length);

        description.val('');
        name.val('');
    }

    var init = function() {

        $(".info")
            .hide()
            .fadeIn();

        $("td > img").click(closeInfoBox);
        $("#templateTask").hide();
        $(tasks)
            .filter(shouldBeShowTask)
            .each(addTask);

        //console.log("Adding tasks...");
        //$.each(tasks, addTask);
        //console.log("Done...");

        $("#newTaskForm button").click(newTask);

        $(Object.keys(categories)).each(function(val, text) {
            console.log(text);
            $("form select").append(
                $('<option></option>').val(val).html(categories[text])
            );
        });
    }

    var doneTask = function(param) {
        $(param.currentTarget).find(".checkbox-custom").prop("checked", true)

        setTimeout(function() {
            $(param.currentTarget).slideUp(400, function() {
                $(param.currentTarget).remove();
                checkTasksSize();
            })
        }, 50);
    }

    function checkTasksSize() {
        if ($("input[id*='task']").length == 1) {
            $("#taskContainer")
                .html("<p class='emptyList'>You don\'t have tasks</p>");
        }
    }

    return {
        init: init,
        doneTask: doneTask
    }

})()