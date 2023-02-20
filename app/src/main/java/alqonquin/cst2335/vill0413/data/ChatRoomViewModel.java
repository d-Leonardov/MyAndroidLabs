package alqonquin.cst2335.vill0413.data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ChatRoomViewModel extends ViewModel {

	public MutableLiveData<ArrayList<String>> messages = new MutableLiveData< >();
}
