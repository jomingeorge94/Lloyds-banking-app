package ncl.ac.uk.cs.teamone.lloydsstudent;

import com.github.mikephil.charting.utils.ValueFormatter;

/**
 * Created by Dan on 28/03/2015.
 */
public class CurrencyFormatter implements ValueFormatter {
    @Override
    public String getFormattedValue(float value) {
        return String.format("£%.2f", value);
    }
}
