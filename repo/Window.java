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
import java.sql.SQLException;
import java.util.*;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;

public class Window extends JFrame {

	public JPanel contentpane;
	public JPanel mainPage;
	public JPanel screen_sell;
	public JTable table_item;
	public JTable table_sell;
	public JTextField textField_1;
	public JTable table_barcode;
	public JTextField txt_barcodeinput;
	
	public static Window window;
	public static DefaultTableModel model, model2, model3;

	
	
	String barcode;
	public JTextField txt_sum;
	public JTextField textField_4;
	public JTextField textField_5;
	public JTextField txt_staffno;
	public JTextField txt_staffname;
	public JTextField txt_workingstaffno;
	public JTextField txt_workingstaffname;
	public JTextField txt_etc;
	public JTextField txt_cash;
	public JTextField txt_customerinput;
	public JTextField txt_charge;
	public JTextField txt_etcinfo;
	private JTextField txt_etcinput;
	private JTextField txt_cashinput;
	private JTextField txt_payinfo;
	
	public static void Invalidate(){
	      Map<String, Item> displayItems = new HashMap<String, Item>();

	      // 유통기한 다른 아이템 합쳐서 local.displayItems 에 저장
	      for(Map.Entry<String, Item> entry : SharedGlobalWithUI.displayItems.entrySet()){
	         String barcode = entry.getKey();
	         Item item = entry.getValue();

	         if(displayItems.containsKey(item.itemNo)){
	            Item tmp = displayItems.get(item.itemNo);
	            tmp.qty += item.qty;
	         }else{
	            displayItems.put(new String(item.itemNo), new Item(item));
	         }
	      }

	      String[] inputStrs = new String[5];

	      model.setNumRows(0);

	      // display
	      for(Map.Entry<String, Item> entry: displayItems.entrySet()){
	         Item item = entry.getValue();
	         inputStrs[0] = item.itemNo;
	         inputStrs[1] = item.itemName;
	         inputStrs[2] = Integer.toString(item.price);
	         inputStrs[3] = Integer.toString(item.qty);
	         inputStrs[4] = Integer.toString(item.price*item.qty);

	         model.addRow(inputStrs);
	      }
	   }
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
//				Connection branchConn = DBConnection.getBranchConnection();
//		        Connection mainConn = DBConnection.getMainConnection();
//		        SharedGlobalWithUI.workingStaffNo = -1;
//		        SharedGlobalWithUI.customRageCd = null;
//		        SharedGlobalWithUI.customSex = null;
				
