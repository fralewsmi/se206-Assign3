package pkgBackgroundTasks;

import java.io.File;

import javax.swing.SwingWorker;

import pgkGUI.InputText;
import pgkGUI.MediaPlayer;
import pkgBashProcesses.ProcessConvert;
import pkgBashProcesses.ProcessMerge;
import pkgBashProcesses.ProcessSaveText;
import pkgBashProcesses.ProcessText2Wave;

public class MergeTTS  extends SwingWorker<Void, Void> {

	private static final String OUTPUTLOCATION = ".outputMergeTTS.mp4";
	String textInput = InputText.getTextInput();

	@Override // Multiple process builders with festival, text2wave and ffmpeg
	protected Void doInBackground() throws Exception {
		try {
			File file = new File(".text.txt");
			file.delete();
			ProcessSaveText saveText = new ProcessSaveText();
			saveText.execute();
			
			file = new File(".wave.wav");
			file.delete();
			ProcessText2Wave txt2wav = new ProcessText2Wave();
			txt2wav.execute();
			
			ProcessConvert convert = new ProcessConvert();
			convert.execute();
			
			ProcessMerge merge = new ProcessMerge();
			merge.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Play the new video once the process is done
	protected void done() {

		MediaPlayer.getMediaPlayerComponent().getMediaPlayer().playMedia(OUTPUTLOCATION);
	}

	public static String getOutputLocation() {
		return OUTPUTLOCATION;
	}

}