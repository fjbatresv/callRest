package com.fjbatresv.callrest.listas.add.ui;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.fjbatresv.callrest.App;
import com.fjbatresv.callrest.R;
import com.fjbatresv.callrest.entities.Lista;
import com.fjbatresv.callrest.listas.add.ListaAddPresenter;
import com.fjbatresv.callrest.listas.list.ui.ListasActivity;
import com.fjbatresv.callrest.listas.view.ui.ListaViewActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListaAddActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, ListaAddView {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.txtNombre)
    EditText nombre;
    @Bind(R.id.txtDesc)
    EditText desc;
    @Bind(R.id.tipo)
    Spinner tipo;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.nav_view)
    NavigationView navigationView;

    @Inject
    ListaAddPresenter presenter;

    public static final String LISTA = "LISTA";

    private App app;
    private int visible;
    private int gone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_add);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);
        app = (App) getApplication();
        inject();
        presenter.onCreate();
        load();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.listas_add_appbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_save:
                presenter.saveLista(new Lista(
                        nombre.getText().toString(),
                        desc.getText().toString(),
                        tipo.getSelectedItem().toString()));
                break;
        }
        return true;
    }

    private void load() {
        ArrayAdapter<String> tiposAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.listas_add_tipo));
        tiposAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipo.setAdapter(tiposAdapter);
        visible = View.VISIBLE;
        gone = View.GONE;
    }

    private void inject() {
        app.listaAdd(this).inject(this);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sidebar_listas:
                startActivity(new Intent(this, ListasActivity.class));
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void loading(boolean load) {
        if (load){
            nombre.setEnabled(false);
            desc.setEnabled(false);
            tipo.setEnabled(false);
        }else{
            nombre.setEnabled(true);
            desc.setEnabled(true);
            tipo.setEnabled(true);
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void listaDone(Lista lista) {
        Snackbar.make(drawerLayout,
                String.format(getString(R.string.listas_add_message_add_success), lista.getNombre()),
                Snackbar.LENGTH_LONG).show();
        startActivity(new Intent(this, ListaViewActivity.class)
        .putExtra(ListaViewActivity.LISTA, lista.getNombre()));
    }
}
