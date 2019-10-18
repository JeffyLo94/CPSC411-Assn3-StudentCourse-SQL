package com.jeffreylo.android.assn2_studentcourse.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jeffreylo.android.assn2_studentcourse.R;
import com.jeffreylo.android.assn2_studentcourse.StudentDetailActivity;
import com.jeffreylo.android.assn2_studentcourse.model.Student;
import com.jeffreylo.android.assn2_studentcourse.model.StudentDB;

public class SummaryLVAdapter extends BaseAdapter {

    @Override
    public int getCount() {
        return StudentDB.getInstance().getStudents().size();
    }

    @Override
    public Object getItem(int position) {
        return StudentDB.getInstance().getStudents().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row_view;

        if (view == null) {
            // cnt++;
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            row_view = inflater.inflate(R.layout.student_row, viewGroup, false);
        } else row_view = view;

        Student stud = StudentDB.getInstance().getStudents().get(i);

        TextView firstNameView = (TextView) row_view.findViewById(R.id.first_name);
        firstNameView.setText(stud.getFirstName());
        TextView lastNameView = (TextView) row_view.findViewById(R.id.last_name);
        lastNameView.setText(stud.getLastName());
        row_view.setTag(new Integer(i));

        row_view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //
                        Intent intent = new Intent(view.getContext(), StudentDetailActivity.class);
                        intent.putExtra("StudentIndex", ((Integer)view.getTag()).intValue());
                        view.getContext().startActivity(intent);
                    }
                }
        );

        return row_view;
    }
}
