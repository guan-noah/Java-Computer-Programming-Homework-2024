//Punctuation.java
/* Noah Guan
 * 12-11-2024
 * Per. 6 Java w/ Mr. Yu
 * Punctuation.java
 * Program #30.1
 * 
 * Pseudocode: 
main 
	call searchIt
	
searchIt 
	print 3 new lines 
	intro tell user what's going to happen
	text variable = prompt text 
	print text and continue intro 
	call print result with call calculate result inside 
	print 3 new lines 

calculate result 
	declare index of text for use in for loop (don't want to keep declaring... 
		...inside the for loop)
	declare charAtIndex for use in for loop 
	d&i var for all punctuation words; var to return and print 
	for loop (index of text = 0; index of text < length of text, index of text++)
		initialize char at index using loop #, starting at the first char 
		if (the char at index has punctuation) 
			get the substring between the last space and (index-1) bec. ...
				...index is stopped at a space; that is the word 
			if last space is negative 1, make it zero 	
			substring the word now after making it 0
			append send lastword to all punctuation words 
	return all punctuation words 	
			
check for punctuation
	d&i hasPunctuation to false; will be changed if true 
	d&i variable to convert char to string for ellipse inclusion
	if (has punctuation list)
		hasPunctuation = true 
	return hasPunctuation 

print result 
	print line that's given 
 * no testing plan (no user input) 
 */

