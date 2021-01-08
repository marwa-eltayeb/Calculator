package com.marwaeltayeb.calculator;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import java.util.Locale;

public class SoundUtils {

    static TextToSpeech tts = null;

    public static void playSound(String text, Context context) {
        // Initialize the TextToSpeech Variable.
        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                // TTS is successfully initialized
                if (status == TextToSpeech.SUCCESS) {
                    // Set speech language
                    int result = tts.setLanguage(Locale.US);
                    // If your device does not support language you set above
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(context, "Language not supported", Toast.LENGTH_SHORT).show();
                        // Otherwise the language is supported.
                    } else {
                        // If there is no text, speak "no text".
                        if (text == null || "".equals(text)) {
                            tts.speak("no Text", TextToSpeech.QUEUE_FLUSH, null);
                        } else {
                            // Otherwise there ia a text, speak it.
                            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                        }
                    }
                }
            }
        });
    }
}
