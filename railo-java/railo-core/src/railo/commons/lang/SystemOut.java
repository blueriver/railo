package railo.commons.lang;

import java.io.PrintWriter;
import java.util.Date;

import railo.runtime.PageContext;
import railo.runtime.engine.ThreadLocalPageContext;

public final class SystemOut {

	public static int OUT=1;
	public static int ERR=2;
	
    /**
     * logs a value 
     * @param value
     */
    public static void printDate(PrintWriter pw,String value) {
    	long millis=System.currentTimeMillis();
    	pw.write(
    			new Date(millis)
    			+"-"
    			+(millis-(millis/1000*1000))
    			+" "+value+"\n");
    	pw.flush();
    }
    /**
     * logs a value 
     * @param value
     */
    public static void print(PrintWriter pw,String value) {
    	pw.write(value+"\n");
    	pw.flush();
    } 


	public static void printStack(PrintWriter pw) {
		new Throwable().printStackTrace(pw);
	}

	public static void printStack(int type) {
		PageContext pc=ThreadLocalPageContext.get();
    	if(pc!=null) {
    		if(type==ERR)
    			printStack(pc.getConfig().getErrWriter());
    		else 
    			printStack(pc.getConfig().getOutWriter());
    	}
    	else {
    		printStack(new PrintWriter((type==ERR)?System.err:System.out));
    	}
	}
    
    /**
     * logs a value 
     * @param value
     */
    public static void printDate(String value) {
    	printDate(value,OUT);
    }
    
    public static void printDate(String value,int type) {
    	printDate(getPrinWriter(type),value);
    }
    

    public static PrintWriter getPrinWriter(int type) {
    	PageContext pc=ThreadLocalPageContext.get();
    	if(pc!=null) {
    		if(type==ERR) return pc.getConfig().getErrWriter();
    		return pc.getConfig().getOutWriter();
    	}
    	return new PrintWriter((type==ERR)?System.err:System.out);
    }
    
    
    
    /**
     * logs a value 
     * @param value
     */
    
    public static void print(String value) {
    	print(value, OUT);
    }
    
    public static void print(String value,int type) {
    	PageContext pc=ThreadLocalPageContext.get();
    	if(pc!=null) {
    		if(type==ERR)
    			print(pc.getConfig().getErrWriter(),value);
    		else 
    			print(pc.getConfig().getOutWriter(),value);
    	}
    	else {
    		print(new PrintWriter((type==ERR)?System.err:System.out),value);
    	}
    }

}
