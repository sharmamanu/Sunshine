package com.ink.sunshine;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created & Developed by Manu Sharma on 10/19/2016.
 */

public class LocationEditTextPreference extends EditTextPreference {

    static final String LOG_TAG = LocationEditTextPreference.class.getSimpleName();

    static final private int DEFAULT_MINIMUM_LOCATION_LENGTH = 2;
    private int mMinLength;

    public LocationEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.LocationEditTextPreference,
                0, 0);
        try {
            mMinLength = a.getInteger(R.styleable.LocationEditTextPreference_minLength, DEFAULT_MINIMUM_LOCATION_LENGTH);
            Log.d(LOG_TAG, mMinLength + "");
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void showDialog(Bundle state) {
        super.showDialog(state);
        EditText et = getEditText();
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Dialog d = getDialog();
                if (d instanceof AlertDialog) {
                    AlertDialog ad = (AlertDialog) d;
                    Button positiveButton = ad.getButton(AlertDialog.BUTTON_POSITIVE);
                    if (editable.length() < mMinLength) {
                        positiveButton.setEnabled(false);
                    }else {
                        positiveButton.setEnabled(true);
                    }
                }
            }
        });
    }
}
