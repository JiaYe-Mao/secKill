package enums;

public enum SecKillStateEnum {
    SUCCESS(1, "secKill succeeded"),
    END(0, "secKill ended"),
    REPEAT_KILL(-1, "secKill repeated"),
    INNER_ERROR(-2, "inner error"),
    DATA_REWRITE(-3, "secKill data rewrite");

    private int state;

    private String stateInfo;

    SecKillStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
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

    public static SecKillStateEnum stateOf(int index){
        for (SecKillStateEnum state : values()){
            if (state.getState() == index){
                return state;
            }
        }
        return null;
    }
}
