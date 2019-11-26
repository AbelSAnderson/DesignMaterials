package com.example.designmaterials.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.designmaterials.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LinksFragment extends Fragment {

    public static final int PERMISSION_TEXT = 1;
    public static final int PERMISSION_CALL_PHONE = 2;

    public LinksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_links, container, false);

        view.findViewById(R.id.textButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.SEND_SMS)) {
                        final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).setTitle(getString(R.string.smsPermissionTitle)).setMessage(getString(R.string.smsPermissionMessage)).create();
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getString(R.string.permissionAlertDialogButtonText), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                alertDialog.dismiss();
                                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.SEND_SMS}, PERMISSION_TEXT);
                            }
                        });
                        alertDialog.show();
                    } else {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.SEND_SMS}, PERMISSION_TEXT);
                    }
                } else {
                    Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto: 0000000000"));
                    intent.putExtra("sms_body", getString(R.string.smsIntentMessage));
                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        Toast.makeText(getActivity(), getString(R.string.errorNoSoftware), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        view.findViewById(R.id.phoneButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CALL_PHONE)) {
                        final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).setTitle(getString(R.string.phonePermissionTitle)).setMessage(getString(R.string.phonePermissionMessage)).create();
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getString(R.string.permissionAlertDialogButtonText), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                alertDialog.dismiss();
                                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_CALL_PHONE);
                            }
                        });
                        alertDialog.show();
                    } else {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_CALL_PHONE);
                    }
                } else {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: 0000000000"));
                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        Toast.makeText(getActivity(), getString(R.string.errorNoSoftware), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        view.findViewById(R.id.emailButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);

                String message = getString(R.string.emailIntentMessageStart) +
                        getString(R.string.emailIntentPrimaryColor) + String.format("#%06X", (0xFFFFFF & sharedPreferences.getInt("primaryColor", 123))) +
                        getString(R.string.emailIntentPrimaryColorLight) + String.format("#%06X", (0xFFFFFF & sharedPreferences.getInt("primaryColorLight", 123))) +
                        getString(R.string.emailIntentPrimaryColorDark) + String.format("#%06X", (0xFFFFFF & sharedPreferences.getInt("primaryColorDark", 123))) +
                        getString(R.string.emailIntentSecondaryColor) + String.format("#%06X", (0xFFFFFF & sharedPreferences.getInt("secondaryColor", 123))) +
                        getString(R.string.emailIntentSecondaryColorLight) + String.format("#%06X", (0xFFFFFF & sharedPreferences.getInt("secondaryColorLight", 123))) +
                        getString(R.string.emailIntentSecondaryColorDark) + String.format("#%06X", (0xFFFFFF & sharedPreferences.getInt("secondaryColorDark", 123))) +
                        getString(R.string.emailIntentMessageEnd);

                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.emailIntentSubject));
                intent.putExtra(Intent.EXTRA_TEXT, message);
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), getString(R.string.errorNoSoftware), Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.findViewById(R.id.mapButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=37.4088695,-122.0773546(Google Headquarters)"));
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), getString(R.string.errorNoSoftware), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}