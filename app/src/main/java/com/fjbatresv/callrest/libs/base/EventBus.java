package com.fjbatresv.callrest.libs.base;

/**
 * Created by javie on 27/09/2016.
 */
public interface EventBus {
    void register(Object obj);
    void unRegister(Object obj);
    void post(Object obj);
}
