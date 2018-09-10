package com.example.android.cityapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView txtresult;
public Bitmap bitmap;

    public AppCompatButton appCompatButtonİlçeler, appCompatButtonPlaka, appCompatButtonNüfus, appCompatButtonBölge, appCompatButtonAcıklama, appCompatButtonYöreselOyunlar, appCompatButtonGezilecekyerler;
 //   public AppCompatImageView appCompatImageViewBelediye;
    public ImageView ımageViewBelediye;
    public AppCompatSpinner appCompatSpinnerİl;

    public WebView webView;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // txtresult = (TextView) findViewById(R.id.result);


        myDialog = new Dialog(this);

        appCompatButtonİlçeler = (AppCompatButton) findViewById(R.id.btnİlçeler);
        appCompatButtonPlaka = (AppCompatButton) findViewById(R.id.btnPlaka);
        appCompatButtonNüfus = (AppCompatButton) findViewById(R.id.btnNüfusBilgisi);
        appCompatButtonBölge = (AppCompatButton) findViewById(R.id.btnBölge);
        appCompatButtonAcıklama = (AppCompatButton) findViewById(R.id.btnAcıklama);
        appCompatButtonYöreselOyunlar = (AppCompatButton) findViewById(R.id.btnYöreselOyunlar);
        appCompatButtonGezilecekyerler = (AppCompatButton) findViewById(R.id.btnGezilecekYerler);
        appCompatSpinnerİl = (AppCompatSpinner) findViewById(R.id.spinnerİl);
      //  appCompatImageViewBelediye = (AppCompatImageView) findViewById(R.id.belediyeImage);
      ımageViewBelediye=(ImageView)findViewById(R.id.belediyeImage) ;

