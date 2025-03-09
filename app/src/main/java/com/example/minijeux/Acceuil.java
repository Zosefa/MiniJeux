package com.example.minijeux;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minijeux.model.Game;
import com.example.minijeux.model.GameAdapter;

import java.util.ArrayList;
import java.util.List;

public class Acceuil extends AppCompatActivity  {

    private RecyclerView recyclerView;
    private GameAdapter gameAdapter;
    private List<Game> gameList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 colonnes

        gameList = new ArrayList<>();
        gameList.add(new Game("BLACK JACK", R.drawable.lucky_card));
        gameList.add(new Game("STAIRS", R.drawable.lucky_card));
        gameList.add(new Game("LUCKY CARDS", R.drawable.lucky_card));
        gameList.add(new Game("MINES", R.drawable.lucky_card));
        gameList.add(new Game("PIRATE GOLD", R.drawable.lucky_card));
        gameList.add(new Game("COINFLIP", R.drawable.lucky_card));
        gameList.add(new Game("WHEEL", R.drawable.lucky_card));
        gameList.add(new Game("COMPUTER", R.drawable.lucky_card));
        gameList.add(new Game("MORE OR LESS", R.drawable.lucky_card));
        gameList.add(new Game("DICE", R.drawable.lucky_card));
        gameList.add(new Game("COMING SOON", R.drawable.lucky_card));

        gameAdapter = new GameAdapter(this, gameList);
        recyclerView.setAdapter(gameAdapter);
    }
}
