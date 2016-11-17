package theson.com.ailatrieuphu.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import theson.com.ailatrieuphu.R;
import theson.com.ailatrieuphu.manager.DatabaseManager;
import theson.com.ailatrieuphu.manager.SoundManager;
import theson.com.ailatrieuphu.model.DialogCall;
import theson.com.ailatrieuphu.model.DialogEndTime;
import theson.com.ailatrieuphu.model.DialogLevelMoney;
import theson.com.ailatrieuphu.model.DialogChangeQuestion;
import theson.com.ailatrieuphu.model.DialogExitGame;
import theson.com.ailatrieuphu.model.Question;

public class PlayActivity extends AppCompatActivity implements DialogExitGame.OnReceiveDataListener,
        DialogChangeQuestion.OnReceiveNextDataListener,
        View.OnClickListener, Runnable {

    private static final int ANSWER_A = 1;
    private static final int ANSWER_B = 2;
    private static final int ANSWER_C = 3;
    private static final int ANSWER_D = 4;

    public static final int TIME_PLAY_EACH_QUESTION = 34;
    public static final int MONEY_YES = 888;
    public static final int MONEY_NO = 999;

    public static final int TIME_REMAIN_EACH_QUESTION = 101;
    public static final int WAIT_TRUE_ANSWER = 102;
    public static final int CORRECT_ANSWER = 103;
    public static final int BACK_TO_NORMAL = 104;
    public static final int CREATE_QUESTION = 105;
    public static final int WRONG_ANSWER = 106;


    public static final int REQUEST_CODE = 999;
    public static final String KEY_MONEY = "KEY_MONEY";
    public static final String KEY_NUMBER_QUESTION = "KEY_NUMBER_QUESTION";


    private int myAnswer;
    private int trueCase;
    private int test;
    private int level;
    private int time;
    private int numberQuestion;
    private boolean isRunning;
    private boolean userHelper5050;

    private DialogChangeQuestion dialogChangeQuestion;
    private DialogEndTime dialogEndTime;
    private DialogLevelMoney dialogLevelMoney;
    private DialogExitGame dialogExitGame;
    private DialogCall dialogCall;

    private TextView txtQuestion;
    private TextView txtAnswerA;
    private TextView txtAnswerB;
    private TextView txtAnswerC;
    private TextView txtAnswerD;

    private TextView txtA;
    private TextView txtB;
    private TextView txtC;
    private TextView txtD;

    private TextView txtTime;
    private TextView txtNumberQuestion;
    private TextView txtMoney;

    private ImageView imgAnswerA;
    private ImageView imgAnswerB;
    private ImageView imgAnswerC;
    private ImageView imgAnswerD;

    private ImageView btnExitGame;
    private ImageView btnChangeQuestion;
    private ImageView btn5050;
    private ImageView btnAskAudience;
    private ImageView btnCall;

    private ArrayList<Question> questions;
    private DatabaseManager databaseManager;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        initializeComponents();
        dialogLevelMoney.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialogLevelMoney.dismiss();
            }
        }, 3000);
        loadDatabase();


        Thread thread = new Thread(this);
        thread.start();
    }


    private Handler handler = new Handler() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case TIME_REMAIN_EACH_QUESTION:
                    txtTime.setText(msg.arg1 + "");
                    if (msg.arg1 == 0) {
                        Handler handlerShow = new Handler();
                        handlerShow.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialogEndTime.show();
                            }
                        }, 2000);

                        postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent();
                                intent.setClass(PlayActivity.this, FinishGameActivity.class);
                                intent.putExtra(KEY_NUMBER_QUESTION, numberQuestion + "");
                                intent.putExtra(KEY_MONEY, txtMoney.getText().toString());
                                startActivityForResult(intent, REQUEST_CODE);
                                finish();
                            }
                        }, 3000);
                    }
                    break;
                case WAIT_TRUE_ANSWER:
                    changeColourWait();
                    break;
                case CORRECT_ANSWER:
                    changeColourTrue();
                    if (msg.arg1 == MONEY_YES) {
                        txtMoney.setText(getMoneyLevel());
                    }
                    break;
                case BACK_TO_NORMAL:
                    changeColourNormal();
                    break;
                case CREATE_QUESTION:
                    dialogLevelMoney.setBackgroundLevelMoney(numberQuestion + 1);
                    dialogLevelMoney.show();
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialogLevelMoney.dismiss();
                        }
                    }, 4000);
                    loadDatabase();
                    break;
                case WRONG_ANSWER:
                    changeColourFalse();
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent();
                            intent.setClass(PlayActivity.this, FinishGameActivity.class);
                            intent.putExtra(KEY_NUMBER_QUESTION, numberQuestion + "");
                            intent.putExtra(KEY_MONEY, txtMoney.getText().toString());
                            startActivityForResult(intent, REQUEST_CODE);
                            finish();
                        }
                    }, 3000);
                    break;
                default:
                    break;
            }
        }
    };


    private void initializeComponents() {
        numberQuestion = 0;
        isRunning = true;
        txtQuestion = (TextView) findViewById(R.id.txt_question);
        txtAnswerA = (TextView) findViewById(R.id.txt_answer_a);
        txtAnswerB = (TextView) findViewById(R.id.txt_answer_b);
        txtAnswerC = (TextView) findViewById(R.id.txt_answer_c);
        txtAnswerD = (TextView) findViewById(R.id.txt_answer_d);
        txtA = (TextView) findViewById(R.id.txt_a);
        txtB = (TextView) findViewById(R.id.txt_b);
        txtC = (TextView) findViewById(R.id.txt_c);
        txtD = (TextView) findViewById(R.id.txt_d);
        txtTime = (TextView) findViewById(R.id.txt_time);
        txtMoney = (TextView) findViewById(R.id.txt_money);

        ProgressBar progress_time_down = (ProgressBar) findViewById(R.id.progress_time_down);

        imgAnswerA = (ImageView) findViewById(R.id.img_answer_a);
        imgAnswerB = (ImageView) findViewById(R.id.img_answer_b);
        imgAnswerC = (ImageView) findViewById(R.id.img_answer_c);
        imgAnswerD = (ImageView) findViewById(R.id.img_answer_d);
        txtNumberQuestion = (TextView) findViewById(R.id.txt_num_questions);

        dialogEndTime = new DialogEndTime(this);
        dialogLevelMoney = new DialogLevelMoney(this);
        dialogLevelMoney.getWindow().getAttributes().windowAnimations = R.style.DialogLevelMoney;
        btnExitGame = (ImageView) findViewById(R.id.btn_exit_game);
        btnChangeQuestion = (ImageView) findViewById(R.id.btn_change_question);
        btn5050 = (ImageView) findViewById(R.id.btn_5050);
        btnAskAudience = (ImageView) findViewById(R.id.btn_ask_audience);
        btnCall = (ImageView) findViewById(R.id.btn_call);

        btnExitGame.setOnClickListener(this);
        btnChangeQuestion.setOnClickListener(this);
        btn5050.setOnClickListener(this);
        btnAskAudience.setOnClickListener(this);
        btnCall.setOnClickListener(this);

        resetImageSetOnClick();
    }

    private void loadDatabase() {
        isRunning = true;
        if (userHelper5050) {
            visibleAnswer();
        }
        numberQuestion++;
        time = TIME_PLAY_EACH_QUESTION;
        databaseManager = new DatabaseManager(this);
        questions = databaseManager.get15Question();
        trueCase = questions.get(0).getTrueCase();
        txtQuestion.setText(questions.get(0).getQuestion());
        txtAnswerA.setText(questions.get(0).getCaseA());
        txtAnswerB.setText(questions.get(0).getCaseB());
        txtAnswerC.setText(questions.get(0).getCaseC());
        txtAnswerD.setText(questions.get(0).getCaseD());
        txtNumberQuestion.setText("Câu " + numberQuestion);
        resetImageSetOnClick();
    }

    private void changeQuestion() {
        isRunning = true;
        if (userHelper5050) {
            visibleAnswer();
        }
        time = TIME_PLAY_EACH_QUESTION;
        databaseManager = new DatabaseManager(this);
        questions = databaseManager.get15Question();
        trueCase = questions.get(0).getTrueCase();
        txtQuestion.setText(questions.get(0).getQuestion());
        txtAnswerA.setText(questions.get(0).getCaseA());
        txtAnswerB.setText(questions.get(0).getCaseB());
        txtAnswerC.setText(questions.get(0).getCaseC());
        txtAnswerD.setText(questions.get(0).getCaseD());
        txtNumberQuestion.setText("Câu " + numberQuestion);
        resetImageSetOnClick();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_answer_a:
                myAnswer = ANSWER_A;
                imgAnswerB.setOnClickListener(null);
                imgAnswerC.setOnClickListener(null);
                imgAnswerD.setOnClickListener(null);
                waitingAnswer();
                break;
            case R.id.img_answer_b:
                myAnswer = ANSWER_B;
                imgAnswerA.setOnClickListener(null);
                imgAnswerC.setOnClickListener(null);
                imgAnswerD.setOnClickListener(null);
                waitingAnswer();
                break;
            case R.id.img_answer_c:
                myAnswer = ANSWER_C;
                imgAnswerA.setOnClickListener(null);
                imgAnswerB.setOnClickListener(null);
                imgAnswerD.setOnClickListener(null);
                waitingAnswer();
                break;
            case R.id.img_answer_d:
                myAnswer = ANSWER_D;
                imgAnswerA.setOnClickListener(null);
                imgAnswerB.setOnClickListener(null);
                imgAnswerC.setOnClickListener(null);
                waitingAnswer();
                break;
            case R.id.btn_exit_game:
                dialogExitGame = new DialogExitGame(this);
                dialogExitGame.setOnReceiveDataListener(this);
                dialogExitGame.show();
                break;

            case R.id.btn_change_question:
                dialogChangeQuestion = new DialogChangeQuestion(this);
                btnChangeQuestion.setOnClickListener(null);
                dialogChangeQuestion.setOnReceiveNextDataListener(this);
                dialogChangeQuestion.show();
                break;

            case R.id.btn_5050:
                deleteTowAnswerFalse();
                break;

            case R.id.btn_ask_audience:
                selectAudience();
                break;

            case R.id.btn_call:
                showDialogCallHelper();
                break;
        }
    }
    private void selectAudience() {
       // btnAskAudience.setImageResource(R.drawable.atp__activity_player_button_image_help_audience_x);
        // btnAskAudience.setOnClickListener(null);
        Animation animPercent = AnimationUtils.loadAnimation(this, R.anim.anim_dialog_audience_percent_appear);
        dialog = new Dialog(this, R.style.AudienceDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_ask_audience);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        Button btnClose = (Button) dialog.findViewById(R.id.btn_audience_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        TextView txtCaseAPercent = (TextView) dialog.findViewById(R.id.txt_case_a_percent);
        TextView txtCaseBPercent = (TextView) dialog.findViewById(R.id.txt_case_b_percent);
        TextView txtCaseCPercent = (TextView) dialog.findViewById(R.id.txt_case_c_percent);
        TextView txtCaseDPercent = (TextView) dialog.findViewById(R.id.txt_case_d_percent);

        TextView txtCaseA = (TextView) dialog.findViewById(R.id.txt_audience_case_a);
        TextView txtCaseB = (TextView) dialog.findViewById(R.id.txt_audience_case_b);
        TextView txtCaseC = (TextView) dialog.findViewById(R.id.txt_audience_case_c);
        TextView txtCaseD = (TextView) dialog.findViewById(R.id.txt_audience_case_d);

        int audience[] = getAudiencePercent();

        TableRow.LayoutParams Params1 = new TableRow.LayoutParams(48,audience[0]*5);
        txtCaseA.setLayoutParams(Params1);
        txtCaseAPercent.setText(audience[0] + "%");
        TableRow.LayoutParams Params2 = new TableRow.LayoutParams(48,audience[1]*5);
        txtCaseB.setLayoutParams(Params2);
        txtCaseBPercent.setText(audience[1] + "%");
        TableRow.LayoutParams Params3 = new TableRow.LayoutParams(48,audience[2]*5);
        txtCaseC.setLayoutParams(Params3);
        txtCaseCPercent.setText(audience[2] + "%");
        TableRow.LayoutParams Params4 = new TableRow.LayoutParams(48,audience[3]*5);
        txtCaseD.setLayoutParams(Params4);
        txtCaseDPercent.setText(audience[3] + "%");

        txtCaseA.startAnimation(animPercent);
        txtCaseB.startAnimation(animPercent);
        txtCaseC.startAnimation(animPercent);
        txtCaseD.startAnimation(animPercent);
        dialog.show();


    }
    public int[] getAudiencePercent() {
        Random rd = new Random();
        int valueRandom[] = {5, 10, 5};
        int valueRandomBegin[] = {36, 21, 16};

        int result[] = {0, 0, 0, 0};
        int trueCase = questions.get(0).getTrueCase()-1;
        result[trueCase] = rd.nextInt(valueRandom[0]) + valueRandomBegin[0];
        for (int i = 1; i < 3; i++) {
            int temp = trueCase + i;
            if (temp > 3) {
                temp -= 4;
            }
            result[temp] = rd.nextInt(valueRandom[i]) + valueRandomBegin[i];
        }
        int sumCurrent = 0, index = 0;
        for (int i = 0; i < 4; i++) {
            if (result[i] > 0) {
                sumCurrent += result[i];
            } else {
                index = i;
            }
        }

        result[index] = 100 - sumCurrent;

        return result;
    }


    private void waitingAnswer() {
        isRunning = false;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                Message message = new Message();
                message.what = WAIT_TRUE_ANSWER;
                message.setTarget(handler);
                message.sendToTarget();
                while (count <= 3) {
                    count++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                checkAnswer();
            }
        });
        thread.start();
    }

    private void changeColourWait() {
        AnimationDrawable animationDrawable;
        switch (myAnswer) {
            case ANSWER_A:
                imgAnswerA.setImageResource(R.drawable.animation_list_select);
                animationDrawable = (AnimationDrawable) imgAnswerA.getDrawable();
                animationDrawable.start();
                break;
            case ANSWER_B:
                imgAnswerB.setImageResource(R.drawable.animation_list_select);
                animationDrawable = (AnimationDrawable) imgAnswerB.getDrawable();
                animationDrawable.start();
                break;
            case ANSWER_C:
                imgAnswerC.setImageResource(R.drawable.animation_list_select);
                animationDrawable = (AnimationDrawable) imgAnswerC.getDrawable();
                animationDrawable.start();
                break;
            case ANSWER_D:
                imgAnswerD.setImageResource(R.drawable.animation_list_select);
                animationDrawable = (AnimationDrawable) imgAnswerD.getDrawable();
                animationDrawable.start();
                break;
            default:
                break;
        }
    }

    private void changeColourTrue() {
        AnimationDrawable animationDrawable;
        switch (trueCase) {
            case ANSWER_A:
                imgAnswerA.setImageResource(R.drawable.animation_list_bg_true);
                animationDrawable = (AnimationDrawable) imgAnswerA.getDrawable();
                animationDrawable.start();
                break;
            case ANSWER_B:
                imgAnswerB.setImageResource(R.drawable.animation_list_bg_true);
                animationDrawable = (AnimationDrawable) imgAnswerB.getDrawable();
                animationDrawable.start();
                break;
            case ANSWER_C:
                imgAnswerC.setImageResource(R.drawable.animation_list_bg_true);
                animationDrawable = (AnimationDrawable) imgAnswerC.getDrawable();
                animationDrawable.start();
                break;
            case ANSWER_D:
                imgAnswerD.setImageResource(R.drawable.animation_list_bg_true);
                animationDrawable = (AnimationDrawable) imgAnswerD.getDrawable();
                animationDrawable.start();
                break;
            default:
                break;
        }
    }

    private void changeColourFalse() {
        switch (myAnswer) {
            case ANSWER_A:
                imgAnswerA.setImageResource(R.drawable.atp__activity_player_layout_play_answer_background_wrong);
                break;
            case ANSWER_B:
                imgAnswerB.setImageResource(R.drawable.atp__activity_player_layout_play_answer_background_wrong);
                break;
            case ANSWER_C:
                imgAnswerC.setImageResource(R.drawable.atp__activity_player_layout_play_answer_background_wrong);
                break;
            case ANSWER_D:
                imgAnswerD.setImageResource(R.drawable.atp__activity_player_layout_play_answer_background_wrong);
                break;
            default:
                break;
        }
    }

    private void changeColourNormal() {
        switch (myAnswer) {
            case ANSWER_A:
                imgAnswerA.setImageResource(R.drawable.atp__activity_player_layout_play_answer_background_normal);
                break;
            case ANSWER_B:
                imgAnswerB.setImageResource(R.drawable.atp__activity_player_layout_play_answer_background_normal);
                break;
            case ANSWER_C:
                imgAnswerC.setImageResource(R.drawable.atp__activity_player_layout_play_answer_background_normal);
                break;
            case ANSWER_D:
                imgAnswerD.setImageResource(R.drawable.atp__activity_player_layout_play_answer_background_normal);
                break;
            default:
                break;
        }
    }

    private void resetImageSetOnClick() {
        imgAnswerA.setOnClickListener(this);
        imgAnswerB.setOnClickListener(this);
        imgAnswerC.setOnClickListener(this);
        imgAnswerD.setOnClickListener(this);
    }

    private void deleteTowAnswerFalse() {
        userHelper5050 = true;
        Random rd = new Random();
        btn5050.setImageResource(R.drawable.atp__activity_player_button_image_help_5050_x);
        btn5050.setOnClickListener(null);
        trueCase = questions.get(0).getTrueCase();
        int falseAnswerOne;
        int falseAnswerTwo;

        falseAnswerOne = rd.nextInt(4) + 1;
        falseAnswerTwo = rd.nextInt(4) + 1;
        while ((falseAnswerOne == trueCase)) {
            falseAnswerOne = rd.nextInt(4) + 1;
        }
        while (falseAnswerTwo == trueCase || falseAnswerTwo == falseAnswerOne) {
            falseAnswerTwo = rd.nextInt(4) + 1;
        }
        switch (falseAnswerOne) {
            case ANSWER_A:
                imgAnswerA.setVisibility(View.INVISIBLE);
                txtAnswerA.setVisibility(View.INVISIBLE);
                txtA.setVisibility(View.INVISIBLE);
                break;
            case ANSWER_B:
                imgAnswerB.setVisibility(View.INVISIBLE);
                txtAnswerB.setVisibility(View.INVISIBLE);
                txtB.setVisibility(View.INVISIBLE);
                break;
            case ANSWER_C:
                imgAnswerC.setVisibility(View.INVISIBLE);
                txtAnswerC.setVisibility(View.INVISIBLE);
                txtC.setVisibility(View.INVISIBLE);
                break;
            case ANSWER_D:
                imgAnswerD.setVisibility(View.INVISIBLE);
                txtAnswerD.setVisibility(View.INVISIBLE);
                txtD.setVisibility(View.INVISIBLE);
                break;
            default:
                break;
        }

        switch (falseAnswerTwo) {
            case ANSWER_A:
                imgAnswerA.setVisibility(View.INVISIBLE);
                txtAnswerA.setVisibility(View.INVISIBLE);
                txtA.setVisibility(View.INVISIBLE);
                break;
            case ANSWER_B:
                imgAnswerB.setVisibility(View.INVISIBLE);
                txtAnswerB.setVisibility(View.INVISIBLE);
                txtB.setVisibility(View.INVISIBLE);
                break;
            case ANSWER_C:
                imgAnswerC.setVisibility(View.INVISIBLE);
                txtAnswerC.setVisibility(View.INVISIBLE);
                txtC.setVisibility(View.INVISIBLE);
                break;
            case ANSWER_D:
                imgAnswerD.setVisibility(View.INVISIBLE);
                txtAnswerD.setVisibility(View.INVISIBLE);
                txtD.setVisibility(View.INVISIBLE);
                break;
            default:
                break;
        }
    }

    private void visibleAnswer() {
        imgAnswerA.setVisibility(View.VISIBLE);
        txtA.setVisibility(View.VISIBLE);
        txtAnswerA.setVisibility(View.VISIBLE);

        imgAnswerB.setVisibility(View.VISIBLE);
        txtB.setVisibility(View.VISIBLE);
        txtAnswerB.setVisibility(View.VISIBLE);

        imgAnswerC.setVisibility(View.VISIBLE);
        txtC.setVisibility(View.VISIBLE);
        txtAnswerC.setVisibility(View.VISIBLE);

        imgAnswerD.setVisibility(View.VISIBLE);
        txtD.setVisibility(View.VISIBLE);
        txtAnswerD.setVisibility(View.VISIBLE);
    }


    public void checkAnswer() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (myAnswer == trueCase) {
                    int count = 0;
                    test = MONEY_YES;
                    Message message = new Message();
                    message.what = CORRECT_ANSWER;
                    message.arg1 = test;
                   //message.setTarget(handler);
                   //message.sendToTarget();
                    handler.sendMessage(message);
                    while (count <= 3) {
                        count++;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (level < 14) {
                        Message message2 = new Message();
                        message2.what = BACK_TO_NORMAL;
                        //message2.setTarget(handler);
                        //message2.sendToTarget();
                        handler.sendMessage(message2);
                        level++;

                        Message message1 = new Message();
                        message1.what = CREATE_QUESTION;
                        //message1.setTarget(handler);
                        //message1.sendToTarget();
                        handler.sendMessage(message1);
                        time = TIME_PLAY_EACH_QUESTION;
                        isRunning = true;
                        while (isRunning && time > -1) {
                            time--;
                            Message message3 = new Message();
                            message3.what = TIME_REMAIN_EACH_QUESTION;
                            message3.arg1 = time;
                            //message3.setTarget(handler);
                            //message3.sendToTarget();
                            handler.sendMessage(message3);

                            if (time == 0) {
                                isRunning = false;
                            }
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else {
                    test = MONEY_NO;

                    Message message = new Message();
                    message.what = WRONG_ANSWER;
                    //message.setTarget(handler);
                    //message.sendToTarget();
                    handler.sendMessage(message);

                    Message message1 = new Message();
                    message1.what = CORRECT_ANSWER;
                    message1.arg1 = test;
                    ///message1.setTarget(handler);
                  //  message1.sendToTarget();
                    handler.sendMessage(message1);
                }
            }
        });
       thread.start();

    }

    private String getMoneyLevel() {
        String[] arrMoney = new String[]{
                "200,000", "400,000", "600,000", "1,000,000", "2,000,000", "3,000,000",
                "6,000,000", "10,000,000", "14,000,000", "22,000,000", "30,000,000",
                "40,000,000", "60,000,000", "85,000,000", "150,000,000"
        };
        return arrMoney[numberQuestion - 1];
    }


    private void showDialogCallHelper() {
        dialogCall = new DialogCall(this);
        btnCall.setImageResource(R.drawable.atp__activity_player_button_image_help_call_x);
        btnCall.setOnClickListener(null);
        String answer;
        if (trueCase == 1) {
            answer = "A";
        } else if (trueCase == 2) {
            answer = "B";
        } else if (trueCase == 3) {
            answer = "C";
        } else {
            answer = "D";
        }

        dialogCall.setAnswer(answer);
        dialogCall.setOwnerActivity(this);
        dialogCall.show();
    }

    @Override
    public void OnDataReceiveData(String data) {
        switch (data) {
            case DialogExitGame.TXT_OK:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void OnChangeDataReceiveData(String data) {
        switch (data) {
            case DialogChangeQuestion.TXT_OK:
                time = TIME_PLAY_EACH_QUESTION - 3;
                changeQuestion();
                btnChangeQuestion.setImageResource(R.drawable.atp__activity_player_button_image_help_change_question_x);
                break;
        }
    }

    @Override
    public void run() {
        while (isRunning && time > -1) {
            time--;
            Message message = new Message();
            message.what = TIME_REMAIN_EACH_QUESTION;
            message.arg1 = time;
           /* message.setTarget(handler);
            message.sendToTarget();*/
            handler.sendMessage(message);
            if (time == 0) {
                isRunning = false;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
