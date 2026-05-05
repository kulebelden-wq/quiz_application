package com.example.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "quiz.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_QUESTIONS = "questions";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_SCENARIO = "scenario";
    private static final String COLUMN_QUESTION = "question";
    private static final String COLUMN_OPTION1 = "option1";
    private static final String COLUMN_OPTION2 = "option2";
    private static final String COLUMN_OPTION3 = "option3";
    private static final String COLUMN_OPTION4 = "option4";
    private static final String COLUMN_ANSWER_INDEX = "answer_index";
    private static final String COLUMN_EXPLANATION = "explanation";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_QUESTIONS_TABLE = "CREATE TABLE " + TABLE_QUESTIONS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_CATEGORY + " TEXT,"
                + COLUMN_SCENARIO + " TEXT,"
                + COLUMN_QUESTION + " TEXT,"
                + COLUMN_OPTION1 + " TEXT,"
                + COLUMN_OPTION2 + " TEXT,"
                + COLUMN_OPTION3 + " TEXT,"
                + COLUMN_OPTION4 + " TEXT,"
                + COLUMN_ANSWER_INDEX + " INTEGER,"
                + COLUMN_EXPLANATION + " TEXT" + ")";

        db.execSQL(CREATE_QUESTIONS_TABLE);
        seedDatabase(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        onCreate(db);
    }

    private void seedDatabase(SQLiteDatabase db) {
        // Technology
        addQuestionToDb(db, new Question("Technology", "A developer wants to make their website look different on mobile and desktop using CSS.", "What is the best technique to use?", "Flexbox", "Media Queries", "JavaScript", "HTML5", 1, "Media Queries allow you to apply different styles based on the device's screen size."));
        addQuestionToDb(db, new Question("Technology", "Your computer is fast but runs out of space when you download big games.", "Which component should you upgrade?", "RAM", "CPU", "SSD/HDD", "GPU", 2, "Storage (SSD/HDD) is where files and games are saved long-term."));
        addQuestionToDb(db, new Question("Technology", "A company wants to store data that can be accessed from anywhere in the world instantly.", "What service should they use?", "Local Server", "Cloud Computing", "USB Drive", "Floppy Disk", 1, "Cloud computing allows data storage and processing over the internet from any location."));
        addQuestionToDb(db, new Question("Technology", "You receive an email from 'your bank' asking for your password urgently.", "What is this type of attack called?", "Spam", "DDoS", "Phishing", "Malware", 2, "Phishing is a deceptive attempt to steal sensitive information by posing as a trustworthy entity."));
        addQuestionToDb(db, new Question("Technology", "A smartphone app keeps crashing whenever the user rotates the screen.", "What is likely the developer's mistake?", "Low battery", "Memory Leak", "Activity Lifecycle management", "Poor WiFi", 2, "In Android, rotating the screen restarts the Activity; developers must handle this state change properly."));
        addQuestionToDb(db, new Question("Technology", "A programmer wants to store a list of names that won't change throughout the program.", "What is the best data structure to use?", "Array", "Constant/Immutable List", "Stack", "Queue", 1, "Immutable lists prevent accidental changes to data that should remain constant."));
        addQuestionToDb(db, new Question("Technology", "A user wants to connect their wireless headphones to their laptop.", "Which technology is most commonly used for this?", "Wi-Fi", "Bluetooth", "NFC", "Infrared", 1, "Bluetooth is the standard for short-range wireless communication between devices."));
        addQuestionToDb(db, new Question("Technology", "A website takes 10 seconds to load because the images are too large.", "How can the developer fix this?", "Buy a faster PC", "Compress the images", "Use more colors", "Add more text", 1, "Image compression reduces file size without significantly losing quality, leading to faster loading."));
        addQuestionToDb(db, new Question("Technology", "A laptop is getting very hot and making a loud whirring noise.", "What is most likely happening?", "Screen is too bright", "The fan is working hard to cool the CPU", "The battery is full", "The mouse is broken", 1, "Computers use fans to dissipate heat generated by the processor during intensive tasks."));
        addQuestionToDb(db, new Question("Technology", "You want to create an app that works on both iOS and Android with one codebase.", "Which framework would you choose?", "Swift", "Kotlin", "Flutter", "Java", 2, "Flutter and React Native are popular frameworks for cross-platform development."));
        addQuestionToDb(db, new Question("Technology", "What does 'HTTP' stand for?", "HyperText Transfer Protocol", "High Tech Transfer Path", "Hyper Terminal Text Print", "Hyperlink Total Task", 0, "It is the foundation of data communication for the World Wide Web."));
        addQuestionToDb(db, new Question("Technology", "Which is the primary language for Web logic?", "HTML", "CSS", "JavaScript", "Python", 2, "While HTML and CSS handle structure and style, JavaScript handles the logic and interactivity."));
        addQuestionToDb(db, new Question("Technology", "Who created Linux?", "Bill Gates", "Linus Torvalds", "Steve Jobs", "Mark Zuckerberg", 1, "Linus Torvalds created the Linux kernel in 1991."));
        addQuestionToDb(db, new Question("Technology", "Which company owns Android?", "Apple", "Microsoft", "Google", "Facebook", 2, "Google acquired Android Inc. in 2005 and has developed it since."));
        addQuestionToDb(db, new Question("Technology", "What is the brain of the computer?", "Hard Drive", "RAM", "CPU", "Motherboard", 2, "The Central Processing Unit (CPU) performs most of the processing inside a computer."));
        addQuestionToDb(db, new Question("Technology", "What does 'RAM' stand for?", "Read Access Memory", "Random Access Memory", "Real Action Mode", "Rapid Auto Memory", 1, "RAM is short-term memory where data is stored while it is being used."));
        addQuestionToDb(db, new Question("Technology", "1 Gigabyte is equal to how many Megabytes?", "100 MB", "500 MB", "1024 MB", "2048 MB", 2, "In digital storage, units typically follow the power of 2 (2^10 = 1024)."));
        addQuestionToDb(db, new Question("Technology", "Which is an example of 'Software'?", "Monitor", "Keyboard", "Microsoft Word", "Mouse", 2, "Software is a collection of instructions that tell the hardware what to do."));
        addQuestionToDb(db, new Question("Technology", "What does 'VPN' stand for?", "Virtual Private Network", "Very Private Node", "Visual Protocol Name", "Virtual Point Network", 0, "A VPN creates a secure, encrypted connection over a less secure network."));
        addQuestionToDb(db, new Question("Technology", "What is '404' on a website?", "Success", "Page Not Found", "Access Denied", "Server Error", 1, "404 is a standard HTTP status code indicating the server couldn't find the requested page."));

        // Mathematics
        addQuestionToDb(db, new Question("Mathematics", "A shop offers a 'Buy 1 Get 1 Half Price' deal on 20,000 shirts.", "How much do you pay for two shirts?", "20,000", "30,000", "40,000", "35,000", 1, "First shirt is 20,000. second is half price (10,000). 20,000 + 10,000 = 30,000."));
        addQuestionToDb(db, new Question("Mathematics", "You are driving at 60 km/h. Your destination is 120 km away.", "How many hours will the trip take?", "1 hour", "2 hours", "3 hours", "4 hours", 1, "Time = Distance / Speed. 120 / 60 = 2 hours."));
        addQuestionToDb(db, new Question("Mathematics", "A cake recipe requires 2 eggs for every 100g of flour. You have 300g of flour.", "How many eggs do you need?", "2", "4", "6", "8", 2, "Scale up the recipe: 100g (2 eggs), 200g (4 eggs), 300g (6 eggs)."));
        addQuestionToDb(db, new Question("Mathematics", "A rectangle has a length of 5m and a perimeter of 16m.", "What is the width of the rectangle?", "3m", "5m", "8m", "11m", 0, "Perimeter = 2(L+W). 16 = 2(5+W) -> 8 = 5+W -> W = 3."));
        addQuestionToDb(db, new Question("Mathematics", "A bank offers 10% simple interest per year. You deposit $100.", "How much money will you have after 1 year?", "$100", "$110", "$120", "$200", 1, "Interest = 10% of 100 = $10. Total = 100 + 10 = $110."));
        addQuestionToDb(db, new Question("Mathematics", "You slice a pizza into 8 equal pieces and eat 2.", "What fraction of the pizza is left?", "1/4", "1/2", "3/4", "5/8", 2, "You ate 2/8 (which is 1/4). 6/8 is left, which simplifies to 3/4."));
        addQuestionToDb(db, new Question("Mathematics", "A ladder is 10ft long and leans against a wall. The base is 6ft from the wall.", "How high does the ladder reach? (Pythagorean Theorem)", "4ft", "7ft", "8ft", "9ft", 2, "a^2 + b^2 = c^2 -> 6^2 + h^2 = 10^2 -> 36 + h^2 = 100 -> h^2 = 64 -> h = 8."));
        addQuestionToDb(db, new Question("Mathematics", "A bag contains 3 red balls and 2 blue balls. You pick one at random.", "What is the probability it is blue?", "20%", "40%", "50%", "60%", 1, "Probability = Favored / Total. 2 / (3+2) = 2/5 = 0.4 or 40%."));
        addQuestionToDb(db, new Question("Mathematics", "You have UGX 50,000 and spend UGX 12,500 on lunch and UGX 7,500 on a movie.", "How much change do you have?", "UGX 30,000", "UGX 20,00", "UGX 40,000", "UGX 35,000", 1, "Total spent = 12,500 + 7,500 = 20,000. Remaining = 50 - 20 = 30."));
        addQuestionToDb(db, new Question("Mathematics", "A clock shows 3:00.", "What is the angle between the hour and minute hand?", "45 degrees", "90 degrees", "180 degrees", "0 degrees", 1, "At 3:00, the minute hand is at 12 and the hour hand is at 3, forming a right angle."));
        addQuestionToDb(db, new Question("Mathematics", "What is 12 x 12?", "124", "144", "164", "122", 1, "12 squared is 144."));
        addQuestionToDb(db, new Question("Mathematics", "Solve: 2 + 2 x 2", "8", "4", "6", "10", 2, "Using BODMAS/PEMDAS, multiply first: 2 x 2 = 4, then add: 2 + 4 = 6."));
        addQuestionToDb(db, new Question("Mathematics", "What is the value of Pi (to 2 decimal places)?", "3.12", "3.14", "3.16", "3.20", 1, "Pi is approximately 3.14159..."));
        addQuestionToDb(db, new Question("Mathematics", "How many degrees are in a triangle?", "90", "180", "360", "270", 1, "The interior angles of any triangle always sum to 180 degrees."));
        addQuestionToDb(db, new Question("Mathematics", "What is the square root of 81?", "7", "8", "9", "10", 2, "9 x 9 = 81."));
        addQuestionToDb(db, new Question("Mathematics", "What is 10% of 500?", "5", "50", "100", "500", 1, "10/100 * 500 = 50."));
        addQuestionToDb(db, new Question("Mathematics", "How many sides does a heptagon have?", "6", "7", "8", "9", 1, "A heptagon has seven sides."));
        addQuestionToDb(db, new Question("Mathematics", "What is the only even prime number?", "0", "2", "4", "6", 1, "2 is the only prime number that is even."));
        addQuestionToDb(db, new Question("Mathematics", "Solve for x: 2x - 4 = 10", "3", "5", "7", "14", 2, "2x = 14 -> x = 7."));
        addQuestionToDb(db, new Question("Mathematics", "What is 1/2 + 1/4?", "1/6", "2/4", "3/4", "1/8", 2, "Common denominator: 2/4 + 1/4 = 3/4."));

        // Physics
        addQuestionToDb(db, new Question("Physics", "On the Moon, an astronaut drops a feather and a hammer from the same height simultaneously.", "Which hits the ground first?", "Feather", "Hammer", "Both at the same time", "Depends on strength", 2, "On the Moon, there's no air resistance, and gravity accelerates all objects equally regardless of mass."));
        addQuestionToDb(db, new Question("Physics", "A car doubles its speed from 10 m/s to 20 m/s.", "How does its kinetic energy change?", "Doubles", "Triples", "Quadruples", "Stays the same", 2, "KE = ½mv². If v doubles, v² quadruples, so KE quadruples."));
        addQuestionToDb(db, new Question("Physics", "A helium balloon is floating inside a closed car. When the car accelerates forward, the balloon moves:", "Which direction?", "Forward", "Backward", "Sideways", "Doesn't move", 0, "The air inside the car shifts backward (heavier than helium), pushing the lighter balloon forward."));
        addQuestionToDb(db, new Question("Physics", "Why does a microwave heat food but not the plastic plate?", "What is the reason?", "Plastic reflects microwaves", "Water molecules absorb microwaves; plastic lacks water", "Plastic is too thin", "Microwaves only target proteins", 1, "Microwaves cause water molecules to vibrate, producing heat. Plastic contains almost no water."));
        addQuestionToDb(db, new Question("Physics", "A boat has a small hole at the bottom. As water leaks in, the boat sinks lower.", "What happens to the water pressure at the hole?", "Increases", "Decreases", "Stays the same", "Becomes zero", 0, "Pressure = ρgh. As the boat sinks lower, h (depth) increases, so pressure increases."));
        addQuestionToDb(db, new Question("Physics", "You have two identical bulbs in series. One burns out (breaks).", "The other bulb:", "Gets brighter", "Gets dimmer", "Turns off", "Stays the same", 2, "In series, current has only one path. If one bulb breaks, the circuit is open, so no current flows."));
        addQuestionToDb(db, new Question("Physics", "At the top of a loop, why don't passengers fall out even when upside down?", "What force is responsible?", "Seatbelt holds them", "Centripetal force > gravity", "Gravity disappears at top", "Air pressure holds them", 1, "The normal force from the track plus gravity provides centripetal force. At sufficient speed, the normal force keeps them pressed against the seat."));
        addQuestionToDb(db, new Question("Physics", "If you leave the refrigerator door open in a sealed room, the room temperature will:", "What happens?", "Decrease", "Increase", "Stay the same", "First decrease then increase", 1, "The refrigerator motor produces waste heat. It moves heat from inside to outside coils, but motor inefficiency adds extra heat to the room."));
        addQuestionToDb(db, new Question("Physics", "A magician lies on hundreds of sharp nails without injury. Why?", "Explain the physics.", "Nails are blunt", "Weight is distributed over many nails, reducing pressure", "Magic", "He is very light", 1, "Pressure = Force/Area. More nails = more area = less pressure per nail."));
        addQuestionToDb(db, new Question("Physics", "An ice cube floats in a glass of water with a piece of lead frozen inside it. When the ice melts, the water level:", "What happens to the level?", "Rises", "Falls", "Stays the same", "Cannot determine", 1, "The lead is denser than water. While frozen, it displaces more water than its own volume. After melting, the lead sinks and displaces less water."));
        addQuestionToDb(db, new Question("Physics", "You hear thunder 5 seconds after seeing lightning. How far away is the lightning?", "(Speed of sound = 340 m/s)", "340 m", "680 m", "1700 m", "3400 m", 2, "Distance = speed × time = 340 × 5 = 1700 m (1.7 km)."));
        addQuestionToDb(db, new Question("Physics", "A pendulum is swinging on Earth. If taken to the Moon (lower gravity), its period will:", "What happens to the period?", "Increase", "Decrease", "Stay the same", "Become zero", 0, "T = 2π√(L/g). Lower g means larger T (slower swing)."));
        addQuestionToDb(db, new Question("Physics", "A charged electroscope's leaves repel. If you touch it with your finger, the leaves:", "What happens?", "Repel more", "Fall together", "Explode", "Glow", 1, "Your body provides a path to ground, discharging the electroscope. Leaves no longer repel."));
        addQuestionToDb(db, new Question("Physics", "White light passes through a prism and splits into colors.", "Which color bends the most?", "Red", "Green", "Blue", "Violet", 3, "Violet light has the shortest wavelength and slows down the most in glass, bending the most."));
        addQuestionToDb(db, new Question("Physics", "A strong magnet is dropped through a copper pipe.", "It falls:", "Faster than gravity", "At normal gravity speed", "Slower than gravity", "Stops completely", 2, "Eddy currents induced in the copper create a magnetic field that opposes the motion (Lenz's law), causing magnetic braking."));
        addQuestionToDb(db, new Question("Physics", "You have a half-full water bottle. You spin it vertically.", "At the top of the loop, water:", "Pours out", "Stays in", "Compresses", "Boils", 1, "Centripetal force pushes water toward the bottom of the bottle at the top of the loop if spun fast enough."));
        addQuestionToDb(db, new Question("Physics", "A thermos keeps hot liquids hot by minimizing heat transfer.", "Which method is NOT used?", "Vacuum between walls", "Silvered walls", "Tight stopper", "Electric heating element", 3, "Thermos is passive insulation, not active heating."));
        addQuestionToDb(db, new Question("Physics", "A boat is in a small pond with an anchor resting on the bottom. If you pull the anchor into the boat, the water level in the pond:", "What happens?", "Rises", "Falls", "Stays the same", "Depends on anchor weight", 0, "Anchor on bottom displaces only its volume. In boat, it displaces its weight in water (more volume because anchor denser than water). So level rises."));
        addQuestionToDb(db, new Question("Physics", "A CD shows rainbow colors when light hits it. This is due to:", "What is the phenomenon?", "Pigments", "Diffraction grating", "Refraction", "Absorption", 1, "The CD's microscopic grooves act as a diffraction grating, splitting light into colors."));
        addQuestionToDb(db, new Question("Physics", "Two identical balls are dropped from same height. One bounces perfectly elastically, one sticks to ground.", "Which impulse is greater on the ball?", "Bouncing ball", "Sticking ball", "Same impulse", "Depends on height", 0, "Impulse = change in momentum. Bouncing ball reverses direction (Δp = 2mv). Sticking ball goes to zero (Δp = mv)."));

        // Riddles
        addQuestionToDb(db, new Question("Riddles", "A man buys a parrot that he was told can speak every word. After months, the parrot hasn't spoken a word. The man isn't angry. Why?", "What is the reason?", "The parrot is mute", "The parrot speaks but man is deaf", "The parrot is dead", "The parrot was lying", 1, "The riddle says 'can speak every word' but doesn't say the man can hear."));
        addQuestionToDb(db, new Question("Riddles", "You have three light bulbs in a room. Three switches outside control them. You can only enter once.", "How do you know which switch controls which bulb?", "Turn switch 1 on, wait, turn off, turn switch 2 on, enter: on=2, warm=1, cold=3", "Turn all on, then enter", "Turn one on, enter immediately", "Ask someone inside", 0, "Uses heat from bulb 1 to identify it even when off."));
        addQuestionToDb(db, new Question("Riddles", "A man needs to cross a river with a wolf, a goat, and a cabbage. Boat holds only him and one item. Wolf eats goat, goat eats cabbage if left alone.", "Minimum trips?", "5", "7", "9", "11", 1, "Take goat, return, take wolf, bring goat back, take cabbage, return, take goat."));
        addQuestionToDb(db, new Question("Riddles", "Two doors: one leads to heaven, one to hell. Two guards: one always lies, one always tells truth. One question to one guard.", "What do you ask?", "Are you the truth-teller?", "Which door would other guard say leads to heaven? then take opposite", "Is this heaven door?", "What's 2+2?", 1, "Both guards point to hell door, so opposite is heaven."));
        addQuestionToDb(db, new Question("Riddles", "A man lives on the 10th floor. Every day he takes elevator to ground floor.", "When returning, he takes elevator to 7th floor and walks up 3 floors. Why?", "He likes exercise", "He is too short to reach button 10", "Elevator broken", "Button 10 is missing", 1, "He can only reach button 7. Ground floor button 1 is low enough."));
        addQuestionToDb(db, new Question("Riddles", "A king has 1000 bottles, one poisoned. Poison kills in 24 hours. He has slaves to test.", "Minimum slaves to find poisoned bottle in 24 hours?", "10", "100", "500", "999", 0, "2^10 = 1024 combinations. Each slave drinks from bottles where that bit position = 1."));
        addQuestionToDb(db, new Question("Riddles", "You're in a room with no windows or doors, only a table and mirror.", "How do you escape?", "Break mirror", "Look in mirror, see what you saw, take saw, cut table, two halves make a whole, climb through hole", "Wait", "Pray", 1, "Wordplay: 'see what you saw' (saw tool), 'whole' (hole)."));
        addQuestionToDb(db, new Question("Riddles", "Three prisoners wear black or white hats. Each sees others but not own. They guess simultaneously. If at least one correct, all freed.", "Best strategy?", "Random guess", "If see two same, guess opposite; if different, pass", "Always guess white", "Stay silent", 1, "Guarantees 75% success rate in the standard version of this puzzle."));
        addQuestionToDb(db, new Question("Riddles", "Three friends pay $10 each ($30). Hotel is $25, clerk gives $5 refund. Bellhop gives $1 each, keeps $2.", "Each paid $9 = $27, plus bellhop $2 = $29. Where's the missing dollar?", "No missing dollar, math error", "Bellhop stole it", "Clerk has it", "Friends lost it", 0, "$27 paid includes $25 room + $2 bellhop. Adding $2 again is double-counting."));
        addQuestionToDb(db, new Question("Riddles", "You meet two people. A says: 'B is a knave.' B says: 'We are both knaves.'", "What are they?", "Both knights", "Both knaves", "A knight, B knave", "A knave, B knight", 2, "If B were knight, statement would be false. So B is knave. Then A's statement is true, so A is knight."));
        addQuestionToDb(db, new Question("Riddles", "You have 4-minute and 7-minute hourglasses.", "How to measure exactly 9 minutes?", "Start both, when 4 ends flip it, when 7 ends flip it, when 4 ends again = 9 min", "Use 7 twice", "Use 4 three times", "Impossible", 0, "0: start, 4: flip 4, 7: flip 7, 8: 4 ends, 9: 7 ends."));
        addQuestionToDb(db, new Question("Riddles", "A man is found dead in a room with a puddle of water and broken glass. No wounds.", "How did he die?", "Poisoned", "Drowned in the puddle", "Was a fish whose bowl broke", "Heart attack", 2, "The 'man' was a goldfish."));
        addQuestionToDb(db, new Question("Riddles", "How many people needed for >50% chance two share a birthday (not year)?", "How many?", "183", "23", "50", "100", 1, "With 23 people, probability ≈ 50.7%."));
        addQuestionToDb(db, new Question("Riddles", "You have 9 coins, one counterfeit lighter.", "Balance scale, minimum weighings?", "1", "2", "3", "4", 1, "Divide into 3 groups of 3. Weigh two groups. If equal, fake in third; if not, lighter side. Then weigh 2 of those 3."));
        addQuestionToDb(db, new Question("Riddles", "Four people need to cross a bridge at night. One torch needed. Speeds: 1, 2, 5, 10 min. Max 2 at a time.", "Minimum time?", "17 min", "18 min", "19 min", "20 min", 0, "1&2 (2), 1 (1), 5&10 (10), 2 (2), 1&2 (2) = 17 min."));
        addQuestionToDb(db, new Question("Riddles", "Two fathers and two sons go fishing. They catch three fish. Each eats one whole fish. No leftovers.", "How?", "One didn't eat", "They are grandfather, father, son", "They shared", "Fish were small", 1, "Grandfather, father, and son make 3 people (two fathers and two sons)."));
        addQuestionToDb(db, new Question("Riddles", "Hilbert's Hotel has infinite rooms, all full. A new guest arrives.", "How to accommodate?", "Say no vacancy", "Move each guest to room n+1, free room 1", "Build new hotel", "Share rooms", 1, "Infinite sets can be shifted to create space."));
        addQuestionToDb(db, new Question("Riddles", "You have two eggs and a 100-floor building.", "Minimum drops to find highest floor egg doesn't break?", "14", "50", "99", "7", 0, "Optimal strategy uses floors 14, 27, 39, ... to minimize worst-case drops."));
        addQuestionToDb(db, new Question("Riddles", "Same as wolf, goat, cabbage (fox, chicken, grain).", "Which is NOT a correct first move?", "Take chicken", "Take fox", "Take grain", "Go alone", 1, "Taking fox leaves chicken with grain (chicken eats grain)."));
        addQuestionToDb(db, new Question("Riddles", "A bulb in a basement. Three switches upstairs. One controls bulb. You can only go down once.", "How?", "Turn switch 1 on for 10 min, off, turn switch 2 on, go down: on=2, warm=1, cold=3", "Turn all on", "Turn one on and go", "Use a mirror", 0, "Heat identifies bulb controlled by switch 1."));

        // IQ Test
        addQuestionToDb(db, new Question("IQ Test", "What is the next number in the sequence: 2, 6, 12, 20, ?", "What is next?", "24", "28", "30", "36", 2, "Differences: 4, 6, 8, so next difference 10 → 20+10=30."));
        addQuestionToDb(db, new Question("IQ Test", "You have a 5-gallon and 3-gallon jug.", "How to measure exactly 4 gallons?", "Fill 5, pour to 3, empty 3, pour remaining 2 to 3, fill 5, pour to 3 until full → 4 left in 5", "Fill 3, pour to 5, fill 3, pour to 5 until full (1 left), empty 5, pour 1 to 5, fill 3, pour to 5 → 4", "Both a and b", "Neither", 2, "Both methods work to achieve exactly 4 gallons."));
        addQuestionToDb(db, new Question("IQ Test", "An 8×8 chessboard missing opposite corners.", "Can 31 dominoes (2 squares each) cover it?", "Yes", "No", "Yes if rotated", "Only with 32", 1, "Opposite corners are same color. Missing them leaves 32 of one color, 30 of other. Impossible."));
        addQuestionToDb(db, new Question("IQ Test", "Statement: 'This sentence is false.'", "This is an example of:", "Paradox", "Tautology", "Fact", "Prediction", 0, "If true, it's false. If false, it's true. Self-contradictory."));
        addQuestionToDb(db, new Question("IQ Test", "Two trains 100 km apart approach at 50 km/h each. Bird flies 75 km/h back and forth until they meet.", "Distance bird flies?", "75 km", "100 km", "50 km", "150 km", 0, "Trains meet in 1 hour. Bird flies 75 km/h × 1 h = 75 km."));
        addQuestionToDb(db, new Question("IQ Test", "Three doors: one car, two goats. You pick door 1. Host opens door 3 showing a goat.", "Should you switch to door 2?", "Yes, chance increases from 1/3 to 2/3", "No, 50/50 now", "Yes, chance increases to 1/2", "Stay, chance 1/3", 0, "Host revealing a goat means the other closed door now holds the remaining 2/3 probability."));
        addQuestionToDb(db, new Question("IQ Test", "What is the next number: 1, 11, 21, 1211, ?", "What is next?", "1231", "111221", "312211", "131221", 1, "Look-and-say sequence: 1 (one 1) → 11 (two 1s) → 21 (one 2, one 1) → 1211 → 111221."));
        addQuestionToDb(db, new Question("IQ Test", "You have 12 coins, one counterfeit (weight different, could be lighter or heavier).", "Minimum weighings on balance scale?", "2", "3", "4", "5", 1, "3 weighings can identify counterfeit and whether lighter/heavier among 12 coins."));
        addQuestionToDb(db, new Question("IQ Test", "A father is three times as old as his son. In 10 years, he will be twice as old.", "How old is the son now?", "5", "10", "15", "20", 1, "3x+10 = 2(x+10) → 3x+10=2x+20 → x=10."));
        addQuestionToDb(db, new Question("IQ Test", "You meet two people. One says: 'At least one of us is a liar.'", "What are they?", "Both truth-tellers", "Both liars", "One truth, one liar", "Cannot determine", 0, "If first were liar, statement would be false (both truth-tellers) - contradiction. So first is truth-teller."));
        addQuestionToDb(db, new Question("IQ Test", "A bat and ball cost $1.10 total. Bat costs $1 more than ball.", "How much is ball?", "5 cents", "10 cents", "1 cent", "50 cents", 0, "x+(x+1)=1.10 → 2x=0.10 → x=0.05."));
        addQuestionToDb(db, new Question("IQ Test", "What is the missing number: 16, 06, 68, 88, ?, 98", "What is the number?", "78", "87", "89", "77", 1, "Turn the numbers upside down: sequence becomes 86, 87, 88, 89, 90, 91."));
        addQuestionToDb(db, new Question("IQ Test", "Three boxes: apples, oranges, mixed. All labels wrong. You pick one fruit from one box to find all.", "Which box to pick from?", "Labeled 'apples'", "Labeled 'oranges'", "Labeled 'mixed'", "Any box", 2, "Mixed box must be purely one fruit. Finding that one identifies all boxes."));
        addQuestionToDb(db, new Question("IQ Test", "Lock clues: 682 (1 right pos), 614 (1 right wrong pos), 206 (2 right wrong pos), 738 (none), 380 (1 right wrong pos).", "What is code?", "042", "062", "082", "012", 0, "Deduction from clues: 7,3,8 eliminated; 0,4,2 remain in correct positions."));
        addQuestionToDb(db, new Question("IQ Test", "A says 'B is knight.' B says 'A and C same type.' C says 'A is knave.'", "How many knights?", "0", "1", "2", "3", 1, "Testing A as knight leads to contradiction. A is knave, which makes C knight and B knave."));
        addQuestionToDb(db, new Question("IQ Test", "Measure 15 minutes using 7-minute and 11-minute hourglasses.", "How?", "Start both, flip 7 when done, flip 11 when done, flip 7 when 11 done", "Use 11 twice", "Use 7 thrice", "Impossible", 0, "0: start, 7: flip 7, 11: flip 11, 14: 7 ends, 15: 11 ends."));
        addQuestionToDb(db, new Question("IQ Test", "Two ropes burn unevenly but each takes 60 min. How to measure 45 min?", "What is the method?", "Light rope 1 both ends and rope 2 one end. When 1 done, light other end of 2", "Fold ropes", "Cut ropes", "Impossible", 0, "Rope 1 (both ends) = 30 min. Rope 2 (both ends after 30 min) = 15 min. Total 45."));
        addQuestionToDb(db, new Question("IQ Test", "Mr. Smith murdered Sunday 8 PM. A: 'Watching movie 8-10 PM.' B: 'Jogging 7-9 PM.' C: 'Sleeping 7-9 PM.' D: 'Cooking 8-10 PM.' Movie started at 8:30.", "Who is lying?", "A", "B", "C", "D", 0, "Movie started at 8:30, so A couldn't be watching it at 8 PM."));
        addQuestionToDb(db, new Question("IQ Test", "Three switches, three bulbs in basement. Each controls one. Go down once. Bulbs off initially.", "How?", "Turn switch 1 on for 10 min, off, turn 2 on, go down: on=2, warm=1, cold=3", "Turn all on", "Turn one on and go", "Use a mirror", 0, "Heat allows you to distinguish the switch that was on for 10 minutes but is now off."));
        addQuestionToDb(db, new Question("IQ Test", "What is the next number: 5, 7, 11, 13, ?", "What is next?", "15", "17", "19", "21", 1, "Prime numbers starting from 5: 5, 7, 11, 13, 17."));
    }

    private void addQuestionToDb(SQLiteDatabase db, Question question) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORY, question.getCategory());
        values.put(COLUMN_SCENARIO, question.getScenario());
        values.put(COLUMN_QUESTION, question.getQuestion());
        values.put(COLUMN_OPTION1, question.getOption1());
        values.put(COLUMN_OPTION2, question.getOption2());
        values.put(COLUMN_OPTION3, question.getOption3());
        values.put(COLUMN_OPTION4, question.getOption4());
        values.put(COLUMN_ANSWER_INDEX, question.getAnswerIndex());
        values.put(COLUMN_EXPLANATION, question.getExplanation());
        db.insert(TABLE_QUESTIONS, null, values);
    }

    public void addQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();
        addQuestionToDb(db, question);
        db.close();
    }

    public List<Question> getQuestionsByCategory(String category) {
        List<Question> questionList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        
        Cursor cursor = db.query(TABLE_QUESTIONS, null, COLUMN_CATEGORY + "=?",
                new String[]{category}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Question question = new Question(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SCENARIO)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_QUESTION)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OPTION1)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OPTION2)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OPTION3)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OPTION4)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ANSWER_INDEX)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXPLANATION))
                );
                questionList.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return questionList;
    }

    public boolean isDatabaseEmpty() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_QUESTIONS, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count == 0;
    }
}
