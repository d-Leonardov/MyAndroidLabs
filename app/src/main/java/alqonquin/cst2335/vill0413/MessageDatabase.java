package alqonquin.cst2335.vill0413;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import alqonquin.cst2335.vill0413.data.ChatMessage;

@Database(entities = {ChatMessage.class}, version = 1)
public abstract class MessageDatabase extends RoomDatabase {
	public abstract ChatMessageDAO cmDAO();
}