package pkgBackgroundTasks;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.SwingWorker;

import pgkGUI.MediaPlayer;

public class SaveVideo extends SwingWorker<Void, Void> {

	private String videoLocation = MediaPlayer.getVideoLocation();
	private String saveLocation = MediaPlayer.getVideoSaveLocation();

	@Override
	protected Void doInBackground() throws Exception {

		File source = new File(videoLocation);
		File dest = new File(saveLocation);
		try {
			Files.copy(source.toPath(), dest.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
