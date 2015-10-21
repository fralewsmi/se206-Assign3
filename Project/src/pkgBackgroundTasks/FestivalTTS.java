package pkgBackgroundTasks;

import java.io.IOException;

import javax.swing.SwingWorker;

import pgkGUI.InputText;

public class FestivalTTS extends SwingWorker<Void, Void> {
	
	@Override
	protected Void doInBackground() throws Exception {
		String textInput = InputText.getTextInput();
		String cmd = "echo " + textInput + " | festival --tts";
		ProcessBuilder builderPlay = new ProcessBuilder("/bin/bash", "-c", cmd);
		try {
			@SuppressWarnings("unused")
			Process process = builderPlay.start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}
}
