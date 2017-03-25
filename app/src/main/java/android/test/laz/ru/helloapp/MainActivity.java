package android.test.laz.ru.helloapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

//sava

public class MainActivity extends AppCompatActivity {

    public static String LOG_TAG = "my_log";
    public static TextView fromText;
    public static TextView toText;
    public Button btn;
    public Spinner fromSpinner;
    public Spinner toSpinner;
    public static final String URL = "https://translate.yandex.net/api/v1.5/tr.json";
    public static final String KEY = "?key=trnsl.1.1.20170315T111852Z.8e1ce17582bf567d.c36b8c3cf325da51fd6fa504d099559c62fa9102";
    public static final String TRANLSATE_URL = "/translate";
    public static final String GETLANGS_URL = "/getLangs";
    public static String fromLang = "en";
    public static String toLang = "ru";
    private HashMap<String,String> langDisplayNames;
    public HashSet<String> langPairsSet;
    private RequestQueue reqQueue;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_layout);

        ArrayList<String[]> langPairs = new ArrayList<String[]>();
        NetworkWorker.getInstance(this).getLangs(URL,GETLANGS_URL, KEY);
        fromSpinner = (Spinner) findViewById(R.id.fromSpinner);
        toSpinner = (Spinner) findViewById(R.id.toSpinner);
        //setSpinners(true);
        //setSpinners(false);
        langDisplayNames = new HashMap<String,String>();
        langPairsSet = new HashSet<String>();
        //new FillLangsTask().execute(URL,"/getLangs", KEY);
        fromText = (TextView) findViewById(R.id.fromText);
        toText = (TextView) findViewById(R.id.toText);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inStr = fromText.getText().toString();
                System.out.println("PRESSED");
                NetworkWorker.getInstance(MainActivity.this).translate(URL,TRANLSATE_URL, KEY,  inStr, fromLang, toLang);
            }
        });


    }


    public void setSpinners(boolean isFrom) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Language.fillFromSpinnerList(isFrom));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if(isFrom) {
            fromSpinner.setAdapter(adapter);
            fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    fromLang = fromSpinner.getSelectedItem().toString();
                    System.out.println("Spin from " + fromLang);
                    Log.i("LANG:from ", "");
                    setSpinners(false);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else {
            toSpinner.setAdapter(adapter);
            toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    toLang = toSpinner.getSelectedItem().toString();
                    System.out.println("Spin to " + fromLang);
                    Log.i("LANG:from ", "");
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }

    }


}