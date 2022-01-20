package com.example.team_23_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity class for activity_searchbar.xml. Extends AppCompatActivity
 *
 * @author Louis Ware
 * @version 1.0
 *
 */
public class SearchBar extends AppCompatActivity {

    ListView listView;
    String[] universities = {"Newcastle University", "Northumbria University", "University of Bristol",
            "UCL", "Oxford", "Cambridge",
            "University of Manchester", "University of Liverpool", "University of Birmingham",
            "University of Aberdeen", "University of Bath", "University of Brighton", "University of" +
            " East Anglia", "University of Edinburgh", "University of Glasgow", "Imperial College London",
            "University of Leeds", "University of London", "University of Nottingham", "Swansea University",
            "University of Warwick", "University of York", "University of Westminster", "University of West London"};

    ArrayAdapter<String> arrayAdapter;

    /**
     * Used to start the activity
     *
     * @author Louis Ware
     *
     * @param savedInstanceState reference to a Bundle object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbar);

        listView = findViewById(R.id.listView);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, universities);
        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener((parent, view, position, id) -> { // <- Event handler for items in the list
            String selectedItem = (String) parent.getItemAtPosition(position); // <- Item the user
                                                                               // clicks on as a
                                                                               // String (e.g. will
                                                                               // output 'Oxford'
                                                                               // if user selects oxford)
                if (!selectedItem.equals("Newcastle University")){
                    Toast.makeText(SearchBar.this, "We're sorry, but we do not have support for this University yet",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(SearchBar.this, SearchBarBuildings.class);
                    SearchBar.this.startActivity(intent);
                }
        });
    }

    /**
     * Specifies the options menu for the activity
     *
     * @author Louis Ware
     *
     * @param menu reference to a Menu object
     * @return true if the menu should be displayed, false if not
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.nav_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.nav_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setIconified(false);
        searchView.setQueryHint("Search University");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { return false; }

            /**
             * Method called when new text has been entered into the search bar
             *
             * @author Louis Ware
             *
             * @param newText the updated text in the search bar
             * @return false
             */
            @Override
            public boolean onQueryTextChange(String newText) {
                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
