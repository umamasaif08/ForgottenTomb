import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ============================================================================
 * THE FORGOTTEN TOMB - A Choose Your Own Adventure Text Game
 * ============================================================================
 * A complete, single-file interactive console RPG with branching narratives,
 * inventory management, health tracking, atmospheric storytelling, input
 * validation, and multiple distinct endings.
 * ============================================================================
 */
public class TheForgottenTomb {

    // ==========================================
    // GAME STATE & PLAYER DATA
    // ==========================================
    private static int health = 100;
    private static List<String> inventory = new ArrayList<>();
    private static boolean gameOver = false;
    private static boolean gameWon = false;

    // ANSI Colors for immersive console styling (fallback gracefully in standard terminals)
    private static final String RESET  = "\u001B[0m";
    private static final String RED    = "\u001B[31m";
    private static final String GREEN  = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE   = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN   = "\u001B[36m";
    private static final String BOLD   = "\u001B[1m";

    // ==========================================
    // MAIN ENTRY POINT & REPLAY LOOP
    // ==========================================
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        printTitleBanner();

        while (playAgain) {
            resetGameState();
            playGame(scanner);

            // Replay Prompt with robust input validation
            System.out.println("\n" + CYAN + "============================================================" + RESET);
            System.out.println(BOLD + "The expedition has concluded." + RESET);
            System.out.print("Dare you venture into the tomb once more? (yes/no): ");

            while (true) {
                String response = scanner.nextLine().trim().toLowerCase();
                if (response.equals("yes") || response.equals("y")) {
                    playAgain = true;
                    System.out.println("\n" + YELLOW + "--- Resetting expedition gear and rewinding time... ---\n" + RESET);
                    break;
                } else if (response.equals("no") || response.equals("n")) {
                    playAgain = false;
                    System.out.println("\n" + GREEN + "Thank you for playing 'The Forgotten Tomb'! Until next time, brave explorer." + RESET);
                    break;
                } else {
                    System.out.print(RED + "Invalid response. Please type 'yes' or 'no': " + RESET);
                }
            }
        }

