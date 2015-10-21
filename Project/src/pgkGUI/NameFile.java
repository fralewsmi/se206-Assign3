package pgkGUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class NameFile extends JFrame {

	private JPanel contentPane;
	private static JTextField textField;

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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 158);
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
				
				setVisible(false);
			}
		});
		btnNewButton.setBounds(324, 91, 100, 25);
		contentPane.add(btnNewButton);

		JLabel lblmp = new JLabel(".mp3");
		lblmp.setBounds(254, 55, 184, 25);
		contentPane.add(lblmp);

		JLabel lblSavedToWorking = new JLabel("File will be saved to working directory");
		lblSavedToWorking.setBounds(25, 92, 413, 15);
		contentPane.add(lblSavedToWorking);
	}

	public static String getTextInput() {
		return textField.getText();
	}
}
