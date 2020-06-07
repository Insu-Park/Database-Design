import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
//결제창 팝업
public class Dialog_payment extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_1;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Dialog_payment dialog = new Dialog_payment();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Dialog_payment() {
		setTitle("결제");
		setBounds(100, 100, 450, 526);
		getContentPane().setLayout(new BorderLayout());
		Dimension frameSize = this.getSize(); // 프레임 사이즈
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 사이즈

		
		setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);

		
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("고객ID");
			lblNewLabel.setBounds(42, 48, 57, 15);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("사용가능 포인트");
			lblNewLabel_1.setBounds(42, 101, 95, 15);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("포인트 사용");
			lblNewLabel_2.setBounds(42, 155, 81, 15);
			contentPanel.add(lblNewLabel_2);
		}
		{
			textField = new JTextField();
			textField.setBounds(165, 45, 116, 21);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			textField_2 = new JTextField();
			textField_2.setBounds(165, 152, 116, 21);
			contentPanel.add(textField_2);
			textField_2.setColumns(10);
		}
		{
			JButton btnNewButton = new JButton("검색");
			btnNewButton.setBounds(304, 44, 65, 23);
			contentPanel.add(btnNewButton);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("현금");
			lblNewLabel_3.setBounds(42, 214, 57, 15);
			contentPanel.add(lblNewLabel_3);
		}
		{
			textField_3 = new JTextField();
			textField_3.setBounds(165, 211, 116, 21);
			contentPanel.add(textField_3);
			textField_3.setColumns(10);
		}
		{
			JButton btnNewButton_1 = new JButton("결제하기");
			btnNewButton_1.setBounds(165, 375, 116, 62);
			contentPanel.add(btnNewButton_1);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("카드");
			lblNewLabel_4.setBounds(42, 268, 57, 15);
			contentPanel.add(lblNewLabel_4);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("합계");
			lblNewLabel_5.setBounds(42, 323, 57, 15);
			contentPanel.add(lblNewLabel_5);
		}
		
		textField_1 = new JTextField();
		textField_1.setBounds(165, 98, 116, 21);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(165, 265, 116, 21);
		contentPanel.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(165, 320, 116, 21);
		contentPanel.add(textField_5);
		textField_5.setColumns(10);
	}
}
