package no.uib.ii.inf102.f18.mandatory1;


public interface ISymTable<Key extends Comparable<Key>, Value> {

    /**
     * Put the key into the symbol table and associate it to the value
     *
     * @param key the key
     * @param value value to associate with key
     */
    void put(Key key, Value value);

    /**
     * Get the value associated to the given key, or null if it does not exist
     *
     * @param key the key
     * @return the value associated with the key, or null if key not present
     */
    Value get(Key key);

    /**
     * Check whether the key exists in the symbol table
     *
     * @param key the key
     * @return true if key exists in symbol table, false otherwise
     */
    boolean containsKey(Key key);

    /**
     * Delete the key and associated value from the symbol table.
     *
     * @param key the key to delete
     */
    void delete(Key key);

    /**
     * The number of key/value pairs in the symbol table
     * @return the size
     */
    int size();

    /**
     * Return an iterator over all the keys in the symbol table
     *
     * @return iterator over all the keys in the symbol table
     */
    Iterable<Key> keys();

}
