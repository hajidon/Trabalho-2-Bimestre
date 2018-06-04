package com.example.renato.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;


import com.example.renato.myapplication.banco.Historico;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    Historico historicoEditado = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar( toolbar );
         //Create the AccountHeader
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace( R.id.conteudo_fragment, homeFragment).commit();
        criaDrawer();


    }

    private void criaDrawer() {

        AccountHeader headerResult = new AccountHeaderBuilder()

                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName("Renato Benatti").withEmail("renato.benatti@edu.unipar.br").withIcon(getResources().getDrawable(R.drawable.profile))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

//Now create your drawer and pass the AccountHeader.Result
        new DrawerBuilder().withActivity(this).build();
        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Home").withIcon( R.drawable.ic_baseline_home_24px );
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Historico").withIcon( R.drawable.ic_baseline_history_24px );
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName( "Sobre" ).withIcon( R.drawable.ic_info_outline_black_24dp );
//create the drawer and remember the `Drawer` result object
        DrawerBuilder drawerBuilder = new DrawerBuilder();
        drawerBuilder.withActivity( this );
        drawerBuilder.withAccountHeader(headerResult);
        drawerBuilder.withToolbar(toolbar);
        drawerBuilder.addDrawerItems(
                item1,
                item2,
                new DividerDrawerItem(),
                item3
        );
        //todo incluir um fragment
        drawerBuilder.withOnDrawerItemClickListener( new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                if (drawerItem.getIdentifier() == 1) {

                    HomeFragment homeFragment = new HomeFragment();
                    getSupportFragmentManager().beginTransaction().replace( R.id.conteudo_fragment, homeFragment).commit();

                } else if (drawerItem.getIdentifier() == 2){

                    HistoricoFragment config = new HistoricoFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.conteudo_fragment,config ).commit();

                } else if (drawerItem.getIdentifier() == 3) {
                    AboutFragment aboutFragment = new AboutFragment();
                    getSupportFragmentManager().beginTransaction().replace( R.id.conteudo_fragment,aboutFragment ).commit();
                }
                return false;
            }
        } );
        Drawer result = drawerBuilder.build();
    }

}
