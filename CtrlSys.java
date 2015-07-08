import java.util.LinkedList;
import java.util.List;
import java.io.IOException;

import ru.nsu.alife.fs.FS;
import ru.nsu.alife.fs.IAcceptor;
import ru.nsu.alife.fs.IAction;
import ru.nsu.alife.fs.IFS;
import ru.nsu.alife.fs.PredicateSet;

class CtrlSys extends IAcceptor {

    static PathDevi Pd = new PathDevi();
    int curCarNum;
    double proactivity = 1;
    public final IAction takeLeft, takeRight;// relax;
    PredicateSet goal = new PredicateSet();
    IFS primaryFS;

    CtrlSys(int curCarNum){
        this.curCarNum = curCarNum;
        goal.set(Predicate.onTrack, true);
        primaryFS = (IFS) new FS(goal, 10);

        takeLeft = new TakeLeft(curCarNum);
        takeRight = new TakeRight(curCarNum);
        // relax = new Relax(curCarNum);
    }

    @Override
    public PredicateSet getCurrentSituation() {
        PredicateSet situation = new PredicateSet();

        int par = (int) (PathDevi.distList[curCarNum-1] / 0.1f);

            situation.set(Predicate.onTrack, par == 0);

            situation.set(Predicate.left_1, par == -1);
            situation.set(Predicate.left_2, par == -2);
            situation.set(Predicate.left_3, par == -3);
            situation.set(Predicate.left_4, par == -4);

            situation.set(Predicate.right_1, par == 1);
            situation.set(Predicate.right_2, par == 2);
            situation.set(Predicate.right_3, par == 3);
            situation.set(Predicate.right_4, par == 4);

            situation.set(Predicate.left_far, par < -4 );
            situation.set(Predicate.right_far, par > 4 );
        // System.out.println();
        return situation;
    }

    @Override
    public IAction getRandomAction() {
        IAction action;
        switch ((int) (Math.random() * 2)){
            case 0: action = takeLeft;
                    break;
            case 1: action = takeRight;
                    break;
            default: action = (IAction) new Object();
                    break;
        }
        return(action);
    }
    @Override
    public double getProactivity(){
        proactivity = proactivity * 0.95;
        return proactivity;
    }

    public static void main(String[] args) {
        // try{
        //     java.lang.Runtime.getRuntime().exec("gz physics -s 0.00033");
        // } catch (IOException ex) {}

        CtrlSys cs2, cs3, cs4, cs5;
        cs2 = new CtrlSys(2);
        cs3 = new CtrlSys(3);
        cs4 = new CtrlSys(4);
        cs5 = new CtrlSys(5);

        try{
            for(;;){
                cs2.primaryFS.reachGoal(cs2);
                cs3.primaryFS.reachGoal(cs3);
                cs4.primaryFS.reachGoal(cs4);
                cs5.primaryFS.reachGoal(cs5);

                Thread.sleep(1000);
            }
        } catch(InterruptedException ex){}
    }
}
