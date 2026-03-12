import java.util.Iterator;

/**
 * Implementación de una lista enlazada simple genérica.
 *
 * @param <T> El tipo de elementos almacenados en la lista.
 */
public class ListaLigadaSimple<T> implements Lista<T> {

    private class Nodo{
        /**
         * Elemento almacenado en el nodo.
         */
        public T elemento;

        /**
         * Apuntador al siguiente nodo en la lista.
         */
        public Nodo siguiente;

        /**
         * Crea un nuevo nodo con el elemento proporcionado.
         *
         * @param elemento El elemento a almacenar en el nodo.
         */
        public Nodo(T elemento) {
            this.elemento = elemento;
        }
    }

    /**
     * Un iterador para recorrer la lista enlazada simple.
     */
    private class IteradorListaSimple implements Iterator<T> {

        /**
         * El nodo siguiente al que se moverá el iterador.
         */
        private Nodo iteradorLista;

        /**
         * Crea un nuevo iterador y lo inicializa en un nodo centinela
         * cuyo siguiente apunta a la cabeza de la lista.
         */
        public IteradorListaSimple() {
            iteradorLista = new Nodo(null);
            iteradorLista.siguiente = cabeza;
        }

        /**
         * Verifica si hay un siguiente elemento en la lista.
         *
         * @return true si hay un siguiente elemento, false de lo contrario.
         */
        public boolean hasNext() {
            return iteradorLista.siguiente != null;
        }

        /**
         * Obtiene el siguiente elemento en la lista y mueve el iterador al siguiente nodo.
         *
         * @return El siguiente elemento en la lista.
         */
        public T next() {
            iteradorLista = iteradorLista.siguiente;
            return iteradorLista.elemento;
        }
    }

    /** Primer nodo de la lista. */
    private Nodo cabeza;

    /** Número de elementos almacenados en la lista. */
    private int longitud;

    /**
     * Crea una lista ligada simple vacía.
     * La cabeza se inicializa en null y la longitud en 0.
     */
    public ListaLigadaSimple(){
      this.cabeza = null;
      this.longitud = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<T> iterator() {
        return new IteradorListaSimple();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void agregar(T elemento) throws IllegalArgumentException {
        if(elemento == null){
	    throw new IllegalArgumentException ("el elemento es nulo");
	}
	if(this.longitud == 0){
	    Nodo cabezaN = new Nodo(elemento);
	    this.cabeza = cabezaN;
	    this.longitud = this.longitud + 1;
	    return;
	}
	Nodo nuevoNodo = new Nodo(elemento);
	nuevoNodo.siguiente = this.cabeza;
	this.cabeza = nuevoNodo;
	this.longitud = this.longitud + 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean buscar(T elemento) {
        for(T e: this){
	    if(e.equals(elemento)){
		return true;
	    }
	}
	return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T acceder(int i) throws IllegalArgumentException {
        if(i < 0 || i >= this.longitud){
	    throw new IllegalArgumentException("el índice no es válido");
	}
	int contador = 0;
	for(T e: this){
	    if(contador == i){
		return e;
	    }
	    contador++;
	}
	return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eliminar(int i) {
        if(i < 0 || i >= this.longitud){
	    throw new IllegalArgumentException("el índice no es válido");
	}

	if(i == 0){
	    this.cabeza = this.cabeza.siguiente;
	    this.longitud = this.longitud -1;
	    return;
	}

	Nodo anterior = null;
	Nodo aEliminar = this.cabeza;
	int contador = 0;
	while(contador != i){
	    anterior = aEliminar;
	    aEliminar = aEliminar.siguiente;
	    contador++;
	}
	anterior.siguiente = aEliminar.siguiente;
	aEliminar.siguiente = null;
	this.longitud = this.longitud -1;
    }

    /**
     * Elimina la primera aparición del elemento dado en la lista.
     * Si la lista está vacía, no realiza ninguna operación.
     * Si el elemento coincide con la cabeza, se actualiza la cabeza al siguiente nodo.
     * En otro caso, recorre la lista hasta encontrar el elemento y elimina su nodo
     * actualizando las referencias de los nodos adyacentes.
     *
     * @param elemento El elemento a eliminar de la lista.
     */
    public void eliminar(T elemento){
        if(this.longitud == 0){
	    return;
	}

	if(elemento.equals(this.cabeza.elemento)){
	    this.cabeza = this.cabeza.siguiente;
	    this.longitud = this.longitud -1;
	    return;
	}
	Nodo anterior = null;
	Nodo aEliminar = this.cabeza;
	while(aEliminar != null){
	    if(aEliminar.elemento.equals(elemento)){
		anterior.siguiente = aEliminar.siguiente;
		aEliminar.siguiente = null;
		this.longitud = this.longitud -1;
		return;
	    }
	    anterior = aEliminar;
	    aEliminar = aEliminar.siguiente;
	}
    }

    /**
     * Devuelve el índice de la primera aparición del elemento dado en la lista.
     * Recorre la lista comparando cada elemento con el dado, contando los nodos
     * recorridos. Si encuentra una coincidencia, devuelve el conteo en ese momento.
     *
     * @param elemento El elemento cuyo índice se desea obtener.
     * @return El índice de la primera aparición del elemento en la lista.
     * @throws IllegalArgumentException si el elemento no se encuentra en la lista.
     */
    public int devolverIndiceElemento(T elemento) throws IllegalArgumentException{
        if(buscar(elemento) == false){
	    throw new IllegalArgumentException("no está el elemento");
	}
	int contador = 0;
	for(T e: this){
	    if(e.equals(elemento)){
		return contador;
	    }
	    contador++;
	}
	return -1;
    }

    /**
     * Obtiene la longitud actual de la lista.
     *
     * @return La longitud de la lista.
     */
    public int devolverLongitud() {
        int n = this.longitud;
	return n;
    }

    /**
     * Obtiene el primer nodo de la lista.
     *
     * @return El primer nodo de la lista.
     */
    public Nodo darCabeza() {
        return this.cabeza;
    }

    /**
     * Devuelve una representación en cadena de la lista ligada.
     * Los elementos se muestran en orden, separados por comas y encerrados entre corchetes.
     *
     * @return una cadena con los elementos de la lista.
     */
    @Override
    public String toString() {
        String resultado = "[";
        boolean primero = true;
        for (T elemento : this) {
            if (!primero) {
                resultado += ", ";
            } else {
                primero = false;
            }
            resultado += elemento.toString();
        }

        resultado += "]";
        return resultado;
    }

}
