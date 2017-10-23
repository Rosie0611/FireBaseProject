package rosie.example.com.secondrosieproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import rosie.example.com.secondrosieproject.Activity.Activity_WritePost;

/**
 * Created by skyki on 2017-08-21.
 */

public class Dialog extends DialogFragment {

    public static final int CLICKED_DELETE = 1001;

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Do you Want to delete this post?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        Activity_WritePost context = new Activity_WritePost();
                        context.startActivityForResult(intent, CLICKED_DELETE);


                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return super.onCreateDialog(savedInstanceState);
    }
}
