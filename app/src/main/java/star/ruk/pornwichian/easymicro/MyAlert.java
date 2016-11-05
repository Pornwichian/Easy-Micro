package star.ruk.pornwichian.easymicro;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Pornwichian on 11/5/2016.
 */

public class MyAlert {

    //Explicit
    private Context context;
    private int anInt;
    private String titleString, massageString;

    public MyAlert(Context context, int anInt, String titleString, String massageString) {
        this.context = context;
        this.anInt = anInt;
        this.titleString = titleString;
        this.massageString = massageString;
    }

    public void MyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setCancelable(false);
        builder.setIcon(anInt);
        builder.setTitle(titleString);
        builder.setMessage(massageString);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });

        builder.show();


    }// MyDialog



}//main Class
