<%@ page contentType = "text/css" %>
body {
	margin: 0;
	padding: 0;
}

table.zdp-data-grid {
    border-collapse: collapse;
    width: 50%;
}
table.zdp-data-grid td,th{
    border: 1px solid #000;
    padding: 2px 10px
}
table.zdp-data-grid th{
    background-color: #716060;
    color: black;
    font-weight: bold;
    height: 30px;
}
table.zdp-data-grid tbody tr{
	height: 25px;
}
table.zdp-data-grid tbody tr:nth-child(even){
    background-color: lightgray;
}
table.zdp-data-grid tbody tr:nth-child(odd):hover{
    background-color: darkgray;
}
table.zdp-data-grid tbody tr:nth-child(even):hover{
    background-color: #eeeeee;
}
.clickable {
    cursor: pointer;
    text-decoration: underline;
    color:mediumblue;
}

.page-header {
	height: 30px;
    border-bottom: 3px solid #0909e4;
    border-top: 3px solid #0909e4;
    padding: 20px;
    font-size: 24px;
    background-color: #d2d6e0;
}

.page-footer {
	height: 30px;
    border-top: 3px solid #b6b6d6;
    padding: 5px;
    font-size: 24px;
    color: white;
    background-color: #404044;
    bottom: 0;
    position: absolute;
    width: 100%;
    text-align: center;
}

.page-content {
	padding: 30px;	
}

.msg-error {
	color: red;
}

.msg-success {
	color: green;
}
