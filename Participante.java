/**
 * Representa a un participante en un sistema de competición de artes marciales u
 * actividad similar, identificado por su nombre, número de medallas, número de
 * participaciones y su cinta de nivel.
 */
public class Participante{

	/** El nombre del participante. */
	public String nombre;

	/** El número de medallas obtenidas por el participante. */
	public int numMedallas;

	/** El número de competiciones o participaciones del participante. */
	public int numParticipaciones;

	/** La cinta que clasifica al participante según su nivel. */
	public Cinta cinta;

	/**
	 * Construye un nuevo {@code Participante} con los datos proporcionados.
	 *
	 * @param nombre             el nombre del participante
	 * @param numMedallas        el número de medallas obtenidas
	 * @param numParticipaciones el número de participaciones realizadas
	 * @param cinta              la cinta que indica el nivel del participante
	 */
	public Participante(String nombre, int numMedallas, int numParticipaciones, Cinta cinta){
		this.nombre = nombre;
		this.numMedallas = numMedallas;
		this.numParticipaciones = numParticipaciones;
		this.cinta = cinta;
	}

	/**
	 * Devuelve la cinta del participante.
	 *
	 * @return la cinta del participante
	 */
	public Cinta getCinta(){
		return cinta;
	}

	/**
	 * Calcula y devuelve el índice de victoria del participante.
	 * <p>
	 * El índice de victoria se obtiene dividiendo el número de medallas
	 * entre el número de participaciones.
	 *
	 * @return el cociente {@code numMedallas / numParticipaciones} como valor {@code double}
	 */
	public double indiceVic(){
		return (double) numMedallas / numParticipaciones;
	}
}
