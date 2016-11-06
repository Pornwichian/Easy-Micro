package star.ruk.pornwichian.easymicro;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SignUpActivity extends AppCompatActivity {

    //Explicit
    private EditText nameEditText, userEditText , passwordEditText;
    private ImageView imageView;
    private Button button;
    private String nameString,userString, passwordString,imageString,
    imagePathString,imageNameString;


    private Uri uri;
    private boolean aBoolean = true;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Bind widget

        nameEditText = (EditText) findViewById(R.id.editText);
        userEditText = (EditText) findViewById(R.id.editText3);
        passwordEditText = (EditText) findViewById(R.id.editText4);
        imageView = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.button3);



        //signUp controller
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get value frm edit text
                nameString = nameEditText.getText().toString().trim();
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

                //Check Space
                if (nameString .equals("")||
                        userString.equals("")||
                        passwordString.equals("") ) {

                    //Have Space
                    Log.d("5NovV1", "Have Space");
                    MyAlert myAlert = new MyAlert(SignUpActivity.this,
                            R.drawable.rat48, getResources().getString(R.string.title_haveSpace),
                            getResources().getString(R.string.massage_haveSpace));
                    myAlert.MyDialog();

                } else if (aBoolean) {
                    //None choose image
                    MyAlert myAlert = new MyAlert(SignUpActivity.this,R.drawable.rat48,
                            getResources().getString(R.string.title_imageChoose),
                            getResources().getString(R.string.message_imageChoose));
                    myAlert.MyDialog();
                } else {
                    //Upload to server


                }

            }//Onclick
        });
            //Image Controller
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent to other app
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"`โปรดเลือกโปรแกรมดู เพื่อเลือกรูป"),0);


            }//OnClick
        });


    }//Main method

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if((requestCode==0)&&(resultCode==RESULT_OK)){
            //Result
            Log.d("5NovV1", "Result OK");
            uri = data.getData();

            //Setup choose image
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver()
                        .openInputStream(uri));
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //Check choosed complete

            aBoolean = false;

            //Find path of image choose
            imagePathString = myFindPath(uri);
            Log.d("6NovV1", "Path==>" + imagePathString);

            //Find name of Image

            imageNameString = imagePathString.substring(imagePathString.lastIndexOf("/"));
            Log.d("6NovV1", "Name==>" + imageNameString);
        }//if



    }//On Activity

    private String myFindPath(Uri uri) {
        String result = null;
        String[] strings = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, strings, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            result = cursor.getString(index);
        }else {
            result = uri.getPath();
        }

        return result;

    }
}// Main Class
