package com.example.team_23_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.team_23_project.Activities.BuildingViewActivity;

public class SearchBarBuildings extends AppCompatActivity {

    ListView listView;
    String[] buildings = {"Urban Sciences Building", "Institute of Genetic Medicine\n" +
            "and NESCI", "Business School", "Faculty of Medical Sciences", "Old Library Building",
            "Armstrong Building", "Bedson Building", "Students Union", "Herschel Building", "Politic" +
            "s Building", "Newcastle Law School", "Architecture Building", "Medical School"};

    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbar_buildings);

        listView = findViewById(R.id.listView);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, buildings);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = (String) parent.getItemAtPosition(position);

            if (!selectedItem.equals("Urban Sciences Building")){
                Toast.makeText(SearchBarBuildings.this, "We're sorry, but we do not have support for this building yet",
                        Toast.LENGTH_SHORT).show();
            } else{
                Intent intent = new Intent(SearchBarBuildings.this, BuildingViewActivity.class);
                SearchBarBuildings.this.startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.nav_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.nav_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setIconified(false);
        searchView.setQueryHint("Search Building");

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
