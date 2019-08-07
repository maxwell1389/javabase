package com.github.maxwell.base.main;

public enum ScoreType {

    TOTAL_SCORE("总积分"), FREE_SCORE("免费积分"), COST_SCORE("总消费积分"), DEVEL_SCORE("建设积分"),COMMUN_SCORE("社区积分"),
    SPEND_SCORE("消费积分"),VIDEO_SCORE("视频积分"),TASK_SCORE("任务积分"),FIRE_SCORE("火焰积分"),REGISTER_SCORE("注册积分"),
    INVITE_SCORE("邀请积分"), BACK_SCORE("回归积分");
    private String desc;

    ScoreType(String desc) {
        this.desc = desc;
    }

    public String desc(){
        return this.desc;
    }
}
