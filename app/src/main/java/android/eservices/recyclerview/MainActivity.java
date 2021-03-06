package android.eservices.recyclerview;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GameActionInterface{

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private GameAdapter gameAdapter;
    private List<GameViewModel> items = new ArrayList<GameViewModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        coordinatorLayout = findViewById(R.id.coordinator_layout);
        setupRecyclerView();




    }

    private void setupRecyclerView() {
        //TODO Bind recyclerview and set its adapter.

        recyclerView = findViewById(R.id.my_recyclerview);

        //DataGenerator data = new DataGenerator();
        items = DataGenerator.generateData();
        gameAdapter = new GameAdapter(this,items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(gameAdapter);
        //Use data generator to get data to display.
    }

    public void displaySnackBar(String message) {
        //TODO write a method that displays a snackbar in the coordinator layout with the "message" parameter as content.
    }

    @Override
    public void onGameInfoClicked(String gameTitle) {
    String msg = getString(R.string.game_info_clicked, gameTitle);
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, msg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void onGameClicked(String gameTitle) {
        String msg = getString(R.string.game_clicked, gameTitle);
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, msg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    //TODO create callback methods for item click
    //Use ressource strings to get the text to display

}
