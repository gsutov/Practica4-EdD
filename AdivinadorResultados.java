import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase principal que analiza y compara el rendimiento de un participante de referencia
 * frente a un grupo de participantes, a partir de datos leídos desde un archivo de texto.
 * <p>
 * El archivo {@code participantes.txt} debe tener un participante por línea, con el formato:
 * <pre>
 *   nombre, M: medallas, P: participaciones, C: cinta
 * </pre>
 * La primera línea corresponde al participante de referencia (alumno A) y el resto
 * conforman el grupo de comparación.
 */
public class AdivinadorResultados{

	/**
	 * Devuelve el subconjunto de participantes del grupo {@code g} a quienes el
	 * participante {@code a} supera. Se considera que {@code a} gana si su índice
	 * de victoria es estrictamente mayor, o si es igual pero su cinta tiene un
	 * nivel superior.
	 *
	 * @param a el participante de referencia
	 * @param g la lista de participantes del grupo
	 * @return una lista con los participantes a quienes {@code a} supera
	 */
	public static ListaLigadaSimple<Participante> alumnoALesGana(Participante a, ListaLigadaSimple<Participante> g){
		ListaLigadaSimple<Participante> ganados = new ListaLigadaSimple<>();
		for(Participante p: g){
			if(a.indiceVic() > p.indiceVic()){
				ganados.agregar(p);
			} else if(a.indiceVic() == p.indiceVic() && a.getCinta().nivelCinta() > p.getCinta().nivelCinta()){
				ganados.agregar(p);
			}
		}
		return ganados;
	}

	/**
	 * Devuelve el subconjunto de participantes del grupo {@code g} que superan al
	 * participante de referencia {@code a}. Se considera que {@code a} pierde si su
	 * índice de victoria es estrictamente menor, o si es igual pero su cinta tiene
	 * un nivel inferior.
	 *
	 * @param a el participante de referencia
	 * @param g la lista de participantes del grupo
	 * @return una lista con los participantes que superan a {@code a}
	 */
	public static ListaLigadaSimple<Participante> alumnoAPierde(Participante a, ListaLigadaSimple<Participante> g){
		ListaLigadaSimple<Participante> perdidos = new ListaLigadaSimple<>();
		for(Participante p: g){
			if(a.indiceVic() < p.indiceVic()){
				perdidos.agregar(p);
			} else if(a.indiceVic() == p.indiceVic() && a.getCinta().nivelCinta() < p.getCinta().nivelCinta()){
				perdidos.agregar(p);
			}
		}
		return perdidos;
	}

	/**
	 * Devuelve una lista con los índices de victoria de todos los participantes
	 * del grupo {@code g}.
	 *
	 * @param g la lista de participantes del grupo
	 * @return una lista de {@code Double} con el índice de victoria de cada participante
	 */
	public static ListaLigadaSimple<Double> indicesVictoria(ListaLigadaSimple<Participante> g){
		ListaLigadaSimple<Double> indices = new ListaLigadaSimple<>();
		for(Participante p: g){
			indices.agregar(p.indiceVic());
		}
		return indices;
	}

	/**
	 * Calcula el promedio de los índices de victoria de todos los participantes
	 * del grupo {@code g}.
	 *
	 * @param g la lista de participantes del grupo
	 * @return el promedio de los índices de victoria del grupo
	 */
	public static double indiceVictoriaPromedio(ListaLigadaSimple<Participante> g){
		double promedio = 0;
		for(Participante p: g){
			promedio += p.indiceVic();
		}
		return promedio/g.devolverLongitud();
	}

	/**
	 * Devuelve los participantes del grupo {@code g} que tienen la misma cinta
	 * que el participante de referencia {@code a}.
	 *
	 * @param a el participante de referencia
	 * @param g la lista de participantes del grupo
	 * @return una lista con los participantes cuya cinta coincide con la de {@code a}
	 */
	public static ListaLigadaSimple<Participante> mismaCinta(Participante a, ListaLigadaSimple<Participante> g){
		ListaLigadaSimple<Participante> iguales = new ListaLigadaSimple<>();
		for(Participante p: g){
			if(a.getCinta().nivelCinta() == p.getCinta().nivelCinta()){
				iguales.agregar(p);
			}
		}
		return iguales;
	}

