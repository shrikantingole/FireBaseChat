package com.androidchatapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ChatMultiAdapter extends RecyclerView.Adapter{


    private static final int VIEW_TYPE_USER = 0;
    private static final int VIEW_TYPE_EMPOLYEE = 1;
    List<ChatMessage> chatMessages;
    Context context;

    public ChatMultiAdapter(List<ChatMessage> chatMessages, Context context)
    {
        this.chatMessages=chatMessages;
        this.context=context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==VIEW_TYPE_USER){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_chat_listitem, parent, false);
            return new ChatMultiAdapter.UserViewHolder(view);
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_chat_listitem, parent, false);
            return new ChatMultiAdapter.EmployeeViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position)==0) {
            UserViewHolder userViewHolder = (UserViewHolder) holder;
            userViewHolder.tvTextMessage.setText(chatMessages.get(position).getMgsText());
            userViewHolder.tvMessageDate.setText(DateFormat.format("hh:mm a",chatMessages.get(position).getMsgTime()));
            userViewHolder.tvTextUser.setText(chatMessages.get(position).getMsgUserName());
        }else if (getItemViewType(position)==1){
            EmployeeViewHolder employeeViewHolder = (EmployeeViewHolder) holder;
            employeeViewHolder.tvTextMessage.setText(chatMessages.get(position).getMgsText());
            employeeViewHolder.tvMessageDate.setText(DateFormat.format("hh:mm a",chatMessages.get(position).getMsgTime()));
            employeeViewHolder.tvTextUser.setText(chatMessages.get(position).getMsgUserName());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return chatMessages.get(position).getMsgUserType();
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }


    class UserViewHolder extends RecyclerView.ViewHolder{
        TextView tvTextMessage,tvTextUser,tvMessageDate;

        public UserViewHolder(View itemView) {
            super(itemView);
            this.tvTextMessage = itemView.findViewById(R.id.message_text);
            this.tvTextUser = itemView.findViewById(R.id.message_user);
            this.tvMessageDate = itemView.findViewById(R.id.message_time);

            Utils.setFontTxt(this.tvTextMessage,context,Utils.AVENIR_LIGHT);
            Utils.setFontTxt(this.tvMessageDate,context,Utils.AVENIR_LIGHT);
            Utils.setFontTxt(this.tvTextUser,context,Utils.AVENIR_MEDIUM);


        }
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder{
        TextView tvTextMessage,tvTextUser,tvMessageDate;
        public EmployeeViewHolder(View itemView) {
            super(itemView);
            this.tvTextMessage = itemView.findViewById(R.id.message_text);
            this.tvTextUser = itemView.findViewById(R.id.message_user);
            this.tvMessageDate = itemView.findViewById(R.id.message_time);

            Utils.setFontTxt(this.tvTextMessage,context,Utils.AVENIR_LIGHT);
            Utils.setFontTxt(this.tvMessageDate,context,Utils.AVENIR_LIGHT);
            Utils.setFontTxt(this.tvTextUser,context,Utils.AVENIR_MEDIUM);



        }
    }
}
