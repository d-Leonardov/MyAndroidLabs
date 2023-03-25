package alqonquin.cst2335.vill0413;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

import alqonquin.cst2335.vill0413.data.ChatMessage;
import alqonquin.cst2335.vill0413.databinding.DetailsLayoutBinding;

public class MessageDetailsFragment extends Fragment {

	ChatMessage selected;
	public MessageDetailsFragment(ChatMessage m){
		selected = m;
	}

	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container,Bundle
			savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		DetailsLayoutBinding binding = DetailsLayoutBinding.inflate(inflater);
		binding.messageText.setText(selected.message);
		binding.timeText.setText(selected.timeSent);
		if (selected.isSentButton == true){
			binding.textView5.setText("Send message");
		} else
			binding.textView5.setText("Receive message");
		binding.databaseText.setText("Id = " + selected.id);
		return binding.getRoot();
	}
}
