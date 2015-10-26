package pkgBashProcesses;

import java.io.IOException;

import pgkGUI.MediaPlayer;

public class ProcessMerge {

	private static final String OUTPUTLOCATION = ".outputTTS.mp4";

	public static String getOutputLocation() {
		return OUTPUTLOCATION;
	}

	public void run() {
		System.out.print("merge running\n");

		String videoLocation = MediaPlayer.getVideoLocation();
		String convertLocation = ProcessConvert.getConvertLocation();

		// BASH command to merge the current video with the converted mp3 file
		// to output an mp4
		String cmd = "ffmpeg -y -i " + videoLocation + " -i " + convertLocation + " -filter_complex amix=inputs=2 "
				+ OUTPUTLOCATION;
		ProcessBuilder builderAdd = new ProcessBuilder("/bin/bash", "-c", cmd);
		try {
			@SuppressWarnings("unused")
			Process process = builderAdd.start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.print("merge finished\n");
		return;
	}
}
