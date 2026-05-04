package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    String name;
    TextView txtQuestion, txtTimer;
    Button btnA, btnB, btnC, btnD;

    int currentQuestion = 0;
    int score = 0;

    String[][] questions;
    String[][] selectedQuestions;

    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        txtQuestion = findViewById(R.id.txtQuestion);
        txtTimer = findViewById(R.id.txtTimer);

        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);

        String category = getIntent().getStringExtra("CATEGORY");
        name = getIntent().getStringExtra("NAME");

        if (category.equals("ANIMALS")) {
            questions = animalsQuestions;
        } else if (category.equals("SCIENCE")) {
            questions = scienceQuestions;
        } else {
            questions = sportQuestions;
        }

        // losowanie 5 pytań
        selectedQuestions = getRandomQuestions(questions, 5);

        loadQuestion();

        btnA.setOnClickListener(v -> checkAnswer(btnA.getText().toString()));
        btnB.setOnClickListener(v -> checkAnswer(btnB.getText().toString()));
        btnC.setOnClickListener(v -> checkAnswer(btnC.getText().toString()));
        btnD.setOnClickListener(v -> checkAnswer(btnD.getText().toString()));
    }

    private String[][] getRandomQuestions(String[][] allQuestions, int count) {
        List<String[]> list = new ArrayList<>();

        Collections.addAll(list, allQuestions);
        Collections.shuffle(list);

        String[][] randomQuestions = new String[count][6];

        for (int i = 0; i < count; i++) {
            randomQuestions[i] = list.get(i);
        }

        return randomQuestions;
    }

    private void loadQuestion() {
        if (currentQuestion >= selectedQuestions.length) {
            endQuiz();
            return;
        }

        String[] q = selectedQuestions[currentQuestion];

        txtQuestion.setText(q[0]);
        btnA.setText(q[1]);
        btnB.setText(q[2]);
        btnC.setText(q[3]);
        btnD.setText(q[4]);

        startTimer();
    }

    private void checkAnswer(String answer) {
        timer.cancel();

        if (answer.equals(selectedQuestions[currentQuestion][5])) {
            score++;
        }

        currentQuestion++;
        loadQuestion();
    }

    private void startTimer() {
        timer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtTimer.setText("Czas: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                currentQuestion++;
                loadQuestion();
            }
        }.start();
    }

    private void endQuiz() {
        String category = getIntent().getStringExtra("CATEGORY");

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("TOTAL", selectedQuestions.length);
        intent.putExtra("NAME", name);
        intent.putExtra("CATEGORY", category);

        startActivity(intent);
        finish();
    }

    String[][] animalsQuestions = {
            {"Największy ssak?", "Słoń", "Płetwal błękitny", "Rekin", "Nosorożec", "Płetwal błękitny"},
            {"Najszybsze zwierzę lądowe?", "Lew", "Gepard", "Tygrys", "Koń", "Gepard"},
            {"Które zwierzę lata?", "Pingwin", "Struś", "Nietoperz", "Kura", "Nietoperz"},
            {"Ile nóg ma pająk?", "6", "8", "10", "4", "8"},
            {"Największy ptak?", "Orzeł", "Struś", "Kura", "Pingwin", "Struś"},
            {"Zwierzę z trąbą?", "Koń", "Słoń", "Krowa", "Żyrafa", "Słoń"},
            {"Kto żyje w wodzie?", "Tygrys", "Delfin", "Lis", "Koń", "Delfin"},
            {"Największy kot?", "Lew", "Tygrys", "Jaguar", "Gepard", "Tygrys"},
            {"Zwierzę nocne?", "Sowa", "Kura", "Kaczka", "Gołąb", "Sowa"},
            {"Zwierzę roślinożerne?", "Wilk", "Lew", "Krowa", "Tygrys", "Krowa"},
            {"Zwierzę mięsożerne?", "Koń", "Owca", "Wilk", "Krowa", "Wilk"},
            {"Gdzie żyje pingwin?", "Afryka", "Antarktyda", "Europa", "Azja", "Antarktyda"},
            {"Zwierzę z pasami?", "Lew", "Tygrys", "Zebra", "Puma", "Zebra"},
            {"Zwierzę z długą szyją?", "Koń", "Żyrafa", "Słoń", "Krowa", "Żyrafa"},
            {"Największy gad?", "Krokodyl", "Wąż", "Jaszczurka", "Żółw", "Krokodyl"},
            {"Zwierzę domowe?", "Lew", "Tygrys", "Pies", "Wilk", "Pies"},
            {"Zwierzę w stadzie?", "Lew", "Wilk", "Zebra", "Lis", "Zebra"},
            {"Zwierzę w lesie?", "Rekin", "Lis", "Delfin", "Wieloryb", "Lis"},
            {"Zwierzę szybujące?", "Orzeł", "Kura", "Pingwin", "Struś", "Orzeł"},
            {"Zwierzę z rogami?", "Koń", "Krowa", "Kot", "Pies", "Krowa"}
    };

    String[][] scienceQuestions = {
            {"Planeta najbliżej Słońca?", "Ziemia", "Merkury", "Mars", "Wenus", "Merkury"},
            {"H2O to?", "Tlen", "Woda", "Wodór", "Hel", "Woda"},
            {"Ile to 2+2?", "3", "4", "5", "6", "4"},
            {"Grawitację odkrył?", "Newton", "Einstein", "Tesla", "Edison", "Newton"},
            {"Słońce to?", "Planeta", "Gwiazda", "Księżyc", "Kometa", "Gwiazda"},
            {"Największa planeta?", "Mars", "Ziemia", "Jowisz", "Saturn", "Jowisz"},
            {"DNA to?", "Białko", "Kod genetyczny", "Tłuszcz", "Woda", "Kod genetyczny"},
            {"Prędkość światła ~?", "300 km/h", "300000 km/s", "150 km/h", "1000 km/s", "300000 km/s"},
            {"Atom składa się z?", "Komórek", "Protonów, neutronów, elektronów", "Tkanek", "Cząsteczek", "Protonów, neutronów, elektronów"},
            {"Ziemia krąży wokół?", "Księżyca", "Słońca", "Marsa", "Wenus", "Słońca"},
            {"Gaz do oddychania?", "CO2", "Tlen", "Azot", "Hel", "Tlen"},
            {"Najmniejsza jednostka życia?", "Tkanka", "Komórka", "Organ", "Atom", "Komórka"},
            {"Temperatura wrzenia wody?", "50°C", "100°C", "0°C", "200°C", "100°C"},
            {"Ciało stałe ma?", "Stały kształt", "Brak kształtu", "Gazowy stan", "Płynny stan", "Stały kształt"},
            {"Zjawisko elektryczne?", "Grawitacja", "Prąd", "Wiatr", "Deszcz", "Prąd"},
            {"Organ pompujący krew?", "Płuca", "Serce", "Mózg", "Wątroba", "Serce"},
            {"Księżyc to?", "Planeta", "Satelita", "Gwiazda", "Asteroida", "Satelita"},
            {"Ile kontynentów?", "5", "6", "7", "8", "7"},
            {"Fotosynteza zachodzi w?", "Korzeniu", "Liściach", "Łodydze", "Kwiacie", "Liściach"},
            {"Ziemia ma kształt?", "Płaski", "Kulisty", "Kwadratowy", "Trójkątny", "Kulisty"}
    };

    String[][] sportQuestions = {
            {"Ile graczy w piłce nożnej?", "9", "10", "11", "12", "11"},
            {"Kto wygrał Euro 2016?", "Polska", "Niemcy", "Francja", "Portugalia", "Portugalia"},
            {"Koszykówka – ile punktów za rzut za linią?", "2", "3", "4", "1", "3"},
            {"Tenis – ile setów?", "3 lub 5", "1", "2", "10", "3 lub 5"},
            {"Piłka ręczna – ilu graczy?", "5", "6", "7", "8", "7"},
            {"Najpopularniejszy sport?", "Koszykówka", "Piłka nożna", "Tenis", "Golf", "Piłka nożna"},
            {"Olimpiada co ile lat?", "2", "3", "4", "5", "4"},
            {"Skok w dal – sport?", "Lekkoatletyka", "Pływanie", "Boks", "Tenis", "Lekkoatletyka"},
            {"Messi gra w?", "Kosza", "Piłkę nożną", "Tenisa", "Hokeja", "Piłkę nożną"},
            {"NBA to liga?", "Piłki nożnej", "Koszykówki", "Siatkówki", "Tenisa", "Koszykówki"},
            {"Siatkówka – ilu graczy?", "5", "6", "7", "8", "6"},
            {"Tour de France to?", "Bieg", "Rower", "Pływanie", "Piłka", "Rower"},
            {"F1 to?", "Piłka", "Wyścigi", "Tenis", "Boks", "Wyścigi"},
            {"Ile połówek w meczu?", "1", "2", "3", "4", "2"},
            {"Kto zdobywa gola?", "Koszykarz", "Piłkarz", "Tenisista", "Bokser", "Piłkarz"},
            {"Hokej gra się na?", "Trawie", "Lodzie", "Piasku", "Betonie", "Lodzie"},
            {"Boks to?", "Sport walki", "Gra", "Bieg", "Sport wodny", "Sport walki"},
            {"Maraton ile km?", "21", "42", "10", "5", "42"},
            {"Skoki narciarskie to?", "Zima", "Lato", "Jesień", "Wiosna", "Zima"},
            {"Piłka siatkowa – siatka?", "Tak", "Nie", "Czasem", "Nigdy", "Tak"}
    };
}