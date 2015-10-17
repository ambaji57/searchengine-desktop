package com.karuna.test;
import java.awt.event.*;

import java.io.*;
import java.util.*;
import javax.swing.*;


public class CodeMappings {

	/* A 3D array. Each Row is in order ISCII-CODE,UNICODE-CODE,CHARACTER-TYPE
	   V --> Vowel
	   C --> Character
	   S --> Characters that need Special treatment like "anuswar","ChandraBindu","visarg"
	   N --> Nukta characters
	   The Array is sorted on the Ascii values from A-z.
	   For each Index subtract 65 from the Ascii value of the input Character.
	    If input character is A - 
		  the corresponding mapping is found at index Ascii(A)-65 = 0
	   Eg: Array Index 0 --> A (ROMAN)
	                         ISCII - 165
							 UNICODE - \u0906
							 CHARACTER-TYPE -V (Vowel)
				Devanagari
			[0][0][0] --> 165
			[0][0][1] --> \u0906
			[0][0][3] --> V
			[0][1][0] --> 203
			[0][1][1] --> \u092D
			[0][1][2] --> C
			 
			   Telugu
			[1][0][0] --> 165
			[1][0][1] --> \u0C06
			[1][0][3] --> V
			[1][1][0] --> 203
			[1][1][1] --> \u0C2D
			[1][1][2] --> C
	*/
	private String[][][] codeProperties  = {
		{//Devanagari
	                {"165","\u0906","V"},
				    {"203","\u092D","C"},
					{"185","\u091B","C"},
					{"194","\u0922","C"},
					{"173","\u0910","V"},
					{"188","\u091E","C"},
					{"182","\u0918","C"},
					{"163","\u0903","S"},
					{"167","\u0908","V"},
					{"187","\u091D","C"},
					{"180","\u0916","C"},
					{"0","\u090C","C"},
					{"162","\u0902","S"},
					{"193","\u0923","C"},
					{"177","\u0914","V"},
					{"201","\u092B","C"},
					{"0","\u0960","V"},
					{"214","\u0937","C"},
					{"213","\u0936","C"},
					{"190","\u0920","C"},
					{"169","\u090A","V"},
					{"0","",""},
					{"195","\u0925","C"},
					{"197","\u0927","C"},
					{"0","",""},
					{"233","\u093C","N"},
					{"0","",""},
					{"0","",""},
					{"0","",""},
					{"0","",""},
					{"0","",""},
					{"0","",""},
					{"164","\u0905","V"},
					{"202","\u092C","C"},
					{"184","\u091A","C"},
					{"191","\u0921","C"},
					{"172","\u090F","V"},
					{"183","\u0919","C"},
					{"181","\u0917","C"},
					{"216","\u0939","C"},
					{"166","\u0907","V"},
					{"186","\u091C","C"},
					{"179","\u0915","C"},
					{"209","\u0932","C"},
					{"204","\u092E","C"},
					{"198","\u0928","C"},
					{"176","\u0913","V"},
					{"200","\u092A","C"},
					{"170","\u090B","V"},
					{"207","\u0930","C"},
					{"215","\u0938","C"},
					{"189","\u091F","C"},
					{"168","\u0909","V"},
					{"212","\u0935","C"},
					{"194","\u0924","C"},
					{"196","\u0926","C"},
					{"205","\u092F","C"},
					{"161","\u0901","S"}
		},
		{// Telugu
	                {"165","\u0C06","V"},
				    {"203","\u0C2D","C"},
					{"185","\u0C1B","C"},
					{"194","\u0C22","C"},
					{"173","\u0C10","V"},
					{"188","\u0C1E","C"},
					{"182","\u0C18","C"},
					{"163","\u0C03","S"},
				{"167","\u0C08","V"},
              			{"187","\u0C1D","C"},
				{"180","\u0C16","C"},
				{"0","",""},
				{"162","\u0C02","S"},
				{"193","\u0C23","C"},
				{"177","\u0C14","V"},
				{"201","\u0C2B","C"},
					{"0","",""},
					{"214","\u0C37","C"},
					{"213","\u0C36","C"},
					{"190","\u0C20","C"},
					{"169","\u0C08","V"},
					{"0","",""},
					{"195","\u0C25","C"},
					{"197","\u0C27","C"},
					{"0","",""},
					{"233","\u0C3C","N"},
					{"0","",""},
				{"0","",""},
					{"0","",""},
					{"0","",""},
					{"0","",""},
					{"0","",""},
					{"164","\u0C05","V"},
					{"202","\u0C2C","C"},
					{"184","\u0C1A","C"},
					{"191","\u0C21","C"},
					{"172","\u0C0F","V"},
					{"183","\u0C19","C"},
					{"181","\u0C17","C"},
					{"216","\u0C39","C"},
					{"166","\u0C07","V"},
					{"186","\u0C1C","C"},
					{"179","\u0C15","C"},
					{"209","\u0C32","C"},
					{"204","\u0C2E","C"},
					{"198","\u0C28","C"},
					{"176","\u0C13","V"},
					{"200","\u0C2A","C"},
					{"170","\u0C0B","V"},
					{"207","\u0C30","C"},
					{"215","\u0C38","C"},
					{"189","\u0C1F","C"},
					{"168","\u0C09","V"},
					{"212","\u0C35","C"},
					{"194","\u0C24","C"},
					{"196","\u0C26","C"},
					{"205","\u0C2F","C"},
					{"161","\u0C01","S"}
		}

	};

