import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class test_01 extends JFrame {

	private JPanel contentpane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton buttonLogin;
	private JPanel mainPage;
	private JPanel screen_sell;
	private JTable table_item;
	private JTable table_sell;
	private JTextField textField_1;
	private JTable table_barcode;
	private JTextField textField_2;
	private JTable table_itemlist;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test_01 frame = new test_01();
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
	public test_01() {
		setResizable(false);
		setTitle("UOS25");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1486, 893);
		contentpane = new JPanel();
		contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentpane);
		contentpane.setLayout(null);
		
		
		mainPage = new JPanel();
		mainPage.setBounds(0, 0, 1482, 865);
		contentpane.add(mainPage);
		mainPage.setLayout(null);
		
		screen_sell = new JPanel();
		screen_sell.setBounds(0, 0, 1249, 768);
		mainPage.add(screen_sell);
		screen_sell.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(59, 55, 810, 378);
		screen_sell.add(scrollPane);
		
		table_item = new JTable();
		scrollPane.setViewportView(table_item);
		table_item.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"\uBB3C\uD488\uBC88\uD638", "\uC0C1\uD488\uBA85", "\uB2E8\uAC00", "\uC218\uB7C9", "\uAE08\uC561"
			}
		));
		
		JLabel label_1 = new JLabel("\uD569\uACC4");
		label_1.setBounds(59, 459, 57, 15);
		screen_sell.add(label_1);
		
		JLabel lblNewLabel_1 = new JLabel("\uD604\uAE08");
		lblNewLabel_1.setBounds(59, 495, 57, 15);
		screen_sell.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uD3EC\uC778\uD2B8");
		lblNewLabel_2.setBounds(59, 532, 57, 15);
		screen_sell.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\uCE74\uB4DC");
		lblNewLabel_3.setBounds(643, 573, 57, 15);
		screen_sell.add(lblNewLabel_3);
		
		JLabel label_2 = new JLabel("\uBC14\uCF54\uB4DC \uAC80\uC0C9:");
		label_2.setBounds(909, 55, 77, 15);
		screen_sell.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(998, 52, 156, 21);
		screen_sell.add(textField_2);
		textField_2.setColumns(10);
		
		JPanel screen_staff = new JPanel();
		screen_staff.setBounds(0, 0, 1249, 768);
		mainPage.add(screen_staff);
		screen_staff.setLayout(null);
		
		JPanel screen_eitem = new JPanel();
		screen_eitem.setBounds(0, 0, 1249, 768);
		mainPage.add(screen_eitem);
		screen_eitem.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("\uD589\uC0AC\uC0C1\uD488\uBAA9\uB85D");
		lblNewLabel_7.setBounds(62, 34, 81, 15);
		screen_eitem.add(lblNewLabel_7);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(59, 79, 580, 452);
		screen_eitem.add(scrollPane_5);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\uBB3C\uD488\uBC88\uD638", "\uC774\uBCA4\uD2B8\uC885\uB958", "\uC774\uBCA4\uD2B8\uC774\uB984", "\uC911\uBCF5\uC801\uC6A9\uAC00\uB2A5\uC5EC\uBD80"
			}
		));
		scrollPane_5.setViewportView(table);
		
		JButton btnNewButton_10 = new JButton("\uCD94\uAC00");
		btnNewButton_10.setBounds(691, 77, 97, 23);
		screen_eitem.add(btnNewButton_10);
		
		JPanel screen_refund = new JPanel();
		screen_refund.setBounds(0, 0, 1249, 768);
		mainPage.add(screen_refund);
		screen_refund.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("\uC8FC\uBB38\uB0B4\uC5ED");
		lblNewLabel_4.setBounds(62, 34, 57, 15);
		screen_refund.add(lblNewLabel_4);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(59, 79, 740, 415);
		screen_refund.add(scrollPane_2);
		
		table_sell = new JTable();
		table_sell.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\uD310\uB9E4\uBC88\uD638", "\uD569\uACC4", "\uBD80\uAC00\uC138", "\uC9C1\uC6D0\uBC88\uD638", "\uD310\uB9E4\uC77C\uC2DC", "\uCD9C\uACE0\uBC88\uD638", "\uACE0\uAC1D\uC131\uBCC4"
			}
		));
		scrollPane_2.setViewportView(table_sell);
		
		JPanel screen_barcode = new JPanel();
		screen_barcode.setBounds(0, 0, 1249, 768);
		mainPage.add(screen_barcode);
		screen_barcode.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("\uBC14\uCF54\uB4DC \uAC80\uC0C9");
		lblNewLabel_5.setBounds(62, 34, 76, 15);
		screen_barcode.add(lblNewLabel_5);
		
		textField_1 = new JTextField();
		textField_1.setBounds(148, 31, 143, 21);
		screen_barcode.add(textField_1);
		textField_1.setColumns(10);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(59, 79, 548, 354);
		screen_barcode.add(scrollPane_3);
		
		table_barcode = new JTable();
		scrollPane_3.setViewportView(table_barcode);
		
		JPanel screen_iteminfo = new JPanel();
		screen_iteminfo.setBounds(0, 0, 1249, 768);
		mainPage.add(screen_iteminfo);
		screen_iteminfo.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("\uC0C1\uD488\uC0C1\uC138\uC815\uBCF4");
		lblNewLabel_6.setBounds(62, 34, 78, 15);
		screen_iteminfo.add(lblNewLabel_6);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(59, 79, 666, 478);
		screen_iteminfo.add(scrollPane_4);
		
		table_itemlist = new JTable();
		table_itemlist.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\uBB3C\uD488\uBC88\uD638", "\uC774\uB984", "\uAC1C\uB2F9\uAC00\uACA9", "\uAD8C\uC7A5\uAC00\uACA9", "\uC720\uD1B5\uAE30\uD55C", "\uCC3D\uACE0\uC218\uB7C9", "\uC9C4\uC5F4\uC218\uB7C9"
			}
		));
		scrollPane_4.setViewportView(table_itemlist);
		
		JPanel screen_pay = new JPanel();
		screen_pay.setBounds(0, 0, 1249, 768);
		mainPage.add(screen_pay);
		screen_pay.setLayout(null);
		
		JPanel screen_work = new JPanel();
		screen_work.setBounds(0, 0, 1249, 768);
		mainPage.add(screen_work);
		screen_work.setLayout(null);
		
		JPanel screen_PMH = new JPanel();
		screen_PMH.setBounds(0, 0, 1249, 768);
		mainPage.add(screen_PMH);
		
		JPanel screen_SSH = new JPanel();
		screen_SSH.setBounds(0, 0, 1249, 768);
		mainPage.add(screen_SSH);
		
		JPanel screen_SIH = new JPanel();
		screen_SIH.setBounds(0, 0, 1249, 768);
		mainPage.add(screen_SIH);
		
		JPanel screen_MH = new JPanel();
		screen_MH.setBounds(0, 0, 1249, 768);
		mainPage.add(screen_MH);
		
		JPanel screen_PKH = new JPanel();
		screen_PKH.setBounds(0, 0, 1249, 768);
		mainPage.add(screen_PKH);
		
		JPanel screen_KIC = new JPanel();
		screen_KIC.setBounds(0, 0, 1249, 768);
		mainPage.add(screen_KIC);
		
		JPanel screen_JP = new JPanel();
		screen_JP.setBounds(0, 0, 1249, 768);
		mainPage.add(screen_JP);
		
		JPanel screen_SNK = new JPanel();
		screen_SNK.setBounds(0, 0, 1249, 768);
		mainPage.add(screen_SNK);
		
		JPanel screen_IKH = new JPanel();
		screen_IKH.setBounds(0, 0, 1249, 768);
		mainPage.add(screen_IKH);
		
		JPanel screen_CKH = new JPanel();
		screen_CKH.setBounds(0, 0, 1249, 768);
		mainPage.add(screen_CKH);
		
		JPanel screen_JK = new JPanel();
		screen_JK.setBounds(0, 0, 1249, 768);
		mainPage.add(screen_JK);
		
		JPanel menuPage_1 = new JPanel();
		menuPage_1.setBounds(0, 769, 1249, 96);
		mainPage.add(menuPage_1);
		menuPage_1.setLayout(null);
		//판매마진확인
		JButton btnNewButton = new JButton("\uD310\uB9E4\uB9C8\uC9C4\uD655\uC778");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				screen_sell.setVisible(false);
				screen_refund.setVisible(false);
				screen_barcode.setVisible(false);
				screen_iteminfo.setVisible(false);
				screen_eitem.setVisible(false);
				screen_staff.setVisible(false);
				screen_pay.setVisible(false);
				screen_work.setVisible(false);
				screen_PMH.setVisible(true);
				screen_SSH.setVisible(false);
				screen_SIH.setVisible(false);
				screen_MH.setVisible(false);
				screen_PKH.setVisible(false);
				screen_KIC.setVisible(false);
				screen_JP.setVisible(false);
				screen_SNK.setVisible(false);
				screen_IKH.setVisible(false);
				screen_CKH.setVisible(false);
				screen_JK.setVisible(false);
			}
		});
		btnNewButton.setBounds(0, 0, 126, 96);
		menuPage_1.add(btnNewButton);
		//수수료확인
		JButton btnNewButton_1 = new JButton("\uC218\uC218\uB8CC\uD655\uC778");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screen_sell.setVisible(false);
				screen_refund.setVisible(false);
				screen_barcode.setVisible(false);
				screen_iteminfo.setVisible(false);
				screen_eitem.setVisible(false);
				screen_staff.setVisible(false);
				screen_pay.setVisible(false);
				screen_work.setVisible(false);
				screen_PMH.setVisible(false);
				screen_SSH.setVisible(true);
				screen_SIH.setVisible(false);
				screen_MH.setVisible(false);
				screen_PKH.setVisible(false);
				screen_KIC.setVisible(false);
				screen_JP.setVisible(false);
				screen_SNK.setVisible(false);
				screen_IKH.setVisible(false);
				screen_CKH.setVisible(false);
				screen_JK.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(125, 0, 126, 96);
		menuPage_1.add(btnNewButton_1);
		//수익금확인
		JButton btnNewButton_2 = new JButton("\uC218\uC775\uAE08\uD655\uC778");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screen_sell.setVisible(false);
				screen_refund.setVisible(false);
				screen_barcode.setVisible(false);
				screen_iteminfo.setVisible(false);
				screen_eitem.setVisible(false);
				screen_staff.setVisible(false);
				screen_pay.setVisible(false);
				screen_work.setVisible(false);
				screen_PMH.setVisible(false);
				screen_SSH.setVisible(false);
				screen_SIH.setVisible(true);
				screen_MH.setVisible(false);
				screen_PKH.setVisible(false);
				screen_KIC.setVisible(false);
				screen_JP.setVisible(false);
				screen_SNK.setVisible(false);
				screen_IKH.setVisible(false);
				screen_CKH.setVisible(false);
				screen_JK.setVisible(false);
			}
		});
		btnNewButton_2.setBounds(250, 0, 126, 96);
		menuPage_1.add(btnNewButton_2);
		//매출확인
		JButton btnNewButton_3 = new JButton("\uB9E4\uCD9C\uD655\uC778");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screen_sell.setVisible(false);
				screen_refund.setVisible(false);
				screen_barcode.setVisible(false);
				screen_iteminfo.setVisible(false);
				screen_eitem.setVisible(false);
				screen_staff.setVisible(false);
				screen_pay.setVisible(false);
				screen_work.setVisible(false);
				screen_PMH.setVisible(false);
				screen_SSH.setVisible(false);
				screen_SIH.setVisible(false);
				screen_MH.setVisible(true);
				screen_PKH.setVisible(false);
				screen_KIC.setVisible(false);
				screen_JP.setVisible(false);
				screen_SNK.setVisible(false);
				screen_IKH.setVisible(false);
				screen_CKH.setVisible(false);
				screen_JK.setVisible(false);
			}
		});
		btnNewButton_3.setBounds(375, 0, 126, 96);
		menuPage_1.add(btnNewButton_3);
		//판매기록확인
		JButton btnNewButton_4 = new JButton("\uD310\uB9E4\uAE30\uB85D\uD655\uC778");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screen_sell.setVisible(false);
				screen_refund.setVisible(false);
				screen_barcode.setVisible(false);
				screen_iteminfo.setVisible(false);
				screen_eitem.setVisible(false);
				screen_staff.setVisible(false);
				screen_pay.setVisible(false);
				screen_work.setVisible(false);
				screen_PMH.setVisible(false);
				screen_SSH.setVisible(false);
				screen_SIH.setVisible(false);
				screen_MH.setVisible(false);
				screen_PKH.setVisible(true);
				screen_KIC.setVisible(false);
				screen_JP.setVisible(false);
				screen_SNK.setVisible(false);
				screen_IKH.setVisible(false);
				screen_CKH.setVisible(false);
				screen_JK.setVisible(false);
			}
		});
		btnNewButton_4.setBounds(500, 0, 126, 96);
		menuPage_1.add(btnNewButton_4);
		//기타입고추가
		JButton btnNewButton_5 = new JButton("\uAE30\uD0C0\uC785\uACE0\uCD94\uAC00");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screen_sell.setVisible(false);
				screen_refund.setVisible(false);
				screen_barcode.setVisible(false);
				screen_iteminfo.setVisible(false);
				screen_eitem.setVisible(false);
				screen_staff.setVisible(false);
				screen_pay.setVisible(false);
				screen_work.setVisible(false);
				screen_PMH.setVisible(false);
				screen_SSH.setVisible(false);
				screen_SIH.setVisible(false);
				screen_MH.setVisible(false);
				screen_PKH.setVisible(false);
				screen_KIC.setVisible(true);
				screen_JP.setVisible(false);
				screen_SNK.setVisible(false);
				screen_IKH.setVisible(false);
				screen_CKH.setVisible(false);
				screen_JK.setVisible(false);
			}
		});
		btnNewButton_5.setBounds(625, 0, 126, 96);
		menuPage_1.add(btnNewButton_5);
		//재고폐기
		JButton btnNewButton_6 = new JButton("\uC7AC\uACE0\uD3D0\uAE30");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screen_sell.setVisible(false);
				screen_refund.setVisible(false);
				screen_barcode.setVisible(false);
				screen_iteminfo.setVisible(false);
				screen_eitem.setVisible(false);
				screen_staff.setVisible(false);
				screen_pay.setVisible(false);
				screen_work.setVisible(false);
				screen_PMH.setVisible(false);
				screen_SSH.setVisible(false);
				screen_SIH.setVisible(false);
				screen_MH.setVisible(false);
				screen_PKH.setVisible(false);
				screen_KIC.setVisible(false);
				screen_JP.setVisible(true);
				screen_SNK.setVisible(false);
				screen_IKH.setVisible(false);
				screen_CKH.setVisible(false);
				screen_JK.setVisible(false);
			}
		});
		btnNewButton_6.setBounds(750, 0, 126, 96);
		menuPage_1.add(btnNewButton_6);
		//손실내용기록
		JButton btnNewButton_7 = new JButton("\uC190\uC2E4\uB0B4\uC6A9\uAE30\uB85D");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screen_sell.setVisible(false);
				screen_refund.setVisible(false);
				screen_barcode.setVisible(false);
				screen_iteminfo.setVisible(false);
				screen_eitem.setVisible(false);
				screen_staff.setVisible(false);
				screen_pay.setVisible(false);
				screen_work.setVisible(false);
				screen_PMH.setVisible(false);
				screen_SSH.setVisible(false);
				screen_SIH.setVisible(false);
				screen_MH.setVisible(false);
				screen_PKH.setVisible(false);
				screen_KIC.setVisible(false);
				screen_JP.setVisible(false);
				screen_SNK.setVisible(true);
				screen_IKH.setVisible(false);
				screen_CKH.setVisible(false);
				screen_JK.setVisible(false);
			}
		});
		btnNewButton_7.setBounds(874, 0, 126, 96);
		menuPage_1.add(btnNewButton_7);
		//입고기록확인
		JButton btnNewButton_8 = new JButton("\uC785\uACE0\uAE30\uB85D\uD655\uC778");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screen_sell.setVisible(false);
				screen_refund.setVisible(false);
				screen_barcode.setVisible(false);
				screen_iteminfo.setVisible(false);
				screen_eitem.setVisible(false);
				screen_staff.setVisible(false);
				screen_pay.setVisible(false);
				screen_work.setVisible(false);
				screen_PMH.setVisible(false);
				screen_SSH.setVisible(false);
				screen_SIH.setVisible(false);
				screen_MH.setVisible(false);
				screen_PKH.setVisible(false);
				screen_KIC.setVisible(false);
				screen_JP.setVisible(false);
				screen_SNK.setVisible(false);
				screen_IKH.setVisible(true);
				screen_CKH.setVisible(false);
				screen_JK.setVisible(false);
			}
		});
		btnNewButton_8.setBounds(999, 0, 126, 96);
		menuPage_1.add(btnNewButton_8);
		//출고기록확인
		JButton btnNewButton_9 = new JButton("\uCD9C\uACE0\uAE30\uB85D\uD655\uC778");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screen_sell.setVisible(false);
				screen_refund.setVisible(false);
				screen_barcode.setVisible(false);
				screen_iteminfo.setVisible(false);
				screen_eitem.setVisible(false);
				screen_staff.setVisible(false);
				screen_pay.setVisible(false);
				screen_work.setVisible(false);
				screen_PMH.setVisible(false);
				screen_SSH.setVisible(false);
				screen_SIH.setVisible(false);
				screen_MH.setVisible(false);
				screen_PKH.setVisible(false);
				screen_KIC.setVisible(false);
				screen_JP.setVisible(false);
				screen_SNK.setVisible(false);
				screen_IKH.setVisible(false);
				screen_CKH.setVisible(true);
				screen_JK.setVisible(false);
			}
		});
		btnNewButton_9.setBounds(1125, 0, 126, 96);
		menuPage_1.add(btnNewButton_9);
		
		JPanel menuPage_2 = new JPanel();
		menuPage_2.setBounds(1250, 0, 232, 768);
		mainPage.add(menuPage_2);
		menuPage_2.setLayout(null);
		//판매
		JButton btnNewButton_12 = new JButton("\uD310\uB9E4");
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screen_sell.setVisible(true);
				screen_refund.setVisible(false);
				screen_barcode.setVisible(false);
				screen_iteminfo.setVisible(false);
				screen_eitem.setVisible(false);
				screen_staff.setVisible(false);
				screen_pay.setVisible(false);
				screen_work.setVisible(false);
				screen_PMH.setVisible(false);
				screen_SSH.setVisible(false);
				screen_SIH.setVisible(false);
				screen_MH.setVisible(false);
				screen_PKH.setVisible(false);
				screen_KIC.setVisible(false);
				screen_JP.setVisible(false);
				screen_SNK.setVisible(false);
				screen_IKH.setVisible(false);
				screen_CKH.setVisible(false);
				screen_JK.setVisible(false);
			}
		});
		btnNewButton_12.setBounds(0, 0, 232, 91);
		menuPage_2.add(btnNewButton_12);
		//환불
		JButton btnNewButton_13 = new JButton("\uD658\uBD88");
		btnNewButton_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screen_sell.setVisible(false);
				screen_refund.setVisible(true);
				screen_barcode.setVisible(false);
				screen_iteminfo.setVisible(false);
				screen_eitem.setVisible(false);
				screen_staff.setVisible(false);
				screen_pay.setVisible(false);
				screen_work.setVisible(false);
				screen_PMH.setVisible(false);
				screen_SSH.setVisible(false);
				screen_SIH.setVisible(false);
				screen_MH.setVisible(false);
				screen_PKH.setVisible(false);
				screen_KIC.setVisible(false);
				screen_JP.setVisible(false);
				screen_SNK.setVisible(false);
				screen_IKH.setVisible(false);
				screen_CKH.setVisible(false);
				screen_JK.setVisible(false);
			}
		});
		btnNewButton_13.setBounds(0, 92, 232, 91);
		menuPage_2.add(btnNewButton_13);
		//바코드확인
		JButton btnNewButton_14 = new JButton("\uBC14\uCF54\uB4DC\uD655\uC778");
		btnNewButton_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screen_sell.setVisible(false);
				screen_refund.setVisible(false);
				screen_barcode.setVisible(true);
				screen_iteminfo.setVisible(false);
				screen_eitem.setVisible(false);
				screen_staff.setVisible(false);
				screen_pay.setVisible(false);
				screen_work.setVisible(false);
				screen_PMH.setVisible(false);
				screen_SSH.setVisible(false);
				screen_SIH.setVisible(false);
				screen_MH.setVisible(false);
				screen_PKH.setVisible(false);
				screen_KIC.setVisible(false);
				screen_JP.setVisible(false);
				screen_SNK.setVisible(false);
				screen_IKH.setVisible(false);
				screen_CKH.setVisible(false);
				screen_JK.setVisible(false);
			}
		});
		btnNewButton_14.setBounds(0, 182, 232, 91);
		menuPage_2.add(btnNewButton_14);
		//상품정보
		JButton btnNewButton_16 = new JButton("\uC0C1\uD488\uC815\uBCF4");
		btnNewButton_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screen_sell.setVisible(false);
				screen_refund.setVisible(false);
				screen_barcode.setVisible(false);
				screen_iteminfo.setVisible(true);
				screen_eitem.setVisible(false);
				screen_staff.setVisible(false);
				screen_pay.setVisible(false);
				screen_work.setVisible(false);
				screen_PMH.setVisible(false);
				screen_SSH.setVisible(false);
				screen_SIH.setVisible(false);
				screen_MH.setVisible(false);
				screen_PKH.setVisible(false);
				screen_KIC.setVisible(false);
				screen_JP.setVisible(false);
				screen_SNK.setVisible(false);
				screen_IKH.setVisible(false);
				screen_CKH.setVisible(false);
				screen_JK.setVisible(false);
			}
		});
		btnNewButton_16.setBounds(0, 274, 232, 91);
		menuPage_2.add(btnNewButton_16);
		//행사상품관리
		JButton btnNewButton_17 = new JButton("\uD589\uC0AC\uC0C1\uD488\uAD00\uB9AC");
		btnNewButton_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screen_sell.setVisible(false);
				screen_refund.setVisible(false);
				screen_barcode.setVisible(false);
				screen_iteminfo.setVisible(false);
				screen_eitem.setVisible(true);
				screen_staff.setVisible(false);
				screen_pay.setVisible(false);
				screen_work.setVisible(false);
				screen_PMH.setVisible(false);
				screen_SSH.setVisible(false);
				screen_SIH.setVisible(false);
				screen_MH.setVisible(false);
				screen_PKH.setVisible(false);
				screen_KIC.setVisible(false);
				screen_JP.setVisible(false);
				screen_SNK.setVisible(false);
				screen_IKH.setVisible(false);
				screen_CKH.setVisible(false);
				screen_JK.setVisible(false);
			}
		});
		btnNewButton_17.setBounds(0, 366, 232, 101);
		menuPage_2.add(btnNewButton_17);
		//직원관리
		JButton btnNewButton_18 = new JButton("\uC9C1\uC6D0\uAD00\uB9AC");
		btnNewButton_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screen_sell.setVisible(false);
				screen_refund.setVisible(false);
				screen_barcode.setVisible(false);
				screen_iteminfo.setVisible(false);
				screen_eitem.setVisible(false);
				screen_staff.setVisible(true);
				screen_pay.setVisible(false);
				screen_work.setVisible(false);
				screen_PMH.setVisible(false);
				screen_SSH.setVisible(false);
				screen_SIH.setVisible(false);
				screen_MH.setVisible(false);
				screen_PKH.setVisible(false);
				screen_KIC.setVisible(false);
				screen_JP.setVisible(false);
				screen_SNK.setVisible(false);
				screen_IKH.setVisible(false);
				screen_CKH.setVisible(false);
				screen_JK.setVisible(false);
			}
		});
		btnNewButton_18.setBounds(0, 466, 232, 101);
		menuPage_2.add(btnNewButton_18);
		//급여관리
		JButton btnNewButton_19 = new JButton("\uAE09\uC5EC\uAD00\uB9AC");
		btnNewButton_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screen_sell.setVisible(false);
				screen_refund.setVisible(false);
				screen_barcode.setVisible(false);
				screen_iteminfo.setVisible(false);
				screen_eitem.setVisible(false);
				screen_staff.setVisible(false);
				screen_pay.setVisible(true);
				screen_work.setVisible(false);
				screen_PMH.setVisible(false);
				screen_SSH.setVisible(false);
				screen_SIH.setVisible(false);
				screen_MH.setVisible(false);
				screen_PKH.setVisible(false);
				screen_KIC.setVisible(false);
				screen_JP.setVisible(false);
				screen_SNK.setVisible(false);
				screen_IKH.setVisible(false);
				screen_CKH.setVisible(false);
				screen_JK.setVisible(false);
			}
		});
		btnNewButton_19.setBounds(0, 564, 232, 101);
		menuPage_2.add(btnNewButton_19);
		//출퇴근관리
		JButton btnNewButton_20 = new JButton("\uCD9C\uD1F4\uADFC\uAD00\uB9AC");
		btnNewButton_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screen_sell.setVisible(false);
				screen_refund.setVisible(false);
				screen_barcode.setVisible(false);
				screen_iteminfo.setVisible(false);
				screen_eitem.setVisible(false);
				screen_staff.setVisible(false);
				screen_pay.setVisible(false);
				screen_work.setVisible(true);
				screen_PMH.setVisible(false);
				screen_SSH.setVisible(false);
				screen_SIH.setVisible(false);
				screen_MH.setVisible(false);
				screen_PKH.setVisible(false);
				screen_KIC.setVisible(false);
				screen_JP.setVisible(false);
				screen_SNK.setVisible(false);
				screen_IKH.setVisible(false);
				screen_CKH.setVisible(false);
				screen_JK.setVisible(false);
			}
		});
		btnNewButton_20.setBounds(0, 666, 232, 101);
		menuPage_2.add(btnNewButton_20);
		
		JPanel loginPage = new JPanel();
		loginPage.setBounds(0, 0, 1482, 865);
		contentpane.add(loginPage);
		loginPage.setLayout(null);
		
		JLabel lblPw = new JLabel("PW");
		lblPw.setBounds(608, 236, 34, 18);
		loginPage.add(lblPw);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(608, 182, 34, 18);
		loginPage.add(lblId);
		
		textField = new JTextField();
		textField.setBounds(671, 179, 162, 24);
		loginPage.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(671, 233, 162, 24);
		loginPage.add(passwordField);
		
		buttonLogin = new JButton("\uB85C\uADF8\uC778");
		buttonLogin.setBounds(671, 315, 105, 27);
		loginPage.add(buttonLogin);
		
		mainPage.setVisible(false); //처음에 안보이도록
		
		buttonLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainPage.setVisible(true);
				loginPage.setVisible(false);
				// 로그인버튼 누를 때 일어나는 것
			}
		});
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