webView=(WebView)findViewById(R.id.webview);

        final ArrayList<String> items = getJson("city.json");
        final ArrayList<String> regions = getJsonRegion("city.json");
        final ArrayList<String> populations = getJsonPopulation("city.json");
        final ArrayList<String> information = getJsonInformation("city.json");
        final ArrayList<String> plaka = getJsonPlaka("city.json");
        final ArrayList<String> ilceler = getJsonIlce("city.json");
        final ArrayList<String> folkdance = getJsonFolkDance("city.json");
        final ArrayList<String> link = getJsonLink("city.json");
        final ArrayList<String> logo = getJsonResim("city.json");





        ArrayAdapter<String> adapterilce = new ArrayAdapter<String>(this, R.layout.spinner_item, R.id.txtilce, items);
        appCompatSpinnerİl.setAdapter(adapterilce);
        //    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, R.id.txtilce, ilceler);

        appCompatSpinnerİl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long l) {

                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        //İşlem Blogu
                        bitmap = getBitmapFromURL(logo.get(position));
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        //İşlem Tamamsa
                        ımageViewBelediye.setImageBitmap(bitmap);
                    }
                }.execute();

                webView.loadUrl(link.get(position));
                webView.getSettings().setLoadsImagesAutomatically(true);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebViewClient(new WebViewClient());
                webView.getSettings().setBuiltInZoomControls(true);
                webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

              /* bitmap=getBitmapImage(logo.get(position));

               ımageViewBelediye.setImageBitmap(bitmap);*/

            //  new getImage(ımageViewBelediye).execute(logo.get(position));


                appCompatButtonNüfus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView txt;
                        Button btnClose;
                        myDialog.setContentView(R.layout.popup);
                        txt = (TextView) myDialog.findViewById(R.id.text);
                        txt.setText(populations.get(position));
                        btnClose = (Button) myDialog.findViewById(R.id.btnclose);
                        btnClose.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                myDialog.dismiss();
                            }
                        });
                        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        myDialog.show();
                    }
                });
                appCompatButtonBölge.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView txt;
                        Button btnClose;
                        myDialog.setContentView(R.layout.popup);
                        txt = (TextView) myDialog.findViewById(R.id.text);
                        txt.setText(regions.get(position));
                        btnClose = (Button) myDialog.findViewById(R.id.btnclose);
                        btnClose.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                myDialog.dismiss();
                            }
                        });
                        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        myDialog.show();
                    }
                });

                appCompatButtonAcıklama.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView txt;
                        Button btnClose;
                        myDialog.setContentView(R.layout.popup);
                        txt = (TextView) myDialog.findViewById(R.id.text);
                        txt.setText(information.get(position));
                        btnClose = (Button) myDialog.findViewById(R.id.btnclose);
                        btnClose.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                myDialog.dismiss();
                            }
                        });
                        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        myDialog.show();
                    }
                });
                appCompatButtonPlaka.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView txt;
                        Button btnClose;
                        myDialog.setContentView(R.layout.popup);
                        txt = (TextView) myDialog.findViewById(R.id.text);
                        txt.setText(plaka.get(position));
                        btnClose = (Button) myDialog.findViewById(R.id.btnclose);
                        btnClose.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                myDialog.dismiss();
                            }
                        });
                        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        myDialog.show();
                    }
                });
                appCompatButtonİlçeler.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView txt;
                        Button btnClose;
                        myDialog.setContentView(R.layout.popup);
                        txt = (TextView) myDialog.findViewById(R.id.text);
                        txt.setText(ilceler.get(position));
                        btnClose = (Button) myDialog.findViewById(R.id.btnclose);
                        btnClose.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                myDialog.dismiss();
                            }
                        });
                        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        myDialog.show();
                    }
                });
                appCompatButtonYöreselOyunlar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView txt;
                        Button btnClose;
                        myDialog.setContentView(R.layout.popup);
                        txt = (TextView) myDialog.findViewById(R.id.text);
                        txt.setText(folkdance.get(position));
                        btnClose = (Button) myDialog.findViewById(R.id.btnclose);
                        btnClose.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                myDialog.dismiss();
                            }
                        });
                        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        myDialog.show();
                    }
                });

                appCompatButtonGezilecekyerler.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView txt;
                        Button btnClose;
                        myDialog.setContentView(R.layout.popup);
                        txt = (TextView) myDialog.findViewById(R.id.text);
                        txt.setText(link.get(position));
                        btnClose = (Button) myDialog.findViewById(R.id.btnclose);
                        btnClose.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                myDialog.dismiss();
                            }
                        });
                        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        myDialog.show();
                    }
                });



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public Bitmap getBitmapFromURL(String strURL) {
        try {
            URL url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
/*
public  class getImage extends AsyncTask<String,Void,Bitmap>{

        ImageView imageview;
        public getImage(ImageView img){
            this.imageview=img;
        }

    @Override
    protected Bitmap doInBackground(String... strings) {
         String urlDisplay= strings[0];
         ımageViewBelediye=null;
         try {
             InputStream str=new java.net.URL(urlDisplay).openStream();
             bitmap= BitmapFactory.decodeStream(str);
         } catch (MalformedURLException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageview.setImageBitmap(bitmap);
    }
}
*/

   /* public Bitmap getBitmapImage(String  src){
        try{
            URL url=new URL(src);
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input=connection.getInputStream();
            Bitmap myBİtmap= BitmapFactory.decodeStream(input);
            return myBİtmap;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }*/


    public void getSpeechInput(View view) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtresult.setText(result.get(0));
                }
                break;
        }
    }

    public ArrayList<String> getJson(String fileName) {
        JSONArray jsonArray = null;
        ArrayList<String> cList = new ArrayList<String>();
        try {
            InputStream is = getResources().getAssets().open(fileName);
            int size = is.available();
            byte[] data = new byte[size];
            is.read(data);
            is.close();
            String json = new String(data, "UTF-8");
            jsonArray = new JSONArray(json);
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    cList.add(jsonArray.getJSONObject(i).getString("il"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException je) {
            je.printStackTrace();
        }
        return cList;
    }

    public ArrayList<String> getJsonRegion(String fileName) {
        JSONArray jsonArray = null;
        ArrayList<String> cList = new ArrayList<String>();
        try {
            InputStream is = getResources().getAssets().open(fileName);
            int size = is.available();
            byte[] data = new byte[size];
            is.read(data);
            is.close();
            String json = new String(data, "UTF-8");
            jsonArray = new JSONArray(json);
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    cList.add(jsonArray.getJSONObject(i).getString("region"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException je) {
            je.printStackTrace();
        }
        return cList;
    }

    public ArrayList<String> getJsonPopulation(String fileName) {
        JSONArray jsonArray = null;
        ArrayList<String> cList = new ArrayList<String>();
        try {
            InputStream is = getResources().getAssets().open(fileName);
            int size = is.available();
            byte[] data = new byte[size];
            is.read(data);
            is.close();
            String json = new String(data, "UTF-8");
            jsonArray = new JSONArray(json);
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    cList.add(jsonArray.getJSONObject(i).getString("population"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException je) {
            je.printStackTrace();
        }
        return cList;
    }

    public ArrayList<String> getJsonInformation(String fileName) {
        JSONArray jsonArray = null;
        ArrayList<String> cList = new ArrayList<String>();
        try {
            InputStream is = getResources().getAssets().open(fileName);
            int size = is.available();
            byte[] data = new byte[size];
            is.read(data);
            is.close();
            String json = new String(data, "UTF-8");
            jsonArray = new JSONArray(json);
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    cList.add(jsonArray.getJSONObject(i).getString("information"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException je) {
            je.printStackTrace();
        }
        return cList;
    }

    public ArrayList<String> getJsonPlaka(String fileName) {
        JSONArray jsonArray = null;
        ArrayList<String> cList = new ArrayList<String>();
        try {
            InputStream is = getResources().getAssets().open(fileName);
            int size = is.available();
            byte[] data = new byte[size];
            is.read(data);
            is.close();
            String json = new String(data, "UTF-8");
            jsonArray = new JSONArray(json);
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    cList.add(jsonArray.getJSONObject(i).getString("plaka"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException je) {
            je.printStackTrace();
        }
        return cList;
    }

    public ArrayList<String> getJsonIlce(String fileName) {
        JSONArray jsonArray = null;
        ArrayList<String> cList = new ArrayList<String>();
        try {
            InputStream is = getResources().getAssets().open(fileName);
            int size = is.available();
            byte[] data = new byte[size];
            is.read(data);
            is.close();
            String json = new String(data, "UTF-8");
            jsonArray = new JSONArray(json);
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    cList.add(jsonArray.getJSONObject(i).getString("ilceleri"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException je) {
            je.printStackTrace();
        }
        return cList;
    }

    public ArrayList<String> getJsonFolkDance(String fileName) {
        JSONArray jsonArray = null;
        ArrayList<String> cList = new ArrayList<String>();
        try {
            InputStream is = getResources().getAssets().open(fileName);
            int size = is.available();
            byte[] data = new byte[size];
            is.read(data);
            is.close();
            String json = new String(data, "UTF-8");
            jsonArray = new JSONArray(json);
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    cList.add(jsonArray.getJSONObject(i).getString("folkdance"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException je) {
            je.printStackTrace();
        }
        return cList;
    }

    final public ArrayList<String> getJsonLink(String fileName) {
        JSONArray jsonArray = null;
        ArrayList<String> cList = new ArrayList<String>();
        try {
            InputStream is = getResources().getAssets().open(fileName);
            int size = is.available();
            byte[] data = new byte[size];
            is.read(data);
            is.close();
            String json = new String(data, "UTF-8");
            jsonArray = new JSONArray(json);
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    cList.add(jsonArray.getJSONObject(i).getString("link"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException je) {
            je.printStackTrace();
        }
        return cList;
    }

    final public ArrayList<String> getJsonResim(String fileName) {
        JSONArray jsonArray = null;
        ArrayList<String> cList = new ArrayList<String>();
        try {
            InputStream is = getResources().getAssets().open(fileName);
            int size = is.available();
            byte[] data = new byte[size];
            is.read(data);
            is.close();
            String json = new String(data, "UTF-8");
            jsonArray = new JSONArray(json);
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    cList.add(jsonArray.getJSONObject(i).getString("resim"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException je) {
            je.printStackTrace();
        }
        return cList;
    }


}