		        try {
		               Window frame = Window.getInstance();
		               frame.setVisible(true);
		            }
		            catch (Exception e) {
		               System.out.println(e.getMessage());
		               e.printStackTrace();
		            }
			}
		});
	}
	
	public static Window getInstance(){
		if(window == null)
			window = new Window();
		return window;
	}
	/**
	 * Create the frame.
	 */
	public Window(){
		setResizable(false);
		setTitle("UOS25");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1486, 893);
		//화면 가운데 정렬 
		Dimension frameSize = this.getSize(); // 프레임 사이즈
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 사이즈
		setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
		
		//model
		String header[] = {"물품번호","이름","단가","수량","금액"}; //model
		String contents[][] = {};
		model = new DefaultTableModel(contents, header);
		//model2
		String header2[] = {"바코드","이름","창고추가수량","진열추가수량"}; //model2
		String contents2[][] = {};	
		model2 = new DefaultTableModel(contents2, header2);
		//model3
		String header3[] = {"물품번호","유통기한","창고수량","진열수량","가격"};
		String contents3[][] = {};		
		model3 = new DefaultTableModel(contents3, header3);
		
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
		mainPage.setVisible(true);
		
		screen_sell = new JPanel();
		screen_sell.setBounds(0, 0, 1249, 768);
		mainPage.add(screen_sell);
		screen_sell.setLayout(null);
		
		
		JTable userTable = new JTable(model); //테이블 생성 model
		userTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable jtable = (JTable)e.getSource();
				int row, col;
				//한 번 클릭했을 떄
				if(e.getButton() == 1) {
					
				}
				//더블클릭 했을 때
				if(e.getClickCount() == 2) {
					
				}
			}
		});
		//스크롤판
		JScrollPane scrollPane = new JScrollPane(userTable);
		scrollPane.setBounds(59, 55, 810, 479);
		screen_sell.add(scrollPane);
		
		//바코드 검색 버튼
      	JButton btn_barcodesearch = new JButton("검색");
      	btn_barcodesearch.addActionListener(new ActionListener() {
      		public void actionPerformed(ActionEvent e) {
      			int sum = 0;
      			//판매테이블 상에 있는 모든 물건의 금액을 합친 금액
      			for(int i=0; i<model.getRowCount(); i++) {
      				sum = sum + Integer.valueOf((String) model.getValueAt(i, model.getColumnCount()-1));
      			}
      		}
      	});
      	btn_barcodesearch.addMouseListener(new MouseAdapter() {
      		@Override
            public void mouseClicked(MouseEvent e) {
               barcode = txt_barcodeinput.getText(); //위쪽에 선언됨
            if(barcode.isEmpty())
               return;
            System.out.println("Barcode: " + barcode);
               try {
               Utilities.AddDisplayItem(barcode); //바코드를 입력받으면 실행하는 함수
            } catch (Exception e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
            }

      	});
      	btn_barcodesearch.setBounds(1150, 51, 71, 23);
      	screen_sell.add(btn_barcodesearch);
      	
      //바코드검색 삭제버튼
        JButton btnNewButton_15 = new JButton("삭제");
        btnNewButton_15.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent arg0) {
              if(userTable.getSelectedRow() == -1) {
                 return;
              }
              else {
              String selectedItemNo = (String)userTable.getValueAt(userTable.getSelectedRow(), 0);
              List<String> barcodes = new ArrayList<String>();
              for(Map.Entry<String, Item> entry: SharedGlobalWithUI.displayItems.entrySet()){
                 if(entry.getValue().itemNo.compareTo(selectedItemNo) == 0){
                    barcodes.add(entry.getKey());
                 }
              }
              Iterator<String> iter = barcodes.iterator();
              while(iter.hasNext()) {
                 String barcode = iter.next();
                 SharedGlobalWithUI.displayItems.remove(barcode);
                 System.out.println(barcode + " displayItems 삭제");
              }

              model.removeRow(userTable.getSelectedRow());
              }
              
           }
        });
        btnNewButton_15.setBounds(767, 16, 102, 27);
        screen_sell.add(btnNewButton_15);
        
        
        	
		JLabel label_2 = new JLabel("바코드:");
		label_2.setBounds(909, 55, 57, 15);
		screen_sell.add(label_2);
		
		txt_barcodeinput = new JTextField();
		txt_barcodeinput.setBounds(1000, 52, 134, 21);
		screen_sell.add(txt_barcodeinput);
		txt_barcodeinput.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("합계:");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setBounds(59, 544, 91, 38);
		screen_sell.add(lblNewLabel);
		
		//판매테이블 전체삭제버튼
	      JButton btnNewButton_23 = new JButton("전체삭제");
	      btnNewButton_23.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            Utilities.ClearDisplayItems();
	            model.setNumRows(0);
	         }
	      });
		btnNewButton_23.setBounds(642, 16, 102, 27);
		screen_sell.add(btnNewButton_23);
		
		//나이 콤보박스
		JComboBox combo_age = new JComboBox();
		combo_age.setModel(new DefaultComboBoxModel(new String[] {"10세 미만", "10~19세", "20~29세", "30~39세", "40~49세", "50~59세", "60~69세", "70세 이상"}));
		combo_age.setBounds(1012, 139, 78, 21);
		screen_sell.add(combo_age);
		//성별 콤보박스
		JComboBox combo_sex = new JComboBox();
		combo_sex.setModel(new DefaultComboBoxModel(new String[] {"남자", "여자"}));
		combo_sex.setBounds(909, 139, 91, 21);
		screen_sell.add(combo_sex);
		
		JLabel label = new JLabel("기타결제:");
		label.setFont(new Font("굴림", Font.PLAIN, 15));
		label.setBounds(59, 592, 91, 38);
		screen_sell.add(label);
		
		//판매금액 합계
		txt_sum = new JTextField();
		txt_sum.setEditable(false);
		txt_sum.setBounds(162, 552, 134, 23);
		screen_sell.add(txt_sum);
		txt_sum.setColumns(10);
		
		
		//기타결제금액
		txt_etc = new JTextField();
		txt_etc.setEditable(false);
		txt_etc.setColumns(10);
		txt_etc.setBounds(162, 600, 134, 23);
		screen_sell.add(txt_etc);
		
		JLabel label_1 = new JLabel("거스름돈:");
		label_1.setFont(new Font("굴림", Font.PLAIN, 15));
		label_1.setBounds(59, 688, 91, 38);
		screen_sell.add(label_1);
		//현금결제금액
		txt_cash = new JTextField();
		txt_cash.setEditable(false);
		txt_cash.setColumns(10);
		txt_cash.setBounds(162, 648, 134, 23);
		screen_sell.add(txt_cash);
		
		JLabel lblNewLabel_9 = new JLabel("고객ID:");
		lblNewLabel_9.setBounds(909, 103, 57, 15);
		screen_sell.add(lblNewLabel_9);
		
		txt_customerinput = new JTextField();
		txt_customerinput.setBounds(1000, 100, 134, 21);
		screen_sell.add(txt_customerinput);
		txt_customerinput.setColumns(10);
		
		JButton btn_idsearch = new JButton("검색");
		btn_idsearch.setBounds(1150, 99, 71, 23);
		screen_sell.add(btn_idsearch);
		
		JLabel label_3 = new JLabel("현금:");
		label_3.setFont(new Font("굴림", Font.PLAIN, 15));
		label_3.setBounds(59, 640, 91, 38);
		screen_sell.add(label_3);
		//거스름돈
		txt_charge = new JTextField();
		txt_charge.setEditable(false);
		txt_charge.setColumns(10);
		txt_charge.setBounds(162, 696, 134, 23);
		screen_sell.add(txt_charge);
		//기타결제
		txt_etcinfo = new JTextField();
		txt_etcinfo.setEditable(false);
		txt_etcinfo.setColumns(10);
		txt_etcinfo.setBounds(319, 600, 102, 23);
		screen_sell.add(txt_etcinfo);
		//결제정보입력창
		txt_payinfo = new JTextField();
		txt_payinfo.setColumns(10);
		txt_payinfo.setBounds(1000, 242, 134, 21);
		screen_sell.add(txt_payinfo);
		//결제정보입력버튼
		JButton btn_payinfoenter = new JButton("입력");
		btn_payinfoenter.setBounds(1150, 241, 71, 23);
		screen_sell.add(btn_payinfoenter);
		//최종결제버튼
		JButton btn_payment = new JButton("결제");
		btn_payment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 char sex = (char)combo_sex.getSelectedItem(); //성별
				 String age = (String)combo_age.getSelectedItem(); //나이대
				 String payinfo = txt_payinfo.getText(); //결제정보
				 String moneysum = txt_sum.getText(); //합계금액 -> 실제 수입
				 String moneyetc = txt_etc.getText(); //기타결제금액
				 String moneycash = txt_cash.getText(); //현금결제금액
				 String moneycharge = txt_charge.getText(); //손님한테 준 거스름돈
				 
			}
		});
		btn_payment.setBounds(456, 552, 179, 174);
		screen_sell.add(btn_payment);
		
		JComboBox combo_etcinfo = new JComboBox();
		combo_etcinfo.setModel(new DefaultComboBoxModel(new String[] {"카드", "티머니", "핸드폰소액결제", "포인트결제"}));
		combo_etcinfo.setBounds(909, 190, 78, 21);
		screen_sell.add(combo_etcinfo);
		//기타결제입력창
		txt_etcinput = new JTextField();
		txt_etcinput.setColumns(10);
		txt_etcinput.setBounds(1000, 190, 134, 21);
		screen_sell.add(txt_etcinput);
		//기타결제입력버튼
		JButton btn_etcenter = new JButton("입력");
		btn_etcenter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_etc.setText(txt_etcinput.getText()); //입력한 기타결제금액을 하단에 띄움
				txt_etcinfo.setText((String)combo_etcinfo.getSelectedItem()); //기타결제 콤보박스에서 값을 가져와 하단에 띄움
			}
		});
		btn_etcenter.setBounds(1150, 189, 71, 23);
		screen_sell.add(btn_etcenter);
		
		JLabel lblNewLabel_10 = new JLabel("받은돈:");
		lblNewLabel_10.setBounds(909, 293, 57, 15);
		screen_sell.add(lblNewLabel_10);
		
		txt_cashinput = new JTextField();
		txt_cashinput.setColumns(10);
		txt_cashinput.setBounds(1000, 290, 134, 21);
		screen_sell.add(txt_cashinput);
		//현금입력버튼
		JButton btn_cashenter = new JButton("입력");
		btn_cashenter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_cash.setText(txt_cashinput.getText());
			}
		});
		btn_cashenter.setBounds(1150, 289, 71, 23);
		screen_sell.add(btn_cashenter);
		
		JLabel label_4 = new JLabel("결제정보:");
		label_4.setBounds(909, 245, 78, 15);
		screen_sell.add(label_4);
		
		//초기 화면 세팅
		screen_sell.setVisible(true);

		JPanel screen_pay = new JPanel();
		screen_pay.setBounds(0, 0, 1249, 768);
		mainPage.add(screen_pay);
		screen_pay.setLayout(null);
		
		
		JPanel screen_addstock = new JPanel();
		screen_addstock.setBounds(0, 0, 1249, 768);
		mainPage.add(screen_addstock);
		screen_addstock.setLayout(null);
		//재고관리 콤보박스
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"주문", "재고불일치", "기타"}));
		comboBox.setBounds(894, 122, 95, 21);
		screen_addstock.add(comboBox);
		JTable userTable2 = new JTable(model2); //테이블 생성
		userTable2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable jtable = (JTable)e.getSource();
				int row, col;
				//한 번 클릭했을 떄
				if(e.getButton() == 1) {
					row = jtable.getSelectedRow();
					col = jtable.getSelectedColumn();
					DefaultTableModel model2 = (DefaultTableModel)jtable.getModel();
	                
	                System.out.println(model2.getValueAt(row, col));   // 눌려진 셀의 값을 출력
				}
				//더블클릭 했을 때
				if(e.getClickCount() == 2) {
					
				}
			}
		});
		//스크롤판
		JScrollPane scrollPane2 = new JScrollPane(userTable2);
		scrollPane2.setBounds(60, 55, 810, 543);
		screen_addstock.add(scrollPane2);
		//재고관리 재고추가버튼
	      JButton btnNewButton_21 = new JButton("추가");
	      btnNewButton_21.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            int numRow = model2.getRowCount();
	            String[] barcodes = new String[numRow];
	            String[] itemNames = new String[numRow];
	            int[] displayQty, wrhouseQty;
	            displayQty = new int[numRow];
	            wrhouseQty = new int[numRow];

	            String comboStr = (String)comboBox.getSelectedItem();
	            Utilities.InputDiv inputDiv = null;
	            switch(comboStr){
	               case "주문":
	                  inputDiv = Utilities.InputDiv.ODR;
	                  break;
	               case "재고불일치":
	                  inputDiv = Utilities.InputDiv.INV;
	                  break;
	               case "기타":
	                  inputDiv = Utilities.InputDiv.OTH;
	                  break;
	            }
	            for(int i = 0; i < numRow; i++){
	               barcodes[i] = (String)model2.getValueAt(i, 0);
	               itemNames[i] = (String)model2.getValueAt(i, 1);
	               wrhouseQty[i] = Integer.parseInt((String)model2.getValueAt(i,2));
	               displayQty[i] = Integer.parseInt((String)model2.getValueAt(i, 3));

	               if(wrhouseQty[i] < 0 || displayQty[i] < 0){
	                  Dialog_error errDialog = new Dialog_error("수량이 음수!");
	                  errDialog.setVisible(true);
	                  return;
	               }
	            }

	            try {
	               Utilities.AddStock(barcodes, wrhouseQty, displayQty, inputDiv, numRow);
	            }catch(SQLException sqle){
	               Connection conn = DBConnection.getBranchConnection();
	               try {
	                  conn.rollback();
	               }catch(Exception ex){
	                  System.out.println(ex.getMessage());
	                  ex.printStackTrace();
	               }
	               System.out.println(sqle.getSQLState());
	               sqle.printStackTrace();
	            }catch(Exception ex){
	               System.out.println(ex.getMessage());
	               ex.printStackTrace();
	            }
	         }
	      });
		btnNewButton_21.setBounds(1064, 122, 117, 83);
		screen_addstock.add(btnNewButton_21);
		//재고관리 바코드 검색창
		textField_4 = new JTextField();
		textField_4.setBounds(963, 59, 116, 21);
		screen_addstock.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("바코드:");
		lblNewLabel_1.setBounds(894, 62, 57, 15);
		screen_addstock.add(lblNewLabel_1);
		
		//재고관리 바코드 검색
	      JButton btnNewButton_22 = new JButton("검색");
	      btnNewButton_22.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            String barcode = textField_4.getText();

	            // 이미 추가한 바코드인지 확인
	            for(int i = 0; i < model2.getRowCount(); i++){
	               if(barcode.compareTo((String)model2.getValueAt(i, 0)) == 0)
	                  return; // 존재하면 함수 종료
	            }

	            try {
	               String itemName = Utilities.getItemName(barcode);
	               String[] rowStr = new String[4];
	               rowStr[0] = barcode;
	               rowStr[1] = itemName;
	               rowStr[2] = "0";
	               rowStr[3] = "0";
	               model2.addRow(rowStr);
	            }catch(SQLException sqle){
	               System.out.println(sqle.getSQLState());
	               sqle.printStackTrace();
	            }catch(Exception ex){
	               System.out.println(ex.getMessage());
	               ex.printStackTrace();
	            }
	         }
	      });
	      
		btnNewButton_22.setBounds(1112, 58, 69, 23);
		screen_addstock.add(btnNewButton_22);
		
		JPanel screen_checkstock = new JPanel();
		screen_checkstock.setBounds(0, 0, 1249, 768);
		mainPage.add(screen_checkstock);
		screen_checkstock.setLayout(null);
		
		
		JTable userTable3 = new JTable(model3); //테이블 생성 model3
		userTable3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable jtable = (JTable)e.getSource();
				int row, col;
				//한 번 클릭했을 떄
				if(e.getButton() == 1) {
					row = jtable.getSelectedRow();
					col = jtable.getSelectedColumn();
					DefaultTableModel model2 = (DefaultTableModel)jtable.getModel();
	                
	                System.out.println(model2.getValueAt(row, col));   // 눌려진 셀의 값을 출력
				}
				//더블클릭 했을 때
				if(e.getClickCount() == 2) {
					
				}
			}
		});
		//스크롤판
		JScrollPane scrollPane3 = new JScrollPane(userTable3);
		scrollPane3.setBounds(60, 55, 810, 543);
		screen_checkstock.add(scrollPane3);
		
		JLabel lblNewLabel_2 = new JLabel("바코드:");
		lblNewLabel_2.setBounds(902, 55, 57, 15);
		screen_checkstock.add(lblNewLabel_2);
		
		textField_5 = new JTextField();
		textField_5.setBounds(971, 55, 116, 21);
		screen_checkstock.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnNewButton_10 = new JButton("검색");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int itemno = Integer.valueOf(textField_5.getText());
				//세연함수(itemno);
			}
		});
		btnNewButton_10.setBounds(1114, 55, 66, 23);
		screen_checkstock.add(btnNewButton_10);
		
		

		
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
		
		
		//환불테이블
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
				
			},
			new String[] {
				"\uD310\uB9E4\uBC88\uD638", "\uD569\uACC4", "\uBD80\uAC00\uC138", "\uC9C1\uC6D0\uBC88\uD638", "\uD310\uB9E4\uC77C\uC2DC", "\uCD9C\uACE0\uBC88\uD638", "\uACE0\uAC1D\uC131\uBCC4"
			}
		));
		
		scrollPane_2.setViewportView(table_sell);
		//환불버튼
		JButton btnNewButton_14 = new JButton("환불");
		btnNewButton_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sellno = Integer.valueOf((String)table_sell.getValueAt(table_sell.getSelectedRow(), 0)); //선택된 행의 판매번호를 가져온다
			}
		});
		btnNewButton_14.setBounds(843, 79, 102, 60);
		screen_refund.add(btnNewButton_14);
		
		JPanel screen_staff = new JPanel();
		screen_staff.setBounds(0, 0, 1249, 768);
		mainPage.add(screen_staff);
		screen_staff.setLayout(null);
		
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
		menuPage_1.setBounds(0, 769, 1125, 96);
		mainPage.add(menuPage_1);
		menuPage_1.setLayout(null);
		//�ǸŸ���Ȯ��
		JButton btnNewButton = new JButton("\uD310\uB9E4\uB9C8\uC9C4\uD655\uC778");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				screen_sell.setVisible(false);
				screen_refund.setVisible(false);
				screen_barcode.setVisible(false);
				screen_addstock.setVisible(false);
				screen_checkstock.setVisible(false);
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
				screen_addstock.setVisible(false);
				screen_checkstock.setVisible(false);
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
				screen_addstock.setVisible(false);
				screen_checkstock.setVisible(false);
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
				screen_addstock.setVisible(false);
				screen_checkstock.setVisible(false);
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
				screen_addstock.setVisible(false);
				screen_checkstock.setVisible(false);
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
				screen_addstock.setVisible(false);
				screen_checkstock.setVisible(false);
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
				screen_addstock.setVisible(false);
				screen_checkstock.setVisible(false);
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
				screen_addstock.setVisible(false);
				screen_checkstock.setVisible(false);
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
		JButton btnNewButton_8 = new JButton("입출고기록확인");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screen_sell.setVisible(false);
				screen_refund.setVisible(false);
				screen_barcode.setVisible(false);
				screen_addstock.setVisible(false);
				screen_checkstock.setVisible(false);
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
		
		JPanel menuPage_2 = new JPanel();
		menuPage_2.setBounds(1250, 0, 232, 768);
		mainPage.add(menuPage_2);
		menuPage_2.setLayout(null);
		//�Ǹ�
		JButton btnNewButton_12 = new JButton("\uD310\uB9E4");
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screen_sell.setVisible(true);
				screen_refund.setVisible(false);
				screen_barcode.setVisible(false);
				screen_addstock.setVisible(false);
				screen_checkstock.setVisible(false);
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
				screen_addstock.setVisible(false);
				screen_checkstock.setVisible(false);
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
		JButton btnNewButton_16 = new JButton("재고추가");
		btnNewButton_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screen_sell.setVisible(false);
				screen_refund.setVisible(false);
				screen_barcode.setVisible(false);
				screen_addstock.setVisible(true);
				screen_checkstock.setVisible(false);
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
		JButton btnNewButton_17 = new JButton("재고확인");
		btnNewButton_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screen_sell.setVisible(false);
				screen_refund.setVisible(false);
				screen_barcode.setVisible(false);
				screen_addstock.setVisible(false);
				screen_checkstock.setVisible(true);
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
				screen_addstock.setVisible(false);
				screen_checkstock.setVisible(false);
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
				screen_addstock.setVisible(false);
				screen_checkstock.setVisible(false);
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
				screen_addstock.setVisible(false);
				screen_checkstock.setVisible(false);
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
		//�����Ȯ��
		JButton btnNewButton_9 = new JButton("물품목록갱신");
		btnNewButton_9.setBounds(0, 677, 232, 91);
		menuPage_2.add(btnNewButton_9);
		//우측하단 유저정보패널
		JPanel edgepane = new JPanel();
		edgepane.setBounds(1125, 769, 357, 96);
		mainPage.add(edgepane);
		edgepane.setLayout(null);
		
		JPanel loginPage = new JPanel();
		loginPage.setBounds(0, 0, 357, 96);
		edgepane.add(loginPage);
		loginPage.setLayout(null);
		
		JPanel workPage = new JPanel();
		workPage.setBounds(0, 0, 357, 96);
		edgepane.add(workPage);
		workPage.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("근무자번호:");
	      lblNewLabel_7.setBounds(12, 24, 73, 15);
	      workPage.add(lblNewLabel_7);
	      
	      //근무중인 직원이름 출력
	      txt_workingstaffno = new JTextField();
	      txt_workingstaffno.setEditable(false);
	      txt_workingstaffno.setBounds(97, 21, 96, 21);
	      workPage.add(txt_workingstaffno);
	      txt_workingstaffno.setColumns(10);
	
	      //근무자 로그아웃, 퇴근
	      JButton btn_logout = new JButton("퇴근");
	      btn_logout.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            Utilities.LeaveWork();
	            workPage.setVisible(false);
	            loginPage.setVisible(true);
	
	         }
	      });
	
	      btn_logout.setBounds(223, 21, 97, 54);
	      workPage.add(btn_logout);
	
	      JLabel lblNewLabel_8 = new JLabel("근무자이름");
	      lblNewLabel_8.setBounds(12, 60, 73, 15);
	      workPage.add(lblNewLabel_8);
	
	      txt_workingstaffname = new JTextField();
	      txt_workingstaffname.setEditable(false);
	      txt_workingstaffname.setBounds(97, 54, 96, 21);
	      workPage.add(txt_workingstaffname);
	      txt_workingstaffname.setColumns(10);
	      
	      JLabel lblNewLabel_3 = new JLabel("직원번호:");
	      lblNewLabel_3.setBounds(12, 25, 57, 15);
	      loginPage.add(lblNewLabel_3);
	      //직원번호입력
	      txt_staffno = new JTextField();
	      txt_staffno.setBounds(85, 22, 116, 21);
	      loginPage.add(txt_staffno);
	      txt_staffno.setColumns(10);
	      
	      JLabel lblNewLabel_6 = new JLabel("이름:");
	      lblNewLabel_6.setBounds(12, 60, 57, 15);
	      loginPage.add(lblNewLabel_6);
	      //직원이름입력
	      txt_staffname = new JTextField();
	      txt_staffname.setBounds(85, 57, 116, 21);
	      loginPage.add(txt_staffname);
	      txt_staffname.setColumns(10);
	
	      //직원로그인버튼, 출근
	      JButton btn_login = new JButton("출근");
	      btn_login.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            int staffno = Integer.parseInt(txt_staffno.getText());
	            String staffname = txt_staffname.getText();
	            try {
	               if (Utilities.HasStaff(staffno, staffname)) {
	                  SharedGlobalWithUI.workingStaffNo = staffno;
	                  Utilities.StartWork(staffno);
	                  txt_workingstaffno.setText(Integer.toString(staffno));
	                  txt_workingstaffname.setText(staffname);
	               }
	               else{
	                  Dialog_error errWin = new Dialog_error("직원 이름 혹은 번호가 맞지 않습니다!");
	                  errWin.setVisible(true);
	                  return;
	               }
	            }catch(SQLException sqle){
	               System.out.println(sqle.getSQLState());
	               sqle.printStackTrace();
	            }catch(Exception ex){
	               System.out.println(ex.getMessage());
	               ex.printStackTrace();
	            }
	            loginPage.setVisible(false);
	            workPage.setVisible(true);
	         }
	      });
	
	      btn_login.setBounds(223, 21, 97, 54);
	      loginPage.add(btn_login);
		
		
		//물품목록갱신 버튼
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//세연함수(); -> 물품목록갱신
			}
		});
		screen_refund.setVisible(false);
		screen_barcode.setVisible(false);
		screen_addstock.setVisible(false);
		screen_checkstock.setVisible(false);
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
	public static void addPopup(Component component, final JPopupMenu popup) {
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
			public void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
