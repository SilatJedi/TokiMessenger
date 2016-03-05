package com.silatsakti.tokimessenger;

import android.app.Activity;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    public Keyboard keyboard;
    public KeyboardView keyboardView;
    public EditText currentMessage;
    public String[] tokiWords;
    public String[] englishWords;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tokiWords = getResources().getStringArray(R.array.tokiWordList);
        englishWords = getResources().getStringArray(R.array.englishWordList);

        //Create Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the Keyboard
        keyboard = new Keyboard(this,R.xml.latin_key_board);

        // Create the KeyboardView
        keyboardView= (KeyboardView)findViewById(R.id.keyboardview);

        // Attach the keyboard to the view
        keyboardView.setKeyboard(keyboard);

        // Do not show the preview balloons
        keyboardView.setPreviewEnabled(false);

        // Install the key handler
        keyboardView.setOnKeyboardActionListener(onKeyboardActionListener);

        currentMessage = (EditText)findViewById(R.id.currentMessageEditText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openKeyboard(View v)
    {
        keyboardView.setVisibility(View.VISIBLE);
        keyboardView.setEnabled(true);
        if( v!=null)((InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    private KeyboardView.OnKeyboardActionListener onKeyboardActionListener = new KeyboardView.OnKeyboardActionListener() {
        @Override public void onKey(int primaryCode, int[] keyCodes)
        {
            //backspace key
            if (primaryCode == -5) {
                if ( String.valueOf(currentMessage.getText().toString()).length() == 0) {
                    currentMessage.setText("");
                } else {
                    String str = currentMessage.getText().toString();
                    str = str.substring(0, str.length() - 1);
                    currentMessage.setText(str);
                    currentMessage.setSelection(str.length());
                }
            // enter key
            } else if (primaryCode == 13) {
                currentMessage.append("\n");

            //spacebar
            } else if (primaryCode == 32) {

                //String spaceSplit = "";
                currentMessage.append(" ");

            //add character to currentMessage
            }else {
                char c = (char) primaryCode;
                //Log.i("key pressed: ", String.valueOf(c));
                currentMessage.append(String.valueOf(c));
            }

            spellCheck();
        }

        @Override public void onPress(int arg0) {
        }

        @Override public void onRelease(int primaryCode) {
        }

        @Override public void onText(CharSequence text) {
        }

        @Override public void swipeDown() {
        }

        @Override public void swipeLeft() {
        }

        @Override public void swipeRight() {
        }

        @Override public void swipeUp() {
        }
    };

    public void spellCheck() {
        ArrayList<String> badWords = new ArrayList<>();
        String message = currentMessage.getText().toString();
        ArrayList<String> wordsToCheck = new ArrayList<>(Arrays.asList(message.split(" ")));

        for (int k = 0; k < wordsToCheck.size(); k++){
            for (int i = 0; i < tokiWords.length; i++) {
                if (wordsToCheck.get(k).equals(tokiWords[i])) {

                }
            }
        }
    }
}
