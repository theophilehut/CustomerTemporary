package polito.mad.drawer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private ImageButton cam;
    private EditText et_name;
    private EditText et_email;
    private EditText et_desc;
    private EditText et_phone;
    private EditText et_addr;
    private Bitmap image;
    private Bitmap bitmap;
    private ImageView im;
    private String FILENAME = "profile_picture.png";
    private MenuItem save;
    private MenuItem edit;

    private String username;

    private CustomerData customerData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        cam = findViewById(R.id.button2);
        et_name = findViewById(R.id.editText1);
        et_email = findViewById(R.id.editText2);
        et_desc = findViewById(R.id.editText3);
        et_phone = findViewById(R.id.editText4);
        et_addr = findViewById(R.id.editText5);


        et_name.setEnabled(false);
        et_email.setEnabled(false);
        et_desc.setEnabled(false);
        et_phone.setEnabled(false);
        et_addr.setEnabled(false);

        et_name.setTextColor(Color.BLACK);
        et_email.setTextColor(Color.BLACK);
        et_desc.setTextColor(Color.BLACK);
        et_phone.setTextColor(Color.BLACK);
        et_addr.setTextColor(Color.BLACK);
        cam.setVisibility(View.VISIBLE);

        im = findViewById(R.id.imageView);
        username = getSharedPreferences("pref",MODE_PRIVATE).getString("username","");

        updateDataFromDB(username);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        save =(MenuItem)menu.findItem(R.id.Item02);
        edit =(MenuItem)menu.findItem(R.id.item01);
        save.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item01:
                et_name.setEnabled(true);
                et_email.setEnabled(true);
                et_desc.setEnabled(true);
                et_phone.setEnabled(true);
                et_addr.setEnabled(true);
                edit.setVisible(false);
                save.setVisible(true);
                cam.setClickable(true);
                cam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectImage(MainActivity.this);
                    }
                });
                break;
            case R.id.Item02:
                //this part is for the persistence , however the preference can only save primitive types so , only the string variables
                edit.setVisible(true);
                save.setVisible(false);
                et_name.setEnabled(false);
                et_email.setEnabled(false);
                et_desc.setEnabled(false);
                et_phone.setEnabled(false);
                et_addr.setEnabled(false);
                cam.setClickable(false);


//Update the database
                //Update data fields
                customerData.setName(et_name.getText().toString());
                customerData.setPhone(et_phone.getText().toString());
                customerData.setEmail(et_email.getText().toString());
                customerData.setAdress(et_addr.getText().toString());
                customerData.setDescription(et_desc.getText().toString());
                //Get to the reference and update
                DatabaseReference db = FirebaseDatabase.getInstance().getReference("Customers");
                DataManager.uploadData(db, username, customerData);
                //Add the picture
                StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
                if (bitmap != null){
                    Uri uri = DataManager.getImageUri(this, bitmap);
                    StorageReference storageReference = mStorageRef.child("CustomerPictures/" + username + "/profile_picture.png");
                    storageReference.putFile(uri);
                }

        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {

            Intent profile = new Intent(MainActivity.this,MainActivity.class);
            startActivity(profile);
            finish();

        } else if (id == R.id.nav_restaurant_menu) {

            Intent todayMenu = new Intent(MainActivity.this,TodayMenu.class);
            startActivity(todayMenu);
            finish();

        } else if (id == R.id.nav_reservations) {

            Intent reservations = new Intent(MainActivity.this,Reservations.class);
            startActivity(reservations);
            finish();

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Detects request codes
        if(requestCode==0 && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            //Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //ImageView im=findViewById(R.id.imageView);
            im.setImageBitmap(bitmap);
        }
        if(requestCode==1 && resultCode == Activity.RESULT_OK) {
            Bitmap bitmapp = (Bitmap)data.getExtras().get("data");
            bitmap=bitmapp;
            //ImageView im=findViewById(R.id.imageView);
            im.setImageBitmap(bitmap);
        }
    }

    private void selectImage(Context context) {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 1);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 0);//one can be replaced with any action code

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void updateDataFromDB(String identifier) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Customers");
        Query queryRef = db.orderByChild("username").equalTo(identifier);
        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                customerData = dataSnapshot.getChildren().iterator().next().getValue(CustomerData.class);

                et_name.setText(customerData.getName());
                et_phone.setText(customerData.getPhone());
                et_email.setText(customerData.getEmail());
                et_addr.setText(customerData.getAdress());
                et_desc.setText(customerData.getDescription());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        // Add the image
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference imageRef = storageRef.child("CustomerPictures/" + identifier +"/profile_picture.png");
        Log.d("PROFILE", "image path : " + imageRef.getPath());
        DataManager.loadImage(imageRef, im);

    }
}
