<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>signup</title>
</head>

<body>
    <h1 style="text-align: center">UOS 25</h1>
    <hr size="60px">
    <form style="margin-left: 500px; margin-top: 50px">
        <div class="input-group" style="width: 300px;">
            <input type="text" class="form-control" placeholder="ID" aria-describedby="basic-addon2">
        </div>
        <div class="input-group" style="width: 300px;">
            <input type="text" class="form-control" placeholder="PW" aria-describedby="basic-addon2">
        </div>
        <div class="input-group" style="width: 300px;">
            <input type="text" class="form-control" placeholder="이름" aria-describedby="basic-addon2">
            남<input type="radio" name="sex" id="r1" aria-label="남" value="남">
            &nbsp;
            여<input type="radio" name="sex" id="r2" aria-label="여" value="여">
        </div>
        <div class="input-group" style="width: 300px;">
            <input type="text" class="form-control" placeholder="이메일" aria-describedby="basic-addon2">
        </div>
        <div class="input-group" style="width: 300px;">
            <input type="text" class="form-control" placeholder="전화번호" aria-describedby="basic-addon2">
        </div>
        <br>
        <p><a class="btn btn-primary btn-lg" type="submit" href="#" role="button" style="margin-left: 100px">회원가입</a>
        </p>

    </form>

    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>

</html>