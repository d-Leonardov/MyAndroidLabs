package alqonquin.cst2335.vill0413.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ChatMessage {

	@ColumnInfo(name="message")
	public String message;

	@ColumnInfo(name="TimeSent")
	public String timeSent;

	@ColumnInfo(name="isSentButton")
	public boolean isSentButton;

	@PrimaryKey (autoGenerate = true)
	@ColumnInfo (name="id")
	public int id;


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

	public int getId(){
		return id;
	}

	public boolean isSentButton() {
		return isSentButton;
	}

	/*public void setMessage(String message) {
		this.message = message;
	}

	public void setTimeSent(String timeSent) {
		this.timeSent = timeSent;
	}

	public void setSentButton(boolean isSentButton) {
		isSentButton = isSentButton;
	}


*/
}