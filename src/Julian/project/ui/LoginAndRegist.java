package Julian.project.ui;

import Julian.project.bean.User;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class LoginAndRegist {
    Scanner sc = new Scanner(System.in);

    ArrayList<User> userlist = new ArrayList();//用户数据集合

    //登陆注册界面
    public void start() {




        while (true) {
            System.out.println("-------------------------------------------------------");
            System.out.println("欢迎来到Julian的格斗回合制游戏");
            System.out.println("请选择你要做的操作：  1.登录    2.注册    3.退出");
            System.out.println("-------------------------------------------------------");
//判断用户执行的操作
            int chioce = sc.nextInt();
            switch (chioce) {
                case 1 -> login(userlist);
                case 2 -> regist(userlist);
                case 3 -> System.exit(0);
                default -> System.out.println("输入错误，请重新输入");
            }
        }
    }

    //登录操作
    public void login(ArrayList<User> userlist) {
        System.out.println("登录模块已加载");
        System.out.println("请输入用户名：");
        String username = sc.next();
        if (!contain(username, userlist)) {
            System.out.println("用户名不存在，" + username + "请先注册");
            return;
        }
        if (!getState(username)) {
            System.out.println("该用户名已被锁定，如要恢复请联系Julian");
            return;
        }
        int count = 0;
        for (; count < 3; count++) {
            System.out.println("请输入密码：");
            String password = sc.next();
            if (!password.equals(userlist.get(indexInList(username, userlist)).getPassword())) {
                if (count == 2) {
                    System.out.println("密码错误，该用户名:" + username + "  已被锁定，如要恢复请联系Julian");
                    userlist.get(indexInList(username, userlist)).setState(false);
                } else
                    System.out.println("密码错误，还剩下" + (2 - count) + "次机会");
                continue;
            }
            String code = createcode();
            System.out.println("验证码是：" + code);
            System.out.println("请输入验证码");
            String Inputcode = sc.next();
            if (!Inputcode.equals(code)) {
                if (count == 2) {
                    System.out.println("验证码错误，该用户名:" + username + "  已被锁定，如要恢复请联系Julian");
                    userlist.get(indexInList(username, userlist)).setState(false);
                } else
                    System.out.println("验证码错误，还剩下" + (2 - count) + "次机会");
                continue;
            } else {
                System.out.println("用户:" + username + " 登陆成功");
                fighting fg = new fighting();
                fg.Gamestart(username);
                break;
            }
        }
    }

    //注册操作
    public void regist(ArrayList<User> userlist) {
        System.out.println("注册模块已加载");
        User user = new User();
        boolean checkRegist = false;
        //输入用户名检测
        while (true) {
            System.out.println("请输入用户名：");
            String username = sc.next();
            //检查长度要求
            if (!checkLen(username, 3, 16)) {
                System.out.println("用户名必须在3到16位");
                continue;
            }
            //检查是否由数字和字母组成，不能是纯数字
            if (!checkNumAndLetter(username)) {
                System.out.println("用户名由且仅由数字和字母组成，且不能是纯数字");
                continue;
            }
            //检查username是否唯一
            if (contain(username, userlist)) {
                System.out.println("用户名重复了,请重新尝试");
                continue;
            }
            //符合要求的username的操作
            user.setUsername(username);
            break;
        }
        //输入密码检测
        while (true) {
            System.out.println();
            System.out.println("温馨提示：密码由且仅由数字和字母组成，且不能是纯数字，不能有重复字母");
            System.out.println("请输入密码：");
            String password1 = sc.next();
            if (!checkLen(password1, 3, 10)) {
                System.out.println("密码必须是3到10位");
                continue;
            }
            ;
            if (!checkNumAndLetter(password1)) {
                System.out.println("密码由且仅由数字和字母组成，且不能是纯数字");
                continue;
            }
            if (!checkPassword(password1)) {
                System.out.println("密码中不能有重复字母");
                continue;
            }
            System.out.println("请输入确认密码：");
            String password2 = sc.next();
            if (password1.equals(password2)) {
                System.out.println("注册成功");
                user.setPassword(password1);
                break;
            } else {
                System.out.println("密码不一致");
            }
        }
        userlist.add(user);
        System.out.println("用户注册成功，用户名为" + user.getUsername() + "的用户，你的ID是:");
        System.out.println(user.getId());
        System.out.println("此ID唯一且不可更改");
    }


    //获取账号username的账号状态
    public boolean getState(String username) {
        for (int i = 0; i < userlist.size(); i++) {
            if (userlist.get(i).getUsername().equals(username)) {
                return userlist.get(i).isState();
            }
        }
        return false;
    }

    //生成随机验证码四字母一数字
    public static String createcode() {
        ArrayList<Character> list = new ArrayList();
        for (int i = 0; i < 26; i++) {
            list.add((char) ('A' + i));
            list.add((char) ('a' + i));
        }
        Random rd = new Random();
        int index = rd.nextInt(52);
        char[] c = new char[4];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            c[i] = list.get(index);
            index = rd.nextInt(52);
            sb.append(c[i]);
        }
        sb.insert(rd.nextInt(5), rd.nextInt(10));
        String string = sb.toString();
        return string;
    }

    //检查字符串长度是否符合要求
    public boolean checkLen(String str, int min, int max) {
        return str.length() >= min && str.length() <= max;
    }

    //用户名由且仅由数字和字母组成，且不能是纯数字
    public boolean checkNumAndLetter(String str) {
        char[] c = str.toCharArray();
        int LetterCount = 0;
        for (int i = 0; i < c.length; i++) {
            if ((c[i] >= 65 && c[i] <= 90) || (c[i] >= 97 && c[i] <= 122)) {
                LetterCount++;
            } else if (c[i] < 48 || c[i] > 57)
                return false;
        }
        if (LetterCount == 0)
            return false;
        else
            return true;
    }

    //判断用户名username在集合list中是否包含
    public boolean contain(String username, ArrayList<User> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUsername().equals(username))
                return true;
        }
        return false;
    }

    //判断username在集合list里面的位置
    public int indexInList(String username, ArrayList<User> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUsername().equals(username))
                return i;
        }
        return -1;
    }

    //检查密码字母是否合法：字母不可重复,必须仅有字母和数字构成
    public boolean checkPassword(String str) {
        char[] c = str.toCharArray();
        int RepeatedLetterCount = 0;
        for (int i = 0; i < c.length; i++) {
            if ((c[i] >= 65 && c[i] <= 90) || (c[i] >= 97 && c[i] <= 122)) {
                if (str.indexOf(c[i]) == str.lastIndexOf(c[i]))
                    continue;
                else
                    return false;
            } else if (c[i] < 48 || c[i] > 57)
                return false;
        }
        return true;
    }


}





