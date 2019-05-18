package ru.spbstu.lab6.view;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import ru.spbstu.lab6.R;

public class EnterGroupFragment extends Fragment {

    private OnWelcomeScreenContinueButtonClickedListener mListener;

    private String mGroupName;

    public EnterGroupFragment ( ) {
        // Required empty public constructor
    }

    public static EnterGroupFragment newInstance() {
        return new EnterGroupFragment( );
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
        View view = inflater.inflate(R.layout.fragment_enter_group, container, false);

        final TextInputEditText textInputEditText = view.findViewById(R.id.fragment_welcome_edit_text);
        final Button continueButton = view.findViewById(R.id.fragment_welcome_proceed_button);

        textInputEditText.addTextChangedListener(new TextWatcher( ) {
            @Override
            public void beforeTextChanged (CharSequence s, int start, int count, int after) {
                Log.d("Enter", "Before" );
            }

            @Override
            public void onTextChanged (CharSequence s, int start, int before, int count) {
                Log.d("Enter", "OnChange" );
                continueButton.setEnabled(s.length( ) > 0);
            }

            @Override
            public void afterTextChanged (Editable s) {
                Log.d("Enter", "After" );
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                if ( mListener != null ) {
                    Editable group = textInputEditText.getText();
                    if (group != null) {
                        mGroupName = textInputEditText.getText().toString();
                        mListener.onContinueClicked(mGroupName);
                    } else {
                        Toast.makeText(
                                (Context) mListener,
                                ((Context) mListener).getString(R.string.error_group_name),
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
            }
        });


        return view;
    }

    @Override
    public void onAttach (@NonNull Context context) {
        super.onAttach(context);
        if ( context instanceof OnWelcomeScreenContinueButtonClickedListener ) {
            mListener = (OnWelcomeScreenContinueButtonClickedListener) context;
        } else {
            throw new RuntimeException(context.toString( ) + " must implement OnWelcomeScreenContinueButtonClickedListener");
        }
    }

    @Override
    public void onDetach ( ) {
        super.onDetach( );
        mListener = null;
    }

    public interface OnWelcomeScreenContinueButtonClickedListener {
        void onContinueClicked(String groupName);
    }
}
