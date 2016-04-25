$( document ).ready(function(){
	var prevX = 0;
	var prevY = 0;
	window.video = document.getElementById("videoPreview");
	var h = $( document ).height();
	var w = $( document ).width();
	
	// mousemove track x and y
	$('#videoPreview').mousemove(function( event ) {
		var coordX = event.pageX;
		var coordY = event.pageY;
		if(prevY < coordY)
			video.currentTime = video.currentTime + 0.05; // percent * video.duration;
		else
			video.currentTime = video.currentTime - 0.05; // percent * video.duration;
			
		prevY = coordY;
		prevX = coordX;
		$('#debugInfo').text("movement: " + coordY + "; rotation: " + coordX);

		var $videoInfo = $('.videoInfo');
		$videoInfo.find('.info-x .detail-value').text(coordX);
		$videoInfo.find('.info-y .detail-value').text(coordY);
		
		var formatTime = Math.round(video.currentTime * 100) / 100;
		$videoInfo.find('.info-time .detail-value').text(formatTime);
	});

	//click the video area to add class to hide overlay
	//click on the preview area and put the x,y, time value to the side-info 
	$('#videoPreview').click(function(){
		console.log('click');
		$('.sidebar .overlay').addClass('hidden');

		var $videoInfo = $('.videoInfo');
		var xPath = '.info-x .detail-value';
		var yPath = '.info-y .detail-value';
		var timePath = '.info-time .detail-value';
		var x = $videoInfo.find(xPath).text(); //get the text format of this elmement according to the selector/path
		var y = $videoInfo.find(yPath).text();
		var time = $videoInfo.find(timePath).text();

		var $sidebarInfo = $('.sidebar-info');
		$sidebarInfo.find(xPath).text(x); //set the text
		$sidebarInfo.find(yPath).text(y);
		$sidebarInfo.find(timePath).text(time);
	})


	// add new note/quiz button, click and id increase by one
	$('.modal').on('click', '.add-btn', function(){
		var id = $(this).closest('.modal').attr('id');
		addOperation(id);
	})

	// click on edit and save in the choice of modal
	$('.modal').on('click', '.edit, .save', function(){
		var isEdit = $(this).hasClass('edit');
		var $choice = $(this).closest('.choice');
		editOperation($choice, isEdit);
	})

	// click the correct answer
	$('.modal').on('click', '.check-answer', function(){
		var $modal = $(this).closest('.modal');
		var $choices = $(this).closest('.choices');
		var id = $modal.attr('id');
		var isMultiple = id.indexOf('multiple') > -1;
		checkAnswerOperation($choices, $(this), !isMultiple);
	})

	//click on "save changes" on each modal for note
	$('.modal #saveNote').click(function(){
		console.log('click');
		// saveNote();
	})


	$('.modal .reset').click(function(){
		var $modal = $(this).closest('.modal');
		initModal($modal);
	})

	$('.modal .save-modal').click(function(){
		var $modal = $(this).closest('.modal');

		var $contents = $modal.find('.content-area>div');
		var errorMsg = inputValidation($contents)
		if (errorMsg) {
			alert(errorMsg);
			return;
		}
		$modal.modal('hide');
		console.log('fake save');
		
	})

	$('#submitEvent').click(function(){

		// note 		
		var $noteModal = $('#noteModal');
		var notes = getNotes($noteModal);
		// $('.sidebar .overlay').removeClass('hidden');
		 
		// text quiz
		var $textQuizModal = $('#textQuizModal');
		var textQuiz = getTextQuiz($textQuizModal);

		// checkBox quiz
		var $checkBoxQuizModal = $('#checkBoxQuizModal');
		var checkBoxQuiz = getCheckBoxQuiz($checkBoxQuizModal);

		// multipleChoice quiz
		var $multipleChoiceQuizModal = $('#multipleChoiceQuizModal');
		var multipleChoiceQuiz = getMultipleChoiceQuiz($multipleChoiceQuizModal);

		var meta = getMeta();

		var obj = {};
		obj.notes = notes;
		obj.textQuiz = textQuiz;
		obj.checkBoxQuiz = checkBoxQuiz;
		obj.multipleChoiceQuiz = multipleChoiceQuiz;
		obj.meta = meta;

		if (!notes.length && !textQuiz.length && !checkBoxQuiz.length && !multipleChoiceQuiz.length) {
			alert('You cannot submit this event without making any changes');
			return;
		}


		console.log(obj);
		window.obj = obj;

		$.ajax({
			type: "POST",
			//the url where you want to sent the userName and password to
			url: '/event',
			dataType: 'json',
			//json object to sent to the authentication url
			data: JSON.stringify(obj),
			success: function (res) {
				console.log(res); 
				addTimeLog(res);

				// initModal
				initModal($noteModal);
				initModal($textQuizModal);
				initModal($checkBoxQuizModal);
				initModal($multipleChoiceQuizModal);
				
				// notification
				popupBox("Event submitted successfully", 1);
				
				// add overlay
				$('.sidebar .overlay').removeClass('hidden');
			}
		})

	})
	
	$('#timeIndex').on('click', '.time-log', function(e){
		e.preventDefault();
		var eventId = $(this).attr('data-event-id');
		var time = $(this).text();

		$.get('/event/'+eventId, function(response){
			var data = JSON.parse(response);
			console.log(data);
			fillInData(data);
			popupBox('Review time log ' + time + '...', 1);

			$('#submitEvent').addClass('hidden');
			$('#finishReview').removeClass('hidden');
		})
		
	})

	$('.sidebar').on('click', '#finishReview', function(e){
		e.preventDefault();

		popupBox("Finish review...", 1);
		
		var $noteModal = $('#noteModal');
		var $textQuizModal = $('#textQuizModal');
		var $checkBoxQuizModal = $('#checkBoxQuizModal');
		var $multipleChoiceQuizModal = $('#multipleChoiceQuizModal');
		// initModal
		initModal($noteModal);
		initModal($textQuizModal);
		initModal($checkBoxQuizModal);
		initModal($multipleChoiceQuizModal);

		// add overlay
		$('.sidebar .overlay').removeClass('hidden');

		$('#submitEvent').removeClass('hidden');
		$('#finishReview').addClass('hidden');

	})

});



