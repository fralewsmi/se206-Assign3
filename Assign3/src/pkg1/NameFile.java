package pkg1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class NameFile extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NameFile frame = new NameFile();
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
	public NameFile() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 182);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNameOutputFile = new JLabel("Name Output File");
		lblNameOutputFile.setBounds(5, 5, 440, 38);
		lblNameOutputFile.setFont(new Font("Dialog", Font.BOLD, 32));
		lblNameOutputFile.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNameOutputFile);
		
		textField = new JTextField();
		textField.setBounds(15, 55, 221, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = textField.getText();
				String cmd = "ffmpeg -y -i .wave.wav -codec:a libmp3lame -qscale:a 2 "+fileName+".mp3";
				ProcessBuilder builderWave = new ProcessBuilder("/bin/bash", "-c", cmd);		
				try {
					Process process = builderWave.start();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		        setVisible(false);
			}
		});
		btnNewButton.setBounds(5, 119, 440, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblmp = new JLabel(".mp3");
		lblmp.setBounds(254, 55, 184, 25);
		contentPane.add(lblmp);
		
		JLabel lblSavedToWorking = new JLabel("File will be saved to working directory");
		lblSavedToWorking.setBounds(25, 92, 413, 15);
		contentPane.add(lblSavedToWorking);
	}
}
