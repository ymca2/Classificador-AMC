package ist.amc.window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ist.amc.classifier.Classifier;
import ist.amc.dataset.DataSet;

import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

public class InterfaceClassifier extends JFrame {

	private static final long serialVersionUID = -9090701579562557461L;
	private JPanel contentPane;
	private JTextField StringReciver;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//InterfaceClassifier frame = new InterfaceClassifier();
					 //frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	


	public InterfaceClassifier(Classifier classifier, String fileName) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(500,255);
		setLocation(100,350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel title = new JLabel("Classifier AMC 2022/2023");
		title.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		title.setBorder(new EmptyBorder(10, 0, 5, 0));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.insets = new Insets(0, 0, 5, 0);
		gbc_title.gridx = 1;
		gbc_title.gridy = 0;
		contentPane.add(title, gbc_title);
		
		JLabel dataUsedToClassify = new JLabel("Data: "+fileName);
		dataUsedToClassify.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		dataUsedToClassify.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_dataUsedToClassify = new GridBagConstraints();
		gbc_dataUsedToClassify.anchor = GridBagConstraints.WEST;
		gbc_dataUsedToClassify.insets = new Insets(0, 0, 5, 0);
		gbc_dataUsedToClassify.gridx = 1;
		gbc_dataUsedToClassify.gridy = 4;
		
		contentPane.add(dataUsedToClassify, gbc_dataUsedToClassify);
		
		JLabel titleString = new JLabel("String with unknown Class:");
		titleString.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_titleString = new GridBagConstraints();
		gbc_titleString.anchor = GridBagConstraints.WEST;
		gbc_titleString.insets = new Insets(0, 0, 5, 0);
		gbc_titleString.gridx = 1;
		gbc_titleString.gridy = 5;
		contentPane.add(titleString, gbc_titleString);
		
		StringReciver = new JTextField();
		StringReciver.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		StringReciver.setText("Type data separeted by comas");
		GridBagConstraints gbc_StringReciver = new GridBagConstraints();
		gbc_StringReciver.fill = GridBagConstraints.HORIZONTAL;
		gbc_StringReciver.insets = new Insets(0, 0, 5, 0);
		gbc_StringReciver.gridx = 1;
		gbc_StringReciver.gridy = 6;
		contentPane.add(StringReciver, gbc_StringReciver);
		StringReciver.setColumns(10);
		
		
		JLabel justText = new JLabel("\nClass to which the individual is most likely to belong:");
		justText.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_justText = new GridBagConstraints();
		gbc_justText.anchor = GridBagConstraints.WEST;
		gbc_justText.insets = new Insets(0, 0, 5, 0);
		gbc_justText.gridx = 1;
		gbc_justText.gridy = 10;
		contentPane.add(justText, gbc_justText);
		
		JLabel resultOfClassification = new JLabel("-");
		resultOfClassification.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		resultOfClassification.setBorder(new EmptyBorder(10, 0, 0, 0));
		GridBagConstraints gbc_resultOfClassification = new GridBagConstraints();
		gbc_resultOfClassification.anchor = GridBagConstraints.SOUTH;
		gbc_resultOfClassification.gridx = 1;
		gbc_resultOfClassification.gridy = 11;
		contentPane.add(resultOfClassification, gbc_resultOfClassification);
		
		JButton classifierFinal = new JButton("Classify (based in stored data)");
		classifierFinal.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		classifierFinal.setBackground(new Color(255, 0, 0));
		classifierFinal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String file = (StringReciver.getText());
				int newClass = classifier.getClassifier(DataSet.convert(file));
				resultOfClassification.setText(Integer.toString(newClass));
				resultOfClassification.setFont(new Font("Times New Roman", Font.PLAIN, 20));
				resultOfClassification.setForeground(Color.BLUE);
				
				
			}
		});
		
		GridBagConstraints gbc_classifierFinal = new GridBagConstraints();
		gbc_classifierFinal.fill = GridBagConstraints.HORIZONTAL;
		gbc_classifierFinal.insets = new Insets(0, 0, 5, 0);
		gbc_classifierFinal.gridx = 1;
		gbc_classifierFinal.gridy = 8;
		contentPane.add(classifierFinal, gbc_classifierFinal);
	}
	

}

