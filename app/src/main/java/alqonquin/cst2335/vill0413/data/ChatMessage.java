package alqonquin.cst2335.vill0413.data;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class ChatMessage {

	private String message;
	private String timeSent;
	private boolean isSentButton;

	public MutableLiveData<ArrayList<String>> messages = new MutableLiveData< >();

	ChatMessage(String m, String t, boolean sent) {
		message = m;
		timeSent = t;
		isSentButton = sent;
	}

	public String getMessage() {
		return message;
	}

	public String getTimeSent() {
		return timeSent;
	}

	public boolean isSentButton() {
		return isSentButton;
	}
}
