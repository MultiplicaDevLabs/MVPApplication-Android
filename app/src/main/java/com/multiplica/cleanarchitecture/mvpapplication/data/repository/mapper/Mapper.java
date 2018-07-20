package com.multiplica.cleanarchitecture.mvpapplication.data.repository.mapper;

import java.util.AbstractList;
import java.util.ArrayList;

/**
 * Created by user on 18/07/18.
 */

public abstract class Mapper <T1, T2> {

    public abstract T2 map(T1 value);

    public abstract T1 reverseMap(T2 value);

    public ArrayList<T2> map(AbstractList<T1> values) {
        ArrayList<T2> returnValues = new ArrayList<>();
        if (values == null)
            return returnValues;

        for (T1 value : values)
            returnValues.add(map(value));

        return returnValues;
    }

    public AbstractList<T1> reverseMap(ArrayList<T2> values) {
        AbstractList<T1> returnValues = new ArrayList<T1>();
        if (values == null)
            return returnValues;

        for (T2 value : values)
            returnValues.add(reverseMap(value));

        return returnValues;
    }

}
