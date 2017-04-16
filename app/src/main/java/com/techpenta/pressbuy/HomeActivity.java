package com.techpenta.pressbuy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ProgressBar progressBar;
    LinearLayout lower_contact_tab;
    com.mindorks.paracamera.Camera camera;
    int clickcount=0;
    int backclickcount=0;
   static WebView mywebview;
    WebView toolWeb;
    WebView wvslidecart;
    boolean doubleBackToExitPressedOnce=false;
    LinearLayout btnAboutus;
    LinearLayout btnTermsOfUse;
    LinearLayout btnPrivacy;
    LinearLayout btnContactus;
    LinearLayout btnWorkwithUs;
    ImageView navheader1;
    ImageView navheader2;
    ImageView menuSearchToolbar;
    FrameLayout menuBagToolbar;
    ImageView menuNavToolbar;
    RelativeLayout slidetab2;
    Boolean slidecount=true;


    static  WebView tvCounter_value;
   static TextView cartcount;
    long delayInMillis = 3000;
    int x=0;
    long lastSec = 0;
    private Timer myTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
        tvCounter_value=(WebView) findViewById(R.id.tvCounter_value);

        // here the icon webview

        wvslidecart=(WebView) findViewById(R.id.wvslidecart) ;
        /* toolWeb.loadUrl("http://www.pressbuy.com/rest-api/");*/


        slidetab2=(RelativeLayout) findViewById(R.id.slidetab2);
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




       /* int delay = 1000; // delay for 1 sec.
        int period = 10000; // repeat every 10 sec.
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {
            public void run()
            {
                toolWeb.loadUrl("http://www.pressbuy.com/rest-api/");
            }
        }, delay, period);
*/





        //cart






        // Build the camera
      //  https://android-arsenal.com/details/1/4415
        camera = new com.mindorks.paracamera.Camera.Builder()
                .resetToCorrectOrientation(true)// it will rotate the camera bitmap to the correct orientation from meta data
                .setTakePhotoRequestCode(1)
                .setDirectory("pics")
                .setName("ali_" + System.currentTimeMillis())
                .setImageFormat(com.mindorks.paracamera.Camera.IMAGE_JPEG)
                .setCompression(75)
                .setImageHeight(1000)// it will try to achieve this height as close as possible maintaining the aspect ratio;
                .build(this);



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

        lower_contact_tab.setVisibility(View.GONE);



            if (CheckNetwork.isInternetAvailable(getApplicationContext())) {
                mywebview = (WebView) findViewById(R.id.webView1);
                WebSettings webSettings = mywebview.getSettings();
                webSettings.setJavaScriptEnabled(true);
                mywebview.loadUrl("http://www.pressbuy.com/mobile-home/");
                mywebview.setWebViewClient(new myWebClient());

            } else {

                Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    camera.takePicture();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        final FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    camera.takePicture();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

//onclick animate lower contact tab




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


        //navigation icons on click

        navheader1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
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





        /*final MyAsynctask myAsynctask = new MyAsynctask(this);
        myAsynctask.execute("http://www.pressbuy.com/rest-api/");*/


       /* final int sekundi =0 ;
        Timer timer = new Timer();
        TimerTask t = new TimerTask() {
            int sec = 0;
            @Override
            public void run() {
                MyAsynctask myAsynctask = new MyAsynctask(getApplicationContext());
                myAsynctask.execute("http://www.pressbuy.com/rest-api/");
            }
        };
        timer.scheduleAtFixedRate(t,10000,5000);*/



     /* while(lastSec>=0) {
            long sec = System.currentTimeMillis() / 500;
            if (sec != lastSec) {
                //code to run
                DownloadWebPageTask task = new DownloadWebPageTask();
                task.execute(new String[] { "http://www.pressbuy.com/rest-api/" });

                lastSec = sec;
            }//If():

        }*/

    }//oncreate




    // Get the bitmap and image path onActivityResult of an activity or fragment
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == com.mindorks.paracamera.Camera.REQUEST_TAKE_PHOTO){
            Bitmap bitmap = camera.getCameraBitmap();
            if(bitmap != null) {
                Toast.makeText(this, "Picture is taken", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this.getApplicationContext(),"Picture not taken!",Toast.LENGTH_SHORT).show();
            }
        }
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

    /* An instance of this class will be registered as a JavaScript interface */


    private class DownloadWebPageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            for (String url : urls) {
                DefaultHttpClient client = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(url);
                try {
                    HttpResponse execute = client.execute(httpGet);
                    InputStream content = execute.getEntity().getContent();

                    BufferedReader buffer = new BufferedReader(
                            new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                        Log.i("respond",response);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
           // tvCounter_value.setText(Html.fromHtml(result));
        }
    }

    public void readWebpage(View view) {
        DownloadWebPageTask task = new DownloadWebPageTask();
        task.execute(new String[] { "http://www.pressbuy.com/rest-api/" });

    }




    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (mywebview.canGoBack()) {
            final ProgressDialog dialog = new ProgressDialog(HomeActivity.this);
            dialog.show();
            long delayInMillis = 3000;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    dialog.dismiss();
                }
            }, delayInMillis);
            mywebview.goBack();
        } else if(doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast toast=Toast.makeText(getApplicationContext(),"Press again to exit",Toast.LENGTH_SHORT);

        View view = toast.getView();
        TextView txt=(TextView)view.findViewById(android.R.id.message);
        txt.setTextColor(Color.BLACK);
        //To change the Background of Toast
        view.setBackgroundColor(Color.TRANSPARENT);

        toast.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
                }
            }, 2000);

       /* else {

         Toast toast=Toast.makeText(getApplicationContext(),"Press again to exit",Toast.LENGTH_SHORT);
            View view = toast.getView();
            //To change the Background of Toast
            view.setBackgroundColor(Color.TRANSPARENT);
            toast.show();
        }

         */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        //noinspection SimplifiableIfStatement
      /*  if (id == R.id.hh2) {




          *//* TextView txt=new TextView(getApplicationContext();
            txt.setBackgroundResource(R.drawable.rect);
            txt.setText(""+x);
            item.setActionView(txt);*//*
        }*/

        return super.onOptionsItemSelected(item);
    }

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
