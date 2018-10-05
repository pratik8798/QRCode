package com.example.dell.qrcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button scan;
    private TextView printurl;
    private IntentIntegrator qrScan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scan=(Button)findViewById(R.id.button);
        printurl=(TextView)findViewById(R.id.textView);


        qrScan=new IntentIntegrator(this);

        scan.setOnClickListener(this);

    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode ,Intent data)
    {
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null)
        {
            if(result.getContents()==null)
            {
                Toast.makeText(this,"NO result Found",Toast.LENGTH_SHORT).show();

            }
            else
            {
                printurl.setText(result.getContents());
                Linkify.addLinks(printurl, Linkify.WEB_URLS);

            }
        }
        else
        {
            super.onActivityResult(requestCode,resultCode,data);
        }
    }

    @Override
    public void onClick(View v) {
        qrScan.initiateScan();
    }
}
