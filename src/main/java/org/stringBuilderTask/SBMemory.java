package stringBuilderTask;

import java.util.ArrayDeque;
import java.util.Deque;

public class SBMemory {
    private final Deque<String> stringBuilderMemory = new ArrayDeque<>();
    private StringBuilder StrFromBuilder;

    public SBMemory() {

    }

    public void backup(StringBuilder str){

        stringBuilderMemory.push(str.toString());

    }

    public boolean canUndo() {
        return !stringBuilderMemory.isEmpty();
    }

    public String undo(){
        if (canUndo()) {
            return stringBuilderMemory.removeFirst();
        }
        else return "Memory is Empty";
    }


}