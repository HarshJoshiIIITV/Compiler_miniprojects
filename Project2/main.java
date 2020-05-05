package useme;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.*;
import java.lang.System.Logger.Level;
import java.nio.file.Files;
import java.nio.file.Paths;

public class main {
	
	String keywords[]= {"if","else","else if","break","continue","return","while","for","true"};
	String token[]= {"{","}","(",")","1"};
	static String datatype[][]={{"-32768","32767"},{"-2147483648","2147483647"}};
			
			
    private static String usingBufferedReader(String filePath) 
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) 
        {
 
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) 
            {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }	
    
    
    
    
    public static void case1(String s) {
    	StringBuilder string = new StringBuilder(s);
    	int i=string.indexOf("while");
    	int n=s.length();
    	if(i==-1)
    		return;
    	else {
    		int i1=string.indexOf("(",i);
    		int i2=string.indexOf(")",i1);
    		String check=string.substring(i1+1,i2);
    		if(check.trim().equals("true") || check.trim().equals("1")) {
    			int x=string.indexOf("{",i2+1);
    			int stack=1,y=0;
    			for(int j=x+1;j<n;j++){
    				if(stack==0) {
    					break;
    				}
    					
    				if(string.charAt(j)=='{')
    					stack++;
    				else if(string.charAt(j)=='}') {
    					stack--;
    					y=j;
    				}
    			}
    			if(string.indexOf("break",x)==-1)
    				System.out.println("CASE1 ERROR but without break");
    			else if(string.indexOf("break",x)>y) 
    				System.out.println("CASE1 ERROR with break");
    			else
    				System.out.println("case1 not detected");
    		}
    		else {
    			System.out.println("case1 not detected");
    		}
    	}
    }
	
    
    
    public static void case2(String s) {
    	StringBuilder string = new StringBuilder(s);
    	int i=string.indexOf("while");
    	int n=s.length();
    	if(i==-1)
    		return;
    	else {
    		int i1=string.indexOf("(",i);
    		int i2=string.indexOf(")",i1);
    		int i3=string.indexOf("{",i2);
    		int i4=string.indexOf(";",i2);
    		if(i4<i3 || (i4==i2+1)) {
    			System.out.println("Case2 ERROR confirmed semicolon after loop");
    			return;
    		}
    	}
    	System.out.println("Case2 Not detected");
    }
    
    
    
    
    public static void case4(String s) {
    	StringBuilder string = new StringBuilder(s);
    	int x=string.indexOf("int");
    	int n=s.length();
    	if(x!=-1) {
    		int i1=string.indexOf("=",x);
    		String var=string.substring(x+3,i1 );
    		var=var.trim();
    		int i2=string.indexOf(";",i1);
    		String init=string.substring(i1+1,i2);
    		init=init.trim();
    		int i3=string.indexOf("while",i2);
    		int i4=string.indexOf("(",i3);
    		int i5=string.indexOf("!=",i4);
    		int i11=string.indexOf("==",i4);
    		
    		if(i5!=-1) {
	    		int i6=string.indexOf(")",i5);
	    		String var2=string.substring(i4+1,i5);
	    		var2=var2.trim();
	    		if(var.equals(var2)) {
	    			String breakpt=string.substring(i5+2,i6);
	    			breakpt=breakpt.trim();
	    			x=string.indexOf("{",i6+1);
	    			int stack=1,y=0;
	    			for(int j=x+1;j<n;j++){
	    				if(stack==0) {
	    					break;
	    				}
	    					
	    				if(string.charAt(j)=='{')
	    					stack++;
	    				else if(string.charAt(j)=='}') {
	    					stack--;
	    					y=j;
	    				}
	    			}
	    			int i10=string.indexOf(var2,x);
	    			if(i10==-1 || i10>y) {
	    				System.out.println("no matching var inside loop");
	    				return;
	    			}
	    				
	    			int i7=string.indexOf("+=",i10);
	    			int i9=string.indexOf("++",i10);
	    			if(i7!=-1 && i7<y) {
	    				int i8=string.indexOf(";",i7+2);
	    				String increment=string.substring(i7+2,i8);
	    				increment=increment.trim();
	//    				System.out.println(init+" "+breakpt+" "+increment);
	    				if(string.indexOf("break",x)!=-1 && string.indexOf("break",x)<y) {
	    					System.out.println("break found we cant go for case4");
	    					return;
	    				}
	    				int breakpot=Integer.parseInt(breakpt);
	    				int incrementot=Integer.parseInt(increment);
	    				int initial=Integer.parseInt(init);
	    				if(initial>breakpot) {
	    					System.out.println("Initial is greater than breakpoint so unbounded condition case4");
	    					return;
	    				}
	    				
	    				if((breakpot % incrementot)!=initial) {
	    					System.out.println("Case4 ERROR detected");
	    					return;
	    				}
	    			}
	    			else if(i9!=-1 && i9<y) {
	    				String increment="1";
	//    				System.out.println(init+" "+breakpt+" "+increment);
	    			}
	    		}
    		}
    		else if(i11!=-1) {
    			int i6=string.indexOf(")",i5);
    			String var2=string.substring(i4+1,i11);
	    		var2=var2.trim();
	    		if(var.equals(var2)) {
	    			String breakpt=string.substring(i11+2,i6);
	    			breakpt=breakpt.trim();
	    			x=string.indexOf("{",i6+1);
	    			int stack=1,y=0;
	    			for(int j=x+1;j<n;j++){
	    				if(stack==0) {
	    					break;
	    				}
	    					
	    				if(string.charAt(j)=='{')
	    					stack++;
	    				else if(string.charAt(j)=='}') {
	    					stack--;
	    					y=j;
	    				}
	    			}
	    			int i10=string.indexOf(var2,x);
	    			if(i10==-1 || i10>y) {
	    				System.out.println("no matching var inside loop");
	    				return;
	    			}
    		}
    		}
    	}
    	System.out.println("Case4 error not detected");
    }
    
    
    
    
    public static void case5(String s) {
    	StringBuilder string=new StringBuilder(s);
    	int x=string.indexOf("short int");
    	int y=string.indexOf("int");
 
    	if(x!=-1) {
    		int i1=string.indexOf("=",x);
    		String var=string.substring(x+9,i1 );
    		var=var.trim();
    		int i2=string.indexOf("for",i1);
    		int i3=string.indexOf("(",i2);
    		int i4=string.indexOf("=",i3);
    		String var2=string.substring(i3+1,i4);
    		var2=var2.trim();
    		if(var.equals(var2)) {
    			int i5=string.indexOf(";",i4);
    			String value1=string.substring(i4+1,i5);
    			value1=value1.trim();
    			int i6=string.indexOf(";",i5+1);
    			int i7=string.indexOf("<",i5);
    			int i8=string.indexOf(";",i7);
    			String value2=string.substring(i7+1,i8);
    			value2=value2.trim();
    			int value3=Integer.parseInt(value1);
    			int value4=Integer.parseInt(value2);
    			int value5=Integer.parseInt(datatype[0][1]);
    			if(value4>value3 && (value4>value5)) {
    				System.out.println("Case5 ERROR detected for Short");
    				return;
    			}
    		}
    	}
    	
    	else if(y!=-1) {
    		
    		int i1=string.indexOf("=",y);
    		String var=string.substring(y+3,i1 );
    		var=var.trim();
    		int i2=string.indexOf("for",i1);
    		int i3=string.indexOf("(",i2);
    		int i4=string.indexOf("=",i3);
    		String var2=string.substring(i3+1,i4);
    		var2=var2.trim();
    		System.out.println(var2+" "+var);
    		if(var.equals(var2)) {
    			int i5=string.indexOf(";",i4);
    			String value1=string.substring(i4+1,i5);
    			value1=value1.trim();
    			int i6=string.indexOf(";",i5+1);
    			int i7=string.indexOf("<",i5);
    			int i8=string.indexOf(";",i7);
    			String value2=string.substring(i7+1,i8);
    			value2=value2.trim();
    			long value3=Long.parseLong(value1);
    			long value4=Long.parseLong(value2);
    			long value5=Integer.parseInt(datatype[1][1]);
    			if(value4>value3 && (value4>value5)) {
    				System.out.println("Case5 ERROR detected for Int ");
    				return;
    			}
    		}		
    	}
    	System.out.println("Case5 not detected");
    }
    

    
    
    public static void warn_case3(String s) {
    	StringBuilder string = new StringBuilder(s);
    	int i=string.indexOf("while");
    	if(i==-1)
    		return;
    	else {
    		int i1=string.indexOf("(",i);
    		int i2=string.indexOf(")",i1);
    		int i3=string.indexOf("==",i1);
    		int i4=string.indexOf("=",i1);
    		
    		if((i3==-1) && (i4!=-1) && (i4<i2)) {
    			System.out.println("Warning Case3 Assingment in place of comparison operator");
    			return;
    		}
    	}
    	System.out.println("warning case3 not found");
    }
    
    
    
    
    public static void warn_case2(String s) {
    	StringBuilder string = new StringBuilder(s);
    	int i=string.indexOf("while");
    	int n=s.length();
    	if(i==-1)
    		return;
    	else {
    		int x=string.indexOf("{",i+1);
    		int stack=1,y=0;
			for(int j=x+1;j<n;j++){
				if(stack==0) {
					break;
				}
					
				if(string.charAt(j)=='{')
					stack++;
				else if(string.charAt(j)=='}') {
					stack--;
					y=j;
				}
			}
			int i1=string.indexOf("continue",x);
			int i2=string.indexOf("break",x);
			if(i1<i2 && i1<y && i2<y) {
				System.out.println("Warning Case2 its continue before break");
				return;
			}
			
    	}
    	System.out.println("warning case2 not found");
    }
    
    
    
    
    public static void warn_case1(String s) {
    	StringBuilder string = new StringBuilder(s);
    	int i=string.indexOf("while");
    	int n=s.length();
    	if(i==-1)
    		return;
    	else {
    		int i1=string.indexOf("(",i);
    		int i2=string.indexOf(")",i1);
    		String check=string.substring(i1+1,i2);
    		if(check.trim().equals("true") || check.trim().equals("1")) {
    			int x=string.indexOf("{",i2+1);
    			int stack=1,y=0;
    			for(int j=x+1;j<n;j++){
    				if(stack==0) {
    					break;
    				}
    					
    				if(string.charAt(j)=='{')
    					stack++;
    				else if(string.charAt(j)=='}') {
    					stack--;
    					y=j;
    				}
    			}
    			// Here y store loop ending okay?
    			System.out.println("Ending of Loop while "+y);
    			// CHECKING IF
    			
    			int j1=string.indexOf("if",x);
    			if(j1>y || j1==-1) {
    				System.out.println("No if found");
    				return;
    				
    			}
    			int j2=string.indexOf("{",j1);
    			int stack2=1,y2=0;
    			boolean counter1=false,counter2=false;
    			for(int j=j2+1;j<n;j++){
    				if(stack2==0) {
    					break;
    				}

    				if(string.charAt(j)=='{')
    					stack++;
    				else if(string.charAt(j)=='}') {
    					stack2--;
    					y2=j;
    				}
    			}
    			// Here y2 store ending of if
    			System.out.println("Ending of if "+y2);
    			if(string.indexOf("break",j2)!=-1 && string.indexOf("break",j2)<y2)
    				counter1=true;
    			
    			
    			
    			//CHECKING ELSE
    			
    			j1=string.indexOf("else",y2);
    			if(j1>y || j1==-1) {
    				System.out.println("No else found");
    				return;
    			}
    			j2=string.indexOf("{",j1);
    			stack2=1;
    			y2=0;
    			for(int j=j2+1;j<n;j++){
    				if(stack2==0) {
    					break;
    				}

    				if(string.charAt(j)=='{')
    					stack++;
    				else if(string.charAt(j)=='}') {
    					stack2--;
    					y2=j;
    				}
    			}
    			System.out.println("Ending of else "+y2);
    			if(string.indexOf("break",j2)!=-1 && string.indexOf("break",j2)<y2)
    				counter2=true;
    			
    			
    			if((string.indexOf("break",y2)==-1) || (string.indexOf("break",y2)>y)) {
    				if(counter1==true && counter2==true) {
    					System.out.println("break found in both if and else so warn_case1 Not Found");
    				}
    				else {
    					System.out.println("WARNING CASE1 FOUND");
    				}
    			}
    			else {
    				System.out.println("break found before loop ending after else ending");
    			}
    				
    			}
    			
    		}
    	}
    
    
	public static void warn_case4(String s){
		StringBuilder string = new StringBuilder(s);
		int i=s.indexOf("for");
		int i1=s.indexOf("(",i);
		int i2=s.indexOf(";",i1);
		int i3=s.indexOf(";",i2+1);
		int i4=s.indexOf(")",i3);
		int i5=s.indexOf("=",i1);
		String var=string.substring(i1+1,i5);
		var=var.trim();
		String init=string.substring(i5+1,i2);
		init=init.trim();
		int i6=s.indexOf("<",i2+1);
		String bound=string.substring(i6+1,i3);
		bound=bound.trim();
		String increment=string.substring(i3+1,i4);
		int initial=Integer.parseInt(init);
		int bounded=Integer.parseInt(bound);
		if(initial<bounded) {
			if(increment.indexOf("--")!=-1) {
				System.out.println("Warn Case4 detected");
				return;
			}
			
		}
		else if(initial>bounded) {
			if(increment.indexOf("++")!=-1) {
				System.out.println("Warn Case4 detected");
				return;
			}
		}
		
		System.out.println("Warning Case4 not detected");
	}
    
    
    
    
    
	public static void main(String[] args) {
	        Scanner file;
	        PrintWriter writer;
	        try {
	        	File text = new File("C:\\Users\\user\\eclipse-workspace\\useme\\src\\useme\\file.txt"); // PATH OF FILE TO BE READ
	        	File text2 = new File("C:\\Users\\user\\eclipse-workspace\\useme\\src\\useme\\file2.txt"); // PATH OF FILE TO WRITE AFTER REMOVING LINE SPACES OF CODE IN FILE1
	            file = new Scanner(text);
	            writer = new PrintWriter(text2);
	            while (file.hasNext()) {
	                String line = file.nextLine();
	                if (!line.isEmpty() && !line.trim().isEmpty() && line!=null ) {
	                    writer.write(line);
	                    writer.write("\n");
	                }
	            }

	            file.close();
	            writer.close();

	        } catch (FileNotFoundException ex) {
	            System.out.println("Exception here");
	        }
	        String pro=usingBufferedReader( "C:\\Users\\user\\eclipse-workspace\\useme\\src\\useme\\file2.txt");
//	        System.out.println(pro);
	        String ans[]=pro.split("\n");
//	        for(int i=0;i<ans.length;i++) {
//	        	System.out.print(ans[i]+",");
//	        }
//	        case1(pro);
//	        case2(pro);
	        warn_case3(pro);
//	        warn_case2(pro);
//	        warn_case1(pro);
//	        case5(pro);
//	        case4(pro);
//	        warn_case4(pro);
		}
	}

