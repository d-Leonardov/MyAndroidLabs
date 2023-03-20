package alqonquin.cst2335.vill0413;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import alqonquin.cst2335.vill0413.data.ChatMessage;
import alqonquin.cst2335.vill0413.data.ChatRoomViewModel;
import alqonquin.cst2335.vill0413.databinding.ActivityChatRoomBinding;
import alqonquin.cst2335.vill0413.databinding.ReceiveMessageBinding;
import alqonquin.cst2335.vill0413.databinding.SentMessageBinding;

public class ChatRoom extends AppCompatActivity {

	ActivityChatRoomBinding binding;
	ArrayList<ChatMessage> messages ;

	public RecyclerView.Adapter myAdapter;

	ChatRoomViewModel chatModel;

	ChatMessage chat = new ChatMessage("","",false);

	SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
	String currentDateandTimendTime = sdf.format(new Date());
	ChatMessageDAO mDAO;

	Executor thread = Executors.newSingleThreadExecutor();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		MessageDatabase db = Room.databaseBuilder(getApplicationContext(),
				MessageDatabase.class, "database-name").build();
		mDAO = db.cmDAO();

		binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
		messages = chatModel.messages.getValue();

		if(messages == null)
		{
			chatModel.messages.postValue( messages = new ArrayList<ChatMessage>());

			Executor thread = Executors.newSingleThreadExecutor();
			thread.execute(() ->
			{
				messages.addAll( mDAO.getAllMessages() ); //Once you get the data from database

				runOnUiThread( () ->  binding.recycleView.setAdapter( myAdapter )); //You can then load the RecyclerView
			});
		}

		binding.sendButton.setOnClickListener( click ->{

			String message = binding.textInput.getText().toString();
			boolean sentButton = true;
			ChatMessage chat = new ChatMessage(message,currentDateandTimendTime, sentButton );
			messages.add(chat);
			thread.execute(() ->
			{
				chat.id = mDAO.insertMessage(chat);
			});
			myAdapter.notifyItemInserted(messages.size()-1);
			binding.textInput.setText("");

		});

		binding.receiveButton.setOnClickListener( click ->{

			String message = binding.textInput.getText().toString();
			boolean sentButton = false;
			chat = new ChatMessage(message,currentDateandTimendTime, sentButton );
			messages.add(chat);
			thread.execute(() ->
			{
				chat.id = mDAO.insertMessage(chat);
			});

			myAdapter.notifyItemInserted(messages.size()-1);
			binding.textInput.setText("");

		});

		binding.recycleView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {

			@NonNull
			@Override
			public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
				if (viewType == 0) {
					SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater());
					return new MyRowHolder(binding.getRoot());
				} else {
					ReceiveMessageBinding binding = ReceiveMessageBinding.inflate(getLayoutInflater());
					return new MyRowHolder(binding.getRoot());
				}
			}

			@Override
			public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
				ChatMessage chatMessage = messages.get(position);
				holder.messageText.setText(chatMessage.getMessage());
				holder.timeText.setText(chatMessage.getTimeSent());
			}

			@Override
			public int getItemCount() {
				return messages.size();
			}

			public int getItemViewType(int position){
				ChatMessage chatMessage = messages.get(position);
				if (chatMessage.isSentButton()) {
					return 0;
				} else {
					return 1;
				}
			}
		});

		binding.recycleView.setLayoutManager(new LinearLayoutManager(this));
	}
	class MyRowHolder extends RecyclerView.ViewHolder {

		public TextView messageText;
		public TextView timeText;

		public MyRowHolder(@NonNull View itemView) {
			super(itemView);

			AlertDialog.Builder builder = new AlertDialog.Builder( ChatRoom.this );

			itemView.setOnClickListener(click ->{
				int position = getAbsoluteAdapterPosition();
				builder.setMessage("Do you want to delete the message: " +
						messageText.getText())
				.setTitle("Question:")
				.setNegativeButton("NO",(dialog, cl) -> { })
				.setPositiveButton("YES",(dialog, cl) -> {

					ChatMessage removedMessage = messages.get(position);
					thread.execute(() ->
					{
						removedMessage.id = mDAO.deleteMessage(removedMessage);
					});
					messages.remove(position);
					myAdapter.notifyItemRemoved(position);

					Snackbar.make(messageText,"You deleted message # " + position,
							Snackbar.LENGTH_LONG)
							.setAction("Undo", clk ->{
								thread.execute(() ->
								{
									removedMessage.id = mDAO.insertMessage(removedMessage);
								});
								messages.add(position,removedMessage);
								myAdapter.notifyItemInserted(position);
							})
							.show();
				})
				.create()
				.show();
			});
			messageText = itemView.findViewById(R.id.message);
			timeText = itemView.findViewById(R.id.time);
		}
	}
}