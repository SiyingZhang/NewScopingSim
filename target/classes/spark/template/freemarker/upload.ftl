<!DOCTYPE html>
<html>
<head>
	<title>ScopingSim-Upload video</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<!--external css-->
	<link rel="stylesheet" type="text/css"  href="css/styles.css" />
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<style>
		.heading {
			color: #515151;
			font-family: "Verdana", sans-serif;
			text-align: center;
			position: relative;
			margin-top: 10%;
		}
		.subheading {
			color: #B1B3B2;
			font-family: "Times New Roman", Times, sans-serif;
			font-style: italic;
			text-align: center;
			position: relative;
		}
		.wrapper {
			text-align: center;
			vertical-align: middle;
		}
		#createbutton {
			position: relative;
			margin-left: auto;
			margin-right: auto;
			margin-top: 5%;
		}
	</style>
</head>
<body>
	<!--navigation bar-->
	<nav class="navbar navbar-default"style="margin-bottom: 0px; position: relative; height: 8%">
		<div class="navbar-header">
			<!--left dropdown menu-->
			 <ul class="nav navbar-nav navbar-left">
			    <li class="dropdown" >
			      <a id="eventLog" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Event Log <span class="caret"></span></a>
			      <ul id="timeIndex" class="dropdown-menu">
			        <li><a href="#">0:04</a></li>
			        <li><a href="#">0:15</a></li>
			        <li><a href="#">1:30</a></li>
			      </ul>
			    </li><!--dropdown-->
			 </ul><!--nav-->
		</div>

		<div class="navbar-collapse collapse" id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li id="user"><a href="#"><span class="check-answer glyphicon glyphicon-user"></span> Dmitriy Babichenko</a></li>
			</ul>
		</div>
		<h2 id="title"><a href="#">ScopingSim</a></h2>
		
	    <!--title-->
	    <!--right user logo-->
	 <!--   <a class="navbar-brand" href="#">UserName</a>-->
	</nav>
	<h2 class="heading">Uploading video under "${caseName}" case</h2>
	<h4 class="subheading">Editing more after uploading</h4>
	<!-- Trigger the modal with a button -->
	<div class="wrapper">
		<button id="createbutton" type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Upload a Video</button>
	</div>

	<!-- Modal -->
	<div id="myModal" class="modal fade" role="dialog">
	  <div class="modal-dialog">

	    <!-- Case content-->
	    <div class="modal-content">
	    	<div class="modal-header">
	        	<button type="button" class="close" data-dismiss="modal">&times;</button>
	        	<h4 id="head-text" class="modal-title">Video Settings</h4>
	      	</div>
			<div class="modal-body">
				<div class="input-group">
					<label>Name</label>
					<input type="text" class="form-control" placeholder="Video Name" aria-describedby="sizing-addon2">
				</div>
				<div class="input-group">
					<label>Description</label>
					<input type="text" class="form-control" placeholder="Video Description" aria-describedby="sizing-addon2">
				</div>
			</div>
	      	<div class="modal-footer">
	      		<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
	        	<button type="button" class="btn btn-primary">Upload</button>        	
	      	</div>
	    </div>

	  </div>
	</div>
</body>
</html>