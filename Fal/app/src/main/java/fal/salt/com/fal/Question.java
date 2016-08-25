package fal.salt.com.fal;

/**
 * Created by zhouquan on 16/8/23.
 */
public class Question {
    private int mTextId;

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean mAnswerTrue) {
        this.mAnswerTrue = mAnswerTrue;
    }

    public int getTextId() {
        return mTextId;
    }

    public void setTextId(int mTextId) {
        this.mTextId = mTextId;
    }

    private boolean mAnswerTrue;

    public Question(int mTextId, boolean mAnswerTrue){
        this.mAnswerTrue = mAnswerTrue;
        this.mTextId  = mTextId;

    }
}
