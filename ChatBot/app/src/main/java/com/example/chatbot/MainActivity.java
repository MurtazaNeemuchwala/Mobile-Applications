package com.example.chatbot;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    BroadcastReceiver receiver;
    final int delay = 5000;
    String tag = "bruh";
    int state = 0;

    TextView TextView_PhoneNumber, TextView_State, TextView_messageReceived;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView_PhoneNumber = findViewById(R.id.id_textView_phoneNumber);
        TextView_State = findViewById(R.id.id_textview_state);
        TextView_messageReceived = findViewById(R.id.id_textview_messageReceived);

    }

    @Override
    protected void onResume() {
        super.onResume();
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                Object[] objs = (Object[]) intent.getExtras().get("pdus");
                SmsMessage[] smsArr = new SmsMessage[objs.length];
                /*final SmsManager man = SmsManager.getDefault();*/
                for (int i = 0; i != objs.length; i++) {
                    smsArr[i] = SmsMessage.createFromPdu((byte[]) objs[i], intent.getStringExtra("format"));
                    final String address = smsArr[i].getOriginatingAddress();
                    String message = smsArr[i].getMessageBody();
                    Log.d(tag, "message: " + message);

                    TextView_PhoneNumber.setText("Phone Number: " + address);
                    Log.d(tag, "Phone Number: " + address);
                    TextView_messageReceived.setText("Text Received: " + message);
                    Log.d(tag, "Text Received: " + message);

                    // TODO: 4/21/2021 : create an AI to send messages in response to client
                    if (state == 0) {
                        String[] arrKeywords = new String[]{"hello", "hi", "whats up", "greetings sir"};
                        String[] arrResponses = new String[]{"wat u up to", "wuts good", "wazzup", "wat ya doin"};
                        String[] arrResponsesConfused = new String[]{"wdym?", "I don't understand", "wut u talm bout?", "what did he saaaay?!"};

                        if (messageReader(arrKeywords, message) == true) {
                            sendMessage("Hey, "+responsePicker(arrResponses), address);
                            state ++;
                            TextView_State.setText("Greeting State");
                        } else {
                            TextView_State.setText("Confusion State");
                            sendMessage(responsePicker(arrResponsesConfused), address);
                        }

                    }
                    else if (state == 1){
                        String[] arrKeywords = new String[]{"buy", "purchase", "cop", "acquire"};
                        String[] arrResponses = new String[]{"what team's jersey would you like to buy", "what league jersey would you like to buy", "what jersey do you want", "whose jersey do you want"};
                        String[] arrResponsesConfused = new String[]{"wdym?", "I don't understand", "wut u talm bout?", "what did he saaaay?!"};

                        if (messageReader(arrKeywords, message) == true) {
                            sendMessage("Bettt, " +responsePicker(arrResponses)+"?", address);
                            state ++;
                            TextView_State.setText("Inquiry State");
                        } else {
                            TextView_State.setText("COnfusion State");
                            sendMessage(responsePicker(arrResponsesConfused), address);
                        }
                    }
                    else if (state == 2){
                        String[] arrKeywords = new String[]{"please", "plz", "pls", "fs"};
                        String[] arrResponses = new String[]{"send me your billing info", "hmu with ur billing stuff", "drop ur billing info for me", "run me ur billing info"};
                        String[] arrResponsesConfused = new String[]{"wdym?", "I don't understand", "wut u talm bout?", "what did he saaaay?!"};

                        if (messageReader(arrKeywords, message) == true) {
                            sendMessage("Bettt, " +responsePicker(arrResponses) + ". Also send me your email so I can share the shipping details", address);
                            state ++;
                            TextView_State.setText("Purchase State");
                        } else {
                            sendMessage(responsePicker(arrResponsesConfused), address);
                        }
                    }
                    else if (state == 3){
                        String[] arrKeywords = new String[]{"@gmail", "@hotmail", "@outlook", "@proton"};
                        String[] arrResponses = new String[]{"shipping info and confirmation mail will hit you soon", "the deets are being sent to ur mail", "purchase confirmation being sent rn", "check ur mail to see the recipt and deets"};
                        String[] arrResponsesConfused = new String[]{"wdym?", "I don't understand", "wut u talm bout?", "what did he saaaay?!"};

                        if (messageReader(arrKeywords, message) == true) {
                            sendMessage("Bettt, " +responsePicker(arrResponses), address);
                            state ++;
                            TextView_State.setText("Confirmation State");
                        } else {
                            sendMessage(responsePicker(arrResponsesConfused), address);
                        }
                    }
                    else if (state == 4){
                        String[] arrKeywords = new String[]{"thank you", "thanks", "alright", "bet"};
                        String[] arrResponses = new String[]{"thank you for shopping, hope you like your jersey", "Thank you for shopping, and we hope you like your jersey.", "Thank you for shopping with us, and we hope you enjoy your jersey.", "Thank you for shopping with our brand, and we hope you like your jersey."};
                        String[] arrResponsesConfused = new String[]{"wdym?", "I don't understand", "wut u talm bout?", "what did he saaaay?!"};

                        if (messageReader(arrKeywords, message) == true) {
                            sendMessage(responsePicker(arrResponses), address);
                            TextView_State.setText("Conclusion State");
                            state = 100;
                        } else {
                            sendMessage(responsePicker(arrResponsesConfused), address);
                        }
                    }
                }
            }
        };

        String permissionRECEIVE = Manifest.permission.RECEIVE_SMS;
        String permissionSEND = Manifest.permission.SEND_SMS;
        if (ContextCompat.checkSelfPermission(this, permissionRECEIVE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, permissionSEND) != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[2];
            permission_list[0] = permissionRECEIVE;
            permission_list[1] = permissionSEND;
            ActivityCompat.requestPermissions(this, permission_list, 1);


        } else {
            IntentFilter IntentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
            registerReceiver(receiver, IntentFilter);
            Log.d(tag, "permissions allowed");
        }

    }

    public void sendMessage(final String sendMessage, final String address) {
        final SmsManager man = SmsManager.getDefault();
        Handler hand = new Handler();
        hand.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(tag, "message sent");
                man.sendTextMessage(address, null, sendMessage, null, null);
            }
        }, delay);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }


    public String responsePicker(String[] response) {
        int i = new Random().nextInt(response.length);
        return response[i];
    }

    public boolean messageReader(String[] keywords, String message) {
        for (int i = 0; i < keywords.length; i++) {
            String keyword = keywords[i];
            if (message.toLowerCase().contains(keyword)) {
                return true;
            }
        }
        return false;
    }

}
