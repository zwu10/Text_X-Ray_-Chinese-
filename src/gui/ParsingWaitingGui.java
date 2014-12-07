// This is the waiting GUI.
// This GUI will be displayed when the parse is processing.
package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ParsingWaitingGui {
	public static Runnable parsingWaitingGui = new Runnable() {
		public void run() {

			global.Component.waitingFrame
					.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			global.Component.waitingFrame.setResizable(false);
			global.Component.waitingFrame.getContentPane().setPreferredSize(
					new Dimension(990, 590));
			global.Component.waitingFrame.pack();
			global.Component.waitingFrame.setVisible(true);

			Container textContent = global.Component.waitingFrame
					.getContentPane();
			textContent.setLayout(null);

			JPanel textPanel = new JPanel();
			textPanel.setSize(1000, 300);
			textPanel.setLocation(200, 0);

			textPanel.setLayout(null);
			textContent.add(textPanel);

			global.Component.textLabel.setSize(1000, 300);
			global.Component.textLabel.setLocation(0, 0);
			global.Component.textLabel.setFont(new Font("微软雅黑", Font.BOLD, 36));
			textPanel.add(global.Component.textLabel);

			JPanel textPanel2 = new JPanel();
			textPanel2.setSize(1000, 300);
			textPanel2.setLocation(250, 200);

			textPanel2.setLayout(null);
			textContent.add(textPanel2);

			global.Component.textLabel2.setSize(1000, 300);
			global.Component.textLabel2.setLocation(0, 0);
			global.Component.textLabel2
					.setFont(new Font("微软雅黑", Font.BOLD, 36));
			textPanel2.add(global.Component.textLabel2);
		}
	};
}
