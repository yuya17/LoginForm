package com.example.loginform;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final String[] fragments = {
            "com.example.loginform.MySizeFragment",
            "com.example.loginform.PropertyFragment",
            "com.example.loginform.MemorialFragment"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //DrawerLayoutのオブジェクトを呼び出す
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        /*Drawerが開く設定を記載していく。
         この引数の詳細⇆this:Drawerを持っているActivity,drawer:DrawerLayout Classのオブジェクト,toolbar:サポートv7ライブラリのツールバー,
         R.string.navigation_drawer_open:開くActionの説明文字列のResourceID,R.string.navigation_drawer_close:閉じるActionの説明文字列のResourceID
        */
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //DrawerLayoutのsetDrawerListener()に引数を指定
        drawer.setDrawerListener(toggle);
        //ActionBarDrawerToggleのsyncState()でDrawerLayoutとシンクロ
        toggle.syncState();

        /*
        xmlでapp:headerLayout="@layout/nav_header_main"を指定すると、ヘッダーに指定されるレイアウトを指定。
        app:menu属性はドロワー要素内のメニュー要素となるメニューファイルを指定。
        NavigationViewのオブジェクトを呼び出し、setNavigationItemSelectedListener()がcurrent Activityがイベントを受け取れるようにしている。
        * */
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    //このメソッドは戻るボタンが押された時に呼び出されるメソッド
    //Drawerが開かれている時は閉じるように設定されている。その設定はGravityCompatクラスが必要。
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //NavigationViewのメニュー項目が選択された時に呼び出されるメソッド
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch(id){
            case R.id.nav_mysize:
                fragmentManager.beginTransaction().replace(R.id.action_container,
                        Fragment.instantiate(MainActivity.this,fragments[0])).commit();
                break;
            case R.id.nav_property:
                fragmentManager.beginTransaction().replace(R.id.action_container,
                        Fragment.instantiate(MainActivity.this,fragments[1])).commit();
                break;
            case  R.id.nav_memorial:
                fragmentManager.beginTransaction().replace(R.id.action_container,
                        Fragment.instantiate(MainActivity.this,fragments[2])).commit();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}

