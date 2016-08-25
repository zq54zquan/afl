package fal.salt.com.fal;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mChatButton;
    private boolean isCheat;
    private Question[] mQuestions = new Question[] {
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    private  TextView mTextView;
    private int mCurrentIndex = 0;
    private static final String TAG = "QuizActivityTag";
    private static final String indexKey = "index";
    private static final int REQ_CHAET = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate bunlde called");
        setContentView(R.layout.activity_quiz);
        if (savedInstanceState!= null) {
            mCurrentIndex = savedInstanceState.getInt(indexKey);
        }
        mFalseButton = (Button)this.findViewById(R.id.false_button);
        mTrueButton = (Button) this.findViewById(R.id.true_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkQuestion(false);
            }
        });
        mTextView = (TextView) this.findViewById(R.id.question_text_view);
        mTextView.setText(mQuestions[mCurrentIndex].getTextId());
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkQuestion(true);
            }

        });
        mNextButton = (Button)this.findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = (mCurrentIndex+1)%(mQuestions.length);
                mCurrentIndex = index;
                isCheat = false;
                TextView t = (TextView)QuizActivity.this.findViewById(R.id.question_text_view);
                t.setText(mQuestions[index].getTextId());
            }
        });

        mChatButton = (Button)findViewById(R.id.cheat_button);
        mChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean answer = mQuestions[mCurrentIndex].isAnswerTrue();
                Intent i = ChatActivity.newIntent(QuizActivity.this,answer);
                startActivityForResult(i,REQ_CHAET);

            }
        });

    }

    private void checkQuestion(boolean ans) {
        int message = 0;
        if (isCheat) {
            message = R.string.judgment_toast;
        }else {
        boolean right = this.mQuestions[mCurrentIndex].isAnswerTrue();
        if (right == ans) {
            message = R.string.correct_toast;
        }else  {
            message = R.string.incorrect_toast;
        }
        }
        Toast.makeText(QuizActivity.this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CHAET && resultCode == RESULT_OK) {
            if (ChatActivity.isAnswerShown(data)) {
                isCheat = true;
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"onStart called");
    }
    @Override
    public void  onStop() {
        super.onStop();
        Log.d(TAG,"onStop called");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d(TAG,"onPause called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(indexKey,this.mCurrentIndex);
    }
}
