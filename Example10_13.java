import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class Example10_13 extends JFrame implements ActionListener{
	JTable table;
	Object[][] a;
	Object[][] b;
	List<Map> list_table=new ArrayList<Map>();
	Object[] name={"姓名", "英语", "数学", "总成绩"};
	JButton setRows, cal, del;
	JTextField inputNumber;
	int rows=1;
	JPanel p;
	
	public Example10_13() throws HeadlessException {
		super();
		// TODO Auto-generated constructor stub
		cal=new JButton("总成绩");
		setRows=new JButton("确定");
		del=new JButton("删除");
		inputNumber=new JTextField(10);
		setRows.addActionListener(this);
		cal.addActionListener(this);
		del.addActionListener(this);
		a=new Object[rows][4];
		table=new JTable(a, name);
		p=new JPanel();
		p.add(new JLabel("输入表格行数"));
		p.add(inputNumber);
		p.add(setRows);
		p.add(cal);
		p.add(del);
		this.add(p, BorderLayout.SOUTH);
		this.add(new JScrollPane(table), BorderLayout.CENTER);
		this.setBounds(100, 100, 550, 200);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Example10_13();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==setRows){
			rows=Integer.parseInt(inputNumber.getText());
			a=new Object[rows][4];
			table=new JTable(a, name);
			this.getContentPane().removeAll();
			this.add(new JScrollPane(table), BorderLayout.CENTER);
			this.add(p, BorderLayout.SOUTH);
			this.validate();
		}else if(e.getSource()==cal){
			for(int i=0;i<rows;i++){
				double sum=0;
				boolean boo=true;
				for(int j=1;j<=2;j++){
					try{
						sum+=Double.parseDouble(a[i][j].toString());
					}catch(Exception e1){
						boo=false;
						table.repaint();
					}
					if(boo==true){
						a[i][3]=""+sum;
						table.repaint();
					}
				}
			}
		}else if(e.getSource()==del){
			for(int i=0;i<table.getRowCount();i++){
				int col_len=table.getColumnCount();
				Map<Integer, String> map=new HashMap<Integer, String>();
				for(int j=0;j<col_len;j++){
					map.put(j, (String)table.getValueAt(i, j));
				}
				list_table.add(map);
			}
			int row=table.getSelectedRow();
			if(row!=-1){
				list_table.remove(row);
				b=new Object[table.getRowCount()-1][table.getColumnCount()];
				for(int i=0;i<list_table.size();i++){
					for(int j=0;j<list_table.get(i).size();j++){
						b[i][j]=list_table.get(i).get(j);
					}
				}
				table=new JTable(b, name);
				this.getContentPane().removeAll();
				this.add(new JScrollPane(table), BorderLayout.CENTER);
				this.add(p, BorderLayout.SOUTH);
				this.validate();
			}
//			int row = table.getSelectedRow();
//			if(row!=-1){
//				b=new Object[table.getRowCount()-1][table.getColumnCount()];
//				if(table.getRowCount()-1==0){
//					table=new JTable(b, name);
//					this.getContentPane().removeAll();
//					this.add(new JScrollPane(table), BorderLayout.CENTER);
//					this.add(p, BorderLayout.SOUTH);
//					this.validate();
//				}else{
//					for(int i=0;i<b.length;i++){
//						for(int j=0;j<b[i].length;j++){
//							if(row==i){
//								break;
//							}else{
//								b[i][j]=table.getValueAt(i, j);
//							}
//						}
//					}
//				}
//				table=new JTable(b, name);
//				this.getContentPane().removeAll();
//				this.add(new JScrollPane(table), BorderLayout.CENTER);
//				this.add(p, BorderLayout.SOUTH);
//				this.validate();
//			}
		}
		list_table.clear();
	}

}
