<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/imgupload.css">
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="./js/imgupload.js"></script>
<title>WM Project - Upload</title>
</head>
<body>
	<div id="main_div">
		<div id="top_div">
			<font id="top_font">이미지 업로드</font>
		</div><br>
		<div style="float:left;width:100%;padding-top:10px;padding-bottom:10px;">
			<form action="./imgupload" method="post" enctype="multipart/form-data" id="uploadform">
			<div style="display:inline-block;width:230px;border:1px solid black;padding:5px 10px 5px 10px;">
				<input type="file" name="filename" accept="image/gif,image/jpeg,image/png" id="filename">
			</div><br>
				<button id="btn_submit" type="button" onclick="img_upload();">업로드</button>
			</form>
		</div>
	</div>
</body>
</html>