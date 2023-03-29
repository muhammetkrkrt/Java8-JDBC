package com.bilgeadam.repository;

import com.bilgeadam.entity.Person;
import com.bilgeadam.util.JDBCHelper;

import java.sql.*;

public class PersonRepository implements IPersonRepository{
    @Override
    public void insertPerson(Person person) {
        String sql = "insert into persons(first_name,last_name,email,joined_date) values(?,?,?,?)";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCHelper.getConnection();
            //transaction için otomatik yazmayı kapattık
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setDate(4, new Date(person.getJoinedDate().getTime()));
            preparedStatement.executeUpdate();

            //bilerek hata almak istiyorum
            //preparedStatement.setString(4, person.getEmail());
            //preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JDBCHelper.closeConnection(connection);
                JDBCHelper.closePreparedStatement(preparedStatement);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void displayPersons(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1)
                    + " " + resultSet.getString(2)
                    + " " + resultSet.getString(3)
                    + " " + resultSet.getDate(4)
                    + " " + resultSet.getString(5));
        }
    }

    @Override
    public void getAllPerson() {
        String sql = "select * from persons";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            displayPersons(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JDBCHelper.closeConnection(connection);
                JDBCHelper.closePreparedStatement(preparedStatement);
                JDBCHelper.closeResultSet(resultSet);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void deleteById(int id) {

    }

}

//Transaction--> Bir Run time exceptionda verilerin database yazılmamsını sağlar.
//1- Banka hesabınızdan bir para çıkışı olması lazım

//gödner butotnuna bastıkran sonra bir hata meydana gelirse ikiş işlemide geri alıyo

//2- göndermek istediğiniz kişinini hesabına gidiyor.

