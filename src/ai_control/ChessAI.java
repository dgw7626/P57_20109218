/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_control;

import java.util.ArrayList;
import java.util.LinkedList;
import view.ChessView;

/**
 *
 * @author Hanul Rheem 20109218
 */
public class ChessAI implements ChessAIInterfaces{
    //Initializing AI path selection sets.
    public ArrayList<int[]> ChessAIPath;
    @Override
    public void InitializeBehaviourSets() {
        this.ChessAIPath = new ArrayList<>();
        ChessAIPath.add(new int[]{220,100,212,224});
        ChessAIPath.add(new int[]{222,39,220,158});
        ChessAIPath.add(new int[]{219,160,472,156});
        ChessAIPath.add(new int[]{472,156,471,410});
        ChessAIPath.add(new int[]{156,34,344,218});
        ChessAIPath.add(new int[]{345,219,157,413});
        ChessAIPath.add(new int[]{282,96,281,221});
        ChessAIPath.add(new int[]{346,131,92,284});
        ChessAIPath.add(new int[]{94,36,155,155});
        ChessAIPath.add(new int[]{408,36,345,158});
        
    }
}