//functions
function fillInData(response) {
	

	var meta = response.meta;
	fillMeta(meta);

	var notes = response.notes;
	if (notes && notes.length > 0)
		fillNotes(notes);

	var textQuiz = response.textQuiz;
	if (textQuiz && textQuiz.length > 0)
		fillQuiz(textQuiz, 'textQuizModal', false);

	var checkBoxQuiz = response.checkBoxQuiz;
	if (checkBoxQuiz && checkBoxQuiz.length > 0)
		fillQuiz(checkBoxQuiz, 'checkBoxQuizModal', true);

	var multiChoiceQuiz = response.multipleChoiceQuiz;
	if (multiChoiceQuiz && multiChoiceQuiz.length > 0)
		fillQuiz(multiChoiceQuiz, 'multipleChoiceQuizModal', true);
}

function fillMeta(meta) {

	var v = document.getElementById("videoPreview");
	

	$('.sidebar .overlay').addClass('hidden');

	var $videoInfo = $('.videoInfo');
	var xPath = '.info-x .detail-value';
	var yPath = '.info-y .detail-value';
	var timePath = '.info-time .detail-value';
	var x = meta.x;
	var y = meta.y;
	var time = meta.time;

	v.currentTime = meta.time;
	var $sidebarInfo = $('.sidebar-info');
	$sidebarInfo.find(xPath).text(x); //set the text
	$sidebarInfo.find(yPath).text(y);
	$sidebarInfo.find(timePath).text(time);

}

