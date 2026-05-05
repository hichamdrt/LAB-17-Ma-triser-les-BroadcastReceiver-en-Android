package com.example.receiverdemo;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private AirplaneModeReceiver airplaneReceiver;
    private boolean isReceiverRegistered = false;

    private Button btnToggleAirplane, btnSendCustom;
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        airplaneReceiver = new AirplaneModeReceiver();

        tvStatus = findViewById(R.id.tvStatus);
        btnToggleAirplane = findViewById(R.id.btnToggleAirplane);
        btnSendCustom = findViewById(R.id.btnSendCustom);

        btnToggleAirplane.setOnClickListener(v -> toggleAirplaneReceiver());
        btnSendCustom.setOnClickListener(v -> sendCustomBroadcast());
    }

    // ✅ Gestion du Receiver Dynamique
    private void toggleAirplaneReceiver() {

        if (!isReceiverRegistered) {

            // ✅ Création du filtre pour écouter le mode avion
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);

            // ✅ Enregistrement dynamique
            registerReceiver(airplaneReceiver, filter);

            isReceiverRegistered = true;

            tvStatus.setText("Receiver Mode Avion : ACTIVÉ (dynamique)");
            btnToggleAirplane.setText("Désactiver Receiver Avion");

        } else {

            unregisterReceiver(airplaneReceiver);

            isReceiverRegistered = false;

            tvStatus.setText("Receiver Mode Avion : DÉSACTIVÉ");
            btnToggleAirplane.setText("Activer Receiver Avion");
        }
    }

    // ✅ Envoi d’un Custom Broadcast
    private void sendCustomBroadcast() {

        Intent intent = new Intent("com.example.receiverdemo.CUSTOM_EVENT");
        intent.putExtra("message", "Bonjour depuis le custom broadcast !");

        sendBroadcast(intent);

        Toast.makeText(this,
                "Custom Broadcast envoyé !",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {

        if (isReceiverRegistered) {
            unregisterReceiver(airplaneReceiver);
        }

        super.onDestroy();
    }
}