package com.example.angelo.doorbelliot;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedOutputStream;

/**
 * Created by angelo on 08/05/17.
 */

public class NewConnectionFragment extends android.support.v4.app.Fragment {
    private EditText client, server, port, topic;
    private Button conn;
    String clientName,serverName, topicName,ser;     //ho aggiunto la stringa ser per il nome del server
    int portName;
    PassValues pass;

    SharedPreferences sp;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view= inflater.inflate(R.layout.new_connection, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        client=(EditText)view.findViewById(R.id.clientId);
        server=(EditText)view.findViewById(R.id.serverURI);
        port=(EditText)view.findViewById(R.id.port);
        topic=(EditText)view.findViewById(R.id.sub);
        conn=(Button)view.findViewById(R.id.newConnection);

        getSharedParameters();    //prendo i parametri condivisi

        String prt= sp.getString("port","");
        Toast.makeText(getContext(),String.valueOf(port),Toast.LENGTH_LONG).show();
        serverName="tcp://"+ ( sp.getString("server","") )+":"+prt;
        pass.passage(sp.getString("client",""),serverName,(sp.getString("topic","")) );




        conn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(parseValori(client.getText().toString(),server.getText().toString(),port.getText().toString(),topic.getText().toString())){
                    clientName=client.getText().toString();
                    serverName=server.getText().toString();
                    ser=serverName;
                    portName=Integer.parseInt(port.getText().toString());
                    topicName=topic.getText().toString();
                    serverName="tcp://"+serverName+":"+portName;

                    pass.passage(clientName,serverName,topicName);

                    setSharedParameters();  //setto i parametri condivisi

                }else Toast.makeText(getContext(),"inserire campi mancanti",Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean parseValori(String client, String server, String port, String topic) {
        boolean ok=false;
        if(client.equals("") || server.equals("")  || port.equals("") || topic.equals("")){
        }else ok=true;
        return ok;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(getContext());
        pass=(PassValues)context;
    }

    public interface PassValues{
        public void passage (String client, String server,String topic);
    }


    public void getSharedParameters()
    {
        sp= PreferenceManager.getDefaultSharedPreferences(getActivity());
        String st1= sp.getString("client","");
        client.setText(st1);
        String st2= sp.getString("server","");
        server.setText(st2);
        String st3= sp.getString("port","");
        port.setText(st3);
        String st4= sp.getString("topic","");
        topic.setText(st4);
    }

    public void setSharedParameters()
    {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("client",clientName);
        editor.putString("server",ser);
        editor.putString("port",port.getText().toString());    //avevo problemi con il getInt: essendoci nell'OnClick il metodo che parsa la porta non c'è problema
        editor.putString("topic",topicName);
        editor.commit();

    }

}
