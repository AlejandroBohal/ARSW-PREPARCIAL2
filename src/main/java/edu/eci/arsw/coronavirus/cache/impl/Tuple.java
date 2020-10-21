package edu.eci.arsw.coronavirus.cache.impl;

import java.util.Objects;

/**
 *
 * @param <T1> Stats
 * @param <T2> Date
 */
public class Tuple<T1, T2> {

    T1 o1;
    T2 o2;

    public Tuple(T1 o1, T2 o2) {
        super();
        this.o1 = o1;
        this.o2 = o2;
    }

    /**
     *
     * @return retorna los stats
     */
    public T1 getStats() {
        return o1;
    }

    /**
     *
     * @return retorna la fecha
     */
    public T2 getDate() {
        return o2;
    }





}
