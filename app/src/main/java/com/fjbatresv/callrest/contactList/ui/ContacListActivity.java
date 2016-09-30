package com.fjbatresv.callrest.contactList.ui;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fjbatresv.callrest.App;
import com.fjbatresv.callrest.R;
import com.fjbatresv.callrest.contactList.ContactListPresenter;
import com.fjbatresv.callrest.contactList.ui.adapters.ContactListAdapter;
import com.fjbatresv.callrest.contactList.ui.adapters.ContactListOnItemClickListener;
import com.fjbatresv.callrest.entities.Contacto;
import com.fjbatresv.callrest.entities.Lista;
import com.fjbatresv.callrest.listas.list.ui.ListasActivity;
import com.fjbatresv.callrest.listas.view.ui.ListaViewActivity;

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

    public static final String LISTA = "lista";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contac_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
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
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(adapter);
        nombre.setText(getIntent().getStringExtra(LISTA));
        presenter.loadContacts(getIntent().getStringExtra(LISTA));
        addContact.setText(String.format(getString(R.string.contact_list_button_add), 0));
    }

    @OnClick(R.id.addContact)
    public void add(){
        presenter.saveContacts(adapter.getSelected(), getIntent().getStringExtra(LISTA));
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
        adapter.loadList(contactos);
    }

    @Override
    public void addSelected(Contacto contacto) {
        adapter.addSelected(contacto);
        int selecteds = adapter.getSelected().size();
        addContact.setEnabled(selecteds > 0);
        addContact.setText(String.format(getString(R.string.contact_list_button_add), String.valueOf(selecteds)));
    }

    @Override
    public void removeSelected(Contacto contacto) {
        adapter.removeSelected(contacto);
        int selecteds = adapter.getSelected().size();
        addContact.setEnabled(selecteds > 0);
        addContact.setText(String.format(getString(R.string.contact_list_button_add), String.valueOf(selecteds)));
    }
}
