package sg.edu.rp.c346.id22020383.mylocalbanks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ImageButton DBSibtn;
    TextView DBStv;
    ImageButton OCBCibtn;
    TextView OCBCtv;
    ImageButton UOBibtn;
    TextView UOBtv;
    ToggleButton toggleButton;

    String wordClicked = "";
    boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBSibtn = findViewById(R.id.DBSibtn);
        DBStv = findViewById(R.id.DBStv);
        OCBCibtn = findViewById(R.id.OCBCibtn);
        OCBCtv = findViewById(R.id.OCBCtv);
        UOBibtn = findViewById(R.id.UOBibtn);
        UOBtv = findViewById(R.id.UOBtv);
        toggleButton = findViewById(R.id.toggleButton);

        registerForContextMenu(DBSibtn);
        registerForContextMenu(OCBCibtn);
        registerForContextMenu(UOBibtn);

        DBSibtn.setTag(getString(R.string.dbs));
        OCBCibtn.setTag(getString(R.string.ocbc));
        UOBibtn.setTag(getString(R.string.uob));

        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                DBStv.setTextColor(Color.RED);
                DBSibtn.setBackgroundColor(Color.YELLOW);
                DBSibtn.setScaleX(1.5f);
                DBSibtn.setScaleY(1.5f);

            } else {
                DBStv.setTextColor(Color.GRAY);
                DBSibtn.setBackgroundColor(Color.TRANSPARENT);
                DBSibtn.setScaleX(1.0f);
                DBSibtn.setScaleY(1.0f);
            }
        });

        DBSibtn.setOnClickListener(v -> {
            isFavorite = !isFavorite;
            if (isFavorite) {
                DBSibtn.setBackgroundColor(Color.YELLOW);
            } else {
                DBSibtn.setBackgroundColor(Color.TRANSPARENT);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.EnglishSelection) {
            setLocale("en");
            recreate();
            return true;
        } else if (id == R.id.MandarinSelection) {
            setLocale("cn");
            recreate();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setLocale(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration configuration = new Configuration();
        configuration.locale = locale;

        Resources resources = getResources();
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getTag() != null) {
            wordClicked = v.getTag().toString();
        }

        menu.add(0, 0, 0, "Website");
        menu.add(0, 1, 1, "Contact");
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (wordClicked.equalsIgnoreCase(getString(R.string.dbs))) {
            if (item.getItemId() == 0) {
                Intent intentWeb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.dbs.com.sg"));
                startActivity(intentWeb);
                return true;
            } else if (item.getItemId() == 1) {
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "18001111111"));
                startActivity(intentCall);
                return true;
            }
        } else if (wordClicked.equalsIgnoreCase(getString(R.string.ocbc))) {
            if (item.getItemId() == 0) {
                Intent intentWeb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ocbc.com"));
                startActivity(intentWeb);
                return true;
            } else if (item.getItemId() == 1) {
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "18003633333"));
                startActivity(intentCall);
                return true;
            }
        } else if (wordClicked.equalsIgnoreCase(getString(R.string.uob))) {
            if (item.getItemId() == 0) {
                Intent intentWeb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.uob.com.sg"));
                startActivity(intentWeb);
                return true;
            } else if (item.getItemId() == 1) {
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "18002222121"));
                startActivity(intentCall);
                return true;
            }
        }

        return super.onContextItemSelected(item);
    }
}
