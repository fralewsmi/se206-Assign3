package pkgBashProcesses;

import java.io.IOException;

import pgkGUI.InputText;

public class ProcessFestival {

	public void run() {

		String textInput = InputText.getTextInput();

		// Bash process to run the festival text to speech
		String cmd = "echo " + textInput + " | festival --tts";
		ProcessBuilder builderPlay = new ProcessBuilder("/bin/bash", "-c", cmd);
		try {
			@SuppressWarnings("unused")
			Process process = builderPlay.start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return;
	}

}
