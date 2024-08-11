<!doctype html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <title>Gene DB</title>
    <style>
      body {
        font-family: Consolas, monospace;
        font-family: 12px;
      }
      table {
        width: 100%;
      }
      th, td {
        padding: 10px;
        border-bottom: 1px solid #dadada;
      }
    </style>
  </head>
  <body>
  <div id="GeneSerch_area">
      <h1>유전체 게시판</h1>
      <h4>유전체를 자유롭게 찾을 수 있는 게시판입니다.</h4>
      <div id="search_box">
          <form action = "/templates/account/search_result.php" method = "get">
              <select name = "catgo">
                  <option value = "Patients_id">환자아이디</option>
                  <option value = "SEX">성별</option>
                  <option value = "AGE">나이</option>
                  <option value = "HEIGHT">신장</option>
                  <option value = "WEIGHT">몸무게</option>
                  <option value = "Lipids_TCHL">지질_TCHL</option>
                  <option value = "blood_pressure_SBP">최고혈압</option>
                  <option value = "blood_pressure_DBP">최저혈압</option>
    </select>
    <input type = "text" name = "search" size = "40" required = "required" /> <button>검색</button>
    </form>
    </div>    

    <table>
      <thead>
        <tr>
          <th>Patients_id</th>
          <th>SEX</th>
          <th>AGE</th>
          <th>HEIGHT</th>
          <th>WEIGHT</th>
          <th>Lipids_TCHL</th>
          <th>blood_pressure_SBP</th>
          <th>blood_pressure_DBP</th>
        </tr>
      </thead>
      <tbody>
        <?php
          $conn = mysqli_connect('localhost:3307','root','123456','sprout');
          $query = "SELECT * FROM patients";
          $result = mysqli_query( $conn, $query );
          while( $row = mysqli_fetch_array( $result ) ) {
            echo
            '<tr><td>'.
            $row["patients_id"].
            '</td><td>  '.
            $row["sex"].
            '</td><td>'.
            $row["age"].
            '</td><td>'.
            $row["height"].
            '</td><td>'.
            $row["weight"].
            '</td><td>'.
            $row["Lipids_TCHL"].
            '</td><td>'.
            $row["blood_pressure_SBP"].
            '</td><td>'.
            $row["blood_pressure_DBP"].
            '</td></tr>';

          }
        ?>
      </tbody>
    </table>
  </body>
</html>
