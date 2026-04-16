package stringBuilderTask;

public class Main {


    public static void main(String[] args) {

        SBMemory snapshots = new SBMemory();
        StringBuilder stringBuilder = new StringBuilder("123");

        snapshots.backup(stringBuilder);
        stringBuilder.append("qwerty");

        snapshots.backup(stringBuilder);
        stringBuilder.insert(2, "gkgkg");

        System.out.println(stringBuilder);
        System.out.println(snapshots.undo());


    }
}

