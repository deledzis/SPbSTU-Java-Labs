package ru.spbstu.lab6;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ru.spbstu.lab6.model.Student;
import ru.spbstu.lab6.view.EnterGroupFragment;
import ru.spbstu.lab6.view.NewStudentFragment;
import ru.spbstu.lab6.view.StudentsListFragment;

public class MainActivity
        extends AppCompatActivity
        implements EnterGroupFragment.OnWelcomeScreenContinueButtonClickedListener,
        NewStudentFragment.OnNewStudentFragmentResult {

    private AppBarLayout mAppBarLayout;
    private FloatingActionButton mFloatingActionButton;

    private String mGroupName;

    private StudentsListFragment mStudentsListFragment;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAppBarLayout = findViewById(R.id.appbar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFloatingActionButton = findViewById(R.id.fab);

        loadGroup();
        if (mGroupName == null || mGroupName.isEmpty()) {
            mFloatingActionButton.setVisibility(View.GONE);
            mAppBarLayout.setVisibility(View.GONE);
            changeFragment(EnterGroupFragment.newInstance());
        } else {
            setToolbarTitle("Group " + mGroupName + " students");
            mStudentsListFragment = StudentsListFragment.newInstance();
            changeFragment(mStudentsListFragment);
        }

        mFloatingActionButton.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick (View view) {
                addFragment(NewStudentFragment.newInstance());
                mFloatingActionButton.setVisibility(View.GONE);
                setToolbarTitle("Add new student");
                toggleToolbarBackButton(true);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp ( ) {
        onBackPressed();
        return true;
    }

    private void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_holder, fragment)
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .commit();
    }

    private void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_holder, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void saveGroup() {
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("group", mGroupName);
        prefsEditor.apply();
    }

    private void loadGroup() {
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mGroupName = appSharedPrefs.getString("group", "");
    }

    private void setToolbarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    private void toggleToolbarBackButton(boolean show) {
        if (getSupportActionBar() != null) {
            getSupportActionBar( ).setDisplayHomeAsUpEnabled(show);
            getSupportActionBar( ).setDisplayShowHomeEnabled(show);
        }
    }

    @Override
    public void onBackPressed ( ) {
        super.onBackPressed();
        mFloatingActionButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void onContinueClicked (String groupName) {
        mGroupName = groupName;
        setToolbarTitle("Group " + mGroupName + " students");
        saveGroup();
        mStudentsListFragment = StudentsListFragment.newInstance();
        changeFragment(mStudentsListFragment);
        mFloatingActionButton.setVisibility(View.VISIBLE);
        mAppBarLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStudentCreated (Student student) {
        if (mStudentsListFragment != null) {
            mStudentsListFragment.receiveNewStudent(student);
        }
        onBackPressed();
    }

    @Override
    public void onFragmentClosed ( ) {
        setToolbarTitle("Group " + mGroupName + " students");
        toggleToolbarBackButton(false);
        mFloatingActionButton.setVisibility(View.VISIBLE);
    }
}
