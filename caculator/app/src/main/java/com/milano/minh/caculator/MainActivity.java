package com.milano.minh.caculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editNumber;
    private TextView tvResult;

    private Button btnNumber1;
    private Button btnNumber2;
    private Button btnNumber3;
    private Button btnNumber4;
    private Button btnNumber5;
    private Button btnNumber6;
    private Button btnNumber7;
    private Button btnNumber8;
    private Button btnNumber9;
    private Button btnNumber0;


    private Button btnAdd;
    private Button btnSub;
    private Button btnMul;
    private Button btnDiv;

    private Button btnPoint;
    private Button btnClear;
    private Button btnClearAll;
    private Button btnResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        setEventClickView();

    }

    public void initWidget() {
        editNumber = (EditText) findViewById(R.id.edit_text);
        tvResult = (TextView) findViewById(R.id.tv_result);

        btnNumber1 = (Button) findViewById(R.id.btnNumber1);
        btnNumber2 = (Button) findViewById(R.id.btnNumber2);
        btnNumber3 = (Button) findViewById(R.id.btnNumber3);
        btnNumber4 = (Button) findViewById(R.id.btnNumber4);
        btnNumber5 = (Button) findViewById(R.id.btnNumber5);
        btnNumber6 = (Button) findViewById(R.id.btnNumber6);
        btnNumber7 = (Button) findViewById(R.id.btnNumber7);
        btnNumber8 = (Button) findViewById(R.id.btnNumber8);
        btnNumber9 = (Button) findViewById(R.id.btnNumber9);
        btnNumber0 = (Button) findViewById(R.id.btnNumber0);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnMul = (Button) findViewById(R.id.btnMul);

        btnPoint = (Button) findViewById(R.id.btnPoint);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnClearAll = (Button) findViewById(R.id.btnClearAll);
        btnResult = (Button) findViewById(R.id.btnResult);

    }

    // Set sự kiện cho button
    public void setEventClickView () {
        btnNumber1.setOnClickListener(this);
        btnNumber2.setOnClickListener(this);
        btnNumber3.setOnClickListener(this);
        btnNumber4.setOnClickListener(this);
        btnNumber5.setOnClickListener(this);
        btnNumber6.setOnClickListener(this);
        btnNumber7.setOnClickListener(this);
        btnNumber8.setOnClickListener(this);
        btnNumber9.setOnClickListener(this);
        btnNumber0.setOnClickListener(this);

        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnDiv.setOnClickListener(this);

        btnPoint.setOnClickListener(this);
        btnClearAll.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnResult.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNumber0:
                editNumber.append("0");
                break;
            case R.id.btnNumber1:
                editNumber.append("1");
                break;
            case R.id.btnNumber2:
                editNumber.append("2");
                break;
            case R.id.btnNumber3:
                editNumber.append("3");
                break;
            case R.id.btnNumber4:
                editNumber.append("4");
                break;
            case R.id.btnNumber5:
                editNumber.append("5");
                break;
            case R.id.btnNumber6:
                editNumber.append("6");
                break;
            case R.id.btnNumber7:
                editNumber.append("7");
                break;
            case R.id.btnNumber8:
                editNumber.append("8");
                break;
            case R.id.btnNumber9:
                editNumber.append("9");
                break;

            case R.id.btnAdd:
                editNumber.append("+");
                break;
            case R.id.btnSub:
                editNumber.append("-");
                break;
            case R.id.btnMul:
                editNumber.append("*");
                break;
            case R.id.btnDiv:
                editNumber.append("/");
                break;

            case R.id.btnClear:
                String numberAfterRemove= deleteNumber(editNumber.getText().toString());
                tvResult.setText(numberAfterRemove);
                break;
            case R.id.btnClearAll:
                editNumber.setText("");
                tvResult.setText("");
                break;
            case R.id.btnResult:
                //To do
                DecimalFormat df = new DecimalFormat("###.#######");
                double result = 0;
                addOperation(editNumber.getText().toString());
                addNumber(editNumber.getText().toString());
                // Thuật toán tính toán biểu thức
                if(arrOperation.size()>=arrNumber.size() ||arrOperation.size()<1){
                    tvResult.setText("Lỗi định dạng");
                }else {
                    for (int i = 0; i < (arrNumber.size() - 1); i++) {
                        switch (arrOperation.get(i)) {
                            case "+":
                                if (i == 0) {
                                    result = arrNumber.get(i) + arrNumber.get(i + 1);
                                } else {
                                    result = result + arrNumber.get(i + 1);
                                }
                                break;
                            case "-":
                                if (i == 0) {
                                    result = arrNumber.get(i) - arrNumber.get(i + 1);
                                } else {
                                    result = result - arrNumber.get(i + 1);
                                }
                                break;
                            case "*":
                                if (i == 0) {
                                    result = arrNumber.get(i) * arrNumber.get(i + 1);
                                } else {
                                    result = result * arrNumber.get(i + 1);
                                }
                                break;
                            case "/":
                                if (i == 0) {
                                    result = arrNumber.get(i) / arrNumber.get(i + 1);
                                } else {
                                    result = result / arrNumber.get(i + 1);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    tvResult.setText(df.format(result) + "");
                }
                break;
            case R.id.btnPoint:
                editNumber.append(".");
                break;
        }
    }

    //Ham cat chuoi de thuc hien cho hanh dong clear number
    public String deleteNumber(String num) {
        int length = num.length();
        String temp = num.substring(0, length-1);
        return temp;
    }

    //Mảng chứa các phép tính +, - , x, /
    public ArrayList<String> arrOperation;
    //Mảng chứa các số
    public ArrayList<Double> arrNumber;

    //Lấy tất cả các phép tính lưu vào mảng arrOperation
    public int addOperation(String input) {
        arrOperation = new ArrayList<>();

        char[] cArray = input.toCharArray();
        for (int i = 0; i < cArray.length; i++) {
            switch (cArray[i]) {
                case '+':
                    arrOperation.add(cArray[i] + "");
                    break;
                case '-':
                    arrOperation.add(cArray[i] + "");
                    break;
                case '*':
                    arrOperation.add(cArray[i] + "");
                    break;
                case '/':
                    arrOperation.add(cArray[i] + "");
                    break;
                default:
                    break;
            }
        }
        return 0;
    }
    //Lấy tất cả các số lưu vào mảng arrNumber
    public void addNumber(String stringInput) {
        arrNumber = new ArrayList<>();
        Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
        Matcher matcher = regex.matcher(stringInput);
        while(matcher.find()){
            arrNumber.add(Double.valueOf(matcher.group(1)));
        }
    }
}