    private String[][] iscii2Unicode = {
		//Devanagari
		  {"\u0901","\u0902","\u0903","\u0905","\u0906","\u0907","\u0908","\u0909","\u090A","\u090B","\u090E","\u090F","\u0910","\u090D","\u0912","\u0913","\u0914","\u0911","\u0915","\u0916","\u0917","\u0918","\u0919","\u091A","\u091B","\u091C","\u091D","\u091E","\u091F","\u0920","\u0921","\u0922","\u0923","\u0924","\u0925","\u0926","\u0927","\u0928","\u0929","\u092A","\u092B","\u092C","\u092D","\u092E","\u092F","\u095F","\u0930","\u0931","\u0932","\u0933","\u0934","\u0935","\u0936","\u0937","\u0938","\u0939","","\u093E","\u093F","\u0940","\u0941","\u0942","\u0943","\u0946","\u0947","\u0948","\u0945","\u094A","\u094B","\u094C","\u0949","\u094D","\u093C","\u0964","","","","","","","\u0966","\u0967","\u0968","\u0969","\u096A","\u096B","\u096C","\u096D","\u096E","\u096F"},
		  //Telugu
		  {"\u0C01","\u0C02","\u0C03","\u0C05","\u0C06","\u0C07","\u0C08","\u0C09","\u0C0A","\u0C0B","\u0C0E","\u0C0F","\u0C10","\u0C0D","\u0C12","\u0C13","\u0C14","\u0C11","\u0C15","\u0C16","\u0C17","\u0C18","\u0C19","\u0C1A","\u0C1B","\u0C1C","\u0C1D","\u0C1E","\u0C1F","\u0C20","\u0C21","\u0C22","\u0C23","\u0C24","\u0C25","\u0C26","\u0C27","\u0C28","\u0C29","\u0C2A","\u0C2B","\u0C2C","\u0C2D","\u0C2E","\u0C2F","\u0C5F","\u0C30","\u0C31","\u0C32","\u0C33","\u0C34","\u0C35","\u0C36","\u0C37","\u0C38","\u0C39","","\u0C3E","\u0C3F","\u0C40","\u0C41","\u0C42","\u0C43","\u0C46","\u0C47","\u0C48","\u0C45","\u0C4A","\u0C4B","\u0C4C","\u0C49","\u0C4D","\u0C3C","\u0C64","","","","","","","\u0C66","\u0C67","\u0C68","\u0C69","\u0C6A","\u0C6B","\u0C6C","\u0C6D","\u0C6E","\u0C6F"}
	};

	private int unicode2Iscii[] = {0,161,162,163,0,164,165,166,167,168,169,170,0,174,171,172,173,178,175,176,177,179,180,181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200,201,202,203,204,205,207,208,209,210,211,212,213,214,215,216,0,0,233,0,218,219,220,221,222,223,0,227,224,225,226,231,228,229,230,232,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,241,242,243,244,245,246,247,248,249,250};
	