function fillNotes(notesArray) {

	var $noteModal = $('#noteModal');

	$.each(notesArray, function(i) {

		var $template;
		if (i == 0)
			$template = $noteModal.find('.content-area>div');
		else
			$template = addOperation('noteModal');

		$template.find('input').val(this.text);
	})


}

function fillQuiz(quizArray, id, hasChoice) {

	var $modal = $('#' + id);

	$.each(quizArray, function(i) {

		var $template;
		if (i == 0)
			$template = $modal.find('.content-area>div');
		else
			$template = addOperation(id);

		$template.find('input').val(this.question);

		if (hasChoice) {
			var that = this;
			var $choices = $template.find('.choices .choice');
			$.each($choices, function(i){
				$(this).find('.choice-text').text(that.text);
				if (that.choices[i].isCorrect)
					$(this).find('.check-answer').addClass('correct-answer');
			})

		} else {
			$template.find('textarea').val(this.answer);
		}
	})

}



function popupBox(text, second) {
	
	second = second*1000 || 1000;
	
	var $box = $('#popup-box');
	var $ele = $box.find('h4');
	$ele.empty(); // init
	$ele.text(text);
	$box.addClass('box-showup');
	setTimeout(function(){
		$box.removeClass('box-showup');
	}, second);
	
}


function addTimeLog(res) {

	var $timeContainer = $('#timeIndex');
	$timeContainer.find('.no-time-log').addClass('hidden');
	var $timeLog = $('<li>');
	var $link = $('<a href>');
	$link.attr('data-event-id', res.eventId);
	$link.text(res.timestamp)
	$link.addClass('time-log');
	$timeLog.append($link);
	$timeContainer.append($timeLog);
}

function getMeta() {
	var $sidebar = $('.sidebar');
	var x = $sidebar.find('.sidebar-info .info-x .detail-value').text();
	var y = $sidebar.find('.sidebar-info .info-y .detail-value').text();
	var time = $sidebar.find('.sidebar-info .info-time .detail-value').text();
	var obj = {
		x: x,
		y: y,
		time: time
	}
	return obj;
}




function inputValidation($contents) {

	var hasChoice = $contents.find('.choices').length > 0;
	var errorMsg ='';

	$.each($contents, function(i) {

		var input = $(this).find('input');
		if ($contents.length <= 1 && !input.val()) {
			// didn't fill this
			return;
		}

		if (!input.val()) {
			if (!errorMsg) {
				errorMsg = 'didn\'t fill in input';
				return;
			}
		}

		var $area = $(this).find('textarea');
		if ($area.length > 0 && !$area.val()) {
			if (!errorMsg) {
				errorMsg = 'didn\'t provider your answer';
				return;
			}
		}

		if (hasChoice) {
			var $choices = $(this).find('.choices');
			if ($choices.find('.correct-answer').length == 0) {
				if (!errorMsg) {
					errorMsg = 'At least one correct answer for each question';
					return;
				}					
			}
		}


	})

	return errorMsg;
}

function addOperation(id) {
	var $modal = $('#' + id);
	var $template = $modal.find('.input-template').clone().removeClass('hidden input-template').addClass('extra-version');

	var $target = $modal.find('.content-area');

	$target.append('<hr>').append($template);
	$template.hide().slideDown();
	return $template;
}

function editOperation($choice, isEdit) { //isEdit??

	var $text = $choice.find('.choice-text');
	var $editBtn = $choice.find('.edit');
	var $saveBtn = $choice.find('.save');
	if (isEdit) {
		$text.attr('contentEditable', true).addClass('form-control');
		$editBtn.addClass('hidden');
		$saveBtn.removeClass('hidden');
	} else {
		// save
		$text.attr('contentEditable', false).removeClass('form-control');
		$editBtn.removeClass('hidden');
		$saveBtn.addClass('hidden');
	}
}

function checkAnswerOperation($choices, $target, isMultiple) {

	if (isMultiple) {
		$target.toggleClass('correct-answer');
	} else {
		$choices.find('.correct-answer').removeClass('correct-answer');
		$target.addClass('correct-answer');
	}

}

