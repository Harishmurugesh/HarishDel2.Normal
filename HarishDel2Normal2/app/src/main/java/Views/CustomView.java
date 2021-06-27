package Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.harishdel2normal.R;

public class CustomView extends View {

    private int breadth = 200;
    private int radius = 100 ;
    private int y3 = 20 ;
    public int z = 100 , w = 500;
    private int plus = 20;
    private Rect rect;
    private Paint paintrect;
    private Paint paintcircle;
    private float x1 = 100f , y1 = 100f ;
    public float x2 = 0f ;
    private int y2 , x3;
    public int x , y;


    public int count = 0;
    public int rect_breadth , circle_radius , a,b,cx,cy,c;

    public CustomView(Context context) {
        super(context);
        init(null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private  void init (@Nullable AttributeSet set){

        paintcircle = new Paint();
        paintcircle.setAntiAlias(true);
        paintcircle.setColor(Color.GREEN);
        paintrect = new Paint();
        paintrect.setAntiAlias(true);
        paintrect.setColor(Color.RED);
        rect = new Rect();

        if (set == null)
            return;

        TypedArray ta = getContext().obtainStyledAttributes(set, R.styleable.CustomView);

        rect_breadth = ta.getDimensionPixelSize(R.styleable.CustomView_breadth,breadth);
        circle_radius = ta.getDimensionPixelSize(R.styleable.CustomView_radius,radius);
        a = ta.getDimensionPixelSize(R.styleable.CustomView_a,plus);
        b = ta.getDimensionPixelSize(R.styleable.CustomView_b,plus);
        cx = ta.getDimensionPixelSize(R.styleable.CustomView_cx,w);
        cy = ta.getDimensionPixelSize(R.styleable.CustomView_cy,z);
        c = ta.getDimensionPixelSize(R.styleable.CustomView_c,y3);
        ta.recycle();

        rect.left = 0;
        rect.right = 2 * rect_breadth;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        rect.top= getHeight() - rect_breadth;
        rect.bottom = getHeight() ;

        canvas.drawCircle(cx , cy , circle_radius, paintcircle);
        canvas.drawRect(rect, paintrect);

        x2 = getWidth() - circle_radius;
        y2 = getHeight() - circle_radius - rect_breadth ;
        x3 = getHeight() - (rect_breadth) - circle_radius + c;
    }

    public void move() {


        if(cx > x2){a=-a;}
        if(cx < x1){a=-a;}
        if(cy < y1){b=-b;}
        if((cy > y2)&&(cy < x3)){
            if((cx > (rect.left))&&(cx < (rect.right)))
            { b=-b;
                count++; }
        }

        cx = cx + a;
        cy = cy + b;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction()== MotionEvent.ACTION_MOVE){
            x = (int) event.getX();
            y = (int) event.getY();
            if ((rect.left < x)&&(rect.right > x)){
                if((rect.top < y)&&(rect.bottom > y)) {
                    rect.left = x - rect_breadth;
                    rect.right = x + rect_breadth;
                    postInvalidate();

                }  }}
        return true;
    }
}
