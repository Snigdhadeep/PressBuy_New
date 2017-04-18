package com.techpenta.pressbuy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

public class LowerTab extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    ProgressBar progressBar;
    LinearLayout lower_contact_tab;
    WebView mywebview;
    int clickcount=0;

    LinearLayout btnAboutus;
    LinearLayout btnTermsOfUse;
    LinearLayout btnPrivacy;
    LinearLayout btnContactus;
    LinearLayout btnWorkwithUs;
    ImageView navheader1;
    ImageView navheader2;
    WebView  tvCounter_value;
    ImageView menuSearchToolbar;
    FrameLayout menuBagToolbar;
    ImageView menuNavToolbar;
    LinearLayout slidetab2;
    Boolean slidecount=true;
    WebView wvslidecart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lower_tab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        lower_contact_tab=(LinearLayout)findViewById(R.id.lower_contact_tab);

        btnAboutus=(LinearLayout)findViewById(R.id.btnAboutus);
        btnTermsOfUse=(LinearLayout)findViewById(R.id.btnTermsOfUse);
        btnPrivacy=(LinearLayout)findViewById(R.id.btnPrivacy);
        btnContactus=(LinearLayout)findViewById(R.id.btnContactus);
        btnWorkwithUs=(LinearLayout)findViewById(R.id.btnWorkwithUs);

        tvCounter_value=(WebView) findViewById(R.id.tvCounter_value);

        wvslidecart=(WebView) findViewById(R.id.wvslidecart) ;
        /* toolWeb.loadUrl("http://www.pressbuy.com/rest-api/");*/


        slidetab2=(LinearLayout) findViewById(R.id.slidetab2);
        menuSearchToolbar=(ImageView)findViewById(R.id.menuSearchToolbar);
        menuBagToolbar=(FrameLayout) findViewById(R.id.menuBagToolbar);
        menuNavToolbar=(ImageView)findViewById(R.id.menuNavToolbar);




        menuBagToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(slidecount==true) {

                    wvslidecart.loadUrl("http://www.pressbuy.com/rest-api/");
                    slidetab2.setVisibility(View.VISIBLE);
                    Animation animation1 =
                            AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_to_left);
                    slidetab2.setVisibility(View.VISIBLE);
                    slidetab2.startAnimation(animation1);
                    slidecount = false;
                }
                else {
                    slidetab2.setVisibility(View.GONE);
                    slidecount=true;
                }
            }
        });


        final FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickcount=clickcount+1;


                if((clickcount%2)==0) {
                    lower_contact_tab.setVisibility(View.GONE);
                    fab2.setImageResource(R.drawable.uparrow);

                 /*   Animation animation1 =
                            AnimationUtils.loadAnimation(getApplicationContext(), R.anim.up_bottom);


                    lower_contact_tab.startAnimation(animation1);
                    fab2.setImageResource(R.drawable.uparrow);

                    lower_contact_tab.setVisibility(View.GONE);*/


                }
                else {
                    lower_contact_tab.setVisibility(View.VISIBLE);

                  /* Animation animation1 =
                            AnimationUtils.loadAnimation(getApplicationContext(), R.anim.up_bottom);
                    lower_contact_tab.startAnimation(animation1);*/
                    fab2.setImageResource(R.drawable.downarrow);
                    fab2.setClickable(true);


                }
            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = ((NavigationView)findViewById(R.id.nav_view)).getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);
        /*LinearLayout navHeader=(LinearLayout) LayoutInflater.from(this).inflate(R.layout.nav_header_home, null);
        navigationView.addHeaderView(navHeader);*/
        navheader1=(ImageView)header.findViewById(R.id.navicon1);
        navheader2=(ImageView)header.findViewById(R.id.navicon2);


//getting urls of  lower tab elements
        Intent iin= getIntent();
        String url = iin.getExtras().getString("id");


        lower_contact_tab.setVisibility(View.INVISIBLE);


        if(CheckNetwork.isInternetAvailable(getApplicationContext())) {
            mywebview = (WebView) findViewById(R.id.webView2);
            WebSettings webSettings = mywebview.getSettings();
            webSettings.setJavaScriptEnabled(true);
            mywebview.loadUrl(url);
            mywebview.setWebViewClient(new myWebClient());

        }else {

            Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_LONG).show();
        }





        //lowertab button click

        btnAboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), LowerTab.class);
                i.putExtra("id", "http://www.pressbuy.com/about-us/");

                if((clickcount%2)!=0) {
                    startActivity(i);
                }

            }
        });
        btnTermsOfUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), LowerTab.class);
                i.putExtra("id", "http://www.pressbuy.com/terms-of-use/");
                if((clickcount%2)!=0) {
                    startActivity(i);
                }

            }
        });
        btnPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), LowerTab.class);
                i.putExtra("id", "http://www.pressbuy.com/privacy/");
                if((clickcount%2)!=0) {
                    startActivity(i);
                }
            }
        });
        btnContactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), LowerTab.class);
                i.putExtra("id", "http://www.pressbuy.com/contact/");
                if((clickcount%2)!=0) {
                    startActivity(i);
                }
            }
        });
        btnWorkwithUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(getApplicationContext(), LowerTab.class);
                i.putExtra("id", "http://www.pressbuy.com/work-with-us/");
                if((clickcount%2)!=0) {
                    startActivity(i);
                }

            }
        });


        //navigation icons on click

        navheader1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), DrawerActivity.class);
                i.putExtra("id", "http://www.pressbuy.com/mobile-home/");
                startActivity(i);
            }
        });


        navheader2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), DrawerActivity.class);
                i.putExtra("id", "http://www.pressbuy.com/contact/");
                startActivity(i);
            }
        });




    }

    public class myWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            progressBar.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);

            progressBar.setVisibility(View.GONE);
            tvCounter_value.loadUrl("http://www.pressbuy.com/rest-api/");



        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lower_tab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.jhbjhbh) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_allitems) {
            Intent i = new Intent(getApplicationContext(), DrawerActivity.class);
            i.putExtra("id", "http://www.pressbuy.com/all-items/");
            startActivity(i);
        } else if (id == R.id.nav_forBuyers) {

            Intent i = new Intent(getApplicationContext(), DrawerActivity.class);
            i.putExtra("id", "http://www.pressbuy.com/for-buyers/");
            startActivity(i);

        } else if (id == R.id.nav_forSellers) {

            Intent i = new Intent(getApplicationContext(), DrawerActivity.class);
            i.putExtra("id", "http://www.pressbuy.com/for-sellers/");
            startActivity(i);

        } else if (id == R.id.nav_upload) {

            Intent i = new Intent(getApplicationContext(), DrawerActivity.class);
            i.putExtra("id", "http://www.pressbuy.com/upload/");
            startActivity(i);

        } else if (id == R.id.nav_faq) {

            Intent i = new Intent(getApplicationContext(), DrawerActivity.class);
            i.putExtra("id", "http://www.pressbuy.com/faq/");
            startActivity(i);

        } else if (id == R.id.nav_contact) {

            Intent i = new Intent(getApplicationContext(), DrawerActivity.class);
            i.putExtra("id", "http://www.pressbuy.com/contact/");
            startActivity(i);


        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
