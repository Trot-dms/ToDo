var application = (function() {

	var taskTemplate;

	function print(param) {
		if (console && console.log) {
			console.log(param);
		}
	}

	// var categories = {
	// 	STUDY: "study",
	// 	WORK: "work",
	// 	PERSONAL: "personal"
	// };

	// var tasks = [
	// {
	// 	name: "prezentacja",
	// 	desc: "Zrobic prezentacje o jednorożcach i kotach na tęczy",
	// 	isDone: false,
	// 	category: categories.WORK
	// }, {
	// 	name: "zakupy w biedronce",
	// 	desc: "mleko, kawa, cukier, reczeniki papierowe, kapusta, pomidory" + ",chleb, ziemniaki, kokosy, oliwa z oliwek hiszpanskich",
	// 	isDone: false,
	// 	category: categories.PERSONAL
	// }, {
	// 	name: "name2",
	// 	desc: "desc2",
	// 	isDone: true,
	// 	category: categories.PERSONAL
	// }, {
	// 	name: "nauczyc sie jquery",
	// 	desc: "Poznac podstawy biblioteki",
	// 	isDone: false,
	// 	category: categories.STUDY
	// }
	// ];

	function shouldBeShowTask(index, task) {
		return !task.isDone;
	}

	function addTask(index, el) {
		var taskContent = $("#templateTask")
			.html()
			.replace("%NAME%", el.name)
			.replace("%DESC%", el.desc)
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
		var desc = $("#newTaskForm [name='taskDesc']");
		var name = $("#newTaskForm [name='taskName']");
		var category = $("#newTaskForm select option:selected");

		$("#newTaskForm").validate({
			errorPlacement: function(error, element) {
				return true;
			}
		});

		desc.toggleClass('errorField', !desc.valid());
		name.toggleClass('errorField', !name.valid());
		$("#newTaskForm select").toggleClass('errorField', category.val() == "");

		print(category.text());

		if (!desc.valid() || !name.valid() || category.val() == "") {
			return;
		}

		if ($("input[id*='task']").length == 1) {
			$("#taskContainer").html("");
		}

		var newTask = {
			name: name.val(),
			desc: desc.val(),
			isDone: false,
			category: categories[Object.keys(categories)[category.val()]]
		};
		addTask($(".row .task").length, newTask);
		print("Size of tasks:"+tasks.length);

		desc.val('');
		name.val('');
	}

	var init = function() {
		$(".info")
			.hide()
			.fadeIn();

		$("td > img").click(closeInfoBox);
		// $("#templateTask").hide();
		// $(tasks)
		// 	.filter(shouldBeShowTask)
		// 	.each(addTask);
        //
        // $("#newTaskForm button").click(newTask);
        //
        // $(Object.keys(categories)).each(function(val, text) {
			// console.log(text);
			// $("form select").append(
			// 	$('<option></option>').val(val).html(categories[text])
			// );
		// });
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