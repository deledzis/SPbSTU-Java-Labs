package ru.spbstu.lab6.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import ru.spbstu.lab6.R;
import ru.spbstu.lab6.model.Student;

public class StudentsListFragment extends Fragment implements StudentsRecyclerViewAdapter.OnStudentRemovedListener {

    private AppCompatActivity mActivity;

    private ConstraintLayout mEmptyStateLayout;
    private RecyclerView mRecyclerView;

    private ArrayList< Student > mStudents;

    public StudentsListFragment ( ) {
    }

    public static StudentsListFragment newInstance () {
        return new StudentsListFragment( );
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStudents = new ArrayList<>();
    }

    @Override
    public View onCreateView (
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_main_list, container, false);

        mEmptyStateLayout = view.findViewById(R.id.empty_content_layout);
        mRecyclerView = view.findViewById(R.id.fragment_list_students_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

        return view;
    }

    @Override
    public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadData();
    }

    @Override
    public void onResume ( ) {
        super.onResume( );

        checkList();
    }

    @Override
    public void onPause ( ) {
        super.onPause( );

        if (mStudents != null && !mStudents.isEmpty()) {
            saveData();
        }
    }

    @Override
    public void onAttach (@NonNull Context context) {
        super.onAttach(context);

        mActivity = (AppCompatActivity) context;
    }

    private void checkList() {
        if (mStudents == null || mStudents.isEmpty()) {
            showEmptyState();
        } else {
            mRecyclerView.setAdapter(new StudentsRecyclerViewAdapter(mStudents, this));
            hideEmptyState();
        }
    }

    private void showEmptyState() {
        mEmptyStateLayout.setVisibility(View.VISIBLE);
    }

    private void hideEmptyState() {
        mEmptyStateLayout.setVisibility(View.GONE);
    }

    private void saveData() {
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(mActivity.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mStudents);
        prefsEditor.putString("students_list", json);
        prefsEditor.apply();
    }

    private void loadData() {
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(mActivity.getApplicationContext());
        Gson gson = new Gson();
        String json = appSharedPrefs.getString("students_list", "");
        Type type = new TypeToken<ArrayList<Student>>(){}.getType();
        mStudents = gson.fromJson(json, type);
    }

    public void receiveNewStudent(Student student) {
        if (mStudents == null) mStudents = new ArrayList<>();
        mStudents.add(student);
        saveData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    @Override
    public void onStudentRemoved (Student student) {
        checkList();
        saveData();
    }
}
