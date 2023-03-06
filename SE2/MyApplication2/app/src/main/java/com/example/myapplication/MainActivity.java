package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private static Button btnAbschicken;
    private static Button btn2;
    private static TextView matr;
    public static TextView retServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        matr = (TextView)findViewById(R.id.matrikel);
        btnAbschicken = (Button)findViewById(R.id.Abschicken);
        btn2 = (Button)findViewById(R.id.btn2);
        retServer = (TextView)findViewById(R.id.retServer);

        btnAbschicken.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!matr.getText().toString().isEmpty()) {
                    System.out.println("Client Start!");
                    DBConnection client = new DBConnection();
                    client.execute(matr.getText().toString());      //1 großes Array
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                String out = "";
                ArrayList<Integer> numbers = new ArrayList<Integer>();
                String matrikelNummer = matr.getText().toString();

                char[] x = matrikelNummer.toCharArray();
                Arrays.sort(x);
                matrikelNummer = new String(x);

                for(int i = 0; i < matrikelNummer.length(); i++)
                    out+=(isPrime(matrikelNummer.charAt(i)-'0')?"":matrikelNummer.charAt(i)-'0');
                retServer.setText(out);
            }
        });
    }
    private boolean isPrime(int num) {
        if (num <= 1)
            return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }
}