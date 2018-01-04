package com.beag;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class DeNGui
{
    private JFrame frameDen;
    private final ButtonGroup buttonGroupUtf8 = new ButtonGroup();
    private final ButtonGroup buttonGroupUtf8ByteNum = new ButtonGroup();
    private final ButtonGroup buttonGroupUniCode = new ButtonGroup();
    private static String STR_ENCODE = "Encode";
    private static String STR_DECODE = "Decode";
    private static String STR_COPY = "Copy output to clipboard";
    String[] OPTIONS = new String[] { "URL (UTF-8)", "Hex (UTF-8)", "Decimal (UTF-8)", "Base64", "HTML 4", "XML", "UniCode" };

    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    DeNGui window = new DeNGui();
                    window.frameDen.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    private DeNGui()
    {
        initialize();
    }

    private void initialize()
    {
        this.frameDen = new JFrame();
        this.frameDen.setResizable(false);
        this.frameDen.setFont(new Font("Arial Unicode MS", 0, 12));
        this.frameDen.getContentPane().setFont(new Font("Arial Unicode MS", 0, 11));
        this.frameDen.setTitle("DeN");
        this.frameDen.setBounds(100, 100, 573, 450);
        this.frameDen.setDefaultCloseOperation(3);

        final JPanel panelDen = new JPanel();
        this.frameDen.getContentPane().add(panelDen, "Center");
        panelDen.setLayout(null);

        //Optional Panels
        final JPanel panel_utf8 = new JPanel();
        panel_utf8.setBounds(434, 104, 123, 168);
        panelDen.add(panel_utf8);
        panel_utf8.setLayout(null);
        panel_utf8.setVisible(false);

        final JPanel panel_utf8Bytes = new JPanel();
        panel_utf8Bytes.setBounds(0, 63, 123, 105);
        panel_utf8.add(panel_utf8Bytes);
        panel_utf8Bytes.setLayout(null);
        panel_utf8Bytes.setVisible(false);

        final JPanel panelUniCode = new JPanel();
        panelUniCode.setBounds(434, 104, 123, 168);
        panelDen.add(panelUniCode);
        panelUniCode.setLayout(null);
        panelUniCode.setVisible(false);

        //Optioinal Radio Buttons
        final JRadioButton rdbtnSpecialChars = new JRadioButton("Special Chars");
        rdbtnSpecialChars.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (rdbtnSpecialChars.isSelected()) {
                    panel_utf8Bytes.setVisible(false);
                }
            }
        });
        rdbtnSpecialChars.setSelected(true);
        this.buttonGroupUtf8.add(rdbtnSpecialChars);
        rdbtnSpecialChars.setBounds(6, 7, 109, 23);
        panel_utf8.add(rdbtnSpecialChars);

        final JRadioButton rdbtnAllChars = new JRadioButton("All Chars");
        rdbtnAllChars.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (rdbtnAllChars.isSelected()) {
                    panel_utf8Bytes.setVisible(true);
                }
            }
        });
        this.buttonGroupUtf8.add(rdbtnAllChars);
        rdbtnAllChars.setBounds(6, 33, 109, 23);
        panel_utf8.add(rdbtnAllChars);

        final JRadioButton rdbtn1Byte = new JRadioButton("1 byte");
        rdbtn1Byte.setBounds(6, 7, 111, 23);
        panel_utf8Bytes.add(rdbtn1Byte);
        rdbtn1Byte.setSelected(true);
        this.buttonGroupUtf8ByteNum.add(rdbtn1Byte);

        final JRadioButton rdbtn2bytes = new JRadioButton("2 bytes");
        rdbtn2bytes.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                rdbtn2bytes.isSelected();
            }
        });
        rdbtn2bytes.setBounds(6, 35, 111, 23);
        panel_utf8Bytes.add(rdbtn2bytes);
        this.buttonGroupUtf8ByteNum.add(rdbtn2bytes);

        final JRadioButton rdbtn3bytes = new JRadioButton("3 bytes");
        rdbtn3bytes.setBounds(6, 61, 111, 23);
        panel_utf8Bytes.add(rdbtn3bytes);
        this.buttonGroupUtf8ByteNum.add(rdbtn3bytes);

        //Unicode Buttons
        final JRadioButton radioButtonUniCodeU00 = new JRadioButton("\\u00");
        radioButtonUniCodeU00.setSelected(true);
        this.buttonGroupUniCode.add(radioButtonUniCodeU00);
        radioButtonUniCodeU00.setBounds(6, 7, 109, 23);
        panelUniCode.add(radioButtonUniCodeU00);

        final JRadioButton radioButtonUniCodeX = new JRadioButton("\\x");
        radioButtonUniCodeX.setSelected(false);
        this.buttonGroupUniCode.add(radioButtonUniCodeX);
        radioButtonUniCodeX.setBounds(6, 33, 109, 23);
        panelUniCode.add(radioButtonUniCodeX);

        //ComboBox
        final JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                if (comboBox.getSelectedIndex() == 0) {
                    panelUniCode.setVisible(false);
                    panel_utf8.setVisible(true);
                }
                else if(comboBox.getSelectedIndex() == 6){
                    panel_utf8.setVisible(false);
                    panelUniCode.setVisible(true);
                }
                else {
                    panel_utf8.setVisible(false);
                    panelUniCode.setVisible(false);
                }
            }
        });
        comboBox.setModel(new DefaultComboBoxModel<String>(OPTIONS));
        comboBox.setSelectedIndex(0);
        comboBox.setBounds(434, 44, 111, 24);
        panelDen.add(comboBox);

        JLabel lblEncodeDecode = new JLabel("Input");
        lblEncodeDecode.setFont(new Font("Tahoma", 0, 15));
        lblEncodeDecode.setBounds(14, 18, 123, 15);
        panelDen.add(lblEncodeDecode);

        final JTextArea jtextUserInput = new JTextArea();
        jtextUserInput.setToolTipText("");
        jtextUserInput.setFont(new Font("Arial Unicode MS", 0, 13));
        jtextUserInput.setText("");
        jtextUserInput.setLineWrap(true);
        jtextUserInput.setBounds(14, 44, 410, 137);
        panelDen.add(jtextUserInput);

        final JTextArea progOutput = new JTextArea();
        progOutput.setEditable(false);
        progOutput.setToolTipText("");
        progOutput.setFont(new Font("Arial Unicode MS", 0, 13));
        progOutput.setText("");
        progOutput.setLineWrap(true);
        progOutput.setBounds(14, 223, 410, 137);
        panelDen.add(progOutput);

        final JButton btnEncode = new JButton(STR_ENCODE);
        btnEncode.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                progOutput.setText("");
                //replace \n with CRLF as JTextArea defaults to \n
                String userInput = jtextUserInput.getText().replace("\n", "\r\n");

                if ((comboBox.getSelectedIndex() == 0) && (rdbtnSpecialChars.isSelected()))
                {
                    String encode = DeN.urlEncode(userInput);
                    progOutput.setText(encode);
                }
                else if ((comboBox.getSelectedIndex() == 0) && (rdbtnAllChars.isSelected()) && (rdbtn1Byte.isSelected()))
                {
                    String encode = DeN.utf8Encode(userInput, 1);
                    progOutput.setText(encode);
                }
                else if ((comboBox.getSelectedIndex() == 0) && (rdbtnAllChars.isSelected()) && (rdbtn2bytes.isSelected()))
                {
                    String encode = DeN.utf8Encode(userInput, 2);
                    progOutput.setText(encode);
                }
                else if ((comboBox.getSelectedIndex() == 0) && (rdbtnAllChars.isSelected()) && (rdbtn3bytes.isSelected()))
                {
                    String encode = DeN.utf8Encode(userInput, 3);
                    progOutput.setText(encode);
                }
                else if (comboBox.getSelectedIndex() == 1)
                {
                    String encode = DeN.hexEncode(userInput);
                    progOutput.setText(encode);
                }
                else if (comboBox.getSelectedIndex() == 2)
                {
                    String encode = DeN.decEncode(userInput);
                    progOutput.setText(encode);
                }
                else if (comboBox.getSelectedIndex() == 3)
                {
                    String encode = DeN.base64Encode(userInput);
                    progOutput.setText(encode);
                }
                else if (comboBox.getSelectedIndex() == 4)
                {
                    String encode = DeN.htmlEncode(userInput);
                    progOutput.setText(encode);
                }
                else if (comboBox.getSelectedIndex() == 5)
                {
                    String encode = DeN.xmlEncode(userInput);
                    progOutput.setText(encode);
                }
                else if (comboBox.getSelectedIndex() == 6 && radioButtonUniCodeU00.isSelected()){
                    String encode = DeN.unicodeEncode(userInput.toCharArray(), 1);
                    progOutput.setText(encode);
                }
                else if (comboBox.getSelectedIndex() == 6 && radioButtonUniCodeX.isSelected()){
                    String encode = DeN.unicodeEncode(userInput.toCharArray(), 2);
                    progOutput.setText(encode);
                }
                copyToClipboard(progOutput.getText());
                //displayDialog(frameDen, "Copied", true);
            }
        });
        btnEncode.setToolTipText(STR_COPY);
        btnEncode.setBounds(97, 386, 117, 25);
        panelDen.add(btnEncode);

        JButton btnDecode = new JButton(STR_DECODE);
        btnDecode.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (comboBox.getSelectedIndex() == 0)
                {
                    String decode = DeN.urlDecode(jtextUserInput.getText());
                    progOutput.setText(decode);
                }
                else if (comboBox.getSelectedIndex() == 1)
                {
                    String decode = DeN.hexDecode(jtextUserInput.getText());
                    progOutput.setText(decode);

                }
                else if (comboBox.getSelectedIndex() == 2)
                {
                    String decode = DeN.decDecode(jtextUserInput.getText());
                    progOutput.setText(decode);
                }
                else if (comboBox.getSelectedIndex() == 3)
                {
                    String decode = DeN.base64Decode(jtextUserInput.getText());
                    progOutput.setText(decode);
                }
                else if (comboBox.getSelectedIndex() == 4)
                {
                    String decode = DeN.htmlDecode(jtextUserInput.getText());
                    progOutput.setText(decode);
                }
                else if (comboBox.getSelectedIndex() == 5)
                {
                    String decode = DeN.xmlDecode(jtextUserInput.getText());
                    progOutput.setText(decode);
                }
                else if (comboBox.getSelectedIndex() == 6){
                    String decode = DeN.unicodeDecode(jtextUserInput.getText());
                    progOutput.setText(decode);
                }
                copyToClipboard(progOutput.getText());
            }
        });
        btnDecode.setToolTipText(STR_COPY);
        btnDecode.setBounds(234, 386, 117, 25);
        panelDen.add(btnDecode);

        JLabel lblOutput = new JLabel("Output");
        lblOutput.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblOutput.setBounds(14, 197, 123, 15);
        panelDen.add(lblOutput);
    }

    private static void copyToClipboard(String outputText){
        StringSelection stringSelection = new StringSelection (outputText);
        Clipboard clipBrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
        clipBrd.setContents (stringSelection, null);
    }

    private static void displayDialog(Frame swingFrame, String title, Boolean modality){
        final JDialog dialog = new JDialog(swingFrame, title, modality);
        Timer timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                dialog.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();
        dialog.setLocationRelativeTo(swingFrame);
        dialog.setBounds(swingFrame.getBounds().x, swingFrame.getBounds().y+200, swingFrame.getWidth(), 4);
        dialog.setVisible(true);
    }
}