        scanner.close();
    }

    /**
     * Resets player statistics and inventory for a fresh playthrough.
     */
    private static void resetGameState() {
        health = 100;
        inventory.clear();
        gameOver = false;
        gameWon = false;
    }

    // ==========================================
    // CORE STORYLINE & SCENE CONTROLLER
    // ==========================================
    private static void playGame(Scanner scanner) {
        scene1_TombEntrance(scanner);

        if (!gameOver) {
            scene2_WhisperingCatacombs(scanner);
        }

        if (!gameOver) {
            scene3_ChamberOfTrials(scanner);
        }

        if (!gameOver) {
            scene4_ChasmOfJudgement(scanner);
        }

        if (!gameOver) {
            scene5_InnerSanctum(scanner);
        }
    }

    // ------------------------------------------------------------------------
    // SCENE 1: THE TOMB ENTRANCE / SUNKEN ANTECHAMBER
    // ------------------------------------------------------------------------
    private static void scene1_TombEntrance(Scanner scanner) {
        printSceneHeader("SCENE I: The Sunken Antechamber");
        System.out.println(
            "Heavy stone grinds behind you, sealing out the scorching desert sun forever.\n" +
            "The air inside is icy and thick with millennia of dry decay and sulfur.\n" +
            "Carvings of jackal-headed gods glare from the basalt walls, their painted eyes gleaming in the dark."
        );

        displayStatus();

        System.out.println("\nWhat would you like to do?");
        System.out.println("1. Search the crumbling alcove covered in spiderwebs.");
        System.out.println("2. Light your spare match to inspect the grand obsidian door straight ahead.");
        System.out.println("3. Descend the mossy, flooded staircase spiraling down into the darkness.");

        int choice = getValidIntInput(scanner, 1, 3);

        switch (choice) {
            case 1:
                System.out.println("\n" + YELLOW + "You reach into the crumbling alcove." + RESET);
                System.out.println("Your fingers brush against cold, carved lapis lazuli. You pull out a pristine " +
                                   CYAN + "[Golden Scarab Amulet]" + RESET + " etched with protective solar hieroglyphs!");
                addItem("Golden Scarab Amulet");
                System.out.println("Feeling emboldened, you proceed down the corridor toward the subterranean halls.");
                pause(1200);
                break;

            case 2:
                System.out.println("\n" + YELLOW + "You strike a match and approach the obsidian door." + RESET);
                System.out.println("A hidden dart trap clicks beneath your boot! A poison dart grazes your shoulder!");
                takeDamage(25);
                System.out.println("Gasping through the burning sting, you spot a heavy brass " +
                                   CYAN + "[Ancient Torch]" + RESET + " resting in a sconce and quickly grab it before moving on.");
                addItem("Ancient Torch");
                pause(1200);
                break;

            case 3:
                System.out.println("\n" + YELLOW + "You carefully step onto the slick, damp staircase." + RESET);
                System.out.println("Water trickles around your boots as the subterranean humidity clings to your skin.");
                System.out.println("You move swiftly into the lower depths without disturbing any tomb wards.");
                pause(1200);
                break;
        }
    }

    // ------------------------------------------------------------------------
    // SCENE 2: THE WHISPERING CATACOMBS
    // ------------------------------------------------------------------------
    private static void scene2_WhisperingCatacombs(Scanner scanner) {
        printSceneHeader("SCENE II: The Whispering Catacombs");
        System.out.println(
            "Knee-deep, murky water sloshes against your legs as you enter a cavernous hall of submerged crypts.\n" +
            "Ghostly whispers echo from the flooded niches, repeating a rhythmic chant in an extinct tongue.\n" +
            "In the center floats a rotting ceremonial barge carrying a gilded chest adorned with ruby fangs."
        );

        displayStatus();

        System.out.println("\nWhat will you do?");
        System.out.println("1. Wade toward the floating barge to loot the ruby-encrusted chest.");
        System.out.println("2. Chant along with the ghostly whispers to show reverence.");
        System.out.println("3. Ignore the barge and hurry along the raised stone ledge to the next vault.");

        int choice = getValidIntInput(scanner, 1, 3);

        switch (choice) {
            case 1:
                if (hasItem("Ancient Torch")) {
                    System.out.println("\n" + YELLOW + "You raise your Ancient Torch high over the black water." + RESET);
                    System.out.println("The bright flame illuminates a swarm of venomous water vipers lurking below!");
                    System.out.println("Using the torch to keep them at bay, you safely retrieve the " +
                                       CYAN + "[Ruby Serpent Key]" + RESET + " from inside the chest!");
                    addItem("Ruby Serpent Key");
                } else {
                    System.out.println("\n" + RED + "You blindly wade into the pitch-black water towards the chest." + RESET);
                    System.out.println("Fierce water serpents strike at your legs from the shadows!");
                    takeDamage(35);
                    if (health > 0) {
                        System.out.println("You violently kick them away and scramble to the chest, desperately snatching the " +
                                           CYAN + "[Ruby Serpent Key]" + RESET + " before fleeing!");
                        addItem("Ruby Serpent Key");
                    }
                }
                pause(1200);
                break;

            case 2:
                System.out.println("\n" + PURPLE + "You hum along with the eerie, ancient rhythm." + RESET);
                if (hasItem("Golden Scarab Amulet")) {
                    System.out.println("The Golden Scarab vibrates against your chest, emitting a soothing warmth.");
                    System.out.println("The spirits recognize you as an anointed priest! They mend your wounds.");
                    heal(20);
                } else {
                    System.out.println("Your pronunciation is horribly flawed! The spectral voices shriek in anger, rattling your skull!");
                    takeDamage(20);
                }
                pause(1200);
                break;

            case 3:
                System.out.println("\n" + YELLOW + "You stick strictly to the high stone ledge, avoiding the dark water completely." + RESET);
                System.out.println("While you find no treasure, you escape the damp hall unscathed.");
                pause(1200);
                break;
        }

        if (health <= 0) {
            triggerEnding("BAD_DROWNED");
        }
    }

    // ------------------------------------------------------------------------
    // SCENE 3: THE CHAMBER OF TRIALS
    // ------------------------------------------------------------------------
    private static void scene3_ChamberOfTrials(Scanner scanner) {
        printSceneHeader("SCENE III: The Chamber of Trials");
        System.out.println(
            "Massive sandstone pillars tower into the gloom, wrapped in thick iron chains.\n" +
            "At the center sits a pedestal bearing a riddle written in glowing red runes upon a stone dial.\n" +
            "A heavy iron gate bars the exit, guarded by two towering terracotta sentinels holding scythes."
        );

        displayStatus();

        System.out.println("\nThe inscription on the dial reads:");
        System.out.println(BOLD + "\"I speak without a mouth and hear without ears. I have no body, but I come alive with wind. What am I?\"" + RESET);
        System.out.println("1. Turn the dial to: [FIRE]");
        System.out.println("2. Turn the dial to: [AN ECHO]");
        System.out.println("3. Turn the dial to: [A SHADOW]");
        System.out.println("4. Try to smash the mechanism with your brute strength.");

        int choice = getValidIntInput(scanner, 1, 4);

        switch (choice) {
            case 1:
            case 3:
                System.out.println("\n" + RED + "The dial clicks into place with a hollow thud. WRONG!" + RESET);
                System.out.println("Jets of boiling steam shoot from the floor grates!");
                takeDamage(30);
                if (health > 0) {
                    System.out.println("With the trap exhausted, the emergency release triggers and the iron gate reluctantly opens.");
                } else {
                    triggerEnding("BAD_STEAM");
                }
                pause(1200);
                break;

            case 2:
                System.out.println("\n" + GREEN + "The dial slides smoothly to 'ECHO' with a melodious chime!" + RESET);
                System.out.println("The terracotta sentinels lower their scythes in salute, and the gate swings open!");
                System.out.println("A small hidden compartment pops open, revealing a sparkling " +
                                   CYAN + "[Elixir of Vitality]" + RESET + "!");
                addItem("Elixir of Vitality");
                heal(30);
                pause(1200);
                break;

            case 4:
                System.out.println("\n" + RED + "You strike the ancient mechanism with all your might." + RESET);
                System.out.println("A ceiling counterweight snaps! A boulder crashes down, crushing your arm before rolling away.");
                takeDamage(45);
                if (health > 0) {
                    System.out.println("The shattered wall exposes a crawlspace leading to the next chamber.");
                } else {
                    triggerEnding("BAD_CRUSHED");
                }
                pause(1200);
                break;
        }
    }

    // ------------------------------------------------------------------------
    // SCENE 4: THE CHASM OF JUDGEMENT
    // ------------------------------------------------------------------------
    private static void scene4_ChasmOfJudgement(Scanner scanner) {
        printSceneHeader("SCENE IV: The Chasm of Judgement");
        System.out.println(
            "A bottomless chasm splits the mountain in two, shrouded in emerald phosphorescent fog.\n" +
            "A narrow bridge of floating, rune-etched floor stones spans the terrifying void.\n" +
            "One wrong step means plummeting into the forgotten underworld forever."
        );

        displayStatus();

        System.out.println("\nHow do you choose to cross the abyss?");
        System.out.println("1. Tread carefully across the stones marked with the Sun glyph.");
        System.out.println("2. Tread across the stones marked with the Moon glyph.");
        System.out.println("3. Attempt to sprint and leap across the stones at full speed.");

        if (hasItem("Golden Scarab Amulet")) {
            System.out.println("4. [SPECIAL] Hold up the Golden Scarab Amulet to reveal the celestial path.");
        }

        int maxChoice = hasItem("Golden Scarab Amulet") ? 4 : 3;
        int choice = getValidIntInput(scanner, 1, maxChoice);

        switch (choice) {
            case 1:
                System.out.println("\n" + GREEN + "You step onto the Sun tiles." + RESET);
                System.out.println("Golden light binds the stones firmly in place beneath your boots. You cross safely!");
                pause(1200);
                break;

            case 2:
                System.out.println("\n" + RED + "You step onto the Moon tiles." + RESET);
                System.out.println("The illusory stone dissipates into smoke under your weight!");
                System.out.println("You plunge into the abyss, your screams swallowed by the endless void.");
                health = 0;
                triggerEnding("BAD_CHASM");
                break;

            case 3:
                System.out.println("\n" + YELLOW + "You take a deep breath and sprint across the floating rocks!" + RESET);
                System.out.println("A tile slips beneath you! You lunge forward, barely grabbing the far ledge with bleeding fingers!");
                takeDamage(25);
                if (health > 0) {
                    System.out.println("You hoist yourself up onto solid ground, heart pounding like a drum.");
                } else {
                    triggerEnding("BAD_CHASM");
                }
                pause(1200);
                break;

            case 4:
                System.out.println("\n" + CYAN + "The Golden Scarab illuminates the true bridge in radiant, solid golden light!" + RESET);
                System.out.println("You walk across effortlessly as if strolling on marble floors.");
                pause(1200);
                break;
        }
    }

    // ------------------------------------------------------------------------
    // SCENE 5: THE INNER SANCTUM & MULTIPLE ENDINGS
    // ------------------------------------------------------------------------
    private static void scene5_InnerSanctum(Scanner scanner) {
        printSceneHeader("SCENE V: The Inner Sanctum of the Eternal Pharaoh");
        System.out.println(
            "You step into an opulent, breathtaking chamber overflowing with mounds of gold coins and diamond relics.\n" +
            "Atop an altar sits the legendary [Heart of Osiris] -- a pulsing ruby orb radiating ancient celestial power.\n" +
            "Suddenly, the grand sarcophagus slides open! The resurrected Pharaoh, wreathed in ghostly blue flames, steps forth!"
        );

        displayStatus();

        System.out.println("\nThe Pharaoh's booming voice echoes: " + BOLD + "\"MORTAL! Why have you desecrated my eternal rest?\"" + RESET);
        System.out.println("1. Grab as much gold as you can carry and sprint for the secret escape chute.");
        System.out.println("2. Draw your expedition blade and fight the Mummy Lord to the death.");
        System.out.println("3. Bow respectfully and offer the Heart of Osiris back to its rightful altar.");

        boolean hasSecretRequirements = hasItem("Golden Scarab Amulet") && hasItem("Ruby Serpent Key");
        if (hasSecretRequirements) {
            System.out.println("4. [SECRET] Present both the Golden Scarab and the Ruby Serpent Key in divine communion.");
        }

        int maxChoice = hasSecretRequirements ? 4 : 3;
        int choice = getValidIntInput(scanner, 1, maxChoice);

        switch (choice) {
            case 1:
                // Bad Ending: Greed
                System.out.println("\n" + RED + "You shovel fistfuls of cursed gold into your satchel and turn to flee." + RESET);
                System.out.println("The gold turns scorching hot, melding to your skin and turning your flesh into solid gold statues!");
                triggerEnding("BAD_GREED");
                break;

            case 2:
                // Combat outcome
                System.out.println("\n" + RED + "You charge forward, blade raised toward the towering immortal!" + RESET);
                if (health >= 60) {
                    System.out.println("With relentless courage and swift reflexes, you strike the Pharaoh's enchanted heart pendant!");
                    System.out.println("The Pharaoh dissolves into glittering stardust, leaving behind the legendary relic.");
                    triggerEnding("GOOD_VICTORY");
                } else {
                    System.out.println("Weakened from your injuries in the tomb, your strike falls short.");
                    System.out.println("The Pharaoh unleashes a shockwave of sand and decay, turning you to ash.");
                    triggerEnding("BAD_COMBAT");
                }
                break;

            case 3:
                // Good / Humble Ending
                System.out.println("\n" + GREEN + "You drop to one knee and gently place the artifact before the Pharaoh." + RESET);
                System.out.println("The Pharaoh's stern gaze softens into a majestic smile.");
                System.out.println("\"You possess wisdom rare among tomb-robbers. Take my blessing and go in peace.\"");
                triggerEnding("GOOD_SCHOLAR");
                break;

            case 4:
                // Secret Bonus Ending
                System.out.println("\n" + PURPLE + "You raise the Golden Scarab and insert the Ruby Serpent Key into the altar's celestial socket." + RESET);
                System.out.println("Cosmic light erupts through the chamber! The tomb transforms into a celestial observatory.");
                System.out.println("The Pharaoh recognizes you as the reincarnated High Astrologer of the Sun Dynasty!");
                triggerEnding("SECRET_ASCENSION");
                break;
        }
    }

    // ==========================================
    // ENDINGS DISPLAY
    // ==========================================
    private static void triggerEnding(String endingKey) {
        gameOver = true;
        System.out.println("\n" + BOLD + "============================================================" + RESET);

        switch (endingKey) {
            case "BAD_DROWNED":
                System.out.println(RED + BOLD + "[ENDING: Sunken Bones of the Deep]" + RESET);
                System.out.println("You were dragged into the submerged depths of the catacombs, joining the lost legion forever.");
                break;

            case "BAD_STEAM":
            case "BAD_CRUSHED":
                System.out.println(RED + BOLD + "[ENDING: Victim of the Ancients' Traps]" + RESET);
                System.out.println("The tomb's architectural defense mechanisms proved fatal. Your skeleton will warn future looters.");
                break;

            case "BAD_CHASM":
                System.out.println(RED + BOLD + "[ENDING: The Endless Fall]" + RESET);
                System.out.println("You tumbled into the subterranean abyss. No one will ever uncover your final resting place.");
                break;

            case "BAD_GREED":
                System.out.println(RED + BOLD + "[ENDING: The Golden Curse]" + RESET);
                System.out.println("Your insatiable greed bound your soul to the tomb forever as a gilded statue decorating the treasury.");
                break;

            case "BAD_COMBAT":
                System.out.println(RED + BOLD + "[ENDING: Fallen Warrior]" + RESET);
                System.out.println("You fought valiantly against an ancient supernatural deity, but the Pharaoh's power was too immense.");
                break;

            case "GOOD_VICTORY":
                gameWon = true;
                System.out.println(GREEN + BOLD + "[GOOD ENDING: Slayer of the Pharaoh]" + RESET);
                System.out.println("You escaped the crumbling tomb carrying the priceless [Heart of Osiris] and boundless fame!");
                System.out.println("Your name will be written into the history books as the greatest explorer of the century!");
                break;

            case "GOOD_SCHOLAR":
                gameWon = true;
                System.out.println(GREEN + BOLD + "[GOOD ENDING: The Respected Archaeologist]" + RESET);
                System.out.println("You left the tomb with the Pharaoh's blessing, priceless ancient manuscripts, and your humanity intact.");
                System.out.println("You return to civilization as a legendary scholar and guardian of lost mysteries!");
                break;

            case "SECRET_ASCENSION":
                gameWon = true;
                System.out.println(PURPLE + BOLD + "[SECRET ENDING: Celestial Sovereign of Eternity]" + RESET);
                System.out.println("You unlocked the ultimate cosmic mystery of the ancients and ascended as an immortal guardian.");
                System.out.println("You now command the celestial secrets of the stars across eternity!");
                break;
        }

        System.out.println(BOLD + "============================================================" + RESET);
    }

    // ==========================================
    // HELPER METHODS: INVENTORY, STATS, UI
    // ==========================================
    private static void addItem(String item) {
        inventory.add(item);
        System.out.println(GREEN + ">> Acquired: [" + item + "]" + RESET);
    }

    private static boolean hasItem(String item) {
        return inventory.contains(item);
    }

    private static void takeDamage(int amount) {
        health -= amount;
        if (health < 0) health = 0;
        System.out.println(RED + ">> You took " + amount + " damage! (Health: " + health + "/100)" + RESET);
    }

    private static void heal(int amount) {
        health += amount;
        if (health > 100) health = 100;
        System.out.println(GREEN + ">> You recovered " + amount + " health! (Health: " + health + "/100)" + RESET);
    }

    private static void displayStatus() {
        System.out.println("\n" + CYAN + "------------------------------------------------------------" + RESET);
        String healthColor = (health > 50) ? GREEN : (health > 25 ? YELLOW : RED);
        System.out.print("Status: [Health: " + healthColor + health + "/100" + RESET + "] | ");
        System.out.print("Inventory: ");
        if (inventory.isEmpty()) {
            System.out.println(YELLOW + "Empty" + RESET);
        } else {
            System.out.println(GREEN + String.join(", ", inventory) + RESET);
        }
        System.out.println(CYAN + "------------------------------------------------------------" + RESET);
    }

    private static void printSceneHeader(String title) {
        System.out.println("\n" + CYAN + "============================================================" + RESET);
        System.out.println(BOLD + YELLOW + "  " + title.toUpperCase() + RESET);
        System.out.println(CYAN + "============================================================" + RESET);
    }

    private static void printTitleBanner() {
        System.out.println(CYAN + "============================================================" + RESET);
        System.out.println(YELLOW + BOLD + "                * * * THE FORGOTTEN TOMB * * *             " + RESET);
        System.out.println(CYAN + "           A Choose Your Own Adventure Text Odyssey          " + RESET);
        System.out.println(CYAN + "============================================================" + RESET);
        System.out.println("Deep beneath the shifting dunes of the Black Desert lies an");
        System.out.println("unmapped burial complex lost to history for over 4,000 years.");
        System.out.println("Will you uncover immortality, claim unimaginable riches, or");
        System.out.println("become another forgotten skeleton in the dark?");
        System.out.println(CYAN + "============================================================\n" + RESET);
    }

    /**
     * Reads and validates an integer choice from the user within [min, max].
     * Never crashes on invalid input or unexpected characters.
     */
    private static int getValidIntInput(Scanner scanner, int min, int max) {
        while (true) {
            System.out.print(BOLD + "\nEnter your choice (" + min + "-" + max + "): " + RESET);
            String rawInput = scanner.nextLine().trim();

            if (rawInput.isEmpty()) {
                System.out.println(RED + "Please enter a valid choice number." + RESET);
                continue;
            }

            try {
                int val = Integer.parseInt(rawInput);
                if (val >= min && val <= max) {
                    return val;
                } else {
                    System.out.println(RED + "Choice out of range. Please choose a number between " + min + " and " + max + "." + RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(RED + "Invalid input '" + rawInput + "'. Please enter a numerical digit." + RESET);
            }
        }
    }

    /**
     * Small delay helper to improve narrative pacing.
     */
    private static void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }
}
