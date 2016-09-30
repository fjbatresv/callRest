package com.fjbatresv.callrest.listas.list.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.fjbatresv.callrest.App;
import com.fjbatresv.callrest.R;
import com.fjbatresv.callrest.entities.Lista;
import com.fjbatresv.callrest.listas.add.ui.ListaAddActivity;
import com.fjbatresv.callrest.listas.list.ListasPresenter;
import com.fjbatresv.callrest.listas.list.ui.adapters.ListasAdapter;
import com.fjbatresv.callrest.listas.list.ui.adapters.OnItemClickListener;
import com.fjbatresv.callrest.listas.view.ui.ListaViewActivity;
import com.fjbatresv.callrest.listas.view.ui.ListaViewView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListasActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, ListasView, OnItemClickListener{
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.container)
    RelativeLayout container;
    @Bind(R.id.addList)
    FloatingActionButton addList;

    @Inject
    ListasPresenter presenter;
    @Inject
    ListasAdapter adapter;

    private App app;
    private int visible;
    private int gone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("listas", "en activity listas");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listas);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);
        app = (App) getApplication();
        load();
        inject();
        recycler();
        presenter.onCreate();
        presenter.getLists();
    }

    private void load() {
        visible = View.VISIBLE;
        gone = View.GONE;
    }

    private void recycler() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void inject() {
        app.listas(this, this).inject(this);
    }

    @OnClick(R.id.addList)
    public void addList(){
        startActivity(new Intent(this, ListaAddActivity.class));
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
            case R.id.sidebar_listas:
                break;
            case R.id.sidebar_login:
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void loading(boolean load) {
        if (load){
            progressBar.setVisibility(visible);
        }else{
            progressBar.setVisibility(gone);
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setListas(List<Lista> listas) {
        adapter.setListas(listas);
    }

    @Override
    public void onListaCardClick(Lista lista) {
        startActivity(new Intent(this, ListaViewActivity.class)
        .putExtra(ListaViewActivity.LISTA, lista.getNombre()));
    }
}
