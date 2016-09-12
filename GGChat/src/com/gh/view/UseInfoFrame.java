package com.gh.view;


import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Toolkit;

public class UseInfoFrame extends JFrame {

	private JPanel contentPane;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_4;

	/**
	 * Create the frame.
	 */
	public UseInfoFrame(String id,String name,String sex,String likes,String email,String info) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UseInfoFrame.class.getResource("/com/gh/res/1.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle(id+"µÄÐÅÏ¢");
		setBounds(100, 100, 450, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_3 = new JLabel("\u59D3 \u540D\uFF1A");
		label_3.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		label_3.setBounds(51, 63, 54, 15);
		contentPane.add(label_3);
		
		JLabel lb_id = new JLabel(name);
		lb_id.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		lb_id.setBounds(155, 62, 54, 15);
		contentPane.add(lb_id);
		
		JLabel lblNewLabel = new JLabel("\u767B\u9646\u540D\uFF1A");
		lblNewLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		lblNewLabel.setBounds(51, 89, 54, 15);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("\u6027  \u522B\uFF1A");
		label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		label.setBounds(51, 114, 54, 15);
		contentPane.add(label);
		
		label_1 = new JLabel("\u7231  \u597D\uFF1A");
		label_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		label_1.setBounds(51, 139, 54, 15);
		contentPane.add(label_1);
		
		label_2 = new JLabel("\u90AE  \u7BB1\uFF1A");
		label_2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		label_2.setBounds(51, 164, 54, 15);
		contentPane.add(label_2);
		
		label_4 = new JLabel("\u4E2A\u6027\u7B7E\u540D\uFF1A");
		label_4.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		label_4.setBounds(51, 198, 76, 15);
		contentPane.add(label_4);
		
		JLabel lb_name = new JLabel(id);
		lb_name.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		lb_name.setBounds(155, 88, 83, 15);
		contentPane.add(lb_name);
		
		JLabel lb_sex = new JLabel(sex);
		lb_sex.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		lb_sex.setBounds(155, 113, 83, 15);
		contentPane.add(lb_sex);
		
		JLabel le_likes = new JLabel(likes);
		le_likes.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		le_likes.setBounds(155, 139, 207, 15);
		contentPane.add(le_likes);
		
		JLabel lb_email = new JLabel(email);
		lb_email.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		lb_email.setBounds(155, 163, 207, 15);
		contentPane.add(lb_email);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(121, 195, 200, 37);
		contentPane.add(scrollPane);
		
		JTextArea ta_info = new JTextArea(info);
		ta_info.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		ta_info.setBorder(null);
		ta_info.setEditable(false);
		scrollPane.setViewportView(ta_info);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(UseInfoFrame.class.getResource("/com/gh/res/\u597D\u53CB\u4FE1\u606F.png")));
		lblNewLabel_2.setBounds(0, 0, 434, 285);
		contentPane.add(lblNewLabel_2);
	}
}
