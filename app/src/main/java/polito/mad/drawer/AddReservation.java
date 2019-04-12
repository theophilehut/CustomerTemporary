package polito.mad.drawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class AddReservation extends AppCompatActivity {

    private EditText res_name;
    private EditText res_time;
    private EditText res_notes;
    private EditText res_phone;
    private EditText res_dishes;
    private MenuItem save;

    ReservationData rd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reservation);

        res_name = findViewById(R.id.etResName);
        res_time = findViewById(R.id.etResTime);
        res_phone = findViewById(R.id.etResPhone);
        res_dishes = findViewById(R.id.etResDishes);
        res_notes = findViewById(R.id.etResNotes);

        res_name.setEnabled(true);
        res_time.setEnabled(true);
        res_phone.setEnabled(true);
        res_dishes.setEnabled(true);
        res_notes.setEnabled(true);

        res_name.setHint("Name");
        res_time.setHint("Time");
        res_phone.setHint("Phone number");
        res_dishes.setHint("Dishes");
        res_notes.setHint("Notes");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_reservation, menu);
        save =(MenuItem)menu.findItem(R.id.item01);
        save.setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item01:
                Intent intent = new Intent();
                intent.putExtra("name", res_name.getText().toString());
                intent.putExtra("phone", res_phone.getText().toString());
                intent.putExtra("time", res_time.getText().toString());
                intent.putExtra("dishes", res_dishes.getText().toString());
                intent.putExtra("notes", res_notes.getText().toString());

                setResult(RESULT_OK, intent);
                finish();
                break;

        }


        return super.onOptionsItemSelected(item);
    }
}

