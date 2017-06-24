package com.example.rachid.smscatcher.views;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rachid.smscatcher.R;
import com.example.rachid.smscatcher.builders.SmsCatcherBuilder;
import com.example.rachid.smscatcher.controllers.SmsModelObserver;
import com.example.rachid.smscatcher.models.ParticipationDto;
import com.example.rachid.smscatcher.models.SmsModel;

import java.util.LinkedList;
import java.util.List;

public class ParticipationActivity extends AppCompatActivity implements SmsModelObserver {

    private final int REQUEST_PERMISSION = 0;
    private final String[] PERMISSIONS = {"android.permission.READ_SMS", "android.permission.RECEIVE_SMS"};
    private ListView mListView;
    private List<ParticipationDto> participationDtos;
    private SmsCatcherBuilder smsCatcherBuilder;
    private SmsModel smsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listView);
        smsCatcherBuilder = (SmsCatcherBuilder) this.getApplication();
        smsModel = smsCatcherBuilder.getSmsModel();
        smsModel.ajouterSmsModelChangeListener(this);
        participationDtos = new LinkedList<ParticipationDto>();
        participationDtos.addAll(smsModel.getDixDerniers());
        if (!checkPermissions()) {
            Button btnPerm = (Button) findViewById(R.id.btnRequestPerm);
            btnPerm.setVisibility(View.VISIBLE);
            return;
        }
        afficherListeInscriptions();
    }

    private boolean checkPermissions() {
        TextView textView = (TextView) findViewById(R.id.info);
        for(String permission: PERMISSIONS) {
            int resPerm = this.checkCallingOrSelfPermission(permission);
            if (resPerm != PackageManager.PERMISSION_GRANTED) {
                Log.i("Permissions", "Denied");
                textView.setText(R.string.permissions_denied);
                return false;
            }

        }
        Log.i("Permissions", "Granted");
        textView.setText(R.string.msg_info);
        return true;
    }
    private void showPermission(String permission) {
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, permission);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    permission)) {
                Log.i("Show","Troll");
            } else {
                requestPermission(permission, REQUEST_PERMISSION);
            }
        } else {
            Toast.makeText(this, "Permission (already) Granted!", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String permissions[],
            int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show();
                    Button btnPerm = (Button) findViewById(R.id.btnRequestPerm);
                    btnPerm.setVisibility(View.GONE);
                    checkPermissions();
                    afficherListeInscriptions();
                } else {
                    Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
        }
    }
    private void requestPermission(String permissionName, int permissionRequestCode) {
        ActivityCompat.requestPermissions(this,
                new String[]{permissionName}, permissionRequestCode);
    }

    public void requestPermission(View view){
        for(String permission: PERMISSIONS){
            showPermission(permission);
        }
    }
    @Override
    public void onSmsReceived() {
        if (participationDtos.size() >= 10) {
            participationDtos.remove(0);
        }
        participationDtos.add(smsModel.getDernier());
        afficherListeInscriptions();
    }

    private void afficherListeInscriptions() {
        ParticipationListViewAdapter adapter = new ParticipationListViewAdapter(ParticipationActivity.this, participationDtos);
        mListView.setAdapter(adapter);
    }
}
