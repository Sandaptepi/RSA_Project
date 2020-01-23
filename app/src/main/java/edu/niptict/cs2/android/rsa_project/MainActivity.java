package edu.niptict.cs2.android.rsa_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.math.BigInteger;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText input, output;
    private String publicKey = "";
    private String privateKey = "";
    private byte[] encodeData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText) findViewById(R.id.input);
        output = (EditText) findViewById(R.id.output);

        try {
            Map<String,Object> keyMap = RSA.initKey();
            publicKey = RSA.getPublicKey(keyMap);
            privateKey = RSA.getPrivateKey(keyMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void encrypt(View v) {

    byte[] rsaData = input.getText().toString().getBytes();

        try {
            encodeData = RSA.encryptByPublicKey(rsaData,getPublicKey());
            String encodeStr = new BigInteger(1,encodeData).toString();
            output.setText(encodeStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void decrypt(View view) {

        try {
            byte[] decodeData = RSA.encryptByPrivateKey(encodeData,getPrivateKey());
            String decodeStr = new String(decodeData);
            output.setText(decodeStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }


}
