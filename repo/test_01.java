import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.Vector;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Font;

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
	
	public static test_01 window;
	public DefaultTableModel model;
	
	String barcode;
	private JTextField textField_3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Connection branchConn = DBConnection.getBranchConnection();
		        Connection mainConn = DBConnection.getMainConnection();
		        SharedGlobalWithUI.workingStaffNo = 2;
		        SharedGlobalWithUI.customRageCd = "CD1003";
		        SharedGlobalWithUI.customSex = "M";
				
				try {
					test_01 frame = test_01.getInstance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static test_01 getInstance() {
		if(window == null)
			window = new test_01();
		return window;
	}
	/**
	 * Create the frame.
	 */
	private test_01() {
		setResizable(false);
		setTitle("UOS25");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1486, 893);
		Dimension frameSize = this.getSize(); // 프레임 사이즈
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 사이즈
		setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
		
		//결제팝업창
		Dialog_payment dialog = new Dialog_payment();
		
		
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
		
		/*JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(59, 55, 810, 543);
		screen_sell.add(scrollPane);*/
		
		//유동적인 테이블만들기!!
		//Vector<String> userColumn = new Vector<String>();
		//Vector<String> userRow;
		//열 추가
		/*userColumn.addElement("\uBB3C\uD488\uBC88\uD638");
		userColumn.addElement("\uC0C1\uD488\uBA85");
		userColumn.addElement("\uB2E8\uAC00");
		userColumn.addElement("\uC218\uB7C9");
		userColumn.addElement("\uAE08\uC561");*/
		String header[] = {"물품번호","이름","단가","수량","금액"};
		String contents[][] = {
				//{null,null,null,null,null}
		};
		//테이블 모델 생성
		model = new DefaultTableModel(contents, header);
		JTable userTable = new JTable(model); //테이블 생성
		userTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable jtable = (JTable)e.getSource();
				int row, col;
				//한 번 클릭했을 떄
				if(e.getButton() == 1) {
					row = jtable.getSelectedRow();
					col = jtable.getSelectedColumn();
					DefaultTableModel model2 = (DefaultTableModel)jtable.getModel();
	                
	                System.out.println(model2.getValueAt(row, 0));   // 눌려진 행의 부분에서 0번째 값을 출력 
	                System.out.println(model2.getValueAt(row, col));   // 눌려진 행과 열에 해당하는 선택된 데이터 하나 출력
				}
				//더블클릭 했을 때
				if(e.getClickCount() == 2) {
					
				}
			}
		});
		//스크롤판
		JScrollPane scrollPane = new JScrollPane(userTable);
		scrollPane.setBounds(59, 55, 810, 543);
		screen_sell.add(scrollPane);
		
		
		/*String inputStr[] = new String[5];
		inputStr[0] = "11";
		inputStr[1] = "22";
		inputStr[2] = "33";
		inputStr[3] = "44";
		inputStr[4] = "55";*/
		
		/*userRow= new Vector<String>();//내용추가 (행추가)      
        userRow.addElement("1");//1          
        userRow.addElement("2");//2
        userRow.addElement("3");//3
        userRow.addElement("4");//4
        userRow.addElement("5");//5*/
        
        
        //model.addRow(inputStr);
        //System.out.println(userTable.getRowCount() +", "+ userTable.getColumnCount());
        
        
		/*table_item = new JTable();
		scrollPane.setViewportView(table_item);*/
		
		/*table_item.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"\uBB3C\uD488\uBC88\uD638", "\uC0C1\uD488\uBA85", "\uB2E8\uAC00", "\uC218\uB7C9", "\uAE08\uC561"
			}
		));*/
        //바코드 검색 버튼
      	JButton btnNewButton_11 = new JButton("검색");
      	btnNewButton_11.addMouseListener(new MouseAdapter() {
      		@Override
      		public void mouseClicked(MouseEvent e) {
      			barcode = textField_2.getText(); //위쪽에 선언됨
      			try {
					Utilities.AddDisplayItem(barcode); //바코드를 입력받으면 실행하는 함수
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
      			SharedGlobalWithUI.Invalidate(); //바코드를 검색하면 테이블 갱신
      		}
      	});
      	btnNewButton_11.setBounds(1150, 51, 71, 23);
      	screen_sell.add(btnNewButton_11);
      	
      	//바코드검색 삭제버튼
      	JButton btnNewButton_15 = new JButton("삭제");
      	btnNewButton_15.addActionListener(new ActionListener() {
      		public void actionPerformed(ActionEvent arg0) {
      			if(userTable.getSelectedRow() == -1) {
      				return;
      			}
      			else {
      				model.removeRow(userTable.getSelectedRow());
      				//map에서 아이템 삭제하는 함수 넣자
      			}
      			
      		}
      	});
      	btnNewButton_15.setBounds(787, 16, 82, 27);
      	screen_sell.add(btnNewButton_15);
      	
      	
      		
		JLabel label_2 = new JLabel("바코드:");
		label_2.setBounds(909, 55, 57, 15);
		screen_sell.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(978, 52, 156, 21);
		screen_sell.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btn_payment = new JButton("\uACB0\uC81C");
		btn_payment.setBounds(909, 118, 173, 78);
		btn_payment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dialog.setVisible(true);
				
			}
		});
		screen_sell.add(btn_payment);
		
		JLabel lblNewLabel = new JLabel("합계");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel.setBounds(59, 635, 64, 38);
		screen_sell.add(lblNewLabel);
		
		textField_3 = new JTextField();
		textField_3.setBounds(135, 635, 134, 38);
		screen_sell.add(textField_3);
		textField_3.setColumns(10);
		
		JPanel screen_refund = new JPanel();
		screen_refund.setBounds(0, 0, 1249, 768);
		mainPage.add(screen_refund);
		screen_refund.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("판매내역");
		lblNewLabel_4.setBounds(62, 34, 57, 15);
		screen_refund.add(lblNewLabel_4);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(59, 79, 740, 483);
		screen_refund.add(scrollPane_2);
		
		
		
		table_sell = new JTable();
		table_sell.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Object[] tempList = new Object[table_sell.getColumnCount()];
				//한 번 클릭했을 때
				if(arg0.getButton() == 1) {
					//tempList에 하나의 행을 담는다
					for(int i=0; i<table_sell.getColumnCount(); i++) {
						tempList[i] = table_sell.getValueAt(table_sell.getSelectedRow(),i);
						System.out.println(tempList[i]);			
					}
					//키만 넘겨준다
					int sellno = Integer.valueOf((String) tempList[0]);
					//Refund(sellno);
				}
				
			}
		});
		table_sell.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "2", "3", "4", "5", "6", "7"},
				{"11", "22", "33", "44", "55", "66", "77"},
			},
			new String[] {
				"\uD310\uB9E4\uBC88\uD638", "\uD569\uACC4", "\uBD80\uAC00\uC138", "\uC9C1\uC6D0\uBC88\uD638", "\uD310\uB9E4\uC77C\uC2DC", "\uCD9C\uACE0\uBC88\uD638", "\uACE0\uAC1D\uC131\uBCC4"
			}
		));
		
		scrollPane_2.setViewportView(table_sell);
		
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
		//�ǸŸ���Ȯ��
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
		//������Ȯ��
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
		//���ͱ�Ȯ��
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
		//����Ȯ��
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
		//�Ǹű��Ȯ��
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
		//��Ÿ�԰��߰�
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
		//������
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
		//�սǳ�����
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
		//�԰���Ȯ��
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
		//�����Ȯ��
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
		menuPage_2.setBounds(1250, 0, 232, 682);
		mainPage.add(menuPage_2);
		menuPage_2.setLayout(null);
		//�Ǹ�
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
		//ȯ��
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
		//��ǰ����
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
		btnNewButton_16.setBounds(0, 184, 232, 91);
		menuPage_2.add(btnNewButton_16);
		//����ǰ����
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
		btnNewButton_17.setBounds(0, 276, 232, 101);
		menuPage_2.add(btnNewButton_17);
		//��������
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
		btnNewButton_18.setBounds(0, 378, 232, 101);
		menuPage_2.add(btnNewButton_18);
		//�޿�����
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
		btnNewButton_19.setBounds(0, 479, 232, 101);
		menuPage_2.add(btnNewButton_19);
		//����ٰ���
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
		btnNewButton_20.setBounds(0, 580, 232, 101);
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
		
		mainPage.setVisible(false);
		
		buttonLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainPage.setVisible(true);
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
				loginPage.setVisible(false);
				
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
