package com.example.loginform;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.net.Uri;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static java.net.Proxy.Type.HTTP;

/**
 * Created by yuyanozaki on 2017/01/20.
 */

public class DataBaseHelper extends AsyncTask<String,Void,String> {
    Context context;
    DataBaseHelper(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {//Before working doInBackground(),in loading time this method is working
        super.onPreExecute();

    }


    @Override
    protected String doInBackground(String... params) {//all working is in this method. etc) PHP communication,

        String reg_url = "http://10.0.1.34/user/register.php";//10.0.1.34は自分自身のIPアドレス
        String method = params[0];
        String login_url = "http://10.0.1.34/user/login.php";
        //Register Process
        if(method.equals("raj")){
            String name = params[1];
            String email = params[2] ;
            String phone = params[3];
            String pass = params[4];
            try {
                URL url = new URL(reg_url);//get URL
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                //URLを送る許可を与える、If I wanna use POST Connection ,I will need to set HttpURLConnection
                httpURLConnection.setRequestMethod("POST");//send the request
                httpURLConnection.setDoOutput(true);//Outputしていいかを決めるPermission
                OutputStream outputStream = httpURLConnection.getOutputStream();//Dataをdatabaseにoutputしたい時はこのクラスを使う。
                //dataを読み書きしたい時はBufferedWriterを使う。SQLiteDataBaseのgetReadable or getWritableと同じ役割。Permission
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"utf-8"));
                //変数にandroidのdataを格納。このdataはPHPが読み取れるようにutf-8に変えたものである。
                String data = URLEncoder.encode("name","utf-8")+"="+URLEncoder.encode(name,"utf-8")+"&"+
                        URLEncoder.encode("Email","utf-8")+"="+URLEncoder.encode(email,"utf-8")+"&"+
                        URLEncoder.encode("PhoneNo","utf-8")+"="+URLEncoder.encode(phone,"utf-8")+"&"+
                        URLEncoder.encode("PassWord","utf-8")+"="+URLEncoder.encode(pass,"utf-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream is = httpURLConnection.getInputStream();
                is.close();
                return "Register success";
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Login Process
        else if(method.equals("Login")){
            String name = params[1];
            String pass = params[2];
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"utf-8"));

                String data = URLEncoder.encode("name","utf-8")+"="+URLEncoder.encode(name,"utf-8")+"&"+
                        URLEncoder.encode("PassWord","utf-8")+"="+URLEncoder.encode(pass,"utf-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));

                StringBuilder stringBuilder =  new StringBuilder();//in order to create String
                String line = null;
                while((line=bufferedReader.readLine())!=null){
                    stringBuilder.append(line+"\n");
                }

                String result = stringBuilder.toString();
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (ProtocolException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }


        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) { //After working doInBackground(),this method is in order to show result which is whether login success or not
        super.onPostExecute(s);

        if(s.equals("Register success")){
            Toast.makeText(context,"Register success",Toast.LENGTH_LONG).show();
        }
        else{
            String data = s.trim();
            if(data.equals("success")){
                Toast.makeText(context,data,Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(context,data,Toast.LENGTH_LONG).show();
            }
        }

    }
}
