package com.silatsakti.tokimessenger;

import android.app.Activity;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public Keyboard keyboard;
    public KeyboardView keyboardView;
    public EditText currentMessage;
    public ArrayList<String> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the Keyboard
        keyboard = new Keyboard(this,R.xml.latinKeyBoard);

        // Lookup the KeyboardView
        keyboardView= (KeyboardView)findViewById(R.id.keyboardview);

        // Attach the keyboard to the view
        keyboardView.setKeyboard(keyboard);

        // Do not show the preview balloons
        keyboardView.setPreviewEnabled(false);

        // Install the key handler
        keyboardView.setOnKeyboardActionListener(onKeyboardActionListener);

        words = new ArrayList<>();

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
            if (primaryCode == -5) {
                if ( String.valueOf(currentMessage.getText().toString()).length() == 0) {
                    currentMessage.setText("");
                } else {
                    String str = currentMessage.getText().toString();
                    str = str.substring(0, str.length() - 1);
                    currentMessage.setText(str);
                }

            } else if (primaryCode == 13) {
                currentMessage.append("\n");

            } else if (primaryCode == 32) {

                //String spaceSplit = "";
                currentMessage.append(" ");

            }else {
                char c = (char) primaryCode;
                //Log.i("key pressed: ", String.valueOf(c));
                currentMessage.append(String.valueOf(c));
            }
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
}