	private String[][][] iscii2UnicodeMap = {
		{//Devanagari
		            {"161","\u0901"},
		            {"162","\u0902"},
		            {"163","\u0903"},
		            {"164","\u0905"},
		            {"165","\u0906"},
		            {"166","\u0907"},
		            {"167","\u0908"},
		            {"168","\u0909"},
		            {"169","\u090A"},
		            {"170","\u090B"},
		            {"171","\u090E"},
		            {"172","\u090F"},
		            {"173","\u0910"},
		            {"174","\u090D"},
		            {"175","\u0912"},
		            {"176","\u0913"},
		            {"177","\u0914"},
		            {"178","\u0911"},
		            {"179","\u0915"},
		            {"180","\u0916"},
		            {"181","\u0917"},
		            {"182","\u0918"},
		            {"183","\u0919"},
		            {"184","\u091A"},
		            {"185","\u091B"},
		            {"186","\u091C"},
		            {"187","\u091D"},
		            {"188","\u091E"},
		            {"189","\u091F"},
		            {"190","\u0920"},
		            {"191","\u0921"},
		            {"192","\u0922"},
		            {"193","\u0923"},
		            {"194","\u0924"},
		            {"195","\u0925"},
		            {"196","\u0926"},
		            {"197","\u0927"},
		            {"198","\u0928"},
		            {"199","\u0929"},
		            {"200","\u092A"},
		            {"201","\u092B"},
		            {"202","\u092C"},
		            {"203","\u092D"},
		            {"204","\u092E"},
		            {"205","\u092F"},
		            {"206","\u095F"},
		            {"207","\u0930"},
		            {"208","\u0931"},
		            {"209","\u0932"},
		            {"210","\u0933"},
		            {"211","\u0934"},
		            {"212","\u0935"},
		            {"213","\u0936"},
		            {"214","\u0937"},
		            {"215","\u0938"},
		            {"216","\u0939"},
		            {"218","\u093E"},
		            {"219","\u093F"},
		            {"220","\u0940"},
		            {"221","\u0941"},
		            {"222","\u0942"},
		            {"223","\u0943"},
		            {"224","\u0946"},
		            {"225","\u0947"},
		            {"226","\u0948"},
		            {"227","\u0945"},
		            {"228","\u094A"},
		            {"229","\u094B"},
		            {"230","\u094C"},
		            {"231","\u0949"},
		            {"232","\u094D"},
		            {"233","\u093C"},
		            {"234","\u0964"},
		            {"241","\u0966"},
		            {"242","\u0967"},
		            {"243","\u0968"},
		            {"244","\u0969"},
		            {"245","\u096A"},
		            {"246","\u096B"},
		            {"247","\u096C"},
		            {"248","\u096D"},
		            {"249","\u096E"},
		            {"250","\u096F"}
		},
		{//Telugu
		            {"161","\u0C01"},
		            {"162","\u0C02"},
		            {"163","\u0C03"},
		            {"164","\u0C05"},
		            {"165","\u0C06"},
		            {"166","\u0C07"},
		            {"167","\u0C08"},
		            {"168","\u0C09"},
		            {"169","\u0C0A"},
		            {"170","\u0C0B"},
		            {"171","\u0C0E"},
		            {"172","\u0C0F"},
		            {"173","\u0C10"},
		            {"174","\u0C0D"},
		            {"175","\u0C12"},
		            {"176","\u0C13"},
		            {"177","\u0C14"},
		            {"178","\u0C11"},
		            {"179","\u0C15"},
		            {"180","\u0C16"},
		            {"181","\u0C17"},
		            {"182","\u0C18"},
		            {"183","\u0C19"},
		            {"184","\u0C1A"},
		            {"185","\u0C1B"},
		            {"186","\u0C1C"},
		            {"187","\u0C1D"},
		            {"188","\u0C1E"},
		            {"189","\u0C1F"},
		            {"190","\u0C20"},
		            {"191","\u0C21"},
		            {"192","\u0C22"},
		            {"193","\u0C23"},
		            {"194","\u0C24"},
		            {"195","\u0C25"},
		            {"196","\u0C26"},
		            {"197","\u0C27"},
		            {"198","\u0C28"},
		            {"199","\u0C29"},
		            {"200","\u0C2A"},
		            {"201","\u0C2B"},
		            {"202","\u0C2C"},
		            {"203","\u0C2D"},
		            {"204","\u0C2E"},
		            {"205","\u0C2F"},
		            {"206","\u0C5F"},
		            {"207","\u0C30"},
		            {"208","\u0C31"},
		            {"209","\u0C32"},
		            {"210","\u0C33"},
		            {"211","\u0C34"},
		            {"212","\u0C35"},
		            {"213","\u0C36"},
		            {"214","\u0C37"},
		            {"215","\u0C38"},
		            {"216","\u0C39"},
		            {"218","\u0C3E"},
		            {"219","\u0C3F"},
		            {"220","\u0C40"},
                            {"221","\u0C41"},
		            {"222","\u0C42"},
		            {"223","\u0C43"},
		            {"224","\u0C46"},
		            {"225","\u0C47"},
		            {"226","\u0C48"},
		            {"227","\u0C45"},
		            {"228","\u0C4A"},
		            {"229","\u0C4B"},
		            {"230","\u0C4C"},
		            {"231","\u0C49"},
		            {"232","\u0C4D"},
		            {"233","\u0C3C"},
		            {"234","\u0C64"},
		            {"241","\u0C66"},
		            {"242","\u0C67"},
		            {"243","\u0C68"},
		            {"244","\u0C69"},
		            {"245","\u0C6A"},
		            {"246","\u0C6B"},
		            {"247","\u0C6C"},
		            {"248","\u0C6D"},
		            {"249","\u0C6E"},
		            {"250","\u0C6F"}
		}
	};

