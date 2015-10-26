package pkgBashProcesses;

import java.io.IOException;

public class ProcessConvert {

	private static final String CONVERTLOCATION = "convert.mp3";

	public static String getConvertLocation() {
		return CONVERTLOCATION;
	}

	public void run() {
		System.out.print("convert running\n");

		String waveLocation = ProcessText2Wave.getWaveLocation();

		// BASH command to convert the wave file into an mp3
		String cmd = "ffmpeg -y -i " + waveLocation + " -codec:a libmp3lame -qscale:a 2 " + CONVERTLOCATION;
		ProcessBuilder builderConvert = new ProcessBuilder("/bin/bash", "-c", cmd);
		try {
			@SuppressWarnings("unused")
			Process process = builderConvert.start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.print("convert finished\n");
		return;
	}
}
