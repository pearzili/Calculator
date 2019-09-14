package com.example.calculator;

import android.app.Activity;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
public class MainActivity extends Activity implements View.OnClickListener {
    private  Button zero;
    private  Button one;
    private  Button two;
    private  Button three;
    private  Button four;
    private  Button five;
    private  Button six;
    private  Button seven;
    private  Button eight;
    private  Button nine;
    //运算符
    private  Button add;
    private  Button sub;
    private  Button mul;
    private  Button div;
    private  Button dot;
    private  Button equal;

    //清除
    private  Button clear;
    private  Button delete;
    boolean clean;//清空标志
    //文本框
    private  EditText showtext;
    private  EditText resulttext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //数字
         zero=(Button)findViewById(R.id.zero);
         one=(Button)findViewById(R.id.one);
         two=(Button)findViewById(R.id.two);
         three=(Button)findViewById(R.id.three);
         four=(Button)findViewById(R.id.four);
         five=(Button)findViewById(R.id.five);
         six=(Button)findViewById(R.id.six);
         seven=(Button)findViewById(R.id.seven);
         eight=(Button)findViewById(R.id.eight);
         nine=(Button)findViewById(R.id.nine);
        //清除
         clear=(Button)findViewById(R.id.clear);
        //运算符
         div=(Button)findViewById(R.id.div);
         mul=(Button)findViewById(R.id.mul);
         sub=(Button)findViewById(R.id.sub);
         add=(Button)findViewById(R.id.add);
         equal=(Button)findViewById(R.id.equal);
         dot=(Button)findViewById(R.id.dot);
        delete=(Button)findViewById(R.id.delete);
        //文本框
        showtext=(EditText)findViewById(R.id.showtext);
        //绑定时间
        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        //运算符
        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        div.setOnClickListener(this);
        mul.setOnClickListener(this);
        dot.setOnClickListener(this);
        equal.setOnClickListener(this);
        clear.setOnClickListener(this);
        delete.setOnClickListener(this);
    }
    //读取每个按钮内容
    public void onClick(View view) {
        //获取文本内容
        String input = showtext.getText().toString();
        switch (view.getId()) {
            case R.id.zero:
                input+="0";
                showtext.setText(input);
            case R.id.one:
                input+="1";
                showtext.setText(input);
            case R.id.two:
                input+="2";
                showtext.setText(input);
            case R.id.three:
                input+="3";
                showtext.setText(input);
            case R.id.four:
                input+="4";
                showtext.setText(input);
            case R.id.five:
                input+="5";
                showtext.setText(input);
            case R.id.six:
                input+="6";
                showtext.setText(input);
            case R.id.seven:
                input+="7";
                showtext.setText(input);
            case R.id.eight:
                input+="8";
                showtext.setText(input);
            case R.id.nine:
                input+="9";
                showtext.setText(input);
            case R.id.dot://小数点
                if (input.length()==0||input.indexOf(" ")==input.length()-3||input.lastIndexOf(".")>input.lastIndexOf(" "))
                {
                    break;
                }
                else
                {
                    input+=".";
                    showtext.setText(input);
                }
                break;
            case R.id.delete://退格
                if (clean) {
                    clean = false;
                    input = "";
                    showtext.setText("");
                } else if (input != null&&!input.equals("")) {
                    showtext.setText(input.substring(0, input.length() - 1));
                }
                break;
            case R.id.add:
                if(input.length()==0)
                {
                    break;
                }
                if(input.contains(" "))
                {
                    if(input.indexOf(" ")==input.length()-3||input.indexOf(" ")==input.length()-2||input.indexOf(" ")==input.length()-1)
                        break;
                    GetResult();
                }

            case R.id.sub:
            case R.id.mul:
            case R.id.div:
                if (clean) {
                    clean = false;
                    input = "";
                    showtext.setText("");
                }
                if(input.contains("+")||input.contains("-")||input.contains("*")||input.contains("/"))
                    input=input.substring(0,input.indexOf(" "));
                showtext.setText(input + "" + ((Button) view).getText() + " ");
                break;
            case R.id.clear:
                if(clean)
                    clean = false;
                input = "";
                showtext.setText("");
                break;
            case R.id.equal:
                GetResult();
                break;

        }
    }
        public void GetResult()//计算"="结果
        {
            String exp=showtext.getText().toString();//获取文本框内容
            if(exp==null||exp.equals("")){
                return;
            }
            if(!exp.contains("")){
                return;
            }
            if(clean){
                clean=false;
                return;
            }
            clean=true;
            double result=0;
            //进行截取
            //运算符前的数字
            String s1=exp.substring(0,exp.indexOf(" "));
            //运算符
            String op=exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2);
            //运算符后的数字
            String s2=exp.substring(exp.indexOf(" ")+3);

            if(!s1.equals("")&&!s2.equals("")){
                //如果包含小数点的运算
                double d1=Double.parseDouble(s1);//则数字都是double类型
                double d2=Double.parseDouble(s2);

                if(op.equals("+")){
                    //如果是+
                    result=d1+d2;
                }else if(op.equals("-")){
                    //如果是-
                    result=d1-d2;
                }else if(op.equals("*")){
                    //如果是*
                    result=d1*d2;
                }else if(op.equals("/")){
                    if(d2==0){
                        //如果被除数是0
                        result=0;//则结果为0
                    }
                    else {
                        //否则执行正常运算
                        result=d1/d2;
                    }
                }
                if(!s1.contains(".") &&!s2.contains(".")&&!op.equals("/")){
                    //如果是整数类型
                    int r=(int)result;
                    showtext.setText(r+"");
                }else {
                    showtext.setText(result+"");
                }
            }else  if(!s1.equals("")&& s2.equals("")){
                //如果只输入运算符前的数字
                showtext.setText(exp);//直接返回当前输入内容
            }else if (s1.equals("")&& !s2.equals("")){
                //如果是只输入运算符后面的数
                double d2 =Double.parseDouble(s2);

                //运算符当前没有输入数字
                if(op.equals("+")){
                    result= 0 + d2;
                }else  if(op.equals("-")){
                    result= 0 - d2;
                }else if (op.equals("*")){
                    result= 0;
                }else  if(op.equals("/")){
                    result= 0;
                }
                if(!s1.contains(".")&&!s2.contains(".")){
                    int r=(int) result;
                    seven.setText(r+"");
                }else {
                    showtext.setText(result+"");
                }
            }else {
                showtext.setText("");

     }
  }

}
