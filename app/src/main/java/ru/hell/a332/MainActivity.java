package ru.hell.a332;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private ImageView image;
    private Spinner select, chTheme, chMarg;
    private Button ok;
    private TextView text;
    private String param = "ru";
    private static int currentThemeRes = -1, set, mrg;
    int margin;
    /*float textSize;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (currentThemeRes != -1){
            setTheme(currentThemeRes);
        }

        setContentView(R.layout.activity_main);

        init();

        /*final ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(text.getMaxWidth(), text.getMaxHeight());*/

        final ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) text.getLayoutParams();


        ArrayAdapter<?> arrCh = ArrayAdapter.createFromResource( this, R.array.language, android.R.layout.simple_spinner_item);
        arrCh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        select.setAdapter(arrCh);

        ArrayAdapter<?> arrTh = ArrayAdapter.createFromResource( this, R.array.theme, android.R.layout.simple_spinner_item);
        arrTh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        chTheme.setAdapter(arrTh);

        ArrayAdapter<?> arrMg = ArrayAdapter.createFromResource( this, R.array.margin, android.R.layout.simple_spinner_item);
        arrMg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        chMarg.setAdapter(arrMg);

        ok.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                set = chTheme.getSelectedItemPosition();
                switch (set) {
                    case 0:
                        currentThemeRes = R.style.AppTheme;
                        break;
                    case 1:
                        currentThemeRes = R.style.AppTheme_Green;
                        break;
                    case 2:
                        currentThemeRes =  R.style.AppTheme_Dark;
                        break;
                    case 3:
                        currentThemeRes = R.style.AppTheme_Blue;
                        break;
                    default:
                        break;
                }

                mrg = chMarg.getSelectedItemPosition();
                switch (mrg) {
                    case 0:
                        margin=getResources().getDimensionPixelSize(R.dimen.activity_vertical_margin_small);
                        params.topMargin = margin;
                        params.leftMargin = margin;
                        params.bottomMargin = margin;
                        params.rightMargin = margin;

             /*           textSize = getResources().getDimension(R.dimen.text_size_small);
                        text.setTextSize(textSize);
                        text.setPadding(R.dimen.activity_horizontal_margin_small, R.dimen.activity_vertical_margin_small,R.dimen.activity_horizontal_margin_small, R.dimen.activity_vertical_margin_small);*/
/*                        layoutParams.setMargins(5,5,5,5);
                        text.setLayoutParams(layoutParams);*/
                        break;
                    case 1:
                        margin=getResources().getDimensionPixelSize(R.dimen.activity_vertical_margin_medium);
                        params.topMargin = margin;
                        params.leftMargin = margin;
                        params.bottomMargin = margin;
                        params.rightMargin = margin;
             /*           textSize = getResources().getDimension(R.dimen.text_size_medium);
                        text.setTextSize(textSize);
                        text.setPadding(R.dimen.activity_horizontal_margin_medium, R.dimen.activity_vertical_margin_medium,R.dimen.activity_horizontal_margin_medium, R.dimen.activity_vertical_margin_medium);*/
/*                        layoutParams.setMargins(15,15,15,15);
                        text.setLayoutParams(layoutParams);*/
                        break;
                    case 2:
                        margin=getResources().getDimensionPixelSize(R.dimen.activity_vertical_margin_big);
                        params.topMargin = margin;
                        params.leftMargin = margin;
                        params.bottomMargin = margin;
                        params.rightMargin = margin;
                        text.setLayoutParams(params);
           /*             textSize = getResources().getDimension(R.dimen.text_size_big);
                        text.setTextSize(textSize);
                        text.setPadding(R.dimen.activity_horizontal_margin_big, R.dimen.activity_vertical_margin_big,R.dimen.activity_horizontal_margin_big, R.dimen.activity_vertical_margin_big);*/
/*                        layoutParams.setMargins(50,50,50,50);
                        text.setLayoutParams(layoutParams);*/
                        break;
                    default:
                        break;
                }

                param = select.getSelectedItem().toString();
                if(param.equals("Russisch") || param.equals("Russian") || param.equals("Русский")){
                    param = "ru";
                    changeLang(param);
                }
                if (param.equals("English") || param.equals("Английский") || param.equals("Englisch")){
                    param = "en";
                    changeLang(param);
                }
                if (param.equals("German") || param.equals("Немецкий") || param.equals("Deutsche")){
                    param = "de";
                    changeLang(param);
                }
            }
        });

    }

    private void init() {
        chMarg = findViewById(R.id.spinMarg);
        chTheme = findViewById(R.id.spinTheme);
        image = findViewById(R.id.imageFlag);
        select = findViewById(R.id.spinner);
        ok = findViewById(R.id.button);
        text = findViewById(R.id.text);
    }

    private void changeLang(String reg) {
        Locale locale = new Locale(reg);

        Configuration config = new Configuration();
        config.setLocale(locale);

        getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        recreate();
    }

}
