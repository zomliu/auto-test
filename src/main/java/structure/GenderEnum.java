package structure;

public enum  GenderEnum {
    MALE(1, "男生"),
    FMALE(2, "女生");

    private int value;
    private String desc;

    GenderEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getVal(){
        return this.value;
    }

    public String getDesc(){
        return this.desc;
    }
}
