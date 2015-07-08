import java.io.*;

class MsgSender{
    private static Process p;
    private static PrintWriter pw;
    static {
        try{
            p = Runtime.getRuntime().exec("./publisher/build/publisher");
        } catch(IOException ex) {}
        pw = new PrintWriter(p.getOutputStream());
    }
  // private static ProcessBuilder pb = new ProcessBuilder("./publisher/build/publisher");

  // MsgSender(){
  //   try{
  //       //   p = Runtime.getRuntime().exec("sh ./publisher/build/publisher");
  //       // p = pb.start();
  //   } catch(IOException ex) {}
  //   }

    synchronized static void sndMsg(String msg){
        MsgSender.pw.println(msg);
        MsgSender.pw.flush();
    }

    public static void main(String[] args) {
        // MsgSender ms = new MsgSender();
        // ms.pw.println("train::car_2::left_wheel_joint 5");
        // ms.pw.flush();
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // ms.pw.close();
        // ms.p.destroy();
        // System.out.println("train::car_2::left_wheel_joint 5");

        MsgSender.sndMsg("train::car_2::left_wheel_joint -5");
    }
}
