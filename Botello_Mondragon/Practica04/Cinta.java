/**
 * Representa una cinta de nivel en un sistema de clasificación de participantes.
 * Cada cinta tiene un color que determina su nivel jerárquico dentro del sistema.
 * Los niveles disponibles son: Kaimua (0), Lua (1), Moana (2) y Ulakui (3).
 */
public class Cinta{

	/** El color o nombre de la cinta, que determina su nivel. */
	public String colorCinta;

	/**
	 * Construye una nueva instancia de {@code Cinta} con el color especificado.
	 *
	 * @param colorCinta el nombre del color o nivel de la cinta
	 *                   (por ejemplo, "Kaimua", "Lua", "Moana", "Ulakui")
	 */
	public Cinta(String colorCinta){
		this.colorCinta = colorCinta;
	}

	/**
	 * Devuelve el valor numérico que corresponde al nivel de esta cinta
	 * dentro de la jerarquía establecida.
	 * <p>
	 * La correspondencia es la siguiente:
	 * <ul>
	 *   <li>Kaimua → 0</li>
	 *   <li>Lua    → 1</li>
	 *   <li>Moana  → 2</li>
	 *   <li>Ulakui → 3</li>
	 * </ul>
	 *
	 * @return el índice numérico del nivel de la cinta, o {@code -1} si el color
	 *         no corresponde a ningún nivel conocido
	 */
    public int nivelCinta(){   //Devuelve el valor que corresponde a cada cinta (1 para Kaimua, 2 para Lua, etc )
		String[] cintas = {"Kaimua", "Lua",  "Moana", "Ulakui"};
		for (int i = 0; i < cintas.length; i++){
			if(cintas[i].equals(colorCinta)){
				return i;
			}
		}
		return -1;
	}

}
