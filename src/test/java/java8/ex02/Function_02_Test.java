package java8.ex02;

import java8.data.Account;
import java8.data.Person;
import org.junit.Test;

import java.util.function.BiFunction;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 02 - java.util.function.BiFunction
 */
public class Function_02_Test {

    //  tag::buildAccount[]
    // TODO Compléter la fonction buildAccount
    // TODO la fonction possède 2 paramètres en entrée : une personne et un solde
    BiFunction<Person, Integer, Account> buildAccount = ((p, s) ->{
    	Account acc = new Account();
    	acc.setOwner(p);
    	acc.setBalance(s);
    	return acc;
    });
    //  end::buildAccount[]

    @Test
    public void test_build_account() throws Exception {

    	Person personne = new Person();
    	personne.setAge(80);
    	personne.setFirstname("John");
    	personne.setLastname("France");
    	personne.setPassword("pass");
        // TODO invoquer la fonction buildAccount pour que le test soit passant
        Account account = buildAccount.apply(personne, 500);

        assertThat(account, hasProperty("balance", is(500)));
        assertThat(account.getOwner(), hasProperty("firstname", is("John")));
        assertThat(account.getOwner(), hasProperty("lastname", is("France")));
        assertThat(account.getOwner(), hasProperty("age", is(80)));
        assertThat(account.getOwner(), hasProperty("password", is("pass")));
    }


}
