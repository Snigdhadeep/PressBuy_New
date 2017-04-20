package com.techpenta.pressbuy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ProgressBar progressBar;
    LinearLayout lower_contact_tab;
   static WebView mywebview;
    WebView tvCounter_value;
    WebView wvslidecart;
    int clickcount=0;

    LinearLayout btnAboutus;
    LinearLayout btnTermsOfUse;
    LinearLayout btnPrivacy;
    LinearLayout btnContactus;
    LinearLayout btnWorkwithUs;

    ImageView navheader1;
    ImageView navheader2;

    ImageView menuSearchToolbar;
    LinearLayout menuBagToolbar;
    ImageView menuNavToolbar;
    LinearLayout llsearch;
    LinearLayout llcart;
    LinearLayout lllocation;
    RelativeLayout slidetab2;
    LinearLayout slidetab1;
    LinearLayout slidetab3;
    Boolean slidecount1=true;
    Boolean slidecount2=true;
    Boolean slidecount3=true;

    EditText etSearch;
    ImageView ivSearch;
    String txtSearch;

    ImageView slideaddcart;
    ImageView slideremovecart;

    EditText etLocation;
    ImageView ivLocation;
    String txtLocationSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);


        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        lower_contact_tab=(LinearLayout)findViewById(R.id.low);

        btnAboutus=(LinearLayout)findViewById(R.id.btnAboutus);
        btnTermsOfUse=(LinearLayout)findViewById(R.id.btnTermsOfUse);
        btnPrivacy=(LinearLayout)findViewById(R.id.btnPrivacy);
        btnContactus=(LinearLayout)findViewById(R.id.btnContactus);
        btnWorkwithUs=(LinearLayout)findViewById(R.id.btnWorkwithUs);

        wvslidecart=(WebView) findViewById(R.id.wvslidecart) ;
        tvCounter_value=(WebView) findViewById(R.id.tvCounter_value) ;


        llsearch=(LinearLayout) findViewById(R.id.llsearch);
        llcart=(LinearLayout) findViewById(R.id.llcart);
        lllocation=(LinearLayout)findViewById(R.id.lllocation);

        slidetab2=(RelativeLayout) findViewById(R.id.slidetab2cart);
        slidetab1=(LinearLayout) findViewById(R.id.slidetab1search);
        slidetab3=(LinearLayout)findViewById(R.id.slidetab3location);
        menuSearchToolbar=(ImageView)findViewById(R.id.menuSearchToolbar);
        menuBagToolbar=(LinearLayout) findViewById(R.id.menuBagToolbar);
        menuNavToolbar=(ImageView)findViewById(R.id.menuNavToolbar);


        etSearch=(EditText)findViewById(R.id.etSearch);
        ivSearch=(ImageView)findViewById(R.id.ivSearch);

        slideaddcart=(ImageView)findViewById(R.id.slideaddcart);
        slideremovecart=(ImageView)findViewById(R.id.slideremovecart);



        etLocation=(EditText)findViewById(R.id.etLocation);
        ivLocation=(ImageView)findViewById(R.id.ivLocation);


        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSearch=etSearch.getText().toString();
                final Intent i = new Intent(getApplicationContext(), DrawerActivity.class);
                i.putExtra("idsearch1", "https://www.pressbuy.com/?s=");
                i.putExtra("txtSearch",""+txtSearch);
                i.putExtra("idsearch2","&header-off=1");
                Log.i("txtSearch",txtSearch);
                startActivity(i);
            }
        });






        menuSearchToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                llcart.setVisibility(View.GONE);
                if(slidecount1==true) {
                    lllocation.setVisibility(View.GONE);
                    llsearch.setVisibility(View.VISIBLE);
                    slidetab1.setVisibility(View.VISIBLE);
                    Animation animation1 =
                            AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_to_left);

                    slidetab1.startAnimation(animation1);
                    slidecount1 = false;
                }
                else {

                    slidetab1.setVisibility(View.GONE);
                    llsearch.setVisibility(View.GONE);
                    slidecount1=true;
                }
            }
        });



        menuBagToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                lllocation.setVisibility(View.GONE);
                llsearch.setVisibility(View.GONE);
                if(slidecount2==true) {


                    wvslidecart.loadUrl("http://www.pressbuy.com/rest-api/");
                    llcart.setVisibility(View.VISIBLE);
                    slidetab2.setVisibility(View.VISIBLE);
                    Animation animation1 =
                            AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_to_left);
                    slidetab2.startAnimation(animation1);
                    slidecount2 = false;
                }
                else {

                    slidetab2.setVisibility(View.GONE);

                    llcart.setVisibility(View.GONE);
                    slidecount2=true;
                }
            }
        });

        slideaddcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DrawerActivity.class);
                i.putExtra("id", "https://www.pressbuy.com/cart/?header-off=1");
                startActivity(i);
            }
        });
        slideremovecart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DrawerActivity.class);
                i.putExtra("id", "http://www.pressbuy.com/cart/?empty-cart&header-off=1");
                startActivity(i);
            }
        });

        menuNavToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                llcart.setVisibility(View.GONE);
                llsearch.setVisibility(View.GONE);
                if(slidecount3==true) {



                    lllocation.setVisibility(View.VISIBLE);
                    slidetab3.setVisibility(View.VISIBLE);
                    Animation animation1 =
                            AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_to_left);

                    slidetab3.startAnimation(animation1);
                    slidecount3 = false;
                }
                else {

                    slidetab3.setVisibility(View.GONE);
                    lllocation.setVisibility(View.GONE);
                    slidecount3=true;
                }

            }
        });



        ivLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtLocationSearch=etLocation.getText().toString();
                final Intent i = new Intent(getApplicationContext(), DrawerActivity.class);
                i.putExtra("idsearch1", "https://www.pressbuy.com/search-location/?header-off=1&&zip=%5C");
                i.putExtra("txtSearch",""+txtLocationSearch);
                Log.i("txtSearch",txtLocationSearch);
                startActivity(i);
            }
        });



        final FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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


        Intent iin= getIntent();
        String url = iin.getExtras().getString("id");
        String idsearch1 = iin.getExtras().getString("idsearch1");
        String txtSearch = iin.getExtras().getString("txtSearch");
        String idsearch2 = iin.getExtras().getString("idsearch2");
        Log.i("urlfinal",idsearch1+txtSearch+idsearch2);
        String url2=idsearch1+txtSearch+idsearch2;
        String urlLocation=idsearch1+txtSearch;


        lower_contact_tab.setVisibility(View.INVISIBLE);


        if(CheckNetwork.isInternetAvailable(getApplicationContext())) {

            mywebview = (WebView) findViewById(R.id.webView3);
            WebSettings webSettings = mywebview.getSettings();
            webSettings.setJavaScriptEnabled(true);
            if(url!=null){
            mywebview.loadUrl(url);
            }
            else if (url2!=null)
            {
                mywebview.loadUrl(url2);
            }
            else
            {
                mywebview.loadUrl(urlLocation);
            }
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




    }//oncreate ends here

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
        getMenuInflater().inflate(R.menu.drawer, menu);
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
