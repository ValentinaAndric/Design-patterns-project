package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Line;
import geometry.Point;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgLine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private boolean confirm;
	private JTextField txtSPX;
	private JTextField txtSPY;
	private JTextField txtEPX;
	private JTextField txtEPY;
	public Line line;
	private Color color = Color.BLACK;
	private JButton btnOutlineColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLine() {
		setModal(true);
		setTitle("Modify Line");
		setBackground(Color.WHITE);
		setResizable(false);
		setLocationRelativeTo(null);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("START POINT ");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 2;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("coordinate X");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 2;
			gbc_lblNewLabel_1.gridy = 1;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			txtSPX = new JTextField();
			GridBagConstraints gbc_txtSPX = new GridBagConstraints();
			gbc_txtSPX.insets = new Insets(0, 0, 5, 0);
			gbc_txtSPX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtSPX.gridx = 5;
			gbc_txtSPX.gridy = 1;
			contentPanel.add(txtSPX, gbc_txtSPX);
			txtSPX.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("coordinate Y");
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_2.gridx = 2;
			gbc_lblNewLabel_2.gridy = 2;
			contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		}
		{
			txtSPY = new JTextField();
			txtSPY.setText("");
			GridBagConstraints gbc_txtSPY = new GridBagConstraints();
			gbc_txtSPY.insets = new Insets(0, 0, 5, 0);
			gbc_txtSPY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtSPY.gridx = 5;
			gbc_txtSPY.gridy = 2;
			contentPanel.add(txtSPY, gbc_txtSPY);
			txtSPY.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("END POINT");
			GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
			gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_3.gridx = 2;
			gbc_lblNewLabel_3.gridy = 4;
			contentPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("coordinate X");
			GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
			gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_4.gridx = 2;
			gbc_lblNewLabel_4.gridy = 5;
			contentPanel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		}
		{
			txtEPX = new JTextField();
			GridBagConstraints gbc_txtEPX = new GridBagConstraints();
			gbc_txtEPX.insets = new Insets(0, 0, 5, 0);
			gbc_txtEPX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEPX.gridx = 5;
			gbc_txtEPX.gridy = 5;
			contentPanel.add(txtEPX, gbc_txtEPX);
			txtEPX.setColumns(10);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("coordinate Y");
			GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
			gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_5.gridx = 2;
			gbc_lblNewLabel_5.gridy = 6;
			contentPanel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		}
		{
			txtEPY = new JTextField();
			txtEPY.setText("");
			GridBagConstraints gbc_txtEPY = new GridBagConstraints();
			gbc_txtEPY.insets = new Insets(0, 0, 5, 0);
			gbc_txtEPY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEPY.gridx = 5;
			gbc_txtEPY.gridy = 6;
			contentPanel.add(txtEPY, gbc_txtEPY);
			txtEPY.setColumns(10);
		}

		btnOutlineColor = new JButton("OUTLINE COLOR");
		btnOutlineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color outlineColor = JColorChooser.showDialog(null, "Choose outline color",
						btnOutlineColor.getBackground());
				if (outlineColor != null)
					btnOutlineColor.setBackground(outlineColor);

			}
		});
		GridBagConstraints gbc_btnOutlineColor = new GridBagConstraints();
		gbc_btnOutlineColor.insets = new Insets(0, 0, 0, 5);
		gbc_btnOutlineColor.gridx = 2;
		gbc_btnOutlineColor.gridy = 8;
		contentPanel.add(btnOutlineColor, gbc_btnOutlineColor);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (txtSPX.getText().trim().isEmpty() || txtSPY.getText().trim().isEmpty()
								|| txtEPX.getText().trim().isEmpty() || txtEPY.getText().trim().isEmpty()) {
							confirm = false;
							JOptionPane.showMessageDialog(null, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
						} else {
							try {
								if (Integer.parseInt(txtSPX.getText().toString()) < 0
										|| Integer.parseInt(txtSPY.getText().toString()) < 0
										|| Integer.parseInt(txtEPX.getText().toString()) < 0
										|| Integer.parseInt(txtEPY.getText().toString()) < 0) {
									JOptionPane.showMessageDialog(null, "Insert values greater than 0!", "Error",
											JOptionPane.ERROR_MESSAGE);

								} else {
									line = new Line(
											new Point(Integer.parseInt(txtSPX.getText().toString()),
													Integer.parseInt(txtSPY.getText().toString())),
											new Point(Integer.parseInt(txtEPX.getText().toString()),
													Integer.parseInt(txtEPY.getText().toString())),
											false, btnOutlineColor.getBackground());

									confirm = true;
									setVisible(false);

								}

							} catch (Exception e2) {
								JOptionPane.showMessageDialog(null, "Enter numbers only!", "Error",
										JOptionPane.ERROR_MESSAGE);
							}

						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

	public JTextField getTxtSPX() {
		return txtSPX;
	}

	public void setTxtSPX(JTextField txtSPX) {
		this.txtSPX = txtSPX;
	}

	public JTextField getTxtSPY() {
		return txtSPY;
	}

	public void setTxtSPY(JTextField txtSPY) {
		this.txtSPY = txtSPY;
	}

	public JTextField getTxtEPX() {
		return txtEPX;
	}

	public void setTxtEPX(JTextField txtEPX) {
		this.txtEPX = txtEPX;
	}

	public JTextField getTxtEPY() {
		return txtEPY;
	}

	public void setTxtEPY(JTextField txtEPY) {
		this.txtEPY = txtEPY;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public JButton getBtnOutlineColor() {
		return btnOutlineColor;
	}

	public void setBtnOutlineColor(JButton btnOutlineColor) {
		this.btnOutlineColor = btnOutlineColor;
	}

}
