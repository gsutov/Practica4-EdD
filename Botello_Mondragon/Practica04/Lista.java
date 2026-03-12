/**
 * Interfaz que define las operaciones de una lista genérica.
 * Extiende la interfaz {@link Coleccion} agregando operaciones
 * de acceso, eliminación y búsqueda por índice.
 *
 * @param <T> El tipo de elementos almacenados en la lista.
 */
public interface Lista<T> extends Coleccion<T>{

    /**
     * Elimina el elemento en la posición indicada de la lista.
     *
     * @param indice La posición del elemento a eliminar.
     * @throws IllegalArgumentException si el índice no es válido.
     */
    public void eliminar(int indice);

    /**
     * Devuelve el elemento almacenado en la posición indicada de la lista.
     *
     * @param indice La posición del elemento a obtener.
     * @return El elemento en la posición indicada.
     * @throws IllegalArgumentException si el índice no es válido.
     */
    public T acceder(int indice);

    /**
     * Devuelve el índice de la primera aparición del elemento dado en la lista.
     *
     * @param elemento El elemento cuyo índice se desea obtener.
     * @return El índice de la primera aparición del elemento.
     * @throws IllegalArgumentException si el elemento no se encuentra en la lista.
     */
    public int devolverIndiceElemento(T elemento);

    /**
     * Devuelve la cantidad de elementos almacenados en la lista.
     *
     * @return La longitud actual de la lista.
     */
    public int devolverLongitud();

}
