package ru.spbstu.lab6.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.spbstu.lab6.R;
import ru.spbstu.lab6.model.Student;

public class StudentsRecyclerViewAdapter extends RecyclerView.Adapter< StudentsRecyclerViewAdapter.ViewHolder > {

    private final List< Student > mStudents;
    private final OnStudentRemovedListener mListener;

    StudentsRecyclerViewAdapter (List< Student > students, OnStudentRemovedListener listener) {
        mStudents = students;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext( ))
                .inflate(R.layout.item_person_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull final ViewHolder holder, final int position) {
        holder.mStudent = mStudents.get(position);
        holder.mNameTextView.setText(holder.mStudent.getFirstName() + " " + holder.mStudent.getLastName());
        holder.mEmailTextView.setText(holder.mStudent.getEmail());
        holder.mPhoneTextView.setText(holder.mStudent.getPhone());
        holder.mIsHeadmanIcon.setVisibility(holder.mStudent.isHeadman() ? View.VISIBLE : View.GONE);

        holder.mDeleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                removeStudentFromList(position);
                mListener.onStudentRemoved(holder.mStudent);
            }
        });
    }

    private void removeStudentFromList(int position) {
        mStudents.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mStudents.size());
    }

    @Override
    public int getItemCount ( ) {
        return mStudents.size( );
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mNameTextView;
        final TextView mEmailTextView;
        final TextView mPhoneTextView;
        final ImageView mDeleteIcon;
        final ImageView mIsHeadmanIcon;
        Student mStudent;

        ViewHolder (View view) {
            super(view);
            mView = view;
            mNameTextView = view.findViewById(R.id.person_name_text);
            mEmailTextView = view.findViewById(R.id.person_email_text);
            mPhoneTextView = view.findViewById(R.id.person_phone_text);
            mDeleteIcon = view.findViewById(R.id.person_delete_icon);
            mIsHeadmanIcon = view.findViewById(R.id.person_headman_mark);
        }
    }

    public interface OnStudentRemovedListener {
        void onStudentRemoved (Student student);
    }
}
