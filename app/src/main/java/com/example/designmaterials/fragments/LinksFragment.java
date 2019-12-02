package com.example.designmaterials.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.designmaterials.R;

import org.json.JSONObject;

import java.util.HashMap;

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
        view.findViewById(R.id.tweeterButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://twitter.com/intent/tweet?text="+getString(R.string.share_text)+"&url="+getString(R.string.share_url);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        view.findViewById(R.id.facebookButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.facebook.com/sharer/sharer.php?u="+getString(R.string.share_url)+"&m="+getString(R.string.share_text);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        view.findViewById(R.id.emailButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                boolean pixelChoice = sharedPrefs.getBoolean("pixelChoice", false);
                boolean rgbChoice = sharedPrefs.getBoolean("rgbChoice", false);
                boolean jsonValue = sharedPrefs.getBoolean("jsonValue", false);

                int thePrimaryColor = sharedPreferences.getInt("primaryColor", 123);
                int thePrimaryColorDark = sharedPreferences.getInt("primaryColorDark", 123);
                int thePrimaryColorLight = sharedPreferences.getInt("primaryColorLight", 123);
                int theSecondaryColor = sharedPreferences.getInt("secondaryColor", 123);
                int theSecondaryColorDark = sharedPreferences.getInt("secondaryColorDark", 123);
                int theSecondaryColorLight = sharedPreferences.getInt("secondaryColorLight", 123);

                String headingFontName = sharedPreferences.getString("headingFontName", "opensans");
                String bodyFontName = sharedPreferences.getString("bodyFontName", "opensans");
                String buttonFontName = sharedPreferences.getString("buttonFontName", "opensans");

                String headingFontWeight = sharedPreferences.getString("headingFontWeight", "");
                String bodyFontWeight = sharedPreferences.getString("bodyFontWeight", "");
                String buttonFontWeight = sharedPreferences.getString("buttonFontWeight", "");

                int headingsFontsize = sharedPreferences.getInt("headingFontSize", 22);
                int bodyFontsize = sharedPreferences.getInt("bodyFontSize", 22);
                int buttonFontsize = sharedPreferences.getInt("buttonFontSize", 22);

                String headingFontSizeStr = "";
                String bodyFontSizeStr = "";
                String buttonFontSizeStr = "";
                String headingFontWeightStr = "";
                String bodyFontWeightStr = "";
                String buttonFontWeightStr = "";
                String primaryColorStr = "";
                String primaryColorLightStr = "";
                String primaryColorDarkStr = "";
                String secondaryColorStr = "";
                String secondaryColorLightStr = "";
                String secondaryColorDarkStr = "";
                String message="";

                switch (headingFontWeight) {
                    case "":
                        headingFontWeightStr = "regular";
                        break;
                    case "b":
                        headingFontWeightStr = "bold";
                        break;
                    case "l":
                        headingFontWeightStr = "light";
                        break;
                }
                switch (bodyFontWeight) {
                    case "":
                        bodyFontWeightStr = "regular";
                        break;
                    case "b":
                        bodyFontWeightStr = "bold";
                        break;
                    case "l":
                        bodyFontWeightStr = "light";
                        break;
                }
                switch (buttonFontWeight) {
                    case "":
                        buttonFontWeightStr = "regular";
                        break;
                    case "b":
                        buttonFontWeightStr = "bold";
                        break;
                    case "l":
                        buttonFontWeightStr = "light";
                        break;
                }



                if (pixelChoice) {
                    Resources r = getResources();
                    headingFontSizeStr = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, headingsFontsize, r.getDisplayMetrics()) + "px";
                    bodyFontSizeStr = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, bodyFontsize, r.getDisplayMetrics()) + "px";
                    buttonFontSizeStr = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, buttonFontsize, r.getDisplayMetrics()) + "px";
                } else {
                    headingFontSizeStr = headingsFontsize + "dp";
                    bodyFontSizeStr = bodyFontsize + "dp";
                    buttonFontSizeStr = buttonFontsize + "dp";
                }
                if (rgbChoice) {
                    primaryColorStr = "(R:" + Color.red(thePrimaryColor) + ", G:" + Color.green(thePrimaryColor) + ", B:" + Color.blue(thePrimaryColor) + ")";
                    primaryColorLightStr = "(R:" + Color.red(thePrimaryColorLight) + ", G:" + Color.green(thePrimaryColorLight) + ", B:" + Color.blue(thePrimaryColorLight) + ")";
                    primaryColorDarkStr = "(R:" + Color.red(thePrimaryColorDark) + ", G:" + Color.green(thePrimaryColorDark) + ", B:" + Color.blue(thePrimaryColorDark) + ")";
                    secondaryColorStr = "(R:" + Color.red(theSecondaryColor) + ", G:" + Color.green(theSecondaryColor) + ", B:" + Color.blue(theSecondaryColor) + ")";
                    secondaryColorLightStr = "(R:" + Color.red(theSecondaryColorLight) + ", G:" + Color.green(theSecondaryColorLight) + ", B:" + Color.blue(theSecondaryColorLight) + ")";
                    secondaryColorDarkStr = "(R:" + Color.red(theSecondaryColorDark) + ", G:" + Color.green(theSecondaryColorDark) + ", B:" + Color.blue(theSecondaryColorDark) + ")";
                } else {
                    primaryColorStr = String.format("#%06X", (0xFFFFFF & thePrimaryColor));
                    primaryColorLightStr = String.format("#%06X", (0xFFFFFF & thePrimaryColorLight));
                    primaryColorDarkStr = String.format("#%06X", (0xFFFFFF & thePrimaryColorDark));
                    secondaryColorStr = String.format("#%06X", (0xFFFFFF & theSecondaryColor));
                    secondaryColorLightStr = String.format("#%06X", (0xFFFFFF & theSecondaryColorLight));
                    secondaryColorDarkStr = String.format("#%06X", (0xFFFFFF & theSecondaryColorDark));
                }
                if(jsonValue){
                    HashMap<String,String> allStyle=new HashMap<>();
                    allStyle.put("PrimaryColor",primaryColorStr);
                    allStyle.put("PrimaryColorLight",primaryColorLightStr);
                    allStyle.put("PrimaryColorDark",primaryColorDarkStr);
                    allStyle.put("SecondaryColor",secondaryColorStr);
                    allStyle.put("SecondaryColorDark",secondaryColorDarkStr);
                    allStyle.put("SecondaryColorLight",secondaryColorLightStr);
                    allStyle.put("HeadingFontName",headingFontName);
                    allStyle.put("BodyFontName",bodyFontName);
                    allStyle.put("ButtonFontName",buttonFontName);
                    allStyle.put("HeadingFontWeight",headingFontWeightStr);
                    allStyle.put("BodyFontWeight",bodyFontWeightStr);
                    allStyle.put("ButtonFontWeight",buttonFontWeightStr);
                    allStyle.put("HeadingFontSize",headingFontSizeStr);
                    allStyle.put("BodyFontSize",bodyFontSizeStr);
                    allStyle.put("ButtonFontSize",buttonFontSizeStr);

                    JSONObject jsonObject=new JSONObject(allStyle);

                    message = getString(R.string.emailIntentMessageStart) +getString(R.string.emailIntentMessageJson)+"\n\n"+
                            jsonObject+"\n\n"+
                            getString(R.string.emailIntentMessageEnd) + "\n from " + getString(R.string.nav_header_subtitle);

                }else{
                     message = getString(R.string.emailIntentMessageStart) +
                            getString(R.string.emailIntentPrimaryColor) +" "+ primaryColorStr +
                            getString(R.string.emailIntentPrimaryColorLight) + " " + primaryColorLightStr +
                            getString(R.string.emailIntentPrimaryColorDark) + " " + primaryColorDarkStr +
                            "\n" + getString(R.string.emailIntentSecondaryColor) + " " + secondaryColorStr +
                            getString(R.string.emailIntentSecondaryColorLight) + " " + secondaryColorLightStr +
                            getString(R.string.emailIntentSecondaryColorDark) + secondaryColorDarkStr + " " + "\n" +
                            getString(R.string.emailIntentHeadingFontName) + " " + headingFontName +
                            getString(R.string.emailIntentBodyFontName) + " " + bodyFontName +
                            getString(R.string.emailIntentButtonFontName) + " " + buttonFontName + "\n" +
                            getString(R.string.emailIntentHeadingFontWeight) + " " + headingFontWeightStr +
                            getString(R.string.emailIntentButtonFontName) + " " + buttonFontWeightStr +
                            getString(R.string.emailIntentBodyFontName) + " " + bodyFontWeightStr + "\n" +
                            getString(R.string.emailIntentHeadingFontSize) + " " + headingFontSizeStr +
                            getString(R.string.emailIntentBodyFontSize) + " " + bodyFontSizeStr +
                            getString(R.string.emailIntentButtonFontSize) + " " + buttonFontSizeStr +
                            getString(R.string.emailIntentMessageEnd) + "\n from " + getString(R.string.nav_header_subtitle);

                }

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