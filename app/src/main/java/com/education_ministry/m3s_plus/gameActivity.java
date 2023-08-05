package com.education_ministry.m3s_plus;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.education_ministry.m3s_plus.model.Values;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.IntStream;

public class gameActivity extends AppCompatActivity {
    public static HashMap<Integer, Values> dataStore = new HashMap<>();
    ArrayList<String> prevTextList = new ArrayList<>();
    ArrayList<Integer> prevStepsList = new ArrayList<>();
    ArrayList<Integer> lastSeenStepList = new ArrayList<>();
    ArrayList<Values> undoList = new ArrayList<>();
    int step = 0;
    int fL3LastSeenStep = 0;
    String value1, value2;
    boolean firstClick = false;
    boolean yesRestartGame=false;
    boolean checkValueCondition=true;

    boolean stopwatch=false;
    public static ArrayList<String> recordMap=new ArrayList<String>();

    boolean neverShowAgain=false;
    int currentEntry=0;


    Button btn1;
    Button btn2;
    TextView blankBtn;
    TextView showLabel;
    TextView showLabelAnimationOverlay;
    TextView valueLabelOverlay;
    TextView valueLabel;
    TextView jLabel1;
    TextView txt5;
    TextView txtVal;
    TextView txtCount;
    Chronometer chronometer1;


    public File file;
    String folderName;
    String fileName;
    String path;
    int value3;
    int clickedCount=0;
    int flr2_initial_step=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

         chronometer1 = findViewById(R.id.project_time);

        final MediaPlayer soundbtn1 = MediaPlayer.create(gameActivity.this, R.raw.click);
        final MediaPlayer soundbtn2 = MediaPlayer.create(gameActivity.this, R.raw.click);
        final MediaPlayer soundSiren = MediaPlayer.create(gameActivity.this, R.raw.siren);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        value1 =MainActivity.input1;
        value2 = MainActivity.input2;

        value3=MainActivity.newValue;


        dataStore.put(0, new Values(value1, 0));
        dataStore.put(2, new Values(value2, value3));
        dataStore.put(4, new Values(value1, value3));
        dataStore.put(6, new Values("NEXT", 0));
        dataStore.put(7, new Values(value2, 0));
        dataStore.put(9, new Values("NEXT", 0));
        dataStore.put(10, new Values(value2, value3));
        dataStore.put(12, new Values(value2, 0));
        dataStore.put(121, new Values("NEXT", 0));
        dataStore.put(14, new Values("NEXT", 0));
        dataStore.put(16, new Values(value1, 0));
        dataStore.put(161, new Values("NEXT", 0));
        dataStore.put(17, new Values(value2, 0));
        dataStore.put(18, new Values(value1, value3));
        dataStore.put(19, new Values("NEXT", 0));
        dataStore.put(21, new Values(value1, 0));
        dataStore.put(25, new Values(value1, 0));
        dataStore.put(34, new Values("NEXT", 0));
        dataStore.put(37, new Values("NEXT", 0));
        dataStore.put(39, new Values(value2, 0));
        dataStore.put(43, new Values(value1, 0));
        dataStore.put(49, new Values(value1, 0));
        dataStore.put(52, new Values(value2, 0));
        dataStore.put(55, new Values("NEXT", 0));
        dataStore.put(58, new Values("NEXT", 0));
        dataStore.put(61, new Values("NEXT", 0));
        dataStore.put(64, new Values("NEXT", 0));
        dataStore.put(67, new Values(value2, 0));
        dataStore.put(70, new Values("NEXT", 0));
        dataStore.put(71, new Values("NEXT", 0));
        dataStore.put(80, new Values("NEXT", 0));
        dataStore.put(83, new Values(value1, 0));
        dataStore.put(89, new Values(value1, 0));
        dataStore.put(92, new Values("NEXT", 0));


        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        blankBtn = findViewById(R.id.blankBtn);
        showLabel = findViewById(R.id.showLabel);
        valueLabel = findViewById(R.id.valueLabel);
        jLabel1 = findViewById(R.id.jLabel1);
        txt5 = findViewById(R.id.textView5);
        txtVal = findViewById(R.id.textViewVal);
        txtCount = findViewById(R.id.textViewCount);
        showLabelAnimationOverlay = findViewById(R.id.showLabelAnimationOverlay);
        valueLabelOverlay = findViewById(R.id.valueLabelOverlay);

        btn2.setText(value2);
        btn1.setText(value1);

