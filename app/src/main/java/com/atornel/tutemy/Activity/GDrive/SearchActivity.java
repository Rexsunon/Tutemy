package com.atornel.tutemy.Activity.GDrive;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.atornel.tutemy.Adapter.PostAdapter;
import com.atornel.tutemy.Common.HTTPDataHandler;
import com.atornel.tutemy.Model.PostModel.PostObject;
import com.atornel.tutemy.R;
import com.google.gson.Gson;

import static com.atornel.tutemy.Util.APILinks.GDRIVE_BASE_URL;
import static com.atornel.tutemy.Util.APILinks.TORRENT_BASE_URL;

public class SearchActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PostObject postObject;
    public String stringSearch;
    public MenuItem searchItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent intent = getIntent();
        stringSearch = intent.getStringExtra("search");
        setTitle(stringSearch);
        String apiLink = GDRIVE_BASE_URL + "wp-json/wp/v2/posts?search="+stringSearch+"&per_page=100";

        Log.i("SearchLink", apiLink);



        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(SearchActivity.this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        AsyncTask<String, String, String> loadFeedAsync = new AsyncTask<String, String, String>() {

            ProgressDialog mDialog = new ProgressDialog(SearchActivity.this);

            @Override
            protected void onPreExecute() {
                mDialog.setMessage("Loading, Please Wait...");
                mDialog.show();
            }

            @Override
            protected String doInBackground(String... params) {

                String result;
                String result1;
                HTTPDataHandler http = new HTTPDataHandler();

                result1 = http.GetHTTPData(params[0]);
                result = "{\"items\": " +result1+ "}";
                Log.i("JSONResult", result);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {

                mDialog.dismiss();
                postObject = new Gson().fromJson(s,PostObject.class);
                PostAdapter adapter = new PostAdapter(postObject, SearchActivity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        };

        String url_get_data = apiLink;
        loadFeedAsync.execute(url_get_data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        searchItem = menu.findItem(R.id.ic_searchView);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener((new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                String apiLink = TORRENT_BASE_URL + "wp-json/wp/v2/posts?search="+query+"&per_page=100";

                AsyncTask<String, String, String> loadFeedAsync = new AsyncTask<String, String, String>() {

                    ProgressDialog mDialog = new ProgressDialog(SearchActivity.this);

                    @Override
                    protected void onPreExecute() {
                        mDialog.setMessage("Loading, Please Wait...");
                        mDialog.show();
                    }

                    @Override
                    protected String doInBackground(String... params) {

                        String result;
                        String result1;
                        HTTPDataHandler http = new HTTPDataHandler();

                        result1 = http.GetHTTPData(params[0]);
                        result = "{\"items\": " +result1+ "}";
                        Log.i("JSONResult", result);
                        return result;
                    }

                    @Override
                    protected void onPostExecute(String s) {

                        mDialog.dismiss();
                        postObject = new Gson().fromJson(s,PostObject.class);
                        PostAdapter adapter = new PostAdapter(postObject, SearchActivity.this);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    }
                };

                String url_get_data = apiLink;
                loadFeedAsync.execute(url_get_data);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        }));

        MenuItemCompat.setOnActionExpandListener(searchItem,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        // Do something when collapsed
                        return true; // Return true to collapse action view
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        // Do something when expanded
                        return true; // Return true to expand action view
                    }
                });

        return true;

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
