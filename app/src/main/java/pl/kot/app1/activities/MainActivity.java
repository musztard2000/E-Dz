package pl.kot.app1.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import pl.kot.app1.R;

public class MainActivity extends Activity {

    private TextView outputTextView;
    private EditText percentageEditText;
    private EditText numberEditText;
    private Button calculatePercentageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        inicjujKomponenty();

        calculatePercentageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float procent = Float.parseFloat(percentageEditText.getText().toString());
                float dec = procent / 100;
                float rezultat = dec * Float.parseFloat(numberEditText.getText().toString());
                outputTextView.setText(Float.toString(rezultat));
            }
        });
    }

    private void inicjujKomponenty() {
        outputTextView = (TextView) findViewById(R.id.totalTextView);
        percentageEditText = (EditText) findViewById(R.id.procentTextEdit);
        numberEditText = (EditText) findViewById(R.id.numberTextEdit);
        calculatePercentageButton = (Button) findViewById(R.id.calculatePercentageBtn);
    }
}
