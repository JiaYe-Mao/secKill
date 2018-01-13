package Dto;

import entity.SuccessKilled;
import enums.SecKillStateEnum;

public class SecKillExecution {

    private long secKillId;

    private int state;

    private String stateInfo;

    private SuccessKilled successKilled;

    /**
     * 秒杀成功则使用这个
     * @param secKillId
     * @param secKillStateEnum
     * @param successKilled
     */
    public SecKillExecution(long secKillId, SecKillStateEnum secKillStateEnum, SuccessKilled successKilled) {

        this.secKillId = secKillId;
        this.state = secKillStateEnum.getState();
        this.stateInfo = secKillStateEnum.getStateInfo();
        this.successKilled = successKilled;
    }

    /**
     * 失败的话只传状态(state)和失败信息
     * @param secKillId
     * @param secKillStateEnum
     */
    public SecKillExecution(long secKillId, SecKillStateEnum secKillStateEnum) {
        this.secKillId = secKillId;
        this.state = secKillStateEnum.getState();
        this.stateInfo = secKillStateEnum.getStateInfo();
    }

    public long getSecKillId() {
        return secKillId;
    }

    public void setSecKillId(long secKillId) {
        this.secKillId = secKillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    @Override
    public String toString() {
        return "SecKillExecution{" +
                "secKillId=" + secKillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }
}
