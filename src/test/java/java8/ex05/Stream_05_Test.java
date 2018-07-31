package java8.ex05;

import org.junit.Test;

import java8.data.domain.Customer;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchEvent.Modifier;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 5 - Files
 */
public class Stream_05_Test {

	// Chemin vers un fichier de données des naissances
	private static final String NAISSANCES_DEPUIS_1900_CSV = "./naissances_depuis_1900.csv";

	private static final String DATA_DIR = "./pizza-data";


	// Structure modélisant les informations d'une ligne du fichier
	class Naissance {
		String annee;
		String jour;
		Integer nombre;

		public Naissance(String annee, String jour, Integer nombre) {
			this.annee = annee;
			this.jour = jour;
			this.nombre = nombre;
		}

		public String getAnnee() {
			return annee;
		}

		public void setAnnee(String annee) {
			this.annee = annee;
		}

		public String getJour() {
			return jour;
		}

		public void setJour(String jour) {
			this.jour = jour;
		}

		public Integer getNombre() {
			return nombre;
		}

		public void setNombre(Integer nombre) {
			this.nombre = nombre;
		}
	}


	@Test
	public void test_group() throws IOException {

		// TODO utiliser la méthode java.nio.file.Files.lines pour créer un stream de lignes du fichier naissances_depuis_1900.csv
		// Le bloc try(...) permet de fermer (close()) le stream après utilisation
		try (Stream<String> lines = java.nio.file.Files.lines(Paths.get(NAISSANCES_DEPUIS_1900_CSV))) {
			
			// TODO construire une MAP (clé = année de naissance, valeur = somme des nombres de naissance de l'année)
			Map<String, Integer> result = lines.skip(1)
					.map(l->{
						String[] line = l.split(";");
						Naissance n = new Naissance(line[1], line[2], Integer.parseInt(line[3]));
						return n;
					})
					.collect(Collectors.groupingBy(Naissance::getAnnee, summingInt(n->n.getNombre())));

			assertThat(result.get("2015"), is(8097));
			assertThat(result.get("1900"), is(5130));
		}
	}

	@Test
	public void test_max() throws IOException {

		// TODO utiliser la méthode java.nio.file.Files.lines pour créer un stream de lignes du fichier naissances_depuis_1900.csv
		// Le bloc try(...) permet de fermer (close()) le stream après utilisation
		try (Stream<String> lines = java.nio.file.Files.lines(Paths.get(NAISSANCES_DEPUIS_1900_CSV))) {

			// TODO trouver l'année où il va eu le plus de nombre de naissance
			Comparator<Naissance> countNaissance = Comparator.comparing(Naissance::getNombre);
			Optional<Naissance> result = lines.skip(1)
					.map(l->{
						String[] line = l.split(";");
						Naissance n = new Naissance(line[1], line[2], Integer.parseInt(line[3]));
						return n;
					})
					.max(countNaissance);


			assertThat(result.get().getNombre(), is(48));
			assertThat(result.get().getJour(), is("19640228"));
			assertThat(result.get().getAnnee(), is("1964"));
		}
	}

	@Test
	public void test_collectingAndThen() throws IOException {
		// TODO utiliser la méthode java.nio.file.Files.lines pour créer un stream de lignes du fichier naissances_depuis_1900.csv
		// Le bloc try(...) permet de fermer (close()) le stream après utilisation
		try (Stream<String> lines = java.nio.file.Files.lines(Paths.get(NAISSANCES_DEPUIS_1900_CSV))) {

			// TODO construire une MAP (clé = année de naissance, valeur = maximum de nombre de naissances)
			// TODO utiliser la méthode "collectingAndThen" à la suite d'un "grouping"
			Map<String, Naissance> result = null ; //lines.skip(1)
//					.map(l->{
//						String[] line = l.split(";");
//						Naissance n = new Naissance(line[1], line[2], Integer.parseInt(line[3]));
//						return n;
//					})
//					.collect(Collectors.collectingAndThen(Collectors.groupingBy(Naissance::getAnnee, Naissance::getClass), finisher));

			assertThat(result.get("2015").getNombre(), is(38));
			assertThat(result.get("2015").getJour(), is("20150909"));
			assertThat(result.get("2015").getAnnee(), is("2015"));

			assertThat(result.get("1900").getNombre(), is(31));
			assertThat(result.get("1900").getJour(), is("19000123")); 
			assertThat(result.get("1900").getAnnee(), is("1900"));
		}
	}

	// Des données figurent dans le répertoire pizza-data
	// TODO explorer les fichiers pour voir leur forme
	// TODO compléter le test

	@Test
	public void test_pizzaData() throws IOException {
		// TODO utiliser la méthode java.nio.file.Files.list pour parcourir un répertoire

		// TODO trouver la pizza la moins chère
		String pizzaNamePriceMin = null;

		assertThat(pizzaNamePriceMin, is("L'indienne"));

	}

	// TODO Optionel
	// TODO Créer un test qui exporte des données new Data().getPizzas() dans des fichiers
	// TODO 1 fichier par pizza
	// TODO le nom du fichier est de la forme ID.txt (ex. 1.txt, 2.txt)

}