// This the tree graph GUI.
// In this GUI, the tree graph will be displayed.
package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import edu.stanford.nlp.parser.ui.TreeJPanel;

public class TreeGraphGui {
	public static Runnable treeGraphGui = new Runnable() {
		public void run() {
			final JFrame parsingFrame = new JFrame("剖析树 (parse tree)");
			parsingFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			parsingFrame.setResizable(false);
			// parsingFrame.setSize(600, 1000);
			parsingFrame.getContentPane().setPreferredSize(
					new Dimension(1000, 600));
			parsingFrame.pack();
			parsingFrame.setVisible(true);

			Container treeContent = parsingFrame.getContentPane();
			treeContent.setLayout(null);

			TreeJPanel treePanel = new TreeJPanel();
			treePanel.setTree(global.Variable.tree);
			treePanel.setSize(800, 600);
			treePanel.setLocation(0, 0);
			treePanel.setBackground(Color.WHITE);
			treePanel.setLayout(null);
			treeContent.add(treePanel);

			JPanel textTreePanel = new JPanel();
			textTreePanel.setSize(200, 500);
			textTreePanel.setLocation(800, 0);
			textTreePanel.setBackground(Color.green);
			textTreePanel.setLayout(null);
			treeContent.add(textTreePanel);

			// Text Area
			JTextArea textArea = new JTextArea(global.Variable.parsedTree);
			textArea.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			textArea.setEditable(false);

			// Text Scroll Pane
			JScrollPane TextScrollPane = new JScrollPane(textArea);
			TextScrollPane.setSize(200, 500);
			TextScrollPane.setLocation(0, 0);
			TextScrollPane
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			TextScrollPane
					.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			textTreePanel.add(TextScrollPane);

			JPanel exitButtonPanel = new JPanel();
			exitButtonPanel.setSize(200, 100);
			exitButtonPanel.setLocation(800, 500);
			exitButtonPanel.setLayout(null);
			treeContent.add(exitButtonPanel);

			JButton ExitButton = new JButton("关闭 (exit)");
			ExitButton.setFont(new Font("微软雅黑", Font.BOLD, 16));
			ExitButton.setFocusable(false);
			ExitButton.setSize(150, 50);
			ExitButton.setLocation(25, 25);
			exitButtonPanel.add(ExitButton);

			ActionListener ExitButtonActionListener = new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					global.Variable.treeGraphIsDisplaying = false;
					parsingFrame.dispose();
				}
			};
			ExitButton.addActionListener(ExitButtonActionListener);
		}
	};

}
