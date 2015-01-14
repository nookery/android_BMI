package com.example.bmi;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//检测按钮是否按下
		findViews();
		setListeners();
	}
	
	private Button button_calc;
	private EditText field_height;
	private EditText field_weight;
	private TextView view_result;
	private TextView view_suggest;
	
	private void findViews() {
		button_calc = (Button) findViewById(R.id.submit);
		field_height = (EditText) findViewById(R.id.height);
		field_weight = (EditText) findViewById(R.id.weight);
		view_result = (TextView) findViewById(R.id.result);
		view_suggest = (TextView) findViewById(R.id.suggest);
	}
	
	private void setListeners() {
		button_calc.setOnClickListener(calcBMI);
	}
	
	private OnClickListener calcBMI = new OnClickListener() {
		public void onClick(View v) {
			DecimalFormat nf = new DecimalFormat("0.00");
			try {
				double height = Double.parseDouble(field_height.getText().toString())/100;
				double weight = Double.parseDouble(field_weight.getText().toString());
				double BMI = weight / (height * height);
				
				view_result.setText(getText(R.string.bmi_result)+ nf.format(BMI));
				
				if (BMI > 25) {
					view_suggest.setText(R.string.advice_heavy);
				} else if (BMI < 20) {
					view_suggest.setText(R.string.advice_light);
				} else {
					view_suggest.setText(R.string.advice_average);
				}
				
				openOptionsDialog();
			} catch (Exception obj) {
				Toast.makeText(MainActivity.this, "只能输入数字", Toast.LENGTH_SHORT).show();
			}

 		}
	};
	
	private void openOptionsDialog() {
		//Toast.makeText(MainActivity.this, "BMI计算器", Toast.LENGTH_SHORT).show();
		new AlertDialog.Builder(MainActivity.this)
			.setTitle(R.string.about_title)
			.setMessage(R.string.about_msg)
			.setPositiveButton(R.string.OK_label,
					new DialogInterface.OnClickListener() {
				public void onClick(
						DialogInterface dialoginterface, int i){}
			})
			.setNegativeButton(R.string.homepage_label, 
					new DialogInterface.OnClickListener(){
						public void onClick(
								DialogInterface dialoginterface, int i) {
							//go to url
							Uri uri = Uri.parse(getString(R.string.homepage_uri));
							Intent intent = new Intent(Intent.ACTION_VIEW, uri);
							startActivity(intent);
						}
			})
			.show();
	}
}
