import objectdraw.*;
import java.awt.*;

//Program by Afia Nyantakyi
//Folder/Program Name: asnInvisibleBoxGame
//Date: 9/12/19
//Overview: To create a game in which the player has to
//find boxes that are hidden from them.

   
   
   
public class asnInvisibleBoxGame extends WindowController
{
   //These are the three boxes
   private FilledRect box1, box2, box3; 
   //This is an array for the finding if the boxes were clicked
   private boolean [] boxHit = {false, false, false}; 
   //count is to count how many boxes they hit and score is to keep score of it.
   private int count, score; 
   //this is to count how many points they lost
   private int lost;
   //This is to tell them which boxes the hit
   private String acutalHit;    
   //This is an array to randomly pick the position of the boxes
   private RandomIntGenerator [] random = new RandomIntGenerator [3];
   
   
   
   
   //this is to run the program
   public static void main(String[] args)
   {
      new asnInvisibleBoxGame().startController(300,350);
   }
   
   
   
   
   //when the program begins,
   public void begin()
   {
     //The instruction for the game will appear.
     new Text ("This game is where you try to find the hidden", 10, 20, canvas);
     new Text ("box. There are three boxes of different sizes.", 10, 40, canvas);
     new Text ("you also lose some points if you pick wrong.", 10, 80, canvas);
     new Text ("You get points for click on the boxes, however", 10, 60, canvas);
     new Text ("The game only ends when your mouse leaves", 10, 100, canvas);
     new Text ("this pop-up. Good Luck", 10, 120, canvas);
     //This will produce the random position
     random [0] = new RandomIntGenerator (0, 235);
     random [1] = new RandomIntGenerator (0, 205);
     random [2] = new RandomIntGenerator (0, 135);

   }
   
   
   
   
   //When they press the mouse,
   public void onMousePress (Location p)
   {
      //This will show them where they pressed
      new FramedOval (p, 5, 5, canvas);
      
      //This is the if statement to find whether they hit the box or not
      //if they hit box 1 and they have not hit it before
      if ( (box1.contains(p)) && (boxHit[0] != true) )
      {
         //count increase by one
         count++;
         //The boolean for the box is set to true
         boxHit[0] = true;
      }
      
      // if they hit box 2, etc
      else if ( (box2.contains(p))  && (boxHit[1] != true))
      {
         count++;
         boxHit[1] = true;
      }
      
      //if they hit box 3, etc
      else if ( (box3.contains (p)) && (boxHit[2] != true))
      {
         count++;
         boxHit[2] = true;
       }
      
      // if they don't hit the boxes, then
      else
      {
         //the points they lost increase
         lost++;
      }
   }
   
   
   
   
   //when they exit the pop
   public void onMouseExit (Location a)
   {  
      //the boxes show
      box1.show();
      box2.show();
      box3.show();
      
      
      //the boolean for box 1 is true
      if (boxHit[0])
      {
         //Small box will be put into the string
         acutalHit = acutalHit + "Small box ";
         
         //if they hit more than one box and count is more than 1,
         if (count > 1)
         {
            //then it will put an "and" between the next part
            acutalHit = acutalHit + " and ";
            //count will decrease by one
            count--;
         }
      }
      
      //the boolean for box 2 is true, etc
      if (boxHit[1])
      {
         acutalHit = acutalHit + "Medium box ";         
         if (count > 1)
         {
            acutalHit = acutalHit + " and ";
            count--;
         }
      }
         
      //the boolean for box 3 is true, etc 
      if (boxHit[2])
      {
         acutalHit = acutalHit + "Large box ";        
         if (count > 1)
         {
            acutalHit = acutalHit + " and ";
            count--;
         }  
      }
      
      //if none of them were hit
      if ( (!boxHit[0]) && (!boxHit[1]) && (!boxHit [2]) )
      {
        //Nothing would be put into the string
        acutalHit = "Nothing";
      }
      
      
      
      //This is combination for where the boxes where hit or not
      //based on that, the score will be put into the variable
      if (boxHit[0] && boxHit [1] && boxHit [2] )
      {
         score = 200;
      }
      else if (boxHit [0] && boxHit [1])
      {
         score = 150;
      }
      else if (boxHit [0] && boxHit [2])
      {
         score = 125;
      }
      else if (boxHit [1] && boxHit [2] )
      {
         score = 110;
      } 
      else if (boxHit [0] )
      {
         score = 100;
      }
      else if (boxHit [1])
      {
         score = 75;
      }
      else if (boxHit [2])
      {
         score = 50;
      }
      
      
      //This will display the name of the boxes they hit
      new Text ("You hit " + acutalHit, 20, 100, canvas);
      //This will display the score of the person
      new Text ("Your score: " + (score - lost), 20, 120, canvas);
      //This will set the count, acutalhits, score and lost points to 0 for the next game
      count = 0;
      acutalHit = "";
      score = 0; 
      lost = 0;
   }
   
   
   
   
   //When the mouse enters the pop, the next game starts
   public void onMouseEnter(Location p)
   {
      //This clear the previous game
      canvas.clear();
      
      
      //This sets the boolean arry to to false
      for ( int k = 0; k < 3; k++)
      {
         boxHit[k] = false;
      }

      //The boxes will be placed
      //The random[0].nextValue() is where it will be placed
      box1 = new FilledRect (random [0].nextValue(), random [0].nextValue(), 60, 60, canvas);
      box2 = new FilledRect (random [1].nextValue(), random [1].nextValue(), 90, 90, canvas);
      box3 = new FilledRect (random [2].nextValue(), random [2].nextValue(), 160, 160, canvas);
   
      //The boxes colors are set
      box1.setColor(Color.yellow);
      box2.setColor (Color.green);
      box3.setColor (Color.blue);
      
      //The boxes are hidden
      box1.hide();
      box2.hide();
      box3.hide();
      
   }
      
}
