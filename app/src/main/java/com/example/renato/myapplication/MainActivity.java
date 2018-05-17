package com.example.renato.myapplication;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;;
import android.support.v7.app.AppCompatActivity;



import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar;
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar( toolbar );
         //Create the AccountHeader
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
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Home");
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName("config");

//create the drawer and remember the `Drawer` result object
        DrawerBuilder drawerBuilder = new DrawerBuilder();
        drawerBuilder.withActivity( this );
        drawerBuilder.withAccountHeader(headerResult);
        drawerBuilder.withToolbar(toolbar);
        drawerBuilder.addDrawerItems(
                item1,
                new DividerDrawerItem(),
                item2,
                new SecondaryDrawerItem().withName( "Nova Linha" )
        );
        //todo incluir um fragment
        drawerBuilder.withOnDrawerItemClickListener( new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

           if (drawerItem.getIdentifier() == 1) {

               HomeFragment homeFragment = new HomeFragment();
               getSupportFragmentManager().beginTransaction().replace( R.id.conteudo_fragment, homeFragment).commit();

           } else if (drawerItem.getIdentifier() == 2){
               ConfigFragment config = new ConfigFragment();
               getSupportFragmentManager().beginTransaction().replace(R.id.conteudo_fragment,config ).commit();
           }
                return false;
            }
        } );
        Drawer result = drawerBuilder.build();

    }

}
