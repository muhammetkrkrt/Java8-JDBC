import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        /*
            database oluşturalım school
            student tablomuz olsun--> name, surname city

            excute() parametre olarak bitane connection, sql sorugusu alcak
            aldıgı sql sorgusunu çalıştırcaz

            Student sınıfı oluşturcaz database uygun şekilde oluşturalım

            create methodu yazalım parametre olarak connection, student nesnelerini alsın

            update methodu yazalım
            parametre olarak --> connection, student, id

            //eski--> Mehmet kış izmir
            //yeni--> ali, yaz, istanbul
            //update(connection, student, id)

            //dışardan girdiğimiz şehir isminden kaç tane verimiz oldugunu bulalım
            countbyCityName(connection, String)

         */

        String url = "jdbc:postgresql://localhost:5432/school";
        String username = "postgres";
        String password = "12345";

        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Bağlantı Gerçekleşti");
            String sql = "insert into student(name,surname, city) values('Hakan','Yaz','Bodrum')";
            //String sql2 = "delete from student where id = 1";

            //execute(connection, sql);
            //Student student = new Student("Zeynep","Yagmur","İzmir",32);

            //createStudent(connection,student);
            //updateStudent(connection,student,3);

            //findStudentByCityName(connection, "istanbul");

            delete(connection,50);

        } catch (PSQLException e) {
            System.err.println("Lütfen database İsmini kontrol edin");
        } catch (Exception e) {
            System.err.println("Beklenmedik bir Hata meydana geldi");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("Bağlantı kapatıldı");
                }
            } catch (SQLException e) {
                System.err.println("Kapatma Hatası");
            }
        }
    }
    public static void execute(Connection connection, String sql) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("İşlem Başarılı");

        } catch (SQLException e) {
            System.out.println("Insert İşlemi Başarısız");
        }
    }

    public static void createStudent(Connection connection, Student student) {
        String sql = "insert into student(name,surname, city ,age) values(?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getCity());
            preparedStatement.setInt(4, student.getAge());

            preparedStatement.executeUpdate();
            System.out.println(student.getName() + " Veri Tabanına Eklendi");
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateStudent(Connection connection, Student student, int id){
        String sql = "update student set name=?, surname=? , city=?, age=? where id = ? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getCity());
            preparedStatement.setInt(4, student.getAge());
            preparedStatement.setInt(5, id);

            int effectedRow = preparedStatement.executeUpdate();
            if(effectedRow > 0){
                System.out.println("Update İşlemi Gerçekleşti");
            }else{
                System.out.println("Lütfen girilen Id yi kontrol edin");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void findStudentByCityName(Connection connection, String cityName){
        String sql = "select name, surname,age from student where lower(city) = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cityName.toLowerCase());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString(1) + " "
                        + resultSet.getString("surname") + " "
                        + resultSet.getInt(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //delete methodu connection, id
    public static void delete(Connection connection, int id){
        String sql = "delete from student where id =?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int effectedRow = preparedStatement.executeUpdate();
            if(effectedRow >0 ){
                System.out.println("Silme işlemi gerçekleşti");
            }else {
                System.out.println("Lütfen id yi kontrol edin");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
