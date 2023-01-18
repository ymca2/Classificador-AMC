package ist.amc.window;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ist.amc.classifier.Classifier;
import ist.amc.dataset.DataSet;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.SwingConstants;
import java.awt.Font;

public class DataReciever extends JFrame {

	private static final long serialVersionUID = 146619431690044161L;
	private JPanel contentPane;
	private static JTextField nameOfTheFile;
	private JTextField announcesThatLearnigEnded;
	private static int columnSize;
	
	

	public static int getColumnSize() {
		return DataReciever.columnSize;
	}

	public static void setColumnSize(int columnSize) {
		DataReciever.columnSize = columnSize;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataReciever frame = new DataReciever();
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
	
	public DataReciever() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(200, 100, 450, 300);
		setSize(500,210);
		setLocation(100,100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel titleOfDataReciever = new JLabel("Classifier AMC 2022/2023");
		titleOfDataReciever.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		titleOfDataReciever.setBorder(new EmptyBorder(10, 0, 5, 0));
		GridBagConstraints gbc_titleOfDataReciever = new GridBagConstraints();
		gbc_titleOfDataReciever.insets = new Insets(0, 0, 5, 0);
		gbc_titleOfDataReciever.gridx = 0;
		gbc_titleOfDataReciever.gridy = 1;
		contentPane.add(titleOfDataReciever, gbc_titleOfDataReciever);
		
		JLabel text1 = new JLabel("Insert path of File in wich the \"Learning\" will be based");
		text1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_text1 = new GridBagConstraints();
		gbc_text1.anchor = GridBagConstraints.WEST;
		gbc_text1.insets = new Insets(0, 0, 5, 0);
		gbc_text1.gridx = 0;
		gbc_text1.gridy = 2;
		contentPane.add(text1, gbc_text1);
		
		
		
		JButton buttonOfLearning = new JButton("Learn");
		buttonOfLearning.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		buttonOfLearning.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!DataReciever.doesFileExists(nameOfTheFile.getText())) {
				    JOptionPane.showMessageDialog(null,"File Does Not Exist");
				}
				else {
			    announcesThatLearnigEnded.setText(("Loading"));
				Classifier classifier = sendClassifier();
				announcesThatLearnigEnded.setText(("Done"));
				InterfaceClassifier frame = new InterfaceClassifier(classifier, nameOfTheFile.getText(),DataReciever.getColumnSize(), (JFrame) JOptionPane.getFrameForComponent(buttonOfLearning) );
				frame.setVisible(true);
				
				}
			}
			
		});
		nameOfTheFile = new JTextField();
		nameOfTheFile.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_nameOfTheFile = new GridBagConstraints();
		gbc_nameOfTheFile.insets = new Insets(0, 0, 5, 0);
		gbc_nameOfTheFile.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameOfTheFile.gridx = 0;
		gbc_nameOfTheFile.gridy = 3;
		contentPane.add(nameOfTheFile, gbc_nameOfTheFile);
		nameOfTheFile.setColumns(10);
		GridBagConstraints gbc_buttonOfLearning = new GridBagConstraints();
		gbc_buttonOfLearning.insets = new Insets(0, 0, 5, 0);
		gbc_buttonOfLearning.gridx = 0;
		gbc_buttonOfLearning.gridy = 4;
		contentPane.add(buttonOfLearning, gbc_buttonOfLearning);
		
		announcesThatLearnigEnded = new JTextField();
		announcesThatLearnigEnded.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		announcesThatLearnigEnded.setHorizontalAlignment(SwingConstants.CENTER);
		announcesThatLearnigEnded.setText("-");
		GridBagConstraints gbc_announcesThatLearnigEnded = new GridBagConstraints();
		gbc_announcesThatLearnigEnded.gridx = 0;
		gbc_announcesThatLearnigEnded.gridy = 5;
		contentPane.add(announcesThatLearnigEnded, gbc_announcesThatLearnigEnded);
		announcesThatLearnigEnded.setColumns(10);}
		
		public static Classifier sendClassifier() {
			String filename = (nameOfTheFile.getText());
			DataSet data = DataSet.buildDataSet(filename);
			Classifier classifier = null;
			if (data.getLineNumber()== 0) {
				JOptionPane.showMessageDialog(null, data.getMessage());
			}
			else {
			    classifier = Classifier.buildClassifier(data);
			    DataReciever.setColumnSize(data.getColumnNumber()-1);
			}
			
			return classifier;
	}
		
		public static int StringSize() {
			String filename = (nameOfTheFile.getText());
			DataSet data = DataSet.buildDataSet(filename);
			int dimension = data.getColumnNumber()-1;
			return dimension;
	}
		
		public static boolean doesFileExists(String filePath) {
			File file = new File(filePath);
			if ( file.exists() && !file.isDirectory()) return true;
			return false;
			
		}
		
	

}
