package com.fjbatresv.callrest.listas.ui;

import com.fjbatresv.callrest.entities.Lista;

import java.util.List;

/**
 * Created by javie on 27/09/2016.
 */
public interface ListasView {
    void loading(boolean load);
    void showError(String error);
    void setListas(List<Lista> listas);
}
