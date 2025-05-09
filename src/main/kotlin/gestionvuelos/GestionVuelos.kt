package gestionvuelos

import java.awt.EventQueue
import java.awt.Font
import javax.swing.*
import javax.swing.border.EmptyBorder

class GestionVuelos(username: String?) : JFrame() {
    private val contentPane: JPanel

    /**
     * Create the frame.
     */
    init {
        setTitle("Gestión de vuelos")
        setDefaultCloseOperation(EXIT_ON_CLOSE)
        setBounds(100, 100, 585, 498)

        val menuBar = JMenuBar()
        setJMenuBar(menuBar)

        val mnNewMenu_2 = JMenu("Inicio")
        menuBar.add(mnNewMenu_2)

        val mntmNewMenuItem_4 = JMenuItem("Exportar vuelos...")
        mnNewMenu_2.add(mntmNewMenuItem_4)

        val mntmNewMenuItem_5 = JMenuItem("Importar vuelos...")
        mnNewMenu_2.add(mntmNewMenuItem_5)

        val separator = JSeparator()
        mnNewMenu_2.add(separator)

        val mntmNewMenuItem_6 = JMenuItem("Salir")
        mnNewMenu_2.add(mntmNewMenuItem_6)

        val mnNewMenu_1 = JMenu("Gestión")
        menuBar.add(mnNewMenu_1)

        val mntmNewMenuItem_9 = JMenuItem("Nuevo vuelo")
        mnNewMenu_1.add(mntmNewMenuItem_9)

        val mntmNewMenuItem_10 = JMenuItem("Modificar vuelo")
        mnNewMenu_1.add(mntmNewMenuItem_10)

        val mntmNewMenuItem_3 = JMenuItem("Cancelar vuelo")
        mnNewMenu_1.add(mntmNewMenuItem_3)

        val mnNewMenu_3 = JMenu("Ventana")
        menuBar.add(mnNewMenu_3)

        val mnNewMenu_4 = JMenu("Apariencia")
        mnNewMenu_3.add(mnNewMenu_4)

        val mntmNewMenuItem_7 = JMenuItem("Modo oscuro")
        mnNewMenu_4.add(mntmNewMenuItem_7)

        val mntmNewMenuItem_8 = JMenuItem("Ajustes")
        mnNewMenu_3.add(mntmNewMenuItem_8)

        val mnNewMenu = JMenu("Help")
        menuBar.add(mnNewMenu)

        val mntmNewMenuItem = JMenuItem("Atención al cliente")
        mnNewMenu.add(mntmNewMenuItem)

        val mntmNewMenuItem_2 = JMenuItem("Novedades")
        mnNewMenu.add(mntmNewMenuItem_2)

        val mntmNewMenuItem_1 = JMenuItem("Versión")
        mnNewMenu.add(mntmNewMenuItem_1)
        contentPane = JPanel()
        contentPane.setBorder(EmptyBorder(5, 5, 5, 5))

        setContentPane(contentPane)
        contentPane.setLayout(null)

        val lblUsername = JLabel("Nombre de usuario:")
        lblUsername.setFont(Font("Tahoma", Font.BOLD, 14))
        lblUsername.setBounds(20, 10, 161, 23)
        contentPane.add(lblUsername)

        val lblNewLabel = JLabel(username)
        lblNewLabel.setBounds(179, 17, 109, 13)
        contentPane.add(lblNewLabel)

        val textArea = JTextArea()
        textArea.setBounds(20, 76, 522, 339)
        contentPane.add(textArea)

        val lblVuelos = JLabel("Vuelos")
        lblVuelos.setFont(Font("Tahoma", Font.BOLD, 18))
        lblVuelos.setBounds(20, 43, 85, 23)
        contentPane.add(lblVuelos)
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
                        val frame = GestionVuelos("test")
                        frame.setVisible(true)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
        }
    }
}
