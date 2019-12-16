package com.sw2.app.movieapp.Interface;

import java.util.List;

/**
 * Created by ahmed on 11/23/2016.
 */

public interface Operations {
    public void delete(String Id);
    public List<Object> readAll();
    public Object read( String Id);
    public void update(Object o);
    public void insert(Object o);

}
