package fal.salt.com.fal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE =
            "com.bignerdranch.android.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN =
            "com.bignerdranch.android.geoquiz.answer_shown";
    private boolean mAnswer;
    private TextView mTextView;
    private Button mCheatBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mAnswer = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);
        mCheatBtn = (Button) findViewById(R.id.show_answer_button);
        mTextView = (TextView)findViewById(R.id.answer_text_view);
        mCheatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswer == true) {
                    mTextView.setText(R.string.true_button);
                }else {
                    mTextView.setText(R.string.false_button);
                }
               setShowedAnswer(true);
            }
        });
    }


    public static Intent newIntent(Context context, Boolean answer) {
        Intent i = new Intent(context,ChatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE,answer);
        return i;
    }


    private void setShowedAnswer(boolean isShow) {
        Intent i = new Intent();
        i.putExtra(EXTRA_ANSWER_SHOWN,isShow);
        setResult(RESULT_OK,i);
    }

    public static boolean isAnswerShown(Intent i) {
        return i.getBooleanExtra(EXTRA_ANSWER_SHOWN,false);
    }


}
