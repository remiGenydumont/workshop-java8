package java8.ex03;

import java8.data.Person;
import org.junit.Test;

import java.util.function.BinaryOperator;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 03 - java.util.function.BinaryOperator
 */
public class Function_03_Test {

    //  tag::makeAChild[]
    // TODO Compléter la fonction makeAChild
    // TODO l'enfant possède le nom du père
    // TODO l'enfant possède le prenom "<PRENOM_PERE> <PRENOM_MERE>"
    // TODO l'age de l'enfant est 0
    // TODO le mot de passe de l'enfant est null
    BinaryOperator<Person> makeAChild = ((p1,p2)->{
    	Person child = new Person();
    	child.setAge(0);
    	child.setLastname(p2.getLastname());
    	child.setFirstname(p2.getFirstname()+" "+p1.getFirstname());
    	return child;
    });
    //  end::makeAChild[]


    @Test
    public void test_makeAChild() throws Exception {

        Person father = new Person("John", "France", 25, "johndoe");
        Person mother = new Person("Aline", "Lebreton", 22, "alino");

        // TODO compléter le test pour qu'il soit passant
        Person child = makeAChild.apply(mother, father);

        assertThat(child, hasProperty("firstname", is("John Aline")));
        assertThat(child, hasProperty("lastname", is("France")));
        assertThat(child, hasProperty("age", is(0)));
        assertThat(child, hasProperty("password", nullValue()));
    }

}
