package no.itera.app.makeadifference.ui;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.util.AttributeSet;

public class SearchDrawerView extends NavigationView {

  public SearchDrawerView(Context context) {
    this(context, null);
  }

  public SearchDrawerView(Context context, AttributeSet attributeSet) {
    this(context, attributeSet, 0);
  }

  public SearchDrawerView(Context context, AttributeSet attributeSet, int defStyle) {
    super(context, attributeSet, defStyle);
  }

}
