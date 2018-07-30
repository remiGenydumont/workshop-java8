package java8.ex03;

import java8.data.Data;
import java8.data.Person;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 03 - Méthode statique
 */
public class Method_03_Test {

    // tag::IDao[]
    interface IDao {
        List<Person> findAll();

        // TODO créer use méthode statique IDao getDefaultInstance()
        // TODO cette méthode retourne une instance de la classe DaoA
        public static DaoA getDefaultInstance(){
        	Method_03_Test mTest = new Method_03_Test();
        	Method_03_Test.DaoA daoA = mTest.new DaoA();
        	return daoA ;
        }
    }
    // end::IDao[]

    class DaoA implements IDao {

        List<Person> people = Data.buildPersonList(20);

        @Override
        public List<Person> findAll() {
            return people;
        }

    }

    @Test
    public void test_getDefaultInstance() throws Exception {
        // TODO invoquer la méthode getDefaultInstance() pour que le test soit passant
        IDao result = IDao.getDefaultInstance();

        assertThat(result.findAll(), hasSize(20));
    }
}
