/**
 * =====================================================================
 * Programming Project for NCEA Level 3, Standard 91906
 * ---------------------------------------------------------------------
 * Project Name:   PROJECT NAME HERE
 * Project Author: PROJECT AUTHOR HERE
 * GitHub Repo:    GITHUB REPO URL HERE
 * ---------------------------------------------------------------------
 * Notes:
 * PROJECT NOTES HERE
 * =====================================================================
 */



import com.formdev.flatlaf.FlatDarkLaf
import java.awt.*
import java.awt.event.*
import javax.swing.*


/**
 * Launch the application
 */
fun main() {
    FlatDarkLaf.setup()     // Flat, dark look-and-feel
    val app = App()         // Create the app model
    MainWindow(app)         // Create and show the UI, using the app model

}


/**
 * The application class (model)
 * This is the place where any application data should be
 * stored, plus any application logic functions
 */
class App() {
    // Constants defining any key values
    val planets = mutableListOf<String>()
    var currentPlanet = 0

    init {
        planets.add("Mercury")
        planets.add("Venus")
        planets.add("Earth")
        planets.add("Mars")
        planets.add("Jupiter")
        planets.add("Saturn")
        planets.add("Uranus")
        planets.add("Neptune")
    }

    // Functions that give logic to the app (scrolling through the list)

    fun nextPlanet(){
        currentPlanet++
        if (currentPlanet > planets.size - 1) currentPlanet = planets.size - 1
    }

    fun prevPlanet(){
        currentPlanet--
        if (currentPlanet < 0) currentPlanet = 0
    }


}


/**
 * Main UI window (view)
 * Defines the UI and responds to events
 * The app model should be passwd as an argument
 */
class MainWindow(val app: App) : JFrame(), ActionListener {

    // Fields to hold the UI elements
    private lateinit var textField: JTextField
    private lateinit var rightButton: JButton
    private lateinit var leftButton: JButton
    private lateinit var planetLabel: JLabel
    private lateinit var planetInfo: JLabel
    /**
     * Configure the UI and display it
     */
    init {
        configureWindow()               // Configure the window
        addControls()                   // Build the UI

        setLocationRelativeTo(null)     // Centre the window
        isVisible = true                // Make it visible

        updateView()                    // Initialise the UI
    }

    /**
     * Configure the main window
     */
    private fun configureWindow() {
        title = "Kotlin Swing GUI Demo"
        contentPane.preferredSize = Dimension(600, 350)
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        isResizable = false
        layout = null

        pack()
    }

    /**
     * Populate the UI with UI controls
     */
    private fun addControls() {
        val baseFont = Font(Font.SANS_SERIF, Font.PLAIN, 36)

        planetLabel = JLabel()
        planetLabel.font = baseFont
        planetLabel.bounds = Rectangle(50,50,250,100)
        add(planetLabel)

        planetInfo = JLabel("PlanetInfo")
        planetInfo.font = baseFont
        planetInfo.bounds = Rectangle(300,50,500,100)
        add(planetInfo)

        textField = JTextField()
        textField.horizontalAlignment = SwingConstants.CENTER
        textField.bounds = Rectangle(300, 200, 250, 100)
        textField.font = baseFont
        add(textField)

        leftButton = JButton("◀")
        leftButton.bounds = Rectangle(50,200,100,100)
        leftButton.font = baseFont
        leftButton.addActionListener(this)
        add(leftButton)

        rightButton = JButton("▶")
        rightButton.bounds = Rectangle(175,200,100,100)
        rightButton.font = baseFont
        rightButton.addActionListener(this)     // Handle any clicks
        add(rightButton)

    }


    /**
     * Update the UI controls based on the current state
     * of the application model
     */
    fun updateView() {
        val planetName = app.planets[app.currentPlanet]

        planetLabel.text = "${planetName}:"

        rightButton.isEnabled = (app.currentPlanet >= 0)
        leftButton.isEnabled = (app.currentPlanet < app.planets.size)
    }

    /**
     * Handle any UI events (e.g. button clicks)
     * Usually this involves updating the application model
     * then refreshing the UI view
     */
    override fun actionPerformed(e: ActionEvent?) {
        when (e?.source) {
            rightButton -> {
                // Moves to the next planet in the planets list
                app.nextPlanet()
                // Update the GUI
                updateView()
            }
            leftButton -> {
                app.prevPlanet()
                updateView()
            }
        }
    }



}

