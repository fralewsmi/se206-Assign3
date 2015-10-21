package pkgBackgroundTasks;

import java.io.IOException;

import javax.swing.SwingWorker;

import pgkGUI.MediaPlayer;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;

public class AudioChanger extends SwingWorker<Void, Void> {
	
	String videoLocation = MediaPlayer.getVideoLocation();
	String audioLocation = MediaPlayer.getAudioLocation();
	EmbeddedMediaPlayerComponent mediaPlayerComponent = MediaPlayer.getMediaPlayerComponent();
	
	@Override // Use a process builder with fmmpeg to add the audio to the video
	protected Void doInBackground() throws Exception {
		String outputLocation = ".out.mp4";
		String cmd = "ffmpeg -y -i " + videoLocation + " -i " + audioLocation + " -map 0:v -map 1:a "+ outputLocation;
		ProcessBuilder builderAudio = new ProcessBuilder("/bin/bash", "-c", cmd);
		try {
			Process process = builderAudio.start();
			process.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		videoLocation = outputLocation;
		return null;
	}
	
	// Play the new video once the process is finished
	protected void done() {
		mediaPlayerComponent.getMediaPlayer().playMedia(videoLocation);
	}
	
}
