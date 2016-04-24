/**
 * Get case name and go to video update interface
 */

$(document).ready(function() {
	$('#btn-create').click(function(e) {
		e.preventDefault();
		
		var caseName = $('#caseName').val();
		var caseDescription = $('#caseDescription').val();
		if(inputValue(caseName, caseDescription)) {
			var data = {
				caseName: caseName, 
				caseDescription: caseDescription
				};
			$.ajax({
				type: 'POST',
				url: "/case",
				data: data,
				dataType: "json",
				success: function (response) {
					console.log(response);
					if (response.notExist) {
						$.get("/uploadVideo", data); //transfer case data into video.
						window.location = "/upload.html";				
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