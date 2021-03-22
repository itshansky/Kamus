package com.farhanrinsky.kamus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AudioActivity extends Fragment {
    @BindView(R.id.editText_audio)
    EditText editTextAudio;
    @BindView(R.id.btn_audio)
    Button buttonSpeak;
    @BindView(R.id.spinner)
    Spinner spinner;
    String bahasa;
    TextToSpeech TTS;

    public AudioActivity() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(getResources().getString(R.string.app_name)+"/"+getResources().getString(R.string.audio));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_audio, container, false);
        ButterKnife.bind(this, v);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bahasa = spinner.getSelectedItem().toString();
                if (bahasa.equals("Indonesia")) {
                    TTS.setLanguage(new Locale("in", "ID"));
                }
                if (bahasa.equals("Inggris")) {
                    TTS.setLanguage(Locale.US);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        TTS = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    // Empty
                } else {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = editTextAudio.getText().toString().trim();
                if (toSpeak.equals("")) {
                    Toast.makeText(getActivity(), "Masukkan kalimat", Toast.LENGTH_SHORT).show();
                } else {
                    TTS.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                    Toast.makeText(getActivity(), toSpeak, Toast.LENGTH_LONG).show();

                }
            }
        });
        return v;
    }

    @Override
    public void onPause() {
        if (TTS != null && TTS.isSpeaking()) {
            TTS.stop();
        }
        super.onPause();
    }
}