	String[] unicode2wxMap = {"z","M","H","","a","A","i","I","u","U","q","","aEY","aeV","e","E","OY","oV","o","O","k","K","g","G","f","c","C","j","J","F","t","T","d","D","N","w","W","x","X","n","nZ","p","P","b","B","m","y","r","rZ","l","lY","lYz","v","S","R","s","h","","","Z","","A","i","I","u","U","q","Q","EY","eV","e","E","OY","oV","o","O","","","","","","","","","","","","","","","","","","","","Q","LY","L","LY","","","0","1","2","3","4","5","6","7","8","9","","","","","","","","","","","","","","","","",""};
	
    /*
	   A 3D Array.
	     1st Dimension is ISCII code of the Matra
	     2nd Dimension is wx equivivalent of Matra
		 3rd Dimension is unicode equivivalent of Matra
		 The Array index has no Significance.
		    Devanagari
			
		 Eg: [0][0][0] -- A
		     [0][0][1] -- \u093E

			 Telugu
		 Eg: [1][0][0] -- A
		     [1][0][1] -- \u0C3E
			 
	*/
	private String[][][] vowel2Matra= {
		{//Devanagari
				{"218","A","\u093E"},
				{"219","i","\u093F"},
				{"220","I","\u0940"},
				{"221","u","\u0941"},
				{"222","U","\u0942"},
				{"223","q","\u0943"},
				{"225","e","\u0947"},
				{"226","E","\u0948"},
				{"229","o","\u094B"},
				{"230","O","\u094C"}
		},
		{//Telugu
				{"218","A","\u0C3E"},
				{"219","i","\u0C3F"},
				{"220","I","\u0C40"},
				{"221","u","\u0C41"},
				{"222","U","\u0C42"},
				{"223","q","\u0C43"},
				{"225","e","\u0C47"},
				{"226","E","\u0C48"},
				{"229","o","\u0C4B"},
				{"230","O","\u0C1C"}
		}
	};



	/**  Returns the Type of the Character passed.
	 *   Return character   --- Character Type<br>
	 *	 	'V'			 --- Vowel<br>
	 *      'C'			 --- Consonant<br>
	 * 		'S'			 --- Special (anuswar,chandrabindu.avagrah)<br>
	 *		' '(Null)	 --- Not Found<br>
	 */
	public char getCharType(char ch,String script) {
		// subtract 65 from the ASCII value of the Input Character
		int offset = (int)ch - 65;
		try {
			if(script.equalsIgnoreCase("Devanagari")) {
				char[] type = codeProperties[0][offset][2].toCharArray();
				return type[0];
			}
			else if(script.equalsIgnoreCase("Telugu")) {
				char[] type = codeProperties[1][offset][2].toCharArray();
				return type[0];
			}
		}
		catch(ArrayIndexOutOfBoundsException aob) {
			return ' ';
		}
		return ' ';
	}

