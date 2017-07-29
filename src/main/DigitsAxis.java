package main;

import java.awt.Graphics2D;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.awt.Color;

import org.jfree.chart.axis.AxisState;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueTick;
import org.jfree.text.TextUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;

/**
 * Created by DM on 08.01.2017.
 */
public class DigitsAxis extends NumberAxis{
    public static final String[] DIGITS_NAMES = {
            "Дата/час                   ",
            "Швидкість",
            "Тиск НМ",
            "Тиск ГМ",
            "АРШ ввім.",
            "АЛС ввім.",
            "Радіост. ввім.",
            "ЕПК ввімк.",
            "РП відновл.",
            "Рух від КАХ",
            "Ввімк. ВАХ",
            "Ввімк. ВАД",
            "Ввімк. ОВТ",
            "Зачин. дверей",
            "КБ(ПБ)",
            "Відсутня част.",
            "Доп.шв.0 км/г",
            "Доп.шв. 40 км/г",
            "Доп.шв. 60 км/г",
            "Доп.шв. 70 км/г",
            "Доп.шв. 80 км/г",
            "Стоян. гальмо",
            "Ввімк.бл.перетв.",
            "КРП",
            "1 пп",
            "2 пп",
            "3 пп",
            "4 пп",
            "5 пп",
            "6 пп",
            "Провід Ф7",
            "8 пп",
            "18 пп",
            "20 пп",
            "25 пп",
            "34 пп",
            "48 пп",
            "АРШ",
            "ДАУ-АРШ",
            "ВУ",
            "Вибір напрямку"
    };

    protected AxisState  drawTickMarksAndLabels(Graphics2D g2,double cursor,Rectangle2D plotArea,Rectangle2D dataArea,RectangleEdge edge) {
        AxisState state = new AxisState(cursor);

        g2.setFont(getTickLabelFont());

        double ol = getTickMarkOutsideLength();
        double il = getTickMarkInsideLength();
        int y = (int)(Math.round(cursor-ol));
        LineMetrics lineMetrics = g2.getFont().getLineMetrics("Дата/час                   ", g2.getFontRenderContext());
        int h = (int) (lineMetrics.getHeight() + 6);

        List<ValueTick> ticks = refreshTicks(g2, state, dataArea, edge);
        state.setTicks(ticks);

        /* Last x point */
        ValueTick tick = ticks.get(ticks.size()-1);
        float[] prevAnchorPoint = calculateAnchorPoint(tick, cursor,dataArea, edge);
        double xmax = prevAnchorPoint[0];
        double max_day = tick.getValue();

        /* First x point */
        tick = ticks.get(0);
        prevAnchorPoint = calculateAnchorPoint(tick, cursor,dataArea, edge);
        double xmin = Math.round(prevAnchorPoint[0]);
        double min_day = tick.getValue();
        double days_visible = max_day - min_day + 1;
        /* 0.1 day horizontal gap. */
        double gap = 0.1*(xmax-xmin)/days_visible;

//        System.out.println("min_day "+min_day+" max_day"+max_day);

        g2.setFont(getTickLabelFont());
        g2.setColor(Color.BLACK);
        int start_day = 0;
        for (int month=0; month<360; month++) {
//            int end_day = start_day + MONTH_LENGTHS[month] - 1;
//            System.out.println("start-end "+start_day+" "+end_day);
//            if ( (start_day>=min_day) && (start_day<=max_day) && (end_day>=min_day) && (end_day<=max_day) ) {
//                double factor_x1 = (start_day - min_day) / days_visible;
//                double x1 = xmin + (xmax-xmin)* factor_x1;
//                double factor_x2 = (end_day - min_day) / days_visible;
//                double x2 = xmin + (xmax-xmin)* factor_x2;
//                System.out.println("month="+month+", start_day="+start_day+" end_day="+end_day+" x1="+x1+" x2="+x2);
//                g2.setColor(Color.LIGHT_GRAY);
//                g2.fill3DRect((int)(x1+gap),y,(int)(x2-x1-2*gap),h,true);
//                g2.setColor(Color.BLACK);
//                TextUtilities.drawAlignedString(DIGITS_NAMES[month], g2, (float)((x1+x2)/2), (float)(y+ol), TextAnchor.TOP_CENTER);
            }
//            start_day += MONTH_LENGTHS[month];
//        }
        return state;
    }

}
