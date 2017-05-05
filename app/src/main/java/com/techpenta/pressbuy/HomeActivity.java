package com.techpenta.pressbuy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ProgressBar progressBar;
    LinearLayout lower_contact_tab;
    com.mindorks.paracamera.Camera camera;
    int clickcount=0;
    int backclickcount=0;
    WebView mywebview;
    boolean doubleBackToExitPressedOnce=false;
    LinearLayout btnAboutus;
    LinearLayout btnTermsOfUse;
    LinearLayout btnPrivacy;
    LinearLayout btnContactus;
    LinearLayout btnWorkwithUs;
    ImageView navheader1;
    ImageView navheader2;

    // LogCat tag
    private static final String TAG = HomeActivity.class.getSimpleName();


    // Camera activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    private Uri fileUri; // file url to store image/video

    private Button btnCapturePicture, btnRecordVideo;
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


                    captureImage();

            }
        });

        final FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);

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

        // Checking camera availability
        if (!isDeviceSupportCamera()) {
            Toast.makeText(getApplicationContext(),
                    "Sorry! Your device doesn't support camera",
                    Toast.LENGTH_LONG).show();
            // will close the app if the device does't have camera
            finish();
        }

    }//oncreate


    /**
     * Checking device has camera hardware or not
     * */
    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }




    // Get the bitmap and image path onActivityResult of an activity or fragment



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

        }
    }




    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (mywebview.canGoBack()) {
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
        if (id == R.id.jhbjhbh) {
            return true;
        }

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



    /**
     * Launching camera app to capture image
     */
    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    /**
     * Launching camera app to record video
     */
    private void recordVideo() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);

        // set video quality
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file
        // name

        // start the video capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_VIDEO_REQUEST_CODE);
    }

    /**
     * Here we store the file url as it will be null after returning from camera
     * app
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on screen orientation
        // changes
        outState.putParcelable("file_uri", fileUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }



    /**
     * Receiving activity result method will be called after closing the camera
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                // successfully captured the image
                // launching upload activity
                launchUploadActivity(true);


            } else if (resultCode == RESULT_CANCELED) {

                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();

            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }

        } else if (requestCode == CAMERA_CAPTURE_VIDEO_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                // video successfully recorded
                // launching upload activity
                launchUploadActivity(false);

            } else if (resultCode == RESULT_CANCELED) {

                // user cancelled recording
                Toast.makeText(getApplicationContext(),
                        "User cancelled video recording", Toast.LENGTH_SHORT)
                        .show();

            } else {
                // failed to record video
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to record video", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    private void launchUploadActivity(boolean isImage){
        Intent i = new Intent(HomeActivity.this, UploadActivity.class);
        i.putExtra("filePath", fileUri.getPath());
        Log.i("filepath",fileUri.getPath().toString());

        i.putExtra("isImage", isImage);
        startActivity(i);
    }

    /**
     * ------------ Helper Methods ----------------------
     * */

    /**
     * Creating file uri to store image/video
     */
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /**
     * returning image / video
     */
    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                Config.IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(TAG, "Oops! Failed create "
                        + Config.IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

}
