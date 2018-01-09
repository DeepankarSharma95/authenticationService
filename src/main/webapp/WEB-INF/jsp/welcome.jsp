<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title> Simple File Upload Application using XHR
</title>
<script type="text/javascript">
function xhrUpload() {
var formData = new FormData();
formData.append("file", document.getElementsByName("file")[0].files[0]);
var xhr = new XMLHttpRequest();
xhr.onreadystatechange = function(e) {
                if ( 4 == this.readyState ) {
                    document.body.innerHTML = this.responseText;
                }
            };
xhr.open('post', '${pageContext.request.contextPath}/upload');
xhr.send(formData);
}
</script>
</head>
<body>
<h1>Simple File Upload Application</h1>

<form method="POST" action="${pageContext.request.contextPath}/upload"
           id="fileUploadForm">

    <input type="file" name="file" /><br/>
    <input type="button" value="Submit" onClick="xhrUpload();" />

</form>

</body>
</html>