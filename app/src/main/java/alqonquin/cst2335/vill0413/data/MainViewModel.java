package alqonquin.cst2335.vill0413.data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel{
    //public String editString;
    public MutableLiveData<String> editString = new MutableLiveData<>();
}