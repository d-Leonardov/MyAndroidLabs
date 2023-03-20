package alqonquin.cst2335.vill0413.data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class ChatMessage {

	@ColumnInfo(name="message")
	protected String message;

	@ColumnInfo(name="TimeSent")
	private String timeSent;

	@ColumnInfo(name="isSentButton")
	private boolean isSentButton;

	@PrimaryKey (autoGenerate = true)
	@ColumnInfo (name="id")
	public long id;


	public ChatMessage(String m, String t, boolean sent) {
		message = m;
		timeSent = t;
		isSentButton = sent;
	}

	public ChatMessage() {

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

	public void setMessage(String message) {
		this.message = message;
	}

	public void setTimeSent(String timeSent) {
		this.timeSent = timeSent;
	}

	public void setSentButton(boolean isSentButton) {
		isSentButton = isSentButton;
	}

}
