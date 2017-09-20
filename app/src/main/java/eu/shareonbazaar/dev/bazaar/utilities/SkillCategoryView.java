package eu.shareonbazaar.dev.bazaar.utilities;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import eu.shareonbazaar.dev.bazaar.R;

public class SkillCategoryView extends TextView {
    public SkillCategoryView(Context context) {
        super(context);
    }

    public SkillCategoryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SkillCategoryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*public void setBackgroudColor() {
        Resources res = this.getContext().getResources();
        String[] colors = res.getStringArray(R.array.categoryColors);
        int myCol = Color.parseColor(colors[dangerLevel - 1]);

        res.get
        Drawable mDrawable = res.getDrawable(R.drawable.circle,this.getContext().getTheme());
        mDrawable.setColorFilter(myCol, PorterDuff.Mode.SRC);

        this.setBackground(mDrawable);
        this.setText(String.valueOf(getDangerLevel()));
    }*/

    private void generateRandomNumber(){

    }
}
