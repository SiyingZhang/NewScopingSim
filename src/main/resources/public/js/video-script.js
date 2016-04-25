/**
 * Get video name and go to video update interface
 */

$(document).ready(function() {
	$('#btn-upload').click(function(e) {
		e.preventDefault();
		
		var videoName = $('#videoName').val();
		var videoPath = "/user/Documents/scopingsim/stock.mp4";
		if(inputValue(caseName, caseDescription)) {
			var data = {
				videoName: videoName, 
				videoPath: videoPath
				};
			$.ajax({
				type: 'POST',
				url: "/uploadVideo",
				data: data,
				dataType: "json",
				success: function (response) {
					console.log(response);
					if (response.notExist) {
						//$.get("/uploadVideo", data); //transfer case data into video.
						
						window.location = "/videoEditor.html";				
					} else {
						$("#caseName").val("");
						$('#caseDescription').val("");
					}
				},
				error: function(jqXHR, textStatus, errorThrown) {		
					console.log(errorThrown);
				},
			});
		}
	});
});

function inputValue(caseName, caseDescription) {
	var allClear = true;
	var RED = "#FC2B6A";
	var GREEN = "#02ccba";
	//Do some judgements
	if(caseName == "") {
		$('#caseName').css("border-color", RED);
		allClear = false;
	} else {
		$('#caseName').css("border-color", GREEN);
	}
	return allClear;
}