package polito.mad.drawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class AddTodayMenu extends AppCompatActivity {

    private EditText tm_name;
    private EditText tm_price;
    private EditText tm_description;
    private EditText tm_notes;
    private MenuItem save;

    TodayMenuData tmd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_today_menu);

        tm_name = findViewById(R.id.etTMName);
        tm_price = findViewById(R.id.etTMPrice);
        tm_description = findViewById(R.id.etTMDescription);
        tm_notes = findViewById(R.id.etTMNotes);

        tm_name.setEnabled(true);
        tm_price.setEnabled(true);
        tm_description.setEnabled(true);
        tm_notes.setEnabled(true);

        tm_name.setHint("Food Name");
        tm_price.setHint("Price");
        tm_description.setHint("Short Description");
        tm_notes.setHint("Aditional Notes");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_today_menu, menu);
        save =(MenuItem)menu.findItem(R.id.item01);
        save.setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item01:
                Intent intent = new Intent();
                intent.putExtra("dishes", tm_name.getText().toString());
                intent.putExtra("price", tm_price.getText().toString());
                intent.putExtra("description", tm_description.getText().toString());
                intent.putExtra("notes", tm_notes.getText().toString());


                setResult(RESULT_OK, intent);
                finish();
                break;

        }


        return super.onOptionsItemSelected(item);
    }
}

