package pkg1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class InputText extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel = new JLabel("Enter Text-To-Speech");
	private JTextArea textArea = new JTextArea();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InputText frame = new InputText();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InputText() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel.setBounds(5, 5, 553, 39);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		textArea.setBounds(5, 44, 553, 189);
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("Play");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = textArea.getText();
				String cmd = "echo "+input+" | festival --tts";
				ProcessBuilder builderPlay = new ProcessBuilder("/bin/bash", "-c", cmd);		
				try {
					Process process = builderPlay.start();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(5, 233, 132, 23);
		contentPane.add(btnNewButton);
		

		
		JButton btnNewButton_1 = new JButton("Save To File");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = textArea.getText();
				File file = new File(".text.txt");
	    		file.delete();
				String cmd = "echo "+input+" > .text.txt";
				ProcessBuilder builderText = new ProcessBuilder("/bin/bash", "-c", cmd);		
				try {
					Process process = builderText.start();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				file = new File(".wave.wav");
	    		file.delete();
				cmd = "text2wave -o .wave.wav .text.txt";
				ProcessBuilder builderWave = new ProcessBuilder("/bin/bash", "-c", cmd);		
				try {
					Process process = builderWave.start();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				NameFile nameFile = new NameFile();
				nameFile.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(147, 233, 140, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Add To Video");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				BackgroundTask backTask = new BackgroundTask();
				backTask.execute();
			}
		});
		btnNewButton_2.setBounds(297, 233, 132, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Close");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_3.setBounds(441, 232, 117, 25);
		contentPane.add(btnNewButton_3);
	}
	
	public class BackgroundTask extends SwingWorker<Void,Void> {

		String outputLocation = ".out1.mp4";

		@Override
		protected Void doInBackground() throws Exception {
			try {
				String input = textArea.getText();
				File file = new File(".text.txt");
	    		file.delete();
				String cmd = "echo "+input+" > .text.txt";
				ProcessBuilder builderText = new ProcessBuilder("/bin/bash", "-c", cmd);		
				try {
					Process process = builderText.start();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				file = new File(".wave.wav");
	    		file.delete();
				cmd = "text2wave -o .wave.wav .text.txt";
				ProcessBuilder builderWave = new ProcessBuilder("/bin/bash", "-c", cmd);		
				try {
					Process process = builderWave.start();
					process.waitFor();
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
				cmd = "ffmpeg -y -i .wave.wav -codec:a libmp3lame -qscale:a 2 .convert.mp3";
				ProcessBuilder builderConvert = new ProcessBuilder("/bin/bash", "-c", cmd);		
				try {
					Process process = builderConvert.start();
					process.waitFor();
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
				cmd = "ffmpeg -y -i "+MediaPlayer.videoLocation+" -i .convert.mp3 -filter_complex amix=inputs=2 "+outputLocation;
				ProcessBuilder builderAdd = new ProcessBuilder("/bin/bash", "-c", cmd);		
				try {
					Process process = builderAdd.start();
					process.waitFor();
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		protected void done() {
			setVisible(false);
			MediaPlayer.mediaPlayerComponent.getMediaPlayer().playMedia(outputLocation);
		}
		
	}
}
