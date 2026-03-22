package Julian.project.bean;

public class enemy extends character {
    public String skill;
    public boolean defending;

    public enemy() {
        super();
    }

    public enemy(String name, int hp, int attack, int defense, String skill) {
        super(name, hp, attack, defense);
        this.skill = skill;
    }

    //伤害判定
    @Override
    public void takedamage(int damage) {
        if (defending) {
            damage = damage / 2;
            defending = false;
        }
        super.takedamage(damage);
    }

    @Override
    public void showskill() {
        System.out.println("[" + skill + "]");
    }

    @Override
    public void show() {

        System.out.println("[敌人名称：" + name + "\t\t当前血量：" + Hp + "\t\t攻击力：" + Attack + "\t\t防御力：" + Defense + "]");
    }
}
