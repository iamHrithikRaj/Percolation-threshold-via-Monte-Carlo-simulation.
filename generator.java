import java.io.*;
class Generator{
    public void generateCods(int n){/*Generates coordinates 
        in input.txt*/
        try{
            File f = new File("input.txt");
            FileWriter writer = new FileWriter(f);
            BufferedWriter Bwriter = new BufferedWriter(writer);  
            for(int i = 0 ;i<n*n;i++)  {
                int x = (int)(Math.random()*n+1);
                int y = (int)(Math.random()*n+1);
                if(x>0 && y>0)
                    Bwriter.write(x+" "+y+"\n");
            }
            Bwriter.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}