package edu.eci.arsw.coronavirus.cache.impl;

import edu.eci.arsw.coronavirus.cache.ICoronaVirusStatsCache;
import edu.eci.arsw.coronavirus.model.Case;
import edu.eci.arsw.coronavirus.services.impl.CoronavirusStatsServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CoronaVirusStatsCacheImpl implements ICoronaVirusStatsCache {

    private ConcurrentHashMap<String, Tuple<List<Case>,Date>> cacheStore;

    public CoronaVirusStatsCacheImpl(){
       this.cacheStore = new ConcurrentHashMap<>();
    }

    @Override
    public void putCache(String name, List<Case> stats) {
        cacheStore.put(name, new Tuple<>(stats, new Date()));
    }


    @Override
    public Tuple<List<Case>, Date> getCache(String name) {
        return cacheStore.get(name);
    }

    @Override
    public long getDate(String name) {
        if (this.cacheStore.get(name) != null){
            return cacheStore.get(name).getDate().getTime();
        }else{
            return new Date().getTime();
        }
    }
}