function initModal($modal) {
	var $template = $modal.find('.input-template').clone().removeClass('hidden input-template').add('original-version');
	$modal.find('.content-area').empty();
	$modal.find('.content-area').append($template);
	$modal.find('.correct-answer').removeClass('correct-answer');
}

function Event() {
	self = this;


}

function getNotes($modal) {
	var $inputs = $modal.find('.content-area input');
	var res = [];
	$.each($inputs, function(i){
		if ($(this).val()) {
			var obj = {};
			obj.text = $(this).val();
			res.push(obj);			
		}

	})

	return res;
}


function getTextQuiz($modal) {
	var $textQuestions = $modal.find('.content-area .text-questions');
	var res = [];
	$.each($textQuestions, function(i){
		var question = $(this).find('input').val();
		var answer = $(this).find('textarea').val();

		if (question && answer) {
			var q = {};
			q.question = question;
			q.answer = answer;
			res.push(q);
		}
			
	})

	return res;
}

function getCheckBoxQuiz($modal) {
	var $checkBoxQuestions = $modal.find('.content-area .check-box-questions');
	var res = [];
	$.each($checkBoxQuestions, function(i){
		var question = $(this).find('input').val();

		var $choices = $(this).find('.choices');
		var choices = getChoices($choices);



		if (question) {
			var q = {};
			q.question = question;
			q.choices = choices;
			res.push(q);
		}
			
	})

	return res;
}

function getMultipleChoiceQuiz($modal) {
	var $multipleChoicesQuestions = $modal.find('.content-area .multiple-choices-questions');
	var res = [];
	$.each($multipleChoicesQuestions, function(i){
		var question = $(this).find('input').val();

		var $choices = $(this).find('.choices');
		var choices = getChoices($choices);

		if (question) {
			var q = {};
			q.question = question;
			q.choices = choices;
			res.push(q);
		}
			
	})

	return res;
}


function getChoices($choices) {

	var $allChoices = $choices.find('.choice');
	var res = [];
	$.each($allChoices, function(i){

		var text = $(this).find('.choice-text').text();
		var isRight = $(this).find('.check-answer').hasClass('correct-answer');
		var choice = {
			text: text,
			isCorrect: isRight
		};

		res.push(choice);
	})
	return res;

}



// function Note() {

// 	var self = this;
// 	self.cache = null;
// 	var url = '';

// 	this.save = function(id, obj, callback) {

// 		$.ajax({
// 			type: 'post',
// 			url: url,
// 			data: JSON.stringify(obj), // may change
// 			contentType: 'application/json',
// 			success: function (data) {
// 				self.cache = data;
// 				callback(data);
// 			}
// 		});

// 	}

// 	this.get = function(id, callback) {
// 		if (!cache) {

// 			$.ajax({
// 				type: 'get',
// 				url: url,
// 				data: {id: id}, // may change
// 				contentType: 'application/json',
// 				success: function (data) {
// 					self.cache = data;
// 					callback(data);
// 				}
// 			});

// 		} else {
// 			callback(self.cache);
// 		}
// 	}

// }


// function TextQuiz() {
	
// }

// function CheckBoxQuiz() {
	
// }

// function MultipleChoiceQuiz() {
	
// }


//postEvent
//catch x, y, time
// function postEvent() {
// //get the x, y, time value
// 	var $videoInfo = $('.videoInfo');
// 	var xPath = '.info-x .detail-value';
// 	var yPath = '.info-y .detail-value';
// 	var timePath = '.info-time .detail-value';
// 	var x = $videoInfo.find(xPath).text();
// 	var y = $videoInfo.find(yPath).text();
// 	var time = $videoInfo.find(timePath).text();

// //get the content from note
// function saveNote(){

// }



// //post it in json
//     $.ajax("sampletextcontent.json", //?????url
//              {success: initModal, type: "POST", dataType: "json" }); //?????success:clean all the modal
//         }

//     successFn)

