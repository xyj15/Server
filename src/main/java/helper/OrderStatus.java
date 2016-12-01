package helper;

/**
 * Created by 97147 on 2016/11/19.
 */
public enum OrderStatus {
    Executed(0), Unexecuted(1), Abnormal(2), Canceled(3);
    int v;
    private OrderStatus(int value){
        this.v=value;
    }

    public int getV() {
        return v;
    }

}
