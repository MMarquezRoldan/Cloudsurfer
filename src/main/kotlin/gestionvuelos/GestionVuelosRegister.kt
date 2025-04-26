package gestionvuelos

import java.awt.Color
import java.awt.Cursor
import java.awt.EventQueue
import java.awt.Font
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.ItemEvent
import java.awt.event.ItemListener
import javax.swing.*
import javax.swing.border.EmptyBorder
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

class GestionVuelosRegister : JFrame() {
    private val contentPane: JPanel
    private val txtID: JTextField
    private val txtSurname: JTextField
    private val txtName: JTextField
    private val passwordField: JPasswordField
    private val passwordFieldRepeat: JPasswordField
    private val btnRegisterButton: JButton
    private val chckbxConditions: JCheckBox

    /**
     * Create the frame.
     */
    init {
        setDefaultCloseOperation(EXIT_ON_CLOSE)
        setBounds(100, 100, 600, 500)
        contentPane = JPanel()
        contentPane.setBorder(EmptyBorder(5, 5, 5, 5))
        setContentPane(contentPane)
        contentPane.setLayout(null)
        setTitle("Register")

        val lblRegister = JLabel("Register")
        lblRegister.setFont(Font("Tahoma", Font.BOLD, 30))
        lblRegister.setBounds(227, 10, 129, 50)
        contentPane.add(lblRegister)

        val lblName = JLabel("Name")
        lblName.setFont(Font("Tahoma", Font.PLAIN, 14))
        lblName.setBounds(159, 70, 71, 27)
        contentPane.add(lblName)

        val lblSurname = JLabel("Surname")
        lblSurname.setFont(Font("Tahoma", Font.PLAIN, 14))
        lblSurname.setBounds(159, 107, 71, 27)
        contentPane.add(lblSurname)

        val lblId = JLabel("ID")
        lblId.setFont(Font("Tahoma", Font.PLAIN, 14))
        lblId.setBounds(159, 144, 71, 27)
        contentPane.add(lblId)

        val lblPassword = JLabel("Password")
        lblPassword.setFont(Font("Tahoma"   , Font.PLAIN, 14))
        lblPassword.setBounds(159, 262, 71, 27)
        contentPane.add(lblPassword)

        val lblRepeatPassword = JLabel("Repeat password")
        lblRepeatPassword.setFont(Font("Tahoma", Font.PLAIN, 14))
        lblRepeatPassword.setBounds(159, 299, 118, 27)
        contentPane.add(lblRepeatPassword)

        txtID = JTextField()
        txtID.setToolTipText("Introduce your ID")
        txtID.setColumns(10)
        txtID.setBounds(289, 150, 144, 19)
        contentPane.add(txtID)

        txtSurname = JTextField()
        txtSurname.setToolTipText("Introduce your surname")
        txtSurname.setColumns(10)
        txtSurname.setBounds(289, 113, 144, 19)
        contentPane.add(txtSurname)

        txtName = JTextField()
        txtName.setToolTipText("Introduce your name")
        txtName.setForeground(Color(0, 0, 0))
        txtName.setColumns(10)
        txtName.setBounds(289, 73, 144, 19)
        contentPane.add(txtName)

        passwordField = JPasswordField()
        passwordField.setToolTipText("Introduce your password")
        passwordField.setBounds(289, 268, 144, 19)
        contentPane.add(passwordField)

        passwordFieldRepeat = JPasswordField()
        passwordFieldRepeat.setToolTipText("Repeat your password")
        passwordFieldRepeat.setBounds(289, 305, 144, 19)
        contentPane.add(passwordFieldRepeat)

        val separator = JSeparator()
        separator.setBounds(159, 181, 274, 2)
        contentPane.add(separator)

        val lblLenght = JLabel("Min. length 8 characters")
        lblLenght.setBounds(289, 193, 144, 13)
        contentPane.add(lblLenght)

        val lblUpperCase = JLabel("At least 1 upper case")
        lblUpperCase.setBounds(289, 216, 144, 13)
        contentPane.add(lblUpperCase)

        val lblNumberRestriction = JLabel("At least 1 number")
        lblNumberRestriction.setBounds(289, 239, 144, 13)
        contentPane.add(lblNumberRestriction)

        btnRegisterButton = JButton("Register")
        btnRegisterButton.setEnabled(false) // deshabilitado por defecto
        btnRegisterButton.setBounds(262, 386, 85, 21)
        btnRegisterButton.setCursor(Cursor(Cursor.HAND_CURSOR))
        btnRegisterButton.addActionListener(object : ActionListener {
            override fun actionPerformed(e: ActionEvent?) {
                JOptionPane.showMessageDialog(
                    null,
                    "Registration has been completed",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
                )
                dispose()
            }
        })
        contentPane.add(btnRegisterButton)

        chckbxConditions = JCheckBox("I have read and agree to the")
        chckbxConditions.setBounds(159, 332, 188, 21)
        contentPane.add(chckbxConditions)

        val lblTermsConditions = JLabel("[Terms and Conditions].")
        lblTermsConditions.setBounds(353, 336, 157, 13)
        lblTermsConditions.setCursor(Cursor(Cursor.HAND_CURSOR))
        contentPane.add(lblTermsConditions)

        // Listener para campos de texto
        val docListener: DocumentListener = object : DocumentListener {
            override fun changedUpdate(e: DocumentEvent?) {
                checkFields()
            }

            override fun removeUpdate(e: DocumentEvent?) {
                checkFields()
            }

            override fun insertUpdate(e: DocumentEvent?) {
                checkFields()
            }
        }

        txtName.getDocument().addDocumentListener(docListener)
        txtSurname.getDocument().addDocumentListener(docListener)
        txtID.getDocument().addDocumentListener(docListener)
        passwordField.getDocument().addDocumentListener(docListener)
        passwordFieldRepeat.getDocument().addDocumentListener(docListener)

        chckbxConditions.addItemListener(ItemListener { e: ItemEvent? -> checkFields() })
    }

    private fun checkFields() {
        val allFieldsFilled =
            !txtName.getText().trim { it <= ' ' }.isEmpty() && !txtSurname.getText().trim { it <= ' ' }
                .isEmpty() && !txtID.getText().trim { it <= ' ' }
                .isEmpty() && passwordField.getPassword().size > 8 && passwordFieldRepeat.getPassword().size > 0

        val pass = String(passwordField.getPassword())
        val repeat = String(passwordFieldRepeat.getPassword())

        val passwordsMatch = pass == repeat
        val lengthOK = pass.length >= 8
        val hasUpper = pass.matches(".*[A-Z].*".toRegex())
        val hasNumber = pass.matches(".*\\d.*".toRegex())
        val checkBoxChecked = chckbxConditions.isSelected()

        val passwordValid = passwordsMatch && lengthOK && hasUpper && hasNumber

        btnRegisterButton.setEnabled(allFieldsFilled && passwordValid && checkBoxChecked)
    }

    companion object {
        private const val serialVersionUID = 1L

        /**
         * Launch the application.
         */
        @JvmStatic
        fun main(args: Array<String>) {
            EventQueue.invokeLater(object : Runnable {
                override fun run() {
                    try {
                        val frame = GestionVuelosRegister()
                        frame.setVisible(true)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
        }
    }
}