	/**  Returns the Type of the Character passed in the form of String for the given Unicode Character for a particular 
	  *  script(Devanagari/Telugu).
	 *   Return character   --- Character Type<br>
	 *	 	'V'			 --- Vowel<br>
	 *      'C'			 --- Consonant<br>
	 * 		'S'			 --- Special (anuswar,chandrabindu.avagrah)<br>
	 *		' '(Null)	 --- Not Found<br>
	 */
	public String getCharType(String unicodeString,String script) {
		for(int i=0;i<codeProperties.length;i++) {
			if(script.equalsIgnoreCase("Devanagari")) {
				if(codeProperties[0][i][1].equals(unicodeString)) {
					return codeProperties[0][i][2];
				}
			}
			else if(script.equalsIgnoreCase("Telugu")) {
				if(codeProperties[1][i][1].equals(unicodeString)) {
					return codeProperties[1][i][2];
				}
			}
		}
		return "";
	}

	/** Returns the ISCII code of the Character Passed for a particular Script(Devanagari/Telugu).
     */
	public int getIsciiCode(char ch,String script) {
		int offset = (int)ch - 65;
		try {
			if(script.equalsIgnoreCase("Devanagari")) {
				return Integer.parseInt(codeProperties[0][offset][0]);
			}
			else if(script.equalsIgnoreCase("Telugu")) {
				return Integer.parseInt(codeProperties[1][offset][0]);
			}
		}
		catch(ArrayIndexOutOfBoundsException aob) {
			return (int)ch;
		}
	  return (int)ch;
	}

	/** Returns the UNICODE of the Character Passed for a particular script(Devanagari/Telugu).
     */
	public String getUnicode(char ch,String script) {
		// subtract 65 from the Ascii value of the Input Character
		int offset = (int)ch - 65;
		try {
			if(script.equalsIgnoreCase("Devanagari")) {
				return codeProperties[0][offset][1];
                                
			}
			else if(script.equalsIgnoreCase("Telugu")) {
				return codeProperties[1][offset][1];
			}
		}
		catch(ArrayIndexOutOfBoundsException aob) {
			return (new Character(ch).toString());
		}
		return "";
	}

   /** 
     * Return the Matra of the corresponding vowel passed as input for a particular script(Devanagari/Telugu)
	 */
	public String getMatraFromVowel(char ch,String script) {
		if(script.equalsIgnoreCase("Devanagari")) {
			for(int i=0;i<vowel2Matra[0].length;i++) {
				char wx[] = vowel2Matra[0][i][1].toCharArray();
				if(wx[0]==ch) {
					return vowel2Matra[0][i][2];
				}
			}
		}
		else if(script.equalsIgnoreCase("Telugu")) {
			for(int i=0;i<vowel2Matra[1].length;i++) {
				char wx[] = vowel2Matra[1][i][1].toCharArray();
				if(wx[0]==ch) {
					return vowel2Matra[1][i][2];
				}
			}
		}
		return "";
	}
   
   /** 
     * Returns the Unicode String given IsciiCode as input for a particular script(Devanagari/Telugu)
	 */

	 public String getUnicodeFromIscii(int isciiCode,String script) {
		 int index = isciiCode - 161;
		 if(script.equalsIgnoreCase("Devanagari")) {
			 return iscii2Unicode[0][index];
		 }
		 else if(script.equalsIgnoreCase("Telugu")) {
			 return iscii2Unicode[1][index];
		 }
		 return "";
	 }
	 
	public String getUnicodeFromIsciiMap(int isciiCode,String script) {
		if(script.equalsIgnoreCase("Devanagari")) {
			for(int i=0;i<iscii2UnicodeMap[0].length;i++) {
				int currentIndexCode = Integer.parseInt(iscii2UnicodeMap[0][i][0]);
				if(currentIndexCode == isciiCode) {	
					return iscii2UnicodeMap[0][i][1];
				}
			}
		}
		else if(script.equalsIgnoreCase("Telugu")) {
			for(int i=0;i<iscii2UnicodeMap[1].length;i++) {
				int currentIndexCode = Integer.parseInt(iscii2UnicodeMap[1][i][0]);
				if(currentIndexCode == isciiCode) {	
					return iscii2UnicodeMap[1][i][1];
				}
			}
		}
		return "";
	}

