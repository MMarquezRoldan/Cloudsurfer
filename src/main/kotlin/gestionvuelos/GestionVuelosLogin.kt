package gestionvuelos

import java.awt.Cursor
import java.awt.EventQueue
import java.awt.Font
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.*
import javax.swing.border.EmptyBorder
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet

class GestionVuelosLogin : JFrame() {
    private val contentPane: JPanel
    private val textID: JTextField
    private val passwordField: JPasswordField
    private val btnLogin: JButton

    init {
        setDefaultCloseOperation(EXIT_ON_CLOSE)
        setBounds(100, 100, 600, 400)
        contentPane = JPanel()
        contentPane.setBorder(EmptyBorder(5, 5, 5, 5))
        setTitle("Client login")

        val hand = Cursor(Cursor.HAND_CURSOR)
        val reg = GestionVuelosRegister()

        setContentPane(contentPane)
        contentPane.setLayout(null)

        val lblLogin = JLabel("Client login")
        lblLogin.setFont(Font("Tahoma", Font.BOLD, 30))
        lblLogin.setBounds(193, 24, 180, 104)
        contentPane.add(lblLogin)

        val lblID = JLabel("ID")
        lblID.setFont(Font("Tahoma", Font.PLAIN, 14))
        lblID.setBounds(155, 138, 71, 27)
        contentPane.add(lblID)

        val lblPassword = JLabel("Password")
        lblPassword.setFont(Font("Tahoma", Font.PLAIN, 14))
        lblPassword.setBounds(155, 175, 71, 27)
        contentPane.add(lblPassword)

        textID = JTextField()
        textID.setBounds(236, 144, 170, 19)
        contentPane.add(textID)
        textID.setColumns(10)

        passwordField = JPasswordField()
        passwordField.setBounds(236, 181, 170, 19)
        contentPane.add(passwordField)

        btnLogin = JButton("Login")
        btnLogin.setBounds(236, 224, 85, 21)
        btnLogin.setCursor(hand)
        btnLogin.setEnabled(false)
        btnLogin.addActionListener(object : ActionListener {
            override fun actionPerformed(e: ActionEvent?) {
                checkFields()
                // Verificar si los campos están llenos y hacer algo (por ejemplo, imprimir en consola)
                if (textID.text.isNotEmpty() && passwordField.password.isNotEmpty()) {
                    validateLogin(textID.text, String(passwordField.password))  // Validar el login
                }
            }
        })
        contentPane.add(btnLogin)

        val lblForgotPassword = JLabel("Forgot password?")
        lblForgotPassword.setFont(Font("Tahoma", Font.BOLD, 10))
        lblForgotPassword.setBounds(236, 271, 170, 13)
        lblForgotPassword.setCursor(hand)
        lblForgotPassword.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent?) {
                val reset = GestionVuelosReset()
                reset.setVisible(true)
                dispose()
            }
        })
        contentPane.add(lblForgotPassword)

        val lblNewClient = JLabel("New client?")
        lblNewClient.setFont(Font("Tahoma", Font.PLAIN, 10))
        lblNewClient.setBounds(236, 294, 71, 13)
        contentPane.add(lblNewClient)

        val lblRegister = JLabel("Register")
        lblRegister.setFont(Font("Tahoma", Font.BOLD, 10))
        lblRegister.setBounds(302, 294, 71, 13)
        lblRegister.setCursor(hand)
        lblRegister.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent?) {
                reg.setVisible(true)
                dispose()
            }
        })
        contentPane.add(lblRegister)

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

        textID.getDocument().addDocumentListener(docListener)
        passwordField.getDocument().addDocumentListener(docListener)
    }

    private fun checkFields() {
        val allFieldsFilled = textID.text.isNotEmpty() && passwordField.password.isNotEmpty()

        btnLogin.isEnabled = allFieldsFilled
    }

    // validar login
    private fun validateLogin(userID: String, password: String) {
        val url = "jdbc:mysql://localhost:3306/gestion_vuelos"
        val user = "admin"
        val dbPassword = ""

        try {
            // Registrar el controlador JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver")

            val connection: Connection = DriverManager.getConnection(url, user, dbPassword)

            // Prepara la consulta SQL para verificar el ID y la contraseña
            val sql = "SELECT * FROM pasajeros WHERE id_pasajero = ?"
            val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(1, userID)
            val resultSet: ResultSet = preparedStatement.executeQuery()

            if (resultSet.next()) {
                // Verificar si la contraseña es correcta
                val storedPassword = resultSet.getString("contrasena")
                if (storedPassword == password) {
                    println("Login successful!")
                    // Aquí puedes redirigir a la siguiente ventana o realizar cualquier otra acción
                } else {
                    println("Incorrect password.")
                }
            } else {
                println("User ID does not exist.")
            }

            connection.close()
        } catch (e: Exception) {
            println("Error connecting to the database: ${e.message}")
        }
    }

    companion object {
        private const val serialVersionUID = 1L

        @JvmStatic
        fun main(args: Array<String>) {
            EventQueue.invokeLater(object : Runnable {
                override fun run() {
                    try {
                        val frame = GestionVuelosLogin()
                        frame.isVisible = true
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
        }
    }
}
