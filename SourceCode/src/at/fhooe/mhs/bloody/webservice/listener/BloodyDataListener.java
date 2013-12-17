package at.fhooe.mhs.bloody.webservice.listener;

import java.util.List;

import at.fhooe.mhs.bloody.webservice.data.BloodyData;

public interface BloodyDataListener {
	
	public void onBloodyDataReceived(List<BloodyData> bloodyData);
}
