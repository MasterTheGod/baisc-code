package Julian.project.bean;

import java.util.ArrayList;

public class hero extends character {
    public ArrayList<String> skilllist;

    public hero() {
        skilllist = new ArrayList<>();
        skilllist.add("普通攻击");
        skilllist.add("强力一击");
        skilllist.add("生命吸取");
    }

    public hero(String name, int hp, int attack, int defense) {
        super(name, hp, attack, defense);
        skilllist = new ArrayList<>();
        skilllist.add("普通攻击");
        skilllist.add("强力一击");
        skilllist.add("生命吸取");
    }

    @Override
    public void showskill() {
        System.out.println(skilllist);
    }

    @Override
    public void show() {

        System.out.println("[我方英雄名称：" + name + "\t\t\t当前血量：" + Hp + "\t\t\t攻击力：" + Attack + "\t\t\t防御力：" + Defense + "]");
    }
}
