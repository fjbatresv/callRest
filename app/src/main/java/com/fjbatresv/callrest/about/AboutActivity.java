package com.fjbatresv.callrest.about;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.fjbatresv.callrest.R;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Element version = new Element();
        version.setTitle("Versión 1.0");
        View about = new AboutPage(this)
                .isRTL(false)
                .addItem(version)
                .setDescription("En está aplicación podras gestionar listas de llamadas de " +
                        "distintos tipos, para permitirte responder las llamadas que deseas cuando lo deseas.")
                .addGroup("Contactanos")
                .addPlayStore("com.fjbatresv.callrest")
                .addEmail("fjbatresv@gmail.com")
                .create();
        setContentView(about);
    }
}