public class Punctuation
{
	//no fvs
	public Punctuation()
	{
		//none
	}
	public static void main(String[] args)
	{
		Punctuation punc = new Punctuation();
			//make a new instance of Punctuation.java
		punc.searchIt();
			//use the instance to call the searchIt method 
	}
	public void searchIt()
	{
		System.out.println("\n\n\n");
			//required 3 new lines 
		System.out.println("Welcome to Punctuation.java! \nThis program takes " + 
		"a string in and returns all words with punctuation in the string." + 
		"Valid punctuation marks include: '.' \t',' \t';' \t':' \t'!' \t'?' " + 
		"\t'\'' \t'\"' \t\"...\" \t'(' \t')' \t and '-'.  \n\nAs an example, " + 
		"here's an excerpt from Winston Churchill's Blood, Sweat and Tears: \n"); 
			//intro, telling user what's going to happen 
		String text = new String("Blood, Sweat, and Tears by Winston Churchill " +
			"May 13, 1940" +
			"Mr. Speaker:" + 
			"On Friday evening last I received His Majesty's commission " + 
			"to form a new Administration. It was the evident wish and " + 
			"will of Parliament and the nation that this should be " + 
			"conceived on the broadest possible basis and that it should " +
			"include all parties, both those who supported the late " + 
			"Government and also the parties of the Opposition." + 
			"I have completed the most important part of this task. " + 
			"A War Cabinet has been formed of five Members, representing, " + 
			"with the Liberal Opposition, the unity of the nation. The three " + 
			"party Leaders have agreed to serve, either in the War Cabinet or in " + 
			"high executive office. The three Fighting Services have been " + 
			"filled. It was necessary that this should be done in one single " + 
			"day, on account of the extreme urgency and rigour of events. "+ 
			"A number of other key positions were filled yesterday, and I am " + 
			"submitting a further list to His Majesty tonight. I hope to " + 
			"complete the appointment of the principal Ministers during " + 
			"tomorrow. The appointment of the other Ministers usually takes " + 
			"a little longer, but I trust that, when Parliament meets again, " + 
			"this part of my task will be completed, and that the Administration " + 
			"will be complete in all respects." +
			"Sir, I considered it in the public interest to suggest that " + 
			"the House should be summoned to meet today. Mr. Speaker agreed " + 
			"and took the necessary steps, in accordance with the powers " + 
			"conferred upon him by the Resolution of the House. At the " + 
			"end of the proceedings today, the Adjournment of the House " + 
			"will be proposed until Tuesday, the 21st May, with, of course, " + 
			"provision for earlier meeting, if need be. The business to be " + 
			"considered during that week will be notified to Members at the " + 
			"earliest opportunity. I now invite the House, by the Resolution " + 
			"which stands in my name, to record its approval of the steps " + 
			"taken and to declare its confidence in the new Government." +
			"Sir, to form an Administration of this scale and complexity " + 
			"is a serious undertaking in itself, but it must be remembered " + 
			"that we are in the preliminary stage of one of the greatest " + 
			"battles in history, that we are in action at many points in " + 
			"Norway and in Holland, that we have to be prepared in the " + 
			"Mediterranean, that the air battle is continuous and that many " + 
			"preparations have to be made here at home. In this crisis I hope " + 
			"I may be pardoned if I do not address the House at any length " + 
			"today. I hope that any of my friends and colleagues, or former " + 
			"colleagues, who are affected by the political reconstruction, " + 
			"will make all allowances for any lack of ceremony with which " + 
			"it has been necessary to act. I would say to the House, as I " + 
			"said to those who've joined this government: \"I have nothing " + 
			"to offer but blood, toil, tears and sweat.\"" +
			"We have before us an ordeal of the most grievous kind. We have " + 
			"before us many, many long months of struggle and of suffering. " + 
			"You ask, what is our policy? I will say: It is to wage war, " + 
			"by sea, land and air, with all our might and with all the strength " + 
			"that God can give us; to wage war against a monstrous tyranny, " + 
			"never surpassed in the dark and lamentable catalogue of human " + 
			"crime. That is our policy. You ask, what is our aim? I can answer " + 
			"in one word: victory. Victory at all costs, victory in spite of " + 
			"all terror, victory, however long and hard the road may be; for " + 
			"without victory, there is no survival. Let that be realised; " + 
			"no survival for the British Empire, no survival for all that the " + 
			"British Empire has stood for, no survival for the urge and impulse " + 
			"of the ages, that mankind will move forward towards its goal. " + 
			"But I take up my task with buoyancy and hope. I feel sure that " + 
			"our cause will not be suffered to fail among men. At this time I " + 
			"feel entitled to claim the aid of all, and I say, \"Come then, " + 
			"let us go forward together with our united strength.\"");
				//the text in variable with identifier "text"  
		System.out.println(text + "\n\nAnd now, after plugging this into " + 
		"Punctuation.java: \n");
			//continuation of intro 
		printWords(getPunctuationWords(text));
			//print result 
		
		System.out.println("\n\n\n");
	}
	public String getPunctuationWords(String textIn)
	{
		int indexOfText;
			//declare index of text for use in for loop (don't want to 
				//keep declaring inside the for loop) 
		char charAtIndex;
			//declare charAtIndex for use in for loop 
		String allPuncWords = new String("");
			//all words with punctuation; essentially the variable we want 
				//to print
		for (indexOfText = 0; indexOfText < textIn.length(); indexOfText++) 
			//run through the text 
		{
			charAtIndex = textIn.charAt(indexOfText);
				//this initializes charAtIndex to the char at the index 
					//of the text (as the name implies), starting at the 
					//first char (because the index starts at 0)
			
			if (checkForPunctuation(charAtIndex))
			{
				//get the substring between the (index - 1) (index is stopped at a space) 
					//and the last space; that is the word 
				int lastSpace = textIn.lastIndexOf(' ', (indexOfText - 1));
					//the current index is stopped at a space so we'll have to start from 
						//one character before the index 
				if (lastSpace == -1)
					lastSpace = 0;
					//this ensures that even the start of the text will be in index bounds
				String word = textIn.substring(lastSpace, indexOfText + 1).trim() + " ";
					//word is defined as anything between 2 spaces 
					
				allPuncWords = allPuncWords + word;
					//append word to all punctuation words  
			}
		}
		return allPuncWords;
	}
	public boolean checkForPunctuation(char charIn)
	{
		boolean hasPunctuation = false;
				//d&i hasPunctuation to false; will be changed if true
		String charStringAtIndex = "" + charIn;
			//convert the char to a string so we can compare ellipses 
				//punctuation as well
		if (charStringAtIndex.equals(".") || charStringAtIndex.equals(",") || 
		charStringAtIndex.equals(";") || charStringAtIndex.equals(":") || 
		charStringAtIndex.equals("!") || charStringAtIndex.equals("?") || 
		charStringAtIndex.equals("\'") || charStringAtIndex.equals("\"") || 
		charStringAtIndex.equals("...") || charStringAtIndex.equals("(") || 
		charStringAtIndex.equals(")") || charStringAtIndex.equals('-'))
			//if the char is any of this defined punctuation 
		{ 
			hasPunctuation = true;
		}
		return hasPunctuation;
	}
	/* //alright, so this was my method (for code organization) 
	 * 		//if I was allowed to have it (I wasn't)
	public String sendLastWord(int indexNumIn, String textIn)
		//this method sends back the substring from the index given back to 
			//the last space (gets the word) 
	{
		//get the substring between the (index - 1) (index is stopped at a space) 
			//and the last space; that is the word 
		int lastSpace = textIn.lastIndexOf(' ', (indexNumIn - 1));
			//the current index is stopped at a space so we'll have to start from 
				//one character before the index 
		if (lastSpace == -1)
			lastSpace = 0;
			//this ensures that even the start of the text will be in index bounds
		String word = textIn.substring(lastSpace, indexNumIn + 1).trim() + " ";
			//word is defined as anything between 2 spaces 
		*/
		/* //this following code commented out is if the previous code doesn't 
		 * 	//account if there's punctuation in the word 
		for (int indexOfWord = 0; indexOfWord < word.length(); indexOfWord++)
		{
			if (checkForPunctuation(word.charAt(indexOfWord)))
				//if there is punctuation anywhere in the word 
			{
				return word;
			}
			else 
			{
				return "";
					//don't return anything because the word doesn't 
						//include any punctuation
			}
		}
		*/
		/*
		return word;
			//at this point, the word will have punctuation in it. 
	}
	*/
	public void printWords(String printLine)
	{
		System.out.println(printLine);
	}
}
