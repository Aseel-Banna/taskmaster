package com.example.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    static private ListItemClickListener mOnClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView taskTitle;
        private TextView taskBody;
        private TextView taskState;


            public ViewHolder (View itemView) {
                super(itemView);
                taskTitle = itemView.findViewById(R.id.taskTitle);
                taskBody = itemView.findViewById(R.id.taskBody);
                taskState = itemView.findViewById(R.id.taskState);
                itemView.setOnClickListener(this);
        }

        public TextView getTextView() {
                return taskTitle;
            }
        public TextView getTextView2() {
            return taskBody;
        }
        public TextView getTextView3() {
            return taskState;
        }


        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            Intent intent= new Intent (itemView.getContext(), Details.class);
            intent.putExtra("title", getTextView().getText() );
            intent.putExtra("body",  getTextView2().getText());
            intent.putExtra("state", getTextView3().getText());
            itemView.getContext().startActivity(intent);
//            switch (clickedPosition) {
//                case 0:
//                    intent = new Intent (itemView.getContext(), Details.class);
//                    intent.putExtra("title",  "Task 1");
//                    intent.putExtra("body",  "It is the first task I have created");
//                    intent.putExtra("state", "complete");
//                    itemView.getContext().startActivity(intent);
//                    break;
//                case 1:
//                    intent = new Intent (itemView.getContext(), Details.class);
//                    intent.putExtra("title",  "Task 2");
//                    intent.putExtra("body",  "It is the second task I have created");
//                    intent.putExtra("state", "in progress");
//                    itemView.getContext().startActivity(intent);
//                    break;
//                case 2:
//                    intent = new Intent (itemView.getContext(), Details.class);
//                    intent.putExtra("title",  "Task 3");
//                    intent.putExtra("body",  "It is the third task I have created");
//                    intent.putExtra("state", "new");
//                    itemView.getContext().startActivity(intent);
//                    break;
//            }

        }
    }


    private List<Task> mTasks;

    public TaskAdapter(List<Task> tasks, ListItemClickListener listener) {
        this.mOnClickListener = listener;
        mTasks = tasks;
    }

    public TaskAdapter(List<Task> tasks) {
        mTasks = tasks;
    }

        @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
            View contactView = inflater.inflate(R.layout.fragment_task, parent, false);

            // Return a new holder instance
            ViewHolder viewHolder = new ViewHolder(contactView);
            return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        Task task = mTasks.get(position);

        // Set item views based on your views and data model
        TextView taskTitle = holder.taskTitle;
        taskTitle.setText(task.getTitle());

        TextView taskBody = holder.taskBody;
        taskBody.setText(task.getBody());

        TextView taskState = holder.taskState;
        taskState.setText(task.getState());
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

}
