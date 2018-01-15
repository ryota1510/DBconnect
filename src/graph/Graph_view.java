package graph;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import dbconnect.MYSQL;

public class Graph_view extends Frame implements ActionListener,WindowListener {
	
	private static final String LInechart = null;
	private static final String Barchart = null;
	private Button button1 = new Button("BarChart");
	private Button button2 = new Button("LineChart");
	
	public Graph_view() {
		addWindowListener(this);
		setTitle("Graph");
		setLayout(new FlowLayout(FlowLayout.CENTER));
		add(button1);
		button1.addActionListener(this);
		add(button2);
		button2.addActionListener(this);
		
		
	}
	
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
				
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		int id, year,ton;
		String name;
		ResultSet rs;
		MYSQL mysql = new MYSQL();
		rs = mysql.selectAll();

		try {
			while(rs.next()){	// rs に id,name,year,ton のデータが行ごとに格納されている。rs.next()で進んでいく。最後まで行ったらループを抜ける。
			    id = rs.getInt("id");
			    name = rs.getString("name");
			    year = rs.getInt("year");
			    ton = rs.getInt("ton");
			    data.addValue(ton, name, ""+year); // addValue: 整数,文字列,文字列
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		
		if(e.getSource() == button1) {
			removeAll();
			add(button1);
			add(button2);
			JFreeChart Barchart = ChartFactory.createBarChart(
					"Import Volume", 
					"Year",
					"Ton", 
					data,
					PlotOrientation.VERTICAL,
					true,
					false,
					false
			);
			ChartPanel cpanel = new ChartPanel(Barchart);
			add(cpanel, BorderLayout.CENTER);
		}
		
		else if(e.getSource() == button2){
			removeAll();
			add(button1);
			add(button2);
			JFreeChart Linechart = ChartFactory.createLineChart(
					"Import Volume", 
					"Year",
					"Ton", 
					data,
					PlotOrientation.VERTICAL,
					true,
					false,
					false
			);
			ChartPanel cpanel = new ChartPanel(Linechart);
			add(cpanel, BorderLayout.CENTER);
		}
		setVisible(true);
	}

}
