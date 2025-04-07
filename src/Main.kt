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

/**
 * The application class (model)
 * This is the place where any application data should be
 * stored, plus any application logic functions
 */

class App() {
    // Constants defining any key values

    val player1 = Player("",50,50,20,1)

    val bossList = mutableListOf<Boss>(
        Boss("Grimhollow Warden",100,20,10,1),
        Boss("Malakar, the Soulforged",200,20,100,2),
        Boss("Vaeltharoth, Devourer of Light",500,50,120,3)
    )

    var playerMana = player1.mana
    var playerHealth = player1.health

    var abilities = mutableListOf(
        Abilities("Fire Ball",10,5),
        Abilities("Ice Shard",30,20),
        Abilities("Rock Throw",15,10),
        Abilities("Air Cutter",5,5)
    )

}

class Abilities(val name: String,val damage: Int, val manaCost: Int){

}

class Boss(val name: String, var health: Int, var strength: Int, var agility: Int, val room: Int){


    // Updates the health value in case of damage taken
    fun takeDamage(damage: Int){
        health -= damage
        if (health < 0) health = 0
    }

    // Function to attack the user
    fun attackPlayer(player: Player){
        if (player.health > 0) {
            player.takeDamage(strength)
        }
        else{
            //
        }
    }

    // Function that handles dodging
    fun dodgePlayerAttack(){
        // Addition of time window needed and if button E is not pressed within time then Health
        // does not change and dialog saying the boss has dodged will be displayed Timer is
        // linked to agility
    }

}

class Player(val name: String, var health: Int, var mana: Int, var agility: Int, var level: Int){


    // Function that changes the user health
    fun takeDamage(damage: Int){
        health -= damage
        if (health < 0) health = 0
    }

    // Function to attack the Boss
    fun attackBoss(boss: Boss){
        if (boss.health > 0) {
            boss.takeDamage(mana)
        }
        else{
            // Return bossIsDefeated as True - Need to create a bossIsDefeated variable
        }
    }

    // Function that handles dodging
    fun dodgeBossAttack(){
        // Addition of time window needed and if button E is not pressed within time then
        // dodge is not used and the player is attacked.
    }
}

// Class to manage interactions between the player and boss private val ensures that only this class manages
// interaction between the two classes in the backend
class ManageInteractions(private val player: Player,private val boss: Boss){

    fun turnManagement(){

    }
}

fun main() {

    FlatDarkLaf.setup()
    val app = App()                // Create the app model
    MainWindow(app)              // Create and show the UI, using the app model

}


/**
 * Main UI window (view)
 * Defines the UI and responds to events
 * The app model should be passwd as an argument
 */

class MainWindow(val app: App) : JFrame(), ActionListener {

    // Fields to hold the UI elements
    private lateinit var playerMana: JLabel
    private lateinit var bossMana: JLabel
    private lateinit var playerHealth: JLabel
    private lateinit var bossHealth: JLabel
    private lateinit var bossRoom: JLabel

    private lateinit var ability1: JLabel
    private lateinit var ability2: JLabel
    private lateinit var ability3: JLabel
    private lateinit var ability4: JLabel
    /**
     * Configure the UI and display it
     */
    init {
        configureWindow()               // Configure the window
        addElements()                   // Build the UI
        addControls()

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
    private fun addElements() {
        val baseFont = Font(Font.SANS_SERIF, Font.PLAIN, 36)

        bossRoom = JLabel("")
        bossRoom.font = baseFont
        bossRoom.bounds = Rectangle(250,0,100,50)
        add(bossRoom)

        playerMana = JLabel("mana: ${app.playerMana}")
        playerMana.font = Font(Font.SANS_SERIF, Font.PLAIN, 8)
        playerMana.bounds = Rectangle(49,240,200,5)
        playerMana.background = Color.BLUE
        playerMana.isOpaque = true
        add(playerMana)

        playerHealth = JLabel("HP: ${app.playerHealth}")
        playerHealth.bounds = Rectangle(50,190,200,50)
        playerHealth.background = Color.RED
        playerHealth.isOpaque = true
        add(playerHealth)

        bossHealth = JLabel("HP: ")
        bossHealth.bounds = Rectangle(350,25,200,50)
        bossHealth.background = Color.RED
        bossHealth.isOpaque = true
        add(bossHealth)

        bossMana = JLabel("mana: ")
        bossMana.font = Font(Font.SANS_SERIF, Font.PLAIN, 8)
        bossMana.bounds = Rectangle(349,75,200,5)
        bossMana.background = Color.BLUE
        bossMana.isOpaque = true
        add(bossMana)

    }

    private fun addControls(){
        val baseFont = Font(Font.SANS_SERIF, Font.PLAIN, 36)

        ability1 = JLabel(app.abilities[0].name)
        ability1.font = baseFont
        ability1.bounds = Rectangle(0,250,295,50)
        ability1.background = Color.RED
        ability1.isOpaque = true
        add(ability1)

        ability2 = JLabel(app.abilities[1].name)
        ability2.font = baseFont
        ability2.bounds = Rectangle(295,250,305,50)
        ability2.background = Color.BLUE
        ability2.isOpaque = true
        add(ability2)

        ability3 = JLabel(app.abilities[2].name)
        ability3.font = baseFont
        ability3.bounds = Rectangle(0,300,295,50)
        ability3.background = Color.GREEN
        ability3.isOpaque = true
        add(ability3)

        ability4 = JLabel(app.abilities[3].name)
        ability4.font = baseFont
        ability4.bounds = Rectangle(295,300,305,50)
        ability4.background = Color.WHITE
        ability4.isOpaque = true
        add(ability4)

    }

    /**
     * Update the UI controls based on the current state
     * of the application model
     */
    fun updateView() {
        bossRoom.text = "${app.bossList[0].name}'s Lair"
    }

    /**
     * Handle any UI events (e.g. button clicks)
     * Usually this involves updating the application model
     * then refreshing the UI view
     */
    override fun actionPerformed(e: ActionEvent?) {
        when (e?.source) {

        }

    }
}


