package com.example.minijeux.jeux;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.example.minijeux.R;

public class BlackJack extends AppCompatActivity {
    private List<Integer> deck;
    private List<Integer> playerHand;
    private List<Integer> dealerHand;
    private int playerScore, dealerScore;

    private TextView playerScoreText, dealerScoreText, resultText;
    private Button hitButton, standButton, restartButton;
    private ImageView playerCard1, playerCard2, dealerCard1, dealerCard2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.black_jack);

        playerScoreText = findViewById(R.id.playerScore);
        dealerScoreText = findViewById(R.id.dealerScore);
        resultText = findViewById(R.id.resultText);
        hitButton = findViewById(R.id.hitButton);
        standButton = findViewById(R.id.standButton);
        restartButton = findViewById(R.id.restartButton);
        playerCard1 = findViewById(R.id.playerCard1);
        playerCard2 = findViewById(R.id.playerCard2);
        dealerCard1 = findViewById(R.id.dealerCard1);
        dealerCard2 = findViewById(R.id.dealerCard2);

        startGame();

        hitButton.setOnClickListener(v -> playerHit());
        standButton.setOnClickListener(v -> dealerTurn());
        restartButton.setOnClickListener(v -> startGame());
    }

    private void startGame() {
        deck = createDeck();
        Collections.shuffle(deck);

        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();

        playerHand.add(drawCard());
        playerHand.add(drawCard());
        dealerHand.add(drawCard());
        dealerHand.add(drawCard());

        playerScore = calculateScore(playerHand);
        dealerScore = calculateScore(dealerHand);

        updateUI();
        checkBlackJack();
    }

    private int drawCard() {
        return deck.remove(0);
    }

    private List<Integer> createDeck() {
        List<Integer> newDeck = new ArrayList<>();
        for (int i = 1; i <= 13; i++) {
            for (int j = 0; j < 4; j++) {
                newDeck.add(i);
            }
        }
        return newDeck;
    }

    private int calculateScore(List<Integer> hand) {
        int score = 0;
        int aces = 0;
        for (int card : hand) {
            if (card > 10) {
                score += 10;
            } else if (card == 1) {
                aces++;
                score += 11;
            } else {
                score += card;
            }
        }
        while (score > 21 && aces > 0) {
            score -= 10;
            aces--;
        }
        return score;
    }

    private void playerHit() {
        playerHand.add(drawCard());
        playerScore = calculateScore(playerHand);
        updateUI();

        if (playerScore > 21) {
            resultText.setText("Vous avez dépassé 21 ! Vous perdez.");
            disableButtons();
        }
    }

    private void dealerTurn() {
        while (dealerScore < 17) {
            dealerHand.add(drawCard());
            dealerScore = calculateScore(dealerHand);
        }
        updateUI();
        checkWinner();
    }

    private void checkBlackJack() {
        if (playerScore == 21) {
            resultText.setText("Blackjack ! Vous gagnez !");
            disableButtons();
        } else if (dealerScore == 21) {
            resultText.setText("Le croupier a un Blackjack. Vous perdez.");
            disableButtons();
        }
    }

    private void checkWinner() {
        if (dealerScore > 21 || playerScore > dealerScore) {
            resultText.setText("Vous gagnez !");
        } else if (playerScore == dealerScore) {
            resultText.setText("Égalité !");
        } else {
            resultText.setText("Le croupier gagne !");
        }
        disableButtons();
    }

    private void disableButtons() {
        hitButton.setEnabled(false);
        standButton.setEnabled(false);
    }

    private void updateUI() {
        playerScoreText.setText("Score Joueur: " + playerScore);
        dealerScoreText.setText("Score Croupier: " + dealerScore);
    }
}
