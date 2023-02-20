package alqonquin.cst2335.vill0413;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import alqonquin.cst2335.vill0413.data.ChatRoomViewModel;
import alqonquin.cst2335.vill0413.databinding.ActivityChatRoomBinding;
import alqonquin.cst2335.vill0413.databinding.SentMessageBinding;

public class ChatRoom extends AppCompatActivity {

	ActivityChatRoomBinding binding;
	ArrayList<String> messages ;

	public RecyclerView.Adapter myAdapter;

	ChatRoomViewModel chatModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);

		messages = chatModel.messages.getValue();

		if(messages == null)
		{
			chatModel.messages.postValue( messages = new ArrayList<String>());
		}

		binding.recycleView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {

			@NonNull
			@Override
			public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
				SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater());
				return new MyRowHolder(binding.getRoot());
			}

			@Override
			public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
				String messageOnRow = messages.get(position);

				holder.messageText.setText(messageOnRow);
				holder.timeText.setText("");
			}

			@Override
			public int getItemCount() {
				return messages.size();
			}

			public int getItemViewType(int position){
				return 0;
			}

		});

		binding.recycleView.setLayoutManager(new LinearLayoutManager(this));

		binding.sendButton.setOnClickListener( click ->{
			messages.add(binding.textInput.getText().toString());
			myAdapter.notifyItemInserted(messages.size()-1);
			binding.textInput.setText("");
		});

	}

	class MyRowHolder extends RecyclerView.ViewHolder {

		public TextView messageText;
		public TextView timeText;

		public MyRowHolder(@NonNull View itemView) {
			super(itemView);

			messageText = itemView.findViewById(R.id.message);
			timeText = itemView.findViewById(R.id.time);
		}
	}
}