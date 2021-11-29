<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="m" %>
<html>
<head>
    <title>Register</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp"
          rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"
          integrity="sha512-UJfAaOlIRtdR+0P6C3KUoTDAxVTuy3lnSXLyLKlHYJlcSU8Juge/mjeaxDNMlw9LgeIotgz5FP8eUQPhX1q10A=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"
            integrity="sha512-NiWqa2rceHnN3Z5j6mSAvbwwg3tiwVNxiAQaaSMSXnRRDh5C2mk/+sKQRw8qjV1vN4nf8iK2a0b048PnHbyx+Q=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</head>
<body>
<m:headerJSP/>
<div class="section">
    <div class="row"><h2 class="header center-align">Registration Form</h2></div>

    <form action="register" method="post">
        <input type="hidden" name="command" value="register_command">
        <div class="row">
            <div class="input-field col s4 offset-s4">
                <i class="material-icons prefix">account_circle</i>
                <input class="validate"
                       type="text" id="userName" name="login">
                <label for="userName">Username</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s4 offset-s4">
                <i class="material-icons prefix">mail</i>
                <input class="validate"
                       type="text" id="email" name="email">
                <label for="email">Email</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s4 offset-s4">
                <i class="material-icons prefix">lock</i>
                <input class="validate" placeholder="Password"
                       type="password" id="password" name="password">
                <label for="password">Password</label>
            </div>
        </div>
        <div class="row">
            <button class="col offset-s7 btn waves-effect waves-light" type="submit" name="action">Register
                <i class="material-icons right">launch</i>
            </button>
        </div>

    </form>
</div>
</body>
</html>
