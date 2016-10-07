package com.fjbatresv.callrest.listas.view.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fjbatresv.callrest.App;
import com.fjbatresv.callrest.R;
import com.fjbatresv.callrest.about.AboutActivity;
import com.fjbatresv.callrest.contactList.ui.ContacListActivity;
import com.fjbatresv.callrest.entities.Contacto;
import com.fjbatresv.callrest.entities.Lista;
import com.fjbatresv.callrest.listas.add.ui.ListaAddActivity;
import com.fjbatresv.callrest.listas.list.ui.ListasActivity;
import com.fjbatresv.callrest.listas.view.ListaViewPresenter;
import com.fjbatresv.callrest.listas.view.ui.adapters.ListaViewAdapter;
import com.fjbatresv.callrest.listas.view.ui.adapters.ListaViewOnItemClickListener;
import com.fjbatresv.callrest.main.MainActivity;
import com.fjbatresv.callrest.settings.ui.SettingsActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;

public class ListaViewActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, ListaViewView, ListaViewOnItemClickListener {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.barName)
    TextView barName;

    @Bind(R.id.addContact)
    FabSpeedDial addContact;

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.nav_view)
    NavigationView navigationView;

    @Bind(R.id.nombre)
    TextView nombre;
    @Bind(R.id.desc)
    TextView desc;
    @Bind(R.id.tipo)
    TextView tipo;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.progressBar)
    ProgressBar progressbar;

    @Inject
    ListaViewPresenter presenter;
    @Inject
    ListaViewAdapter adapter;

    private App app;
    private int visible;
    private int gone;
    private Context context;

    private Lista lista;

    public static final String LISTA = "LISTA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_view);
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

    private void load() {
        visible = View.VISIBLE;
        gone = View.GONE;
        barName.setText(getString(R.string.listas_list_title));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(adapter);
        context = getApplicationContext();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(adapter);
        presenter.loadList(getIntent().getStringExtra(LISTA));
        addContact.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_edit_list:
                        startActivity(new Intent(context, ListaAddActivity.class).putExtra(ListaAddActivity.LISTA, lista.getNombre()));
                        break;
                    case R.id.menu_add_contact:
                        addContact();
                        break;
                    case R.id.menu_add_contact_phone:
                        progressbar.setVisibility(visible);
                        startActivity(new Intent(context, ContacListActivity.class)
                                .putExtra(ContacListActivity.LISTA, lista.getNombre()));
                        break;
                    case R.id.menu_delete:
                        delete();
                        break;
                }
                return false;
            }
        });
    }

    private void delete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Esta seguro de eliminar la lista?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.delete(lista.getId());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void addContact() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nuevo contacto");
        final View view = LayoutInflater.from(this).inflate(R.layout.new_contact, null);
        builder.setView(view);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText nombre = (EditText) view.findViewById(R.id.txtNombre);
                EditText numero = (EditText) view.findViewById(R.id.txtNumero);
                presenter.addContact(lista, new Contacto(null, nombre.getText().toString(),
                        numero.getText().toString(), lista.getNombre()));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void inject() {
        app.listaView(this, this).inject(this);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            startActivity(new Intent(this, ListasActivity.class)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP
            |Intent.FLAG_ACTIVITY_NEW_TASK));
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

    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void contacAdded(Lista lista) {
        adapter.addContacts(lista.getContactos());
    }

    @Override
    public void foundedList(Lista lista) {
        this.lista = lista;
        nombre.setText(lista.getNombre());
        desc.setText(lista.getDescripcion());
        tipo.setText(lista.getTipo());
        adapter.setList(lista.getContactos());
    }

    @Override
    public void deleted() {
        startActivity(new Intent(this, ListasActivity.class));
    }

    @Override
    public void onDelete(Contacto contacto) {
        presenter.removeContact(lista, contacto);
    }
}
