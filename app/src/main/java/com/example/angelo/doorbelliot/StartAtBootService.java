package com.example.angelo.doorbelliot;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttService;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import static java.security.AccessController.getContext;

public class StartAtBootService extends MqttService {

    protected Handler handler;
    protected Toast mToast;
    NewConnectionFragment.PassValues pass;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void disconnect(String clientHandle, String invocationContext, String activityToken) {
        super.disconnect(clientHandle, invocationContext, activityToken);
    }

    //publish da usare per le query
    @Override
    public IMqttDeliveryToken publish(String clientHandle, String topic, byte[] payload, int qos, boolean retained, String invocationContext, String activityToken) throws MqttPersistenceException, MqttException {
        return super.publish(clientHandle, topic, payload, qos, retained, invocationContext, activityToken);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // codice da eseguire quando il service viene creato
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
// qui ho provato a fare il pass.passage per settare la connessione, senza successo
            }
        });
        return android.app.Service.START_STICKY;

    }
    
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        // codice da eseguire quando il service viene fatto partire
    }




    /*
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("EXIT", "ondestroy!");
       Intent broadcastIntent = new Intent(this, MainActivity.class);
        sendBroadcast(broadcastIntent);

    }
     */
}