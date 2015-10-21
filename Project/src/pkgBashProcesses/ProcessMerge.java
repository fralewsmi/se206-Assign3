package pkgBashProcesses;

import java.io.IOException;

import javax.swing.SwingWorker;

import pgkGUI.MediaPlayer;
import pkgBackgroundTasks.MergeTTS;

public class ProcessMerge  extends SwingWorker<Void, Void> {
	
	String videoLocation = MediaPlayer.getVideoLocation();
	String outputLocation = MergeTTS.getOutputLocation();
	
	@Override
	protected Void doInBackground() throws Exception {
		
		String cmd = "ffmpeg -y -i " + videoLocation + " -i .convert.mp3 -filter_complex amix=inputs=2 "+ outputLocation;
		ProcessBuilder builderAdd = new ProcessBuilder("/bin/bash", "-c", cmd);
		try {
			Process process = builderAdd.start();
			process.waitFor();
		} catch (IOException | InterruptedException e1) {
			e1.printStackTrace();
		}
		return null;
	}
}
