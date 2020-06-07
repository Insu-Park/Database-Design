import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dialog_error extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dialog_error dialog = new Dialog_error("test message");
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Dialog_error(String msg) {
		setTitle("error");
		setBounds(100, 100, 325, 191);
		Dimension frameSize = this.getSize(); // 프레임 사이즈
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 사이즈
		setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
		getContentPane().setLayout(null);
		
		JButton btn_ok = new JButton("OK");
		btn_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_ok.setBounds(122, 107, 63, 23);
		getContentPane().add(btn_ok);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(43, 28, 223, 69);
		textArea.setText(msg);
		getContentPane().add(textArea);

	}
}
