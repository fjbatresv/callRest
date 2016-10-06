package com.fjbatresv.callrest.listas.add.ui;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fjbatresv.callrest.App;
import com.fjbatresv.callrest.R;
import com.fjbatresv.callrest.about.AboutActivity;
import com.fjbatresv.callrest.entities.Lista;
import com.fjbatresv.callrest.listas.add.ListaAddPresenter;
import com.fjbatresv.callrest.listas.list.ui.ListasActivity;
import com.fjbatresv.callrest.listas.view.ui.ListaViewActivity;
import com.fjbatresv.callrest.main.MainActivity;
import com.fjbatresv.callrest.settings.ui.SettingsActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListaAddActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, ListaAddView {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.barName)
    TextView barName;
    @Bind(R.id.txtNombre)
    EditText nombre;
    @Bind(R.id.txtDesc)
    EditText desc;
    @Bind(R.id.tipo)
    Spinner tipo;
    @Bind(R.id.listaAddTitle)
    TextView listaAddTitle;
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
    private Lista lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_add);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
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
                                lista == null ? null : lista.getId(),
                        nombre.getText().toString(),
                        desc.getText().toString(),
                        tipo.getSelectedItem().toString()),
                        getIntent().hasExtra(LISTA) ? false : true);
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
        if (getIntent().hasExtra(LISTA)){
            listaAddTitle.setText(getString(R.string.listas_edit_title));
            presenter.loadLista(getIntent().getStringExtra(LISTA));
        }
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
            case R.id.sidebar_home:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.sidebar_listas:
                startActivity(new Intent(this, ListasActivity.class));
                break;
            case R.id.sidebar_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.sidebar_about:
                startActivity(new Intent(this, AboutActivity.class));
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
        startActivity(new Intent(this, ListaViewActivity.class)
        .putExtra(ListaViewActivity.LISTA, lista.getNombre()));
    }

    @Override
    public void loadList(Lista lista) {
        this.lista = lista;
        nombre.setText(lista.getNombre());
        desc.setText(lista.getDescripcion());
        int contador = 0;
        for (String tipoStr : getResources().getStringArray(R.array.listas_add_tipo)){
            if (tipoStr.equalsIgnoreCase(lista.getTipo())){
                tipo.setSelection(contador);
                break;
            }
        }

    }
}
