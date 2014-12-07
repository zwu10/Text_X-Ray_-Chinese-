// This is the main GUI of this project.
// This GUI contains one text editor and several function buttons.
package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;

public class MainGui {
	public static Runnable mainRunner = new Runnable() {
		public void run() {
			
			// Frame declaration
			final JFrame mainFrame = new JFrame("中文剖析器 Text X-Ray (Chinese)");
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainFrame.setResizable(false);
			mainFrame.getContentPane().setPreferredSize(
					new Dimension(1000, 600));
			mainFrame.pack();
			mainFrame.setVisible(true);

			// Add a container to the frame
			Container content = mainFrame.getContentPane();
			// Set the layout of the container to be null
			content.setLayout(null);

			// Parse and Clean Panel declaration
			JPanel ParseCleanPanel = new JPanel();
			ParseCleanPanel.setSize(1000, 60);
			ParseCleanPanel.setLocation(0, 0);
			ParseCleanPanel.setLayout(null);
			content.add(ParseCleanPanel);

			// Parse Button declaration
			final JButton ParseButton = new JButton("剖析 (Parse)");
			ParseButton.setFont(new Font("微软雅黑", Font.BOLD, 23));
			ParseButton.setFocusable(false);
			ParseButton.setSize(200, 40);
			ParseButton.setLocation(150, 10);
			ParseCleanPanel.add(ParseButton);

			// Parse Button Action Listener
			ActionListener ParseButtonActionListener = new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					
					// Record button press to the log
					parser.method.PrintToLog
							.catchCurrentButton(ParseButton.getText(),
									global.Variable.LOG_LOCATION);
					
					// Invoke the waiting GUI, so that the user cannot press
					// any button when the parse is processing
					EventQueue
							.invokeLater(gui.ParsingWaitingGui.parsingWaitingGui);

					// A new thread used to parse current text
					Thread parseThread = new Thread() {
						public void run() {
							
							// Delay this thread, because the waiting GUI must show first
							// before the parse process starts
							try {
								Thread.sleep(100);
							} catch (InterruptedException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							
							// Release all pressed button
							parser.method.Reset.buttonReset();
							
							// Remove all spaces in the text editor
							String noSpaceText = global.Component.textPane
									.getText().replaceAll(" ", "");
							
							// Change all colored text to black, if any
							parser.method.TextHighlight
									.noHighlightText(noSpaceText);
							
							// Hide the main GUI
							mainFrame.setVisible(false);
							
							// Start the parse process
							parser.method.Parser
									.StartParse(global.Component.textPane
											.getText());
							
							// After parse completed, stop hiding the main GUI
							// and dispose the waiting GUI
							mainFrame.setVisible(true);
							global.Component.waitingFrame.dispose();

							// Pop up a dialog showing the time cost
							JTextArea textArea = new JTextArea(
									"剖析已经完成！\n共用时："
											+ global.Variable.timerTime
											+ "秒"
											+ "\n\n(parsing finished!\nthe total time is: "
											+ global.Variable.timerTime + " s)");
							textArea.setFont(new Font("微软雅黑", Font.PLAIN, 20));
							textArea.setBackground(new Color(0, 0, 0, 0));

							JOptionPane.showMessageDialog(mainFrame, textArea,
									"", JOptionPane.INFORMATION_MESSAGE);
							if (global.Variable.parseTimer.isRunning())
								global.Variable.parseTimer.stop();
							global.Variable.timerTime = 0;

						}
					};

					parseThread.start();

					// A thread used to start a timer for the waiting GUI
					Thread timerThread = new Thread() {
						public void run() {

							ActionListener listener = new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									global.Variable.timerTime++;
									global.Component.textLabel.setText("");
									global.Component.textLabel2.setText("");
									global.Component.textLabel
											.setText("正在剖析，请稍等。。。。。。" + " "
													+ global.Variable.timerTime
													+ "秒");
									global.Component.textLabel2
											.setText("parsing, please wait..."
													+ " "
													+ global.Variable.timerTime
													+ "s");
								}
							};
							global.Variable.parseTimer = new javax.swing.Timer(
									1000, listener);
							global.Variable.parseTimer.start();
							while (true)
								;

						}
					};

					timerThread.start();
				}

			};
			ParseButton.addActionListener(ParseButtonActionListener);

			// Clean Button declaration
			final JButton CleanButton = new JButton("清空 (Clean)");
			CleanButton.setFont(new Font("微软雅黑", Font.BOLD, 23));
			CleanButton.setFocusable(false);
			CleanButton.setSize(200, 40);
			CleanButton.setLocation(650, 10);
			ParseCleanPanel.add(CleanButton);

			// Clean Button Action Listener
			ActionListener CleanButtonActionListener = new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					
					// Record button press to the log
					parser.method.PrintToLog
							.catchCurrentButton(CleanButton.getText(),
									global.Variable.LOG_LOCATION);
					
					// Release all pressed buttons
					parser.method.Reset.buttonReset();
					
					// Clear involved global variables
					global.Variable.ParsedWordList.clear();
					global.Variable.ParsedSentenceList.clear();
					global.Variable.parsedTree = "";

					// Clean the text in the text editor
					global.Component.textPane.setText("");
				}

			};
			CleanButton.addActionListener(CleanButtonActionListener);

			// Text Enter Panel declaration
			JPanel TextEnterPanel = new JPanel();
			TextEnterPanel.setSize(500, 940);
			TextEnterPanel.setLocation(0, 60);
			TextEnterPanel.setLayout(null);
			content.add(TextEnterPanel);

			// Prompt Enter Text Label declaration
			JLabel PromptEnterTextLabel = new JLabel("请在下方输入文字",
					SwingConstants.CENTER);
			JLabel PromptEnterTextLabel2 = new JLabel(
					"(Please enter text below)", SwingConstants.CENTER);
			PromptEnterTextLabel.setSize(500, 30);
			PromptEnterTextLabel2.setSize(500, 30);
			PromptEnterTextLabel.setLocation(0, 10);
			PromptEnterTextLabel2.setLocation(0, 40);
			PromptEnterTextLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
			PromptEnterTextLabel2.setFont(new Font("微软雅黑", Font.BOLD, 16));
			TextEnterPanel.add(PromptEnterTextLabel);
			TextEnterPanel.add(PromptEnterTextLabel2);

			// Set the default font of the text editor
			global.Component.textPane.setFont(new Font("微软雅黑", Font.BOLD,
					global.Variable.EditorFontSize));
			StyleConstants.setForeground(global.Component.style, Color.black);
			try {
				global.Component.doc.insertString(
						global.Component.doc.getLength(),
						parser.text.SampleText.sampleText,
						global.Component.style);
			} catch (BadLocationException e) {
			}

			// Text Scroll Pane declaration
			JScrollPane TextScrollPane = new JScrollPane(
					global.Component.textPane);
			TextScrollPane.setSize(450, 440);
			TextScrollPane.setLocation(25, 80);
			TextScrollPane
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			TextScrollPane
					.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			TextEnterPanel.add(TextScrollPane);

			// Toggle Button Panel declaration
			JPanel ToggleButtonPanel = new JPanel();
			ToggleButtonPanel.setSize(500, 940);
			ToggleButtonPanel.setLocation(500, 60);
			ToggleButtonPanel.setLayout(null);
			content.add(ToggleButtonPanel);

			// Prompt Press Button Label declaration
			JLabel PressButtonTextLabel = new JLabel("请选择下方按键",
					SwingConstants.CENTER);
			JLabel PressButtonTextLabel2 = new JLabel(
					"(Please press buttons below)", SwingConstants.CENTER);
			PressButtonTextLabel.setSize(500, 30);
			PressButtonTextLabel2.setSize(500, 30);
			PressButtonTextLabel.setLocation(0, 10);
			PressButtonTextLabel2.setLocation(0, 40);
			PressButtonTextLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
			PressButtonTextLabel2.setFont(new Font("微软雅黑", Font.BOLD, 16));
			ToggleButtonPanel.add(PressButtonTextLabel);
			ToggleButtonPanel.add(PressButtonTextLabel2);

			// Noun Button declaration
			global.Component.NounButton.setSize(120, 30);
			global.Component.NounButton.setLocation(10, 80);
			global.Component.NounButton
					.setFont(new Font("微软雅黑", Font.BOLD, 14));
			global.Component.NounButton.setForeground(Color.green);
			global.Component.NounButton.setFocusable(false);
			ToggleButtonPanel.add(global.Component.NounButton);

			// Noun Button Action Listener
			ActionListener NounButtonActionListener = new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					
					// Highlight text
					parser.method.TextHighlight.wordHighlight("noun",
							global.Component.NounButton);
					
					// Record button press to the log
					parser.method.PrintToLog.catchCurrentButton(
							global.Component.NounButton.getText(),
							global.Variable.LOG_LOCATION);

				}
			};
			global.Component.NounButton
					.addActionListener(NounButtonActionListener);

			// Verb Button declaration
			global.Component.VerbButton.setSize(120, 30);
			global.Component.VerbButton.setLocation(190, 80);
			global.Component.VerbButton
					.setFont(new Font("微软雅黑", Font.BOLD, 14));
			global.Component.VerbButton.setForeground(Color.pink);
			global.Component.VerbButton.setFocusable(false);
			ToggleButtonPanel.add(global.Component.VerbButton);

			// Verb Button Action Listener
			ActionListener VerbButtonActionListener = new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					
					// Highlight text
					parser.method.TextHighlight.wordHighlight("verb",
							global.Component.VerbButton);

					// Record button press to the log
					parser.method.PrintToLog.catchCurrentButton(
							global.Component.VerbButton.getText(),
							global.Variable.LOG_LOCATION);

				}
			};
			global.Component.VerbButton
					.addActionListener(VerbButtonActionListener);

			// Adjective Button declaration
			global.Component.AdjectiveButton.setSize(120, 30);
			global.Component.AdjectiveButton.setLocation(370, 80);
			global.Component.AdjectiveButton.setFont(new Font("微软雅黑",
					Font.BOLD, 14));
			global.Component.AdjectiveButton.setForeground(Color.red);
			global.Component.AdjectiveButton.setFocusable(false);
			ToggleButtonPanel.add(global.Component.AdjectiveButton);

			// Adjective Button Action Listener
			ActionListener AdjectiveButtonActionListener = new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					
					// Highlight text
					parser.method.TextHighlight.wordHighlight("adj.",
							global.Component.AdjectiveButton);
					
					// Record button press to the log
					parser.method.PrintToLog.catchCurrentButton(
							global.Component.AdjectiveButton.getText(),
							global.Variable.LOG_LOCATION);

				}
			};
			global.Component.AdjectiveButton
					.addActionListener(AdjectiveButtonActionListener);

			// Adverb Button declaration
			global.Component.AdverbButton.setSize(120, 30);
			global.Component.AdverbButton.setLocation(10, 130);
			global.Component.AdverbButton.setFont(new Font("微软雅黑", Font.BOLD,
					14));
			global.Component.AdverbButton.setForeground(Color.blue);
			global.Component.AdverbButton.setFocusable(false);
			ToggleButtonPanel.add(global.Component.AdverbButton);

			// Adverb Button Action Listener
			ActionListener AdverbButtonActionListener = new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					
					// Highlight text
					parser.method.TextHighlight.wordHighlight("adv.",
							global.Component.AdverbButton);
					
					// Record button press to the log
					parser.method.PrintToLog.catchCurrentButton(
							global.Component.AdverbButton.getText(),
							global.Variable.LOG_LOCATION);

				}
			};
			global.Component.AdverbButton
					.addActionListener(AdverbButtonActionListener);

			// Pronoun Button declaration
			global.Component.PronounButton.setSize(120, 30);
			global.Component.PronounButton.setLocation(190, 130);
			global.Component.PronounButton.setFont(new Font("微软雅黑", Font.BOLD,
					14));
			global.Component.PronounButton
					.setForeground(new Color(255, 0, 127));
			global.Component.PronounButton.setFocusable(false);
			ToggleButtonPanel.add(global.Component.PronounButton);

			// Pronoun Button Action Listener
			ActionListener PronounButtonActionListener = new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					parser.method.TextHighlight.wordHighlight("pron.",
							global.Component.PronounButton);
					parser.method.PrintToLog.catchCurrentButton(
							global.Component.PronounButton.getText(),
							global.Variable.LOG_LOCATION);

				}
			};
			global.Component.PronounButton
					.addActionListener(PronounButtonActionListener);

			// Numeral Button declaration
			global.Component.NumeralButton.setSize(120, 30);
			global.Component.NumeralButton.setLocation(370, 130);
			global.Component.NumeralButton.setFont(new Font("微软雅黑", Font.BOLD,
					14));
			global.Component.NumeralButton.setForeground(Color.MAGENTA);
			global.Component.NumeralButton.setFocusable(false);
			ToggleButtonPanel.add(global.Component.NumeralButton);

			// Numeral Button Action Listener
			ActionListener NumeralButtonActionListener = new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					parser.method.TextHighlight.wordHighlight("num.",
							global.Component.NumeralButton);
					parser.method.PrintToLog.catchCurrentButton(
							global.Component.NumeralButton.getText(),
							global.Variable.LOG_LOCATION);

				}
			};
			global.Component.NumeralButton
					.addActionListener(NumeralButtonActionListener);

			// Preposition Button declaration
			global.Component.PrepositionButton.setSize(120, 30);
			global.Component.PrepositionButton.setLocation(10, 180);
			global.Component.PrepositionButton.setFont(new Font("微软雅黑",
					Font.BOLD, 14));
			global.Component.PrepositionButton.setForeground(Color.GRAY);
			global.Component.PrepositionButton.setFocusable(false);
			ToggleButtonPanel.add(global.Component.PrepositionButton);

			// Preposition Button Action Listener
			ActionListener PrepositionButtonActionListener = new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					parser.method.TextHighlight.wordHighlight("prep.",
							global.Component.PrepositionButton);
					parser.method.PrintToLog.catchCurrentButton(
							global.Component.PrepositionButton.getText(),
							global.Variable.LOG_LOCATION);

				}
			};
			global.Component.PrepositionButton
					.addActionListener(PrepositionButtonActionListener);

			// Conjunction Button declaration
			global.Component.ConjunctionButton.setSize(120, 30);
			global.Component.ConjunctionButton.setLocation(190, 180);
			global.Component.ConjunctionButton.setFont(new Font("微软雅黑",
					Font.BOLD, 14));
			global.Component.ConjunctionButton.setForeground(Color.ORANGE);
			global.Component.ConjunctionButton.setFocusable(false);
			ToggleButtonPanel.add(global.Component.ConjunctionButton);

			// Conjunction Button Action Listener
			ActionListener ConjunctionButtonActionListener = new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					parser.method.TextHighlight.wordHighlight("conj.",
							global.Component.ConjunctionButton);
					parser.method.PrintToLog.catchCurrentButton(
							global.Component.ConjunctionButton.getText(),
							global.Variable.LOG_LOCATION);

				}
			};
			global.Component.ConjunctionButton
					.addActionListener(ConjunctionButtonActionListener);

			// Punctuation Button declaration
			global.Component.PunctuationButton.setSize(120, 30);
			global.Component.PunctuationButton.setLocation(370, 180);
			global.Component.PunctuationButton.setFont(new Font("微软雅黑",
					Font.BOLD, 14));
			global.Component.PunctuationButton.setForeground(Color.CYAN);
			global.Component.PunctuationButton.setFocusable(false);
			ToggleButtonPanel.add(global.Component.PunctuationButton);

			// Punctuation Button Action Listener
			ActionListener PunctuationButtonActionListener = new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					parser.method.TextHighlight.wordHighlight("punc.",
							global.Component.PunctuationButton);
					parser.method.PrintToLog.catchCurrentButton(
							global.Component.PunctuationButton.getText(),
							global.Variable.LOG_LOCATION);

				}
			};
			global.Component.PunctuationButton
					.addActionListener(PunctuationButtonActionListener);

			// Long Sentence Button declaration
			global.Component.LongSentenceButton.setSize(410, 30);
			global.Component.LongSentenceButton.setLocation(10, 230);
			global.Component.LongSentenceButton.setFont(new Font("微软雅黑",
					Font.BOLD, 14));
			global.Component.LongSentenceButton.setForeground(Color.red);
			global.Component.LongSentenceButton.setFocusable(false);
			ToggleButtonPanel.add(global.Component.LongSentenceButton);

			// x = Label declaration
			JLabel xLabel = new JLabel("x =");
			xLabel.setSize(30, 30);
			xLabel.setLocation(430, 230);
			xLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));
			xLabel.setForeground(Color.red);
			ToggleButtonPanel.add(xLabel);

			// x = Text Field declaration
			final JTextField xTextField = new JTextField("5");
			xTextField.setSize(30, 30);
			xTextField.setLocation(460, 230);
			xTextField.setFont(new Font("微软雅黑", Font.BOLD, 14));
			xTextField.setForeground(Color.red);
			ToggleButtonPanel.add(xTextField);

			// Long Sentence Button Action Listener
			ActionListener LongSentenceButtonActionListener = new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					parser.method.TextHighlight.sentenceHighlight(
							Integer.parseInt(xTextField.getText()),
							global.Component.LongSentenceButton);
					parser.method.PrintToLog.catchCurrentButton(
							global.Component.LongSentenceButton.getText(),
							global.Variable.LOG_LOCATION);

				}
			};
			global.Component.LongSentenceButton
					.addActionListener(LongSentenceButtonActionListener);

			// Parse Tree Generate Button declaration
			final JButton ParseTreeGenerateButton = new JButton(
					"生成剖析树 (generate parse tree)");
			ParseTreeGenerateButton.setSize(480, 30);
			ParseTreeGenerateButton.setLocation(10, 280);
			ParseTreeGenerateButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
			ParseTreeGenerateButton.setForeground(Color.black);
			ParseTreeGenerateButton.setFocusable(false);
			ToggleButtonPanel.add(ParseTreeGenerateButton);

			// Generate Parse Tree Action Listener
			ActionListener GenerateParseTreeButtonActionListener = new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {

					// Check whether the tree graph GUI is showing or not
					if (global.Variable.treeGraphIsDisplaying) {
						// Do nothing
					} else {
						// Update the flag variable
						global.Variable.treeGraphIsDisplaying = true;
						
						// Invoke the tree graph GUI to display the tree graph
						EventQueue.invokeLater(gui.TreeGraphGui.treeGraphGui);
					}
					
					// Record button press to the log
					parser.method.PrintToLog.catchCurrentButton(
							ParseTreeGenerateButton.getText(),
							global.Variable.LOG_LOCATION);
				}
			};
			ParseTreeGenerateButton
					.addActionListener(GenerateParseTreeButtonActionListener);

			// Release All Toggle Button declaration
			final JButton ReleaseAllToggleButton = new JButton(
					"还原所有已选按键 (release all selected buttons)");
			ReleaseAllToggleButton.setSize(480, 30);
			ReleaseAllToggleButton.setLocation(10, 330);
			ReleaseAllToggleButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
			ReleaseAllToggleButton.setForeground(Color.black);
			ReleaseAllToggleButton.setFocusable(false);
			ToggleButtonPanel.add(ReleaseAllToggleButton);

			// Release All Toggle Button Action Listener
			ActionListener ReleaseAllToggleButtonActionListener = new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					
					// Reset all pressed buttons and highlighted text
					parser.method.Reset.buttonReset();
					parser.method.Reset.wordHighlightingReset();

					String plainText = "";
					for (int i = 0; i < global.Variable.ParsedWordList.size(); i++)
						plainText = plainText
								+ global.Variable.ParsedWordList.get(i)
										.getWord();
					parser.method.TextHighlight.noHighlightText(plainText);

					// Record button press to the log
					parser.method.PrintToLog.catchCurrentButton(
							ReleaseAllToggleButton.getText(),
							global.Variable.LOG_LOCATION);
				}

			};
			ReleaseAllToggleButton
					.addActionListener(ReleaseAllToggleButtonActionListener);

			// Show Log Button declaration
			final JButton ShowLogButton = new JButton("查看系统日志 (view log)");
			ShowLogButton.setSize(480, 30);
			ShowLogButton.setLocation(10, 380);
			ShowLogButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
			ShowLogButton.setForeground(Color.black);
			ShowLogButton.setFocusable(false);
			ToggleButtonPanel.add(ShowLogButton);

			// Show Log Button Action Listener
			ActionListener ShowLogButtonActionListener = new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					
					// Record button press to the log
					parser.method.PrintToLog.catchCurrentButton(
							ShowLogButton.getText(),
							global.Variable.LOG_LOCATION);

					// Show current log in a dialog
					JTextArea textArea = new JTextArea(
							global.Variable.systemLog);
					textArea.setFont(new Font("微软雅黑", Font.PLAIN, 13));
					JScrollPane scrollPane = new JScrollPane(textArea);
					scrollPane.setPreferredSize(new Dimension(800, 450));
					JOptionPane.showMessageDialog(mainFrame, scrollPane,
							"Parse Tree", JOptionPane.PLAIN_MESSAGE);
				}
			};
			ShowLogButton.addActionListener(ShowLogButtonActionListener);

			// Exit Button declaration
			final JButton ExitButton = new JButton("退出 (exit)");
			ExitButton.setSize(200, 70);
			ExitButton.setLocation(150, 440);
			ExitButton.setFont(new Font("微软雅黑", Font.BOLD, 24));
			ExitButton.setForeground(Color.black);
			ExitButton.setFocusable(false);
			ToggleButtonPanel.add(ExitButton);

			// Exit Button Action Listener
			ActionListener ExitButtonActionListener = new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					
					// Record button press to the log
					parser.method.PrintToLog.catchCurrentButton(
							ExitButton.getText(), global.Variable.LOG_LOCATION);
					
					// Terminate program
					System.exit(0);
				}
			};
			ExitButton.addActionListener(ExitButtonActionListener);
		}
	};

}
