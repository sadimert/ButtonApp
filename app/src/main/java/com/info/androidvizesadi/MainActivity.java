package com.info.androidvizesadi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button button1,button2,button3;
    Button button4,button5,button6;
    private final int REQ_CODE = 100;
    TextView textView;
    String abfg="çift";
    TextToSpeech t1,tts;

    Button button7,button8,button9,buttonSil,buttonC,buttonT;
    private String[] sayilar={"1-9 arası sayı giriniz","1","2","3","4","5","6","7","8","9"};
    private ArrayAdapter<String> dataAdapterForIller;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();
        textView = findViewById(R.id.text);
        ImageView speak = findViewById(R.id.speak);
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(new Locale("tr", "TR"));

                }
            }
        });
        tts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR)
                    tts.setLanguage(new Locale("tr", "TR"));
            }
        });

        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Tek veya Çift sayılı dügmeleri gizle veya göster");
                try {
                    startActivityForResult(intent, REQ_CODE);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(),"Telefon Desteklemiyor",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void tanimla(){
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);
        button5=findViewById(R.id.button5);
        button6=findViewById(R.id.button6);
        button7=findViewById(R.id.button7);
        button8=findViewById(R.id.button8);
        button9=findViewById(R.id.button9);

    }
    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        textView=findViewById(R.id.text);

        if (requestCode==REQ_CODE) {
            if (resultCode == RESULT_OK && null != data) {
                ArrayList result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                textView.setText((CharSequence) result.get(0));
                if(textView.getText().equals("çift sayılı düğmeleri gizle" )) {
                    if (button2.getVisibility()==View.VISIBLE) {
                        button2.setVisibility(View.INVISIBLE);
                        button4.setVisibility(View.INVISIBLE);
                        button6.setVisibility(View.INVISIBLE);
                        button8.setVisibility(View.INVISIBLE);
                    }
                    else if(button2.getVisibility()==View.INVISIBLE)
                    {
                        tts.speak("Çiftler Zaten Gizli", TextToSpeech.QUEUE_ADD, null);
                    }
                }

                else if(textView.getText().equals("tek sayılı düğmeleri gizle")){
                    if(button3.getVisibility()==View.VISIBLE) {
                        button1.setVisibility(View.INVISIBLE);
                        button3.setVisibility(View.INVISIBLE);
                        button5.setVisibility(View.INVISIBLE);
                        button7.setVisibility(View.INVISIBLE);
                        button9.setVisibility(View.INVISIBLE);
                    }
                    else if(button3.getVisibility()==View.INVISIBLE)
                    {
                        tts.speak("Tekler Zaten Gizli", TextToSpeech.QUEUE_ADD, null);
                    }
                }
                else if(textView.getText().equals("çift sayılı düğmeleri göster")) {
                    if (button2.getVisibility()==View.INVISIBLE) {
                        button2.setVisibility(View.VISIBLE);
                        button4.setVisibility(View.VISIBLE);
                        button6.setVisibility(View.VISIBLE);
                        button8.setVisibility(View.VISIBLE);
                    }
                    else if(button2.getVisibility()==View.VISIBLE)
                    {
                        tts.speak("Çiftler Zaten Görünür", TextToSpeech.QUEUE_ADD, null);
                    }
                }
                else if(textView.getText().equals("tek sayılı düğmeleri göster")){
                    if(button3.getVisibility()==View.INVISIBLE) {
                        button1.setVisibility(View.VISIBLE);
                        button3.setVisibility(View.VISIBLE);
                        button5.setVisibility(View.VISIBLE);
                        button7.setVisibility(View.VISIBLE);
                        button9.setVisibility(View.VISIBLE);
                    }
                    else if(button3.getVisibility()==View.VISIBLE)
                    {
                        tts.speak("Tekler Zaten Görünür", TextToSpeech.QUEUE_ADD, null);
                    }
                }
            }

        }

    }
}