        if(value1.contains("#")){
            btn1.setTextColor(Color.argb(0,251,45,94));
            btn2.setTextColor(Color.argb(0,251,45,94));

            if(value1.equals("#FB2D5E")){ //red
                btn1.setBackgroundResource(R.drawable.square_radius_fill_red_left_bottom);
            }else if(value1.equals("#4CCC51")){ //green
                btn1.setBackgroundResource(R.drawable.sqaure_radius_fill_green_left_bottom);
            }else if(value1.equals("#2A7BF4")){ //blue
                btn1.setBackgroundResource(R.drawable.square_radius_fill_blue_left_bottom);
            }

            if(value2.equals("#FB2D5E")){ //red
                btn2.setBackgroundResource(R.drawable.square_radius_fill_red_right_bottom);
            }else if(value2.equals("#4CCC51")){ //green
                btn2.setBackgroundResource(R.drawable.square_radius_fill_green_right_bottom);
            }else if(value2.equals("#2A7BF4")){ //blue
                btn2.setBackgroundResource(R.drawable.square_radius_fill_blue_right_bottom);
            }
        }


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String btnText1 = btn1.getText().toString();
                String valueText = valueLabel.getText().toString();
                txt5.setText(btnText1);

                if(valueText.equals("VALUE") && checkValueCondition == true){
                    txtVal.setText("0");
                    checkValueCondition=false;
                }else if(recordMap.size() != 0){
                    txtVal.setText(valueLabel.getText().toString());
                }
                vibe.vibrate(300);
                ++currentEntry;
                soundbtn1.start();


                checkIfEntryLimitReached(currentEntry);

                if(btnText1.contains("#")){
                    if(btnText1.equals("#FB2D5E")){ //RED
                        btnText1 = "R";
                        txt5.setText(btnText1);
                    }else if(btnText1.equals("#4CCC51")){ // GREEN
                        btnText1 ="G";
                        txt5.setText(btnText1);
                    }else if(btnText1.equals("#2A7BF4")){ // BLUE
                        btnText1 ="B";
                        txt5.setText(btnText1);
                    }
                }

