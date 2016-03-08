package no.itera.app.makeadifference.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

  private int textViewId = -1;

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int monthOfYear = calendar.get(Calendar.MONTH);
    int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
    return new DatePickerDialog(getActivity(), this, year, monthOfYear, dayOfMonth);
  }

  @Override
  public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
    this.dismiss();
    Toast.makeText(view.getContext(), "Picked the following date: " + year + "-" + monthOfYear + "-" + dayOfMonth + ".", Toast.LENGTH_SHORT).show();
    if (textViewId != -1) {
      TextView output = (TextView) getActivity().findViewById(textViewId);
      String formattedYear = convertIntegerToString(year, 4);
      String formattedMonthOfYear = convertIntegerToString(monthOfYear + 1, 2);
      String formattedDayOfMonth = convertIntegerToString(dayOfMonth, 2);
      output.setText(String.format("%s-%s-%s", formattedYear, formattedMonthOfYear, formattedDayOfMonth));
    }
  }

  public void setOutputTextView(int textViewId) {
    this.textViewId = textViewId;
  }

  public String convertIntegerToString(int value, int numberOfDigits) {
    String outValue = Integer.toString(value);
    while (outValue.length() < numberOfDigits) {
      outValue = "0" + outValue;
    }
    return outValue;
  }
}
