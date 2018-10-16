package com.vainius.augustinas.androidvisma;

import android.view.View;

import org.json.JSONException;

public interface ItemClickListener {
    void onClick(View view, int position) throws JSONException;
}
