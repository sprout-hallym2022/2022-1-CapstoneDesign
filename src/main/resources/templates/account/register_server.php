<?php
include('db.php');
if(isset($_POST['user_id']) &&isset($_POST['user_nick']) &&isset($_POST['pass1']) &&isset($_POST['pass2']))
{
    //보안을 더욱 강화
    $user_id = mysqli_real_escape_string($conn,$_POST['user_id']);
    $user_nick = mysqli_real_escape_string($conn,$_POST['user_nick']);
    $user_pass1 = mysqli_real_escape_string($conn,$_POST['pass1']);
    $user_pass2 = mysqli_real_escape_string($conn,$_POST['pass2']);

    //에러를 체크
    if(empty($user_id)){
        echo "<script>alert('아이디가 비어있습니다.');history.back();</script>";
    }else if(empty($user_nick)){
        echo "<script>alert('닉네임이 비어있습니다.');location.replace('register_view.php');</script>";
    }else if(empty($user_pass1)){
        echo "<script>alert('비밀번호가 비어있습니다.');history.back();</script>";
    }else if(empty($user_pass2)){
        echo "<script>alert('비밀번호가 비어있습니다.');history.back();</script>";
    }else if($user_pass1 !== $user_pass2){
        echo "<script>alert('비밀번호가 일치하지 않습니다.');history.back();</script>";
    }else{ 
        //암호화
        $user_pass1 = password_hash($user_pass1, PASSWORD_DEFAULT); //단방향 암호

        //아이디 또는 닉네임, 또는 아이디와 동시에 닉네임 중복체크
        $sql_same = "SELECT * FROM member where mb_id = '$user_id' and mb_nick = '$user_nick'";
        $order = mysqli_query($conn, $sql_same);

        if(mysqli_num_rows($order) > 0){ //가로열에 $order 명령이 하나라도 있는지 확인 있으면 중복된 값으로 에러
            echo "<script>alert('아이디 또는 닉네임이 이미 있어요.');history.back();</script>";
        }else{ //에러가 없다면
            $sql_save = "insert into member(mb_id,mb_nick,password) values('$user_id','$user_nick','$user_pass1')";
            $result = mysqli_query($conn,$sql_save);

            if($result){
                echo "<script>alert('성공적으로 가입이 되었습니다.');</script>";
            }else{
                echo "<script>alert('가입에 실패하였습니다.');</script>";
            }
            

        }


      

    }


    

}
else
{
   
}
