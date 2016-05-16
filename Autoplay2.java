import java.util.*;
import java.io.*;
import java.lang.*;

//Author: Lilly Tong
//save this file under your src directory, and run it

//ISSUE: I don't know how to turn GUI off so it loads it everytime a new game launches. If I turn GUI off using the "-ng" switch somehow the game won't run. If someone can fix this that would be great. 

public class Autoplay2
{
	
	public final static int NUM_OF_GAMES = 10;

    public static void main(String args[])
    {
        boolean do_print = args.length > 0 && args[0].equals("-p");

        try {
            Runtime rt = Runtime.getRuntime();

            ProcessBuilder server_pb = new ProcessBuilder("java", "-cp", "../jar/omweso.jar",  "boardgame.Server", "-ng", "-k");

            if(do_print){
                server_pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            }

            Process server = server_pb.start();

            try {
                Thread.sleep(1000);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            for (int i=0; i<NUM_OF_GAMES; i++) {
                System.out.println("Game "+i);
                //  ai3player
                String client1_line = "java -cp ../jar/omweso.jar:./src boardgame.Client s260560802.s260560802Player localhost 8123";
                // String client1_line = "java -cp ../jar/omweso.jar boardgame.Client omweso.CCRandomPlayer";
                String client2_line = "java -cp ../jar/omweso.jar boardgame.Client omweso.CCRandomPlayer";

                Process client1 = rt.exec(client1_line);
                Process client2 = rt.exec(client2_line);
                
                try{
                    client1.waitFor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try{
                    client2.waitFor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            server.destroy();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.exit(0);

    }

//	public final static int NUM_OF_GAMES = 10;
//	
//    public static void main(String args[])
//    {
//       
//		Runtime rt = Runtime.getRuntime();
//		try {
//			for (int i=0; i<NUM_OF_GAMES; i++)
//			{
//			System.out.println("Game "+i);
//			Process server = rt.exec("java -cp jar/omweso.jar boardgame.Server -k -p 8123  -b omweso.CCBoard");
//
//			//Process server = rt.exec("java -cp jar/omweso.jar boardgame.Server -ng -p 8123  -b omweso.CCBoard");
//			Process client1 = rt.exec("java -cp jar/omweso.jar:./aiplayer/src boardgame.Client s260560802.s260560802Player localhost 8123"); //your script of compiling and loading client1. ex. java -cp jar/omweso.jar:./src/ boardgame.Client sXXXXXXXXX.sXXXXXXXXX
////			Process client1 = rt.exec("java -cp jar/omweso.jar boardgame.Client omweso.CCRandomPlayer localhost 8123"); //your script of compiling and loading client2. ex. java -cp jar/omweso.jar:./src/ boardgame.Client sXXXXXXXXX.sXXXXXXXXX
//			Process client2 = rt.exec("java -cp jar/omweso.jar boardgame.Client omweso.CCRandomPlayer localhost 8123"); //your script of compiling and loading client2. ex. java -cp jar/omweso.jar:./src/ boardgame.Client sXXXXXXXXX.sXXXXXXXXX
//		
//		    InputStream instream = client1.getInputStream();
//		    
//		    for(int j = 0; j < instream.available(); j++){
//		    	System.out.print(instream.read());
//		    }
//			
//			File outcomes = new File("logs/outcomes.txt");
//			long timestamp_before = outcomes.lastModified();
//			
//			boolean gameEnded=false;
//			
//			while (!gameEnded)
//			{
//				long timestamp_after=outcomes.lastModified();
//				if (timestamp_before != timestamp_after)
//				{
//					gameEnded=true;
//				}	
//			}
//	
//			server.destroy();
//			client1.destroy();
//			client2.destroy();
//			}
//			
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.exit(0);
           
    }
