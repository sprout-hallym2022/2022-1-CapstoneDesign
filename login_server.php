<?php
include('db.php');
if(isset($_POST['user_id']) &&isset($_POST['pass1']) )
{
    //보안을 더욱 강화
    $user_id = mysqli_real_escape_string($conn,$_POST['user_id']);
    $user_pass1 = mysqli_real_escape_string($conn,$_POST['pass1']);
    
    //에러를 체크
    if(empty($user_id)){
        echo "<script>alert('아이디가 비어있습니다.');history.back();</script>";
    }else if(empty($user_pass1)){
        echo "<script>alert('비밀번호가 비어있습니다.');history.back();</script>";
    }else{ 
        $sql = "select * from member where mb_id = '$user_id'";
        $result = mysqli_query($conn, $sql);

        if(mysqli_num_rows($result) === 1){ //가로열에 $result가 하나라도 있으면 성공
            $row = mysqli_fetch_assoc($result);
            $hash = $row['password'];

            if (password_verify($user_pass1, $hash)){
                echo "<script>alert('성공적으로 로그인이 되었습니다.');</script>";
            }else{
                echo "<script>alert('로그인이 실패하였습니다.');</script>";
            }
           
        }else{
            echo "<script>alert('로그인에 실패하였습니다.');history.back();</script>";

        }
        
            

        }


      

    }
