package com.example.renato.myapplication;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Renato on 23/05/2018.
 */

public class ChamaFragment extends AppCompatActivity {


    public void Chama() {

        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace( R.id.conteudo_fragment, homeFragment).commit();

    }
}
