package Julian.project.bean;

public class character {
    public String name;
    public int Hp;
    public int MaxHp;
    public int Attack;
    public int Defense;

    public character() {

    }

    public character(String name, int hp, int attack, int defense) {
        this.name = name;
        Hp = hp;
        MaxHp = Hp;
        Attack = attack;
        Defense = defense;
    }

    //判断角色是否存活
    public boolean isAlive() {
        return Hp > 0;
    }

    //回血
    public void heal(int healHp) {
        Hp += healHp;
        if (Hp > MaxHp) {
            Hp = MaxHp;
        }
    }

    //收到伤害
    public void takedamage(int damage) {
        if (damage <= 1) {
            damage = 1;
        }
        Hp -= damage;
        if (Hp <= 0) {
            Hp = 0;
        }
    }

    public void show() {
    }

    public void showskill() {
    }

}
