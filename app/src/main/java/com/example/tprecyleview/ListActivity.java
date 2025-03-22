package com.example.tprecyleview;

import static android.content.ContentValues.TAG;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tprecyleview.adapter.StarAdapter;
import com.example.tprecyleview.beans.Star;
import com.example.tprecyleview.service.StarService;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private List<Star> stars;
    private RecyclerView recyclerView;
    private StarAdapter starAdapter = null;
    private StarService service;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        stars = new ArrayList<>();
        service = StarService.getInstance();
        init();
        recyclerView = findViewById(R.id.recycle_view);
        starAdapter = new StarAdapter(this, service.findAll());
        recyclerView.setAdapter(starAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void init(){
        service.create(new Star("kate bosworth", "https://www.google.com/imgres?q=george%20clooney&imgurl=https%3A%2F%2Fupload.wikimedia.org%2Fwikipedia%2Fcommons%2Fthumb%2F6%2F68%2FGeorge_Clooney_66%25C3%25A8me_Festival_de_Venise_%2528cropped2%2529.jpg%2F800px-George_Clooney_66%25C3%25A8me_Festival_de_Venise_%2528cropped2%2529.jpg&imgrefurl=https%3A%2F%2Ffr.wikiquote.org%2Fwiki%2FGeorge_Clooney&docid=IqNBZVThokGUZM&tbnid=W3xDDaiU4JRRcM&vet=12ahUKEwjynM37m5mMAxWG97sIHZ6XOf0QM3oECBYQAA..i&w=800&h=1100&hcb=2&ved=2ahUKEwjynM37m5mMAxWG97sIHZ6XOf0QM3oECBYQAA", 3.5f));
        service.create(new Star("george clooney", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/68/George_Clooney_66%C3%A8me_Festival_de_Venise_%28cropped2%29.jpg/330px-George_Clooney_66%C3%A8me_Festival_de_Venise_%28cropped2%29.jpg", 3));
        service.create(new Star("michelle rodriguez", "https://fr.web.img4.acsta.net/pictures/19/05/22/10/29/0914375.jpg", 5));
        service.create(new Star("george clooney", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/68/George_Clooney_66%C3%A8me_Festival_de_Venise_%28cropped2%29.jpg/330px-George_Clooney_66%C3%A8me_Festival_de_Venise_%28cropped2%29.jpg", 1));
        service.create(new Star("louise bouroin", "https://fr.web.img4.acsta.net/pictures/15/10/06/10/23/291029.jpg", 5));
        service.create(new Star("louise bouroin", "https://fr.web.img4.acsta.net/pictures/15/10/06/10/23/291029.jpg", 1));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                starAdapter.getFilter().filter(newText);
                return true;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.share){
            String txt = "Stars";
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle("Stars app ")
                    .setText(txt)
                    .startChooser();
        }
        return super.onOptionsItemSelected(item);
    }

}