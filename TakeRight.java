import ru.nsu.alife.fs.IAction;

class TakeRight implements IAction{

    int carNum;
    // ProcessBuilder takeRight, relax;

    public TakeRight(int carNum){
        this.carNum = carNum;
        // takeRight = new ProcessBuilder("gz", "joint", "-m", "train", "-j", "car_" + (carNum) + "::right_wheel_joint", "-f", "-10");
        // relax = new ProcessBuilder("gz", "joint", "-m", "train", "-j", "car_" + (carNum) + "::right_wheel_joint", "-f", "0");
    }

    @Override
    public boolean doAction(){
        try{
        //   takeRight.start();
            MsgSender.sndMsg("train::car_" + (carNum) + "::left_wheel_joint -10");
            System.out.println(carNum + "right");
            Thread.sleep(300);
            MsgSender.sndMsg("train::car_" + (carNum) + "::left_wheel_joint 0");
        //   relax.start();
    } //catch (java.io.IOException ex) {}
        catch (InterruptedException ex) {}
        return true;
    }
}
