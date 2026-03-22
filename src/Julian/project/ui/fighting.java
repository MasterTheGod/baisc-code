package Julian.project.ui;

import Julian.project.bean.enemy;
import Julian.project.bean.hero;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class fighting {
    Scanner sc = new Scanner(System.in);
    Random rd = new Random();

    public void Gamestart(String username) {
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("欢迎" + username + "来到Julian的格斗回合制游戏");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
        hero hero = createhero();

        ArrayList<enemy> enemylist = new ArrayList<>();
        enemylist.add(new enemy("初级战士", 80, 15, 10, "猛击"));
        enemylist.add(new enemy("敏捷刺客", 60, 20, 5, "快速攻击"));
        enemylist.add(new enemy("重装坦克", 120, 10, 13, "防御姿态"));
        enemylist.add(new enemy("神秘法师", 70, 20, 3, "火球术"));

        //准备战斗
        int count = 1;//战斗场数
        int wins = 0;//战斗胜利场数
        //与多个敌人战斗
        while (hero.isAlive()) {
            //每胜利一场敌人数据变化
            if (count > 1) {
                for (int i = 0; i < enemylist.size(); i++) {
                    enemylist.get(i).MaxHp += 10;
                    enemylist.get(i).Hp = enemylist.get(i).MaxHp;
                    enemylist.get(i).Attack += 3;
                    enemylist.get(i).Defense += 2;
                    enemylist.get(i).defending = false;
                }
            }
            //随机选择敌人
            Random rd = new Random();
            enemy enemy = new enemy();
            enemy = enemylist.get(rd.nextInt(enemylist.size()));
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("第" + count + "场战斗开始，你的对手是：" + enemy.name);
            hero.show();
            hero.showskill();
            enemy.show();
            enemy.showskill();

            int round = 1;//回合数
            while (hero.isAlive()) {//和单个敌人战斗进行中
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("第" + round + "回合开始");
                System.out.println(hero.name + "的当前生命值：" + hero.Hp);
                System.out.println(enemy.name + "的当前生命值：" + enemy.Hp);
                //我的回合
                myturn(hero, enemy);
                if (!enemy.isAlive()) {
                    System.out.println("恭喜你，你击败了" + enemy.name + "！");
                    wins++;
                    count++;
                    round = 1;
                    break;
                }
                enemyturn(hero, enemy);
                if (!hero.isAlive()) {
                    System.out.println("你被" + enemy.name + "击败了！");
                    System.out.println("游戏结束，感谢您游玩本游戏，本游戏仅为测试学习Java水平所作，颇为粗糙，感谢游玩，联系我：Q：3469957408");
                    System.exit(0);
                    break;
                }
                round++;
            }
            if (hero.isAlive()) {
                int healHpAfterFight = rd.nextInt(35, 71);
                hero.heal(healHpAfterFight);
                System.out.println("你回复了" + healHpAfterFight + "点血量，你的当前生命值为：" + hero.Hp);
                System.out.println("当前胜场" + wins);
                if (wins % 3 == 0) {
                    hero.MaxHp += 50;
                    hero.heal(50);
                    hero.Attack += 10;
                    hero.Defense += 8;
                    System.out.println("英雄属性提升：最大生命值+50，回复血量50点，攻击力+10，防御力+8");
                    hero.show();
                }
                if (!continueGame())
                    System.exit(0);
            }
        }
    }

    //创建玩家角色,定义其属性
    public hero createhero() {
        hero hero = new hero();
        System.out.println("请输入英雄名称：");
        hero.name = sc.next();
        int points = 20;
        System.out.println("英雄属性点数：" + points);
        System.out.println("每个属性点效果如下：生命增加十点，攻击力增加2点，防御力增加一点");
        int hpPoint = 0;
        int attackPoint = 0;
        int defensePoint = 0;
        while (points > 0) {
            System.out.println("当前属性点：" + points);
            hero.show();
            System.out.println("请输入要分配的生命点数：");
            hpPoint = sc.nextInt();
            if (hpPoint < 0 || hpPoint > points) {
                System.out.println("输入的属性点数不合法,必须大于0且小于剩余点数：" + points);
                continue;
            }
            hero.Hp += hpPoint * 20;
            hero.MaxHp = hero.Hp;
            points -= hpPoint;
            hero.show();
            System.out.println("剩余点数：" + points);
            System.out.println("请输入要分配的攻击点数：");
            attackPoint = sc.nextInt();
            if (attackPoint < 0 || attackPoint > points) {
                System.out.println("输入的属性点数不合法,必须大于0且小于剩余点数：" + points);
                continue;
            }
            hero.Attack += attackPoint * 2;
            points -= attackPoint;
            hero.show();
            System.out.println("剩余属性点数:" + points + ",是否全部分配到防御力？");
            System.out.println("1.是" + "    0.重新分配点数");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    defensePoint = points;
                    hero.Defense += defensePoint;
                    points = 0;
                    break;
                case 0:
                    points = 20;
                    System.out.println("英雄属性点数：" + points);
                    System.out.println("每个属性点效果如下：生命增加20点，攻击力增加2点，防御力增加一点");
                    hpPoint = 0;
                    attackPoint = 0;
                    defensePoint = 0;
                    hero.Hp = 0;
                    hero.Attack = 0;
                    hero.Defense = 0;
                    continue;

                default:
                    points = 20;
                    hpPoint = 0;
                    attackPoint = 0;
                    defensePoint = 0;
                    hero.Hp = 0;
                    hero.Attack = 0;
                    hero.Defense = 0;
                    System.out.println("输入错误，请重新输入：");
                    break;
            }

        }
        return hero;
    }


    //玩家回合
    public void myturn(hero hero, enemy enemy) {

        System.out.println("现在是你的回合，请输入你的操作：");
        System.out.println("1.普通攻击" + "    2.强力一击(消耗10HP)" + "    3.生命汲取(回复30到60点随机血量，造成少量伤害)");
        int choice = sc.nextInt();
        switch (choice) {
            default:
                System.out.println("输入错误，默认使用普通攻击");
            case 1:
                int tempHp = enemy.Hp;
                enemy.takedamage(hero.Attack - enemy.Defense);
                System.out.println(hero.name + "使用了普通攻击," + "对" + enemy.name + "造成了" + (tempHp - enemy.Hp) + "点伤害");
                break;
            case 2:
                if (hero.Hp <= 10) {
                    System.out.println("你的血量不足，默认使用普通攻击");
                    tempHp = enemy.Hp;
                    enemy.takedamage(hero.Attack - enemy.Defense);
                    System.out.println(hero.name + "使用了普通攻击," + "对" + enemy.name + "造成了" + (tempHp - enemy.Hp) + "点伤害");
                } else {
                    tempHp = enemy.Hp;
                    enemy.takedamage((int) (hero.Attack * 1.8 - enemy.Defense * 1.0));
                    System.out.println(hero.name + "使用了强力一击," + "对" + enemy.name + "造成了" + (tempHp - enemy.Hp) + "点伤害");
                    hero.takedamage(10);
                }
                break;
            case 3:
                if (hero.Hp <= 5) {
                    System.out.println("你的血量不足，默认使用普通攻击");
                    tempHp = enemy.Hp;
                    enemy.takedamage(hero.Attack - enemy.Defense);
                    System.out.println(hero.name + "使用了普通攻击," + "对" + enemy.name + "造成了" + (tempHp - enemy.Hp) + "点伤害");
                } else {
                    tempHp = enemy.Hp;
                    enemy.takedamage((int) (hero.Attack * 0.8 - enemy.Defense));
                    int healHp = rd.nextInt(30, 61);
                    hero.heal(healHp);
                    System.out.println(hero.name + "使用了生命汲取," + "对" + enemy.name + "造成了" + (tempHp - enemy.Hp) + "点伤害,同时回复了" + healHp + "点血量");
                    System.out.println("你的剩余血量：" + hero.Hp);
                }
                break;
        }
    }

    //敌方回合
    public void enemyturn(hero hero, enemy enemy) {
        System.out.println("现在是" + enemy.name + "的回合");
        String action = "普通攻击";
        int x = rd.nextInt(2);
        if (x == 1)
            action = enemy.skill;
        switch (action) {
            default:
            case "普通攻击":
                int tempHp = hero.Hp;
                hero.takedamage(enemy.Attack - hero.Defense);
                System.out.println(enemy.name + "使用了普通攻击," + "对" + hero.name + "造成了" + (tempHp - hero.Hp) + "点伤害");
                break;
            case "猛击":
                tempHp = hero.Hp;
                hero.takedamage((int) (enemy.Attack * 1.4 - hero.Defense * 1.0));
                System.out.println(enemy.name + "使用了猛击," + "对" + hero.name + "造成了" + (tempHp - hero.Hp) + "点伤害");
                break;
            case "防御姿态":
                enemy.defending = true;
                System.out.println(enemy.name + "使用了防御姿态，下次所受伤害减半！");
                break;
            case "快速攻击":
                tempHp = hero.Hp;
                hero.takedamage((int) (enemy.Attack * 1.2 - hero.Defense * 0.8));
                System.out.println(enemy.name + "使用了快速攻击," + "对" + hero.name + "造成了" + (tempHp - hero.Hp) + "点伤害");
                break;
            case "火球术":
                tempHp = hero.Hp;
                hero.takedamage((int) (enemy.Attack * 1.8 - hero.Defense));
                System.out.println(enemy.name + "使用了火球术," + "对" + hero.name + "造成了" + (tempHp - hero.Hp) + "点伤害");
                break;
        }
    }

    //判断玩家是否继续游戏
    public boolean continueGame() {
        System.out.println("是否继续游戏？" + "    0.否");
        String choice = sc.next();
        if (choice.equals("0")) {
            System.out.println("游戏结束，感谢您游玩本游戏，本游戏仅为测试学习Java水平所作，颇为粗糙，感谢游玩，联系我：Q：3469957408");
            return false;
        } else
            return true;
    }

}


