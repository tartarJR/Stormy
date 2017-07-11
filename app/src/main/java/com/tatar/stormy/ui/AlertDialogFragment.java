package com.tatar.stormy.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;

import com.tatar.stormy.R;

/**
 * Created by musta on 7/7/2017.
 */

public class AlertDialogFragment extends DialogFragment {

    private String message;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.error_dialog_title)
                .setMessage(R.string.error_dialog_message)
                .setPositiveButton(R.string.error_dialog_pos_button_text, null);

        AlertDialog alertDialog = alertDialogBuilder.create();

        return alertDialog;
    }
}
