import java.util.ArrayList;
import java.util.List;

class Node {
    String id;
    String sentence;
    List<Node> sons;
    public static String convertIdToTabs(String id) {
        int count = 0;
        for (int i = 0; i < id.length(); i++) {
            if (id.charAt(i) == '.') count++;
        }
        String result = "";
        for (int i = 0; i < count - 1; i++) result += "\t";
        return result;
    }
    private Node(String id, String sentence) {
        this.id = id;
        this.sentence = sentence;
        sons = new ArrayList<>();
    }
    private void addSon(String sentence) {
        sons.add(new Node(id + (sons.size() + 1) + ".", sentence));
    }
    public Node() { //creates dummy root
        id = "";
        sons = new ArrayList<>();
    }
    public void addSon(int level, String sentence) {
        if (level == 0) addSon(sentence);
        else sons.get(sons.size() - 1).addSon(level - 1, sentence);
    }
    public void print() {
        System.out.println(toString());
        sons.forEach(item->item.print());
    }
    @Override
    public String toString() {
        if (id == "") return "";
        return convertIdToTabs(id) + id + " " + sentence;
    }
}


public class Main {
    public static int calculateLevel(String str) {
        if (!str.contains("\t")) return 0;
        return str.lastIndexOf("\t") - str.indexOf("\t") + 1;
    }
    public static String cutTabs(String str) {
        return str.substring(str.lastIndexOf("\t") + 1);
    }
    public static void main(String[] args) {
        String str1 = "this is the line";
        String str2 = "\tthis is the line";
        String str3 = "\t\tthis is the line";
        String str4 = "\t\tthis is the line";
        String str5 = "\tthis is the line";
        Node root = new Node();
        root.addSon(calculateLevel(str1),cutTabs(str1));
        root.addSon(calculateLevel(str2),cutTabs(str1));
        root.addSon(calculateLevel(str3),cutTabs(str1));
        root.addSon(calculateLevel(str4),cutTabs(str1));
        root.addSon(calculateLevel(str5),cutTabs(str1));
        //root.addSon(str1.lastIndexOf("\t") - str1.indexOf("\t"),"this is the line");
        root.print();
    }
}