	/**
	 * Devuelve los participantes del grupo {@code g} cuyo índice de victoria es
	 * similar al del participante de referencia {@code a}, con una tolerancia de +-0.10.
	 * <p>
	 * Se considera similar si la diferencia absoluta entre los índices de victoria
	 * satisface: {@code |a.indiceVic() - p.indiceVic()| <= 0.10}
	 *
	 * @param a el participante de referencia
	 * @param g la lista de participantes del grupo
	 * @return una lista con los participantes cuyo índice de victoria se encuentra
	 *         dentro del rango [{@code a.indiceVic() - 0.10}, {@code a.indiceVic() + 0.10}]
	 */
	public static ListaLigadaSimple<Participante> indicesimilarAlAlumnoA(Participante a, ListaLigadaSimple<Participante> g){
		ListaLigadaSimple<Participante> similar = new ListaLigadaSimple<>();
		for(Participante p: g){
			if((a.indiceVic() - p.indiceVic()) <= 0.10 && (a.indiceVic() - p.indiceVic()) >= -0.10){
				similar.agregar(p);
			}
		}
		return similar;
	}

	/**
	 * Método auxiliar que parsea una línea del archivo de texto y construye un objeto
	 * {@code Participante} a partir de ella.
	 * <p>
	 * El formato esperado de la línea es:
	 * <pre>
	 *   nombre, M: medallas, P: participaciones, C: cinta
	 * </pre>
	 *
	 * @param linea la cadena de texto con los datos del participante
	 * @return un nuevo objeto {@code Participante} con los datos extraídos de la línea
	 */
	private static Participante obtenerDatos(String linea){
		int c1 = linea.indexOf(",");
		int c2 = linea.indexOf(",", c1 + 1);
		int c3 = linea.indexOf(",", c2 + 1);

		String nombre = linea.substring(0, c1);
		int medallas = Integer.parseInt(linea.substring(c1 + 3, c2));
		int participaciones = Integer.parseInt(linea.substring(c2 + 3, c3));
		Cinta cinta = new Cinta(linea.substring(c3 + 3));

		return new Participante(nombre, medallas, participaciones, cinta);
	}

	/**
	 * Método principal que lee el archivo {@code participantes.txt}, construye los
	 * objetos {@code Participante} y ejecuta los distintos análisis comparativos,
	 * imprimiendo los resultados por consola.
	 * <p>
	 * El primer participante del archivo actúa como alumno de referencia.
	 * El resto conforman el grupo de comparación.
	 *
	 * @param args argumentos de línea de comandos (no se utilizan)
	 */
	public static void main(String[] args){
		Participante a = null;
		ListaLigadaSimple<Participante> g = new ListaLigadaSimple<>();

		try{
			BufferedReader br = new BufferedReader(new FileReader("participantes.txt"));
			String lineaPrimera = br.readLine();
			a = obtenerDatos(lineaPrimera);

			String linea;
			while((linea = br.readLine()) != null){
				g.agregar(obtenerDatos(linea));
			}
			br.close();
		} catch(IOException e){
			e.printStackTrace();
		}

		System.out.println("Alumno de referencia: " + a.nombre);

		System.out.println("\nA quienes les gana:");
		ListaLigadaSimple<Participante> gana = alumnoALesGana(a, g);
		for(Participante p: gana){
			System.out.println(p.nombre + ",M:" + p.numMedallas + ",P:" + p.numParticipaciones + ",C:" + p.cinta.colorCinta);
		}

		System.out.println("\nA quienes pierde:");
		ListaLigadaSimple<Participante> pierde = alumnoAPierde(a, g);
		for(Participante p: pierde){
			System.out.println(p.nombre + ",M:" + p.numMedallas + ",P:" + p.numParticipaciones + ",C:" + p.cinta.colorCinta);
		}

		System.out.println("\nIndices Victoria:");
		ListaLigadaSimple<Double> indices = indicesVictoria(g);
		for(Double d: indices){
			System.out.println(d);
		}

		System.out.println("\nIndice Promedio:");
		double promedio = indiceVictoriaPromedio(g);
		System.out.println(promedio);

		System.out.println("\nMisma cinta que " + a.nombre + ":");
		ListaLigadaSimple<Participante> iguales = mismaCinta(a, g);
		for(Participante p: iguales){
			System.out.println(p.nombre + ",M:" + p.numMedallas + ",P:" + p.numParticipaciones + ",C:" + p.cinta.colorCinta);
		}

		System.out.println("\nIndice similar a " + a.nombre + ":");
		ListaLigadaSimple<Participante> similar = indicesimilarAlAlumnoA(a, g);
		for(Participante p: similar){
			System.out.println(p.nombre + ",M:" + p.numMedallas + ",P:" + p.numParticipaciones + ",C:" + p.cinta.colorCinta);
		}
	}
}