                recordMap.add(btnText1);
                txtCount.setText(String.valueOf(currentEntry));
                checkInput(btn1.getText().toString());
            }
        });

        btn1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                String btnText1Touch = btn1.getText().toString();
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btn1.setBackgroundResource(R.drawable.darken_background_left_bottom);

                    Animation overlayStartShow= AnimationUtils.loadAnimation(gameActivity.this,R.anim.fade);
                    showLabelAnimationOverlay.startAnimation(overlayStartShow);
                    showLabelAnimationOverlay.setVisibility(View.VISIBLE);


                    Animation overlayStartValue= AnimationUtils.loadAnimation(gameActivity.this,R.anim.fade);
                    valueLabelOverlay.startAnimation(overlayStartValue);
                    valueLabelOverlay.setVisibility(View.VISIBLE);
                    if(btnText1Touch.contains("#")){
                        if(btnText1Touch.equals("#FB2D5E")){ //RED
                            btn1.setBackgroundResource(R.drawable.square_radius_fill_red_left_bottom_darken);
                        }else if(btnText1Touch.equals("#4CCC51")){ // GREEN
                            btn1.setBackgroundResource(R.drawable.square_radius_fill_green_left_bottom_darken);
                        }else if(btnText1Touch.equals("#2A7BF4")){ // BLUE
                            btn1.setBackgroundResource(R.drawable.square_radius_fill_blue_left_bottom_darken);
                        }
                    }


                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    Thread t=new Thread(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {

                                    btn1.setBackgroundResource(R.drawable.home_xy_border_radius_only_left_bottom);
                                    if(btnText1Touch.contains("#")){
                                        if(btnText1Touch.equals("#FB2D5E")){ //RED
                                            btn1.setBackgroundResource(R.drawable.square_radius_fill_red_left_bottom);
                                        }else if(btnText1Touch.equals("#4CCC51")){ // GREEN
                                            btn1.setBackgroundResource(R.drawable.sqaure_radius_fill_green_left_bottom);
                                        }else if(btnText1Touch.equals("#2A7BF4")){ // BLUE
                                            btn1.setBackgroundResource(R.drawable.square_radius_fill_blue_left_bottom);
                                        }
                                    }
                                    try {
                                        Thread.sleep(150);
                                        showLabelAnimationOverlay.setVisibility(View.INVISIBLE);
                                        valueLabelOverlay.setVisibility(View.INVISIBLE);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }
                    });
                    t.start();
                }

                txt5.setPadding(0,0,0,0);
                txtVal.setPadding(0,0,0,0);
                return false;
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String btnText2 = btn2.getText().toString();
                String valueText = valueLabel.getText().toString();
                txt5.setText(btnText2);

                if(valueText.equals("VALUE") && checkValueCondition == true){
                    txtVal.setText("0");
                    checkValueCondition=false;
                }else if(recordMap.size() != 0){
                    txtVal.setText(valueLabel.getText().toString());
                }

                vibe.vibrate(300);
                ++currentEntry;
                soundbtn2.start();


                checkIfEntryLimitReached(currentEntry);
                if (btnText2.contains("#")) {
                    if (btnText2.equals("#FB2D5E")) { //RED
                        btnText2 = "R";
                        txt5.setText(btnText2);
                    } else if (btnText2.equals("#4CCC51")) { // GREEN
                        btnText2 = "G";
                        txt5.setText(btnText2);
                    } else if (btnText2.equals("#2A7BF4")) { // BLUE
                        btnText2 = "B";
                        txt5.setText(btnText2);
                    }
                }

                recordMap.add(btnText2);
                txtCount.setText(String.valueOf(currentEntry));
                checkInput(btn2.getText().toString());
            }
        });


        btn2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                String btnText2Touch = btn2.getText().toString();
                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                                    btn2.setBackgroundResource(R.drawable.darken_background_right_bottom);

                    Animation overlayStartShow= AnimationUtils.loadAnimation(gameActivity.this,R.anim.fade);
                    showLabelAnimationOverlay.startAnimation(overlayStartShow);
                    showLabelAnimationOverlay.setVisibility(View.VISIBLE);


                    Animation overlayStartValue= AnimationUtils.loadAnimation(gameActivity.this,R.anim.fade);
                    valueLabelOverlay.startAnimation(overlayStartValue);
                    valueLabelOverlay.setVisibility(View.VISIBLE);

                    if (btnText2Touch.contains("#")) {
                        if (btnText2Touch.equals("#FB2D5E")) { //RED
                            btn2.setBackgroundResource(R.drawable.sqaure_radius_fill_red_right_bottom_darken);
                        } else if (btnText2Touch.equals("#4CCC51")) { // GREEN
                            btn2.setBackgroundResource(R.drawable.sqaure_radius_fill_green_right_bottom_darken);
                        } else if (btnText2Touch.equals("#2A7BF4")) { // BLUE
                            btn2.setBackgroundResource(R.drawable.sqaure_radius_fill_blue_right_bottom_darken);
                        }
                    }

                } else if (event.getAction() == MotionEvent.ACTION_UP) {

                    Thread t=new Thread(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {

                                    btn2.setBackgroundResource(R.drawable.home_xy_border_radius_only_right_bottom);
                                    if(btnText2Touch.contains("#")){
                                        if(btnText2Touch.equals("#FB2D5E")){ //RED
                                            btn2.setBackgroundResource(R.drawable.square_radius_fill_red_right_bottom);
                                        }else if(btnText2Touch.equals("#4CCC51")){ // GREEN
                                            btn2.setBackgroundResource(R.drawable.square_radius_fill_green_right_bottom);
                                        }else if(btnText2Touch.equals("#2A7BF4")){ // BLUE
                                            btn2.setBackgroundResource(R.drawable.square_radius_fill_blue_right_bottom);
                                        }
                                    }
                                    try {
                                        Thread.sleep(150);
                                        showLabelAnimationOverlay.setVisibility(View.INVISIBLE);
                                        valueLabelOverlay.setVisibility(View.INVISIBLE);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }
                    });
                    t.start();
//                    btn2.setBackgroundResource(R.drawable.home_xy_border_radius_only_right_bottom);
//                    if(btnText2Touch.contains("#")){
//                        if(btnText2Touch.equals("#FB2D5E")){ //RED
//                            btn2.setBackgroundResource(R.drawable.square_radius_fill_red_right_bottom);
//                        }else if(btnText2Touch.equals("#4CCC51")){ // GREEN
//                            btn2.setBackgroundResource(R.drawable.square_radius_fill_green_right_bottom);
//                        }else if(btnText2Touch.equals("#2A7BF4")){ // BLUE
//                            btn2.setBackgroundResource(R.drawable.square_radius_fill_blue_right_bottom);
//                        }
//                    }
                }

                txt5.setPadding(0,0,0,0);
                txtVal.setPadding(0,0,0,0);
                return false;
            }
        });


        blankBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vibe.vibrate(300);
                soundbtn2.start();
//                ++currentEntry;

