package com.fjbatresv.callrest.contactList.ui;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fjbatresv.callrest.App;
import com.fjbatresv.callrest.R;
import com.fjbatresv.callrest.about.AboutActivity;
import com.fjbatresv.callrest.contactList.ContactListPresenter;
import com.fjbatresv.callrest.contactList.ui.adapters.ContactListAdapter;
import com.fjbatresv.callrest.contactList.ui.adapters.ContactListOnItemClickListener;
import com.fjbatresv.callrest.entities.Contacto;
import com.fjbatresv.callrest.entities.Lista;
import com.fjbatresv.callrest.listas.list.ui.ListasActivity;
import com.fjbatresv.callrest.listas.view.ui.ListaViewActivity;
import com.fjbatresv.callrest.main.MainActivity;
import com.fjbatresv.callrest.settings.ui.SettingsActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContacListActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, ContactListView,
        ContactListOnItemClickListener{
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.barName)
    TextView barName;

    @Bind(R.id.txtSearch)
    EditText txtSearch;

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.nav_view)
    NavigationView navigationView;

    @Bind(R.id.nombre)
    TextView nombre;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.addContact)
    Button addContact;

    @Inject
    ContactListPresenter presenter;
    @Inject
    ContactListAdapter adapter;

    private App app;
    private int visible;
    private int gone;
    private Lista lista;
    private List<Contacto> contactos;
    private List<Contacto> selected;

    public static final String LISTA = "lista";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contac_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        app = (App) getApplication();
        injection();
        presenter.onCreate();
        load();
    }

    private void injection() {
        app.contactList(this, this).inject(this);
    }

    private void load() {
        visible = View.VISIBLE;
        gone = View.GONE;
        barName.setText(getString(R.string.contact_list_title));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(adapter);
        nombre.setText(getIntent().getStringExtra(LISTA));
        presenter.loadContacts(getIntent().getStringExtra(LISTA));
        addContact.setText(String.format(getString(R.string.contact_list_button_add), 0));
        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!txtSearch.getText().toString().equals("")){
                    adapter.search(txtSearch.getText().toString());
                }else{
                    adapter.restart();
                }
            }
        });
    }

    @OnClick(R.id.addContact)
    public void add(){
        presenter.saveContacts(selected, getIntent().getStringExtra(LISTA));
    }

    @Override
    protected void onDestroy() {
        presenter.onODestroy();
        super.onDestroy();
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

        }else {

        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void contactsAdded() {
        startActivity(new Intent(this, ListaViewActivity.class)
                .putExtra(ListaViewActivity.LISTA, getIntent().getStringExtra(LISTA)));
    }

    @Override
    public void loadContacts(List<Contacto> contactos) {
        this.contactos = contactos;
        adapter.loadList(contactos);
    }

    @Override
    public void addSelected(Contacto contacto) {
        selected.add(contacto);
        int selecteds = selected.size();
        addContact.setEnabled(selecteds > 0);
        addContact.setText(String.format(getString(R.string.contact_list_button_add), String.valueOf(selecteds)));
    }

    @Override
    public void removeSelected(Contacto contacto) {
        selected.remove(contacto);
        int selecteds = selected.size();
        addContact.setEnabled(selecteds > 0);
        addContact.setText(String.format(getString(R.string.contact_list_button_add), String.valueOf(selecteds)));
    }
}
