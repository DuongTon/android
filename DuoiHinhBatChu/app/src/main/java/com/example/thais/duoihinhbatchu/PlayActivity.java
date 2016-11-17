package com.example.thais.duoihinhbatchu;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class PlayActivity extends Activity implements View.OnClickListener {

    private static final int SIZE_BUTTON = 16;
    private static final int SIZE_IMAGE = 30;

    private static final int MAX_ASCII = 91;
    private static final int MIN_ASCII = 65;


    private TextView txtHeart;
    private TextView txtCoin;
    private TextView txtDisplay;
    private TextView txtLevel;

    private ImageView imgPicture;

    private Button btnNext;

    private Button[] btnQuestion = new Button[SIZE_BUTTON];
    private Button[] btnAnswer = new Button[SIZE_BUTTON];

    private Question question;

    private ArrayList<Question> arrQuestion;
    private ArrayList<String> arrNameImage;

    private int indexAnswer;
    private int sizeNamePicture;
    private int coin;
    private int heart;
    private int level;
    private int[] historyButton;

    private boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        heart = 5;
        coin = 0;

        initializeComponents();
        initializeListNameImages();
        initializeListQuestion();
        createQuestion();
    }

    private int getIdOfImageByName(String name) {
        return getResources().getIdentifier(name, "drawable", getPackageName());
    }

    private void initializeComponents() {
        txtHeart = (TextView) findViewById(R.id.txt_heart);
        txtHeart.setText(heart + "");
        txtCoin = (TextView) findViewById(R.id.txt_coin);
        txtCoin.setText(coin + "");
        txtDisplay = (TextView) findViewById(R.id.txt_display);
        txtDisplay.setVisibility(View.INVISIBLE);
        txtLevel = (TextView) findViewById(R.id.txt_level);
        txtLevel.setText(level + "");

        imgPicture = (ImageView) findViewById(R.id.img_picture);

        btnNext = (Button) findViewById(R.id.btn_next);
        btnNext.setOnClickListener(this);

        for (int i = 0; i < SIZE_BUTTON; i++) {
            btnQuestion[i] = (Button) findViewById(R.id.btn_question_01 + i);
            btnQuestion[i].setOnClickListener(this);
            btnAnswer[i] = (Button) findViewById(R.id.btn_answer_01 + i);
            btnAnswer[i].setOnClickListener(this);
        }
    }

    private void initializeListNameImages() {
        arrNameImage = new ArrayList<>();
        arrNameImage.add(Models.AO_MUA);
        arrNameImage.add(Models.BAO_CAO);
        arrNameImage.add(Models.CAN_THIEP);
        arrNameImage.add(Models.CAT_TUONG);
        arrNameImage.add(Models.CHIEU_TRE);
        arrNameImage.add(Models.DANH_LUA);
        arrNameImage.add(Models.DAN_ONG);
        arrNameImage.add(Models.GIAN_DIEP);
        arrNameImage.add(Models.GIANG_MAI);
        arrNameImage.add(Models.HOI_DONG);
        arrNameImage.add(Models.HONG_TAM);
        arrNameImage.add(Models.KHOAI_LANG);
        arrNameImage.add(Models.KIEM_CHUYEN);
        arrNameImage.add(Models.LAN_CAN);
        arrNameImage.add(Models.MA_SAT);
        arrNameImage.add(Models.NAM_BAN_CAU);
        arrNameImage.add(Models.OTO);
        arrNameImage.add(Models.QUY_HANG);
        arrNameImage.add(Models.SONG_SONG);
        arrNameImage.add(Models.THAT_TINH);
        arrNameImage.add(Models.THO_THE);
        arrNameImage.add(Models.TICH_PHAN);
        arrNameImage.add(Models.TO_HOAI);
        arrNameImage.add(Models.TO_TIEN);
        arrNameImage.add(Models.TRANH_THU);
        arrNameImage.add(Models.VUA_PHA_LUOI);
        arrNameImage.add(Models.VUON_BACH_THU);
        arrNameImage.add(Models.XA_KEP);
        arrNameImage.add(Models.XA_PHONG);
        arrNameImage.add(Models.XA_DAP_DIEN);

    }

    private void initializeListQuestion() {
        arrQuestion = new ArrayList<>();
        sizeNamePicture = arrNameImage.size();
        for (int i = 0; i < sizeNamePicture; i++) {
            String name = arrNameImage.get(i);
            int id = getIdOfImageByName(name);
            question = new Question(id, name);
            arrQuestion.add(question);
        }
    }

    private void createQuestion() {
        isRunning = false;
        indexAnswer = 0;
        for (int i = 0; i < SIZE_BUTTON; i++) {
            btnAnswer[i].setVisibility(View.VISIBLE);
            btnQuestion[i].setVisibility(View.VISIBLE);
            btnAnswer[i].setBackgroundResource(R.drawable.tile_empty);

            btnQuestion[i].setText("");
            btnAnswer[i].setText("");
        }

        Random rd = new Random();
        int index = rd.nextInt(SIZE_IMAGE);
        question = arrQuestion.get(index);
        sizeNamePicture = question.getName().length();
        historyButton= new int[sizeNamePicture];
        imgPicture.setImageResource(question.getId());

        for (int i = sizeNamePicture; i < SIZE_BUTTON; i++) {
            btnAnswer[i].setVisibility(View.INVISIBLE);
        }

        String str = question.getName().toUpperCase();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int in = (int) c % 16;
            int indexBtnQuestion = checkButtonEmpty(in % SIZE_BUTTON);
            String text = c + "";
            btnQuestion[indexBtnQuestion].setText(text);
            btnQuestion[indexBtnQuestion].setTextColor(0xff0288d1);
        }

        for (int i = 0; i < SIZE_BUTTON; i++) {
            if (btnQuestion[i].getText().toString().equals("")) {
                int in = new Random().nextInt(MAX_ASCII - MIN_ASCII) + MIN_ASCII;
                String text = (char) in + "";
                btnQuestion[i].setText(text);
                btnQuestion[i].setTextColor(0xff0288d1);
            }
        }
    }

    private int checkButtonEmpty(int i) {
        if (btnQuestion[i].getText().toString().equals("")) {
            return i;
        } else if (i == 16) {
            return checkButtonEmpty(0);
        } else {
            return checkButtonEmpty(i + 1);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_next){
            createQuestion();
            level = Integer.parseInt(txtLevel.getText().toString()) +1;
            txtLevel.setText(level + "");
            btnNext.setVisibility(View.INVISIBLE);
            txtDisplay.setVisibility(View.INVISIBLE);
            return;
        }
        int h =0;
        for(int i = 0; i< SIZE_BUTTON; i++){
            if(v.getId() == btnQuestion[i].getId() && !isRunning){
                for(int j = 0; j< sizeNamePicture; j++){
                    if(btnAnswer[j].getText().toString().equals("")){
                        h = j;
                        break;
                    }
                }

                btnAnswer[h].setText(btnQuestion[i].getText().toString());
                btnAnswer[h].setTextColor(0xffffffff);
                btnAnswer[h].setBackgroundResource(R.drawable.ic_tile_hover);
                btnQuestion[i].setVisibility(View.INVISIBLE);
                historyButton[h] = i;
                indexAnswer ++;
                break;
            }
            if( v.getId() == btnAnswer[i].getId() && btnAnswer[i].getText().length() !=0){
                btnQuestion[historyButton[i]].setVisibility(View.VISIBLE);
                btnAnswer[i].setBackgroundResource(R.drawable.tile_empty);
                txtDisplay.setText("");
                btnAnswer[i].setText("");
                isRunning = false;
                indexAnswer--;
                break;
            }
        }
        checkAnswer();
    }

    private void checkAnswer() {
        if (indexAnswer == sizeNamePicture) {
            isRunning = true;
            boolean isStart = true;
            for(int i =0; i<sizeNamePicture;i++){
                if(!btnAnswer[i].getText().toString().equals(question.getName().toUpperCase().charAt(i) + "")){
                    isStart = false;
                    break;
                }
            }
            if (isStart) {
                txtDisplay.setVisibility(View.VISIBLE);
                txtDisplay.setTextColor(0xffffffff);
                txtDisplay.setText("Bạn đã tìm ra đáp án!");
                for (int i = 0; i < sizeNamePicture; i++) {
                    btnAnswer[i].setBackgroundResource(R.drawable.ic_tile_true);
                }
                btnNext.setVisibility(View.VISIBLE);
                coin = Integer.parseInt(txtCoin.getText().toString()) + 100;
                txtCoin.setText(coin + "");
            } else {
                for (int i = 0; i < sizeNamePicture; i++) {
                    btnAnswer[i].setBackgroundResource(R.drawable.ic_tile_false);
                }
                txtDisplay.setVisibility(View.VISIBLE);
                txtDisplay.setTextColor(0xffffffff);
                txtDisplay.setText("Bạn chọn sai đáp án rồi!");
                heart = Integer.parseInt(txtHeart.getText().toString()) - 1;
                txtHeart.setText(heart + "");
            }
        }
    }
}
