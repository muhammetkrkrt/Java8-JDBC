import com.bilgeadam.controller.PersonController;
import com.bilgeadam.entity.Person;
import com.bilgeadam.repository.PersonRepository;
import com.bilgeadam.util.JDBCHelper;
import com.bilgeadam.util.Menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;


public class Main {

    /*
        1- personDb database oluşturalım --> id, first_name, last_name, joined_date
        entitymizde person sınıfı oluşturalım

        2- util paketine JDBCHelper diye bir class oluşturalım
        getConnection() diye bir method yazcaz bu method bize bir connection nesnesi döncek

        Database bilgilerinide farklı bi sınıftan çekeleim
        JDBCConstant diye bir sınıf oluşturalım db bilgilerini burda tanımlicaz

        3- repository katmanına insertPerson methodu yazalım parametre olarak person nesnesi

        4- getAllPersons() methodu yazalım


        HW-
        5- deleteAllRecords()
        6- updateEmail(String email, int id)--> transaction
        7- getPersonById(int id)
        8- deletePerson(int id)--> transaction
        menu


     */
    public static void main(String[] args) {

        //PersonRepository personRepository = new PersonRepository();
        //Person person = new Person("Hakan","Bilmez","Hakan@gmail.com",new Date());
        //personRepository.insertPerson(person);
        //personRepository.getAllPersons();

        //PersonController personController = new PersonController();
        //Person person = new Person("Mert","Bahar","Mertgmail.com",new Date());
        //personController.insertPerson(person);
        //personController.getAllPerson();

        Menu menu = new Menu();
        menu.menu();

    }

    //Bir somut sınıfm soyut bir sınıftan implement veya extends almazsa ilerde nesnellik zaafiyeti çekebiliriz
}