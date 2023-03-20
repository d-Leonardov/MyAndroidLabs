package alqonquin.cst2335.vill0413;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import alqonquin.cst2335.vill0413.data.ChatMessage;

@Dao
public interface ChatMessageDAO {

	@Insert
	public long insertMessage(ChatMessage m);

	@Query("Select * from ChatMessage")
	List<ChatMessage> getAllMessages();

	@Delete
	public int deleteMessage(ChatMessage m);



}
