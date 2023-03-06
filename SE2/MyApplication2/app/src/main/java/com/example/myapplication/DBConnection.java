package com.example.myapplication;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


public class DBConnection extends AsyncTask<String,String,String> {

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        System.out.println(s);
        MainActivity.retServer.setText(s);
    }

    @Override
    protected String doInBackground(String... input) {
        String answer="";
        Socket socket = null;
        try {
            String address = "se2-isys.aau.at";
            int port = 53212;
            socket = new Socket(address, port);

            String matrikelNr = input[0];
            PrintStream ps = new PrintStream(socket.getOutputStream());     //Write auf OutPutStream
            ps.println(Integer.parseInt(matrikelNr));
            ps.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            do{
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }while(!br.ready());
            answer+=br.readLine();
            return answer;              //return von doInBackground geht in onPostExecute

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
