
import java.util.Scanner;
public class PokemonStatBlockGenerator {

   public static void main(String args[]){
   Scanner scnr = new Scanner(System.in); 
   double hp = 0;
   double atk = 0;
   double def = 0;
   double spa = 0;
   double spd = 0;
   double spe = 0;
   double bst = 0;
   String type1 = " ";
   String type2 = " ";
   double stage = 0;
   int size = 0;
   int largestStat = 0;
   int secondLargestStat = 0;
   int legendTier = 0;
   //gets Statline
   System.out.println("hp?");
   hp = scnr.nextDouble();
   System.out.println("atk?");
   atk = scnr.nextDouble();
   System.out.println("def?");
   def = scnr.nextDouble();
   System.out.println("spa?");
   spa = scnr.nextDouble();
   System.out.println("spd?");
   spd = scnr.nextDouble();
   System.out.println("spe?");
   spe = scnr.nextDouble();
   System.out.println("stage? 0 = basic. Note: this will HEAVILY Scale power. Should probably use 2 for BSTs over 500");
   stage = scnr.nextDouble();
   System.out.println("size? 1 = tiny, 2 = small, 3 = medium, 4 = large, 5 = huge, 6 = gargantuan");
   size = scnr.nextInt();
   System.out.println("Largest Stat? 1 = hp, 2 = atk, 3 = def, 4 = spa, 5 = spd, 6 = spe");
   largestStat = scnr.nextInt();
   System.out.println("Second Largest Stat? 1 = hp, 2 = atk, 3 = def, 4 = spa, 5 = spd, 6 = spe");
   secondLargestStat = scnr.nextInt();
   System.out.println("Legend Level? (0-6) 1 = Meloetta Level, 2 = Birds/Meloetta, 3 = Darkrai/Mewtwo, 4 = Groudon/Kyogre, 5 = Creation Trio, 6 = Arceus");
   legendTier = scnr.nextInt();
   System.out.println("First Type? Capitalize the first letter");
   scnr.nextLine();
   type1 = scnr.nextLine();
   System.out.println("Second Type? Type \"None\" if no second Type");
   type2 = scnr.nextLine();
   
   hp += legendTier * 20; //Legend Buff
   atk += legendTier * 20;
   def += legendTier * 20;
   spa += legendTier * 20;
   spd += legendTier * 20;
   spe += legendTier * 20;
   switch (legendTier) {
      case 3:
      stage +=1;
      break;
      case 5:
      stage +=2;
      break;
      case 6:
      stage +=3;
      break;
   
   }
   
   //Calculates BST
   bst = hp + atk + def + spa + spd + spe;
 
   
   //Calculates & prints CR
   double cr = (bst / 45) - (2 * (2 - stage));
   if (stage == 2) {
   cr +=1;
   }
   if (stage == 0) {
   cr -= 1;
   }
   if (cr < 1) {
   cr = 1; //Sketchy Scaling
   }
   
   cr = (int)cr; // Rounds this down
   System.out.println("Anticipated CR: " + (cr));
   
   double Str = 0;
   double Dex = 0;
   double Con = 0;
   double Int = 0;
   double Wis = 0;
   double Cha = 0;
   //Calculates and Sets Strength
   Str = (atk/7);
   if (size >= 3) {
   Str += (2 * (size - 3));
   }
   else
   {
   Str += (3 * (size - 3));
   }  
   if (Str > 25 && stage <= 2) {
   Str = 25;
   }
   
   //Calculates and Sets Dexterity
   Dex = (spe/5) - 1;
   if (size >= 3) {
   Dex -= (size - 3);
   }
   else
   {
   Dex -= (2 * (size - 3));
   }
   if (Dex > 25 && stage <= 2) {
   Dex = 25;
   }
   
   //Calculates and Sets Constitution
   Con = (hp / 6) + 2;
   if (hp >= 120) {
     Con = (hp / (6 + ((hp - 120)/40))) + 2; //Scales slower as hp increases over 120. 
   
   }
   if (Con >= 17) {
   Con -= 2;
   }
   if (size >= 3) { // Size Mod -1 for each below med
   Con += (1 * (size - 3));
   }
   else
   {
   Con += ((size - 3));
   }
   if (Con <= (12 + (cr - 10))) // Minimum of 12 + CR - 10
   {
   Con = (12 + (cr - 10));
   }
   
   //Calculates and Sets Intelligence
   Int = (spa / 6.5);
   if (Int < 8){ //Min 8
   Int = 8;
   }
   
   //Calculates and Sets Wisdom
   Wis = (spd / 6.5);
   
   //Calculates and sets Charisma
   Cha = (Wis + Int) / 2;
   
   //Calculates AC
   double ac = (((2*def)+ spd)/3)/6;
   if (ac < 10)
   {
   ac = 10;
   }
   
   
   //Mods based on highest Statistic and Legend Tier
   switch (largestStat){
      case 1:
      Con += 2 + (legendTier / 2);
      break;
      case 2:
      Str += 2 + (legendTier / 2);
      break;
      case 3:
      ac += 2 + (legendTier / 2);
      break;
      case 4:
      Int += 2 + (legendTier / 2);
      break;
      case 5:
      Wis += 2 + (legendTier / 2);
      break;
      case 6:
      Dex += 2 + (legendTier / 2);
      break;       
   }
    
   //Mods based on second highest Statistic
   switch (secondLargestStat){
      case 1:
      Con += 1;
      break;
      case 2:
      Str += 1;
      break;
      case 3:
      ac += 1;
      break;
      case 4:
      Int += 1;
      break;
      case 5:
      Wis += 1;
      break;
      case 6:
      Dex += 1;
      break;       
   }
   int acHPboost = 0;
   //Prints AC and performs final calculations
   ac = ac + ((Dex - 10) / 2);
   ac = Math.ceil(ac); //Rounds this up
   if (ac > 25) {
   acHPboost += (ac - 25);
   ac = 25; //caps AC at 25
   }
   System.out.println("Anticipated Armor Class: " + ac);
   
   //Calculates and prints Walking Speed
   System.out.println("Anticipated Walking Speed: " + spe/2 + " Round this to the nearest 5"); 
   
   //Calculates Hit Dice Size
   double hitDice = 0;
   switch (size){
      case 1:
      hitDice = 4;
      break;
      case 2:
      hitDice = 6;
      break;
      case 3:
      hitDice = 8;
      break;
      case 4:
      hitDice = 10;
      break;
      case 5:
      hitDice = 12;
      break;
      case 6:
      hitDice = 20;
      break;
      default:
      hitDice = size * 5;
      break;
      
      
   }
   //Calculates number of hit dice
   double numHitDice = 0;
   if (hp <= 110){
   numHitDice = hp/4.0;
   }
   else{
   numHitDice = hp/3;
   }
   if (numHitDice < (2*cr)){
   numHitDice = 2*cr;
   }
   numHitDice = (int)numHitDice + acHPboost; //Rounds This Down and adds 1 hit dice for each AC over 25
   
   
   
   //Prints Out Statline
   Str = Math.ceil(Str);
   Dex = Math.ceil(Dex);//Rounds These up
   Con = Math.ceil(Con);
   Int = Math.ceil(Int);
   Wis = Math.ceil(Wis);
   Cha = Math.ceil(Cha);
   
   
   double expectedHP = Math.round((((hitDice/2)+0.5)*numHitDice) + (Math.floor((Con-10)/2)*numHitDice));
   //Calculates and Prints HP
   System.out.println("Anticipated hp: " + expectedHP + "(" + numHitDice + "d" + hitDice + " + " + (Math.floor((Con-10)/2)*numHitDice) + ")");
   
   System.out.println("Statline: " + Str + ", " + Dex + ", " + Con + ", " + Int + ", " + Wis + ", " + Cha);
   System.out.println(""); //Prints Stats
   
   //Gets & Prints Special Abilities based on CR
   if (cr >= 11) {
      System.out.println("Magical Attacks and No Vulnerabilities");
   }
   if (cr >= 12) {
      System.out.println("Magical Resistance");
   }
   if (cr > 13) {
      double numActions = ((cr - 14) / 2) + 1;
      if (cr > 18) {
        numActions += Math.floor((cr - 18) / (3 + (0.25 * (cr - 18))));//Scales slower after three actions
      }
      System.out.println(Math.ceil(numActions) + " Legendary Actions");
   }
   if (spd > 80) {
      double numRes = (spd - 80) / 10;
      if (spd > 110) {
        numRes += Math.floor((spd - 110) / (10 + (0.3 * (spd - 110))));//Scales slower after three resistances
      }
      System.out.println(Math.floor(numRes) + " uses of Legendary Resistance");
   }
   if (spe > 99) {
      System.out.println("Evasion");
   }
   
   System.out.println("");
   
   //Resistances and Vulnerabilities
   int fire = 0;
   int bludgeoning = 0;
   int slashing = 0;
   int poison = 0;
   int lightning = 0;
   int psychic = 0;
   int piercing = 0;
   int cold = 0;
   int force = 0;
   int necrotic = 0;
   int nonMagic = 0;
   int radiant = 0;

   //Type 1 Calcs: Positive = Vulnerable
   switch (type1){
      case "Normal":
      bludgeoning += 1;
      force = -1000;
      break;
      case "Fire":
      fire -= 1;
      bludgeoning += 2;
      slashing -= 1;
      cold -= 1;
      radiant -= 1;
      nonMagic -= 1;
      break;
      case "Water":
      fire -= 1;
      bludgeoning -= 1;
      slashing += 1;
      cold -= 1;
      lightning += 1;
      nonMagic -= 1;
      break;
      case "Grass":
      fire += 1;
      bludgeoning -=2;
      lightning -=1;
      cold +=1;
      poison += 1;
      piercing += 1;
      break;
      case "Electric":
      lightning -=1;
      bludgeoning += 1;
      slashing -=1;
      nonMagic -=1;
      break;
      case "Ice":
      fire += 1;
      cold -= 1;
      bludgeoning += 1;
      piercing += 1;
      nonMagic += 1;
      break;
      case "Fighting":
      slashing += 1;
      psychic += 1;
      piercing -= 2;
      necrotic -= 1;
      radiant += 1;
      break;
      case "Poison":
      slashing -= 1;
      poison -=1;
      psychic +=1;
      piercing -=1;
      radiant -=1;
      break;
      case "Ground":
      bludgeoning += 1;
      slashing += 1;
      lightning = -1000;
      cold += 1;
      poison -= 1;
      piercing -=1;
      break;
      case "Flying":
      slashing -=1;
      lightning +=1;
      cold +=1;
      bludgeoning = -1000;
      break;
      case "Psychic":
      bludgeoning -= 1;
      psychic -=1;
      piercing +=1;
      force += 1;
      necrotic += 1;
      break;
      case "Bug":
      fire +=1;
      piercing +=1;
      bludgeoning -=2;
      break;
      case "Rock":
      fire -=1;
      bludgeoning +=3;
      poison -=1;
      break;
      case "Ghost":
      nonMagic = -1000;
      bludgeoning = -1000;
      poison -=1;
      piercing -=1;
      force += 1;
      necrotic +=1;
      break;
      case "Dragon":
      fire -=1;
      bludgeoning -=1;
      slashing -=1;
      lightning -=1;
      cold +=1;
      force +=1;
      radiant +=1;
      break;
      case "Dark":
      bludgeoning += 1;
      psychic = -1000;
      piercing += 1;
      force -=1;
      necrotic -=1;
      radiant +=1;
      break;
      case "Steel":
      nonMagic -= 2;
      fire += 1;
      slashing -=2;
      cold -=1;
      bludgeoning += 2;
      poison = -1000;
      psychic -= 1;
      piercing -=2;
      force -=1;
      radiant -=1;
      break;
      case "Fairy":
      bludgeoning -=1;
      poison +=1;
      piercing -=1;
      force = -1000;
      necrotic -=1;
      nonMagic +=1;
      break;
      default:
      System.out.println("error");
      
   }
   //Type 2 Calcs: Positive = Vulnerable
   switch (type2){
      case "Normal":
      bludgeoning += 1;
      force = -1000;
      break;
      case "Fire":
      fire -= 1;
      bludgeoning += 2;
      slashing -= 1;
      cold -= 1;
      radiant -= 1;
      nonMagic -= 1;
      break;
      case "Water":
      fire -= 1;
      bludgeoning -= 1;
      slashing += 1;
      cold -= 1;
      lightning += 1;
      nonMagic -= 1;
      break;
      case "Grass":
      fire += 1;
      bludgeoning -=2;
      lightning -=1;
      cold +=1;
      poison += 1;
      piercing += 1;
      break;
      case "Electric":
      lightning -=1;
      bludgeoning += 1;
      slashing -=1;
      nonMagic -=1;
      break;
      case "Ice":
      fire += 1;
      cold -= 1;
      bludgeoning += 1;
      piercing += 1;
      nonMagic += 1;
      break;
      case "Fighting":
      slashing += 1;
      psychic += 1;
      piercing -= 2;
      necrotic -= 1;
      radiant += 1;
      break;
      case "Poison":
      slashing -= 1;
      poison -=1;
      psychic +=1;
      piercing -=1;
      radiant -=1;
      break;
      case "Ground":
      bludgeoning += 1;
      slashing += 1;
      lightning = -1000;
      cold += 1;
      poison -= 1;
      piercing -=1;
      break;
      case "Flying":
      slashing -=1;
      lightning +=1;
      cold +=1;
      bludgeoning = -1000;
      break;
      case "Psychic":
      bludgeoning -= 1;
      psychic -=1;
      piercing +=1;
      force += 1;
      necrotic += 1;
      break;
      case "Bug":
      fire +=1;
      piercing +=1;
      bludgeoning -=2;
      break;
      case "Rock":
      fire -=1;
      bludgeoning +=3;
      poison -=1;
      break;
      case "Ghost":
      nonMagic = -1000;
      bludgeoning = -1000;
      poison -=1;
      piercing -=1;
      force += 1;
      necrotic +=1;
      break;
      case "Dragon":
      fire -=1;
      bludgeoning -=1;
      slashing -=1;
      lightning -=1;
      cold +=1;
      force +=1;
      radiant +=1;
      break;
      case "Dark":
      bludgeoning += 1;
      psychic = -1000;
      piercing += 1;
      force -=1;
      necrotic -=1;
      radiant +=1;
      break;
      case "Steel":
      nonMagic -= 2;
      fire += 1;
      slashing -=2;
      cold -=1;
      bludgeoning += 2;
      poison = -1000;
      psychic -= 1;
      piercing -=2;
      force -=1;
      radiant -=1;
      break;
      case "Fairy":
      bludgeoning -=1;
      poison +=1;
      piercing -=1;
      force = -1000;
      necrotic -=1;
      nonMagic +=1;
      break;
      case "None":
      break;
      default:
      System.out.println("error");
      
   }
      //Prints out Resistances 
      System.out.print("Resistances: ");
      if (fire < 0) {
      System.out.print("Fire ");
      }
      if (bludgeoning < 0) {
      System.out.print("Bludgeoning ");
      }
      if (slashing < 0) {
      System.out.print("Slashing ");
      }
      if (poison < 0) {
      System.out.print("Poison ");
      }
      if (lightning < 0) {
      System.out.print("Lightning ");
      }
      if (psychic < 0) {
      System.out.print("Psychic ");
      }
      if (piercing < 0) {
      System.out.print("Piercing ");
      }
      if (cold < 0) {
      System.out.print("Cold ");
      }
      if (force < 0) {
      System.out.print("Force ");
      }
      if (necrotic < 0) {
      System.out.print("Necrotic ");
      }
      if (nonMagic < 0) {
      System.out.print("Non-Magical Bludgeoning, Piercing, and Slashing ");
      }
      if (radiant < 0) {
      System.out.print("Radiant ");
      }
      System.out.println("");
      
      //Prints out Vulnerabilities 
      System.out.print("Vulnerabilties: ");
      if (fire > 0) {
      System.out.print("Fire ");
      }
      if (bludgeoning > 0) {
      System.out.print("Bludgeoning ");
      }
      if (slashing > 0) {
      System.out.print("Slashing ");
      }
      if (poison > 0) {
      System.out.print("Poison ");
      }
      if (lightning > 0) {
      System.out.print("Lightning ");
      }
      if (psychic > 0) {
      System.out.print("Psychic ");
      }
      if (piercing > 0) {
      System.out.print("Piercing ");
      }
      if (cold > 0) {
      System.out.print("Cold ");
      }
      if (force > 0) {
      System.out.print("Force ");
      }
      if (necrotic > 0) {
      System.out.print("Necrotic ");
      }
      if (nonMagic > 0) {
      System.out.print("Non-Magical Bludgeoning, Piercing, and Slashing ");
      }
      if (radiant > 0) {
      System.out.print("Radiant ");
      }
      System.out.println("");
      
      //Prints out Immunities 
      System.out.print("Immunities: ");
      if (fire < -100) {
      System.out.print("Fire ");
      }
      if (bludgeoning < -100) {
      System.out.print("Bludgeoning ");
      }
      if (slashing < -100) {
      System.out.print("Slashing ");
      }
      if (poison < -100) {
      System.out.print("Poison ");
      }
      if (lightning < -100) {
      System.out.print("Lightning ");
      }
      if (psychic < -100) {
      System.out.print("Psychic ");
      }
      if (piercing < -100) {
      System.out.print("Piercing ");
      }
      if (cold < -100) {
      System.out.print("Cold ");
      }
      if (force < -100) {
      System.out.print("Force ");
      }
      if (necrotic < -100) {
      System.out.print("Necrotic ");
      }
      if (nonMagic < -100) {
      System.out.print("Non-Magical Bludgeoning, Piercing, and Slashing ");
      }
      if (radiant < -100) {
      System.out.print("Radiant ");
      }
      System.out.println("");
   
      //Calculates and Prints Defensive CR
      int defensiveCR = 0;
      if (stage == 0) {
      expectedHP += (cr * (stage + 1) * 15) + 15; //Sketchy Expected HP Scaling
      }
      if (stage == 1) {
      expectedHP += (cr * (stage + 1) * 5) + 15;
      }
      
      
      int i = 0;
      for (i = 41; i < expectedHP; i+= 15){
      defensiveCR += 1;
         if (defensiveCR >= 20) {
         break;
         }
      }
      for (i = 356; i < expectedHP; i+= 45){
      defensiveCR += 1;
      }
      
      if (defensiveCR <= 3) { //AC mods
         defensiveCR += Math.floor((ac - 13)/2);
      }
      if (defensiveCR > 3 && defensiveCR <= 4) {
         defensiveCR += Math.floor((ac - 14)/2);
      }
      if (defensiveCR > 4 && defensiveCR <= 7) {
         defensiveCR += Math.floor((ac - 15)/2);
      }
      if (defensiveCR > 7 && defensiveCR <= 9) {
         defensiveCR += Math.floor((ac - 16)/2);
      }
      if (defensiveCR > 9 && defensiveCR <= 12) {
         defensiveCR += Math.floor((ac - 17)/2);
      }
      if (defensiveCR > 12 && defensiveCR <= 16) {
         defensiveCR += Math.floor((ac - 18)/2);
      }
      if (defensiveCR > 16 && defensiveCR <= 30) {
         defensiveCR += Math.floor((ac - 19)/2);
      }
      if (defensiveCR > 30) {
         defensiveCR += Math.floor((ac - 20)/2);
      }
      System.out.println("");
      if (defensiveCR < 0) {
      defensiveCR = 0;
      }
      
      System.out.println("Defensive CR (Adjusted): " + defensiveCR);
      //Calculates and prints out offensive CR info
      int offensiveCR = Math.round(Math.round(2*cr) - defensiveCR + (int)stage);
      if (offensiveCR <= 0) {
        offensiveCR = defensiveCR / 4; // If Offensive CR is negative set it to 1/4 defensive CR
      }
      if (atk > 100 && atk > spa) {
         offensiveCR += Math.floor(atk-100)/20;
      } 
      if (spa > 100 && spa > atk) {
         offensiveCR += Math.floor(spa-100)/20;
      } 
      if (atk == spa && atk > 100) {
         offensiveCR += Math.floor(atk-100)/20;
      } 
      
      System.out.println("Suggested Offensive CR: " + (offensiveCR));
      int damage = 7;
      int difficultyClass = 12;
      for (i = 0; i < offensiveCR; i += 3) { //Calculates Difficulty Class
         difficultyClass += 1;
      }
      
      for (i = 0; i < offensiveCR; i += 1){ //Calculates Suggested Damage
      damage += 6;
         if (damage >= 123) {
         break;
         }
      }
      for (i = 20; i < offensiveCR; i+= 1){
      damage += 18;
      }
      System.out.println("Suggested damage per round: " + damage);
      System.out.println("Suggested DC for abilities: " + difficultyClass);
      System.out.println("Have a nice day");
      
   }
}