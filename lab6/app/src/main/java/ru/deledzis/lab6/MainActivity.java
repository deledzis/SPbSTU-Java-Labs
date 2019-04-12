package ru.deledzis.lab6;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import static ru.deledzis.lab6.App.getAppContext;

public class MainActivity
        extends AppCompatActivity
        implements DrawingView.OnDrawingViewInteractionListener {

    private static final String TAG = "MainActivity";

    private Toolbar mToolbar;
    private DrawingView mDrawingView;

    public static final List<Integer> mColorsList = new ArrayList<Integer>() {{
        add(getColorRes(R.color.md_amber_50));
        add(getColorRes(R.color.md_amber_100));
        add(getColorRes(R.color.md_amber_200));
        add(getColorRes(R.color.md_amber_300));
        add(getColorRes(R.color.md_amber_400));
        add(getColorRes(R.color.md_amber_500));
        add(getColorRes(R.color.md_amber_600));
        add(getColorRes(R.color.md_amber_700));
        add(getColorRes(R.color.md_amber_800));
        add(getColorRes(R.color.md_amber_900));
        add(getColorRes(R.color.md_amber_A100));
        add(getColorRes(R.color.md_amber_A200));
        add(getColorRes(R.color.md_amber_A400));
        add(getColorRes(R.color.md_amber_A700));
        add(getColorRes(R.color.md_blue_50));
        add(getColorRes(R.color.md_blue_100));
        add(getColorRes(R.color.md_blue_200));
        add(getColorRes(R.color.md_blue_300));
        add(getColorRes(R.color.md_blue_400));
        add(getColorRes(R.color.md_blue_500));
        add(getColorRes(R.color.md_blue_600));
        add(getColorRes(R.color.md_blue_700));
        add(getColorRes(R.color.md_blue_800));
        add(getColorRes(R.color.md_blue_900));
        add(getColorRes(R.color.md_blue_A100));
        add(getColorRes(R.color.md_blue_A200));
        add(getColorRes(R.color.md_blue_A400));
        add(getColorRes(R.color.md_blue_A700));
        add(getColorRes(R.color.md_brown_50));
        add(getColorRes(R.color.md_brown_100));
        add(getColorRes(R.color.md_brown_200));
        add(getColorRes(R.color.md_brown_300));
        add(getColorRes(R.color.md_brown_400));
        add(getColorRes(R.color.md_brown_500));
        add(getColorRes(R.color.md_brown_600));
        add(getColorRes(R.color.md_brown_700));
        add(getColorRes(R.color.md_brown_800));
        add(getColorRes(R.color.md_brown_900));
        add(getColorRes(R.color.md_red_50));
        add(getColorRes(R.color.md_red_100));
        add(getColorRes(R.color.md_red_200));
        add(getColorRes(R.color.md_red_300));
        add(getColorRes(R.color.md_red_400));
        add(getColorRes(R.color.md_red_500));
        add(getColorRes(R.color.md_red_600));
        add(getColorRes(R.color.md_red_700));
        add(getColorRes(R.color.md_red_800));
        add(getColorRes(R.color.md_red_900));
        add(getColorRes(R.color.md_red_A100));
        add(getColorRes(R.color.md_red_A200));
        add(getColorRes(R.color.md_red_A400));
        add(getColorRes(R.color.md_red_A700));
        add(getColorRes(R.color.md_green_50));
        add(getColorRes(R.color.md_green_100));
        add(getColorRes(R.color.md_green_200));
        add(getColorRes(R.color.md_green_300));
        add(getColorRes(R.color.md_green_400));
        add(getColorRes(R.color.md_green_500));
        add(getColorRes(R.color.md_green_600));
        add(getColorRes(R.color.md_green_700));
        add(getColorRes(R.color.md_green_800));
        add(getColorRes(R.color.md_green_900));
        add(getColorRes(R.color.md_green_A100));
        add(getColorRes(R.color.md_green_A200));
        add(getColorRes(R.color.md_green_A400));
        add(getColorRes(R.color.md_green_A700));
        add(getColorRes(R.color.md_blue_grey_50));
        add(getColorRes(R.color.md_blue_grey_100));
        add(getColorRes(R.color.md_blue_grey_200));
        add(getColorRes(R.color.md_blue_grey_300));
        add(getColorRes(R.color.md_blue_grey_400));
        add(getColorRes(R.color.md_blue_grey_500));
        add(getColorRes(R.color.md_blue_grey_600));
        add(getColorRes(R.color.md_blue_grey_700));
        add(getColorRes(R.color.md_blue_grey_800));
        add(getColorRes(R.color.md_blue_grey_900));
        add(getColorRes(R.color.md_purple_50));
        add(getColorRes(R.color.md_purple_100));
        add(getColorRes(R.color.md_purple_200));
        add(getColorRes(R.color.md_purple_300));
        add(getColorRes(R.color.md_purple_400));
        add(getColorRes(R.color.md_purple_500));
        add(getColorRes(R.color.md_purple_600));
        add(getColorRes(R.color.md_purple_700));
        add(getColorRes(R.color.md_purple_800));
        add(getColorRes(R.color.md_purple_900));
        add(getColorRes(R.color.md_purple_A100));
        add(getColorRes(R.color.md_purple_A200));
        add(getColorRes(R.color.md_purple_A400));
        add(getColorRes(R.color.md_purple_A700));
        add(getColorRes(R.color.md_pink_50));
        add(getColorRes(R.color.md_pink_100));
        add(getColorRes(R.color.md_pink_200));
        add(getColorRes(R.color.md_pink_300));
        add(getColorRes(R.color.md_pink_400));
        add(getColorRes(R.color.md_pink_500));
        add(getColorRes(R.color.md_pink_600));
        add(getColorRes(R.color.md_pink_700));
        add(getColorRes(R.color.md_pink_800));
        add(getColorRes(R.color.md_pink_900));
        add(getColorRes(R.color.md_pink_A100));
        add(getColorRes(R.color.md_pink_A200));
        add(getColorRes(R.color.md_pink_A400));
        add(getColorRes(R.color.md_pink_A700));
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mDrawingView = findViewById(R.id.drawing_view);
    }

    @Override
    public void onDrawingViewTouched() {
        Toast.makeText(this, "Called onDrawingViewTouched()", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            Toast.makeText(this, "Action refresh", Toast.LENGTH_LONG).show();
            mDrawingView.clearCanvas();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static int getColorRes(int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getAppContext().getColor(id);
        } else {
            return ResourcesCompat.getColor(getAppContext().getResources(), id, getAppContext().getTheme());
        }
    }
}
