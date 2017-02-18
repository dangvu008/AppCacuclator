package com.dang.agi.appcacuclator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.dang.agi.appcacuclator.R.id.btn0;
import static com.dang.agi.appcacuclator.R.id.btn1;
import static com.dang.agi.appcacuclator.R.id.btn2;
import static com.dang.agi.appcacuclator.R.id.btn3;
import static com.dang.agi.appcacuclator.R.id.btn4;
import static com.dang.agi.appcacuclator.R.id.btn5;
import static com.dang.agi.appcacuclator.R.id.btn6;
import static com.dang.agi.appcacuclator.R.id.btn7;
import static com.dang.agi.appcacuclator.R.id.btn8;
import static com.dang.agi.appcacuclator.R.id.btn9;
import static com.dang.agi.appcacuclator.R.id.btnBang;
import static com.dang.agi.appcacuclator.R.id.btnCham;
import static com.dang.agi.appcacuclator.R.id.btnChia;
import static com.dang.agi.appcacuclator.R.id.btnCong;
import static com.dang.agi.appcacuclator.R.id.btnDelete;
import static com.dang.agi.appcacuclator.R.id.btnNhan;
import static com.dang.agi.appcacuclator.R.id.btnTru;
import static com.dang.agi.appcacuclator.R.id.btnnull;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    float ketquaDangTinh,sothuNhat,soThuhai;
    String chuoiNhap = "",chuoiKetQua="",pheptinh="";
    EditText editketQua,editNhap;
    int[] iButtons;
    Button btnNull;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iButtons = new int[]{btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,
                btn8,btn9,btnCong,btnTru,btnNhan,btnChia,btnBang,btnCham,btnDelete,btnnull};
        for (int i = 0; i < iButtons.length; i++) {
            View view = findViewById(iButtons[i]);
            view.setOnClickListener(this);
        }
        editketQua = (EditText) findViewById(R.id.editKetqua);
        editNhap = (EditText) findViewById(R.id.editNhap);
        btnNull = (Button) findViewById(R.id.btnnull);
    }

    @Override
    public void onClick(View v) {
        chuoiKetQua = editketQua.getText().toString();
        chuoiNhap = editNhap.getText().toString();
        switch (v.getId()){
            case btnCong:{
                tinhToan("+");
            }break;
            case btnTru:{
               tinhToan("-");
            }break;
            case btnNhan:{
               tinhToan("*");
            }break;
            case btnChia:{
              tinhToan("/");
            }break;
            case btnBang:{
                if (chuoiNhap !=""){
                    if (chuoiNhap.contains(pheptinh)&& !chuoiNhap.endsWith(pheptinh) && !chuoiNhap.startsWith(pheptinh)){
                        editketQua.setText(String.valueOf(ketquaDangTinh));
                    }
                }
            }break;
            case btnDelete:{
                if (chuoiNhap !=""){
                  chuoiNhap =  chuoiNhap.substring(0,chuoiNhap.length()-1);
                    editketQua.setText("");
                }
            }break;
            case btnnull:{
                if (chuoiNhap !=""){
                  editketQua.setText("");
                    editNhap.setText("");
                }
            }break;
            default :{
                String number = ((Button)v).getText().toString();
                chuoiNhap +=number;
            }break;

        }
        if (pheptinh!="" && !chuoiNhap.endsWith(pheptinh)){
            String strCong[] = chuoiNhap.split("\\"+pheptinh);
            if (strCong.length==2) {
                soThuhai = Float.parseFloat(strCong[1]);
                ketquaDangTinh = ketquadangtinh(sothuNhat, soThuhai, pheptinh);
                editketQua.setText(String.valueOf(ketquaDangTinh));
            }

        }
        editNhap.setText(chuoiNhap);



    }
    private void tinhToan(String pheptinhtoan){
        pheptinh = pheptinhtoan;
        if(checkLastCharacter(chuoiNhap)&&chuoiNhap.length()!=0) {
            chuoiNhap += pheptinhtoan;
            String[] sstrs = chuoiNhap.split("\\" + pheptinhtoan);
            if (ketquaDangTinh == 0) {
                if (sstrs.length == 1) {
                    sothuNhat = Float.parseFloat(sstrs[0]);
                    Log.d("sothunhat", String.valueOf(sothuNhat));
                }else if (sstrs.length==2){
                    sothuNhat = Float.parseFloat(sstrs[0]);
                    soThuhai = Float.parseFloat(sstrs[1]);
                }
            }else{
                if (sstrs.length==2){
                   chuoiKetQua= editketQua.getText().toString();
                    if (chuoiNhap.contains(pheptinhtoan)  && !chuoiNhap.startsWith(pheptinhtoan)) {
                        chuoiNhap = "";
                        chuoiNhap += ketquaDangTinh;
                        chuoiNhap += pheptinh;
                        editNhap.setText(chuoiNhap);
                        editketQua.setText(String.valueOf(ketquaDangTinh));
                        Log.d("chuoinhap", chuoiNhap);
                        Log.d("chuoikq", String.valueOf(ketquaDangTinh));
                    }
                }
            }
        }

    }
    public boolean checkLastCharacter(String chuoi){
        if (chuoi.trim().endsWith("+")||chuoi.trim().endsWith("-")||chuoi.trim().endsWith("x")
                ||chuoi.trim().endsWith("/")){
            return false;
        }
        return true;
    }
    public float ketquadangtinh(Float thamso1,Float thamso2,String pheptinh){
        float kq =0;
        switch (pheptinh){
            case "+":{
                kq = thamso1+thamso2;
            }break;
            case "-":{
                kq = thamso1-thamso2;
            }break;
            case "*":{
                kq = thamso1*thamso2;
            }break;
            case "/":{
                kq = thamso1/thamso2;
            }break;
            default:
                break;
        }
       return  kq;
    }
}
