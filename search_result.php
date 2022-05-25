<?php
  include'/login_server.php';
?>
<!doctype html>
<head>
<meta charset="UTF-8">
<title>유전체 게시판</title>
</head>
<body>
<div id="GeneSerch_area"> 
<!-- 18.10.11 검색 추가 -->
<?php
 
  /* 검색 변수 */
  $catagory = $_GET['catgo'];
  $search_con = $_GET['search'];
?>
    <?php if($catagory=='Patients_id'){
        $catname = '환자 아이디';
    } else if($catagory=='SEX'){
        $catname = '성별';
    } else if($catagory=='AGE'){
        $catname = '나이';
    } else if($catagory=='HEIGHT'){
        $catname = '신장';
    }else if($catagory=='WEIGHT'){
        $catname = '무게';
    }else if($catagory=='Lipids_TCHL'){
        $catname = '지질';
    }else if($catagory=='blood_pressure_SBP'){
        $catname = '최고혈압';
    }else if($catagory=='blood_pressure_DBP'){
        $catname = '최저혈압';
    }?>
  <h1><?php echo $catname; ?>:<?php echo $search_con; ?>검색결과</h1>
  <h4 style="margin-top:30px;"><a href="/db.php">홈으로</a></h4>
    <table class="list-table">
      <thead>
          <tr>
              <th width="100">환자아이디</th>
                <th width="100">성별</th>
                <th width="100">나이</th>
                <th width="100">신장</th>
                <th width="100">무게</th>
                <th width="100">지질</th>
                <th width="100">최고혈압</th>
                <th width="100">최저혈압</th>
            </tr>
        </thead>
        <?php
          $sql2 = mq("select * from patients where $catagory like '%$search_con%' order by idx desc");
          $result = mysqli_query( $conn, $query );
          while( $rows = $sql2 -> fetch_array($result)){

          $Patients_id=$board["Patients_id"]; 
            if(strlen($Patients_id)>1)
              { 
                $Patients_id=str_replace($board["Patients_id"],mb_substr($board["Patients_id"],0,30,"utf-8")."...",$board["Patients_id"]);
              }
            $sql3 = query("select * from patients where con_num='".$board['idx']."'");
            $rep_count = mysqli_num_rows($sql3);
        ?>
      <tbody>
        <tr>
          <td width="70"><?php echo $board['idx']; ?></td>
          <td width="500">
            <?php 
              $lockimg = "<img src='/img/lock.png' Patients_id='20' SEX='20' AGE='20' HEIGHT='20' WEIGHT='20' Lipids_TCHL='20' blood_pressure_SBP='20' blood_pressure_DBP='20' />";
              if($board['lock_post']=="1")
              { ?><a href='/login_server.php?idx=<?php echo $board["idx"];?>'><?php echo $Patients_id;
              }else{?>

       
        <?php
          ?>
        <!--- 추가부분 18.08.01 END -->
        <a href='/db.php?idx=<?php echo $board["idx"]; ?>'><span style="background:yellow;"><?php echo $Patients_id; }?></span><span class="re_ct">[<?php echo $rep_count;?>]<?php echo $img; ?> </span></a></td>
          <td width="120"><?php echo $board['name']?></td>
          <td width="100"><?php echo $board['date']?></td>

        </tr>
      </tbody>

      <?php } ?>
    </table>
    <!-- 18.10.11 검색 추가 -->
    <div id="search_box2">
      <form action="/search_result.php" method="get">
      <select name="catgo">
      <option value = "Patients_id">환자아이디</option>
                  <option value = "SEX">성별</option>
                  <option value = "AGE">나이</option>
                  <option value = "HEIGHT">신장</option>
                  <option value = "WEIGHT">몸무게</option>
                  <option value = "Lipids_TCHL">지질_TCHL</option>
                  <option value = "blood_pressure_SBP">최고혈압</option>
                  <option value = "blood_pressure_DBP">최저혈압</option>
      </select>
      <input type="text" name="search" size="40" required="required"/> <button>검색</button>
    </form>
  </div>
</div>
</body>
</html>
