package com.example.team_23_project;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class SearchBar extends AppCompatActivity {

    ListView listView;
    String[] universities = {"Newcastle", "Northumbria", "Bristol", "UCL", "Oxford", "Cambridge", "Manchester", "Liverpool", "Birmingham"};
    // will get universities from database later, for now it's hardcoded in a String array

    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbar);

        listView = findViewById(R.id.listView);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, universities);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // <- Event handler for items in the list
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position); // <- Item the user
                                                                                   // clicks on as a
                                                                                   // String (e.g. will
                                                                                   // output 'Oxford'
                                                                                   // if user selects oxford)

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.nav_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.nav_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setIconified(false);
        searchView.setQueryHint("Search University");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });



        return super.onCreateOptionsMenu(menu);
    }
}
