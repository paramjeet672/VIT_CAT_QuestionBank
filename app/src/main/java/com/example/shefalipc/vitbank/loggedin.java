package com.example.shefalipc.vitbank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.example.shefalipc.vitbank.util.Myadapter;

import static java.lang.System.exit;


public class loggedin extends ActionBarActivity  {


    private DrawerLayout drawerLayout;
   // private ListView listView;
    RecyclerView mRecyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;

    ActionBarDrawerToggle drawerListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Home");

        setContentView(R.layout.activity_loggedin);
        String[] TITLES = {"Home","SCSE","SITE","Downloads","About us","Log Out"};
        String NAME = "14BCE0742";
        String EMAIL = "";
        int PROFILE = R.drawable.profile;
        int[] ICONS = {R.drawable.home,R.drawable.scse,R.drawable.site,R.drawable.download1,R.drawable.aboutus,R.drawable.settings};

        mRecyclerView = (RecyclerView)findViewById(R.id.RecyclerView);






        mAdapter = new Myadapter(TITLES,ICONS,NAME,EMAIL,PROFILE);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture

        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView

        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                loggedin.this, new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                // TODO Auto-generated method stub

final Intent oops;


                switch (position) {
                    case 1:

                        break;

                    case 2:
                        oops = new Intent(loggedin.this,scse.class);
                        startActivity(oops);

                        break;

                    case 3:oops = new Intent(loggedin.this,site.class);
                        startActivity(oops);
                        break;

                    case 4:
                        oops = new Intent(loggedin.this,notifications.class);
                        startActivity(oops);
                        break;
                    case 5:
                        oops = new Intent(loggedin.this,aboutus.class);
                        startActivity(oops);
                        break;
                    case 6:
                        //oops = new Intent(loggedin.this,MainActivity.class);
                        //startActivity(oops);

                        exit(1);

                }
            }}));


        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        drawerListener= new ActionBarDrawerToggle(this,drawerLayout,R.string.drawer_open,R.string.drawer_close)
        {
            @Override
            public void onDrawerClosed(View drawerView)
            {
                super.onDrawerClosed(drawerView);
            }
            @Override
            public  void onDrawerOpened(View drawerView)
            {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerListener);
        drawerListener.syncState(); //wwill listen to the drawer listener
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        drawerListener.syncState();
    }



    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
class RecyclerItemClickListener implements
        RecyclerView.OnItemTouchListener {
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }



    GestureDetector mGestureDetector;

    public RecyclerItemClickListener(Context context,
                                     OnItemClickListener listener) {
        mListener = listener;
        mGestureDetector = new GestureDetector(context,
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        return true;
                    }
                });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mListener != null
                && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView,
                    view.getChildPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
    }
}
