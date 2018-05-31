package org.delphy.testredis.pointcut;

public class Swordman {
    public void block(String enemy) {
        System.out.println(this.getClass().getSimpleName() + "格挡" + enemy);
    }

    public void chop(String enemy) {
        System.out.println(this.getClass().getSimpleName() + "劈砍攻击" + enemy);
    }
}
