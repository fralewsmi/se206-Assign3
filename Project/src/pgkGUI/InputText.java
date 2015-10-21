package pgkGUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import pkgBackgroundTasks.FestivalTTS;
import pkgBackgroundTasks.MergeTTS;
import pkgBackgroundTasks.SaveTTS;

@SuppressWarnings("serial")
public class InputText extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel = new JLabel("Enter Text-To-Speech");
	private static JTextArea textArea = new JTextArea();

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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 589, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNewLabel.setBounds(5, 5, 553, 39);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);

		textArea.setBounds(5, 44, 553, 182);
		contentPane.add(textArea);
				
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(446, 238, 131, 23);
		contentPane.add(progressBar);
		
		// Use process builder with festival to play the text to speech
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FestivalTTS festivalTTS = new FestivalTTS();
				festivalTTS.execute();
			}
		});
		btnPlay.setBounds(5, 238, 108, 23);
		contentPane.add(btnPlay);

		// Use process builder to save the output of the festival tts with text2wave 
		JButton btnSave = new JButton("Save To File");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveTTS saveTTS = new SaveTTS();
				saveTTS.execute();
			}
		});
		btnSave.setBounds(125, 238, 150, 23);
		contentPane.add(btnSave);
		
		// Add the festival tts to the video with a background task
		JButton btnAdd = new JButton("Add To Video");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				progressBar.setIndeterminate(true);
				MergeTTS mergeTTS = new MergeTTS();
				mergeTTS.execute();
				progressBar.setIndeterminate(false);
				setVisible(false);
			}
		});
		btnAdd.setBounds(287, 238, 150, 23);
		contentPane.add(btnAdd);

		
	}

	public static String getTextInput() {
		return textArea.getText();
	}
}
