package com.karuna.test;

import java.io.*;


// class for converting unicode  to Wx-alphabetic notation
class CodeConverter
{
        //creating object for CodeMapping class
        CodeMappings codeMappingsObject;
	
	// Flag to keep track of Halant
	private boolean CONSONANT_FLAG;
 public CodeConverter()
  {

     // creating object for codemapping class
    codeMappingsObject= new CodeMappings();
  }

// method for converting unicode to Wx-alphabetic notation

public String convertUnicode2Wx(String unicodeString,String script) {
		String wxString="";
		CONSONANT_FLAG=false;
		char[] unicodeChars = unicodeString.toCharArray();
		for(int i=0;i<unicodeChars.length;i++) {
			//if the unicodeCharacter is in Devanagari Range
			int charCode = (int)unicodeChars[i];
			if(script.equalsIgnoreCase("Devanagari")) {
				if(charCode  > 2304 ) {
					// if the character is a Proper Consonant
					if(charCode >= 2325 && charCode <= 2361) {
						if(CONSONANT_FLAG && i!=unicodeChars.length-1) {
							wxString = wxString + "a"+ codeMappingsObject.getWxFromUnicode(unicodeChars[i],script);
						}
                                                else if(CONSONANT_FLAG && i==unicodeChars.length-1) {
                                                       wxString= wxString+"a"+codeMappingsObject.getWxFromUnicode(unicodeChars[i],script)+"a";
                                               }
						else if(i==unicodeChars.length-1){
                                                    wxString= wxString+codeMappingsObject.getWxFromUnicode(unicodeChars[i],script)+"a";
                                                }
                                                else {
							CONSONANT_FLAG=true;
							wxString = wxString + codeMappingsObject.getWxFromUnicode(unicodeChars[i],script);
						}
					}
					// if the Character is a Vowel
					else if(charCode >= 2309 && charCode <= 2324) {
						CONSONANT_FLAG=false;
						wxString = wxString + codeMappingsObject.getWxFromUnicode(unicodeChars[i],script);
					}
					// if the Character is a Matra
					else if(charCode >= 2366 && charCode <= 2380) {
						CONSONANT_FLAG=false;
						wxString = wxString + codeMappingsObject.getWxFromUnicode(unicodeChars[i],script);
					}
					else if(charCode == 2381 ) {
						CONSONANT_FLAG=false;
					}
					else if(charCode == 2364 ) {
						wxString = wxString + "Z" ;
					}
                                        else if(charCode == 2365 ) {
                                                wxString= wxString+ codeMappingsObject.getWxFromUnicode(unicodeChars[i],script);
                                        }
                                        else if(charCode ==2307) {
                                                 int perv=(int)unicodeChars[i-1];
                                                if(perv>=2366 && perv<=2380){
                                                   wxString=wxString+"H";}
                                                 else {
                                                wxString= wxString+"aH";}
                                        }
					// if the Character is a Consonant with Nukta
					else if(charCode >= 2392 && charCode <= 2399) {
						CONSONANT_FLAG=false;
					}
				}
				else {
					if(CONSONANT_FLAG) {
						wxString = wxString+ new Character((char)charCode).toString();//+ "a";              // new Character((char)charCode).toString();
						CONSONANT_FLAG=false;
					}
					else if(i==unicodeString.length()-2) {
						wxString = wxString + "a";
					}
                                        else {
						wxString = wxString + new Character((char)charCode).toString();
					}
                                        if (unicodeChars[i]== ':'){
                                               wxString= wxString;
                                        }

				}
			}
		}
		return wxString;
	}

   // main method for executing and checking for errors in a  program 

    public static void main(String arg[]) throws IOException
    {
       System.out.println("enter unicode string for testing");
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       String str= br.readLine();
       CodeConverter c= new CodeConverter();
       String str1= c.convertUnicode2Wx(str,"Devanagari");
       System.out.println(str1);
    } 
 }
