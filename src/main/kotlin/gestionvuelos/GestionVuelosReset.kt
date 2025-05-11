package gestionvuelos

import java.awt.EventQueue
import java.awt.Font
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.sql.DriverManager
import java.sql.PreparedStatement
import javax.swing.*
import javax.swing.border.EmptyBorder

class GestionVuelosReset : JFrame() {
    private val contentPane: JPanel
    private val textField: JTextField
    private val passwordField: JPasswordField?
    private val passwordField_1: JPasswordField?

    init {
        setDefaultCloseOperation(EXIT_ON_CLOSE)
        setBounds(100, 100, 500, 350)
        setLocationRelativeTo(null)
        contentPane = JPanel()
        contentPane.setBorder(EmptyBorder(5, 5, 5, 5))
        setTitle("Reset password")
        isResizable = false

        setContentPane(contentPane)
        contentPane.setLayout(null)

        val lblId = JLabel("ID")
        lblId.setFont(Font("Tahoma", Font.PLAIN, 14))
        lblId.setBounds(109, 100, 107, 27)
        contentPane.add(lblId)

        val lblNewPassword = JLabel("New password")
        lblNewPassword.setFont(Font("Tahoma", Font.PLAIN, 14))
        lblNewPassword.setBounds(109, 137, 107, 27)
        contentPane.add(lblNewPassword)

        val lblRepeatPassword = JLabel("Repeat password")
        lblRepeatPassword.setFont(Font("Tahoma", Font.PLAIN, 14))
        lblRepeatPassword.setBounds(109, 174, 107, 27)
        contentPane.add(lblRepeatPassword)

        textField = JTextField()
        textField.setBounds(226, 106, 120, 19)
        contentPane.add(textField)
        textField.setColumns(10)

        passwordField = JPasswordField()
        passwordField.setBounds(226, 143, 120, 19)
        contentPane.add(passwordField)

        passwordField_1 = JPasswordField()
        passwordField_1.setBounds(226, 180, 120, 19)
        contentPane.add(passwordField_1)

        val btnReset = JButton("Reset")
        btnReset.setBounds(226, 221, 120, 21)
        btnReset.addActionListener {
            val url = "jdbc:mysql://localhost:3306/gestion_vuelos"
            val user = "admin"
            val dbPassword = ""

            try {
                Class.forName("com.mysql.cj.jdbc.Driver")
                val connection: java.sql.Connection = DriverManager.getConnection(url, user, dbPassword)
                val statement: PreparedStatement = connection.prepareStatement(
                    "UPDATE PASAJEROS SET CONTRASENA = ? WHERE ID_PASAJERO = ?"
                )
                statement.setString(1, String(passwordField.password))
                statement.setString(2, textField.getText())
                val rowsAffected = statement.executeUpdate()

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Password has been updated successfully",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                    )
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "No passenger found with that ID",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    )
                }

                val gestionVuelosLogin = GestionVuelosLogin()
                gestionVuelosLogin.isVisible = true

                dispose()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        contentPane.add(btnReset)

        val lblResetYourPassword = JLabel("Reset your password")
        lblResetYourPassword.setFont(Font("Tahoma", Font.BOLD, 30))
        lblResetYourPassword.setBounds(79, 10, 321, 50)
        contentPane.add(lblResetYourPassword)
    }

    companion object {
        private const val serialVersionUID = 1L

        @JvmStatic
        fun main(args: Array<String>) {
            EventQueue.invokeLater(object : Runnable {
                override fun run() {
                    try {
                        val frame = GestionVuelosReset()
                        frame.setVisible(true)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
        }
    }
}
