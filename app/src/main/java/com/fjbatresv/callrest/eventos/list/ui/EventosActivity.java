package com.fjbatresv.callrest.eventos.list.ui;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.fjbatresv.callrest.App;
import com.fjbatresv.callrest.R;
import com.fjbatresv.callrest.entities.Evento;
import com.fjbatresv.callrest.eventos.list.EventosPresenter;
import com.fjbatresv.callrest.main.MainActivity;
import com.fjbatresv.callrest.settings.ui.SettingsActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EventosActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, EventosView{
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;

    @Inject
    EventosPresenter presenter;

    private App app;
    private int visible;
    private int gone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);
        ButterKnife.bind(this);
        app = (App) getApplication();
        inject();
        presenter.onCreate();
        load();
        presenter.loadList();
    }

    private void load() {
        visible = View.VISIBLE;
        gone = View.GONE;
    }

    private void inject() {
        //app.eventos(this, this).inject(this):
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void loading(boolean load) {

    }

    @Override
    public void loadEventos(List<Evento> eventos) {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sidebar_home:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.sidebar_settings:
                startActivity(new Intent(this, SettingsActivity.class));
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