//                recordMap.add("BLANK");
                txtCount.setText(String.valueOf(currentEntry));
                txt5.setText("BLANK");
                txt5.setPadding(60,0,0,0);
                txtVal.setPadding(160,0,0,0);
            }
        });


        blankBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Thread t=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(150);
                                    blankBtn.setBackgroundResource(R.drawable.blank_yellow_btn);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }
                });
                t.start();
                blankBtn.setBackgroundResource(R.drawable.blank_btn_yellow_darken);
                return false;
            }
        });


        findViewById(R.id.undo_btn).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {

                vibe.vibrate(300);
                soundbtn2.start();
                int[] fl3Steps = {7,16,39,43,49,52,67,83,89};
                int[] fl3Steps2 = {6,14,55,58,61,71};


                int currStep = step;

                if (recordMap.size() != 0) {


                    Animation overlayStartShow= AnimationUtils.loadAnimation(gameActivity.this,R.anim.fade);
                    showLabelAnimationOverlay.startAnimation(overlayStartShow);
                    showLabelAnimationOverlay.setVisibility(View.VISIBLE);


                    Animation overlayStartValue= AnimationUtils.loadAnimation(gameActivity.this,R.anim.fade);
                    valueLabelOverlay.startAnimation(overlayStartValue);
                    valueLabelOverlay.setVisibility(View.VISIBLE);


                    Thread t=new Thread(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(150);
                                        showLabelAnimationOverlay.setVisibility(View.INVISIBLE);
                                        valueLabelOverlay.setVisibility(View.INVISIBLE);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    });
                    t.start();

                    recordMap.remove(recordMap.size() - 1);
                    step = prevStepsList.get(prevStepsList.size() - 1);
                    prevStepsList.remove(prevStepsList.size() - 1);

                    undoList.remove(undoList.size() - 1);
                    if(undoList.size() != 0){
                        Values undoObject = undoList.get(undoList.size() - 1);
                        showLabel.setText(undoObject.getPrediction());
                        valueLabel.setText(String.valueOf(undoObject.getValue()));
                        txtVal.setText(String.valueOf(undoObject.getValue()));
                    }

                    if(Arrays.binarySearch(fl3Steps,currStep)>0){
                        for (int i = undoList.size()-1; i >= 0 ; i--) {
                            if(undoList.get(i).getStep() == currStep){
                                dataStore.get(currStep).setValue(undoList.get(i).getValue());
                                break;
                            }
                        }
                    }



                    if(Arrays.binarySearch(fl3Steps,step)>0){
                            if(Arrays.binarySearch(fl3Steps2,currStep)>0){
                                lastSeenStepList.remove(lastSeenStepList.size() - 1);
                            if(lastSeenStepList.size() != 0){
                                fL3LastSeenStep = lastSeenStepList.get(lastSeenStepList.size() - 1);
                            }else{
                                fL3LastSeenStep = 0;
                            }
                        }
                    }
                }

                --currentEntry;
                if(currentEntry <= 0){
                    valueLabel.setText("VAL");
                    txt5.setText("no");
                    txtCount.setText("0");
                    txtVal.setText("0");
                    currentEntry=0;
                    showLabel.setTextColor(Color.rgb(66,88,90));
                    showLabel.setBackgroundResource(R.drawable.btn_border_only_left_top);
                    showLabel.setText("SHOW");
                }else {
                    prevTextList.remove(prevTextList.size() - 1);
                    String prevText = prevTextList.get(prevTextList.size() - 1);
                    Values undoValue = undoList.get(undoList.size() - 1);
                    String undoText = undoValue.getPrediction();
                    checkIfEntryLimitReached(currentEntry);
                    txt5.setText(prevText);
                    txtCount.setText(String.valueOf(currentEntry));
                        if (prevText.equals("#FB2D5E")) {
                            txt5.setText("R");
                            showLabel.setText("RED");
                            showLabel.setTextColor(Color.argb(0, 251, 45, 94));
                            showLabel.setBackgroundResource(R.drawable.top_radius_red_color);
                        } else if (prevText.equals("#4CCC51")) {
                            txt5.setText("G");
                            showLabel.setText("GREEN");
                            showLabel.setTextColor(Color.argb(0, 76, 204, 81));
                            showLabel.setBackgroundResource(R.drawable.top_radius_green_color);
                        } else if (prevText.equals("#2A7BF4")) {
                            txt5.setText("B");
                            showLabel.setText("BLUE");
                            showLabel.setTextColor(Color.argb(0, 42, 123, 244));
                            showLabel.setBackgroundResource(R.drawable.top_radius_blue_color);
                        }

                        if(undoText.equals("NEXT")){
                            showLabel.setTextColor(Color.rgb(66,88,90));
                            showLabel.setBackgroundResource(R.drawable.btn_border_only_left_top);
                            showLabel.setText("NEXT");
                        }
                }

                txt5.setPadding(0,0,0,0);
                txtVal.setPadding(0,0,0,0);
                checkForBlinkerStopWhenUndo(currentEntry);
            }
        });


        findViewById(R.id.saveFileByNameBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText fileNameField = findViewById(R.id.textView9Game2);
                String fileNameCastedString = fileNameField.getText().toString();

                if(recordMap.isEmpty()){
                    Animation animation2= AnimationUtils.loadAnimation(gameActivity.this,R.anim.move_animation_down);
                    findViewById(R.id.messagePopupboxGame2).startAnimation(animation2);
                    findViewById(R.id.messagePopupboxGame2).setVisibility(View.GONE);
                    Toast.makeText(gameActivity.this,"NO RECORDS ARE ENTERED",Toast.LENGTH_LONG).show();
                }else {
                    Workbook wb = new HSSFWorkbook();
                    Cell cell = null;
                    Sheet sheet = null;
                    sheet = wb.createSheet("Report");

                    int rowNumber = 0;
                    int columnNumber = 0;
                    Row row = sheet.createRow(0);

                    for (int i = 0; i < recordMap.size(); i++) {

                        if (i != 0 && recordMap.get(i) == recordMap.get(i - 1)) {
                            rowNumber++;
                            Row newRow;
                            if (sheet.getRow(rowNumber) == null) {
                                newRow = sheet.createRow(rowNumber);
                            } else {
                                newRow = sheet.getRow(rowNumber);
                            }
                            cell = newRow.createCell(columnNumber - 1);
                            cell.setCellValue(recordMap.get(i));


                        } else {
                            rowNumber = 0;
                            if (columnNumber == i) {
                                cell = row.createCell(i);
                                cell.setCellValue(recordMap.get(i));

                            } else {
                                cell = row.createCell(columnNumber);
                                cell.setCellValue(recordMap.get(i));

                                //                if(recordMap.get(i).equals("RED") || recordMap.get(i).equals("GREEN") || recordMap.get(i).equals("BLUE")){
                                //                    setCellColor(style,cell,recordMap.get(i).toString());
                                //                }
                            }
                            columnNumber++;
                        }

                        sheet.setColumnWidth(i, (20 * 100));

                    }

                    String folderName = "Reports";
//                    String fileName = folderName + System.currentTimeMillis() + ".xls";
                    String fileName = fileNameCastedString+".xls";
                    String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + File.separator + folderName + File.separator + fileName;

                    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + File.separator + folderName);
                    if (!file.exists()) {
                        file.mkdirs();
                    }

                    FileOutputStream outputStream = null;

                    try {
                        outputStream = new FileOutputStream(path);
                        wb.write(outputStream);
                        Toast.makeText(getApplicationContext(), "REPORT GENERATED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                        recordMap.clear();
                        clickedCount=0;
                        txtCount.setText(String.valueOf(clickedCount));
//                        if(yesRestartGame){
                            yesRestartGame=false;
//                            Intent restartGame = new Intent(gameActivity.this, gameActivity.class);
//                            startActivity(restartGame);
//                            finish();
//                        }else{
                        finish();
//                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "REPORT GENERATION FAILED", Toast.LENGTH_LONG).show();
                        recordMap.clear();
                        clickedCount=0;
                        txtCount.setText(String.valueOf(clickedCount));
                        finish();
                        try {
                            outputStream.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });


        findViewById(R.id.saveFileByNameBtn).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    findViewById(R.id.saveFileByNameBtn).setBackgroundResource(R.drawable.darken_background_right_bottom);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    findViewById(R.id.saveFileByNameBtn).setBackgroundResource(R.drawable.popup_name_file);

                }
                return false;
            }
        });


        findViewById(R.id.yesRestartbtn).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    findViewById(R.id.yesRestartbtn).setBackgroundResource(R.drawable.darken_background_right_bottom);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    findViewById(R.id.yesRestartbtn).setBackgroundResource(R.drawable.popup_name_file);

                }
                return false;
            }
        });

        findViewById(R.id.closeFileNoSaveBtn).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                            findViewById(R.id.closeFileNoSaveBtn).setBackgroundResource(R.drawable.darken_background_right_bottom);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    findViewById(R.id.closeFileNoSaveBtn).setBackgroundResource(R.drawable.popup_name_file);

                }
                return false;
            }
        });

        findViewById(R.id.noRestartButton).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    findViewById(R.id.noRestartButton).setBackgroundResource(R.drawable.darken_background_right_bottom);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    findViewById(R.id.noRestartButton).setBackgroundResource(R.drawable.popup_name_file);

                }
                return false;
            }
        });

        findViewById(R.id.closeGame2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animation2= AnimationUtils.loadAnimation(gameActivity.this,R.anim.move_animation_down);
                findViewById(R.id.messagePopupboxGame2).startAnimation(animation2);
                findViewById(R.id.messagePopupboxGame2).setVisibility(View.GONE);
            }
        });
        findViewById(R.id.closeGameRestart2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animation2= AnimationUtils.loadAnimation(gameActivity.this,R.anim.move_animation_down);
                findViewById(R.id.messagePopupboxGameRestart2).startAnimation(animation2);
                findViewById(R.id.messagePopupboxGameRestart2).setVisibility(View.GONE);
            }
        });

        findViewById(R.id.closeFileNoSaveBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation2= AnimationUtils.loadAnimation(gameActivity.this,R.anim.move_animation_down);
                findViewById(R.id.messagePopupboxGame2).startAnimation(animation2);
                findViewById(R.id.messagePopupboxGame2).setVisibility(View.GONE);
            }
        });


        findViewById(R.id.closeGameLimitReached2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation2= AnimationUtils.loadAnimation(gameActivity.this,R.anim.move_animation_down);
                findViewById(R.id.messagePopupboxGameLimitReached2).startAnimation(animation2);
                findViewById(R.id.messagePopupboxGameLimitReached2).setVisibility(View.GONE);
            }
        });

        findViewById(R.id.exitFromGameBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(currentEntry == 0){
                    finish();
                }else {
                    Animation animation = AnimationUtils.loadAnimation(gameActivity.this, R.anim.move_animation);
                    findViewById(R.id.messagePopupboxGame2).startAnimation(animation);
                    findViewById(R.id.messagePopupboxGame2).setVisibility(View.VISIBLE);
                }
            }
        });

        findViewById(R.id.yesRestartbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(recordMap.isEmpty()){
                finish();
                }else {
                    Animation animation2 = AnimationUtils.loadAnimation(gameActivity.this, R.anim.move_animation_down);
                    findViewById(R.id.messagePopupboxGameRestart2).startAnimation(animation2);
                    findViewById(R.id.messagePopupboxGameRestart2).setVisibility(View.GONE);

                    Animation animationSa = AnimationUtils.loadAnimation(gameActivity.this, R.anim.move_animation);
                    findViewById(R.id.messagePopupboxGame2).startAnimation(animationSa);
                    findViewById(R.id.messagePopupboxGame2).setVisibility(View.VISIBLE);
                    yesRestartGame = true;
                }
            }
        });


        findViewById(R.id.noRestartButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation2= AnimationUtils.loadAnimation(gameActivity.this,R.anim.move_animation_down);
                findViewById(R.id.messagePopupboxGameRestart2).startAnimation(animation2);
                findViewById(R.id.messagePopupboxGameRestart2).setVisibility(View.GONE);
            }
        });



        findViewById(R.id.yesLimitReachedbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation2= AnimationUtils.loadAnimation(gameActivity.this,R.anim.move_animation_down);
                findViewById(R.id.messagePopupboxGameLimitReached2).startAnimation(animation2);
                findViewById(R.id.messagePopupboxGameLimitReached2).setVisibility(View.GONE);
                neverShowAgain=true;

            }
        });
        findViewById(R.id.noLimitReachedButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation2= AnimationUtils.loadAnimation(gameActivity.this,R.anim.move_animation_down);
                findViewById(R.id.messagePopupboxGameLimitReached2).startAnimation(animation2);
                findViewById(R.id.messagePopupboxGameLimitReached2).setVisibility(View.GONE);


                Animation animationSa = AnimationUtils.loadAnimation(gameActivity.this, R.anim.move_animation);
                findViewById(R.id.messagePopupboxGame2).startAnimation(animationSa);
                findViewById(R.id.messagePopupboxGame2).setVisibility(View.VISIBLE);
                neverShowAgain=true;
            }
        });


        findViewById(R.id.newPage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(gameActivity.this, R.anim.move_animation);
                findViewById(R.id.messagePopupboxGameRestart2).startAnimation(animation);
                findViewById(R.id.messagePopupboxGameRestart2).setVisibility(View.VISIBLE);
            }
        });
    }


    @Override
    public void onBackPressed() {

    }


    private void checkInput(String btnValue) {

        prevStepsList.add(step);
        prevTextList.add(btnValue);
        Values currValue = dataStore.get(step);
        String prediction = currValue.getPrediction();

            chronometer1.setBase(SystemClock.elapsedRealtime());
            chronometer1.stop();
            chronometer1.start();


        if (step == 0) {

            if (btnValue.equalsIgnoreCase(prediction)) {
                step = 2;
            } else {
                step = 4;
            }

        } else if (step == 2) {

            if (btnValue.equalsIgnoreCase(prediction)) {
                step = 4;
            } else {
                step = 16;
                newSetValue(2);
            }

        } else if (step == 4) {

            if (btnValue.equalsIgnoreCase(prediction)) {
                step = 2;
            } else {
                step = 7;
                newSetValue(4);
            }

        } else if (step == 6) {

            if (btnValue.equalsIgnoreCase(value1)) {
                step = 9;
            } else {
                step = 92;
            }

        } else if (step == 7) {

            if (btnValue.equalsIgnoreCase(prediction)) {
                step = 10;
            } else {
                step = 14;
                fL3LastSeenStep = 7;
                lastSeenStepList.add(fL3LastSeenStep);
            }

        } else if (step == 9) {

            if (btnValue.equalsIgnoreCase(value1)) {
                step = 19;
            } else {
                step = 49;
                newSetValue(fL3LastSeenStep);
            }

        } else if (step == 10) {

            if (btnValue.equalsIgnoreCase(prediction)) {
                step = 10;
            } else {
                step = 12;
                newSetValue(10);
            }

        } else if (step == 12) {

            if (btnValue.equalsIgnoreCase(prediction)) {
                step = 4;
            } else {
                step = 16;
                newSetValue(12);
            }

        } else if (step == 121) { //step-121 means step-12 in M3S+ flowchart

            if (btnValue.equalsIgnoreCase(value1)) {
                step = 14;
            } else {
                step = 39;
                newSetValue(fL3LastSeenStep);
            }

        } else if (step == 14) {

            if (btnValue.equalsIgnoreCase(value1)) {
                step = 19;
            } else {
                step = 161;
            }

        } else if (step == 161) { //step-161 means step-16 in M3S+ flowchart

            if (btnValue.equalsIgnoreCase(value1)) {
                step = 52;
                newSetValue(fL3LastSeenStep);
            } else {
                step = 34;
            }

        } else if (step == 16) {
            if (btnValue.equalsIgnoreCase(prediction)) {
                step = 18;
//                dataStore.get(step).setValue(10);
            } else {
                step = 6;
                fL3LastSeenStep = 16;
                lastSeenStepList.add(fL3LastSeenStep);
            }

        } else if (step == 18) {

            if (btnValue.equalsIgnoreCase(prediction)) {
                step = 18;
                dataStore.get(step).setValue(value3);
            } else {
                step = 25;
                newSetValue(18);
            }

        } else if (step == 25) {

            if (btnValue.equalsIgnoreCase(prediction)) {
                step = 2;
            } else {
                step = 7;
                newSetValue(25);
            }

        } else if (step == 17) {

            if (btnValue.equalsIgnoreCase(prediction)) {
                step = 10;
            } else {
                step = 19;
                newSetValue(17);
            }

        } else if (step == 19) {

            if (btnValue.equalsIgnoreCase(value1)) {
                step = 70;
            } else {
                step = 6;
            }

        } else if (step == 34) {

            if (btnValue.equalsIgnoreCase(value1)) {
                step = 14;
            } else {
                step = 37;
            }

        } else if (step == 37) {

            if (btnValue.equalsIgnoreCase(value1)) {
                step = 14;
            } else {
                step = 39;
                newSetValue(fL3LastSeenStep);
            }

        } else if (step == 39) {

            if (btnValue.equalsIgnoreCase(prediction)) {
                step = 10;
            } else {
                step = 71;
                fL3LastSeenStep = 39;
                lastSeenStepList.add(fL3LastSeenStep);
            }

        } else if (step == 43) {

            if (btnValue.equalsIgnoreCase(prediction)) {
                step = 18;
            } else {
                step = 61;
                fL3LastSeenStep = 43;
                lastSeenStepList.add(fL3LastSeenStep);
            }

        } else if (step == 49) {

            if (btnValue.equalsIgnoreCase(prediction)) {
                step = 2;
            } else {
                step = 55;
                fL3LastSeenStep = 49;
                lastSeenStepList.add(fL3LastSeenStep);
            }

        } else if (step == 52) {

            if (btnValue.equalsIgnoreCase(prediction)) {
                step = 4;
            } else {
                step = 58;
                fL3LastSeenStep = 52;
                lastSeenStepList.add(fL3LastSeenStep);
            }

        } else if (step == 55) {

            if (btnValue.equalsIgnoreCase(value1)) {
                step = 9;
            } else {
                step = 121;
            }

        } else if (step == 58) {

            if (btnValue.equalsIgnoreCase(value1)) {
                step = 70;
            } else {
                step = 161;
            }

        } else if (step == 61) {

            if (btnValue.equalsIgnoreCase(value1)) {
                step = 52;
                newSetValue(fL3LastSeenStep);
            } else {
                step = 64;
            }

        } else if (step == 64) {

            if (btnValue.equalsIgnoreCase(value1)) {
                step = 14;
            } else {
                step = 67;
                newSetValue(fL3LastSeenStep);
            }

        } else if (step == 67) {

            if (btnValue.equalsIgnoreCase(prediction)) {
                step = 10;
            } else {
                step = 14;
                fL3LastSeenStep = 67;
                lastSeenStepList.add(fL3LastSeenStep);
            }

        } else if (step == 70) {

            if (btnValue.equalsIgnoreCase(value1)) {
                step = 43;
                newSetValue(fL3LastSeenStep);
            } else {
                step = 6;
            }

        } else if (step == 71) {

            if (btnValue.equalsIgnoreCase(value1)) {
                step = 80;
            } else {
                step = 89;
                newSetValue(fL3LastSeenStep);
            }

        } else if (step == 80) {

            if (btnValue.equalsIgnoreCase(value1)) {
                step = 83;
                newSetValue(fL3LastSeenStep);
            } else {
                step = 6;
            }

        } else if (step == 83) {

            if (btnValue.equalsIgnoreCase(prediction)) {
                step = 18;
            } else {
                step = 6;
                fL3LastSeenStep = 83;
                lastSeenStepList.add(fL3LastSeenStep);
            }

        } else if (step == 89) {

            if (btnValue.equalsIgnoreCase(prediction)) {
                step = 2;
            } else {
                step = 55;
                fL3LastSeenStep = 89;
                lastSeenStepList.add(fL3LastSeenStep);
            }

        } else if (step == 92) {

            if (btnValue.equalsIgnoreCase(value1)) {
                step = 14;
            } else {
                step = 121;
            }

        }
            updateData(step);

    }

    private void updateData(int currentStep) {
        Values newValue = dataStore.get(step);
        String newlyPrediction = newValue.getPrediction();
        int newlyValue = newValue.getValue();

        if(newValue.getPrediction().contains("#")){
            showLabel.setBackgroundColor(Color.parseColor(newValue.getPrediction()));

            String prediction1 = newValue.getPrediction();

            if(prediction1.equals("#FB2D5E")){
                showLabel.setText("RED");
                showLabel.setTextColor(Color.argb(0,251,45,94));
                showLabel.setBackgroundResource(R.drawable.top_radius_red_color);
            }else if(prediction1.equals("#4CCC51")){
                showLabel.setText("GREEN");
                showLabel.setTextColor(Color.argb(0,76,204,81));
                showLabel.setBackgroundResource(R.drawable.top_radius_green_color);
            }else if(prediction1.equals("#2A7BF4")){
                showLabel.setText("BLUE");
                showLabel.setTextColor(Color.argb(0,42,123,244));
                showLabel.setBackgroundResource(R.drawable.top_radius_blue_color);
            }
        }else{
            showLabel.setTextColor(Color.rgb(66,88,90));
            showLabel.setBackgroundResource(R.drawable.btn_border_only_left_top);
            showLabel.setText(newlyPrediction);
        }
            valueLabel.setText(String.valueOf(newlyValue));
            undoList.add(new Values(step, newlyPrediction, newlyValue));
    }





    private void newSetValue(int initialStep){
        Values currentStep = dataStore.get(step);
        int multipliedValue = dataStore.get(initialStep).getValue() * 2;

        if(multipliedValue >= value3*Math.pow(2, MainActivity.iterationCount)){
            currentStep.setValue(value3);
        }else{
            currentStep.setValue(multipliedValue);
        }
    }


    private void checkIfEntryLimitReached(int value){
        if(value > MainActivity.entryLimit && MainActivity.entryLimitAssigned == true ){

            TextView blinker = findViewById(R.id.redBlinker);
            blinker.setVisibility(View.VISIBLE);
            Animation animation2= AnimationUtils.loadAnimation(gameActivity.this,R.anim.keep_blinking);
            findViewById(R.id.redBlinker).startAnimation(animation2);

            if(neverShowAgain == false){

                Animation limitPopup= AnimationUtils.loadAnimation(gameActivity.this,R.anim.move_animation);
                findViewById(R.id.messagePopupboxGameLimitReached2).startAnimation(limitPopup);
                findViewById(R.id.messagePopupboxGameLimitReached2).setVisibility(View.VISIBLE);
                neverShowAgain=true;
            }
        }


    }

    private void checkForBlinkerStopWhenUndo(int value){
        if(value <= MainActivity.entryLimit) {
            TextView blinker = findViewById(R.id.redBlinker);
            blinker.setVisibility(View.INVISIBLE);
            Animation animation2 = AnimationUtils.loadAnimation(gameActivity.this, R.anim.blinker_stop);
            blinker.startAnimation(animation2);
        }
    }

}