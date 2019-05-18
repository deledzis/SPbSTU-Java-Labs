package ru.spbstu.lab6.view;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import ru.spbstu.lab6.R;
import ru.spbstu.lab6.model.Student;

public class NewStudentFragment extends Fragment {
    private OnNewStudentFragmentResult mListener;

    private Student mStudent;

    private TextInputEditText mFirstNameEditText;
    private TextInputEditText mLastNameEditText;
    private TextInputEditText mEmailEditText;
    private TextInputEditText mPhoneEditText;
    private Button mAddStudentButton;

    public NewStudentFragment ( ) {
        // Required empty public constructor
    }

    public static NewStudentFragment newInstance() {
        return new NewStudentFragment( );
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView (
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_add_new_student, container, false);

        mStudent = new Student();

        mFirstNameEditText = view.findViewById(R.id.add_student_first_name_edit_text);
        mLastNameEditText = view.findViewById(R.id.add_student_last_name_edit_text);
        mEmailEditText = view.findViewById(R.id.add_student_email_edit_text);
        mPhoneEditText = view.findViewById(R.id.add_student_phone_edit_text);
        SwitchCompat isHeadmanSwitch = view.findViewById(R.id.add_student_is_headman_switch);
        mAddStudentButton = view.findViewById(R.id.add_student_button);

        mFirstNameEditText.addTextChangedListener(new TextWatcher( ) {
            @Override
            public void beforeTextChanged (CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged (CharSequence s, int start, int before, int count) {
                mStudent.setFirstName(s.toString());
                mAddStudentButton.setVisibility(checkEnable() ? View.VISIBLE : View.GONE);
            }

            @Override
            public void afterTextChanged (Editable s) { }
        });
        mLastNameEditText.addTextChangedListener(new TextWatcher( ) {
            @Override
            public void beforeTextChanged (CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged (CharSequence s, int start, int before, int count) {
                mStudent.setLastName(s.toString());
                mAddStudentButton.setVisibility(checkEnable() ? View.VISIBLE : View.GONE);
            }

            @Override
            public void afterTextChanged (Editable s) { }
        });
        mEmailEditText.addTextChangedListener(new TextWatcher( ) {
            @Override
            public void beforeTextChanged (CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged (CharSequence s, int start, int before, int count) {
                mStudent.setEmail(s.toString());
                mAddStudentButton.setVisibility(checkEnable() ? View.VISIBLE : View.GONE);
            }

            @Override
            public void afterTextChanged (Editable s) { }
        });
        mPhoneEditText.addTextChangedListener(new TextWatcher( ) {
            @Override
            public void beforeTextChanged (CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged (CharSequence s, int start, int before, int count) {
                mStudent.setPhone(s.toString());
                mAddStudentButton.setVisibility(checkEnable() ? View.VISIBLE : View.GONE);
            }

            @Override
            public void afterTextChanged (Editable s) { }
        });
        isHeadmanSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener( ) {
            @Override
            public void onCheckedChanged (CompoundButton buttonView, boolean isChecked) {
                mStudent.setHeadman(isChecked);
            }
        });
        mAddStudentButton.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                if (mListener != null) {
                    if ( mStudent != null ) {
                        mListener.onStudentCreated(mStudent);
                    }
                }
            }
        });

        return view;
    }

    private boolean checkEnable() {
        return mFirstNameEditText.getText() != null && mFirstNameEditText.getText().length() > 0 &&
                mLastNameEditText.getText() != null && mLastNameEditText.getText().length() > 0 &&
                mEmailEditText.getText() != null && mEmailEditText.getText().length() > 0 &&
                mPhoneEditText.getText() != null && mPhoneEditText.getText().length() > 0;
    }

    @Override
    public void onAttach (@NonNull Context context) {
        super.onAttach(context);
        if ( context instanceof EnterGroupFragment.OnWelcomeScreenContinueButtonClickedListener ) {
            mListener = (OnNewStudentFragmentResult) context;
        } else {
            throw new RuntimeException(context.toString( ) + " must implement OnWelcomeScreenContinueButtonClickedListener");
        }
    }

    @Override
    public void onDetach ( ) {
        super.onDetach( );
        mListener.onFragmentClosed();
        mListener = null;
    }

    public interface OnNewStudentFragmentResult {
        void onStudentCreated(Student student);
        void onFragmentClosed();
    }
}
