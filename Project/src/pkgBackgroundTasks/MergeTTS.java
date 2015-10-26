package pkgBackgroundTasks;

import javax.swing.SwingWorker;

import pgkGUI.MediaPlayer;
import pkgBashProcesses.ProcessConvert;
import pkgBashProcesses.ProcessMerge;
import pkgBashProcesses.ProcessSaveText;
import pkgBashProcesses.ProcessText2Wave;

public class MergeTTS extends SwingWorker<Void, Void> {

	// Multiple process builders with festival, text2wave and ffmpeg
	@Override
	protected Void doInBackground() throws Exception {

		try {

			System.out.print("1\n");
			// Save the text from user input to text file
			ProcessSaveText saveText = new ProcessSaveText();
			saveText.run();

			System.out.print("2\n");
			// Convert text to wave file
			ProcessText2Wave txt2wav = new ProcessText2Wave();
			txt2wav.run();

			System.out.print("3\n");
			// Convert wave to mp3 file
			ProcessConvert convert = new ProcessConvert();
			convert.run();

			System.out.print("4\n");
			// Merge selected video and mp3 to mp4 file
			ProcessMerge merge = new ProcessMerge();
			merge.run();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// Play the new video once the process is done
	@Override
	protected void done() {

		System.out.print("Done\n");

		// set the new video for further use and play it
		String outputLocation = ProcessMerge.getOutputLocation();
		MediaPlayer.setVideoLocation(outputLocation);

		MediaPlayer.getMediaPlayerComponent().getMediaPlayer().playMedia(outputLocation);
	}

}