	/**
	  * Returns ISCII code given UnicodeString as input for a particular script(Devanagari/Telugu)
	  */

	public int getIsciiFromUnicodeMap(String unicodeString,String script) {
		if(script.equalsIgnoreCase("Devanagari")) {
			for(int i=0;i<codeProperties[0].length;i++) {
				if(iscii2UnicodeMap[0][i][1].equals(unicodeString)) {	
					return Integer.parseInt(iscii2UnicodeMap[0][i][0]);
				}
			}
		}
		else if(script.equalsIgnoreCase("Telugu")) {
			for(int i=0;i<codeProperties[1].length;i++) {
				if(iscii2UnicodeMap[1][i][1].equals(unicodeString)) {	
					return Integer.parseInt(iscii2UnicodeMap[1][i][0]);
				}
			}
		}
	  return 0;
	}

	public int getIsciiFromUnicode(String unicodeString,String script) {
		int index;
		char ch[] = unicodeString.toCharArray();
		if(script.equalsIgnoreCase("Devanagari")) {
			//Unicode Range for Devanagari starts from 2304
			index = (int)ch[0] - 2304;
			return unicode2Iscii[index];
		}
		else if(script.equalsIgnoreCase("Telugu")) {
			//Unicode Range for Devanagari starts from 3072
			index = (int)ch[0] - 3072;
			return unicode2Iscii[index];
		}
		return 0;
	}

    /**
	  * Returns ISCII code given unicodeMatra as input for a particular script(Devanagari/Telugu)
	  */
	public int getIsciiFromUnicodeMatra(String unicodeMatra,String script) {
		if(script.equalsIgnoreCase("Devanagari")) {
			for(int i=0;i<vowel2Matra[0].length;i++) {
				if(vowel2Matra[0][i][2].equals(unicodeMatra)) {
					return Integer.parseInt(vowel2Matra[0][i][0]);
				}
			}
		}
		else if(script.equalsIgnoreCase("Telugu")) {
			for(int i=0;i<vowel2Matra[1].length;i++) {
				if(vowel2Matra[1][i][2].equals(unicodeMatra)) {
					return Integer.parseInt(vowel2Matra[1][i][0]);
				}
			}
		}
		return 0;
	}

    /**
	  * Returns the wxString given Unicode Character as input for a particular script(Devanagari/Telugu)
	  */
	public String getWxFromUnicode(char unicodeChar,String script) {
		int offset=0;
		if(script.equalsIgnoreCase("Devanagari")) {
			offset = (int)unicodeChar - 2305;
		}
		else if(script.equalsIgnoreCase("Telugu")) {
			offset = (int)unicodeChar - 3073;
		}
		String romanStr = unicode2wxMap[offset];
		if(!romanStr.equals("")) {
			return unicode2wxMap[offset];
		}
		return new Character(unicodeChar).toString();
	}
    
	/**
	  * Returns true if the Unicode Character passed is a Matra , otherwise returns false for a particular script(Devanagari/Telugu) 
	  */
	public boolean isMatra(char unicodeChar,String script) {
		int offset=0;
		if(script.equalsIgnoreCase("Devanagari")) {
			 offset = (int)unicodeChar - 2305;
		}
		else if(script.equalsIgnoreCase("Telugu")) {
			 offset = (int)unicodeChar - 3073;
		}
		if((offset >= 61) && (offset<=75) ) {
			return true;
		}
		return false;
	}


    /**
	  * Returns true if the Unicode Character passed as input is a Halant, otherwise returns false
	  */
	public boolean isHalant(char unicodeChar,String script) {
		int charCode = (char) unicodeChar;
		//This character will not be there in Telugu Script.
		if(script.equalsIgnoreCase("Devanagari")) {
			if(charCode==2381) {
				return true;
			}
		}
		return false;
	}
}
