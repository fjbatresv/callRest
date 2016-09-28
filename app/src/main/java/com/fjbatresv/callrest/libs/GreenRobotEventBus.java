package com.fjbatresv.callrest.libs;

import com.fjbatresv.callrest.libs.base.EventBus;

/**
 * Created by javie on 27/09/2016.
 */
public class GreenRobotEventBus implements EventBus{
    private org.greenrobot.eventbus.EventBus bus;

    public GreenRobotEventBus(org.greenrobot.eventbus.EventBus bus) {
        this.bus = bus;
    }

    @Override
    public void register(Object obj) {
        bus.register(obj);
    }

    @Override
    public void unRegister(Object obj) {
        bus.unregister(obj);
    }

    @Override
    public void post(Object obj) {
        bus.post(obj);
    }
}
