package edu.eci.arsw.coronavirus.cache;

import edu.eci.arsw.coronavirus.cache.impl.Tuple;
import edu.eci.arsw.coronavirus.model.Case;

import java.util.Date;
import java.util.List;

/**
 * The interface Corona virus stats cache.
 */
public interface ICoronaVirusStatsCache {

    /**
     * Put cache.
     *
     * @param name  the name
     * @param stats the stats
     */
    void putCache(String name, List<Case> stats);

    /**
     * Gets cache.
     *
     * @param name the name
     * @return the cache
     */
    Tuple<List<Case>, Date> getCache(String name);

    /**
     * Gets date.
     *
     * @param name the name
     * @return the date
     */
    long getDate(String name);
}
