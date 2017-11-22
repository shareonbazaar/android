package eu.shareonbazaar.dev.bazaar.utilities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Random;

import eu.shareonbazaar.dev.bazaar.R;

@SuppressLint("AppCompatCustomView")
public class LabelView extends TextView{

    private char mLabel;

    public LabelView(Context context) {
        super(context);
    }

    public LabelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LabelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setLabel(char label) {
        mLabel = label;
        Resources res = this.getContext().getResources();
        String[] colors = res.getStringArray(R.array.labelColors);
        int myCol = Color.parseColor(colors[getColorIndex(colors.length)]);

//        Drawable mDrawable = res.getDrawable(R.drawable.background_label,this.getContext().getTheme());
        Drawable mDrawable = ContextCompat.getDrawable(this.getContext(), R.drawable.background_label);
        mDrawable.setColorFilter(myCol, PorterDuff.Mode.SRC);

        this.setBackground(mDrawable);
        this.setText(String.valueOf(getLabel()));
    }

    private int getColorIndex(int max){
        Random random = new Random();

        return random.nextInt(max);
    }

    public char getLabel() {
        return mLabel;
    }
}
