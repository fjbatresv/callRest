package com.fjbatresv.callrest.settings.ui;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.fjbatresv.callrest.App;
import com.fjbatresv.callrest.R;
import com.fjbatresv.callrest.about.AboutActivity;
import com.fjbatresv.callrest.entities.Lista;
import com.fjbatresv.callrest.entities.Settings;
import com.fjbatresv.callrest.listas.list.ui.ListasActivity;
import com.fjbatresv.callrest.main.MainActivity;
import com.fjbatresv.callrest.settings.SettingsPresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SettingsView {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.barName)
    TextView barName;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;

    @Bind(R.id.txtExtension)
    EditText txtExtension;
    @Bind(R.id.sms)
    Switch sms;
    @Bind(R.id.txtSms)
    LinearLayout txtSms;
    @Bind(R.id.txtSmsNoWeekend)
    EditText txtSmsNoWeekend;
    @Bind(R.id.txtSmsNoWork)
    EditText txtSmsNoWork;
    @Bind(R.id.txtSmsJustWork)
    EditText txtSmsJustWork;

    @Inject
    SettingsPresenter presenter;

    private App app;
    private int visible;
    private int gone;
    private Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        app = (App) getApplication();
        inject();
        presenter.onCreate();
        load();
        presenter.load();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void inject() {
        app.settings(this).inject(this);
    }

    private void load() {
        visible = View.VISIBLE;
        gone = View.GONE;
        barName.setText(getString(R.string.settings_title));
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @OnClick(R.id.sms)
    public void sms(){
        if (sms.isChecked()){
            txtSms.setVisibility(View.VISIBLE);
        }else{
            txtSms.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here
        switch (item.getItemId()) {
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
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void loading(boolean load) {

    }

    @Override
    public void loadActual(Settings settings) {
        this.settings = settings;
        txtExtension.setText(settings.getExtension());
        sms.setChecked(settings.getSms());
        if (sms.isChecked()) {
            txtSms.setVisibility(visible);
        } else {
            txtSms.setVisibility(gone);
        }
        txtSmsJustWork.setText(settings.getSmsJustWork());
        txtSmsNoWeekend.setText(settings.getSmsNoWeekend());
        txtSmsNoWork.setText(settings.getSmsNoWork());
    }



    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
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
                save();
                break;
        }
        return true;
    }

    public void save() {
        Settings settings = new Settings(
                1,
                txtExtension.getText().toString(),
                sms.isChecked(),
                txtSmsNoWeekend.getText().toString(),
                txtSmsNoWork.getText().toString(),
                txtSmsJustWork.getText().toString()
        );
        presenter.save(settings);
    }

    @Override
    public void saved() {
        Toast.makeText(this, getString(R.string.settings_save_success), Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, MainActivity.class));
    }
}
