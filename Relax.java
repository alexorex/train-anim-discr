import ru.nsu.alife.fs.IAction;

class Relax implements IAction{

    int carNum;
    ProcessBuilder relax1, relax2;

    public Relax(int carNum){
        this.carNum = carNum;
        relax1 = new ProcessBuilder("gz", "joint", "-m", "train", "-j", "car_" + (carNum) + "::left_wheel_joint", "-f", "0");
        relax2 = new ProcessBuilder("gz", "joint", "-m", "train", "-j", "car_" + (carNum) + "::right_wheel_joint", "-f", "0");
    }

    @Override
    public boolean doAction(){
        try{
          relax1.start();
          relax2.start();
          System.out.println(carNum + "relax");
        }
        catch(java.io.IOException ex){}
        return true;
    }
}
