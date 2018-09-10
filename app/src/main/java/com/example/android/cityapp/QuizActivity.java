package com.example.android.cityapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    private Button answerButton;
    private RadioGroup radioGroup;
    private RadioButton answer_a, answer_b, answer_c, answer_d, selected_rb;
    private TextView questionText;
    private int currentQuestionIndex;
    private ArrayList<Question> questionArrayList;
    private DatabaseReference databaseReference;
    private int score = 0;
    private TextView scoreText;
    private TextView gameState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Inıt();

        databaseReference = FirebaseDatabase.getInstance().getReference("questions");
        questionArrayList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Question question = postSnapshot.getValue(Question.class);
                    questionArrayList.add(question);
                }
                displayQuestion(currentQuestionIndex);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        answerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioGroup.getCheckedRadioButtonId() != -1) {
                    controlAnswers();
                } else {
                    Toast.makeText(getApplicationContext(), "Seçim yap", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void controlAnswers() {
        if (answerCheck()) {
            score = score + 5;
            scoreText.setText("Puanınız :" + " " + String.valueOf(score));
            Toast.makeText(getApplicationContext(), "TEBRİKLERR", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Üzgünüm", Toast.LENGTH_SHORT).show();
        }
        goOn();
    }

    private void goOn() {
        currentQuestionIndex = (currentQuestionIndex + 1) % questionArrayList.size();
        if (currentQuestionIndex == 0) {
            gameState.setVisibility(View.VISIBLE);
            radioGroup.clearCheck();
            answerButton.setEnabled(false);
            return;
        }
        displayQuestion(currentQuestionIndex);

    }


    private boolean answerCheck() {
        String answer = "";
        int id = radioGroup.getCheckedRadioButtonId();
        selected_rb = (RadioButton) findViewById(id);
        if (selected_rb == answer_a) {
            answer = "a";
        }
        if (selected_rb == answer_b) {
            answer = "b";
        }
        if (selected_rb == answer_c) {
            answer = "c";
        }
        if (selected_rb == answer_d) {
            answer = "d";
        }
        return questionArrayList.get(currentQuestionIndex).isCorrectAnswer(answer);
    }

    private void Inıt() {
        answerButton = (Button) findViewById(R.id.answerButton);
        questionText = (TextView) findViewById(R.id.questionText);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        answer_a = (RadioButton) findViewById(R.id.answer_a);
        answer_b = (RadioButton) findViewById(R.id.answer_b);
        answer_c = (RadioButton) findViewById(R.id.answer_c);
        answer_d = (RadioButton) findViewById(R.id.answer_d);
        scoreText = (TextView) findViewById(R.id.scoreText);
        gameState = (TextView) findViewById(R.id.gameState);
        radioGroup.clearCheck();
    }

    private void displayQuestion(int pos) {
        radioGroup.clearCheck();
        questionText.setText(questionArrayList.get(pos).getQuestionText());
        answer_a.setText(questionArrayList.get(pos).getChoice_a());
        answer_b.setText(questionArrayList.get(pos).getChoice_b());
        answer_c.setText(questionArrayList.get(pos).getChoice_c());
        answer_d.setText(questionArrayList.get(pos).getChoice_d());
    }
}
